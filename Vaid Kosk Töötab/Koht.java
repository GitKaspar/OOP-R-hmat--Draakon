import java.util.Scanner;

public class Koht {
    private String nimi;
    boolean kasKülastatud;
    private String loetavFail;
    private Eesmärk ese;
    private boolean kasÕnnestus;

    public Koht(String nimi, boolean kasKülastatud, String loetavFail,Eesmärk ese) {
        this.nimi = nimi;
        this.kasKülastatud = kasKülastatud;
        this.loetavFail = loetavFail;
        this.ese = ese;
    }

    @Override
    public String toString() {
        if (kasKülastatud)
            return nimi + ". Oled siin käinud.";
        else
            return nimi + ". Sa ei ole siin käinud.";
    }

    public String getLoetavFail() {
        return loetavFail;
    }

    public void setKasKülastatud(boolean kasKülastatud) {
        this.kasKülastatud = kasKülastatud;
    }

    public void koskValiKoht(int õigeNr){
        int võimalusi = 2;

        while (võimalusi > 0 && !kasÕnnestus) {
            System.out.println("Sinu valik: ");
            Scanner sisendKlaverilt = new Scanner(System.in);
            int valik = sisendKlaverilt.nextInt();

            String õige1 = "Kae! Oligi seal! Haarad temast kinni";
            String õige2 = "Kae! Oligi seal! Haarad temast kinni ja tuled tiigist välja: räpane ja väsinud, aga võidukas.";
            String õige3 = "Kae! Oligi seal! Haarad temast kinni ja tuled tagasi kaldale.";
            String vale1 = "Vaatad sinna, vaatad tänna. Katsud vett, aga ei näe elukast märkigi.";
            String vale2 = "Ei leidnud. Nüüd oled räpane ja väsinud.";
            String vale3 = "Memm rääkis kunagi, et kose all on saladused. Seekord mitte.";
            String vihje1 = "Vanatoi ütles kunagi, et neile meeldib käia veepinnal sääsevastseid degusteerimas.";
            String vihje2 = "Valgust pidid teised kartma. Kus on siin kõige pimedam?";
            String vihje3 = "Kas Memm mitte ei öelnud kunagi, et meeldib loomale liikuv vesi?";

            String[] valed = {vale1, vale2, vale3};
            String[] õiged = {õige1, õige2, õige3};

            // Kuidas vale ja õige õige numbriga siduda?
            
            if (valik <= 3 && valik >= 1) {
                if (valik == õigeNr) {
                    System.out.println(õiged[valik - 1]);
                    kasÕnnestus = true;}
                else if(õigeNr == 1){
                    võimalusi--;
                    System.out.println(valed[valik-1]);
                    if(võimalusi == 1)
                        System.out.println(vihje1);
                }
                else if (õigeNr == 2) {
                    võimalusi--;
                    System.out.println(valed[valik-1]);
                    if(võimalusi == 1)
                        System.out.println(vihje2);
                } else if (õigeNr == 3) {
                    võimalusi--;
                    System.out.println(valed[valik-1]);
                    if(võimalusi == 1)
                        System.out.println(vihje3);
                }
            } else {
                System.out.println("Valikuvariandid on 1, 2 ja 3.");
                koskValiKoht(õigeNr);
            }
        }
    }

    public void annaEse(Tegelane tegelane){
        if(kasÕnnestus) {
            tegelane.lisaRanitsasse(this.ese);
            System.out.println("\nVõtad kandekotist kaabitsa ja kraabid looma kõhu alt magusalõhnalist nõret.\n");
        }
    }

    public Eesmärk getEse() {
        return ese;
    }

    public void koskLõpeta() throws Exception{
        System.out.println("Sinu valik: ");
        Scanner sisendKlaverilt = new Scanner(System.in);
        int valik = sisendKlaverilt.nextInt();

        String valik1 = "Kiiresti kaob ta paari sabahoobiga vee alla.\n" +
                "Miski ütleb, et ta oleks sinu abita sama teed läinud.\n" +
                "Tagasi külla.\n";
        String valik2 = "Kostab vali karjatus. Verd on igal pool.\n" +
                "One hea tunne?\n" +
                "Tagasi külla.\n";
        String valik3 = "Vingerjas teine! Libises käest ja lipsas kohe vette tagasi.\n" +
                "Siin pole enam midagi teha.\n" +
                "Tagasi külla. \n";

        switch (valik){
            case 1: {ProovimeUuesti.väljastaPausiga(valik1);
                break;}
            case 2: {ProovimeUuesti.väljastaPausiga(valik2);
                break;}
            case 3: {ProovimeUuesti.väljastaPausiga(valik3);
                break;}
            default: {
                System.out.println("Valikuvariandid on 1, 2 ja 3.");
                koskLõpeta();
            };
        }
    }

    public boolean isKasÕnnestus() {
        return kasÕnnestus;
    }
}

