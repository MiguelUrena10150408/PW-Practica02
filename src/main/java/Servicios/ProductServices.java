package Servicios;

import java.util.ArrayList;
import java.util.List;


import Modelos.Producto;

public class ProductServices {
    private static ProductServices instance;
    private static List<Producto> products = new ArrayList<>();

    private ProductServices() {}

    public static ProductServices getInstance() {
        if (instance == null) {
            instance = new ProductServices();
        }
        return instance;
    }
    public static List<Producto> getAllProducts() {
        return products;
    }

    public static void addProduct(Producto producto) {
        products.add(producto);
    }

    public static void updateProduct(String name, Producto updatedProduct) {
        for (Producto product : products) {
            if (product.getName().equals(name)) {
                product.setStock(updatedProduct.getStock());
                product.setPrice(updatedProduct.getPrice());
                break;
            }
        }
    }
    public static int getLastProduct (){
        if(products.isEmpty()){
            return 0;
        } else {
            return products.getLast().getIdProducto() +1;
        }
    }
    public static void deleteProduct(String name) {
        products.removeIf(product -> product.getName().equals(name));
    }
}
