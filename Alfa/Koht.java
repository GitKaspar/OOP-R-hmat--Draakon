public class Koht {
        private String nimi;
        private boolean kasKülastatud;

    public Koht(String nimi, boolean kasKülastatud) {
        this.nimi = nimi;
        this.kasKülastatud = kasKülastatud;
    }

    @Override
        public String toString() {
            if (kasKülastatud)
                return nimi + ". Oled siin käinud.";
            else
                return nimi + ". Sa ei ole siin käinud.";
        }
    }
}
