package controller;

import bo.BoFactory;
import bo.custom.StudentBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDto;
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
import view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StudentController implements Initializable {

    public JFXTextField txtcontact;
    @FXML
    private ImageView imgClose;

    @FXML
    private ImageView imgHome;

    @FXML
    private JFXTextField txtname;

    @FXML
    private Label lblId;

    @FXML
    private JFXTextField txtaddress;

    @FXML
    private JFXTextField txtstudent;

    @FXML
    private JFXDatePicker dtdob;

    @FXML
    private JFXComboBox<String> cmdgender;

    @FXML
    private TableView<StudentTM> table;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableColumn<?, ?> coladdress;

    @FXML
    private TableColumn<?, ?> colcontact;

    @FXML
    private TableColumn<?, ?> coldob;

    @FXML
    private TableColumn<?, ?> colgender;

    @FXML
    private TableColumn<?, ?> coloperation;

    @FXML
    private JFXButton btnadd;

    @FXML
    private TextField txtsearch;

    StudentBo studentBo = BoFactory.getInstance().getBo(BoFactory.BoType.STUDENT);
    ObservableList<StudentTM> studenttm = FXCollections.observableArrayList();
    ObservableList<String> gender = FXCollections.observableArrayList("Male","Female");
    @FXML
    void add(ActionEvent event) throws Exception {
        if (txtname.getText().isEmpty()){
            txtname.requestFocus();
            return;
        }else if(txtaddress.getText().isEmpty()){
            txtaddress.requestFocus();
            return;
        }else if(txtcontact.getText().isEmpty()){
            txtcontact.requestFocus();
            return;
        }else if (dtdob.getEditor().getText().isEmpty()){
            dtdob.requestFocus();
            return;
        }else if(cmdgender.getSelectionModel().isEmpty()){
            cmdgender.requestFocus();
            return;
        }
        ArrayList<StudentDto> all = studentBo.getAll();
        for (StudentDto studentDto : all) {
            if (lblId.getText().equals(studentDto.getId())){
                boolean update = studentBo.update(new StudentDto(lblId.getText(), txtname.getText(),
                        txtaddress.getText(), txtcontact.getText(), dtdob.getValue(), String.valueOf(cmdgender.getValue())));
                if (update){
                    tableLoad();
                }
                return;
            }
        }
        boolean c = studentBo.save(new StudentDto(lblId.getText(), txtname.getText(), txtaddress.getText()
                , txtcontact.getText(), dtdob.getValue(),  String.valueOf(cmdgender.getValue())));
        if (c){
            clear();
        }else {

        }
        clear();
        tableLoad();
        genarateId();
    }

    @FXML
    void close(MouseEvent event) {
    close();
    }

    @FXML
    void home(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader =new FXMLLoader(this.getClass().getResource("../view/Dashboard.fxml"));
        Parent rootPane1 = fxmlLoader.load();
        Stage stage = (Stage) imgHome.getScene().getWindow();
        stage.setScene(new Scene(rootPane1));
    }

    @FXML
    void search(ActionEvent event) throws Exception {
        StudentDto search = studentBo.search(txtsearch.getText());
        lblId.setText(search.getId());
        txtname.setText(search.getName());
        txtaddress.setText(search.getAddress());
        txtcontact.setText(search.getContact());
        dtdob.setValue(search.getDate());
        cmdgender.setValue(search.getGender());


    }
    void close(){
        Stage stage= (Stage) imgClose.getScene().getWindow();
        stage.close();
    }
    void clear(){
        lblId.setText(" ");
        txtname.clear();
        txtaddress.clear();
        txtcontact.clear();
        dtdob.getEditor().clear();
        cmdgender.setValue("Gender");
    }
    void genarateId() throws Exception {
        ArrayList<StudentDto> all = studentBo.getAll();
        if (all.size()==0){
            lblId.setText("S001");
        }else {
            StudentDto studentDto = studentBo.gernarateId();
            String[] cs = studentDto.getId().split("S");
            int count = Integer.parseInt(cs[1]);
            if (count >= 99) {
                lblId.setText("S" + (count + 1));
            } else if (count >= 9) {
                lblId.setText("S0" + (count + 1));
            } else {
                lblId.setText("S00" + (count + 1));
            }
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmdgender.setItems(gender);

        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coladdress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colcontact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        coldob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colgender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        coloperation.setCellValueFactory(new PropertyValueFactory<>("button"));


        try {
            genarateId();
            tableLoad();
        } catch (Exception e) {
            e.printStackTrace();
        }

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                StudentTM selectedItem = table.getSelectionModel().getSelectedItem();
                lblId.setText(selectedItem.getId());
                txtname.setText(selectedItem.getName());
                txtaddress.setText(selectedItem.getAddress());
                txtcontact.setText(selectedItem.getContact());
                dtdob.setValue(LocalDate.parse(selectedItem.getDob()));
                cmdgender.setValue(selectedItem.getGender());
                btnadd.setText("Update");
            }

        });
    }


    void tableLoad() throws Exception {
        studenttm.clear();
        table.refresh();
        ArrayList<StudentDto> all = studentBo.getAll();
        for (StudentDto studentDto : all) {
            JFXButton delete = new JFXButton("Delete");
            delete.setOnAction((e)->{
                try {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete You Sure ?", ButtonType.YES, ButtonType.NO);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        studentBo.delete(studentDto.getId());
                        tableLoad();
                        genarateId();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
            studenttm.add(new StudentTM(studentDto.getId(), studentDto.getName(),studentDto.getAddress(),studentDto.getContact(),String.valueOf(studentDto.getDate()),studentDto.getGender(),delete));
        }
        table.setItems(studenttm);
    }
}
