package ci553.happyshop.client.login;

import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class LoginModel {
    String filePath = "";



    public boolean userLogin(TextField username, TextField password) {
        boolean userExist = false;
        try{
            String line;
            // create a reader for the lines, put line into list split by the ","
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            String[] values = br.readLine().split(",");
            // create strings for username & password to check if it exists
            // this way stops partial strings causing a false positive
            String fileUsername = values[0];
            String filePassword = values[1];

            while ((line = br.readLine()) != null) {
                if  (line.contains(fileUsername) && line.contains(filePassword)) {
                    userExist = true;
                }
                else{
                    userExist = false;
                    // LoginView - Tell user it was not found
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return false;
            // LoginView - Tell user it was not found
        }
        return userExist;
    }
}




}
