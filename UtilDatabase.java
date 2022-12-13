import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class UtilDatabase {

    public static void createDB(String conteudo,String nomeArquivo) throws IOException {
        File inventory = new File((nomeArquivo+".csv"));

        FileWriter out = new FileWriter(inventory);
        if(!inventory.exists()){
            out.append("Name/Surname/Telefone/Adress\n");
        }
        out.append(conteudo);
        out.close();
        System.out.println("Conteudo exportado");
    }


    public static String criaTxt(List<Contato> contatos) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < contatos.size(); i++) {
            List<Telefone> telefones = contatos.get(i).getTelefones();
            List<Endereco> enderecos = contatos.get(i).getEnderecos();
            sb.append(contatos.get(i).getNome());
            sb.append(",");
            sb.append(contatos.get(i).getSobrenome());
            sb.append(",");


            for(int j = 0; j < telefones.size(); j++) {
                sb.append(telefones.get(j).getDdi());
                sb.append("/");
                sb.append(telefones.get(j).getDdd());
                sb.append("/");
                sb.append(telefones.get(j).getNumero());
                sb.append(";");
            }

            if (telefones.size()==0){
                sb.append("SemTelefone");
            }

            sb.append(",");
            for(int k = 0; k < enderecos.size(); k++) {
                sb.append(enderecos.get(k).getCep());
                sb.append("/");
                sb.append(enderecos.get(k).getLogradouro());
                sb.append("/");
                sb.append(enderecos.get(k).getNumero());
                sb.append("/");
                sb.append(enderecos.get(k).getCidade());
                sb.append("/");
                sb.append(enderecos.get(k).getEstado());
                sb.append(";");
            }

            if (enderecos.size()==0){
                sb.append("SemEndereco");
            }

            sb.append("\n");
        }
        return sb.toString();
    }

    public static void createInventory(Scanner entrada ) {
        System.out.println("Digite o nome do arquivo");
        String nomeArquivo = entrada.nextLine();

        try{
            File inventory = new File((nomeArquivo+".csv"));

            if(!inventory.exists()){
                System.out.println("Nenhum produto cadastrado");
                return;
            }



            Scanner sc = new Scanner(inventory);

            int counter = 0;

            while (sc.hasNextLine()) {
                String[] contatoData = sc.nextLine().split(",");

                if (!contatoData[0].equalsIgnoreCase("name")) {
                    String nome = contatoData[0].trim();
                    String sobrenome = contatoData[1].trim();
                    String telefonesString = contatoData[2].trim();
                    String enderecosString = contatoData[3].trim();

                    if (telefonesString.equals("SemTelefone") && enderecosString.equals("SemEndereco")){
                        AgendaDeContatos.contatos.add(new Contato(nome, sobrenome, new ArrayList<Telefone>(), new ArrayList<Endereco>()));
                    } else if (telefonesString.equals("SemTelefone")) {
                        List<Endereco> enderecos = new ArrayList<Endereco>();
                        String[] enderecosListaString = enderecosString.split(";");
                        for (int indice=0; indice < enderecosListaString.length; indice++){
                            String[] enderecoString = enderecosListaString[indice].split("/");
                            enderecos.add(new Endereco(enderecoString[0].trim(), enderecoString[1].trim(), enderecoString[2].trim(), enderecoString[3].trim(), enderecoString[4].trim()));
                        }
                        AgendaDeContatos.contatos.add(new Contato(nome, sobrenome, new ArrayList<Telefone>(), enderecos));
                    } else if (enderecosString.equals("SemEndereco")) {
                        List<Telefone> telefones = new ArrayList<Telefone>();
                        String[] telefonesListaString = telefonesString.split(";");
                        for (int indice=0; indice < telefonesListaString.length; indice++){
                            String[] telefoneString = telefonesListaString[indice].split("/");
                            telefones.add(new Telefone(telefoneString[0].trim(), telefoneString[1].trim(), telefoneString[2].trim()));
                        }
                        AgendaDeContatos.contatos.add(new Contato(nome, sobrenome, telefones, new ArrayList<Endereco>()));
                    } else {
                        List<Endereco> enderecos = new ArrayList<Endereco>();
                        String[] enderecosListaString = enderecosString.split(";");
                        for (int indice=0; indice < enderecosListaString.length; indice++){
                            String[] enderecoString = enderecosListaString[indice].split("/");
                            enderecos.add(new Endereco(enderecoString[0].trim(), enderecoString[1].trim(), enderecoString[2].trim(), enderecoString[3].trim(), enderecoString[4].trim()));
                        }
                        List<Telefone> telefones = new ArrayList<Telefone>();
                        String[] telefonesListaString = telefonesString.split(";");
                        for (int indice=0; indice < telefonesListaString.length; indice++){
                            String[] telefoneString = telefonesListaString[indice].split("/");
                            telefones.add(new Telefone(telefoneString[0].trim(), telefoneString[1].trim(), telefoneString[2].trim()));
                        }
                        AgendaDeContatos.contatos.add(new Contato(nome, sobrenome, telefones, enderecos));
                    }
                    counter++;
                }
            }
            sc.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

}
