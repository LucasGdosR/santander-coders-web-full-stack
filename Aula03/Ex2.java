import java.util.Scanner;

public class Ex2 {
    public static void main(String[] args){
        System.out.println("Digite sua idade: ");
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            sc.next();
        }
        Integer idade = sc.nextInt();
        sc.close();
        System.out.println((idade < 16) ? "Sem direito a votar" :
                (idade < 18) ? "Voto facultativo" :
                        (idade < 71) ? "Voto obrigatório" : "Voto facultativo");
    }
}
