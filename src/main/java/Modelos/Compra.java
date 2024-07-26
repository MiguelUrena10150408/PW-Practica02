package Modelos;

import java.util.List;

import static Servicios.PurchaseServices.getLasPurchase;

public class Compra {
    private int id;
    private String username;
    private List<Producto> productos;

    // Getters y setters
    public Compra(int id, String username, List<Producto> productos) {
        this.id = getLasPurchase();
        this.username = username;
        this.productos = productos;
    }
    public void setId(int  id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
