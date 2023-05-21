package app.david.orgue.jocs.jocSet

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import app.david.orgue.*
import app.david.orgue.Constantes
import app.david.orgue.GestorBDPartida
import app.david.orgue.MainActivity
import app.david.orgue.historia.ImageActivity
import app.david.orgue.historia.InfoActivity
import app.david.orgue.jocs.jocVuit.JocVuitActivity

const val RONDA_NUMERO_SET = "7"
const val NUM_OPCIONS_JOC_SET = 5

val PARAULES_CORRECTES = mapOf<Int, String>(
    0 to "Poms dels registres",
    1 to "Tubs",
    2 to "Manuals (teclats)",
    3 to "Pedaler",
    4 to "Pedal de l’expressiu"
)
val RONDA_UN_SET_UN = "app.david.orgue.jocs.jocSet.JocSetUnActivity"

class JocSetUnActivity : AppCompatActivity() {

    var gbdRest: GestorBDPartida = GestorBDPartida(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joc_set_un)

        gbdRest.createRonda(RONDA_NUMERO_SET, NUM_OPCIONS_JOC_SET)

        val barnilles = findViewById<TextView>(R.id.opcio1joc7)
        val caixa = findViewById<TextView>(R.id.opcio2joc7)
        val manuals = findViewById<TextView>(R.id.opcio3joc7)
        val manxes = findViewById<TextView>(R.id.opcio4joc7)
        val pedalExpresiu = findViewById<TextView>(R.id.opcio5joc7)
        val pedaler = findViewById<TextView>(R.id.opcio6joc7)
        val poms = findViewById<TextView>(R.id.opcio7joc7)
        val secret = findViewById<TextView>(R.id.opcio8joc7)
        val tubs = findViewById<TextView>(R.id.opcio9joc7)

        barnilles.setOnClickListener(clickListener)
        caixa.setOnClickListener(clickListener)
        manuals.setOnClickListener(clickListener)
        manxes.setOnClickListener(clickListener)
        pedalExpresiu.setOnClickListener(clickListener)
        pedaler.setOnClickListener(clickListener)
        poms.setOnClickListener(clickListener)
        secret.setOnClickListener(clickListener)
        tubs.setOnClickListener(clickListener)

        val opcio1joc7icon = findViewById<ImageButton>(R.id.opcio1joc7icon)
        val opcio2joc7icon = findViewById<ImageButton>(R.id.opcio2joc7icon)
        val opcio3joc7icon = findViewById<ImageButton>(R.id.opcio3joc7icon)
        val opcio4joc7icon = findViewById<ImageButton>(R.id.opcio4joc7icon)
        val opcio5joc7icon = findViewById<ImageButton>(R.id.opcio5joc7icon)
        val opcio6joc7icon = findViewById<ImageButton>(R.id.opcio6joc7icon)
        val opcio7joc7icon = findViewById<ImageButton>(R.id.opcio7joc7icon)
        val opcio8joc7icon = findViewById<ImageButton>(R.id.opcio8joc7icon)
        val opcio9joc7icon = findViewById<ImageButton>(R.id.opcio9joc7icon)

        opcio1joc7icon.setOnClickListener(clickListenerImage)
        opcio2joc7icon.setOnClickListener(clickListenerImage)
        opcio3joc7icon.setOnClickListener(clickListenerImage)
        opcio4joc7icon.setOnClickListener(clickListenerImage)
        opcio5joc7icon.setOnClickListener(clickListenerImage)
        opcio6joc7icon.setOnClickListener(clickListenerImage)
        opcio7joc7icon.setOnClickListener(clickListenerImage)
        opcio8joc7icon.setOnClickListener(clickListenerImage)
        opcio9joc7icon.setOnClickListener(clickListenerImage)

        val resultados =
            arrayOf(barnilles, caixa, manuals, manxes, pedalExpresiu, pedaler, poms, secret, tubs)
        var buttonSeguent = findViewById<Button>(R.id.siguiente7)
        buttonSeguent.setOnClickListener {
            var totalResultats = 0

            val paraulesSeleccionades = mutableMapOf<String, Boolean>(
                "Poms dels registres" to false,
                "Tubs" to false,
                "Manuals (teclats)" to false,
                "Pedaler" to false,
                "Pedal de l’expressiu" to false
            )
            for (resultado in resultados) {
                if (resultado.background != null) {
                    totalResultats++
                    for ((key, value) in PARAULES_CORRECTES) {
                        if (value == resultado.text) {
                            paraulesSeleccionades["$value"] = true
                            break
                        }
                    }
                }
            }

            for ((texto, resultadoFinal) in paraulesSeleccionades) {
                if (!resultadoFinal) {
                    gbdRest.updateRonda(RONDA_NUMERO_SET, false, NUM_OPCIONS_JOC_SET)
                }
            }

            gbdRest.updateRonda(RONDA_NUMERO_SET, true, NUM_OPCIONS_JOC_SET, "select")

            Constantes.writeFile(
                this,
                RONDA_UN_SET_UN,
                Constantes.FILEPARTIDAACBADA
            )

            val infoPage = Intent(this@JocSetUnActivity, InfoActivity::class.java)
            infoPage.putExtra("id_ronda", RONDA_NUMERO_SET)
            infoPage.putExtra("total_opcions", NUM_OPCIONS_JOC_SET.toString())
            startActivity(infoPage);


        }
    }

    @SuppressLint("ResourceAsColor")
    private val clickListener = View.OnClickListener { v ->
        var tag = v.tag
        if (tag != null && tag != "") {
            v.setBackgroundColor(0x00000000)
            v.setTag("")
        } else {
            v.setBackgroundColor(R.color.teal_200)
            v.setTag("Color")
        }
    }

    private val clickListenerImage = View.OnClickListener { v ->
        val mai1n = Intent(this@JocSetUnActivity, ImageActivity::class.java)
        mai1n.putExtra("id_foto", v.tag.toString())

        startActivity(mai1n)
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onResume() {
        super.onResume()
        val rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA)
        if (rondaAcabada == RONDA_UN_SET_UN) {
            Constantes.writeFile(
                this,
                "app.david.orgue.jocs.jocVuit.JocVuitActivity",
                Constantes.FILEROUNDNAME
            )

            val barnilles = findViewById<TextView>(R.id.opcio1joc7)
            val caixa = findViewById<TextView>(R.id.opcio2joc7)
            val manuals = findViewById<TextView>(R.id.opcio3joc7)
            val manxes = findViewById<TextView>(R.id.opcio4joc7)
            val pedalExpresiu = findViewById<TextView>(R.id.opcio5joc7)
            val pedaler = findViewById<TextView>(R.id.opcio6joc7)
            val poms = findViewById<TextView>(R.id.opcio7joc7)
            val secret = findViewById<TextView>(R.id.opcio8joc7)
            val tubs = findViewById<TextView>(R.id.opcio9joc7)

            barnilles.setOnClickListener(null);
            caixa.setOnClickListener(null);
            manuals.setOnClickListener(null);
            manxes.setOnClickListener(null);
            pedalExpresiu.setOnClickListener(null);
            pedaler.setOnClickListener(null);
            poms.setOnClickListener(null);
            secret.setOnClickListener(null);
            tubs.setOnClickListener(null);


            if (barnilles.background == null) {
                barnilles.setBackgroundColor(Color.GREEN)
            } else {
                barnilles.setBackgroundColor(Color.RED)
            }

            if (caixa.background == null) {
                caixa.setBackgroundColor(Color.GREEN)
            } else {
                caixa.setBackgroundColor(Color.RED)
            }

            if (manuals.background != null) {
                manuals.setBackgroundColor(Color.GREEN)
            } else {
                manuals.setBackgroundColor(Color.RED)
            }

            if (manxes.background == null) {
                manxes.setBackgroundColor(Color.GREEN)
            } else {
                manxes.setBackgroundColor(Color.RED)
            }

            if (pedalExpresiu.background != null) {
                pedalExpresiu.setBackgroundColor(Color.GREEN)
            } else {
                pedalExpresiu.setBackgroundColor(Color.RED)
            }

            if (pedaler.background != null) {
                pedaler.setBackgroundColor(Color.GREEN)
            } else {
                pedaler.setBackgroundColor(Color.RED)
            }

            if (poms.background != null) {
                poms.setBackgroundColor(Color.GREEN)
            } else {
                poms.setBackgroundColor(Color.RED)
            }

            if (secret.background == null) {
                secret.setBackgroundColor(Color.GREEN)
            } else {
                secret.setBackgroundColor(Color.RED)
            }

            if (tubs.background != null) {
                tubs.setBackgroundColor(Color.GREEN)
            } else {
                tubs.setBackgroundColor(Color.RED)
            }


            val seguent = findViewById<Button>(R.id.siguiente7)
            seguent.setOnClickListener {
                val main = Intent(this@JocSetUnActivity, JocVuitActivity::class.java)
                startActivity(main)
            }
        }
    }

    override fun onBackPressed() {
        val rondaAcabada = Constantes.readFile(this, Constantes.FILEPARTIDAACBADA)
        if (rondaAcabada == RONDA_UN_SET_UN) {
            Constantes.writeFile(
                this,
                "app.david.orgue.jocs.jocVuit.JocVuitActivity",
                Constantes.FILEROUNDNAME
            )
        } else {
            Constantes.writeFile(this, RONDA_UN_SET_UN, Constantes.FILEROUNDNAME);
        }

        val main = Intent(this@JocSetUnActivity, MainActivity::class.java)
        startActivity(main);
    }
}