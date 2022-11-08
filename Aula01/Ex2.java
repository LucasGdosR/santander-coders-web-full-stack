import java.util.Locale;

public class Ex2 {
    public static void main(String[] args) {
        int idade = 31;
        float numDaSorte = (float) idade + .023f;
        System.out.printf(Locale.ITALIAN,"O número da sorte é: %.3f.", numDaSorte);
    }
}
