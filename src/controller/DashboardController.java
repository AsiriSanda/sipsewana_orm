package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {
    public ImageView imgClose;
    public AnchorPane pnCourse;
    public AnchorPane pnStudent;
    public AnchorPane pnreg;

    public void close(MouseEvent mouseEvent) {
      close();
    }

    public void course(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader =new FXMLLoader(this.getClass().getResource("../view/Course.fxml"));
        Parent rootPane1 = fxmlLoader.load();
        Stage stage = (Stage) pnCourse.getScene().getWindow();
        stage.setScene(new Scene(rootPane1));


    }
    void close(){
        Stage stage= (Stage) imgClose.getScene().getWindow();
        stage.close();
    }

    public void student(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader =new FXMLLoader(this.getClass().getResource("../view/Student.fxml"));
        Parent rootPane1 = fxmlLoader.load();
        Stage stage = (Stage) pnStudent.getScene().getWindow();
        stage.setScene(new Scene(rootPane1));
    }

    public void reg(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader =new FXMLLoader(this.getClass().getResource("../view/Registration.fxml"));
        Parent rootPane1 = fxmlLoader.load();
        Stage stage = (Stage) pnreg.getScene().getWindow();
        stage.setScene(new Scene(rootPane1));
    }
}
