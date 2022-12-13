import java.util.Objects;

public class Endereco {
    private String cep;
    private String logradouro;
    private String numero;
    private String cidade;
    private String estado;

    public Endereco(String cep, String logradouro, String numero, String cidade, String estado) {
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
    }
    public Endereco() {
    }
    @Override
    public String toString(){
        return "CEP: "+(cep)+" - "+logradouro+" nº "+numero+" - "+cidade+" "+estado;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Endereco endereco)) return false;
        return cep.equals(endereco.cep) && logradouro.equals(endereco.logradouro) && numero.equals(endereco.numero) && cidade.equals(endereco.cidade) && estado.equals(endereco.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cep, logradouro, numero, cidade, estado);
    }


    public String getCep() {
        return cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public String getCidade() {
        return cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

