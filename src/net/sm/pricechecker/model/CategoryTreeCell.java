package net.sm.pricechecker.model;

import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.sm.pricechecker.PriceChecker;
import net.sm.pricechecker.database.model.CategorySet;
import net.sm.pricechecker.dialog.DialogText;
import net.sm.pricechecker.views.primary.PrimaryView;

import java.io.File;

public class CategoryTreeCell extends TreeCell<CategorySet> {

    private PriceChecker application;
    private PrimaryView view;
    private ContextMenu cm;
    private Stage ownerStage;

    public CategoryTreeCell(PriceChecker application, PrimaryView view, Stage ownerStage) {
        super();

        this.application = application;
        this.ownerStage = ownerStage;
        this.cm = new ContextMenu();
    }

    private void dialogSubCategory(TreeItem<CategorySet> ti) {

        CategorySet father = ti.getValue();
        int fatherID = father.getId();

        String subCategoryName = DialogText.show(this.application, this.ownerStage, this.application.getLanguage().getString("dialog.text.subcategory"));
        if (subCategoryName != null)
            if (!subCategoryName.isEmpty()) {
                this.application.getDatabase().insertCategory(new CategorySet(subCategoryName, fatherID));
                this.view.categoryTreeViewRefresh();
            }
    }

    // TODO : Eliminare la categoria e i prodotti presenti (chiedere conferma)
    private void deleteSubCategory(TreeItem<CategorySet> ti) {

//        Category category = ti.getValue();
//        this.application.getDatabase().deleteCategory(category);
//        this.view.refreshCategory();
    }

    @Override
    protected void updateItem(CategorySet item, boolean empty) {
        super.updateItem(item, empty);

        if (!empty) {

            createItemContextMenu(item);

            setText(item.getName());
            setGraphic(null);
            setContextMenu(cm);

        } else {

            setText(null);
            setGraphic(null);
            setContextMenu(null);
        }
    }

    private void createItemContextMenu(CategorySet item) {

        String addTextLang = this.application.getLanguage().getString("tree.subcategory.new");

        String removeTextPattern = "%s: %s";
        String removeTextLang = this.application.getLanguage().getString("tree.category.delete");
        String removeText = String.format(removeTextPattern, removeTextLang, item.getName());

        MenuItem subCategoryAdd = new MenuItem(addTextLang);
        subCategoryAdd.setOnAction(event -> dialogSubCategory(this.getTreeItem()));
        subCategoryAdd
                .setGraphic(new ImageView(new Image(new File("resources", "category-add.png").toURI().toString(), 25, 25, true, true)));

        MenuItem subCategoryDelete = new MenuItem(removeText);
        subCategoryDelete.setOnAction(event -> deleteSubCategory(this.getTreeItem()));
        subCategoryDelete
                .setGraphic(new ImageView(new Image(new File("resources", "category-delete.png").toURI().toString(), 25, 25, true, true)));

        this.cm.getItems().clear();
        this.cm.getItems().addAll(subCategoryAdd, subCategoryDelete);
    }

    public PriceChecker getApplication() {
        return application;
    }

    public void setApplication(PriceChecker application) {
        this.application = application;
    }

    public ContextMenu getCm() {
        return cm;
    }

    public void setCm(ContextMenu cm) {
        this.cm = cm;
    }

    public PrimaryView getView() {
        return view;
    }

    public void setView(PrimaryView view) {
        this.view = view;
    }

    public void setOwnerStage(Stage ownerStage) {
        this.ownerStage = ownerStage;
    }
}
