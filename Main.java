import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        AgendaDeContatos agenda = new AgendaDeContatos();
        Boolean continuar = true;
        Scanner entrada = new Scanner(System.in);
        Integer opcao;

        ConsoleColors.whiteBoldBrightPrint("Bem vindo à sua Agenda de Contatos!");
        while (continuar){
            ConsoleColors.whiteBoldBrightPrint("Qual operação deseja realizar?");
            listarOpcoes();
            opcao = escolherOpcao(entrada);
            switch (opcao){
                case 1 -> agenda.adicionarContato(entrada);
                case 2 -> agenda.listarContatos();
                case 3 -> agenda.buscarContato(entrada);
                case 4 -> agenda.removerContato(entrada);
                case 5 -> agenda.removerTodos();
                case 6 -> agenda.adicionarTelefone(entrada);
                case 7 -> agenda.adicionarEndereco(entrada);
                case 8 -> agenda.removerTelefone(entrada);
                case 9 -> agenda.removerEndereco(entrada);
                case 10 -> agenda.informacoesDe(entrada);
                case 11 -> agenda.telefonesDe(entrada);
                case 12 -> agenda.enderecosDe(entrada);
                case 13 -> agenda.umTelefoneDe(entrada);
                case 14 -> agenda.umEnderecoDe(entrada);
                case 15 -> agenda.exportarContatos(entrada);
                case 16 -> UtilDatabase.createInventory(entrada);
            }
            ConsoleColors.whiteBoldBrightPrint("Deseja realizar mais operações? S/N");
            continuar = desejaContinuar(entrada);
        }
        entrada.close();
        ConsoleColors.whiteBoldBrightPrint("Volte sempre.");
    }
    static void listarOpcoes(){
        ConsoleColors.whiteBoldBrightPrint("1. Adicionar um contato e seus dados;\n" +
                "2. Listar todos os contatos da agenda;\n" +
                "3. Buscar um contato de acordo com uma palavra chave (Utilize os dados de nome, sobrenome para realizar a busca)\n" +
                "4. Remover um contato da agenda;\n" +
                "5. Remover todos os contatos da agenda;\n" +
                "6. Adicionar um telefone a um contato;\n" +
                "7. Adicionar um endereço a um contato;\n" +
                "8. Remover um telefone de um contato da agenda;\n" +
                "9. Remover um endereço de um contato da agenda;\n" +
                "10. Exibir todas as informações de um contato da agenda;\n" +
                "11. Listar todos os telefones de um contato da agenda;\n" +
                "12. Listar todos os endereços de um contato da agenda;\n" +
                "13. Exibir todas as informações de um telefone de um contato da agenda;\n" +
                "14. Exibir todas as informações de um endereço de um contato da agenda;\n"+
                "15. Exportar os contatos para csv; \n" +
                "16. Importar contatos de um arquivo csv");
    }
    static Integer escolherOpcao(Scanner entrada){
        Integer opcao = entrada.nextInt();
        entrada.nextLine();
        return opcao;
    }
    static Boolean desejaContinuar(Scanner entrada){
        Character resposta = 'a';
        for (;;){
            resposta = entrada.nextLine().toLowerCase().charAt(0);
            if (resposta.equals('s'))
                return true;
            else if (resposta.equals('n'))
                return false;
            ConsoleColors.whiteBoldBrightPrint("Não entendi. S/N? ");
        }
    }
}