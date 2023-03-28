import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DialoogiHaru {

    public static int dialoogiHaru(int valikuid, String sisendFail) throws Exception{
        Scanner loeValikuFail = new Scanner(new File(sisendFail));
        List<String> tutvustus = new ArrayList<>();
        List<String> lõpp = new ArrayList<>();
        List<String> valik1vastus = new ArrayList<>();
        List<String> valik2vastus = new ArrayList<>();
        List<String> valik3vastus = new ArrayList<>();
        while (loeValikuFail.hasNextLine()){
            String rida = loeValikuFail.nextLine();
            if(!rida.isEmpty()) {
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
            }
            else
                tutvustus.add("");
        }

        for (String rida:
             tutvustus) {
            väljastaPausiga(rida);
        }

        System.out.println("\nSinu valik: ");
        Scanner loeValik = new Scanner(System.in);
        int valik = loeValik.nextInt();

        if (valikuid == 2){
            switch (valik){
                case 1:
                    for (String rida:
                            valik1vastus) {
                        väljastaPausiga(rida);
                    }
                    break;
                case 2:
                    for (String rida:
                            valik2vastus) {
                        väljastaPausiga(rida);
                    }
                    break;
                default:
                    System.out.println("Valikuteks sobivad vaid 1 ja 2.");
                    dialoogiHaru(valikuid, sisendFail);
            }

        }

        if (valikuid == 3){
            switch (valik){
                case 1:
                    for (String rida:
                            valik1vastus) {
                        väljastaPausiga(rida);
                    }
                    break;
                case 2:
                    for (String rida:
                            valik2vastus) {
                        väljastaPausiga(rida);
                    }
                    break;
                case 3:
                    for (String rida:
                            valik3vastus) {
                        väljastaPausiga(rida);
                    }
                    break;
                default:
                    System.out.println("Valikuteks sobivad vaid 1, 2 ja 3.");
                    dialoogiHaru(valikuid, sisendFail);
            }
        }

        for (String rida:
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
}
