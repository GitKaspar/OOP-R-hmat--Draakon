import java.util.ArrayList;
import java.util.List;

public class Tegelane {
    private List<Eesmärk> tarvilik = new ArrayList<>();

    public Tegelane(){

    }
    public void lisaRanitsasse(Eesmärk ese){
        tarvilik.add(ese);
    }


}
