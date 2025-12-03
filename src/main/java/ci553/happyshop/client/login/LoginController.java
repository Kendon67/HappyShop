package ci553.happyshop.client.login;

import ci553.happyshop.client.customer.CustomerView;
import ci553.happyshop.client.warehouse.WarehouseView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginController {
    public LoginModel loginModel;
    public LoginView loginView;
    public UserRegister register = new UserRegister();

    public void openCustomerClient() {
        CustomerView customerView = new CustomerView();
        customerView.start(new Stage());
    };

    public void openWarehouseClient(){
        WarehouseView warehouseView = new WarehouseView();
        warehouseView.start(new Stage());
    }

    public void createUser(TextField username, TextField password, String userType) throws IOException {
        register.createUser(username, password, userType);
    }

    public void userLogin(TextField username, TextField password) throws IOException {
        if (loginModel.userLogin(username, password)) {
                openCustomerClient();
        }
    }
}
