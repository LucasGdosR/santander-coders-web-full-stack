public class Testes {
    public static void main(String[] args) {
        Teste[] testes = new Teste[9];
        testes[0] = new Teste(0, "Sem direito a votar");
        testes[1] = new Teste(1, "Sem direito a votar");
        testes[2] = new Teste(15, "Sem direito a votar");
        testes[3] = new Teste(16, "Voto facultativo");
        testes[4] = new Teste(17, "Voto facultativo");
        testes[5] = new Teste(71, "Voto facultativo");
        testes[6] = new Teste(18, "Voto obrigatório");
        testes[7] = new Teste(50, "Voto obrigatório");
        testes[8] = new Teste(70, "Voto obrigatório");

        String[] falhas = new String[9];
        int falhou = 0;

        for (int i = 0; i < testes.length; i++) {
            String podeVotar = podeVotar(testes[i].getIdade());
            if (podeVotar.equals(testes[i].getResultado())) {
                falhas[i] = "";
//                System.out.printf("Teste" + (i + 1) + " Sucesso");
            }
            else {
                falhas[i] = podeVotar;
//                System.out.printf("Teste" + (i + 1) + " Falha");
                falhou++;
            }
        }

        if (falhou == 0) System.out.println("Testes executados com sucesso");
        else {
            System.out.println("Os seguintes testes falharam:");
            for (int i = 0; i < falhas.length; i++) {
                if (!falhas[i].isEmpty()){
                    System.out.println("Teste " + (i + 1) + " - Resultado: \"" + falhas[i] + "\" - Correto: \"" + testes[i].getResultado() + '"');
                }
            }
        }
    }

    public static String podeVotar(int idade){
        return (idade < 16) ? "Sem direito a votar" :
               (idade < 18) ? "Voto facultativo" :
               (idade < 71) ? "Voto obrigatório" : "Voto facultativo";
    }
}
