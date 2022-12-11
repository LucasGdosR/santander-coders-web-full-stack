import java.util.Objects;

public class Telefone {
    private String ddd;
    private String numero;
    Telefone(String ddd, String numero){
        this.ddd = ddd;
        this.numero = numero;
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
}
