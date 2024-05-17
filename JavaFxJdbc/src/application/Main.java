package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Etudiant.fxml"));
            Parent root = loader.load();

            ControllerEtudiant controller = loader.getController();
            EtudiantM etudiantModel = new EtudiantM();
            controller.setEtudiantModel(etudiantModel);

            Scene scene = new Scene(root, 600, 400);
            primaryStage.setMinHeight(400);
            primaryStage.setMinWidth(600);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Gestion Etudiants");
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
