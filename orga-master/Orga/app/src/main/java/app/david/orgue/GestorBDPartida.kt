package app.david.orgue

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import app.david.orgue.jocs.Ronda


@Suppress("UNUSED_EXPRESSION")
class GestorBDPartida(context: Context?) :

    SQLiteOpenHelper(context, "orga", null, 1) {
    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {
        sqLiteDatabase.execSQL(
            "CREATE TABLE ronda ("
                        + "id_ronda VARCHAR(10) PRIMARY KEY, "
                    + "fallos INT DEFAULT 0,"
                    + "porcentageFallos INT,"
                    + "totalFallos INT)"
        )

        sqLiteDatabase.execSQL(
            "CREATE TABLE partida ("
                    + "id_partida INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "id_ronda INT,"
                    + "CONSTRAINT fk_ronda "
                    + "FOREIGN KEY (id_ronda)"
                    + "REFERENCES ronda(id_ronda)"
                    +")"
        )

        sqLiteDatabase.execSQL(
            "CREATE TABLE ultimaPantalla ("
                    + "id_pantalla VARCHAR(30))"
        )
    }

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i1: Int) {}

    fun eliminarRondes() {
        val db = writableDatabase
        db.execSQL("DELETE FROM ronda")
        db.close()

    }

    fun getRonda(id: String): Cursor? {
        val db = writableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM ronda WHERE id_ronda = " + id, null)
        val resultado = cursor.moveToFirst()

        if(resultado){
            return cursor
        }

        return null
    }

    @SuppressLint("Range")
    fun updateRonda(id_ronda:String, acierto: Boolean, opciones: Int, tipo: String? = null) {
        val existeRonda = getRonda(id_ronda)
        val db = writableDatabase
        val opcionesDouble: Double = opciones.toDouble()
        if(acierto){
            val ronda = getRonda(id_ronda)
            var resultat: Int = 0
            if(ronda != null){
                var fallos = ronda.getInt(ronda.getColumnIndex("fallos")).toDouble()

                if(tipo == "imagen"){
                    if(fallos > 6){
                        fallos = 6.0
                    }
                }else if(tipo == "test"){
                    if(fallos > 3){
                        fallos = 3.0
                    }
                }else if(tipo == "drag"){
                    if(fallos > 4){
                        fallos = 4.0
                    }
                }else if(tipo == "test2"){
                    if(fallos > 2){
                        fallos = 2.0
                    }
                }else if(tipo == "select"){
                    if(fallos > 5){
                        fallos = 5.0
                    }
                }else{
                    fallos = 0.0
                }
                val porcentage = ((fallos - opcionesDouble) / opcionesDouble)
                resultat = (porcentage * 100).toInt()
            }
            db.execSQL("UPDATE ronda SET porcentageFallos = '"+resultat+"' WHERE id_ronda = "+id_ronda );
            getRondes()
        }else{
            db.execSQL("UPDATE ronda SET fallos = fallos + 1 WHERE id_ronda = "+id_ronda );
        }
        db.close()
    }

    @SuppressLint("Range")
    fun createRonda(id_ronda:String, totalFallos: Int): Int? {
        val existeRonda = getRonda(id_ronda)
        val db = writableDatabase

        if(existeRonda == null){
            val cv = ContentValues()
            cv.put("id_ronda", id_ronda)
            cv.put("totalFallos", totalFallos)
            db.insertOrThrow("ronda", null, cv)
            db.close()
            return -1
        }else{
            val fallos = existeRonda.getInt(existeRonda.getColumnIndex("fallos"))
            db.close()

            return fallos
        }

    }

    fun getFallos(): Int{
        var rondasList: ArrayList<Ronda> = getRondes()

        var totalFallos = 0
        var totalOpcions = 0

        for(ronda in rondasList){
            val v  = 0
            totalFallos += ronda.getFallos()
            totalOpcions += ronda.getNumOpcions()
        }

        if(totalOpcions < totalFallos){
            totalFallos = totalOpcions
        }

        return totalFallos
    }
    fun getPuntuacioFinal() : String{
        var rondasList: ArrayList<Ronda> = getRondes()

        var totalFallos = 0
        var totalOpcions = 0

        for(ronda in rondasList){
            val v  = 0
            totalFallos += ronda.getFallos()
            totalOpcions += ronda.getNumOpcions()
        }

        if(totalOpcions < totalFallos){
            totalFallos = totalOpcions
        }

        return "Fallos :  " +totalFallos.toString() + " / " + totalOpcions.toString()
    }

    @SuppressLint("Range", "Recycle")
    fun getRondes(): ArrayList<Ronda>{
        val db = writableDatabase
        var rondasList: ArrayList<Ronda> = ArrayList()
        val cursor = db.rawQuery("SELECT * FROM ronda ORDER BY id_ronda ASC", null)
        while (cursor.moveToNext()) {
            val id = (cursor.getString(cursor.getColumnIndex("id_ronda"))).toString()
            val resultat = cursor.getInt(cursor.getColumnIndex("porcentageFallos"))
            val fallos = cursor.getInt(cursor.getColumnIndex("fallos"))
            val numOpcions = cursor.getInt(cursor.getColumnIndex("totalFallos"))

            val ronda = Ronda(
                id,
                fallos,
                resultat,
                numOpcions
            )
            rondasList.add(ronda);

        }
        return rondasList
    }

    fun createPantalla(id_pantalla:String) {
       // val existeRonda = getPantalla(id_pantalla)
//        val db = writableDatabase
//
//        if(existeRonda == null){
//            val cv = ContentValues()
//            cv.put("id_pantalla", id_pantalla)
//            db.insertOrThrow("ultimaPantalla", null, cv)
//        }else{
//            db.execSQL("UPDATE ultimaPantalla SET esUltima = 1 WHERE id_pantalla = "+id_pantalla);
//        }



   //     db.close()
    }

    @SuppressLint("Range")
    fun getPantallaActivate(): Cursor? {
        val db = writableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM ultimaPantalla WHERE esUltima = 1", null)
        val resultado = cursor.moveToFirst()

        if(resultado){
            return cursor
        }


        return null
    }

    @SuppressLint("Range")
    fun getPantalla(id: String): Cursor? {
        val db = writableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM ultimaPantalla WHERE esUltima = 1", null)
        val resultado = cursor.moveToFirst()

        if(resultado){
            db.execSQL("UPDATE ultimaPantalla SET esUltima = 0 WHERE id_pantalla = "+cursor.getString(cursor.getColumnIndex("id_pantalla")).toString());
        }

        val cursor1: Cursor = db.rawQuery("SELECT * FROM ultimaPantalla WHERE id_pantalla = "+id, null)
        val resultado1 = cursor1.moveToFirst()

        if(resultado1){
            db.execSQL("UPDATE ultimaPantalla SET esUltima = 0 WHERE id_pantalla = "+cursor1.getString(cursor1.getColumnIndex("id_pantalla")).toString());

            return cursor
        }

        return null
    }

//    @SuppressLint("Range", "Recycle")
//    fun getPanatalles(){
//        val db = writableDatabase
//        val rondasList: HashMap<String, Pantalla> = HashMap()
//        val cursor = db.rawQuery("SELECT * FROM ultimaPantalla ORDER BY id_pantalla ASC", null)
//        while (cursor.moveToNext()) {
//            val id = (cursor.getString(cursor.getColumnIndex("id_pantalla"))).toString()
//            val esUltima = cursor.getInt(cursor.getColumnIndex("esUltima"))
//
//            val ronda = Pantalla(
//                id,
//                esUltima
//            )
//            rondasList.put(id,ronda);
//
//        }
//        val a = 0
//    }


    companion object {
        private const val KEY_ID = "id_incidencia"
        private const val KEY_NOMBRE = "nombre"
        private const val KEY_DESCRIPCIO = "descripcio"
        private const val KEY_ELEMENT = "element"
        private const val KEY_TIPUS = "tipus"
        private const val KEY_UBICACIO = "ubicacio"
        private const val KEY_DATE = "date"
    }
}