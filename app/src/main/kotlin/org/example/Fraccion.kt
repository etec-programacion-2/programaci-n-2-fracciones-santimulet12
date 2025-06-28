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

    operator fun plus(otra: Fraccion): Fraccion{ // al usar "+" con dos objetos de la clase fraccion, los va a poder sumar
        // (a/b) + (c/d) = (ad + bc)/(b*d)
        val nuevoNumerador: Int = (this.numerador * otra.denominador) + (this.denominador * otra.numerador) //(ad + bc) = numerador
        val nuevoDenominador = (this.denominador * otra.denominador) // (b*d) = denominador

        return simplificar(nuevoNumerador, nuevoDenominador) // Retorna un objeto tipo Fraccion que toma el nuevo numerador y nuevo denominador
    }

    operator fun minus(otra: Fraccion): Fraccion{ // al usar "-" con dos objetos de la clase fraccion, los va a poder restar
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

        return Fraccion(num/mcd, denom/mcd) // Retornamos un objeto tipo fraccion simplificado
    }

    //-----------------------------------------------Métodos públicos-----------------------------------------------------
    fun mostrar(){
        println(toString())
    }

}