import java.util.Scanner;

public class Ex3 {
    public static void main(String[] args){
        String[] bebidas = new String[6];
        bebidas[0] = "1 - Coca-Cola - R$ 5,00";
        bebidas[1] = "2 - Coca-Cola 0 R$ 4,50";
        bebidas[2] = "3 - Pepsi - R$ 4,40";
        bebidas[3] = "4 - Guaraná Antarctica - R$ 3,50";
        bebidas[4] = "5 - Fanta Laranja - R$ 4,23";
        bebidas[5] = "6 - Água - R$ 2,50";
        System.out.println("Escolha uma opção de bebida (número):");
        for (String bebida : bebidas)
            System.out.println(bebida);
        Scanner sc = new Scanner(System.in);
        Integer opcao = 0;
        while (opcao < 1 || opcao > 6) {
            while (!sc.hasNextInt())
                sc.next();
            opcao = sc.nextInt();
        }
        sc.close();
        switch (opcao) {
            case 1 -> System.out.println(bebidas[0]);
            case 2 -> System.out.println(bebidas[1]);
            case 3 -> System.out.println(bebidas[2]);
            case 4 -> System.out.println(bebidas[3]);
            case 5 -> System.out.println(bebidas[4]);
            case 6 -> System.out.println(bebidas[5]);
        }
    }
}
