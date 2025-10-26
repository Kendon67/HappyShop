package ci553.happyshop.client.customer;

import ci553.happyshop.utility.UIStyle;
import ci553.happyshop.utility.WinPosManager;
import ci553.happyshop.utility.WindowBounds;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Objects;

/**
 * The CustomerView is separated into two sections by a line :
 *
 * 1. Search Page – Always visible, allowing customers to browse and search for products.
 * 2. the second page – display either the Trolley Page or the Receipt Page
 *    depending on the current context. Only one of these is shown at a time.
 */

public class CustomerView  {
    public CustomerController cusController;

    private final int WIDTH = UIStyle.customerWinWidth;
    private final int HEIGHT = UIStyle.customerWinHeight;
    private final int COLUMN_WIDTH = WIDTH / 2 - 10;

    private HBox hbRoot; // Top-level layout manager
    private VBox vbTrolleyPage;  //vbTrolleyPage and vbReceiptPage will swap with each other when need
    private VBox vbReceiptPage;

    TextField tfId; //for user input on the search page. Made accessible so it can be accessed or modified by CustomerModel
    TextField tfName; //for user input on the search page. Made accessible so it can be accessed by CustomerModel

    //four controllers needs updating when program going on
    private ImageView ivProduct; //image area in searchPage
    private Label lbProductInfo;//product text info in searchPage
    private TextArea taTrolley; //in trolley Page
    private TextArea taReceipt;//in receipt page

    // Holds a reference to this CustomerView window for future access and management
    // (e.g., positioning the removeProductNotifier when needed).
    private Stage viewWindow;

    public void start(Stage window) {
        VBox vbSearchPage = createSearchPage();
        vbTrolleyPage = CreateTrolleyPage();
        vbReceiptPage = createReceiptPage();

        // Create a divider line
        Line line = new Line(0, 0, 0, HEIGHT);
        line.setStrokeWidth(4);
        line.setStroke(Color.PINK);
        VBox lineContainer = new VBox(line);
        lineContainer.setPrefWidth(4); // Give it some space
        lineContainer.setAlignment(Pos.CENTER);

        hbRoot = new HBox(10, vbSearchPage, lineContainer, vbTrolleyPage); //initialize to show trolleyPage
        hbRoot.setAlignment(Pos.CENTER);
        hbRoot.setStyle(UIStyle.rootStyle);

        //playMusic("/sounds/The Simpsons.mp3");
        Scene scene = new Scene(hbRoot, WIDTH, HEIGHT);
        window.setScene(scene);
        window.setTitle("🛒 HappyShop Customer Client");
        WinPosManager.registerWindow(window,WIDTH,HEIGHT); //calculate position x and y for this window
        window.show();
        viewWindow=window;// Sets viewWindow to this window for future reference and management.
    }

    private VBox createSearchPage() {
        Label laPageTitle = new Label("Search by Product ID/Name");
        laPageTitle.setStyle(UIStyle.labelTitleStyle);

        Label laId = new Label("ID:      ");
        laId.setStyle(UIStyle.labelStyle);
        tfId = new TextField();
        tfId.setPromptText("eg. 0001");
        tfId.setStyle(UIStyle.textFiledStyle);
        HBox hbId = new HBox(10, laId, tfId);

        Label laName = new Label("Name:");
        laName.setStyle(UIStyle.labelStyle);
        tfName = new TextField();
        tfName.setPromptText("implement it if you want");
        tfName.setStyle(UIStyle.textFiledStyle);
        HBox hbName = new HBox(10, laName, tfName);

        Label laPlaceHolder = new Label(  " ".repeat(15)); //create left-side spacing so that this HBox aligns with others in the layout.
        Button btnSearch = new Button("Search");
        btnSearch.setStyle(UIStyle.buttonStyle);
        btnSearch.setOnAction(this::buttonClicked);
        Button btnAddToTrolley = new Button("Add to Trolley");
        btnAddToTrolley.setStyle(UIStyle.buttonStyle);
        btnAddToTrolley.setOnAction(this::buttonClicked);
        HBox hbBtns = new HBox(10, laPlaceHolder,btnSearch, btnAddToTrolley);

        ivProduct = new ImageView("imageHolder.jpg");
        ivProduct.setFitHeight(60);
        ivProduct.setFitWidth(60);
        ivProduct.setPreserveRatio(true); // Image keeps its original shape and fits inside 60×60
        ivProduct.setSmooth(true); //make it smooth and nice-looking

        lbProductInfo = new Label("Thank you for shopping with us.");
        lbProductInfo.setWrapText(true);
        lbProductInfo.setMinHeight(Label.USE_PREF_SIZE);  // Allow auto-resize
        lbProductInfo.setStyle(UIStyle.labelMulLineStyle);
        HBox hbSearchResult = new HBox(5, ivProduct, lbProductInfo);
        hbSearchResult.setAlignment(Pos.CENTER_LEFT);

        VBox vbSearchPage = new VBox(15, laPageTitle, hbId, hbName, hbBtns, hbSearchResult);
        vbSearchPage.setPrefWidth(COLUMN_WIDTH);
        vbSearchPage.setAlignment(Pos.TOP_CENTER);
        vbSearchPage.setStyle("-fx-padding: 15px;");

        return vbSearchPage;
    }

    private VBox CreateTrolleyPage() {
        Label laPageTitle = new Label("🛒🛒  Trolley 🛒🛒");
        laPageTitle.setStyle(UIStyle.labelTitleStyle);

        taTrolley = new TextArea();
        taTrolley.setEditable(false);
        taTrolley.setPrefSize(WIDTH/2, HEIGHT-50);

        Button btnCancel = new Button("Cancel");
        btnCancel.setOnAction(this::buttonClicked);
        btnCancel.setStyle(UIStyle.buttonStyle);

        Button btnPayment = new Button("Payment");
        btnPayment.setOnAction(this::buttonClicked);
        btnPayment.setStyle(UIStyle.buttonStyle);

        HBox hbBtns = new HBox(10, btnCancel, btnPayment);
        hbBtns.setStyle("-fx-padding: 15px;");
        hbBtns.setAlignment(Pos.CENTER);

        vbTrolleyPage = new VBox(15, laPageTitle, taTrolley, hbBtns);
        vbTrolleyPage.setPrefWidth(COLUMN_WIDTH);
        vbTrolleyPage.setAlignment(Pos.TOP_CENTER);
        vbTrolleyPage.setStyle("-fx-padding: 15px;");
        return vbTrolleyPage;
    }

    // create a page allowing the user to enter payment details
    // textfields within a VBOX to stack them

    public void cardPaymentPage(){
        Stage cardWindow = new Stage();
        cardWindow.setTitle("Pay");

        VBox cardBox = new VBox();
        cardBox.setAlignment(Pos.CENTER);

        TextField cardholderField = new TextField();
        cardholderField.setPromptText("Cardholder Name");
        cardholderField.setStyle(UIStyle.textFiledStyle);

        TextField cardNumField = new TextField();
        cardNumField.setPromptText("Card Number");
        cardNumField.setStyle(UIStyle.textFiledStyle);

        TextField cardExpiryField = new TextField();
        cardExpiryField.setPromptText("Expiry Date");
        cardExpiryField.setStyle(UIStyle.textFiledStyle);

        TextField cvvField = new TextField();
        cvvField.setPromptText("CVV");
        cvvField.setStyle(UIStyle.textFiledStyle);

        Button cashBtn = new Button("Pay in Cash");
        cashBtn.setStyle(UIStyle.buttonStyle);
        cashBtn.setOnAction(actionEvent1 -> {
            try {
                cashPaymentPage();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // get result of each textfield and pass them to controller for validation
        Button submitBtn = new Button("Submit & Pay Card");
        submitBtn.setStyle(UIStyle.buttonStyle);
        submitBtn.setOnAction(actionEvent -> {
            String cardHolder = cardholderField.getText();
            String cardNum = cardNumField.getText();
            String cardExpiry = cardExpiryField.getText();
            String cvv = cvvField.getText();

            cusController.passCardDetails(cardHolder, cardNum, cardExpiry, cvv);
            try{
                cusController.doAction("Submit & Pay Card");

            } catch (SQLException | IOException e) {
                throw new RuntimeException(e);
            }
            cardWindow.close();
        });
        cardBox.getChildren().addAll(cardholderField,cardNumField,cardExpiryField,
                cvvField, cashBtn, submitBtn);
        cardWindow.setResizable(false);
        cardWindow.setWidth(WIDTH);
        cardWindow.setHeight(HEIGHT);
        cardWindow.setScene(new Scene(cardBox));
        cardWindow.show();
    }

    public void cashPaymentPage() throws SQLException, IOException {
        Stage cashWindow = new Stage();
        cashWindow.setTitle("Cash Payment");

        VBox cashBox = new VBox();
        cashBox.setAlignment(Pos.CENTER);

        TextField cashEntry = new TextField();
        cashEntry.setPromptText("Enter Amount: ");
        cashEntry.setStyle(UIStyle.textFiledStyle);
        cashEntry.setPrefWidth(COLUMN_WIDTH);

        Button submitBtn = new Button("Submit & Pay Cash");
        submitBtn.setStyle(UIStyle.buttonStyle);

        submitBtn.setOnAction(e -> {
            double cashAmount = Double.parseDouble(cashEntry.getText());
            try {
                cusController.passCashDetails(cashAmount);
            } catch (IOException | SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        cashBox.getChildren().addAll(cashEntry, submitBtn);
        cashWindow.setScene(new Scene(cashBox));
        cashWindow.show();
    };

    public void cardInvalid(){
        Dialog<String> cardInvalidAlert = new Dialog<>();
        cardInvalidAlert.setTitle("Card Invalid");
        cardInvalidAlert.setHeaderText("Card Invalid");
        cardInvalidAlert.setContentText("Please enter valid Card Details");
        cardInvalidAlert.getDialogPane().getButtonTypes().add(ButtonType.OK);
        cardInvalidAlert.showAndWait();
    }

    public void forceCash(){
        Dialog<String> cashOnlyAlert = new Dialog<>();
        cashOnlyAlert.setTitle("Cash Only");
        cashOnlyAlert.setContentText("Trolley is under £5, cash payment required.");
        cashOnlyAlert.getDialogPane().getButtonTypes().add(ButtonType.OK);
        cashOnlyAlert.showAndWait();
    }

    public void paymentAccepted(double change){
        Dialog<String> paymentAcceptedAlert = new Dialog<>();
        paymentAcceptedAlert.setTitle("Payment Accepted");
        paymentAcceptedAlert.setHeaderText("Payment has been accepted");
        paymentAcceptedAlert.setContentText("Change: £" + change);
        paymentAcceptedAlert.getDialogPane().getButtonTypes().add(ButtonType.OK);
        paymentAcceptedAlert.showAndWait();
    };

    public void cashFailed() {
        Dialog<String> cashFailedAlert = new Dialog<>();
        cashFailedAlert.setTitle("Payment Accepted");
        cashFailedAlert.setHeaderText("Please enter a valid cash amount for this transaction.");
        cashFailedAlert.getDialogPane().getButtonTypes().add(ButtonType.OK);
        cashFailedAlert.showAndWait();
    }

    private VBox createReceiptPage() {
        Label laPageTitle = new Label("Receipt");
        laPageTitle.setStyle(UIStyle.labelTitleStyle);

        taReceipt = new TextArea();
        taReceipt.setEditable(false);
        taReceipt.setPrefSize(WIDTH/2, HEIGHT-50);

        Button btnCloseReceipt = new Button("OK & Close"); //btn for closing receipt and showing trolley page
        btnCloseReceipt.setStyle(UIStyle.buttonStyle);

        btnCloseReceipt.setOnAction(this::buttonClicked);

        vbReceiptPage = new VBox(15, laPageTitle, taReceipt, btnCloseReceipt);
        vbReceiptPage.setPrefWidth(COLUMN_WIDTH);
        vbReceiptPage.setAlignment(Pos.TOP_CENTER);
        vbReceiptPage.setStyle(UIStyle.rootStyleYellow);
        return vbReceiptPage;
    }


    private void buttonClicked(ActionEvent event) {
        try{
            playSound("/sounds/button-press-382713.mp3");
            System.out.println("should work?");
            Button btn = (Button)event.getSource();
            String action = btn.getText();
            if(action.equals("Add to Trolley")){
                showTrolleyOrReceiptPage(vbTrolleyPage); //ensure trolleyPage shows if the last customer did not close their receiptPage
            }
            if(action.equals("OK & Close")){
                showTrolleyOrReceiptPage(vbTrolleyPage);
            }
            cusController.doAction(action);
        }
        catch(SQLException e){
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public void update(String imageName, String searchResult, String trolley, String receipt) {

        ivProduct.setImage(new Image(imageName));
        lbProductInfo.setText(searchResult);
        taTrolley.setText(trolley);
        if (!receipt.equals("")) {
            showTrolleyOrReceiptPage(vbReceiptPage);
            taReceipt.setText(receipt);
        }
    }

    // Replaces the last child of hbRoot with the specified page.
    // the last child is either vbTrolleyPage or vbReceiptPage.
    private void showTrolleyOrReceiptPage(Node pageToShow) {
        int lastIndex = hbRoot.getChildren().size() - 1;
        if (lastIndex >= 0) {
            hbRoot.getChildren().set(lastIndex, pageToShow);
        }
    }

    public void playSound(String soundPath){
        try{
            Media SFX = new Media(Objects.requireNonNull(getClass().getResource(soundPath)).toExternalForm()); // retrieves sound file from provided path
            MediaPlayer soundPlayer = new MediaPlayer(SFX);
            soundPlayer.play();
        }
        catch (Exception e){
            System.out.println("Error, No sound effect found");
            System.out.println (e);
        }
    }

    public void playMusic (String musicPath){
        try{
            Media Music = new Media(Objects.requireNonNull(getClass().getResource(musicPath)).toExternalForm());
            MediaPlayer musicPlayer = new MediaPlayer(Music);
            musicPlayer.play();
        }
        catch (Exception e){
            System.out.println("Error, No music file found");
            System.out.println (e);
        }
    }

    WindowBounds getWindowBounds() {
        return new WindowBounds(viewWindow.getX(), viewWindow.getY(),
                  viewWindow.getWidth(), viewWindow.getHeight());
    }
}
