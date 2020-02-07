package net.sm.pricechecker.dialog;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import net.sm.pricechecker.PriceChecker;

import java.io.IOException;
import java.util.Optional;

public class DialogText {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label label;
    @FXML
    private TextField textField;

    private PriceChecker application;

    public static String show(PriceChecker application, Stage ownerStage, String labelText) {

        try {

            Dialog<String> dialog = new Dialog<>();
            dialog.setTitle(application.getFullTitle());

            DialogPane dialogPane = dialog.getDialogPane();
            dialogPane.getStylesheets().add(application.getAppThemeURI());
            dialogPane.getStyleClass().add("dialog_001");
            dialogPane.setMinHeight(Region.USE_PREF_SIZE);
            dialogPane.getButtonTypes().add(ButtonType.OK);

            Stage stage = (Stage) dialogPane.getScene().getWindow();
            stage.getIcons().add(application.getAppIconImage());
            stage.initOwner(ownerStage);

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(DialogText.class.getResource("DialogText.fxml"));
            AnchorPane content = fxmlLoader.load();

            DialogText ctrl = fxmlLoader.getController();
            ctrl.setApplication(application);
            ctrl.setLabelText(labelText);

            dialog.getDialogPane().setContent(content);
            dialog.setResultConverter(param -> resultConverter(param, ctrl));

            Optional<String> result = dialog.showAndWait();

            if (result.isPresent())
                return result.get();

        } catch (IOException e) {
            application.getAlertBuilder().error(ownerStage, e.getMessage()).show();
        }

        return null;
    }

    private static String resultConverter(ButtonType param, DialogText ctrl) {

        if (param != null)
            if (param.getButtonData() == ButtonData.OK_DONE)
                if (!ctrl.isEmpty())
                    return ctrl.getText();

        return null;
    }

    @FXML
    private void initialize() {

        initStyleClass();
    }

    private void initStyleClass() {

        anchorPane.getStyleClass().add("main_color_001");

        label.getStyleClass().add("label_001");

        textField.getStyleClass().add("text_field_001");
    }

    private void setLabelText(String text) {
        this.label.setText(text);
    }

    public boolean isEmpty() {
        return textField.getText().trim().isEmpty();
    }

    private void setURLLabelText(String text) {
        this.label.setText(text);
    }

    private String getText() {
        return this.textField.getText();
    }

    public PriceChecker getApplication() {
        return application;
    }

    public void setApplication(PriceChecker application) {
        this.application = application;
    }
}