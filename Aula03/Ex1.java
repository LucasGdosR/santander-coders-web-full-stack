import java.util.Scanner;

public class Ex1 {
    public static void main(String[] args){
        System.out.println("Digite sua idade: ");
        Scanner sc = new Scanner(System.in);
        while (!sc.hasNextInt()) {
            sc.next();
        }
        Integer idade = sc.nextInt();
        sc.close();
        if (idade < 16) System.out.println("Sem direito a votar");
        else if (idade < 18) System.out.println("Voto facultativo");
        else if (idade < 71) System.out.println("Voto obrigatório");
        else System.out.println("Voto facultativo");
    }
}
