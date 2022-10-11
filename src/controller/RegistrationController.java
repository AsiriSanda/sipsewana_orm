package controller;

import bo.BoFactory;
import bo.custom.CourseBo;
import bo.custom.RegistrationBo;
import bo.custom.StudentBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.CourseDto;
import dto.RegistrationDto;
import dto.StudentDto;
import entity.Course;
import entity.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.tm.RegistrationTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

    @FXML
    public ImageView imgclose;
    @FXML
    public ImageView imghome;

    @FXML
    private Label lblregid;

    @FXML
    private JFXComboBox<String> cmbstudentid;

    @FXML
    private JFXComboBox<String> cmbcourseid;

    @FXML
    private Label lbldate;

    @FXML
    private JFXTextField txtfee;

    @FXML
    private JFXButton btnregister;

    @FXML
    private TableView<RegistrationTM> table;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colstudent;

    @FXML
    private TableColumn<?, ?> colcourse;

    @FXML
    private TableColumn<?, ?> coldate;

    @FXML
    private TableColumn<?, ?> colfee;

    ObservableList<RegistrationTM>registrationTM = FXCollections.observableArrayList();
    ObservableList<String>cmdcourse = FXCollections.observableArrayList();
    ObservableList<String>cmbstudent = FXCollections.observableArrayList();
    RegistrationBo registrationBo = BoFactory.getInstance().getBo(BoFactory.BoType.REGISTRATION);
    StudentBo studentBo=BoFactory.getInstance().getBo(BoFactory.BoType.STUDENT);
    CourseBo courseBo=BoFactory.getInstance().getBo(BoFactory.BoType.COURSE);

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        lbldate.setText(String.valueOf(LocalDate.now()));
        colid.setCellValueFactory(new PropertyValueFactory<>("regno"));
        coldate.setCellValueFactory(new PropertyValueFactory<>("regdate"));
        colfee.setCellValueFactory(new PropertyValueFactory<>("regfee"));
        colstudent.setCellValueFactory(new PropertyValueFactory<>("student"));
        colcourse.setCellValueFactory(new PropertyValueFactory<>("course"));


        try {
            comboboxset();
            genarateId();
            tableLoad();
        } catch (Exception e) {
            e.printStackTrace();
        }
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                RegistrationTM selectedItem = table.getSelectionModel().getSelectedItem();
                lblregid.setText(selectedItem.getRegno());
                cmbstudentid.setValue(selectedItem.getStudent());
                cmbcourseid.setValue(selectedItem.getCourse());
                txtfee.setText(selectedItem.getRegfee());
                btnregister.setText("Update");
            }
        });
    }

    @FXML
    void close(MouseEvent event) {
    close();
    }

    @FXML
    void home(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader =new FXMLLoader(this.getClass().getResource("../view/Dashboard.fxml"));
        Parent rootPane1 = fxmlLoader.load();
        Stage stage = (Stage) imghome.getScene().getWindow();
        stage.setScene(new Scene(rootPane1));
    }

    @FXML
    void register(ActionEvent event) throws Exception {
        if (cmbstudentid.getSelectionModel().isEmpty() && cmbstudentid.getValue()==null){
            cmbstudentid.requestFocus();
            return;
        }else if(cmbcourseid.getSelectionModel().isEmpty() && cmbcourseid.getValue()==null){
            cmbcourseid.requestFocus();
            return;
        }else if(txtfee.getText().isEmpty()){
            txtfee.requestFocus();
            return;
        }
        ArrayList<RegistrationDto> all = registrationBo.getAll();
        for (RegistrationDto registrationDto : all) {
            if (lblregid.getText().equals(String.valueOf(registrationDto.getRegno()))){
                boolean update = registrationBo.update(new RegistrationDto(Integer.parseInt(lblregid.getText())
                        ,lbldate.getText(),Double.parseDouble(txtfee.getText())
                        ,new Student(cmbstudentid.getValue().toString()),new Course(cmbcourseid.getValue().toString())));
                if (update){
                    tableLoad();
                }
                return;
            }
        }
        boolean c = registrationBo.save(new RegistrationDto(Integer.parseInt(lblregid.getText()),lbldate.getText(),Double.parseDouble(txtfee.getText()),new Student(cmbstudentid.getValue().toString()),new Course(cmbcourseid.getValue().toString())));
        if (c){
            clear();
        }
        tableLoad();
        genarateId();
    }
    void close(){
        Stage stage= (Stage) imgclose.getScene().getWindow();
        stage.close();
    }
    void clear(){
        lblregid.setText(" ");
        cmbstudentid.setValue(" ");
        cmbcourseid.setValue(" ");
        txtfee.clear();
    }
    void genarateId() throws Exception {
        ArrayList<RegistrationDto> all = registrationBo.getAll();
        if (all.size()==0){
            lblregid.setText("1");
        }else {
            RegistrationDto registrationDto = registrationBo.gernarateId();
            lblregid.setText(String.valueOf(registrationDto.getRegno()+1));
        }
    }

    void tableLoad() throws Exception {
        registrationTM.clear();
        table.refresh();
        ArrayList<RegistrationDto> all = registrationBo.getAll();
        for (RegistrationDto reg : all) {
            registrationTM.add(new RegistrationTM(String.valueOf(reg.getRegno()),reg.getRegdate(),String.valueOf(reg.getRegfee())
                    ,reg.getStudent().getId(),reg.getCourse().getId()));
        }
        table.setItems(registrationTM);
    }
    void comboboxset() throws Exception {
        ArrayList<StudentDto> all = studentBo.getAll();
        for (StudentDto studentDto : all) {
            cmbstudent.add(studentDto.getId());
        }
        cmbstudentid.setItems(cmbstudent);
        ArrayList<CourseDto> all1 = courseBo.getAll();
        for (CourseDto courseDto : all1) {
            cmdcourse.add(courseDto.getId());
        }
        cmbcourseid.setItems(cmdcourse);
    }

}