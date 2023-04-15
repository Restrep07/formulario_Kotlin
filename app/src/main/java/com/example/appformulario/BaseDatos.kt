package com.example.appformulario


import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(context: Context):SQLiteOpenHelper(context, "Itinerario",null,1) {
    override fun onCreate(db: SQLiteDatabase?) {
        var sql= "CREATE TABLE Usuarios (\n" +
                "    id varchar primary key,\n" +
                "    nombre varchar(25),\n" +
                "    color varchar(25),\n" +
                "    precio varchar(25),\n" +
                "    marca varchar(25)\n" +
                ");";
        db?.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun  Guardar (user: Producto): String {
        val db = this.writableDatabase
        var contenedor = ContentValues()
        contenedor.put("id", user.id)
        contenedor.put("nombre", user.nombre)
        contenedor.put("color", user.color)
        contenedor.put("precio", user.precio)
        contenedor.put("marca", user.marca)

        var resultado = db.insert("Productos", null, contenedor)
        try {
            if (resultado == -1.toLong()) {
                return "Existe un error al insertar"
            } else {
                return "Se guardó exitosamente"
            }
        } catch (ex: Exception) {
            return ex.message.toString()
        } finally {
            db.close()
        }
    }

    fun actualizarDatos(
        id: String,
        nombre: String,
        color: String,
        precio: String,
        marca: String
    ): String {
        val db = this.writableDatabase
        var contenedor = ContentValues()
        contenedor.put("nombre", nombre)
        contenedor.put("color", color)
        contenedor.put("precio", precio)
        contenedor.put("marca", marca)
        var resultado = db.update("Productos", contenedor, "id=?", arrayOf(id))
        try {
            if (resultado > 0) {
                return "Actualizacion exitosa"
            } else {
                return "Error en la actualizacion"
            }
        } catch (ex: Exception) {
            return ex.message.toString()
        } finally {
            db.close()
        }
    }

    /*
    *
    * */
    fun actualizarDatos2(user: Producto): String {
        val db = this.writableDatabase
        var contenedor = ContentValues()
        contenedor.put("nombre", user.nombre)
        contenedor.put("color", user.color)
        contenedor.put("precio", user.precio)
        contenedor.put("marca", user.marca)
        var resultado = db.update("Productos", contenedor, "user.id=?", arrayOf(user.id))
        try {
            if (resultado > 0) {
                return "Actualizacion exitosa"
            } else {
                return "Error en la actualizacion"
            }
        } catch (ex: Exception) {
            return ex.message.toString()
        } finally {
            db.close()
        }
    }

    /* Tarea Eliminar pendiente */
    fun eliminar(user: String): String {
        val db = this.writableDatabase

        var resultado = db.delete("Productos", "id=?", arrayOf(user))
        try {
            if (resultado > 0) {
                return "Eliminacion exitosa"
            } else {
                return "Error en la elimiminacion"
            }
        } catch (ex: Exception) {
            return ex.message.toString()
        } finally {
            db.close()
        }
    }

    /* Listar usuarios */
    @SuppressLint("Range")
    fun ListarDatos(): MutableList<Producto> {
        val lista: MutableList<Producto> = ArrayList()
        val db = this.readableDatabase
        val sql = "select * from Productos"
        var resultado = db.rawQuery(sql, null)
        if (resultado.moveToFirst()) {
            do {
                var datosu = Producto()
                datosu.id = resultado.getString(resultado.getColumnIndex("id"))
                datosu.nombre = resultado.getString(resultado.getColumnIndex("nombre"))
                datosu.color = resultado.getString(resultado.getColumnIndex("color"))
                datosu.precio = resultado.getString(resultado.getColumnIndex("precio"))
                datosu.marca = resultado.getString(resultado.getColumnIndex("marca"))
                lista.add(datosu)
            } while (resultado.moveToNext())

            resultado.close()
            db.close()
        }
        return lista
    }
}