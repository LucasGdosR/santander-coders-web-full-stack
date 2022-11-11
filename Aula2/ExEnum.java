import java.util.Scanner;

enum Meses {
    JANEIRO (1),
    FEVEREIRO (2),
    MARCO (3),
    ABRIL (5),
    MAIO (7),
    JUNHO (11),
    JULHO (13),
    AGOSTO (17),
    SETEMBRO (19),
    OUTUBRO (23),
    NOVEMBRO (29),
    DEZEMBRO (31);
    private Integer valor;

    Meses(Integer valor){
        this.valor = valor;
    }

    public Integer getValor() {
        return valor;
    }
}

public class ExEnum {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println("Informe o mês: ");
        String mes = sc.next().toUpperCase();
        System.out.println("Valor gasto do mês: ");
        switch (mes) {
            case "JANEIRO" -> System.out.println(Meses.JANEIRO.getValor());
            case "FEVEREIRO" -> System.out.println(Meses.FEVEREIRO.getValor());
            case "MARCO" -> System.out.println(Meses.MARCO.getValor());
            case "ABRIL" -> System.out.println(Meses.ABRIL.getValor());
            case "MAIO" -> System.out.println(Meses.MAIO.getValor());
            case "JUNHO" -> System.out.println(Meses.JUNHO.getValor());
            case "JULHO" -> System.out.println(Meses.JULHO.getValor());
            case "AGOSTO" -> System.out.println(Meses.AGOSTO.getValor());
            case "SETEMBRO" -> System.out.println(Meses.SETEMBRO.getValor());
            case "OUTUBRO" -> System.out.println(Meses.OUTUBRO.getValor());
            case "NOVEMBRO" -> System.out.println(Meses.NOVEMBRO.getValor());
            case "DEZEMBRO" -> System.out.println(Meses.DEZEMBRO.getValor());
        }
    }
}
