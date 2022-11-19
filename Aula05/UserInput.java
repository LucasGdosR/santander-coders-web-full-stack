import java.util.Scanner;
public class UserInput {
    private final Scanner scanner;
    UserInput(){ this.scanner = new Scanner(System.in); }
    public String inputName(){
        String name = scanner.nextLine();
        while (name.length() == 0){
            System.out.println("You must add a product name!");
            name = scanner.nextLine();
        }
        return name;
    }
    public int inputQuantity(){
        int quantity;
        while (true) {
            try {
                quantity = scanner.nextInt();
                if (quantity < 1){
                    System.out.println("Error! Dear customer, input a positive integer.");
                    continue;
                }
                break;
            } catch(Exception e){
                scanner.nextLine();
                System.out.println("Error! Dear customer, input an integer.");
            }
        }
        return quantity;
    }
    public double inputPrice(){
        double price;
        while (true) {
            try {
                price = scanner.nextDouble();
                if (price <= 0){
                    System.out.println("Error! Dear customer, be honest! That's too cheap!");
                    continue;
                }
                break;
            } catch(Exception e){
                scanner.nextLine();
                System.out.println("Error! Dear customer, input a number.");
            }
        }
        scanner.nextLine();
        return price;
    }
    public boolean addMore(){
        boolean addMore;
        while (true){
            String input = scanner.nextLine();
            try {
                if ('y' == (input.toLowerCase().charAt(0))){
                    addMore = true;
                    break;
                }
                else if ('n' == (input.toLowerCase().charAt(0))){
                    addMore = false;
                    scanner.close();
                    break;
                }
                System.out.println("Dear customer, input \"yes\" or \"no\". Would you like to add a new product? ");
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("Error! Write something! Y/N: ");
            }
        }
        return addMore;
    }
}
