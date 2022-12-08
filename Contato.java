import java.util.List;
import java.util.Objects;

public class Contato {
    private String nome;
    private String sobrenome;
    private List<Telefone> telefones;
    private List<Endereco> enderecos;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contato contato)) return false;
        return nome.equals(contato.nome) && sobrenome.equals(contato.sobrenome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, sobrenome);
    }

    protected Contato(String nome, String sobrenome, List<Telefone> telefones, List<Endereco> enderecos){
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.telefones = telefones;
        this.enderecos = enderecos;
    }
    protected String getNome() {
        return nome;
    }
    protected String getSobrenome() {
        return sobrenome;
    }
    protected List<Telefone> getTelefones() {
        return telefones;
    }
    protected List<Endereco> getEnderecos() {
        return enderecos;
    }
    @Override
    public String toString(){
        return "Nome: " + getNome() + "; Sobrenome: " + getSobrenome();
    }
}
