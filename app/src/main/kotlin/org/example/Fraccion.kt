package org.example

class Fraccion(
    private var _numerador: Int = 0,
    private var _denominador: Int = 1
    ) { //Colocamos valores por defecto para los parámetros y definimos las variables privadas en el constructor

    var numerador: Int
        get() = _numerador // Obtener el valor del atributo privado a través del get
        set(value) { _numerador = value } //Asignación del valor

    var denominador: Int
        get() = _denominador
        set(value) {
            if (value == 0){throw IllegalArgumentException("El denominador no puede ser cero")} //Asignación del valor, en caso de ser 0 devuelve un error
            else{_denominador = value}
        }

    init {  //Bloque Init para validar el constructor
        if (denominador == 0){
            throw IllegalArgumentException("El denominador no puede ser cero")
        }
    }

    override fun toString(): String { //Sobreescribimos la función toString para mostrar la clase de la forma que nosotros queremos
        return "${_numerador}/${_denominador}"
    }

    operator fun plus(otra: Fraccion): Fraccion{ // al usar "+" con dos objetos de la clase frac, los va a poder sumar
        // (a/b) + (c/d) = (ad + bc)/(b*d)
        val nuevoNumerador: Int = (this.numerador * otra.denominador) + (this.denominador * otra.numerador) //(ad + bc) = numerador
        val nuevoDenominador = (this.denominador * otra.denominador) // (b*d) = denominador

        return simplificar(nuevoNumerador, nuevoDenominador) // Retorna un objeto tipo Frac que toma el nuevo numerador y nuevo denominador
    }

    operator fun minus(otra: Fraccion): Fraccion{ // al usar "-" con dos objetos de la clase frac, los va a poder restar
        // (a/b) - (c/d) = (ad - bc)/(b*d)
        val nuevoNumerador: Int = (this.numerador * otra.denominador) - (this.denominador * otra.numerador) //(ad - bc) = numerador
        val nuevoDenominador: Int = (this.denominador * otra.denominador) // (b*d) = denominador

        return simplificar(nuevoNumerador, nuevoDenominador)
    }

    operator fun times(otra: Fraccion): Fraccion{
        //(a/b) * (c/d) = (ac)/(bd)
        val nuevoNumerador: Int = this.numerador * otra.numerador
        val nuevoDenominador: Int = this.denominador * otra.denominador

        return simplificar(nuevoNumerador,nuevoDenominador)
    }

    operator fun div(otra: Fraccion): Fraccion{
        //(a/b) / (c/d) = (ad)/(bc)
        val nuevoNumerador: Int = this.numerador * otra.denominador
        val nuevoDenominador: Int = this.denominador * otra.numerador

        return simplificar(nuevoNumerador,nuevoDenominador)
    }

    operator fun compareTo(otra: Fraccion): Int{
        // Comparamos usando productos cruzados para evitar problemas de precisión decimal
        // (a/b) comparado con (c/d) es equivalente a comparar (a*d) con (b*c)
        val producto1 = this.numerador.toLong() * otra.denominador.toLong()
        val producto2 = this.denominador.toLong() * otra.numerador.toLong()

        return when{
            producto1 > producto2 -> 1 //Si es mayor, retorna 1
            producto1 < producto2 -> -1 //Si es menor, retorna -1
            else -> 0 //Si es igual, retorna 0
        }
    }

    override fun equals(other: Any?): Boolean {
        //En Kotlin, si sobreescribo equals(),
        //también tengo que sobrescribir hashCode() para garantizar que el contrato entre ambos métodos se respete.

        if (this === other){ //Si ambas son iguales de entrada (===), retorna verdadero
            return true
        }
        if (other !is Fraccion){//Si la otra esperada, no pertenece a la clase fraccion, retorna Falso. ej: 1/4 == "hola"
            return false
        }

        //Simplificamos ambas fracciones
        val thisSimplificada = simplificar(this.numerador.toInt(), this.denominador.toInt())
        val otraSimplificada = simplificar(other.numerador.toInt(), other.denominador.toInt())

        return when{ //Cuando el numerador y denominador de ambas fracciones sean iguales retorna verdadero, sino falso
            thisSimplificada.numerador == otraSimplificada.numerador && thisSimplificada.denominador == otraSimplificada.denominador -> true
            else -> false
        }
    }

    override fun hashCode(): Int { //Devuelve un un número entero que representa el objeto.
        val simplificada = simplificar(this.numerador, this.denominador)
        return 31 * simplificada.numerador + simplificada.denominador
    }

    //-----------------------------------------------Métodos privados-----------------------------------------------------

    private fun simplificar(num: Int, denom: Int): Fraccion{
        var numerador: Int = kotlin.math.abs(num) // Tomamos el valor absoluto del numerador y denominador
        var denominador: Int = kotlin.math.abs(denom)
        var mcd: Int = numerador

        while (denominador != 0) {
            val temp = denominador
            denominador = mcd % denominador
            mcd = temp
        }

        return Fraccion(num/mcd, denom/mcd) // Retornamos un objeto tipo frac simplificado
    }

    //-----------------------------------------------Métodos públicos-----------------------------------------------------
    fun mostrar(){
        println(toString())
    }

    fun esMayor(otra: Fraccion): Boolean{
        if (this.compareTo(otra) == 1){
            return true
        }
        return false
    }

    fun esMenor(otra: Fraccion): Boolean{
        if (this.compareTo(otra) == -1){
            return true
        }
        return false
    }

    fun aDecimal(): Double {
        return numerador.toDouble() / denominador.toDouble()
    }

    companion object { //Es un objeto especial que pertenece a la clase en sí, no a las instancias individuales de la clase
        /**
         * Convierte un número decimal a fracción
         * @param decimal el número decimal a convertir
         * @return Fraccion equivalente al decimal
         */
        fun desdeDecimal(decimal: Double): Fraccion {
            // Casos especiales
            if (decimal == 0.0) return Fraccion(0, 1)
            if (decimal.isInfinite() || decimal.isNaN()) {
                throw IllegalArgumentException("No se puede convertir un valor infinito o NaN a fracción")
            }

            // Determinar el signo
            val esNegativo = decimal < 0
            val valorAbsoluto = kotlin.math.abs(decimal)

            // Convertir la parte decimal a fracción
            val cadenaDecimal = valorAbsoluto.toString()
            val partes = cadenaDecimal.split(".")

            if (partes.size == 1) {
                // Es un número entero
                return Fraccion(decimal.toInt(), 1)
            }

            val parteDecimal = partes[1]
            var denominador = 1
            repeat(parteDecimal.length) {
                denominador *= 10
            }
            val numerador = (valorAbsoluto * denominador).toInt()

            val resultado = Fraccion(numerador, denominador).simplificar(numerador, denominador)

            // Aplicar el signo
            return if (esNegativo) {
                Fraccion(-resultado.numerador, resultado.denominador)
            } else {
                resultado
            }
        }
    }

}
