import utils.OrderFacade;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        OrderFacade orderFacade = new OrderFacade();

        String productId = "P12345";
        String custumerId = "C657";
        double amount = 100.00;

        orderFacade.placeOrder(productId,custumerId,amount);
    }
}