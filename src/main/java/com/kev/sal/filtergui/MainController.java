package com.kev.sal.filtergui;

import com.kev.sal.filtergui.util.objects.GameSettings;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Arrays;

public class MainController {
    // Game Types
    public enum Game {
        SEVENS,
        EIGHTS,
        NINES
    }

    // Game State
    @FXML
    private Text sourceFileName;
    private File sourceFile;

    @FXML
    private ComboBox<Game> gameTypeField;
    @FXML
    private CheckBox hintsField;
    @FXML
    private CheckBox givenField;
    @FXML
    private Label givenEndLabel;
    @FXML
    private CheckBox givenEndField;
    @FXML
    private Label numGivenLabel;
    @FXML
    private ComboBox numGivenField;


    @FXML
    protected void initialize() {
        // Initialize the Game Type ComboBox with the Game enums
        if(this.gameTypeField != null) {
            this.gameTypeField.getItems().addAll(FXCollections.observableList(Arrays.asList(Game.values())));
            this.gameTypeField.setValue(Game.SEVENS);
        }
        // Initialize hints value to be true
        if(this.hintsField != null) {
            this.hintsField.setSelected(true);
        }
        // Initialize num given field and hide it
        if(this.numGivenField != null) {
            this.numGivenLabel.setVisible(false);
            this.numGivenField.setVisible(false);
            this.numGivenField.getItems().addAll(FXCollections.observableList(Arrays.asList(0,1,2)));
            this.numGivenField.setValue(0);
        }
    }

    /**
     * Event listener for choosing a source word list file
     */
    @FXML
    protected void handleSourceFileChooserAction(ActionEvent e) {
        FileChooser fc = new FileChooser();
        // Restrict filetypes to .txt
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fc.getExtensionFilters().add(extFilter);
        Stage stage = (Stage) sourceFileName.getScene().getWindow();
        this.sourceFile = fc.showOpenDialog(stage);
        // Set the label text if a file is chosen
        if(this.sourceFile != null) {
            this.sourceFileName.setText(sourceFile.getName());
        }
    }

    @FXML
    protected void handleHintAction(ActionEvent e) {
        boolean includeHints=hintsField.isSelected();
    }

    @FXML
    protected void handleGivenAction(ActionEvent e) {
        boolean includeGiven=this.givenField.isSelected();
        // Hide Given End row & Num Given row if not selected
        this.givenEndField.setVisible(includeGiven);
        this.givenEndLabel.setVisible(includeGiven);
        this.numGivenLabel.setVisible(includeGiven);
        this.numGivenField.setVisible(includeGiven);

        // Reset Num Given row if not selected
        if(!includeGiven) {
            givenEndField.setSelected(false);
            numGivenField.setValue(0);
        }
    }

    @FXML
    protected void handleExportAction(ActionEvent e) {
        // TODO: Validation?
        // TODO: disable the export button if a file is not selected?
        if(this.sourceFile == null) {
            return;
        }
        // Get the game settings
        GameSettings settings = new GameSettings(this.sourceFile, gameTypeField.getValue(), this.hintsField.isSelected(),
        this.givenEndField.isSelected(), this.givenField.isSelected(), (int) this.numGivenField.getValue());

//        System.out.println("File path: " + sourceFile.getAbsolutePath());
//        System.out.println("Game: " + gameType.name());
//        System.out.println("Hints: " + includeHints);
//        if(includeHints) {
//            System.out.println("Hint Ends: " + includeHintEnds);
//        }
//        System.out.println("Number of given letters: " + numGiven);

        // TODO: do the export logic


    }
}