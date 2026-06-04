package umg.edu.progra.arboles;

/**
 * Clase principal que demuestra el uso del Arbol Binario de Busqueda (BST)
 * implementado manualmente, sin usar librerias como java.util.
 *
 * Ejecucion sugerida:
 *   1. mvn compile
 *   2. mvn exec:java -Dexec.mainClass="umg.edu.progra.arboles.Principal"
 *
 * @author Walter Cordova
 */
public class Principal {

    public static void main(String[] args) {
    	System.out.println("==================================================");
    	System.out.println("   TAREA: ARBOL BINARIO DE BUSQUEDA (BST)");
    	System.out.println("==================================================");


        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        /*
         * Insertamos estos valores para formar el siguiente BST:
         *
         *               50
         *              /  \
         *            30    70
         *           /  \   / \
         *          20  40 60  80
         *         /
         *        10
         */
        int[] valores = { 50, 30, 70, 20, 40, 60, 80, 10 };
        for (int i = 0; i < valores.length; i++) {
            arbol.insertar(valores[i]);
        }

       //===============================================================
       // PROBLEMA 1: Contar Nodos
       //===============================================================
        System.out.println("--- Problema 1: Contar Nodos ---");
        System.out.println("Tamanio con metodo original: " + arbol.tamanio());
        System.out.println("Nodos contados recursivamente:" + arbol.contarNodos());
        System.out.println("¿Coinciden?" + (arbol.tamanio() == arbol.contarNodos()));
        
        //===============================================================
        // PROBLEMA 2: Es Balanceado
        //===============================================================
        System.out.println("\n--- Problema 2: Arbol Balanceado ---");
        System.out.println("¿El arbol principal esta balanceado?" + arbol.verificarBalanceado());
        
        //Prueba con arbol claramente desbalanceado 
        ArbolBinarioBusqueda arbolMalo = new ArbolBinarioBusqueda();
        int[] desbalanceados = {1, 2, 3, 4, 5};
        for (int i = 0; i < desbalanceados.length; i++) {
        	arbolMalo.insertar(desbalanceados[i]);		
        }
        System.out.println("¿El arbol [1,2,3,4,5] esta balanceado?" + arbolMalo.verificarBalanceado());
        
        //===============================================================
        // PROBLEMAS 3: Validar BST
        //===============================================================
        System.out.println("\n--- Problema 3: Validar BST ---");
        System.out.println("¿El arbol principal es un BST valido?" + arbol.verificarBalanceado());
        
        System.out.println("Rompiendo el arbol intencionalmente. . .");
        //accedemos a la raiz (50) -> subarbol izquierdo (30) -> subarbol derecho (40) cambiamos ese 40 por un 99,
        //lo cual rompe la regla porque 99 es mayor que la raiz (50)
        Nodo nodo30 = arbol.getRaiz().getIzquierdo();
        int valorOriginal = nodo30.getDerecho().leerDato();
        nodo30.getDerecho().dato = 99; //rompemos la regla
        
        System.out.println("¿Es un BST valido despues de inyectar el 99?" + arbol.verificarBalanceado());
        
        //restaurmos el arbol para no afectar las pruebas siguientes 
        nodo30.getDerecho().dato = valorOriginal;
        System.out.println("Arbol restaurado. ¿Es BST valido de nuevo?" + arbol.verificarBalanceado());
        
        //===============================================================
        // PROBLEMA 4: Ancestro Comun Mas Bajo (LCA)
        //===============================================================
        System.out.println("\n--- Problema 4: Ancestro Comun Mas Bajo (LCA) ---");
        System.out.println("LCA de 10 y 40 (Esperando 30):" + arbol.ancestroComunMasBajo(10, 40));
        System.out.println("LCA de 10 y 80 (Esperando 50):" + arbol.ancestroComunMasBajo(10, 80));
        System.out.println("LCA de 60 y 80 (Esperando 70):" + arbol.ancestroComunMasBajo(10, 80));
        
        
        //===============================================================
        // PROBLEMA 5: Espejo del arbol (Inversion)
        //===============================================================
        System.out.println("\n--- Problema 5: Espejo del Arbol ---");
        System.out.println(">> Arbol Original:");
        arbol.imprimirArbol();
        System.out.println("InOrden original (Ascendente): ");
        arbol.inOrden();
        
        System.out.println("\n>> Invertiendo el arbol. . .");
        arbol.imprimirArbol();
        System.out.println("InOrden invertido (Descendente)");
        arbol.inOrden();


        
        
        /*
         * Ejercicios
         *
         *  1. Implementar un metodo que devuelva la cantidad TOTAL de nodos
         *     usando recursividad (sin usar el campo 'tamanio').
         *  2. Implementar un metodo 'esBalanceado()' que indique si el arbol
         *     esta balanceado (diferencia de alturas <= 1 en cada nodo).
         *  3. Implementar 'esBSTValido()' que verifique que el arbol cumple
         *     la propiedad de BST recorriendo los nodos.
         *  4. Implementar un metodo para encontrar el ancestro comun mas
         *     bajo (LCA) entre dos valores.
         *  5. Implementar la inversion del arbol (espejo).
         */
    }
}
