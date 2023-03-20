import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class Peamine {
    // Erindi viskamiseta ei saa failist lugeda - veateade.
    public static void main(String[] args) throws Exception {

        System.out.println("Teretulemast mängima meie mängu \"Draakon\"!");
        System.out.println("Tekst kuvatakse mängijale iseenesest.");
        System.out.println("Kui ilmuvad kolm punkti (...), kliki järgmisel real ja vajuta jätkamiseks \"Enter\".");
        System.out.println("Valiku kinnitamiseks kliki mänguaknas, sisesta valiku number ja vajuta \"Enter\".");
        System.out.println("Toredat mängimist!");
        System.out.println();

        Scanner tutvustus = new Scanner(new File("šamaani tutvustus.txt"), "UTF-8");
        while (tutvustus.hasNextLine()) {
            String rida = tutvustus.nextLine();
            int reapikkus;
            if (rida.isEmpty())
                reapikkus = 50; // hilisemate valikute testimiseks sea väärtuseks 0
            else
                reapikkus = rida.length(); // hilisemate valikute testimiseks sea väärtuseks 0
            väljastaPausiga(rida, reapikkus / 15); // vaikimisi 15 hetkel parim
        }

        // Loeme failist kohad
        Scanner kohad = new Scanner(new File("kohad.txt"), "UTF-8");
        List<Koht> kohaList = new ArrayList<>();
        while (kohad.hasNextLine()) {
            String rida = kohad.nextLine();
            String[] tükeldatudRida = rida.split(";");
            Koht uusKoht = new Koht(tükeldatudRida[0], Boolean.parseBoolean(tükeldatudRida[1]));
            kohaList.add(uusKoht);
        }

        System.out.println();
        väljastaPausiga("Vana astub oiates oma telki.", 1);
        väljastaPausiga("Vaatad veidi kahvatus külas ringi ja sead end minekuks valmis.", 1);
        väljastaPausiga("Ülesande täitmist võid alustada sealt, kust soovid.", 1);
        väljastaPausiga("Kuhu kõigepealt?", 1);
        System.out.println();

        int jrkLuger = 1;
        for (Koht koht :
                kohaList) {
            System.out.println(jrkLuger++ + ") " + koht);
        }

        valik(kohaList);

    }

    // Kuna lause ja paus kordusid tihti, lõin selleks eraldi meetodi.
    public static void väljastaPausiga(String lause, int sekundeid) throws Exception {
        int millisekundid = sekundeid * 1000; // Annab sekundile vastava suuruse
        System.out.println(lause);
        if (lause.isEmpty()) {
            BufferedReader jätkaLugemist = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("...");
            jätkaLugemist.read();
        } else
            Thread.sleep(millisekundid);
    }

    public static void valik(List<Koht> kohaList) {

        Scanner valikuNr = new Scanner(System.in);
        System.out.print("Palun sisesta number ja vajuta \"Enter\": ");
        int sisestatudNumber = valikuNr.nextInt();

        switch (sisestatudNumber) {
            case 1 -> System.out.println("Sinu valik: 1. " + kohaList.get(0));
            case 2 -> System.out.println("Sinu valik: 2. " + kohaList.get(1));
            case 3 -> System.out.println("Sinu valik: 3. " + kohaList.get(2));
            case 4 -> System.out.println("Sinu valik: 4. " + kohaList.get(3));
            case 5 -> System.out.println("Sinu valik: 5. " + kohaList.get(4));
            default -> {
                System.out.println("Vahemikus 1-5, palun.");
                valik(kohaList);
            }
        }
    }
}
