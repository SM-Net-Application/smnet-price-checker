package net.sm.pricechecker.task.product;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import net.sm.core.task.TaskInterface;
import net.sm.pricechecker.database.model.CategorySet;
import net.sm.pricechecker.database.model.ProductSet;
import net.sm.pricechecker.views.primary.PrimaryView;

import java.util.HashMap;

public class GetProductByCategory implements TaskInterface {

    private PrimaryView view;
    private CategorySet selectedCategory;

    public GetProductByCategory(PrimaryView view, CategorySet selectedCategory) {
        this.view = view;
        this.selectedCategory = selectedCategory;
    }

    @Override
    public void start(HashMap<String, Object> hashMap) {

        TableView<ProductSet> productsTableView = this.view.getProductsTableView();
        productsTableView.getItems().clear();

        ObservableList<ProductSet> products = this.view.getApplication().getDatabase().getAllProductByCategory(this.selectedCategory.getId());

        hashMap.put("products", products);
    }

    @Override
    public void feedback(HashMap<String, Object> hashMap) {

        ObservableList<ProductSet> products = (ObservableList<ProductSet>) hashMap.get("products");

        TableView<ProductSet> productsTableView = this.view.getProductsTableView();
        productsTableView.setItems(products);
        productsTableView.refresh();
    }
}
