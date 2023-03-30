import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Peaklass {
    public static void main(String[] args) throws Exception {
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
            Koht uusKoht = new Koht(tükeldatudRida[0], Boolean.parseBoolean(tükeldatudRida[1]), tükeldatudRida[2]);
            kohaList.add(uusKoht);
        }

        for (int i = 0; i < 5; ) {
            int jrkLuger = 1;
            for (Koht koht :
                    kohaList) {
                System.out.println(jrkLuger++ + ") " + koht);
            }
            int valik = Abi.valiKoht(kohaList);
            switch(valik){
                case 1: // Kosk
                    if(kohaList.get(0).isKasKülastatud()){
                        System.out.println("Siia ei saa enam naasta.");
                        break;}
                    else {
                        Abi.loeFailEtte("kosk_sj.txt");
                         boolean edu = Abi.kosk(Abi.juhuslikArv(3), kohaList.get(0));
                         if (edu){
                             kohaList.get(0).setKasÕnnestus(true);
                             kohaList.get(0).setKasKülastatud(true);
                             i++;
                             Abi.loeFailEtte("kosk_edu.txt");
                             Abi.koskLõpeta();
                             break;
                         }
                         else{
                             kohaList.get(0).setKasKülastatud(true);
                             i++;
                             Abi.loeFailEtte("kosk_ebaõnn.txt");
                             break;
                         }
                        }
                case 2: // Altar
                    if(kohaList.get(1).isKasKülastatud()){
                        System.out.println("Siia ei saa enam naasta.");
                        break;
                    }
                    else {
                        Abi.altar(Abi.juhuslikArv(2),kohaList.get(1));
                        i++;
                        break;
                    }
                case 3:
                    if(kohaList.get(2).isKasKülastatud()){
                        System.out.println("Siia ei saa enam naasta.");
                        break;
                    }
                    else {
                        int jürkaValik1 = Abi.dialoogiHaru(3,"jürka.txt");
                        switch(jürkaValik1) {
                            case 1:
                                Abi.dialoogiHaru(2, "jürka1.txt");
                                kohaList.get(2).setKasKülastatud(true);
                                i++;
                                break;
                            case 2:
                                Abi.dialoogiHaru(2, "jürka2.txt");
                                kohaList.get(2).setKasKülastatud(true);
                                i++;
                                break;
                            case 3:
                                int jürkaValik2 = Abi.dialoogiHaru(3, "jürka3.txt");
                                switch (jürkaValik2) {
                                    case 1:
                                        kohaList.get(2).setKasKülastatud(true);
                                        i++;
                                        break;
                                    case 2:
                                        kohaList.get(2).setKasKülastatud(true);
                                        kohaList.get(2).setKasÕnnestus(true);
                                        i++;
                                        break;
                                    case 3:
                                        kohaList.get(2).setKasKülastatud(true);
                                        i++;
                                        break;
                                }
                            default:
                                break;
                        }
                        break;
                    }// Jürka. PROBLEEM! Läheb edasi neljanda ülesande peale (Fallthrough?)
                case 4:
                    if(kohaList.get(3).isKasKülastatud()){
                        System.out.println("Siia ei saa enam naasta.");
                        break;
                    }
                    else {
                        int adlinValik1 = Abi.dialoogiHaru(3, "adlin.txt");
                        switch (adlinValik1){
                            case 1: Abi.dialoogiHaru(3, "adlin1.txt");
                            kohaList.get(3).setKasKülastatud(true);
                            i++;
                            break;
                            case 2:Abi.dialoogiHaru(2, "adlin2.txt");
                            kohaList.get(3).setKasKülastatud(true);
                            i++;
                            break;
                            case 3: Abi.mõistatused(kohaList.get(3));
                            i++;
                            break;
                            default:
                                System.out.println("Kui oled siin, on kuskil viga.");
                        }
                        break;
                    }
                    // Viirukimeistri hütt
                case 5:
                    if(kohaList.get(4).isKasKülastatud()){
                        System.out.println("Siia ei saa enam naasta.");
                        break;
                    }
                    else {
                        int šamaaniValik1 = Abi.dialoogiHaru(3, "aklupati_šamaan.txt");
                        switch (šamaaniValik1){
                            case 1:
                                int šamaaniValik2 = Abi.dialoogiHaru(2, "aklupati_šamaan1.txt");
                                if(šamaaniValik2 == 1)
                                        kohaList.get(4).setKasÕnnestus(true);
                                kohaList.get(4).setKasKülastatud(true);
                                i++;
                                break;
                            case 2:
                                i++;
                                kohaList.get(4).setKasKülastatud(true);
                            break;
                            case 3:i++;
                                kohaList.get(4).setKasKülastatud(true);
                            break;
                        }
                    }// Aplukati šaamani koguduseplats
                default:
                    break;
            }
        }
   //     System.out.println("SAID TSÜKLIST VÄLJA!");

        int õnnestumisi = 0;

        Abi.loeFailEtte("riitus_sj.txt");
        System.out.println();
        for (Koht koht:
             kohaList) {
            koht.loeTulemused();
            koht.väljastaTulemus();
            System.out.println();
            if (koht.isKasÕnnestus())
                õnnestumisi++;
        }
        Abi.loeFailEtte("ootus.txt");

        if(õnnestumisi > 2)
            Abi.loeFailEtte("oledEdukas.txt");
        else
            Abi.loeFailEtte("kukudLäbi.txt");
    }
}
