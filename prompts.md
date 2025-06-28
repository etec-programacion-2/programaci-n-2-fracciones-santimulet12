### Prompt 1
- Fecha: 2025-06-27
- Herramienta: Claude
- Prompt: algoritmo de euclides en Kotlin
- Respuesta: 
```kotlin
fun mcd(a: Int, b: Int): Int {
    var num1 = kotlin.math.abs(a)
    var num2 = kotlin.math.abs(b)
    
    while (num2 != 0) {
        val temp = num2
        num2 = num1 % num2
        num1 = temp
    }
    
    return num1
}
```

### Prompt 2
- Fecha: 2025-06-27
- Herramienta: Claude
- Prompt: Explicame el código que haz hecho
- Respuesta:

# Algoritmo de Euclides - Versión Iterativa

## Código de la versión iterativa

```kotlin
fun mcd(a: Int, b: Int): Int {
    var num1 = kotlin.math.abs(a)
    var num2 = kotlin.math.abs(b)
    
    while (num2 != 0) {
        val temp = num2
        num2 = num1 % num2
        num1 = temp
    }
    
    return num1
}
```

# Explicación paso a paso

## 1. Preparación inicial

```kotlin
var num1 = kotlin.math.abs(a)
var num2 = kotlin.math.abs(b)
```

- Tomamos los valores absolutos de `a` y `b` para trabajar solo con números positivos
- Los asignamos a variables mutables `num1` y `num2`

## 2. El bucle principal

```kotlin
while (num2 != 0) {
    val temp = num2
    num2 = num1 % num2
    num1 = temp
}
```

El bucle continúa mientras `num2` no sea cero. En cada iteración:

1. **Guardamos `num2`** en una variable temporal `temp`
2. **Calculamos el resto** de `num1 ÷ num2` y lo asignamos a `num2`
3. **Actualizamos `num1`** con el valor que tenía `num2` (guardado en `temp`)

## 3. Ejemplo práctico

Calculemos MCD(48, 18):

| Iteración | num1 | num2 | num1 % num2 | Explicación |
|-----------|------|------|-------------|-------------|
| Inicial   | 48   | 18   | -           | Valores iniciales |
| 1         | 18   | 12   | 48 % 18 = 12| 48 ÷ 18 = 2 resto 12 |
| 2         | 12   | 6    | 18 % 12 = 6 | 18 ÷ 12 = 1 resto 6 |
| 3         | 6    | 0    | 12 % 6 = 0  | 12 ÷ 6 = 2 resto 0 |

Cuando `num2` llega a 0, el algoritmo termina y `num1` contiene el MCD.

## 4. ¿Por qué funciona?

El algoritmo se basa en el principio matemático:

> **MCD(a, b) = MCD(b, a mod b)**

Esto significa que el MCD de dos números es igual al MCD del segundo número y el resto de dividir el primero entre el segundo.

## 5. Ventajas de la versión iterativa

- **Eficiencia de memoria**: No usa la pila de llamadas recursivas
- **Mejor para números grandes**: Evita el riesgo de desbordamiento de pila
- **Más fácil de entender**: El flujo es más directo y visual

## 6. Complejidad

- **Tiempo**: O(log min(a,b)) - muy eficiente
- **Espacio**: O(1) - solo usa unas pocas variables

La versión iterativa es generalmente preferida en aplicaciones de producción porque es más eficiente en memoria y no tiene limitaciones de profundidad de recursión.

## Ejemplo de uso

```kotlin
fun main() {
    val resultado = mcd(48, 18)
    println("MCD(48, 18) = $resultado") // Imprime: MCD(48, 18) = 6
}
```
