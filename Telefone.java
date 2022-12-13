import java.util.Objects;

public class Telefone {
    private String ddd;
    private String numero;
    Telefone(String ddd, String numero){
        this.ddd = ddd;
        this.numero = numero;
    }
    public Telefone() {
    }
    @Override
    public String toString(){
        return "("+ ddd + ")" + numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Telefone telefone)) return false;
        return ddd.equals(telefone.ddd) && numero.equals(telefone.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ddd, numero);
    }

    public String getDdd() {
        return ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
