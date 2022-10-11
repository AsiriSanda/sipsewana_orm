package controller;

import bo.BoFactory;
import bo.custom.CourseBo;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dto.CourseDto;
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
import view.tm.CourseTM;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CourseController implements Initializable {
    @FXML
    public ImageView imgHome;
    @FXML
    public TableColumn colOperation;
    @FXML
    private ImageView imgClose;

    @FXML
    private Label lblId;

    @FXML
    private JFXTextField txtName;

    @FXML
    private JFXTextField txtType;

    @FXML
    private JFXTextField txtDuration;

    @FXML
    private TableView<CourseTM> table;

    @FXML
    private TableColumn<?, ?> colid;

    @FXML
    private TableColumn<?, ?> colname;

    @FXML
    private TableColumn<?, ?> coltype;

    @FXML
    private TableColumn<?, ?> colduration;

    @FXML
    private JFXButton btnAdd;

    CourseBo courseBo = BoFactory.getInstance().getBo(BoFactory.BoType.COURSE);
    ObservableList<CourseTM> courseTm = FXCollections.observableArrayList();


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colid.setCellValueFactory(new PropertyValueFactory<>("id"));
        colname.setCellValueFactory(new PropertyValueFactory<>("name"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colduration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colOperation.setCellValueFactory(new PropertyValueFactory<>("button"));


        try {
            genarateId();
            tableLoad();
        } catch (Exception e) {
            e.printStackTrace();
        }

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                CourseTM selectedItem = table.getSelectionModel().getSelectedItem();
                lblId.setText(selectedItem.getId());
                txtName.setText(selectedItem.getName());
                txtType.setText(selectedItem.getType());
                txtDuration.setText(selectedItem.getDuration());
                btnAdd.setText("Update");
            }
        });
    }

    @FXML
    void add(ActionEvent event) throws Exception {
            if (txtName.getText().isEmpty()){
                txtName.requestFocus();
                return;
            }else if (txtType.getText().isEmpty()){
                txtType.requestFocus();
                return;
            }else if(txtDuration.getText().isEmpty()){
                txtDuration.requestFocus();
                return;
            }
        ArrayList<CourseDto> all = courseBo.getAll();
        for (CourseDto courseDto : all) {
            if (lblId.getText().equals(courseDto.getId())){
                boolean update = courseBo.update(new CourseDto(lblId.getText(), txtName.getText().trim()
                        , txtType.getText().trim(), txtDuration.getText().trim()));
                if (update){
                    tableLoad();
                }
                    return;
            }
        }
        boolean c = courseBo.save(new CourseDto(lblId.getText(), txtName.getText().trim()
                , txtType.getText().trim(), txtDuration.getText().trim()));
        if (c){
            clear();
        }else {

        }
        tableLoad();
        genarateId();
    }



    public void home(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader =new FXMLLoader(this.getClass().getResource("../view/Dashboard.fxml"));
        Parent rootPane1 = fxmlLoader.load();
        Stage stage = (Stage) imgHome.getScene().getWindow();
        stage.setScene(new Scene(rootPane1));
    }

    @FXML
    void close(MouseEvent event) {
        close();
    }
    void close(){
        Stage stage= (Stage) imgClose.getScene().getWindow();
        stage.close();
    }
    void clear(){
        lblId.setText(" ");
        txtName.clear();
        txtType.clear();
        txtDuration.clear();
    }

    void tableLoad() throws Exception {
        courseTm.clear();
        table.refresh();
        ArrayList<CourseDto> all = courseBo.getAll();
        for (CourseDto courseDto : all) {
            JFXButton delete = new JFXButton("Delete");
            delete.setOnAction((e)->{
                try {
                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Delete You Sure?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
                    alert.showAndWait();
                    if (alert.getResult() == ButtonType.YES) {
                        courseBo.delete(courseDto.getId());
                        tableLoad();
                        genarateId();
                    }
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            });
            courseTm.add(new CourseTM(courseDto.getId(),
                    courseDto.getName(), courseDto.getType(), courseDto.getDuration(), delete));
        }
        table.setItems(courseTm);
    }
    void genarateId() throws Exception {
        ArrayList<CourseDto> all = courseBo.getAll();
        if (all.size()==0){
            lblId.setText("CT001");
        }else {
            CourseDto courseDto = courseBo.gernarateId();
            String[] cs = courseDto.getId().split("CT");
            int count = Integer.parseInt(cs[1]);
            if (count >= 99) {
                lblId.setText("CT0" + (count + 1));
            } else {
                lblId.setText("CT00" + (count + 1));
            }
        }
    }
}
