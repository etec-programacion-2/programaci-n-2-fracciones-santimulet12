package org.example
import java.util.Scanner

class CalculadoraFracciones {
    fun mostrarMenu() {
        println("=== CALCULADORA DE FRACCIONES ===")
        println("1. Sumar fracciones")
        println("2. Restar fracciones")
        println("3. Multiplicar fracciones")
        println("4. Dividir fracciones")
        println("5. Comparar fracciones")
        println("6. Convertir fracción a decimal")
        println("7. Crear fracción desde decimal")
        println("8. Ejemplos predefinidos")
        println("0. Salir")
        println("Ingrese su opción: ")
    }

    fun leerFraccion(scanner: Scanner, mensaje: String): Fraccion {
        println(mensaje)
        print("Numerador: ")
        val numerador = scanner.nextInt()
        print("Denominador: ")
        val denominador = scanner.nextInt()
        return Fraccion(numerador, denominador)
    }

    fun mostrarEjemplos() {
        println("\n=== EJEMPLOS PREDEFINIDOS ===")

        val f1 = Fraccion(1, 2)  // 1/2
        val f2 = Fraccion(1, 3)  // 1/3

        println("Fracción 1: $f1")
        println("Fracción 2: $f2")
        println("Suma: $f1 + $f2 = ${f1 + f2}")
        println("Resta: $f1 - $f2 = ${f1 - f2}")
        println("Multiplicación: $f1 * $f2 = ${f1 * f2}")
        println("División: $f1 / $f2 = ${f1 / f2}")
        println("¿$f1 > $f2? ${f1 > f2}")
        println("$f1 en decimal: ${f1.aDecimal()}")
    }

}
