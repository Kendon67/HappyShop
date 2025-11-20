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
        HBox root = new HBox();

        VBox leftLayout = new VBox();
        leftLayout.setStyle("-fx-background-color: red;");

        HBox.setHgrow(leftLayout, Priority.ALWAYS);
        // create buttons allowing user access to either part of the application
        Button customerButton = new Button("Customer Login");
        customerButton.setOnAction((event) -> {loginController.openCustomerClient();
        });

        Button warehouseButton = new Button("Warehouse Login");
        warehouseButton.setOnAction((event) -> {loginController.openWarehouseClient();
        });


        VBox rightLayout = new VBox(10);
        rightLayout.getChildren().addAll(customerButton, warehouseButton);

        HBox.setHgrow(leftLayout, Priority.ALWAYS);
        HBox.setHgrow(rightLayout, Priority.ALWAYS);
        root.getChildren().addAll(leftLayout,rightLayout);

        Scene scene = new Scene(root, 500, 500);
//        scene.getStylesheets().add(Objects.requireNonNull(LoginView.class.getResource("loginStyle.css")).toExternalForm());
        loginWindow.setScene(scene);
        loginWindow.show();
    }

}
