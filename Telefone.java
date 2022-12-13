import java.util.Objects;

public class Telefone {
    private String ddd;
    private String ddi;
    private String numero;
    Telefone(String ddi, String ddd, String numero){
        this.ddd = ddd;
        this.ddi = ddi;
        this.numero = numero;
    }
    public Telefone() {
    }

    @Override
    public String toString(){
        return "+"+ ddi + "("+ ddd + ")" + numero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Telefone telefone)) return false;
        return ddi.equals(telefone.ddi) && ddd.equals(telefone.ddd) && numero.equals(telefone.numero);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ddi, ddd, numero);
    }
    public String getDdi() {
        return ddi;
    }
    public String getDdd() {
        return ddd;
    }

    public String getNumero() {
        return numero;
    }
    public void setDdi(String ddi) {
        this.ddi = ddi;
    }
    public void setDdd(String ddd) {
        this.ddd = ddd;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }
}
