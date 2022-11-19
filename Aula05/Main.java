public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to \"Free Market\"! We are not a Mercado Livre ripoff.");
        UserInput userInput = new UserInput();
        ShoppingCart shoppingCart = new ShoppingCart();
        boolean addMore;
        do {
            System.out.println("Tell me what the product's name is: ");
            String productName = userInput.inputName();
            System.out.println("That's a great product! How many of those will it be? ");
            int productQuantity = userInput.inputQuantity();
            System.out.println("Fair enough. And how much is each unit? ");
            double productPrice = userInput.inputPrice();
            shoppingCart.addProduct(productName, productQuantity, productPrice);
            System.out.println("What a bargain! Is it Black November or something? Oh, it is! :)\n" +
                    "Would you like to add more? Y/N: ");
            addMore = userInput.addMore();
        } while (addMore);
        shoppingCart.checkout();
    }
}
