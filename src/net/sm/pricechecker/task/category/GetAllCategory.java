package net.sm.pricechecker.task.category;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import net.sm.core.task.TaskInterface;
import net.sm.pricechecker.database.model.CategorySet;
import net.sm.pricechecker.database.model.ProductSet;
import net.sm.pricechecker.views.primary.PrimaryView;

import java.util.ArrayList;
import java.util.HashMap;

public class GetAllCategory implements TaskInterface {

    private PrimaryView view;

    public GetAllCategory(PrimaryView view) {
        this.view = view;
    }

    @Override
    public void start(HashMap<String, Object> hashMap) {

        TreeItem<CategorySet> root = this.view.getRoot();
        TableView<ProductSet> productsTableView = this.view.getProductsTableView();

        root.getChildren().clear();
        productsTableView.getItems().clear();

        ArrayList<CategorySet> categories = this.view.getApplication().getDatabase().getAllCategory();
        categories.forEach(category -> {

            TreeItem<CategorySet> newTi = new TreeItem<>(category);

            int fatherID = category.getFatherID();
            if (fatherID == -1)
                root.getChildren().add(newTi);
            else {
                TreeItem<CategorySet> father = checkChildren(root.getChildren(), fatherID);
                if (father != null)
                    father.getChildren().add(newTi);
                else
                    root.getChildren().add(newTi);
            }
        });
    }

    private TreeItem<CategorySet> checkChildren(ObservableList<TreeItem<CategorySet>> children, int fatherID) {

        for (TreeItem<CategorySet> child : children) {
            if (child.getValue().getId() == fatherID) {
                return child;
            } else {
                ObservableList<TreeItem<CategorySet>> childrenChild = child.getChildren();
                if (childrenChild.size() > 0) {
                    TreeItem<CategorySet> subChild = checkChildren(childrenChild, fatherID);
                    if (subChild != null)
                        return subChild;
                }
            }
        }

        return null;
    }

    @Override
    public void feedback(HashMap<String, Object> hashMap) {

    }
}
