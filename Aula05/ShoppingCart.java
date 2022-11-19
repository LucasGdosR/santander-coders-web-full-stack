import java.util.HashMap;
import java.util.Map;
public class ShoppingCart {
    private final Map<String, Double[]> shoppingCart;
    ShoppingCart(){
        this.shoppingCart = new HashMap<>();
    }
    public void addProduct(String productName, int productQuantity, double productPrice){
        Double[] financialInfo = { (double)productQuantity, productPrice};
        shoppingCart.put(productName, financialInfo);
    }

    public void checkout(){
        double total = 0;
        System.out.println("Alright, let me add that up for you...");
        for (Map.Entry<String,Double[]> product : shoppingCart.entrySet()) {
            double quantity = product.getValue()[0];
            double price = product.getValue()[1];
            double sum = quantity * price;
            System.out.printf("Product: %s; Quantity: %.0f units; Price (unit): %.2f; Price (total): %.2f.\n",
                    product.getKey(), quantity, price, sum);
            total += sum;
        }
        System.out.printf("Your total will be %.2f. Thank you for your patronage! Happy holidays :)", total);
    }
}
