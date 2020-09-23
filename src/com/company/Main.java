package com.company;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
	    System.out.println("Kaixo, programa hau mezuak kodetu eta deskodetzen ditu, gako baten arabera. Mezedez, zure aukera idatzi. ");
	    System.out.println("1 zenbakia idatzi mezu bat kodetu nahi baduzu");
	    System.out.println("2 zenbakia idatzi mezu bat kodetu nahi baduzu");
	    boolean eginda = false;
	    while (!eginda) {

                int aukera = sc.nextInt();
                switch (aukera) {
                    case 1:
                        Scanner sc1 = new Scanner(System.in);
                        String gakoa = sc1.nextLine();
                        System.out.println("Horain, kodetu nahi duzun esaldia idatzi");
                        String esaldia = sc1.nextLine();
                        kodetu(gakoa,esaldia);
                        eginda = true;
                        break;

                    case 2:
                        Scanner sc2 = new Scanner(System.in);
                        System.out.println("Gakoa idatzi");
                        String gakoa2 = sc2.nextLine();
                        System.out.println("Kriptograma idatzi");
                        String kriptograma = sc2.nextLine();
                        dekodetu(kriptograma,gakoa2);
                        eginda = true;
                        break;
                    default:
                        System.out.println("Beste zenbaki bat idatzi duzu, 1 edo 2 idatzi");
                        break;
                }



        }

        //----------------------------- KODETU METODOAK  ETA BEHAR DITUEN AZPIPROGRAMAK------------------------------
    }
    public static String kodetu(String gakoa, String esaldia){
        gakoa = gakoa.replaceAll("\\s",""); //Quitamos espacios por si la gakoa es una frase.
        gakoa = gakoa.toUpperCase(); //Por si nos ponen la gakoa en minusculas.
        HashMap<Character,Integer> alfabetoa = sortuAlfabetoa();
        HashMap<Integer,Character> cesar = lortuAlfabetoa(gakoa);
        char aux;
        int ans;
        String kodeketa = null;
        for (int i = 0; i < esaldia.length(); i++) {
            aux = esaldia.charAt(i);
            if (Character.isWhitespace(aux)) {
                assert kodeketa != null;
                kodeketa = kodeketa + " ";
            } else {
                ans = alfabetoa.get(aux);
                if (i == 0) {
                    kodeketa = cesar.get(ans).toString();
                } else {
                    kodeketa = kodeketa+ cesar.get(ans);
                }
            }
        }
        System.out.println("Kodetu duzu!");
        assert kodeketa != null;
        return kodeketa;
    }

    private static HashMap<Character,Integer> sortuAlfabetoa(){
        //Crea el diccionario normal y corriente.
        HashMap<Character,Integer> abc = new HashMap<>();
        for (int i = 0; i <= 25; i++) {
            abc.put((char)('A' + i),i); //Kasting bat egingo dugu.

        }
        return abc;
    }



    public static HashMap<Integer,Character> lortuAlfabetoa(String gakoa){
        //Por ahora va a devolver un diccionario aleatorio, sin relación con la key. Hay que encontrar una forma de descodificar.
        HashMap<Integer,Character> abc = new HashMap<>();
        //Algoritmo para generar el diccionario en función de la key
        char[] letr = gakoa.toCharArray();
        //Creamos el abecedario como una lista.
        Character[] lis = new Character[26];
        for (int i = 0; i <= 25; i++) {
            lis[i] = (char)('A' + i);
        }
        //Añadimos primero la palabra del gakoa.
        int  i = 0;
        int desfase = 0;
        while(i+desfase <= gakoa.length()-1 ) {
            if (abc.containsValue(letr[i])) {
                desfase++;
            } else {
                abc.put(i, letr[i + desfase]);
                i++;
            }
        }
         for (int n = gakoa.length()-desfase; n <= 25; n++) {
           // abc.put(i, list.get(i));
            if(!abc.containsValue(lis[n])) {
                abc.put(n, lis[n]);
            }
        }
        return abc;
    }




    //----------------------- DEKODETU ETA BEHAR DITUEN AZPIPROGRAMAK-----------------------------------------
    public static String dekodetu(String kriptograma, String gakoa){

        //Arazoak daudenez hashmap-aren datu egiturarekin, arrayList era bihurtuko ditugu bi listak.
        //Guk lortutako hiztegiaren deskonposaketa
        Collection<Character> values = lortuAlfabetoa(gakoa).values();
        ArrayList<Character> kodeta = new ArrayList<>(values);
        //Hiztegi normalaren deskonposaketa
        ArrayList<Character> alfabetoa = new ArrayList<>(sortuAlfabetoa().keySet());
        String emaitza = null;
        System.out.println(kriptograma.length()-1);
        for (int i = 0; i<=kriptograma.length()-1;i++) {

            char aux = kriptograma.charAt(i);
            if (Character.isWhitespace(aux)) {
                assert emaitza != null;
                emaitza = emaitza + " ";
            } else {
                int ans = kodeta.indexOf(aux);
                if (i == 0) {
                    emaitza = alfabetoa.get(ans).toString();
                } else {
                    emaitza = emaitza + alfabetoa.get(ans);
                }
            }
        }
        System.out.println(emaitza);
        System.out.println("Kriptograma dekodetua modu egokian!!");
        return emaitza;
    }
}
