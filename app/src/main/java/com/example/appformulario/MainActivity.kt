package com.example.appformulario

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast

import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    lateinit var id: TextInputEditText
    lateinit var nombre: TextInputEditText
    lateinit var color: TextInputEditText
    lateinit var precio : TextInputEditText
    lateinit var marca: TextInputEditText
    lateinit var listaU: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        id = findViewById(R.id.textId)
        nombre = findViewById(R.id.textNombre)
        color = findViewById(R.id.textColor)
        precio = findViewById(R.id.textPrecio)
        marca = findViewById(R.id.textMarca)
        listaU = findViewById(R.id.textList)
    }

    fun GuardarDatos(view: View){
        var db = BaseDatos(this)
        var usu= Producto()
        /* validamos inputs*/
        if( id.text.toString().length>0 &&
            nombre.text.toString().length>0 &&
            color.text.toString().length>0 &&
            precio.text.toString().length>0 &&
            marca.text.toString().length>0){
            usu.id =  id.text.toString()
            usu.nombre =  nombre.text.toString()
            usu.color = color.text.toString()
            usu.precio = precio.text.toString()
            usu.marca = marca.text.toString()

            var mensaje = db.Guardar(usu)

            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }
    }

    fun ListarDatos(view:View){
        listaU.text= ""
        var db= BaseDatos(this)
        var datosL = db.ListarDatos()

        for(i in 0..datosL.size-1){
            listaU.append("id:"+datosL.get(i).id+"\n"+"id: "+datosL.get(i).nombre+"\n"+"color: "+datosL.get(i).color+"\n" )
        }
    }

    fun borrarDatos(view:View){
        var db = BaseDatos(this)

        if(id.text.toString().length>0){
            var mensaje = db.eliminar(id.text.toString())
            listaU.text= id.text.toString()
            Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(this, "El campo id es requerido", Toast.LENGTH_SHORT).show()
        }
    }

    fun actualizarDatos(view:View){
        var db = BaseDatos(this)

        var mensaje = db.actualizarDatos(id.text.toString(),nombre.text.toString(),color.text.toString(),precio.text.toString(),marca.text.toString())

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show()
    }
}