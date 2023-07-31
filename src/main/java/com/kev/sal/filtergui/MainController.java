package com.kev.sal.filtergui;

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
    private Game gameType;
    @FXML
    private CheckBox hintsField;
    private boolean includeHints;
    @FXML
    private Label hintEndLabel;
    @FXML
    private CheckBox hintEndField;
    private boolean includeHintEnds;
    @FXML
    private CheckBox givenField;
    private boolean includeGivenLetters;
    @FXML
    private Label numGivenLabel;
    @FXML
    private ComboBox numGivenField;
    private int numGivenLetters;

    private String targetFileName;
    private String incompatibleFileName;

    @FXML
    protected void initialize() {
        // Initialize the Game Type ComboBox with the Game enums
        if(this.gameTypeField != null) {
            this.gameTypeField.getItems().addAll(FXCollections.observableList(Arrays.asList(Game.values())));
            this.gameTypeField.setValue(Game.SEVENS);
        }
        // Initialize hints value to be true
        this.includeHints=true;
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
        // TODO: Restrict filetypes to .txt files
        FileChooser fc = new FileChooser();
        Stage stage = (Stage) sourceFileName.getScene().getWindow();
        this.sourceFile = fc.showOpenDialog(stage);
        // Set the label text if a file is chosen
        if(this.sourceFile != null) {
            this.sourceFileName.setText(sourceFile.getName());
            final int EXT_INDEX = sourceFile.getName().lastIndexOf(".");
            // Set the target file name and incompatible target name
            if(EXT_INDEX > 0) {
                this.targetFileName=sourceFile.getName().substring(0, EXT_INDEX) + "_converted.json";
                this.incompatibleFileName=sourceFile.getName().substring(0, EXT_INDEX) + "_incompatible.txt";
            }
        }
    }

    @FXML
    protected void handleGameTypeAction(ActionEvent e) {
        this.gameType=gameTypeField.getValue();
    }

    @FXML
    protected void handleHintAction(ActionEvent e) {
        this.includeHints=hintsField.isSelected();
        // Hide Hint End row if not selected
        this.hintEndField.setVisible(includeHints);
        this.hintEndLabel.setVisible(includeHints);
        // Reset Hint End row if not selected
        if(!includeHints) {
            hintEndField.setSelected(false);
            this.includeHintEnds=false;
        }
    }

    @FXML
    protected void handleHintEndAction(ActionEvent e) {
        this.includeHintEnds=this.includeHints && this.hintEndField.isSelected();
    }

    @FXML
    protected void handleGivenAction(ActionEvent e) {
        this.includeGivenLetters=this.givenField.isSelected();
        // TODO: Toggle the given thing checkbox
        this.numGivenLabel.setVisible(includeGivenLetters);
        this.numGivenField.setVisible(includeGivenLetters);
        // Reset Num Given row if not selected
        if(!includeGivenLetters) {
            numGivenField.setValue(0);
            this.numGivenLetters=0;
        }
    }

    @FXML
    protected void handleNumGivenAction(ActionEvent e) {
        this.numGivenLetters= (int) numGivenField.getValue();
    }

    @FXML
    protected void handleExportAction(ActionEvent e) {
        // TODO: Validation
        // TODO: Take in all the settings, and then do the export logic
    }
}