package application;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ControllerEtudiant {
    
    @FXML
    private Button add_btn;

    @FXML
    private ComboBox<String> choix_filiere;

    @FXML
    private Button delete_btn;

    @FXML
    private Button edit_btn;

    @FXML
    private RadioButton female;

    @FXML
    private Label filiere;

    @FXML
    private AnchorPane form_listeEtudiant;

    @FXML
    private TableView<Etudiant> liste_table;

    @FXML
    private RadioButton male;

    @FXML
    private Label nom;

    @FXML
    private TextField nom_etudiant;

    @FXML
    private Label prenom;

    @FXML
    private TextField prenom_etudiant;
    
    EtudiantM etudiantModel;

    
    @FXML
    public void initialize() {
        ObservableList<String> filieres = FXCollections.observableArrayList(
                "DSI",
                "MDW",
                "RSI"
        );
        choix_filiere.setItems(filieres);

        if (etudiantModel != null) {
            loadEtudiantData();
        } else {
            System.err.println("Etudiant model is null!");
        }
        
        this.add_btn.setOnAction(event -> addEtudiant());
    }

    public void setEtudiantModel(EtudiantM etudiantModel) {
        this.etudiantModel = etudiantModel;
        if (etudiantModel != null) {
            loadEtudiantData();
        } else {
            System.err.println("Etudiant model is null!");
        }
    }

    private void loadEtudiantData() {
    	if (etudiantModel != null) {
    		liste_table.getItems().setAll(etudiantModel.findAll());
        } else {
            System.out.println("Etudiant model is null!");
        }
    }

    @FXML
    private void handleAddButtonAction() {
        if (etudiantModel != null) { 
            Etudiant newEtudiant = new Etudiant();
            boolean success = etudiantModel.create(newEtudiant);
            if (success) {
                loadEtudiantData();
                clearFields();
                liste_table.refresh(); 
            }
        } else {
            System.err.println("Etudiant model is null!");
        }

    }

    @FXML
    private void handleDeleteButtonAction() {
        Etudiant selectedEtudiant = liste_table.getSelectionModel().getSelectedItem();
        if (selectedEtudiant != null) {
            boolean success = etudiantModel.delete(selectedEtudiant);
            if (success) {
                loadEtudiantData();
            }
        }
    }

    @FXML
    private void handleEditButtonAction() {
        Etudiant selectedEtudiant = liste_table.getSelectionModel().getSelectedItem();
        if (selectedEtudiant != null) {
            selectedEtudiant.setNom(nom_etudiant.getText());
            selectedEtudiant.setPrenom(prenom_etudiant.getText());
            selectedEtudiant.setSexe(male.isSelected() ? "Male" : "Female");
            selectedEtudiant.setFiliere(choix_filiere.getValue());

            boolean success = etudiantModel.update(selectedEtudiant);
            if (success) {
                loadEtudiantData();
                clearFields();
            }
        }
    }

    private void clearFields() {
        nom_etudiant.clear();
        prenom_etudiant.clear();
        male.setSelected(true);
        choix_filiere.getSelectionModel().clearSelection();
    }
    
    private void addEtudiant() {
        String nom = nom_etudiant.getText();
        String prenom = prenom_etudiant.getText();
        String filiere = choix_filiere.getValue();
        String sexe = male.isSelected() ? "M" : "F";

        if (nom.isEmpty() || prenom.isEmpty() || filiere == null || sexe == null) {
            showAlert(Alert.AlertType.ERROR, "Form Error!", "Please enter all fields");
            return;
        }

        Etudiant etudiant = new Etudiant(nom, prenom, filiere, sexe);
        etudiantModel.create(etudiant);
        loadEtudiantData();
    }
    
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    
    
}
