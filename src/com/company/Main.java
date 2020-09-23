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
                        kodetu();
                        eginda = true;
                        break;

                    case 2:
                        dekodetu();
                        eginda = true;
                        break;
                    default:
                        System.out.println("Beste zenbaki bat idatzi duzu, 1 edo 2 idatzi");
                        break;
                }



        }

        //----------------------------- KODETU METODOAK  ETA BEHAR DITUEN AZPIPROGRAMAK------------------------------
    }
    private static void kodetu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Lehenengoz, gakoa idatzi ezazu AVISO, ESTAMOS USANDO" +
                " LA ADAPTACIÓN DE JUANAN, ASIQUE USAR LA KEY QUE EL NOS DA: ZXCVBNMASDFGHJKLQWERTYUIOP ");
        String gakoa = sc.nextLine();
        gakoa = gakoa.replaceAll("\\s",""); //Quitamos espacios por si la gakoa es una frase.
        gakoa = gakoa.toUpperCase(); //Por si nos ponen la gakoa en minusculas.
        HashMap<Character,Integer> alfabetoa = sortuAlfabetoa();
        HashMap<Integer,Character> cesar = lortuAlfabetoa(gakoa);
        //Orain aldatuko  dugu gure esaldia
        System.out.println("Horain, kodetu nahi duzun esaldia idatzi");
        String esaldia = sc.nextLine();
        //Esaldia igaroko dugu eta kodeketa sortuko dugu.
        char aux;
        int ans;
        StringBuilder kodeketa = null;

        for (int i = 0; i < esaldia.length(); i++) {
            aux = esaldia.charAt(i);
            if (Character.isWhitespace(aux)) {
                assert kodeketa != null;
                kodeketa.append(" ");
            } else {
                ans = alfabetoa.get(aux);
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
    }



    private static HashMap<Character,Integer> sortuAlfabetoa(){
        //Crea el diccionario normal y corriente.
        HashMap<Character,Integer> abc = new HashMap<>();
        for (int i = 0; i <= 25; i++) {
            abc.put((char)('A' + i),i); //Kasting bat egingo dugu.

        }
        return abc;
    }



    private static HashMap<Integer,Character> lortuAlfabetoa(String gakoa){
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
    private static void dekodetu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Gakoa idatzi");
        String gakoa = sc.nextLine();
        System.out.println("Kriptograma idatzi");
        String kriptograma = sc.nextLine();
        //Arazoak daudenez hashmap-aren datu egiturarekin, arrayList era bihurtuko ditugu bi listak.
        //Guk lortutako hiztegiaren deskonposaketa
        Collection<Character> values = lortuAlfabetoa(gakoa).values();
        ArrayList<Character> kodeta = new ArrayList<>(values);
        //Hiztegi normalaren deskonposaketa
        ArrayList<Character> alfabetoa = new ArrayList<>(sortuAlfabetoa().keySet());
        StringBuilder emaitza = null;

        for (int i = 0; i<=kriptograma.length()-1;i++) {

            char aux = kriptograma.charAt(i);
            if (Character.isWhitespace(aux)) {
                assert emaitza != null;
                emaitza.append(" ");
            } else {

                int ans = kodeta.indexOf(aux);
                if (i == 0){
                    emaitza = new StringBuilder(alfabetoa.get(ans).toString());
                }else{
                    emaitza.append(alfabetoa.get(ans));
                }

            }
        }
        System.out.println(emaitza);
        System.out.println("Kriptograma dekodetua modu egokian!!");
    }
}
