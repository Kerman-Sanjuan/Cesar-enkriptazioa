package segurtasuna;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class OrdezkatzeHiztegia {
    private String gakoa = "ZXCVBNMASDFGHJKLQWERTYUIOP"; //Aldatu daiteke nahi denean, edozein baliorekin funtzionatuko du.

//----------------------------- ZIFRATZEKO KODEA ETA BEHARREZKO METODOAK------------------------//
    public String zifratu(String mezua){
        gakoa = gakoa.replaceAll("\\s", ""); //Gako guztiekin funtzionatzeko.
        gakoa = gakoa.toUpperCase();
        HashMap<Character, Integer> alfabetoa = sortuAlfabetoa();
        HashMap<Integer, Character> cesar = lortuAlfabetoa(gakoa);
        StringBuilder kodeketa = null;

        for(int i = 0; i < mezua.length(); ++i) {
            char aux = mezua.charAt(i);
            if (Character.isWhitespace(aux)) {
                if (kodeketa == null) throw new AssertionError();
                kodeketa.append(" ");
            } else {
                int ans = alfabetoa.get(aux);
                if (i == 0) {
                    kodeketa = new StringBuilder(cesar.get(ans).toString());
                } else {
                    kodeketa.append(cesar.get(ans));
                }
            }
        }

        System.out.println(kodeketa);
        System.out.println("Kodetu duzu!");
        assert kodeketa != null;
        return kodeketa.toString();
    }


//---------------------------------DESZIFRATZEKO KODEA ETA BEHARREZKO--------------------//

    public  String deszifratu(String kripto){
        Collection<Character> values = lortuAlfabetoa(gakoa).values();
        ArrayList<Character> kodeta = new ArrayList<>(values);
        ArrayList<Character> alfabetoa = new ArrayList<>(sortuAlfabetoa().keySet());
        StringBuilder emaitza = null;

        for(int i = 0; i <= kripto.length() - 1; ++i) {
            char aux = kripto.charAt(i);
            if (Character.isWhitespace(aux)) {
                assert emaitza != null;

                emaitza.append(" ");
            } else {
                int ans = kodeta.indexOf(aux);
                if (i == 0) emaitza = new StringBuilder(alfabetoa.get(ans).toString());
                else {
                    emaitza.append(alfabetoa.get(ans));
                }
            }
        }

        System.out.println(emaitza);
        System.out.println("Kriptograma deskodetua modu egokian!!");
        assert emaitza != null;
        return emaitza.toString();
    }



    //----------------METODO LAGUNTZAILEAK---------------------//
// Alfabeto normala itzuliko du.
    private  HashMap<Character, Integer> sortuAlfabetoa() {
        HashMap<Character, Integer> abc = new HashMap<>();

        for(int i = 0; i <= 25; ++i) {
            abc.put((char)(65 + i), i);
        }

        return abc;
    }
    //Berdin du gakoa zein den, honek bere "alfabetoa" lortuko du, tamainaren independenteki.
    //Gerta daiteke gakoak letra errepikatuak eta bestelakoak edukitzea, kontuan eduki behar dira kasu horiek.
    private  HashMap<Integer, Character> lortuAlfabetoa(String gakoa) {
        HashMap<Integer, Character> abc = new HashMap();
        char[] letr = gakoa.toCharArray();
        Character[] lis = new Character[26];
        int i;
        for(i = 0; i <= 25; ++i) {
            lis[i] = (char)(65 + i);
        }
        i = 0;
        int desfasea = 0;
        while(i + desfasea <= gakoa.length() - 1) {
            if (abc.containsValue(letr[i])) {
                ++desfasea;
            } else {
                abc.put(i, letr[i + desfasea]);
                ++i;
            }
        }
        for(int n = gakoa.length() - desfasea; n <= 25; ++n) {
            if (!abc.containsValue(lis[n])) {
                abc.put(n, lis[n]);
            }
        }
        return abc;
    }
}
