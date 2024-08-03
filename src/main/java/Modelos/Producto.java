package Modelos;
import Servicios.ProductServices;

import static Servicios.ProductServices.getLastProduct;

public class Producto {
   private  int idProducto;
     private String name;
     private double price;
    private int stock;

    // Getters y setters
    public Producto(){}
    public Producto(String name, double price, int stock){
        this.idProducto = getLastProduct();
        this.price = price;
        this.name = name;
        this.stock = stock;

    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {this.stock = stock;}
}
