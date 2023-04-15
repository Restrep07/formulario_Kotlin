package com.example.appformulario

//Creamos la clase Producto.
//Definimos variables, asignamos tipo.

class Producto {
    var id: String = ""
    var nombre: String = ""
    var color: String = ""
    var marca: String = ""
    var precio: String = ""


    constructor(id: String, nombre: String, color: String, marca: String, precio:String) {
        this.id = id
        this.nombre = nombre
        this.color = color
        this.marca = marca
        this.precio = precio
    }

    constructor() {

    }
}