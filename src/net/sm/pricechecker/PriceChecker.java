package net.sm.pricechecker;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.sm.core.configuration.Language;
import net.sm.core.dialog.AlertBuilder;
import net.sm.pricechecker.database.DatabaseH2;
import net.sm.pricechecker.views.primary.PrimaryView;

import java.io.File;
import java.io.IOException;

public class PriceChecker extends Application {

    public final String appDeveloper;
    public final String appName;
    public final String appVersion;

    public Image appIconImage;
    public String appThemeURI;

    private Language language;
    private DatabaseH2 database;
    private AlertBuilder alertBuilder;
    // private TaskManager taskManager;

    //private ScheduledExecutorService scheduler;

    public PriceChecker() {

        this.appDeveloper = "SM-Net";
        this.appName = "PriceChecker";
        this.appVersion = "0.1.0";
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {

        File appIconFile = new File("resources", "icon.png");
        String appIconURI = appIconFile.toURI().toString();
        this.appIconImage = new Image(appIconURI, 100, 100, true, true);

        File appThemeFile = new File("themes", "dark.css");
        this.appThemeURI = appThemeFile.toURI().toString();

        this.alertBuilder = new AlertBuilder(this.getFullTitle(), appThemeURI, "alert_001", appIconURI);

        File appLanguageFile = new File("languages", "it.properties");
        this.language = new Language(appLanguageFile, "language", "<< null >>");

        File appDatabaseFile = new File("database", "database.mv.db");
        this.database = new DatabaseH2(appDatabaseFile, "smnet", "password", true, false);

        // this.taskManager = new TaskManager(this);

//        CheckAllPrice checkAllPriceTask = new CheckAllPrice(this);
//        this.scheduler = Executors.newScheduledThreadPool(1);
//        scheduler.scheduleAtFixedRate(checkAllPriceTask, 1, 30, TimeUnit.MINUTES);

        startPriceCheckerView(primaryStage);
    }

    private void startPriceCheckerView(Stage primaryStage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(PrimaryView.class.getResource("PrimaryView.fxml"));

        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(this.appThemeURI);

        primaryStage.setScene(scene);
        primaryStage.setTitle(this.getFullTitle());
        primaryStage.getIcons().add(appIconImage);

        PrimaryView ctrl = fxmlLoader.getController();
        ctrl.setApplication(this);
        ctrl.setStage(primaryStage);
        ctrl.init();

        primaryStage.show();

        ctrl.categoryTreeViewRefresh();
    }

    public String getFullTitle() {
        return String.format("%s : %s %s", this.appDeveloper, this.appName, this.appVersion);
    }

    public String getAppThemeURI() {
        return appThemeURI;
    }

    public void setAppThemeURI(String appThemeURI) {
        this.appThemeURI = appThemeURI;
    }



    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public DatabaseH2 getDatabase() {
        return database;
    }

    public void setDatabase(DatabaseH2 database) {
        this.database = database;
    }

    public AlertBuilder getAlertBuilder() {
        return alertBuilder;
    }

    public void setAlertBuilder(AlertBuilder alertBuilder) {
        this.alertBuilder = alertBuilder;
    }

    public Image getAppIconImage() {
        return appIconImage;
    }

    public void setAppIconImage(Image appIconImage) {
        this.appIconImage = appIconImage;
    }
}
