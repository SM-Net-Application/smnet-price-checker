package net.sm.pricechecker.views.product;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.sm.core.configuration.Language;
import net.sm.pricechecker.PriceChecker;
import net.sm.pricechecker.data.Check;
import net.sm.pricechecker.database.model.ProductSet;
import net.sm.pricechecker.views.primary.PrimaryView;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

public class ProductEditor {

    @FXML
    private ImageView imageView;
    @FXML
    private Label codeLabel;
    @FXML
    private TextField codeTextField;
    @FXML
    private Label nameLabel;
    @FXML
    private TextField nameTextField;
    @FXML
    private Label linkLabel;
    @FXML
    private TextField linkTextField;
    @FXML
    private Label linkImageLabel;
    @FXML
    private TextField linkImageTextField;
    @FXML
    private Label priceLabel;
    @FXML
    private TextField priceTextField;
    @FXML
    private Button checkNowButton;

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab analysisTab;
    @FXML
    private Label analysisLabel;
    @FXML
    private TableView<Check> priceCheckTableView;
    @FXML
    private TableColumn<Check, LocalDateTime> dateTableColumn;
    @FXML
    private TableColumn<Check, BigDecimal> priceTableColumn;

    @FXML
    private Label priceHighLabel;
    @FXML
    private TextField priceHighTextField;
    @FXML
    private TextField priceHighDateTextField;
    @FXML
    private Label priceLowLabel;
    @FXML
    private TextField priceLowTextField;
    @FXML
    private TextField priceLowDateTextField;
    @FXML
    private Label priceAverageLabel;
    @FXML
    private TextField priceAverageTextField;
    @FXML
    private Label priceStatusLabel;
    @FXML
    private Button openInBrowserButton;

    private TabPane ownerTabPane;
    private Tab thisTab;
    private ProductSet selectedProduct;
    private File imagePath;
    private Language language;
    private PriceChecker application;
    private Stage ownerStage;
    private PrimaryView ownerView;
    private ObservableList<Check> priceCheck;

    @FXML
    private void initialize() {

        this.codeLabel.getStyleClass().add("label_001");
        this.nameLabel.getStyleClass().add("label_001");
        this.linkLabel.getStyleClass().add("label_001");
        this.linkImageLabel.getStyleClass().add("label_001");
        this.priceLabel.getStyleClass().add("label_001");

        this.codeTextField.getStyleClass().add("text_field_001");
        this.nameTextField.getStyleClass().add("text_field_001");
        this.linkTextField.getStyleClass().add("text_field_001");
        this.linkImageTextField.getStyleClass().add("text_field_001");
        this.priceTextField.getStyleClass().add("text_field_001");

        this.checkNowButton.getStyleClass().add("button_image_001");
        this.openInBrowserButton.getStyleClass().add("button_image_001");

        this.tabPane.getStyleClass().add("tab_pane_001");
        this.analysisTab.getStyleClass().add("tab_001");
        this.analysisLabel.getStyleClass().add("label_001");
        this.priceCheckTableView.getStyleClass().add("table_view_001");

        this.priceHighLabel.getStyleClass().add("label_set_001");
        this.priceHighTextField.getStyleClass().add("text_field_001");
        this.priceHighDateTextField.getStyleClass().add("text_field_001");
        this.priceLowLabel.getStyleClass().add("label_set_001");
        this.priceLowTextField.getStyleClass().add("text_field_001");
        this.priceLowDateTextField.getStyleClass().add("text_field_001");
        this.priceAverageLabel.getStyleClass().add("label_set_001");
        this.priceAverageTextField.getStyleClass().add("text_field_001");

        this.priceStatusLabel.getStyleClass().add("label_001");
    }

    public void initView() {

        this.dateTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCheckTime()));
        this.priceTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrice()));

        this.codeTextField.setEditable(false);
        this.linkTextField.setEditable(false);
        this.linkImageTextField.setEditable(false);
        this.priceTextField.setEditable(false);

        this.dateTableColumn.setMinWidth(200.00);
        this.priceTableColumn.setMinWidth(200.00);

        initText();
        initData();
        initImage();
        initListeners();
    }


    private void initText() {

        this.codeLabel.setText(this.language.getString("view.producteditor.code"));
        this.nameLabel.setText(this.language.getString("view.producteditor.name"));
        this.linkLabel.setText(this.language.getString("view.producteditor.link"));
        this.linkImageLabel.setText(this.language.getString("view.producteditor.imagelink"));
        this.priceLabel.setText(this.language.getString("view.producteditor.lastprice"));
        this.checkNowButton.setText(this.language.getString("view.producteditor.checknow"));
        this.openInBrowserButton.setText(this.language.getString("view.producteditor.openinbrowser"));

        this.analysisTab.setText(this.language.getString("view.producteditor.analysistab"));
        this.analysisLabel.setText(this.language.getString("view.producteditor.analysislabel"));
        this.dateTableColumn.setText(this.language.getString("view.producteditor.column.date"));
        this.priceTableColumn.setText(this.language.getString("view.producteditor.column.price"));

        this.priceStatusLabel.setText(this.language.getString("view.producteditor.price.title"));
        this.priceHighLabel.setText(this.language.getString("view.producteditor.price.high"));
        this.priceLowLabel.setText(this.language.getString("view.producteditor.price.low"));
        this.priceAverageLabel.setText(this.language.getString("view.producteditor.price.average"));

        this.priceCheck = FXCollections.observableArrayList();
        refreshPriceCheckTable();
    }

    private void refreshPriceCheckTable() {

//        this.priceCheck = this.application.getDatabase().getAllCheck(this.selectedProduct);
//        this.priceCheckTableView.setItems(this.priceCheck);
//        this.priceCheckTableView.refresh();
//
//        updatePriceStatus();
    }

    private void updatePriceStatus() {

        BigDecimal low = BigDecimal.ZERO;
        LocalDateTime lowTime = null;
        BigDecimal high = BigDecimal.ZERO;
        LocalDateTime highTime = null;
        BigDecimal average = BigDecimal.ZERO;

        boolean first = true;
        for (Check p : this.priceCheck) {

            BigDecimal price = p.getPrice();
            LocalDateTime time = p.getCheckTime();

            average = average.add(price);

            if (first) {
                low = price;
                lowTime = time;
                high = price;
                highTime = time;
                first = false;
            }

            if (price.compareTo(low) < 0) {
                low = price;
                lowTime = time;
            }

            if (price.compareTo(high) > 0) {
                high = price;
                highTime = time;
            }
        }

        int size = this.priceCheck.size();
        if (size > 0)
            average = average.divide(BigDecimal.valueOf(size), 2, RoundingMode.HALF_UP);

        this.priceLowTextField.setText(low.toString());
        if (lowTime != null)
            this.priceLowDateTextField.setText(lowTime.toString());
        this.priceHighTextField.setText(high.toString());
        if (highTime != null)
            this.priceHighDateTextField.setText(highTime.toString());
        this.priceAverageTextField.setText(average.toString());
    }

    private void initData() {

        this.codeTextField.setText(this.selectedProduct.getCode());
        this.nameTextField.setText(this.selectedProduct.getName());
        //this.linkTextField.setText(this.selectedProduct.getLink());
        //this.linkImageTextField.setText(this.selectedProduct.getImageLink());
        //this.priceTextField.setText(this.selectedProduct.getLastCheck().getPrice().toString());
    }

    private void initListeners() {

        this.checkNowButton.setOnAction(event -> checkNow());
        this.openInBrowserButton.setOnAction(event -> openInBrowser());
    }

    private void openInBrowser() {

        try {

            URI uri = new URI(this.selectedProduct.getUrl());
            openInBrowser(uri);

        } catch (URISyntaxException e) {
            System.out.println(e.getMessage());
        }
    }

    private void openInBrowser(URI uri) {

        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null)
            if (desktop.isSupported(Desktop.Action.BROWSE))
                try {
                    desktop.browse(uri);
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
    }

    private void initImage() {
        try {
            this.imageView.setImage(new Image(this.imagePath.toURI().toURL().toString(), 400, 400, true, true));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    private void checkNow() {

//        if (this.application.getAlertBuilder().confirm(this.ownerStage, language.getString("view.producteditor.checknowconfirm"))) {
//
//            EnumStore store = EnumStore.getStoreFromID(selectedProduct.getSellerCode());
//            switch (store) {
//                case AMAZON:
//                    AmazonUtil.checkPrice(this.ownerView, this.ownerStage, this.application, selectedProduct);
//                    break;
//            }
//            refreshPriceCheckTable();
//        }
    }

    public ProductSet getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductSet selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public File getImagePath() {
        return imagePath;
    }

    public void setImagePath(File imagePath) {
        this.imagePath = imagePath;
    }

    public TabPane getOwnerTabPane() {
        return ownerTabPane;
    }

    public void setOwnerTabPane(TabPane ownerTabPane) {
        this.ownerTabPane = ownerTabPane;
    }

    public Tab getThisTab() {
        return thisTab;
    }

    public void setThisTab(Tab thisTab) {
        this.thisTab = thisTab;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public PriceChecker getApplication() {
        return application;
    }

    public void setApplication(PriceChecker application) {
        this.application = application;
    }

    public Stage getOwnerStage() {
        return ownerStage;
    }

    public void setOwnerStage(Stage ownerStage) {
        this.ownerStage = ownerStage;
    }

    public PrimaryView getOwnerView() {
        return ownerView;
    }

    public void setOwnerView(PrimaryView ownerView) {
        this.ownerView = ownerView;
    }
}
