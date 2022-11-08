import java.util.Locale;

public class Main {
    public static void main(String[] args){
        float janeiro = Float.parseFloat(args[0]);
        float fevereiro = Float.parseFloat(args[1]);
        float marco = Float.parseFloat(args[2]);
        System.out.printf("Gasto total do trimestre: R$%.2f.", janeiro + fevereiro + marco);
    }
}