package net.sm.pricechecker.views.primary;

import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.sm.core.dialog.AlertBuilder;
import net.sm.core.task.TaskManager;
import net.sm.pricechecker.PriceChecker;
import net.sm.pricechecker.model.CategoryTreeCell;
import net.sm.pricechecker.database.model.CategorySet;
import net.sm.pricechecker.database.model.ProductSet;
import net.sm.pricechecker.dialog.DialogText;
import net.sm.pricechecker.task.category.GetAllCategory;
import net.sm.pricechecker.task.product.GetProductByCategory;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PrimaryView {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private Label categoriesLabel;
    @FXML
    private Label productsLabel;
    @FXML
    private TreeView<CategorySet> categoriesTreeView;
    @FXML
    private TabPane productsTabPane;
    @FXML
    private Tab productsTab;
    @FXML
    private TableView<ProductSet> productsTableView;
    @FXML
    private TableColumn<ProductSet, ImageView> productImageTableColumn;
    @FXML
    private TableColumn<ProductSet, String> productNameTableColumn;
    @FXML
    private TableColumn<ProductSet, LocalDateTime> productLastUpdateTableColumn;
    @FXML
    private TableColumn<ProductSet, BigDecimal> productPriceTableColumn;
    @FXML
    private Button addButton;

    private PriceChecker application;
    private Stage stage;

    private TreeItem<CategorySet> root;

    @FXML
    private void initialize() {

        initStyleClass();
        initCellValueFactory();
    }

    private void initStyleClass() {

        anchorPane.getStyleClass().add("main_color_001");

        categoriesLabel.getStyleClass().add("label_header_001");
        productsLabel.getStyleClass().add("label_header_001");

        categoriesTreeView.getStyleClass().add("tree_view_001");

        productsTabPane.getStyleClass().add("tab_pane_001");

        productsTab.getStyleClass().add("tab_001");

        productsTableView.getStyleClass().add("table_view_001");

        productImageTableColumn.getStyleClass().add("table_column_002");
        productNameTableColumn.getStyleClass().add("table_column_001");
        productLastUpdateTableColumn.getStyleClass().add("table_column_002");
        productPriceTableColumn.getStyleClass().add("table_column_002");

        addButton.getStyleClass().add("button_image_001");
    }

    private void initCellValueFactory() {

        categoriesTreeView.setCellFactory(param -> new CategoryTreeCell(this.application, this, this.stage));

        //productImageTableColumn.setCellValueFactory(cellData -> productImageProperty(cellData.getValue()));
        productNameTableColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        //productLastUpdateTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDateTime>(cellData.getValue().getLastCheck().getCheckTime()));
        //productPriceTableColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<BigDecimal>(cellData.getValue().getLastCheck().getPrice()));
    }

//    private ObjectProperty<ImageView> productImageProperty(Product value) {
//
//        File imagePath = PhotosUtil.defineImagePath(new File("photos"), value.getCode(), value.getSellerCode(),
//                value.getCountryCode());
//
//        Image image = null;
//        if (imagePath != null)
//            try {
//                image = new Image(imagePath.toURI().toURL().toString(), 100, 100, true, true);
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//        else
//            image = this.application.appIconImage;
//
//        return new SimpleObjectProperty<ImageView>(new ImageView(image));
//    }


    public void init() {

        initText();
        initProperties();
        initListeners();

        categoryTreeViewContextMenu(root);
    }

    private void initText() {

        this.categoriesLabel.setText(this.application.getLanguage().getString("view.main.category"));
        this.productsLabel.setText(this.application.getLanguage().getString("view.main.product"));
        this.productsTab.setText(this.application.getLanguage().getString("view.main.tabs.products"));
        this.productImageTableColumn.setText("");
        this.productNameTableColumn.setText(this.application.getLanguage().getString("view.main.table.name"));
        this.productLastUpdateTableColumn.setText(this.application.getLanguage().getString("view.main.table.lastupdate"));
        this.productPriceTableColumn.setText(this.application.getLanguage().getString("view.main.table.price"));
        this.addButton.setText(this.application.getLanguage().getString("view.main.addproduct"));
    }

    private void initProperties() {

        this.productsTabPane.setTabClosingPolicy(TabClosingPolicy.ALL_TABS);

        this.productsTab.setClosable(false);

        this.productImageTableColumn.setMinWidth(150);
        this.productImageTableColumn.setMaxWidth(150);
        this.productImageTableColumn.setResizable(false);
        this.productImageTableColumn.setSortable(false);

        this.productLastUpdateTableColumn.setMinWidth(200);
        this.productLastUpdateTableColumn.setMaxWidth(200);
        this.productLastUpdateTableColumn.setResizable(false);

        this.productPriceTableColumn.setMinWidth(100);
        this.productPriceTableColumn.setMaxWidth(100);
        this.productPriceTableColumn.setResizable(false);

        this.root = new TreeItem<>();
        this.root.setExpanded(true);

        this.categoriesTreeView.setShowRoot(false);
        this.categoriesTreeView.setRoot(root);
    }


    private void initListeners() {

        // this.addButton.setOnAction(event -> dialogURL());

        this.categoriesTreeView.getSelectionModel().selectedIndexProperty()
                .addListener((observable, oldValue, newValue) -> productTableViewRefresh(newValue.intValue()));

        this.productsTableView.setRowFactory(param -> {
            TableRow<ProductSet> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty()))
                    editProduct(row.getItem());
            });
            return row;
        });
    }

    private void editProduct(ProductSet item) {

//        if (!isAlreadyOpen(item.getName()))
//            try {
//
//                FXMLLoader fxmlLoader = new FXMLLoader();
//                fxmlLoader.setLocation(PrimaryView.class.getResource("ProductEditor.fxml"));
//                AnchorPane layout = (AnchorPane) fxmlLoader.load();
//
//                ProductEditor ctrl = (ProductEditor) fxmlLoader.getController();
//                ctrl.setSelectedProduct(item);
//                ctrl.setApplication(this.application);
//                ctrl.setOwnerStage(this.stage);
//                //ctrl.setOwnerView(this);
//
//                Tab newTab = new Tab(item.getName(), layout);
//                newTab.setClosable(true);
//                newTab.getStyleClass().add("tab_001");
//
////                File imagePath = PhotosUtil.defineImagePath(new File("photos"), item);
////                if (imagePath != null) {
////                    ctrl.setImagePath(imagePath);
////                    newTab.setGraphic(
////                            new ImageView(new Image(imagePath.toURI().toURL().toString(), 40, 40, true, true)));
////                }
//
//                ctrl.setOwnerTabPane(productsTabPane);
//                ctrl.setThisTab(newTab);
//                ctrl.initView();
//
//                this.productsTabPane.getTabs().add(newTab);
//                this.productsTabPane.getSelectionModel().select(newTab);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
    }

//    private boolean isAlreadyOpen(String label) {
//
//        for (Tab tab : productsTabPane.getTabs())
//            if (tab.getText().equals(label)) {
//                productsTabPane.getSelectionModel().select(tab);
//                return true;
//            }
//
//        return false;
//    }

    public void productTableViewRefresh() {
        productTableViewRefresh(this.categoriesTreeView.getSelectionModel().getSelectedIndex());
    }

    public void productTableViewRefresh(int selectedCategoryIndex) {

        if (selectedCategoryIndex > -1) {

            TreeItem<CategorySet> selectedCategoryTreeItem = this.categoriesTreeView.getSelectionModel().getSelectedItem();
            if (selectedCategoryTreeItem != null) {

                CategorySet selectedCategory = selectedCategoryTreeItem.getValue();
                if (selectedCategory != null) {

                    AlertBuilder alertBuilder = this.application.getAlertBuilder();
                    String waitMessage = this.application.getLanguage().getString("task.product.load.by.category");
                    waitMessage = String.format(waitMessage, selectedCategory.getName());

                    TaskManager.run(alertBuilder, this.stage, waitMessage, new GetProductByCategory(this, selectedCategory));
                }
            }
        } else
            this.productsTableView.getItems().clear();

    }


    // TODO: anche la verifica dell'URL è un Task
//    private void dialogURL() {
//
//        if (this.categoriesTreeView.getSelectionModel().getSelectedIndex() > -1) {
//
//            String url = DialogText.show(this.application, this.stage, this.application.getLanguage().getString("dialog.text.url"));
//            if (url != null)
//                checkURL(url);
//            else
//                this.application.getAlertBuilder().error(this.stage, this.application.getLanguage().getString("error.url1")).show();
//        } else
//            this.application.getAlertBuilder().error(this.stage, this.application.getLanguage().getString("error.category1")).show();
//    }
//
//    private void checkURL(String url) {
//
//        EnumStores store = EnumStores.getStoreByURL(url);
//        if (store != null)
//            checkPageSource(url, store);
//        else
//            this.application.getAlertBuilder().error(this.stage, this.application.getLanguage().getString("error.store1")).show();
//    }

    // TODO: La connessione deve essere eseguita da un Task

//    private Document connect(String url) {
//
//        String userAgent = "Firefox/63.0.1";
//
//        try {
//
//            Connection connection = Jsoup.connect(url);
//            connection.userAgent(userAgent);
//            Document document = connection.get();
//
//            if (document != null) {
//                if (!document.head().text().contains("Bot Check"))
//                    return document;
//                else
//                    this.application.getAlertBuilder().error(this.stage, this.application.getLanguage().getString("error.botcheck")).show();
//            } else
//                this.application.getAlertBuilder().error(this.stage, this.application.getLanguage().getString("error.pagesource1")).show();
//
//        } catch (IOException e) {
//            this.application.getAlertBuilder().error(this.stage, this.application.getLanguage().getString("error.connection1"), e.getMessage()).show();
//        }
//
//        return null;
//    }

    private void categoryTreeViewContextMenu(TreeItem<CategorySet> root) {

        MenuItem categoryAdd = new MenuItem(this.application.getLanguage().getString("tree.category.new"));

        String categoryAddImageURI = new File("resources", "category-add.png").toURI().toString();
        Image categoryAddImage = new Image(categoryAddImageURI, 25, 25, true, true);
        categoryAdd.setGraphic(new ImageView(categoryAddImage));

        categoryAdd.setOnAction(event -> dialogCategory(root));

        ContextMenu cm = new ContextMenu(categoryAdd);
        cm.getStyleClass().add("context_menu_001");

        this.categoriesTreeView.setContextMenu(cm);
    }

    private void dialogCategory(TreeItem<CategorySet> ti) {

        String categoryName = DialogText.show(this.application, this.stage, this.application.getLanguage().getString("dialog.text.category"));
        if (categoryName != null)
            if (!categoryName.isEmpty()) {
                this.application.getDatabase().insertCategory(new CategorySet(categoryName));
                this.categoryTreeViewRefresh();
            }
    }

    public void categoryTreeViewRefresh() {

        AlertBuilder alertBuilder = this.application.getAlertBuilder();
        String string = this.application.getLanguage().getString("task.category.load.all");
        TaskManager.run(alertBuilder, this.stage, string, new GetAllCategory(this));
    }

    public PriceChecker getApplication() {
        return application;
    }

    public void setApplication(PriceChecker application) {
        this.application = application;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public TreeItem<CategorySet> getRoot() {
        return root;
    }

    public void setRoot(TreeItem<CategorySet> root) {
        this.root = root;
    }

    public TableView<ProductSet> getProductsTableView() {
        return productsTableView;
    }

    public void setProductsTableView(TableView<ProductSet> productsTableView) {
        this.productsTableView = productsTableView;
    }
}
