public class HaruTest {
    public static void main(String[] args) throws Exception{
        int adlinValik1 = DialoogiHaru.dialoogiHaru(3, "adlin_test.txt");
        switch (adlinValik1) {
            case 1 -> DialoogiHaru.dialoogiHaru(3, "adlin_test1.txt");
            case 2 -> DialoogiHaru.dialoogiHaru(2, "adlin_test2.txt");
            case 3 -> System.out.println("See on ülesande tuum, aga hetkel välja arendamata. :(");
        }

    }
}
