import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Peaklass {
    public static void main(String[] args) throws Exception{
        Abi.loeFailEtte("algus.txt");

        System.out.println("\n1. Alusta");
        System.out.println("2. Lõpeta\n");

        Abi.kaheneValik("šamaani tutvustus.txt");

        System.out.println("Võid ülesandega alustada sealt, kust soovid.\n");

        Scanner kohad = new Scanner(new File("kohad.txt"), "UTF-8");
        List<Koht> kohaList = new ArrayList<>();
        while (kohad.hasNextLine()) {
            String rida = kohad.nextLine();
            String[] tükeldatudRida = rida.split(";");
            Koht uusKoht = new Koht(tükeldatudRida[0], Boolean.parseBoolean(tükeldatudRida[1]);
            kohaList.add(uusKoht);
        }
        for (int i = 0; i < 5;) {


        }

        int adlinValik1 = DialoogiHaru.dialoogiHaru(3, "adlin_test.txt");
        switch (adlinValik1) {
            case 1 -> DialoogiHaru.dialoogiHaru(3, "adlin_test1.txt");
            case 2 -> DialoogiHaru.dialoogiHaru(2, "adlin_test2.txt");
            case 3 -> System.out.println("See on ülesande tuum, aga hetkel välja arendamata. :(");
        }

    }
}
}