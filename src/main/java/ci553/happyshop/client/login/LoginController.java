package ci553.happyshop.client.login;

import ci553.happyshop.client.customer.CustomerView;
import ci553.happyshop.client.warehouse.WarehouseView;
import javafx.stage.Stage;


public class LoginController {
    public LoginView loginView;

    public void openCustomerClient() {
        CustomerView customerView = new CustomerView();
        customerView.start(new Stage());
    };

    public void openWarehouseClient(){
        WarehouseView warehouseView = new WarehouseView();
        warehouseView.start(new Stage());
    }
}
