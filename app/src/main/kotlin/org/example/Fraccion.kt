package org.example

class Fraccion(numerador: Int = 0, denominador: Int = 1) { //Colocamos valores por defecto para los parámetros
    //Definimos las variables privadas
    private var _numerador: Int = numerador
    private var _denominador: Int = denominador

    var numerador: Int
        get() = _numerador
        set(value) { _numerador = value } //Asignación del valor

    var denominador: Int
        get() = _denominador
        set(value) {
            if (value == 0){throw IllegalArgumentException("El denominador no puede ser cero")} //Asignación del valor, en caso de ser 0 devuelve un error
            else{_denominador = value}
        }

    override fun toString(): String { //Sobreescribimos la función toString para mostrar la clase de la forma que nosotros queremos
        return "${_numerador}/${_denominador}"
    }

    fun mostrar(){
        println(toString())
    }
}