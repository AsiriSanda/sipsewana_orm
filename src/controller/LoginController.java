package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class LoginController{

    public Label lblmessage;
    public ImageView imgClose;
    @FXML
    private TextField textusername;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private JFXButton btnlogin;

    @FXML
    void login(ActionEvent event) throws Exception {
        if(textusername.getText().equals("admin") && txtpassword.getText().equals("123")){
            FXMLLoader fxmlLoader =new FXMLLoader(this.getClass().getResource("../view/Dashboard.fxml"));
            Parent rootPane1 = fxmlLoader.load();
            Stage stage = (Stage) btnlogin.getScene().getWindow();
            stage.setScene(new Scene(rootPane1));
        }else if(textusername.getText().isEmpty() && txtpassword.getText().isEmpty()){
            lblmessage.setText("Please Enter Your Data");
        }else {
            lblmessage.setText("Wrong User Name Or Password");
        }



    }





    public void close(MouseEvent mouseEvent) {
        close();
    }
    void close(){
        Stage stage= (Stage) imgClose.getScene().getWindow();
        stage.close();
    }
}
