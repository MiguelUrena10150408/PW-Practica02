package Servicios;

import java.util.ArrayList;
import java.util.List;


import Modelos.Producto;

public class ProductServices {
    private static ProductServices instance;
    private static List<Producto> products = new ArrayList<>();
    private static List<Producto> Carrito = new ArrayList<>();

    private ProductServices() {
        products.add(new Producto("Arepa", 100.10, 0));
        products.add(new Producto("Tamarindo", 101.10, 0));
        products.add(new Producto("Argo", 22.10, 0));
    }

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

    public static List<Producto> getCarrito() {
        return Carrito;
    }

    public static void addCarrito(Producto producto) {
         if(Carrito.contains(producto.getName())) {

         }       else {
             Carrito.add(producto);
         }

    }

    public static int countCarrito(){
        return Carrito.size();
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
    public static void deleteFromCarrito(String name) {
        Producto pepe = new Producto();
        pepe.setName(name);
        ProductServices.getInstance().getCarrito().remove(pepe);

    }


}
