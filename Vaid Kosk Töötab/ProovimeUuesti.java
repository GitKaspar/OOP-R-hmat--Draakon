import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProovimeUuesti {

    public static void main(String[] args) throws Exception{

        Tegelane õpilane = new Tegelane();

        loeFailEtte("algus.txt");

        System.out.println("\n1. Alusta");
        System.out.println("2. Lõpeta\n");

        kaheneValik("šamaani tutvustus.txt");

        System.out.println("\nVõid ülesannetega alustada sealt, kust soovid.\n");
        System.out.println("Valikud:");
        System.out.println("1. Kose juurde salamandrit otsima.");
        System.out.println("2. Metsa ebajumala altarit hävitama.");
        System.out.println("3. Jürka juurde ohvrilooma otsima.");
        System.out.println("4. Viirukimeistrit otsima.");
        System.out.println("5. Ebajumala usukuulutajat peletama.\n");

        Scanner kohad = new Scanner(new File("kohad.txt"), "UTF-8");
        List<Koht> kohaList = new ArrayList<>();
        while (kohad.hasNextLine()) {
            String rida = kohad.nextLine();
            String[] tükeldatudRida = rida.split(";");
            Koht uusKoht = new Koht(tükeldatudRida[0], Boolean.parseBoolean(tükeldatudRida[1]), tükeldatudRida[2], new Eesmärk(tükeldatudRida[3]));
            kohaList.add(uusKoht);}

        valiÜlesanne(kohaList, õpilane);

    }

    public static void kaheneValik(String failiNimi) throws Exception{
        Scanner sisendKlaverilt = new Scanner(System.in);
        int valik = sisendKlaverilt.nextInt();

        if (valik == 1) {
            loeFailEtte(failiNimi);
        } else if(valik == 2) { // Kui siia jääks vaid if, käivituks sisendi 1 puhul ka else. Miks?
            System.out.println("\nHead aega!");
            System.exit(0);
        } else {
            System.out.println("\nValid valikud 1 ja 2 töötavad.\n");
            kaheneValik(failiNimi);
        }
    }

    public static void väljastaPausiga(String lause) throws Exception {
        if (lause.isEmpty()) {
            Scanner jätka = new Scanner(System.in);
            System.out.println("\n...");
            jätka.nextLine();
        } else {
            int reapikkus = lause.length();
            System.out.println(lause);
            Thread.sleep(reapikkus); // reapikkus * 75 hetkel vist okei.
        }
    }

    public static void valiÜlesanne(List<Koht> kohad, Tegelane tegelane) throws Exception{
        Scanner valik = new Scanner(System.in);
        System.out.println("Kuhu kõigepealt?" );
        int valitudÜlesanne = valik.nextInt();

        switch (valitudÜlesanne) {
            case 1: {
                loeFailEtte("kosk_sj.txt");
                kohad.get(0).koskValiKoht(juhuslikArv(3));
                kohad.get(0).annaEse(tegelane);
                if (kohad.get(0).isKasÕnnestus())
                {   loeFailEtte("kosk_edu.txt");
                    kohad.get(0).koskLõpeta();
                }
                else
                    loeFailEtte("kosk_ebaõnn.txt");
                System.exit(0);
            }
            case 2: {System.out.println("See ülesanne pole veel valmis.");
                System.exit(0);}
            case 3: {System.out.println("See ülesanne pole veel valmis.");
                System.exit(0);}
            case 4: {System.out.println("See ülesanne pole veel valmis.");
                System.exit(0);}
            case 5: {System.out.println("See ülesanne pole veel valmis.");
                System.exit(0);}
            default: {// Küsi uuesti numbrit.
                System.out.println("Vahemikus 1-5, palun.");
                valiÜlesanne(kohad, tegelane);}
        }
    }

    public static void loeFailEtte(String failiNimi) throws Exception {
        Scanner lugeja = new Scanner(new File(failiNimi), "UTF-8");
        while (lugeja.hasNextLine()) {
            String rida = lugeja.nextLine();
            int reapikkus = rida.length();
            väljastaPausiga(rida);
        }
    }

    static int juhuslikArv(int valikuid) {
        int min = 1;
        int max = valikuid; // Murekoht? +1 või mitte?
        int tulemus = (int) (min + Math.random() * valikuid);
        return tulemus;
    }

}
