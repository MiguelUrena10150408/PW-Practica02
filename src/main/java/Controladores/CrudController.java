package Controladores;

import Modelos.Compra;
import Modelos.Producto;
import Modelos.User;
import Servicios.ProductServices;
import Servicios.PurchaseServices;
import Servicios.UserServices;
import org.jetbrains.annotations.NotNull;
import io.javalin.http.Context;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CrudController {
    private static UserServices userServices = UserServices.getRama();
    private static ProductServices productServices = ProductServices.getInstance();
    private static PurchaseServices purchaseServices = PurchaseServices.getInstance();

    public static void ListarUsuarios(@NotNull Context ctx) throws Exception {
        List<User> userList = userServices.getAllUsers();
    }

    public static void listarProductos(@NotNull Context ctx) throws Exception{
        List<Producto> productoLista = productServices.getAllProducts();

    }

    public static void listarCompras(@NotNull Context ctx) throws Exception{
        List<Compra> purchaseLista = purchaseServices.getAllPurchases();
    }
    public static void FormCrearUser(@NotNull Context ctx) throws Exception {

        Map<String, Object> modelo = new HashMap<>();
        modelo.put("titulo", "Formulario Creación Usuario");
        modelo.put("accion", "/addCart");
        modelo.put("funda",(ProductServices.getCarrito() !=null )? ProductServices.countCarrito() : 0 );

        ctx.render("/shoppy/NuevosArchivos/authentication-register.html", modelo);
    }
    public static void procesarCreacionUser(@NotNull Context ctx) throws Exception{
        String name = "Jose";
        String username = ctx.pathParam("username");
        String password = ctx.pathParam("password");

        User temp = new User(username, name, password);
        userServices.addUser(temp);

        ctx.redirect("/templates/index.html");

    }

    public static void procesarCreacionProducto(@NotNull Context ctx) throws Exception{

        String nombre = ctx.formParam("productNombre");
        double precio = Double.parseDouble(ctx.formParam("price"));
        int stock = Integer.parseInt(ctx.formParam("stock"));

        Producto temp = new Producto(nombre,precio,stock);
        ProductServices.addProduct(temp);
        ctx.redirect("/templates/index.html");
    }

    public static void procesarCreacionFactura(@NotNull Context ctx) throws Exception{

        String nombre = ctx.formParam("nombre");
        List<Producto> factura = productServices.getCarrito();


        Compra temp = new Compra(nombre,factura);
        PurchaseServices.addPurchase(temp);
        ctx.redirect("/");
    }

    public static void procesarBorrarProducto(@NotNull Context ctx) throws Exception{

        String nombre = ctx.formParam("productNombre");
        productServices.deleteProduct(nombre);
        ctx.redirect("/");
    }
    public static void procesarBorrarProductoCarrito(@NotNull Context ctx) throws Exception{

        String nombre = ctx.formParam("productNombre");
        productServices.deleteFromCarrito(nombre);
        ctx.redirect("/");
    }
}
