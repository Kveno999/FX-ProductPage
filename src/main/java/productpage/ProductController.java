package productpage;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import productpage.databaseutils.ExecutableUtils;
import productpage.models.Product;

public class ProductController {
    
    public Label id;
    public Label price;
    public Label name;
    public Label description;
    public Label owner;

    @FXML
    protected void onGet() {
        Product product = ExecutableUtils.select("SELECT * FROM `product` WHERE 1;", Product.class).get(0);
        id.setText(product.getId().toString());
        price.setText(product.getPrice().toString());
        name.setText(product.getName());
        description.setText(product.getDescription());
        owner.setText(product.getOwner());
    }
}