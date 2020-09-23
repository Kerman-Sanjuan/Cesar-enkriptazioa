package segurtasuna;
import java.util.*;
import java.lang.*;

public class Main {

    public static void main(String[] args) {
        OrdezkatzeHiztegia ord = new OrdezkatzeHiztegia();

        Scanner sc = new Scanner(System.in);
        System.out.println("Kaixo, programa hau mezuak kodetu eta deskodetzen ditu, gako baten arabera. Mezedez, zure aukera idatzi. ");
        System.out.println("1 zenbakia idatzi mezu bat kodetu nahi baduzu");
        System.out.println("2 zenbakia idatzi mezu bat kodetu nahi baduzu");
        boolean eginda = false;
        while (!eginda) {

            int aukera = sc.nextInt();
            switch (aukera) {
                case 1:
                    Scanner sca = new Scanner(System.in);
                    //System.out.println("Lehenengoz, gakoa idatzi");
                    //String gakoa = sca.nextLine();
                    System.out.println("Horain, kodetu nahi duzun esaldia idatzi");
                    String esaldia = sca.nextLine();
                    ord.zifratu(esaldia);
                    eginda = true;
                    break;

                case 2:
                    Scanner sc2 = new Scanner(System.in);
                    //System.out.println("Gakoa idatzi");
                    //String gakoa2 = sc2.nextLine();
                    System.out.println("Kriptograma idatzi");
                    String kriptograma = sc2.nextLine();
                    ord.deszifratu(kriptograma);
                    eginda = true;
                    break;
                default:
                    System.out.println("Beste zenbaki bat idatzi duzu, 1 edo 2 idatzi");
                    break;
            }


        }

    }
}