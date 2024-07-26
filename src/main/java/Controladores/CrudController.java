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
        modelo.put("accion", "/");

        ctx.render("/shoppy/assets/authentication-register.html", modelo);
    }
    public static void procesarCreacionUser(@NotNull Context ctx) throws Exception{
        String nombre = ctx.pathParam("nombre");
        String username = ctx.pathParam("username");
        String password = ctx.pathParam("password");

        User temp = new User(username, nombre, password);
        userServices.addUser(temp);

        ctx.redirect("/shoppy/assets/index.html");

    }

    public static void procesarCreacionProducto(@NotNull Context ctx) throws Exception{

    }

    public static void procesarCreacionFactura(@NotNull Context ctx) throws Exception{

    }
}
