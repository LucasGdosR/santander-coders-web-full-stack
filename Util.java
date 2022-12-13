import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Util {


    public static String criaTxt(List<Contato> contatos) {
        StringBuilder sb = new StringBuilder();
        sb.append("Contatos:");
        sb.append(";");
        for(int i = 0; i < contatos.size(); i++) {
            List<Telefone> telefones = contatos.get(i).getTelefones();
            List<Endereco> enderecos = contatos.get(i).getEnderecos();
            sb.append(contatos.get(i).getNome());
            sb.append(";");
            sb.append(contatos.get(i).getSobrenome());
            sb.append(";");
            sb.append("Numero(s) de Telefone: ");
            sb.append(";");

            for(int j = 0; j < telefones.size(); j++) {
                sb.append(telefones.get(j).getDdd());
                sb.append(";");
                sb.append(telefones.get(j).getNumero());
                sb.append(";");
            }
            for(int k = 0; k < enderecos.size(); k++) {
                sb.append("Endereços cadastrados: ");
                sb.append(";");
                sb.append(enderecos.get(k).getCep());
                sb.append(";");
                sb.append(enderecos.get(k).getLogradouro());
                sb.append(";");
                sb.append(enderecos.get(k).getNumero());
                sb.append(";");
                sb.append(enderecos.get(k).getCidade());
                sb.append(";");
                sb.append(enderecos.get(k).getEstado());
            }
        }
        return sb.toString();
    }


    public static void geraTxt(String conteudo, String nomeArquivo) {
        File dir = new File("C:\\Users\\letic\\Documents\\Ada\\resources");
        dir.mkdirs();

        Path path = Paths.get(dir.getAbsolutePath(), nomeArquivo);

        try {
            Files.write(path, conteudo.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static List<String> leArquivo(String nomeArquivo) {
        Path filePath = Paths.get("C:\\Users\\letic\\Documents\\Ada\\resources\\Contatos-da-Agenda.txt");
        try {
            List<String> lines = Files.readAllLines(filePath);
            return lines;
        } catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void leContatosTxt() {
        List<String> conteudo = leArquivo("Contatos-da-Agenda.txt");
        for(String linha: conteudo) {

            List<Endereco> enderecos = new ArrayList<>();
            List<Telefone> telefones = new ArrayList<>();
            Contato contato = new Contato();
            Endereco endereco = new Endereco();
            Telefone telefone = new Telefone();

            String campos[] = linha.split(";");
            contato.setNome(campos[1]);
            contato.setSobrenome(campos[2]);


            telefone.setDdd(campos[4]);
            telefone.setNumero(campos[5]);
            telefones.add(telefone);

            endereco.setCep(campos[7]);
            endereco.setLogradouro(campos[8]);
            endereco.setNumero(campos[9]);
            endereco.setCidade(campos[10]);
            endereco.setEstado(campos[11]);
            enderecos.add(endereco);

            contato.setEnderecos(enderecos);
            contato.setTelefones(telefones);

            AgendaDeContatos.contatos.add(contato);
        }
    }


}
