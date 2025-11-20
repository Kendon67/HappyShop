package ci553.happyshop.client.login;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.util.Objects;

// Create Login Page GUI
public class LoginView {
    public LoginController loginController;

    public void startLogin(Stage loginWindow){
        loginWindow.setTitle("Login Page");
        HBox root = new HBox();

        VBox leftLayout = new VBox();
        leftLayout.setId("leftLayout");

        Text title = new Text("Happy Shop");
        title.setId("title");

        Image logo = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("shop_logo.png")));
        ImageView logoView = new ImageView(logo);
        logoView.setFitWidth(100);
        logoView.setFitHeight(100);
        logoView.setPreserveRatio(true);

        // create circular white border around the logo
        Circle logoCircle = new Circle(50,50,50);
        logoView.setClip(logoCircle);

        Circle border = new Circle(50, 50, 50);
        border.setStroke(javafx.scene.paint.Color.BLACK);
        border.setStrokeWidth(2);
        border.setFill(Color.WHITE);

        StackPane circularLogo = new StackPane();
        circularLogo.getChildren().addAll(border, logoView);

        leftLayout.getChildren().addAll(title, circularLogo);
        leftLayout.setAlignment(Pos.CENTER);

        // buttons allowing user access to either part of the application
        Button customerButton = new Button("Customer Login");
        customerButton.setOnAction((event) -> {loginController.openCustomerClient();
        });

        Button warehouseButton = new Button("Warehouse Login");
        warehouseButton.setOnAction((event) -> {loginController.openWarehouseClient();
        });

        // create hbox for horizontal lining of button + image
        HBox customerRow = new HBox(20);
        HBox warehouseRow = new HBox(20);

        // user icon
        Image userIcon = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("user_icon.png")));
        ImageView userIconView = new ImageView(userIcon);
        userIconView.setFitWidth(50);
        userIconView.setFitHeight(50);
        userIconView.setPreserveRatio(true);

        customerRow.getChildren().addAll(userIconView,customerButton);
        customerRow.setAlignment(Pos.CENTER);

        // warehouse Icon
        Image warehouseIcon = new Image(Objects.requireNonNull(getClass().getClassLoader().getResourceAsStream("warehouse_icon.png")));
        ImageView warehouseIconView = new ImageView(warehouseIcon);
        warehouseIconView.setFitWidth(50);
        warehouseIconView.setFitHeight(50);
        warehouseIconView.setPreserveRatio(true);

        warehouseRow.getChildren().addAll(warehouseIconView,warehouseButton);
        warehouseRow.setAlignment(Pos.BOTTOM_CENTER);

        VBox rightLayout = new VBox(10);
        rightLayout.getChildren().addAll(customerRow, warehouseRow);
        rightLayout.setAlignment(Pos.CENTER);

        HBox.setHgrow(leftLayout, Priority.ALWAYS);
        HBox.setHgrow(rightLayout, Priority.ALWAYS);
        root.getChildren().addAll(leftLayout,rightLayout);

        Scene scene = new Scene(root, 500, 500);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getClassLoader().getResource("style/login.css")).toExternalForm());
        loginWindow.setScene(scene);
        loginWindow.show();
    }

}
