package ci553.happyshop.client.login;

import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LoginModel {
    String filePath = "src/main/resources/user.txt";
    LoginView loginView;

    public void userLogin(TextField username, TextField password) {
        boolean userExist = false;
        try{
            String line;
            // create a reader for the lines, put line into list split by the ","
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 2){
                    // create strings for username & password to check if it exists
                    // this way stops partial strings causing a false positive
                    String fileUsername = values[0];
                    String filePassword = values[1];

                    if  (fileUsername.equals(username.getText()) && filePassword.equals(password.getText())) {
                        userExist = true;
                        break;
                    }
                }
            }
            if (userExist) {
                loginView.userLoginPage();
            }
            else{
                // inform user with error
            }
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            // LoginView - Tell user it was not found
        }
    }


}





