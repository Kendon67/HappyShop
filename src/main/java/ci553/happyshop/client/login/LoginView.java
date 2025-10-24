package ci553.happyshop.client.login;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.util.Objects;

// Create Login Page GUI
public class LoginView {
    public LoginController loginController;

    public void startLogin(Stage loginWindow){
        loginWindow.setTitle("Login Page");

        // Create buttons allowing user access to either part of the application
        Button customerButton = new Button("Customer Login");
        customerButton.setOnAction((event) -> {loginController.openCustomerClient();
        });

        Button warehouseButton = new Button("Warehouse Login");
        warehouseButton.setOnAction((event) -> {loginController.openWarehouseClient();
        });


        VBox layout = new VBox(10);
        layout.getChildren().addAll(customerButton, warehouseButton);

        Scene scene = new Scene(layout, 250, 300);
//        scene.getStylesheets().add(Objects.requireNonNull(LoginView.class.getResource("loginStyle.css")).toExternalForm());
        loginWindow.setScene(scene);
        loginWindow.show();
    }

}
