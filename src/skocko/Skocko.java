package skocko;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Skocko {

    public static void main(String[] args) {

        int brojac = 0; //brojac 7 krugova
        int score = 0; //skor
        int igra = 0; //brojac partija
        boolean pobeda = false;
        boolean novaIgra = false;

        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        //pravljenje liste
        ArrayList<Integer> randomLista = new ArrayList<Integer>(); // Random lista
        ArrayList<Integer> mojaLista = new ArrayList<Integer>(); // Lista koju ja unoism

        //Pomocni nizovi
        int[] pomNiz1 = new int[4]; //niz koji mi pamti brojeve na mestu
        int[] pomNiz2 = new int[4]; //niz koji mi pamti brojeve koji nisu na mestu

        do {

            if (novaIgra == false) {
                //Upis u random listu
                randomLista.add(r.nextInt(6) + 1);
                randomLista.add(r.nextInt(6) + 1);
                randomLista.add(r.nextInt(6) + 1);
                randomLista.add(r.nextInt(6) + 1);
            }

            //Ispis randomListe zbog provere
            System.out.println(randomLista);

            novaIgra = true;

            System.out.println("------------------------------------------------");
            System.out.println("Unesite kombinaciju od 4 broja: ");
            System.out.println("------------------------------------------------");

            //Upisivanje moje liste
            for (int i = 0; i < 4; i++) {

                System.out.println("Unesite " + (i + 1) + " element niza: ");

                int a = sc.nextInt();

                mojaLista.add(a);
            }
            System.out.println("Vasa kombinacija: " + mojaLista);
            System.out.println("------------------------------------------------");

            int sadrzi = 0;
            int sadrziNaMestu = 0;

            for (int i = 0; i < mojaLista.size(); i++) {

                //Ako su isti brojevi i na istom mestu, u oba niza mi upisuje 1, tj markira polja
                if (randomLista.get(i).equals(mojaLista.get(i))
                        && pomNiz1[i] == 0 && pomNiz2[i] == 0) {

                    pomNiz1[i] = 1;
                    pomNiz2[i] = 1;

                } /*Ako predhodni uslov nije ispunjen
                 
                ispitujem sledece:
                prvi if uslov mi ispituje da li su elementi oba niza sa istog indeksa isti broj
                ako jesu u oba pomocna niza mi upisuje 1
                drugi if uslov proverava da li element postoji uopste u nizu, ako postoji u pomocniNiz1 mi upisuje 2 na tom mestu
                a u pomNizu2 jedinicu, tj markira mesto da postoji da ne bi bilo duplikata
                
                 */ else {
                    for (int j = 0; j < mojaLista.size(); j++) {
                        for (int k = 0; k < randomLista.size(); k++) {

                            if (mojaLista.get(k).equals(randomLista.get(k))) {

                                pomNiz1[k] = 1;
                                pomNiz2[k] = 1;

                            } else if (mojaLista.get(i).equals(randomLista.get(j)) && pomNiz1[i] == 0 && pomNiz2[j] == 0) {

                                pomNiz1[i] = 2;
                                pomNiz2[j] = 1;
                            }

                        }

                    }

                }
            }
            // ispitujem brojeve iz pomNiz1, ako je 1 znaci da je broj na mestu, ako je 2 znaci da postoji ali nije na mestu
            for (int i : pomNiz1) {

                if (i == 1) {

                    sadrziNaMestu++;

                } else if (i == 2) {

                    sadrzi++;
                }

            }
            // u slucaju pobednika
            if (sadrziNaMestu == 4) {

                pobeda = true;
                igra++;
                //dodavanje poena u zavisnosti broja poteza, skor inkrementiramo zbog krajnjeg rezultata ako imamo vise partija odigranih
                if (brojac < 6) {
                    score = score + 30;
                } else {
                    score = score + 20;
                }

                System.out.println("Pogodjeni svi brojevi!!! Pritisnite 1.Za novu igru ||| 2. Za kraj:");

                int opcija = sc.nextInt();

                //Upitnik za exit ili za novu igru
                while (opcija < 1 || opcija > 2) {
                    System.out.println("Uneli ste pogresan broj, unesite ponovo:");
                    opcija = sc.nextInt();
                }

                if (opcija == 1) {

                    // nova igra, restart randomListe
                    System.out.println("Nova igra poinje:");
                    randomLista.removeAll(randomLista);
                    novaIgra = false;

                } else {
                    //Exit, ispis skora i broj partija
                    System.out.println("Igra je zavrsena!");
                    System.out.println("Vas skor je: " + score + " odigranih partija: " + igra);
                    return;
                }
            }
            if (novaIgra == true) {

                System.out.println("Broj pogodjenih brojeva: " + (sadrzi + sadrziNaMestu) + " od toga su na mestu: " + sadrziNaMestu);
                System.out.println("------------------------------------------------");
            }

            //Praznjenje lista u svakoj iteraciji
            mojaLista.clear();
            pomNiz1 = new int[4];
            pomNiz2 = new int[4];

            brojac++;

        } while (brojac < 7 || pobeda == true);

        igra++;
        System.out.println("Niste uspeli da pogodite kombinaciju iz sedam pokusaja, igrica je zavrsena!!!");
        System.out.println("Kombinacija je bila : " + randomLista);

    }

}
