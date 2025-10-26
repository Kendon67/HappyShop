package ci553.happyshop.client.customer;

import java.io.IOException;
import java.sql.SQLException;

public class CustomerController {
    public CustomerModel cusModel;
    public CustomerCard cusCard;

    public void doAction(String action) throws SQLException, IOException {
        switch (action) {
            case "Search":
                cusModel.search();
                break;
            case "Add to Trolley":
                cusModel.addToTrolley();
                break;
            case "Cancel":
                cusModel.cancel();
                break;
            case "Check Out":
                cusModel.checkOut();
                break;
            case "OK & Close":
                cusModel.closeReceipt();
                break;
            case "Submit & Pay":
                cusModel.pay();
                break;
        }
    }

    public void passCardDetails(String cardHolder, String cardNum, String cardExpiry, String cvv) {
        cusCard.recieveDetails(cardHolder, cardNum, cardExpiry, cvv);
    }
}
