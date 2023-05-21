package app.david.orgue.jocs.jocCinc

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipDescription
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.DragEvent
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import app.david.orgue.*
import app.david.orgue.CaixaValor
import app.david.orgue.Constantes
import app.david.orgue.GestorBDPartida
import app.david.orgue.MainActivity
import app.david.orgue.historia.InfoActivity
import app.david.orgue.jocs.jocSis.JocSisActivity
import app.david.orgue.jocs.jocSis.JocSisActivity.RONDA_UN_SIS


val RESULTADOS_CORRECTOS = mapOf<Int, String>(
    0 to "Gran orgue",
    1 to "Orgue positiu",
    2 to "Orgue portatiu"
  )

const val RONDA_NUMERO_CINC = "5"
const val NUM_OPCIONS_JOC_CINC = 3
const val RONDA_UN_CINC_UN = "app.david.orgue.jocs.jocCinc.JocCincUnActivity"


class JocCincUnActivity: AppCompatActivity() {
    var gbdRest: GestorBDPartida = GestorBDPartida(this)

    @SuppressLint("CutPasteId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.droganddrop)

        gbdRest.createRonda(RONDA_NUMERO_CINC, NUM_OPCIONS_JOC_CINC)

        val imagen1 = findViewById<TextView>(R.id.seleccio1joc6)
        imagen1.setOnLongClickListener(longClickListener)
        val imagen2 = findViewById<TextView>(R.id.opcio1joc6)
        imagen2.setOnDragListener(dragListener)
        val imagen3 = findViewById<TextView>(R.id.seleccio2joc6)
        imagen3.setOnLongClickListener(longClickListener)
        val imagen4 = findViewById<TextView>(R.id.opcio2joc6)
        imagen4.setOnDragListener(dragListener)

        val imagen6 = findViewById<TextView>(R.id.opcio3joc6)
        imagen6.setOnDragListener(dragListener)
        val imagen7 = findViewById<TextView>(R.id.seleccio4joc6)
        imagen7.setOnLongClickListener(longClickListener)


        val buttonSeguent = findViewById<Button>(R.id.siguiente)
        buttonSeguent.setOnClickListener{
            Constantes.writeFile(
                this,
                RONDA_UN_CINC_UN,
                Constantes.FILEPARTIDAACBADA
            )


            var id = 0

            val resultados = arrayOf(findViewById<TextView>(R.id.opcio1joc6).text,  findViewById<TextView>(R.id.opcio2joc6).text,
                findViewById<TextView>(R.id.opcio3joc6).text)

            var opcionsSeleccionades = true

            for(resultado in resultados){
                if(resultado == ""){
                    opcionsSeleccionades = false
                }
            }

            if(opcionsSeleccionades){
                for(value in resultados){
                    if(value != RESULTADOS_CORRECTOS[id]){
                        gbdRest.updateRonda(RONDA_NUMERO_CINC, false, NUM_OPCIONS_JOC_CINC)
                    }
                    id++
                }

                gbdRest.updateRonda(RONDA_NUMERO_CINC, true, NUM_OPCIONS_JOC_CINC, "drag")
                val infoPage = Intent(this@JocCincUnActivity, InfoActivity::class.java)
                infoPage.putExtra("id_ronda", RONDA_NUMERO_CINC)
                infoPage.putExtra("total_opcions", NUM_OPCIONS_JOC_CINC.toString())
                startActivity(infoPage)
            }else{
                val toast1 = Toast.makeText(
                    applicationContext,
                    "Has de seleccionar totes les opcions", Toast.LENGTH_SHORT
                )

                toast1.show()
            }


        }

    }

    override fun onBackPressed() {
        val rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA)
        if (rondaAcabada == RONDA_UN_CINC_UN) {
            Constantes.writeFile(this, RONDA_UN_SIS, Constantes.FILEROUNDNAME);
        }else{
            Constantes.writeFile(this, RONDA_UN_CINC_UN, Constantes.FILEROUNDNAME);
        }



        val main = Intent(this@JocCincUnActivity, MainActivity::class.java)
        startActivity(main);
    }

    private val longClickListener = View.OnLongClickListener { v ->
        val item = ClipData.Item(v.tag as? CharSequence)
        val st = v.tag as? CharSequence
        val dragData = ClipData(
            v.tag as CharSequence,
            arrayOf(ClipDescription.MIMETYPE_TEXT_HTML),
            item
        )

        val myShadow = MyDragShadowBuilder(v)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            v.startDragAndDrop(dragData, myShadow, null, 0)
        }else {
            v.startDrag(dragData, myShadow, null , 0)
        }
        true
    }

    private val dragListener = View.OnDragListener { v, event ->
        val receiverView:TextView = v as TextView

        val texto = findViewById<TextView>(R.id.hola)
        var valorAnterior = CaixaValor()

        when (event.action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                texto.text = "Estàs arrossegant un text"
                true
            }

            DragEvent.ACTION_DRAG_ENTERED -> {
                texto.tag = receiverView.text.toString()
                receiverView.setText(event.clipDescription.label)

                true
            }

            DragEvent.ACTION_DRAG_LOCATION -> {


                true

            }

            DragEvent.ACTION_DRAG_EXITED -> {
                if(receiverView.text != "" &&  texto.tag != ""){
                    receiverView.setText(texto.tag.toString())
                }else {
                    receiverView.setText("")
                }
                true
            }

            DragEvent.ACTION_DROP -> {
                texto.text = "Arrossega els elements a la casella"
                true
            }

            DragEvent.ACTION_DRAG_ENDED -> {
                true
            }

            else -> {
                false
            }
        }
    }

    private class MyDragShadowBuilder(val v: View) : View.DragShadowBuilder(v){
        override fun onProvideShadowMetrics(size: Point, touch: Point){
            size.set(view.width, view.height)
            touch.set(view.width / 2, view.height / 2)
        }

        override fun onDrawShadow(canvas: Canvas?) {
            super.onDrawShadow(canvas)
        }
    }

    override fun onResume() {
        super.onResume()
        val rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA)
        if (rondaAcabada == RONDA_UN_CINC_UN) {
            var imagen2 = findViewById<TextView>(R.id.opcio1joc6)
            var imagen4 = findViewById<TextView>(R.id.opcio2joc6)
            var imagen6 = findViewById<TextView>(R.id.opcio3joc6)

            setColorResult(imagen2, 0)
            setColorResult(imagen4, 1)
            setColorResult(imagen6, 2)


            val seguent = findViewById<Button>(R.id.siguiente)
            seguent.setOnClickListener{
                Constantes.writeFile(this, RONDA_UN_CINC_UN, Constantes.FILEROUNDNAME);
                val main = Intent(this@JocCincUnActivity, JocSisActivity::class.java)
                startActivity(main)
            }
        }
    }
    fun setColorResult(imagen:TextView, index: Int){
        if(imagen.text.equals(RESULTADOS_CORRECTOS.get(index))){
            imagen.setTextColor(Color.GREEN)
        }else{
            imagen.setTextColor(Color.RED)
        }
    }

}