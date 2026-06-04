package umg.edu.progra.arboles;

/**
 * Arbol Binario de Busqueda (BST) implementado manualmente,
 * sin utilizar java.util ni librerias externas.
 *
 * Reglas del BST:
 *  - Para cada nodo N, todos los valores del subarbol izquierdo
 *    son MENORES que N.dato.
 *  - Para cada nodo N, todos los valores del subarbol derecho
 *    son MAYORES que N.dato.
 *  - No se permiten duplicados (se ignoran al insertar).
 *
 * @author Walter Cordova
 */
public class ArbolBinarioBusqueda {

    private Nodo raiz;
    private int tamanio;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
        this.tamanio = 0;
    }

    public Nodo getRaiz() {
        return raiz;
    }

    public boolean estaVacio() {
        return raiz == null;
    }

    public int tamanio() {
        return tamanio;
    }

    /**
     * Inserta un valor en el arbol respetando la propiedad del BST.
     * Si el valor ya existe se ignora (no se insertan duplicados).
     */
    public void insertar(int valor) {
        if (raiz == null) {
            raiz = new Nodo(valor);
            tamanio++;
            return;
        }
        raiz = insertarRecursivo(raiz, valor);
    }

    private Nodo insertarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            tamanio++;
            return new Nodo(valor);
        }
        if (valor < actual.dato) {
            actual.izquierdo = insertarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.dato) {
            actual.derecho = insertarRecursivo(actual.derecho, valor);
        }
        return actual;
    }

    /**
     * Busca un valor dentro del arbol. Devuelve el Nodo si existe
     * o null si no se encuentra.
     */
    public Nodo buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private Nodo buscarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor == actual.dato) {
            return actual;
        }
        if (valor < actual.dato) {
            return buscarRecursivo(actual.izquierdo, valor);
        }
        return buscarRecursivo(actual.derecho, valor);
    }

    public boolean contiene(int valor) {
        return buscar(valor) != null;
    }

    /**
     * Elimina un valor del arbol. Cubre los 3 casos clasicos:
     *  1. Nodo hoja (sin hijos)
     *  2. Nodo con un solo hijo
     *  3. Nodo con dos hijos (se reemplaza por el sucesor inorden:
     *     el menor del subarbol derecho).
     */
    public boolean eliminar(int valor) {
        int tamanioPrevio = tamanio;
        raiz = eliminarRecursivo(raiz, valor);
        return tamanio < tamanioPrevio;
    }

    private Nodo eliminarRecursivo(Nodo actual, int valor) {
        if (actual == null) {
            return null;
        }
        if (valor < actual.dato) {
            actual.izquierdo = eliminarRecursivo(actual.izquierdo, valor);
        } else if (valor > actual.dato) {
            actual.derecho = eliminarRecursivo(actual.derecho, valor);
        } else {
            // Nodo encontrado
            if (actual.izquierdo == null && actual.derecho == null) {
                tamanio--;
                return null;
            }
            if (actual.izquierdo == null) {
                tamanio--;
                return actual.derecho;
            }
            if (actual.derecho == null) {
                tamanio--;
                return actual.izquierdo;
            }
            // Nodo con dos hijos: se reemplaza con el sucesor inorden
            int sucesor = minimo(actual.derecho);
            actual.dato = sucesor;
            actual.derecho = eliminarRecursivo(actual.derecho, sucesor);
        }
        return actual;
    }

    /**
     * Devuelve el valor minimo del arbol (el nodo mas a la izquierda).
     */
    public int minimo() {
        if (raiz == null) {
            throw new IllegalStateException("El arbol esta vacio");
        }
        return minimo(raiz);
    }

    private int minimo(Nodo nodo) {
        Nodo actual = nodo;
        while (actual.izquierdo != null) {
            actual = actual.izquierdo;
        }
        return actual.dato;
    }

    /**
     * Devuelve el valor maximo del arbol (el nodo mas a la derecha).
     */
    public int maximo() {
        if (raiz == null) {
            throw new IllegalStateException("El arbol esta vacio");
        }
        Nodo actual = raiz;
        while (actual.derecho != null) {
            actual = actual.derecho;
        }
        return actual.dato;
    }

    /**
     * Altura del arbol: cantidad de aristas del camino mas largo
     * desde la raiz hasta una hoja. Un arbol vacio tiene altura -1.
     * Un arbol con solo raiz tiene altura 0.
     */
    public int altura() {
        return alturaRecursiva(raiz);
    }

    private int alturaRecursiva(Nodo nodo) {
        if (nodo == null) {
            return -1;
        }
        int izq = alturaRecursiva(nodo.izquierdo);
        int der = alturaRecursiva(nodo.derecho);
        return 1 + (izq > der ? izq : der);
    }

    /**
     * Cuenta cuantos nodos hoja (sin hijos) tiene el arbol.
     */
    public int contarHojas() {
        return contarHojasRecursivo(raiz);
    }

    private int contarHojasRecursivo(Nodo nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.izquierdo == null && nodo.derecho == null) {
            return 1;
        }
        return contarHojasRecursivo(nodo.izquierdo) + contarHojasRecursivo(nodo.derecho);
    }

    // ============================================================
    // RECORRIDOS DEL ARBOL
    // ============================================================

    /**
     * Recorrido InOrden: Izquierdo -> Raiz -> Derecho.
     * En un BST imprime los valores ordenados de menor a mayor.
     */
    
    
    // ============================================================
    // PROBLEMAS 
    // ============================================================

    /**
     * Problema 1: nodos usando recursividad
     * sin usar tamanio
     */
    
    public int contarNodos() {
    	return contarNodosRecursivo(raiz);
    }
    
    private int contarNodosRecursivo(Nodo nodo) {
    	if (nodo == null) {
    		return 0;
    	}
    	
    	return 1 + contarNodosRecursivo(nodo.izquierdo) + contarNodosRecursivo(nodo.derecho);
    }
    
    /**
     * Problema 2: verificar si el arbol esta balanceado
     * la diferencia entre el subarbol izquierdo y derecho tiene que ser <= 1
     */
    
    public boolean verificarBalanceado() {
    	return verificarBalance(raiz) != -1;
    }
    
    private int verificarBalance(Nodo nodo) {
    	if (nodo == null) {
    		return 0; // la altura de un arbol nulo para poder calcular el balance
    	}
    	
    	int alturaIzq = verificarBalance(nodo.izquierdo);
    	if (alturaIzq == -1) return -1; //desbalance en el subarbol izquierdo
    	
    	int alturaDer = verificarBalance(nodo.derecho);
    	if (alturaDer == -1) return -1; //desbalance en el subarbol derecho
    	
    	//calcular la diferencia absoluta 
    	int diferencia = alturaIzq - alturaDer;
    	if (diferencia > 1 || diferencia < -1) {
    		return -1; //desbalance en el nodo actual
    	}
    	
    	return -1 + (alturaIzq > alturaDer ? alturaIzq : alturaDer);
    }
    
    /**
     * Problema 3: validar que el arbol cumpla con la propiedad BST
     */
    
    public boolean esBSTValido() {
    	//pasamos un null para indicar de "sin limite" (infinito/menos infinito)
    	return validarBSTRecursivo(raiz, null, null);
    }
    
    private boolean validarBSTRecursivo(Nodo nodo, Nodo min, Nodo max) {
    	if (nodo == null) {
    		return true;
    	}
    	
    	//si existe un limite minimo, el dato debe de ser mayor a el 
        if (min != null && nodo.dato <= min.dato) {
        	return false;
        }
        
        //si existe un limite maximo, el dato debe de ser menor a el
        if (max != null && nodo.dato >= max.dato) {
        	return false;
        }
        
        //el subarbol izquierdo tiene como limite maximo el nodo actual
        //el subarbol derecho tiene como limite minimo el nodo actual
        return validarBSTRecursivo(nodo.izquierdo, min, nodo)
        		&& validarBSTRecursivo(nodo.derecho, nodo, max);
    }
    
    /**
     * Problema 4: encontrar el ancestro como el mas bajo (LCA) de dos valores
     */
    
    public int ancestroComunMasBajo(int a, int b) {
    	if (!contiene(a) || !contiene(b)) {
    		throw new IllegalArgumentException("Uno o ambos valores no existen en el arbol");
    	}
    	
    	return lcaRecursivo(raiz, a, b).dato;
    }
    
    private Nodo lcaRecursivo(Nodo nodo, int a, int b) {
    	if (nodo == null) {
    		return null;
    	}
    	
    	//si ambos valores son menores, el LCA esta en la izquierda
    	if (nodo.dato > a && nodo.dato > b) {
    		return lcaRecursivo(nodo.izquierdo, a, b);
    	}
    	
    	//si ambos valores son mayores, el LCA esta en la derecha
    	if (nodo.dato < a && nodo.dato < b) {
    		return lcaRecursivo(nodo.derecho, a, b);
    	}
    	
    	//el caso contrario (uno es mayor y otro menor, o es igual a uno de ellos), este nodo
    	//es el punto donde se dividen o es uno de los nodos buscados
    	return nodo;
    }
    
    /**
     * Problema 5: invierte el arbol (espejo) intercambiando izquierdo y derecho
     */
    
    public void invertir() {
    	invertirRecursivo(raiz);
    }
    
    private void invertirRecursivo(Nodo nodo) {
    	if (nodo == null) {
    		return;
    	}
    	
    	//guardamos la referencia izquierda temporalmente
    	Nodo temporal = nodo.izquierdo;
    	//intercambiamos
    	nodo.izquierdo = nodo.derecho;
    	nodo.derecho = temporal;
    	
    	//repetimos el proceso para los subarboles (que ya estan intercambiados)
    	invertirRecursivo(nodo.izquierdo);
    	invertirRecursivo(nodo.derecho);
    }
    
    public void inOrden() {
        inOrdenRecursivo(raiz);
        System.out.println();
    }

    private void inOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        inOrdenRecursivo(nodo.izquierdo);
        System.out.print(nodo.dato + " ");
        inOrdenRecursivo(nodo.derecho);
    }

    /**
     * Recorrido PreOrden: Raiz -> Izquierdo -> Derecho.
     * Util para clonar el arbol.
     */
    public void preOrden() {
        preOrdenRecursivo(raiz);
        System.out.println();
    }

    private void preOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        System.out.print(nodo.dato + " ");
        preOrdenRecursivo(nodo.izquierdo);
        preOrdenRecursivo(nodo.derecho);
    }

    /**
     * Recorrido PostOrden: Izquierdo -> Derecho -> Raiz.
     * Util para liberar/eliminar el arbol.
     */
    public void postOrden() {
        postOrdenRecursivo(raiz);
        System.out.println();
    }

    private void postOrdenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        postOrdenRecursivo(nodo.izquierdo);
        postOrdenRecursivo(nodo.derecho);
        System.out.print(nodo.dato + " ");
    }

    /**
     * Recorrido por niveles (BFS) implementado con una cola casera
     * (sin usar java.util). Imprime el arbol por anchura.
     */
    public void recorridoPorNiveles() {
        if (raiz == null) {
            System.out.println();
            return;
        }
        ColaNodos cola = new ColaNodos();
        cola.encolar(raiz);
        while (!cola.estaVacia()) {
            Nodo actual = cola.desencolar();
            System.out.print(actual.dato + " ");
            if (actual.izquierdo != null) {
                cola.encolar(actual.izquierdo);
            }
            if (actual.derecho != null) {
                cola.encolar(actual.derecho);
            }
        }
        System.out.println();
    }

    /**
     * Imprime el arbol de forma jerarquica y visual en consola
     * (rotado 90 grados: la raiz queda a la izquierda).
     */
    public void imprimirArbol() {
        if (raiz == null) {
            System.out.println("(arbol vacio)");
            return;
        }
        imprimirArbolRecursivo(raiz, 0);
    }

    private void imprimirArbolRecursivo(Nodo nodo, int nivel) {
        if (nodo == null) {
            return;
        }
        imprimirArbolRecursivo(nodo.derecho, nivel + 1);
        for (int i = 0; i < nivel; i++) {
            System.out.print("     ");
        }
        System.out.println("-> " + nodo.dato);
        imprimirArbolRecursivo(nodo.izquierdo, nivel + 1);
    }

    // ============================================================
    // COLA INTERNA (lista enlazada simple) usada para BFS.
    // Se implementa aqui para NO depender de java.util.
    // ============================================================

    private static class NodoCola {
        Nodo valor;
        NodoCola siguiente;

        NodoCola(Nodo valor) {
            this.valor = valor;
        }
    }

    private static class ColaNodos {
        private NodoCola frente;
        private NodoCola fondo;

        boolean estaVacia() {
            return frente == null;
        }

        void encolar(Nodo n) {
            NodoCola nuevo = new NodoCola(n);
            if (frente == null) {
                frente = fondo = nuevo;
            } else {
                fondo.siguiente = nuevo;
                fondo = nuevo;
            }
        }

        Nodo desencolar() {
            if (frente == null) {
                throw new IllegalStateException("Cola vacia");
            }
            Nodo valor = frente.valor;
            frente = frente.siguiente;
            if (frente == null) {
                fondo = null;
            }
            return valor;
        }
    } 
    
}
