package sample;

import domain.Status;
import domain.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import service.Service;


import java.util.ArrayList;
import java.util.List;

public class Controller{

    private ObservableList<Task> model = FXCollections.observableArrayList();
    private Service service = new Service();

    @FXML
    TableView tableView;

    @FXML
    TableColumn tableColumnStatus;

    @FXML
    TableColumn tableColumnDenumire;

    @FXML
    TableColumn tableColumnUtilizator;

    @FXML
    ComboBox<String> statusCombo;

    @FXML
    ComboBox<String> userCombo;


    @FXML
    public void initialize(){
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<Task, String>("status"));
        tableColumnDenumire.setCellValueFactory(new PropertyValueFactory<Task, String>("denumire"));
        tableColumnUtilizator.setCellValueFactory(new PropertyValueFactory<Task, String>("utilizator"));

        tableView.setItems(model);

        statusCombo.getItems().removeAll(statusCombo.getItems());
        statusCombo.getItems().addAll("TODO", "INPROGRESS", "DONE");

        userCombo.getItems().removeAll(userCombo.getItems());
        for(String user: getAllUsers()) {
            userCombo.getItems().addAll(user);
        }
    }

    @FXML
    public void handleFilterByTaskStatus(ActionEvent ev) {
        if (statusCombo.getSelectionModel().getSelectedItem() == null) {
            showMessageError("Nu a fost selectat un status");
        } else {
            model.setAll(service.filterByTaskStatus(convertToStatus(statusCombo.getSelectionModel().getSelectedItem())));
        }
    }

    @FXML
    public void handleFilterByStatusAndUser(ActionEvent ev){
        if(statusCombo.getSelectionModel().getSelectedItem() == null || userCombo.getSelectionModel().getSelectedItem() == null)
        {
            showMessageError("Nu a fost selectat un status sau un user !");
        }
        else{
            model.setAll(service.filterByStatusAndUser(convertToStatus(statusCombo.getSelectionModel().getSelectedItem()),
                    userCombo.getSelectionModel().getSelectedItem()));
        }
    }

    @FXML
    public void handleFilterByUser(ActionEvent ev){
        if(userCombo.getSelectionModel().getSelectedItem() == null){
            showMessageError("Nu a fost selectat un user !");
        }
        else {
            model.setAll(service.filterByUser(userCombo.getSelectionModel().getSelectedItem()));
        }
    }

    @FXML
    public void resetList(ActionEvent ev){
        model.setAll(service.getAll());
    }

    public void setService(Service serv){
        this.service = serv;
        this.model.setAll(this.service.getAll());
    }

    private List<String> getAllUsers(){
        List<String> currentUsers = new ArrayList<>();
        for(Task t: service.getAll()){
            if(!currentUsers.contains(t.getUtilizator())){
                currentUsers.add(t.getUtilizator());
            }
        }
        return currentUsers;
    }

    private Status convertToStatus(String string){
        if(string.equals("TODO"))
            return Status.TODO;
        if(string.equals("INPROGRESS"))
            return Status.INPROGRESS;
        if(string.equals("DONE"))
            return Status.DONE;
        return null;
    }

    private void showMessageError(String string){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(string);
        alert.showAndWait();
    }
}
