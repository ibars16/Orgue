package app.david.orgue;


import android.content.Context;

import app.david.orgue.R;

import java.io.BufferedReader;
import java.io.*;

public class Constantes {

    public static final int[] raws = { R.raw.passacalgia, R.raw.fuguec, R.raw.musica3, R.raw.minor2, R.raw.minor3, R.raw.minor3};
    public static final String[] rawsName = {"Passacaglia and Fugue in C Minor", "Fantasia and Fugue in C Minor", "Adagio in G Minor", "Toccata and Fugue in E Minor", "Adagio in G Minor", "Organ Symphony No. 5"};


    public static final String FILEROUNDNAME = "pantalla.txt";
    public static final String FILEMUSIC = "musica.txt";
    public static final String FILESOUND = "sonidos.txt";
    public static final String FILEINDEX = "index.txt";
    public static final String FILEPARTIDAACBADA = "partida_acabada.txt";


    public static float parseFileSouns(String volumen){
        float volumenFloat;
        if(volumen.equals("") || volumen == null){
            volumenFloat = 0.5f;
        }else{
            volumenFloat = (float) (Float.parseFloat(volumen)*0.01);
            volumenFloat = Float.parseFloat(volumenFloat + "f");
        }

        return volumenFloat;
    }

    public static String readFile(Context context, String fileName) {
        File file = new File(context.getFilesDir(), fileName);
        String text = "";

        StringBuilder stringBuilder = null;
        if (file.exists()) {
            stringBuilder = null;
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(context.openFileInput(fileName)));
                stringBuilder = new StringBuilder();
                while ((text = bufferedReader.readLine()) != null) {
                    stringBuilder.append(text);
                }
                bufferedReader.close();
                return stringBuilder.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "";
    }

    public static void writeFile(Context context, String string, String fileName) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            fileOutputStream.write(string.getBytes());
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final String[] INFORMACIO_ORGA_TITUL = {
            "EDIFICI",
            "HISTÒRIA",
            "NOU ORGUE",
            "INSTRUMENT",
            "CONSOLA",
            "CAIXA",
            "TECLATS",
            "REGISTRES",
            "PEDAL DE L'EXPRESSIU",
            "CANONADA",
            "SALMER O SECRET",
            "MANXES",
            "MECANISME",


    };

    public static final String[] INFORMACIO_ORGA_DESCRIPCIO = {
            "Començarem la visita fixant-nos en les dimensions de l’edificació on s’ubica l’orgue." +
                    "Cal que sigui gran, ja que l’instrument també ho és." +
                    "A la cantonada podem observar un carreu diferent dels altres" +
                    "Dos lleons sostenen l’escut de la ciutat de Valls." +
                    "Això ens indica que l’edifici pertany a la ciutat i a tota la gent que hi viu." +
                    "A l’interior també podem veure l’escut, ara sí amb els colors de la ciutat, a cadascuna de les claus de volta.",
            "Valls havia tingut sempre un orgue." +
                    "" +
                    "Abans de construir-se l’actual església de st Joan n’hi havia una de més petita, la de St. Miquel. Se sap que aquesta ja tenia un orgue petit." +
                    "" +
                    "Quan es va construir l’església de St. Joan va necessitar un orgue més gran que s’adeqüés a les noves mesures de la nau." +
                    "" +
                    "La construcció del nou orgue es va fer entre els anys 1587 i 1590. Estava situat a la meitat de la nau." +
                    "" +
                    "Els teclats estaven situats en alçada en una mena de balconada." +
                    "" +
                    "L’organista hi accedia per una escala situada a l’interior del mur." +
                    "" +
                    "L’any 1936 va ser destruït i van fondre el metall dels tubs per fer munició per a la Guerra Civil Espanyola. L’últim dia que es va fer sonar va ser el 21 de juliol. Tenia unes portes que es tancaven quan l’orgue no s’utilitzava. Les portes tapaven els tubs de la façana. " +
                    "" +
                    "Molts orgues catalans de l’època tenien portes amb pintures o cortines." +
                    "" +
                    "Les pintures que es veien a la part de fora, quan les portes estaven tancades, mostraven l’Anunciació de l’àngel a Maria. " +
                    "Les de la part de dins, i que es veien quan les portes estaven obertes, ens mostraven en un cantó el naixement de Jesús i en l’altra la resurrecció.",
            "Un grup de ciutadans i institucions de Valls vinculades amb la música han volgut recuperar aquest instrument per a la ciutat." +
                    "" +
                    "Està pensat perquè tingui diferents usos, concerts, aprenentatge de l’instrument, acompanyament als actes litúrgics... El disseny de la façana té molta verticalitat i està inspirat en tres característics de la ciutat: els castells i el campanar tan alt." +
                    "" +
                    "També els tubs estan organitzats en corones que simulen la pinya dels castells." +
                    "" +
                    "Té una alçada de 14,7 metres i tindrà un registre amb el so de les gralles.",
            "L’orgue és un instrument de vent o aeròfon. L’instrumentista acciona les tecles i uns mecanismes fan passar l’aire pels tubs." +
                    "" +
                    "Degut a les seves dimensions considerables ha d’estar situat en espais grans com són auditoris, sales de concerts, palaus o esglésies." +
                    "" +
                    "Els primers orgues, però, eren molt petits i es podien transportar. " +
                    "" +
                    "Cada tub està associat a una tecla segons la nota que volem que soni tal com es pot veure en aquest orgue portatiu. Amb el temps es van anar ampliant la quantitat de tubs i registres i va evolucionar cap a l’instrument que coneixem avui en dia." +
                    "" +
                    "No tots els tubs de l’orgue es veuen. A l’interior de l’instrument n’hi ha molts més." +
                    "" +
                    "Els orgues petits poden tenir uns 100 tubs." +
                    "" +
                    "Un orgue mitjà té entre 1000 i 2500 tubs." +
                    "" +
                    "Els orgues més grans poden arribar a tenir 11ooo tubs tal com el que hi ha al Palau Nacional de Catalunya a Barcelona.",
            "És la part on hi ha situats els teclats i els registres. Podríem dir que és el centre de comandament per a l’organista.",
            "És el moble o estructura que sosté l’instrument. Conté els tubs i els mecanismes per a fer-lo funcionar. En aquesta imatge té forma de mà." +
                    "L’orgue de Valls no té pròpiament un moble sinó que una estructura interna és la que sosté els tubs.",
            "Estan formats per unes palanques horitzontals basculants que formen l’escala dels sons." +
                    "" +
                    "El pedaler és el teclat que s’acciona amb els peus i fa sonar els tubs més grossos i més greus." +
                    "" +
                    "Els orgues considerats petits tenen un teclat" +
                    "" +
                    "Els orgues mitjans 2 o 3 teclats, un dels quals pot ser pedaler." +
                    "" +
                    "Els orgues grans poden tenir més de 4 teclats i 1 pedaler." +
                    "" +
                    "De fet podríem dir que cada teclat és un orgue diferent.",
            "Són uns botons de fusta o plaques basculants que en activar-los permeten el pas de l'aire cap al conjunt de tubs que l’organista vol que sonin en un moment determinat. " +
                    "" +
                    "Cada registre dona un timbre i un color sonor diferent segons les característiques dels tubs: la forma, la mida, la manera de produir el so … " +
                    "" +
                    "Acostumen a portar un nom indicatiu del timbre (flauta, trompeta…) i un número que indica l’altura del tub que fan sonar. (16’, 8’, 4’, ….)" +
                    "" +
                    "Estan agrupats de manera que cada teclat té el seu grup de registres." +
                    "" +
                    "Els orgues petits tenen uns 12 registres." +
                    "Els orgues mitjans tenen fins a 30 registres" +
                    "Els orgues grans tenen més de 30 registres.",
            "Aquest pedal permet graduar la intensitat del so del teclat que es diu Expressiu." +
                    "" +
                    "Els tubs associats a aquest teclat estan tancats dins d’una caixa amb persianes. Si s’acciona el pedal es van obrint més o menys segons la intensitat que li vulguem donar a la melodia.",
            "Són el conjunt de tubs. Els principals estan situats a la façana i els altres a la part interior." +
                    "" +
                    "Els tubs de la façana poden estar decorats." +
                    "" +
                    "Els materials utilitzats són una aleació d’estany i plom o fusta per als que són prismàtics." +
                    "" +
                    "A l’orgue de Valls el tub més llarg fa 5 metres i el més curt és de la mida de l’ungla del dit gros. Els tubs estan agrupats per registres.  Poden ser oberts, semicapats o tapats" +
                    "" +
                    "Poden tenir forma cilíndrica, cònica o de prisma quadrangular." +
                    "" +
                    "El so es pot produir per la vibració de l’aire en una boca amb bisell o en una llengüeta al tub." +
                    "" +
                    "El timbre característic de cada tub estarà en funció de tots aquests paràmetres."+
                    "Quan es construeix un orgue hi ha un grup de tubs que hi han de ser si o sí. Són els que es diuen “Principals”."+
                    "Els altres poden anar variant segons els gustos musicals de la zona." +
                    "" +
                    "En l’orgue de Valls s’hi ha posat un grup de tubs associats al registre de gralla, ja que som una zona castellera."+
                    "Estan situats just damunt de la consola. " +
                    "" +
                    "Tenen forma cònica, són de fusta i el so es produeix fent vibrar una llengüeta.",
            "És la caixa de fusta que distribueix el vent entre els tubs." +
                    "" +
                    "És rectangular i al damunt hi ha els tubs ordenats de cada registre." +
                    "" +
                    "Cada canal té una vàlvula, ventallola,  que permet el pas de l’aire quan es polsa una tecla." +
                    "" +
                    "Cada teclat té el seu salmer amb els tubs disposats en fileres paral·leles.",
            "És el mecanisme que dona aire a l’orgue per al seu funcionament." +
                    "" +
                    "Antigament estaven accionats manualment per uns manxaires, però avui en dia és un motor elèctric qui produeix l’aire. També hi ha uns pesos que controlen que la pressió sigui constant.",
            "Quan l’organista prem cadascuna de les tecles tiba una vareta que obre el pas de l’aire dels tubs que sonen amb aquella nota. "+
                    "L’aire entra a pressió a través de les vàlvules cap al secret segons els registres que tenim activats."





    };

    public static final int[] INFORMACIO_ORGA_IMATGE = {
            R.drawable.ubicacio1,
            R.drawable.historia1,
            R.drawable.nouorgue,
            R.drawable.oega,
            R.drawable.consola,
            R.drawable.caixa,
            R.drawable.teclat,
            R.drawable.pomsregistres,
            R.drawable.pedalexpressio,
            R.drawable.canonada,
            R.drawable.secrets,
            R.drawable.manxaorguevalls,
            R.drawable.mecanisme,


    };

    public static final String[] INFORMACIO_QR = {
            "Aquests carreus donen informació de qui i com s’ha pogut construir aquest edifici." +
                    "Els lleons representen la força, el valor i la noblesa i, per altra banda, veiem l’escut de la ciutat." +
                    "Els habitants de la ciutat s’han preocupat per poder-lo construir i hi ha col·laborat en la mesura que els era" +
                    "possible.",
            "Ara ja podeu entrar a la nau. Durant la visita haureu d’anar-vos fixant si hi ha recordatoris referents a la ciutat tal" +
                    "com hem vist a fora. Quantifiqueu-los i al final del recorregut trieu la resposta correcta.",
            "La ciutat de Valls se sap que ja tenia orgue des de fa molts anys. Quan es va construir aquest edifici ja es va tenir" +
                    "present que n’havia de tenir un i l’any 1590 se sap que ja va sonar per primer cop. L’orgue que podeu veure a la" +
                    "fotografia és el resultat de les restauracions que aquell orgue va anar tenint al llarg dels anys." +
                    "Durant 346 anys hi va haver un orgue sonant en aquesta nau fins que el 21 de juliol de l’any 1936, amb la guerra" +
                    "civil, va ser destruït." +
                    "Han hagut de passar més de vuitanta  anys per a poder recuperar un orgue per a la ciutat.",
            "La recuperació de l’orgue ha estat possible gràcies al treball constant durant els últims anys d’una Comissió" +
                    "ciutadana formada per persones que estimen la ciutat i la cultura. Per altra banda, els padrins de l'orgue són els qui," +
                    "amb les seves aportacions econòmiques, han fet possible la construcció de l’instrument que avui visitem.",
            "L’antic orgue estava situat a la meitat de la nau i suspès dalt d’una balconada tal com podeu veure en les imatges." +
                    "De vegades els orgues estan situats just damunt la porta d’entrada de l’edifici en la zona del cor." +
                    "Hi ha diferents tipus d’orgues segons les èpoques, la utilització que se’n feia, els espais on estaven ubicats...",
            "L’orgue és un instrument de vent, això vol dir que el so el produeix l’aire quan vibra en passar pels tubs." +
                    "Aquest instrument tan gran té unes parts visibles, la façana i la consola, i unes parts que estan a l’interior i que són" +
                    "els mecanismes que fan que l’orgue soni. La consola és el centre de comandament de la persona que toca" +
                    "l’instrument.",
            "Aquest orgue en l’actualitat consta de 1384 tubs, però està preparat per a tenir-ne 2504. Cada tecla té associat, com" +
                    "a mínim, un tub per a cada un dels registres que es puguin activar." +
                    "Recordeu que la recuperació i construcció ha estat possible gràcies a les donacions de gent particular, empreses de" +
                    "la ciutat i les administracions." +
                    "Hi ha dos tipus de tubs segons la manera de produir el so: els de boca o labials i els de llengüeta. També la mida, la" +
                    "forma i els materials dels quals estan fets els tubs donen una sonoritat diferent segons el que es requereix per a" +
                    "cada un dels registres." +
                    "Si l’organista activa el registre de trompeta sonaran els tubs que estan modelats per a produir aquest tipus de so." +
                    "Si activa el registre de gralla sonaran els tubs que estan preparats per a fer-ho i així amb tots els sons." +
                    "L’organista també pot combinar diferents registres per a tocar alhora." +
                    "Cada grup de registres està associat a un teclat (manual o pedaler) però l’organista també pot combinar els" +
                    "registres d’un teclat amb els d’un altre."
    };

    public Constantes() {
    }
}
