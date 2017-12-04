package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;

public class Main extends Application {

    Service service = new Service();

    @Override
    public void start(Stage primaryStage){
       FXMLLoader loader = new FXMLLoader();
       loader.setLocation(Main.class.getResource("/sample/sample.fxml"));
       AnchorPane rootLayout = null;
       try{
           rootLayout = (AnchorPane)loader.load();
           Controller rootController = loader.getController();
           rootController.setService(service);
           primaryStage.setScene(new Scene(rootLayout, 700,400));
           primaryStage.setTitle("Tasks");
           primaryStage.show();
       }catch (IOException e){
           e.printStackTrace();
       }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
