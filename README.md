# Árbol Binario de Búsqueda (BST) en Java

## 1. Cómo compilar y ejecutar el proyecto

Este proyecto está gestionado con Maven y no requiere dependencias externas para su lógica. Para compilar y ejecutar desde la terminal (estando en el directorio raíz del proyecto `arboles/`):

**Para compilar:**
```bash
mvn compile
```

**Para ejecutar:**
```bash
java -cp target/classes umg.edu.progra.arboles.Principal
```

**Para ejecutar enviando argumentos por consola (Ejercicio Extra 4):**
```bash
java -cp target/classes umg.edu.progra.arboles.Principal 15 5 20 3 8
```

---

## 2. Explicación de los métodos implementados

### Problemas Base
* **`contarNodos()`**: Recorre el árbol usando recursividad, sumando 1 por cada nodo no nulo encontrado. No depende de ninguna variable de clase existente, demostrando un recorrido exhaustivo.
* **`esBalanceado()`**: Verifica que, para cada nodo del árbol, la diferencia absoluta de altura entre su subárbol izquierdo y derecho sea `<= 1`. Utiliza una función auxiliar que calcula la altura y detecta desbalances en una sola pasada (retornando `-1` si hay error para optimizar).
* **`esBSTValido()`**: Asegura que el árbol respete estrictamente la propiedad fundamental del BST. En lugar de solo revisar el nodo padre contra sus hijos inmediatos, pasa límites de validación `[min, max]` mediante referencias de tipo `Nodo` hacia abajo en cada llamada recursiva.
* **`ancestroComunMasBajo(int a, int b)`**: Encuentra el nodo más profundo que es padre de ambos valores (LCA). Aprovechando la propiedad del BST: si ambos valores son menores que el nodo actual, busca a la izquierda; si son mayores, busca a la derecha. El punto donde el camino diverge es el LCA.
* **`invertir()`**: Convierte el árbol en su imagen espejo. Intercambia las referencias de los punteros `izquierdo` y `derecho` de todos los nodos del árbol mediante un recorrido recursivo tipo PostOrden / PreOrden.

### Ejercicios Extra
* **`kEsimoMenor(int k)`**: Usa un recorrido `InOrden` modificado junto con un arreglo de un espacio (para emular paso por referencia en Java) para llevar el conteo de los nodos visitados y detenerse al encontrar el k-ésimo elemento más pequeño.
* **`imprimirRangoOrdenado(int min, int max)`**: Imprime los valores que caen dentro del rango dado. Optimiza el recorrido omitiendo subárboles enteros que matemáticamente no pueden contener valores en el rango (poda del árbol).
* **`diametro()`**: Calcula el camino más largo entre dos nodos contando las aristas. Evalúa la suma de la altura izquierda y derecha por cada nodo, manteniendo un registro dinámico del diámetro máximo encontrado.

---

## 3. Ejemplos de Entrada y Salida

**Árbol base generado en código:** `[50, 30, 70, 20, 40, 60, 80, 10]`

| Problema | Entrada / Acción | Salida Esperada en Consola |
| :--- | :--- | :--- |
| **1. Contar Nodos** | `arbol.contarNodos()` | `Nodos contados recursivamente: 8` |
| **2. Es Balanceado** | Validar árbol `[1, 2, 3, 4, 5]` | `¿El arbol [1,2,3,4,5] esta balanceado? false` |
| **3. Es BST Válido** | Inyectar manualmente el valor `99` en el subárbol izquierdo | `¿Es un BST valido despues de inyectar el 99? false` |
| **4. LCA** | `arbol.ancestroComunMasBajo(10, 80)` | `LCA de 10 y 80 (Esperado 50): 50` |
| **5. Espejo** | `arbol.invertir()` | InOrden pasa de ser `10 20 ...` a `80 70 60 50 40 30 20 10` |
| **E1. k-ésimo Menor** | `arbol.kEsimoMenor(3)` | `El 3er valor mas pequeno es: 30` |
