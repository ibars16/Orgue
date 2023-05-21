package app.david.orgue.jocs

class Ronda {
    private var id_ronda:String? = null
    private var fallos:Int? = null
    private var resultat:Int? = null
    private var numOpcions:Int? = null


    constructor(id_ronda: String?, fallos: Int?, resultat: Int?, numOpcions:Int?) {
        this.id_ronda = id_ronda
        this.resultat = resultat
        this.fallos = fallos
        this.numOpcions = numOpcions

    }
    fun getIdronda(): String{
        return this.id_ronda!!
    }

    fun setIdronda(id_ronda:String){
        this.id_ronda = id_ronda
    }

    fun getResultat(): Int{
        return this.resultat!!
    }

    fun setResultat(resultat:Int){
        this.resultat = resultat
    }

    fun getFallos(): Int{
        return this.fallos!!
    }

    fun setFallos(numOpcions:Int){
        this.numOpcions = numOpcions
    }
    fun getNumOpcions(): Int{
        return this.numOpcions!!
    }

    fun setNumOpcions(numOpcions:Int){
        this.numOpcions = numOpcions
    }

}