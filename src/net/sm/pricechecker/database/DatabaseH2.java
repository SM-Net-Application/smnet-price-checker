package net.sm.pricechecker.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sm.core.configuration.PropertiesReader;
import net.sm.core.database.H2DatabaseEngine;
import net.sm.core.database.QueryResult;
import net.sm.pricechecker.database.model.CategorySet;
import net.sm.pricechecker.database.model.ProductSet;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseH2 extends H2DatabaseEngine {

    private PropertiesReader queryGeneral;
    private PropertiesReader queryTableCategory;
    private PropertiesReader queryTableProduct;
    private PropertiesReader queryTablePriceChange;

    public DatabaseH2(File database, String username, String password, Boolean autoServer, Boolean ifExists) throws IOException {
        super(database, username, password, autoServer, ifExists);

        this.queryGeneral = new PropertiesReader(new File("sql", "general.properties"), "");
        this.queryTableCategory = new PropertiesReader(new File("sql", "category.properties"), "");
        this.queryTableProduct = new PropertiesReader(new File("sql", "product.properties"), "");
        this.queryTablePriceChange = new PropertiesReader(new File("sql", "pricechange.properties"), "");
    }

    public PropertiesReader getQueryGeneral() {
        return queryGeneral;
    }

    public void setQueryGeneral(PropertiesReader queryGeneral) {
        this.queryGeneral = queryGeneral;
    }

    public PropertiesReader getQueryTableCategory() {
        return queryTableCategory;
    }

    public void setQueryTableCategory(PropertiesReader queryTableCategory) {
        this.queryTableCategory = queryTableCategory;
    }

    public PropertiesReader getQueryTableProduct() {
        return queryTableProduct;
    }

    public void setQueryTableProduct(PropertiesReader queryTableProduct) {
        this.queryTableProduct = queryTableProduct;
    }

    public PropertiesReader getQueryTablePriceChange() {
        return queryTablePriceChange;
    }

    public void setQueryTablePriceChange(PropertiesReader queryTablePriceChange) {
        this.queryTablePriceChange = queryTablePriceChange;
    }

    //    public ProductSet getProductByCode(String productCode) {
//
//        ProductSet productSet = null;
//
//        try {
//
//            String query = "SELECT " +
//                    " ID, CATEGORY, SELLER, COUNTRY, CODE, NAME, LINK, IMAGELINK" +
//                    " FROM PRICECHECKER.PRODUCT" +
//                    " WHERE CODE='%s'";
//
//            QueryResult queryResult = this.executeQuery(this.getConnection(), String.format(query, productCode));
//            ResultSet resultSet = queryResult.getResultSet();
//            if (resultSet.next()) {
//
//                int id = resultSet.getInt("ID");
//                int category = resultSet.getInt("CATEGORY");
//                int seller = resultSet.getInt("SELLER");
//                String code = resultSet.getString("CODE");
//                String name = resultSet.getString("NAME");
//                String imageURL = resultSet.getString("IMAGELINK");
//
//                productSet = new ProductSet(id, category, seller, code, name, imageURL);
//            }
//
//            queryResult.close();
//
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//
//        return productSet;
//    }
//
//    public void insertProduct(ProductInfo productInfo) {
//
//        String query = "INSERT INTO PRICECHECKER.PRODUCT" +
//                " (CATEGORY, SELLER, COUNTRY, CODE, NAME, LINK, IMAGELINK)" +
//                " VALUES (%d, %d, %d, '%s', '%s', '%s', '%s');";
//
//        Integer categoryID = productInfo.getCategoryID();
//        int seller = productInfo.getStore().getOrdinal();
//        int country = -1;
//        String code = productInfo.getProductCode();
//        String name = productInfo.getName();
//        String url = String.format(productInfo.getStore().getPatternClearURL(), code);
//        String imageURL = productInfo.getImageURL();
//
//        try {
//            this.runQuery(this.getConnection(), String.format(query, categoryID, seller, country, code, name, url, imageURL));
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
//    }
//
//    private String escape(String name) {
//        return name;
//    }
//
    public ArrayList<CategorySet> getAllCategory() {

        ArrayList<CategorySet> list = new ArrayList<>();

        try {

            String query = this.queryTableCategory.getString("GET_ALL_CATEGORY");

            QueryResult queryResult = this.executeQuery(this.getConnection(), query);
            ResultSet resultSet = queryResult.getResultSet();

            while (resultSet.next()) {

                int id = resultSet.getInt("ID");
                int father = resultSet.getInt("FATHER");
                String name = resultSet.getString("NAME");

                list.add(new CategorySet(id, name, father));
            }

            queryResult.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public ObservableList<ProductSet> getAllProductByCategory(int categoryID) {

        ObservableList<ProductSet> list = FXCollections.observableArrayList();

        try {

            String query = this.queryTableProduct.getString("GET_PRODUCT_BY_CATEGORY");
            query = String.format(query, categoryID);

            QueryResult queryResult = this.executeQuery(this.getConnection(), query);
            ResultSet resultSet = queryResult.getResultSet();
            while (resultSet.next()) {

                int id = resultSet.getInt("ID");
                String code = resultSet.getString("CODE");
                String name = resultSet.getString("NAME");
                int category = resultSet.getInt("CATEGORY");
                int seller = resultSet.getInt("SELLER");
                String url = resultSet.getString("URL");
                String imageURL = resultSet.getString("IMAGEURL");

                ProductSet product = new ProductSet(id, code, name, category, seller, url, imageURL);
                list.add(product);
            }
            queryResult.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    //
//    public ObservableList<Product> getAllProducts() {
//
//        ObservableList<Product> list = FXCollections.observableArrayList();
//
//        try {
//            QueryResult queryResult = this.executeQuery(this.getConnection(), String.format(Query.GET_ALL_PRODUCTS));
//            ResultSet resultSet = queryResult.getResultSet();
//            while (resultSet.next()) {
//
//                int id = resultSet.getInt("ID");
//                int category = resultSet.getInt("CATEGORY");
//                int seller = resultSet.getInt("SELLER");
//                int country = resultSet.getInt("COUNTRY");
//                String code = resultSet.getString("CODE");
//                String name = resultSet.getString("NAME");
//                String link = resultSet.getString("LINK");
//                String imageLink = resultSet.getString("IMAGELINK");
//
//                Product product = new Product(id, seller, country, category, code, name, link, imageLink);
//                product.setLastCheck(this.getLastCheck(product));
//                list.add(product);
//            }
//            queryResult.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
//
    public void insertCategory(CategorySet category) {

        if (category != null) {

            int fatherID = category.getFatherID();
            String name = category.getName();

            String format = this.queryTableCategory.getString("INSERT_CATEGORY");
            String query = String.format(format, name, fatherID);

            try {
                this.runQuery(this.getConnection(), query);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
//
//
//    public void deleteCategory(Category category) {
//
//        int id = category.getCategoryID();
//
//        try {
//            String sql = String.format(Query.DELETE_CATEGORY, id);
//            this.runQuery(this.getConnection(), sql);
//
//            sql = String.format(Query.DELETE_SUBCATEGORY_FROM_FATHER, id);
//            this.runQuery(this.getConnection(), sql);
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Product getAmazonProduct(String productCode) {
//
//        Product product = null;
//        try {
//            QueryResult queryResult = this.executeQuery(this.getConnection(), String.format(Query.SELECT_AMAZON_PRODUCT_FROM_CODE, productCode));
//            ResultSet resultSet = queryResult.getResultSet();
//
//            while (resultSet.next()) {
//
//                int id = resultSet.getInt("ID");
//                int category = resultSet.getInt("CATEGORY");
//                int seller = resultSet.getInt("SELLER");
//                int country = resultSet.getInt("COUNTRY");
//                String code = resultSet.getString("CODE");
//                String name = resultSet.getString("NAME");
//                String link = resultSet.getString("LINK");
//                String imageLink = resultSet.getString("IMAGELINK");
//
//                product = new Product(id, seller, country, category, code, name, link, imageLink);
//            }
//            queryResult.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return product;
//    }
//
//    public Integer insertAmazonProduct(Product amazonProduct) {
//
//        int category = amazonProduct.getCategory();
//        int seller = amazonProduct.getSellerCode();
//        int country = amazonProduct.getCountryCode();
//        String code = amazonProduct.getCode();
//        String name = this.escape(amazonProduct.getName());
//        String link = amazonProduct.getLink();
//        String imageLink = amazonProduct.getImageLink();
//
//        String sql = String.format(Query.INSERT_AMAZON_PRODUCT, category, seller, country, code, name, link,
//                imageLink);
//
//        try {
//            return this.runQueryReturnID(this.getConnection(), sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return -1;
//    }
//
//
//    public Check getLastCheck(Product amazonProduct) {
//
//        Check check = null;
//        try {
//            QueryResult queryResult = this.executeQuery(this.getConnection(), String.format(Query.SELECT_LAST_CHECK, amazonProduct.getId()));
//            ResultSet resultSet = queryResult.getResultSet();
//
//            while (resultSet.next()) {
//
//                int id = resultSet.getInt("ID");
//                int idProduct = resultSet.getInt("IDPRODUCT");
//                Timestamp timestamp = resultSet.getTimestamp("CHECKTIME");
//                int status = resultSet.getInt("STATUS");
//                BigDecimal price = resultSet.getBigDecimal("PRICE");
//
//                check = new Check(id, idProduct, timestamp.toLocalDateTime(), status, price);
//            }
//
//            queryResult.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return check;
//    }
//
//    public void insertAmazonCheck(Product product, PriceCheck check) {
//
//        int id = product.getId();
//        PriceCheckStatus status = check.getStatus();
//        BigDecimal price = check.getPrice();
//
//        String sql = String.format(Query.INSERT_AMAZON_CHECK, id, LocalDateTime.now().toString(), status.getId(),
//                price.toString());
//
//        try {
//            this.runQuery(this.getConnection(), sql);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public ObservableList<Check> getAllCheck(Product product) {
//
//        ObservableList<Check> list = FXCollections.observableArrayList();
//        try {
//            QueryResult queryResult = this.executeQuery(this.getConnection(), String.format(Query.SELECT_ALL_CHECK, product.getId()));
//            ResultSet resultSet = queryResult.getResultSet();
//
//            while (resultSet.next()) {
//
//                int id = resultSet.getInt("ID");
//                int idProduct = resultSet.getInt("IDPRODUCT");
//                Timestamp timestamp = resultSet.getTimestamp("CHECKTIME");
//                int status = resultSet.getInt("STATUS");
//                BigDecimal price = resultSet.getBigDecimal("PRICE");
//
//                list.add(new Check(id, idProduct, timestamp.toLocalDateTime(), status, price));
//            }
//
//            queryResult.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
}