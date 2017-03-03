package General;

/**
 * Created by Tymek on 03.03.2017.
 */
public class ProductsHandler {
    private static ProductsHandler ourInstance = new ProductsHandler();

    public static ProductsHandler getInstance() {
        return ourInstance;
    }

    private ProductsHandler() {
    }
}
