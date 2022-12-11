import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AgendaDeContatos {
    private List<Contato> contatos;
    AgendaDeContatos(){
        this.contatos = new ArrayList<>();
    }
    public void adicionarContato (Scanner entrada){
        System.out.println("Informe o nome para o contato: ");
        String nome = entrada.nextLine();
        System.out.println("Informe o sobrenome para o contato: ");
        String sobrenome = entrada.nextLine();
        if (contatos.contains(new Contato(nome,sobrenome,null,null))){
            System.out.println("Contato já existente.");
            return;
        }
        System.out.println("Deseja incluir um telefone? S/N ");
        Boolean incluirTelefone = simOuNao(entrada);
        System.out.println("Deseja incluir um endereço? S/N ");
        Boolean incluirEndereco = simOuNao(entrada);
        if (!incluirTelefone && !incluirEndereco)
            adicionarContatoSimples(nome, sobrenome);
        else if (incluirTelefone && incluirEndereco)
            adicionarContatoCompleto(nome, sobrenome, entrada);
        else if (incluirTelefone)
            adicionarContatoComTelefone(nome, sobrenome, entrada);
        else adicionarContatoComEndereco(nome, sobrenome, entrada);
    }
    private void adicionarContatoSimples(String nome, String sobrenome){
        contatos.add(new Contato(nome, sobrenome, new ArrayList<Telefone>(), new ArrayList<Endereco>()));
        System.out.println("Adicionado contato " + nome + " " + sobrenome + " sem telefones ou endereços.");
    }
    private void adicionarContatoComTelefone(String nome, String sobrenome, Scanner entrada){
        List<Telefone> telefones = incluirTelefones(entrada);
        contatos.add(new Contato(nome, sobrenome, telefones, new ArrayList<Endereco>()));
        System.out.println("Adicionado contato " + nome + " " + sobrenome + " com os telefones: ");
        telefones.forEach(System.out::println);
    }
    private void adicionarContatoComEndereco(String nome, String sobrenome, Scanner entrada){
        List<Endereco> enderecos = incluirEnderecos(entrada);
        contatos.add(new Contato(nome, sobrenome, new ArrayList<Telefone>(), enderecos));
        System.out.println("Adicionado contato " + nome + " " + sobrenome + " com os endereços: ");
        enderecos.forEach(System.out::println);
    }
    private void adicionarContatoCompleto(String nome, String sobrenome, Scanner entrada){
        List<Telefone> telefones = incluirTelefones(entrada);
        List<Endereco> enderecos = incluirEnderecos(entrada);
        contatos.add(new Contato(nome, sobrenome, telefones, enderecos));
        System.out.println("Adicionado contato " + nome + " " + sobrenome + " com os telefones e endereços: ");
        telefones.forEach(System.out::println);
        enderecos.forEach(System.out::println);
    }
    public void listarContatos(){
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\n==================== Lista de contatos ====================");
        for (int i = 0; i < contatos.size(); i++)
            System.out.printf("%d. \t %-12s\n", (i+1),contatos.get(i));
        System.out.println("===========================================================\n" + ConsoleColors.RESET);
    }

    public void buscarContato(Scanner entrada){
        System.out.println("Insira a palavra-chave: ");
        String nomeOuSobrenome = entrada.nextLine().toLowerCase();
        System.out.println("Contatos que contêm " + nomeOuSobrenome + ":");
        contatos.stream()
                .filter(contato -> contato.getNome().toLowerCase().contains(nomeOuSobrenome) || contato.getSobrenome().toLowerCase().contains(nomeOuSobrenome))
                .forEach(contato -> System.out.println((contatos.indexOf(contato) + 1) + " - " + contato));
    }
    public void removerContato(Scanner entrada){
        int indice = pegarIndice(entrada);
        if (verificarIndice(indice))
        {
            contatos.remove(indice);
        }
    }

    public void removerTodos(){
        contatos.clear();
    }
    public void adicionarTelefone(Scanner entrada){
        Integer indice = pegarIndice(entrada);
        if (verificarIndice(indice)) {
            Telefone telefone = incluirTelefone(entrada);
            if (contatos.get(indice).getTelefones().stream().anyMatch(t -> t.equals(telefone))) {
                System.out.println("Telefone já existente nesse contato.");
                return;
            }
            contatos.get(indice).getTelefones().add(telefone);
        }
    }
    public void adicionarEndereco(Scanner entrada){
        Integer indice = pegarIndice(entrada);
        if (verificarIndice(indice)) {
            Endereco endereco = incluirEndereco(entrada);
            if (contatos.get(indice).getEnderecos().stream().anyMatch(t -> t.equals(endereco))) {
                System.out.println("Endereço já existente nesse contato.");
                return;
            }
            contatos.get(indice).getEnderecos().add(endereco);
        }
    }

    public void removerTelefone(Scanner entrada){
        Integer indiceDoContato = pegarIndice(entrada);
        if (verificarIndice(indiceDoContato)) {
            List<Telefone> telefones = contatos.get(indiceDoContato).getTelefones();
            imprimirListaTelefones(telefones);

            System.out.println("Informe o índice do telefone: ");
            int indiceDoTelefone = entrada.nextInt() - 1;
            entrada.nextLine();
            contatos.get(indiceDoContato).getTelefones().remove(indiceDoTelefone);
        }
    }
    public void removerEndereco(Scanner entrada){
        Integer indiceDoContato = pegarIndice(entrada);
        if (verificarIndice(indiceDoContato)) {
            List<Endereco> enderecos = contatos.get(indiceDoContato).getEnderecos();
            imprimirListaEnderecos(enderecos);

            System.out.println("Informe o índice do endereco: ");
            int indiceDoEndereco = entrada.nextInt() - 1;
            entrada.nextLine();
            contatos.get(indiceDoContato).getEnderecos().remove(indiceDoEndereco);
        }
    }
    public void informacoesDe(Scanner entrada){
        Integer indice = pegarIndice(entrada);

        if (verificarIndice(indice)) {

            List<Telefone> telefones = contatos.get(indice).getTelefones();
            List<Endereco> enderecos = contatos.get(indice).getEnderecos();
            System.out.println(contatos.get(indice));

            imprimirListaTelefones(telefones);
            imprimirListaEnderecos(enderecos);
        }
    }
    public void telefonesDe(Scanner entrada) {
        Integer indice = pegarIndice(entrada);
        if (verificarIndice(indice)) {
            List<Telefone> telefones = contatos.get(indice).getTelefones();
            imprimirListaTelefones(telefones);
        }
    }

    public void imprimirListaTelefones(List<Telefone> telefones) {
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\n==================== Lista de telefones ====================");
        for (int i = 0; i < telefones.size(); i++)
            System.out.printf("%d. \t %-12s\n",(i+1),telefones.get(i));
        System.out.println("============================================================" + ConsoleColors.RESET);
    }
    public void enderecosDe(Scanner entrada){
        Integer indice = pegarIndice(entrada);

        if (verificarIndice(indice)) {
            List<Endereco> enderecos = contatos.get(indice).getEnderecos();
            imprimirListaEnderecos(enderecos);
        }
    }

    public void imprimirListaEnderecos(List<Endereco> enderecos) {
        System.out.println(ConsoleColors.WHITE_BOLD_BRIGHT + "\n==================== Lista de endereços ====================");
        for (int i = 0; i < enderecos.size(); i++)
            System.out.printf("%d. \t %-12s\n",(i+1),enderecos.get(i));
        System.out.println("============================================================" + ConsoleColors.RESET);
    }
    public void umTelefoneDe(Scanner entrada){
        Integer indiceDoContato = pegarIndice(entrada);
        if (verificarIndice(indiceDoContato)) {

            List<Telefone> telefones = contatos.get(indiceDoContato).getTelefones();
            imprimirListaTelefones(telefones);

            System.out.println("Informe o índice do telefone: ");
            Integer indiceDoTelefone = entrada.nextInt() - 1;
            entrada.nextLine();
            System.out.println(contatos.get(indiceDoContato).getTelefones().get(indiceDoTelefone));
        }
    }

    public void umEnderecoDe(Scanner entrada){
        Integer indiceDoContato = pegarIndice(entrada);
        if (verificarIndice(indiceDoContato)) {
            List<Endereco> enderecos = contatos.get(indiceDoContato).getEnderecos();
            imprimirListaEnderecos(enderecos);

            System.out.println("Informe o índice do endereço: ");
            Integer indiceDoEndereco = entrada.nextInt() - 1;
            entrada.nextLine();
            System.out.println(contatos.get(indiceDoContato).getEnderecos().get(indiceDoEndereco));
        }
    }
    private Boolean simOuNao(Scanner entrada){
        Character resposta = 'a';
        for (;;) {
            resposta = entrada.nextLine().toLowerCase().charAt(0);
            if (resposta.equals('s'))
                return true;
            else if (resposta.equals('n'))
                return false;
            System.out.println("Não entendi. S/N? ");
        }
    }
    private List<Telefone> incluirTelefones(Scanner entrada) {
        Boolean continuar = true;
        List<Telefone> telefones = new ArrayList<>();
        while (continuar){
            telefones.add(incluirTelefone(entrada));
            System.out.println("Deseja continuar? ");
            continuar = simOuNao(entrada);
        }
        return telefones;
    }
    private Telefone incluirTelefone(Scanner entrada){
        System.out.println("Insira o DDD: ");
        String ddd = entrada.nextLine();
        System.out.println("Insira o número: ");
        String numero = entrada.nextLine();
        return new Telefone(ddd, numero);
    }
    private List<Endereco> incluirEnderecos(Scanner entrada) {
        Boolean continuar = true;
        List<Endereco> enderecos = new ArrayList<>();
        while (continuar){
            enderecos.add(incluirEndereco(entrada));
            System.out.println("Deseja continuar? ");
            continuar = simOuNao(entrada);
        }
        return enderecos;
    }
    private Endereco incluirEndereco(Scanner entrada){
        System.out.println("Insira o CEP: ");
        String cep = entrada.nextLine();
        System.out.println("Insira o logradouro: ");
        String logradouro = entrada.nextLine();
        System.out.println("Insira o número: ");
        String numero = entrada.nextLine();
        System.out.println("Insira a cidade: ");
        String cidade = entrada.nextLine();
        System.out.println("Insira o estado: ");
        String estado = entrada.nextLine();
        return new Endereco(cep, logradouro, numero, cidade, estado);
    }
    private Integer pegarIndice(Scanner entrada){
        listarContatos();
        System.out.println("Informe o índice do contato: ");
        try {
            Integer indice = entrada.nextInt() - 1;
            return indice;
        }
        catch (InputMismatchException e){
            System.out.println("Erro: caractere inválido, tente novamente: ");
            return (pegarIndice(entrada));
        }
        finally {
            entrada.nextLine();
        }
    }
    private boolean verificarIndice(Integer index){
        try{
           contatos.get(index);
           return(true);
        } catch(IndexOutOfBoundsException e){
            System.out.println("Índice inválido, finalizando operação!");
        }
            return(false);
    };
}