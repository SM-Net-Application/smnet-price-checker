package net.sm.core.dialog;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.image.Image;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.util.Optional;

public class AlertBuilder {

    private String title;
    private String themeFileURL;
    private String themeClass;
    private String iconFileURL;

    public AlertBuilder(String title, String themeFileURL, String themeClass, String iconFileURL) {
        this.title = title;
        this.themeFileURL = themeFileURL;
        this.themeClass = themeClass;
        this.iconFileURL = iconFileURL;
    }

    public Alert build(Alert.AlertType type, Stage stage, String title, String header, String content) {

        Alert alert = build(type);

        if (stage != null)
            alert.initOwner(stage);

        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);

        return alert;
    }

    private Alert build(Alert.AlertType type) {

        Alert alert = new Alert(type);

        if (!this.title.isEmpty())
            alert.setTitle(this.title);

        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setMinHeight(Region.USE_PREF_SIZE);

        if (!this.themeFileURL.isEmpty() && !this.themeClass.isEmpty()) {
            dialogPane.getStylesheets().add(this.themeFileURL);
            dialogPane.getStyleClass().add(this.themeClass);
        }

        if (!this.iconFileURL.isEmpty()) {
            Stage alertStage = (Stage) dialogPane.getScene().getWindow();
            alertStage.getIcons().add(new Image(this.iconFileURL));
        }

        return alert;
    }

    public Alert information(Stage stage, String content) {
        return build(Alert.AlertType.INFORMATION, stage, title, null, content);
    }

    public Alert information(Stage stage, String header, String content) {
        return build(Alert.AlertType.INFORMATION, stage, title, header, content);
    }

    public Alert error(Stage stage, String header, String content) {
        return build(Alert.AlertType.ERROR, stage, title, header, content);
    }

    public Alert error(Stage stage, String content) {
        return build(Alert.AlertType.ERROR, stage, title, null, content);
    }

    public boolean confirm(Stage stage, String header, String content) {

        Alert alert = build(Alert.AlertType.CONFIRMATION, stage, this.title, header, content);
        Optional<ButtonType> buttonType = alert.showAndWait();
        if (buttonType.isPresent())
            if (buttonType.get() == ButtonType.OK)
                return true;

        return false;
    }

    public boolean confirm(Stage stage, String content) {
        return confirm(stage, null, content);
    }

    public Alert wait(Stage stage, String content) {
        return wait(stage, null, content);
    }

    public Alert wait(Stage stage, String header, String content) {
        Alert alert = build(Alert.AlertType.NONE, stage, title, header, content);
        alert.setResult(ButtonType.OK);
        return alert;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThemeFileURL() {
        return themeFileURL;
    }

    public void setThemeFileURL(String themeFileURL) {
        this.themeFileURL = themeFileURL;
    }

    public String getThemeClass() {
        return themeClass;
    }

    public void setThemeClass(String themeClass) {
        this.themeClass = themeClass;
    }

    public String getIconFileURL() {
        return iconFileURL;
    }

    public void setIconFileURL(String iconFileURL) {
        this.iconFileURL = iconFileURL;
    }
}
