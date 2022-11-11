public class Ex1 {
    public static void main(String[] args){
        String frase = "Janeiro: 1543, Fevereiro: 1222, Março: 1234";
        String[] linhas = frase.split(", ");
        Integer soma = 0;
        for (String linha : linhas) {
            System.out.println(linha + "\n");
            soma += Integer.parseInt(linha.replaceAll("[^0-9]", ""));
        }
        System.out.println("Total: " + soma);
    }
}
