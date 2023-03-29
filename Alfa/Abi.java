import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Abi {

    public static int dialoogiHaru(int valikuid, String sisendFail) throws Exception {
        Scanner loeValikuFail = new Scanner(new File(sisendFail));
        List<String> tutvustus = new ArrayList<>();
        List<String> lõpp = new ArrayList<>();
        List<String> valik1vastus = new ArrayList<>();
        List<String> valik2vastus = new ArrayList<>();
        List<String> valik3vastus = new ArrayList<>();
        while (loeValikuFail.hasNextLine()) {
            String rida = loeValikuFail.nextLine();
            if (!rida.isEmpty()) {
                String algus = rida.substring(0, 3);
                switch (algus) {
                    case "v1.":
                        valik1vastus.add(rida.substring(3));
                        break;
                    case "v2.":
                        valik2vastus.add(rida.substring(3));
                        break;
                    case "v3.":
                        valik3vastus.add(rida.substring(3));
                        break;
                    case "lõ.":
                        lõpp.add(rida.substring(3));
                        break;
                    default:
                        tutvustus.add(rida);
                }
            } else
                tutvustus.add("");
        }

        for (String rida :
                tutvustus) {
            väljastaPausiga(rida);
        }

        System.out.println("\nSinu valik: ");
        Scanner loeValik = new Scanner(System.in);
        int valik = loeValik.nextInt();

        if (valikuid == 2) {
            switch (valik) {
                case 1:
                    for (String rida :
                            valik1vastus) {
                        väljastaPausiga(rida);
                    }
                    break;
                case 2:
                    for (String rida :
                            valik2vastus) {
                        väljastaPausiga(rida);
                    }
                    break;
                default:
                    System.out.println("Valikuteks sobivad vaid 1 ja 2.");
                    dialoogiHaru(valikuid, sisendFail);
            }

        }

        if (valikuid == 3) {
            switch (valik) {
                case 1:
                    for (String rida :
                            valik1vastus) {
                        väljastaPausiga(rida);
                    }
                    break;
                case 2:
                    for (String rida :
                            valik2vastus) {
                        väljastaPausiga(rida);
                    }
                    break;
                case 3:
                    for (String rida :
                            valik3vastus) {
                        väljastaPausiga(rida);
                    }
                    break;
                default:
                    System.out.println("Valikuteks sobivad vaid 1, 2 ja 3.");
                    dialoogiHaru(valikuid, sisendFail);
            }
        }

        for (String rida :
                lõpp) {
            väljastaPausiga(rida);
        }

        return valik;
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

    public static void loeFailEtte(String failiNimi) throws Exception {
        Scanner lugeja = new Scanner(new File(failiNimi), "UTF-8");
        while (lugeja.hasNextLine()) {
            String rida = lugeja.nextLine();
            int reapikkus = rida.length();
            väljastaPausiga(rida);
        }
    }

    public static int juhuslikArv(int valikuid) {
        int min = 1;
        int max = valikuid; // Murekoht? +1 või mitte?
        int tulemus = (int) (min + Math.random() * valikuid);
        return tulemus;
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

    public static int valiKoht(List<Koht> kohaList) {

        System.out.print("Sinu valik: \n");
        Scanner valikuNr = new Scanner(System.in);
        int sisestatudNumber = valikuNr.nextInt();

        switch (sisestatudNumber) {
            case 1 -> System.out.println(kohaList.get(0));
            case 2 -> System.out.println(kohaList.get(1));
            case 3 -> System.out.println(kohaList.get(2));
            case 4 -> System.out.println(kohaList.get(3));
            case 5 -> System.out.println(kohaList.get(4));
            default -> {
                System.out.println("Vahemikus 1-5, palun.");
                valiKoht(kohaList);
            }
        } return sisestatudNumber;
    }
}
