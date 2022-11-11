public class Ex2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        String nomeCompleto = sc.nextLine();
        System.out.println("!" + nomeCompleto.replaceAll(" ", " !"));
    }
}
