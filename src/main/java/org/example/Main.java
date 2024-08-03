package org.example;
import Controladores.CrudController;
import Modelos.Producto;
import Servicios.ProductServices;
import Servicios.UserServices;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import io.javalin.rendering.template.JavalinThymeleaf;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static Servicios.ProductServices.addCarrito;
import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.get;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        ProductServices.getInstance();
        UserServices.getRama();
        String mensaje = "Default Entry Human";
        System.out.println(mensaje);
            var app = Javalin.create(config -> {
                config.staticFiles.add(staticFile ->{
                    staticFile.hostedPath = "/";
                    staticFile.directory = "shoppy";
                    staticFile.location = Location.CLASSPATH;
                    staticFile.precompress=false;
                    staticFile.aliasCheck=null;
                } );
                        //Confifgurar el sistema de plantilla por defecto.
                        config.fileRenderer(new JavalinThymeleaf());
            }).start(7070);
        app.get("/hello", ctx -> {
            ctx.sessionAttribute("funda",(ProductServices.getCarrito() !=null )? ProductServices.countCarrito() : 0 );
            ctx.attribute("name", "Javalin");
            ctx.render("shoppy/prueba2.html");
        });
        app.get("/", ctx -> {
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("funda",(ProductServices.getCarrito() !=null )? ProductServices.countCarrito() : 0 );
            ctx.render("shoppy/templates/index.html", modelo);
        });
        app.get("/addCart", ctx -> {
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("products",ProductServices.getAllProducts() );
            modelo.put("funda",(ProductServices.getCarrito() !=null )? ProductServices.countCarrito() : 0 );
            ctx.render("shoppy/NuevosArchivos/NewProductos.html", modelo);
        });
        app.post("/addCart", ctx -> {
            List<String> productoName = ctx.formParams("productName");
            List<String> precio = ctx.formParams("price");
            List<String> quantity = ctx.formParams("quantity");
            for(int i = 0; i < productoName.size(); i++){
                if(Integer.parseInt(quantity.get(i)) > 0){

                    ProductServices.addCarrito(new Producto(productoName.get(i), Double.parseDouble(precio.get(i)), Integer.parseInt(quantity.get(i))));

                }
            }

            ctx.redirect("/");
        });
        app.get("/ProductCreateOrDelete", ctx -> {
            ctx.sessionAttribute("funda",(ProductServices.getCarrito() !=null )? ProductServices.countCarrito() : 0 );
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("products",ProductServices.getAllProducts() );
            modelo.put("funda",(ProductServices.getCarrito() !=null )? ProductServices.countCarrito() : 0 );
            ctx.render("shoppy/NuevosArchivos/ProductCD.html", modelo);
        });
        app.post("/add-product", CrudController::procesarCreacionProducto);
        app.post("/delete-product", CrudController::procesarBorrarProducto);
        app.get("/UserList", ctx -> {

            System.out.println(UserServices.getAllUsers());
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("users", UserServices.getAllUsers());
            modelo.put("funda",(ProductServices.getCarrito() !=null )? ProductServices.countCarrito() : 0 );
            ctx.render("shoppy/NuevosArchivos/NewUserList.html", modelo);
        });
        app.get("/login", CrudController::FormCrearUser);
        app.post("/login", context -> {
            String username = context.formParam("username");
            String password = context.formParam("password");

            if (username != null && password != null) {
                context.sessionAttribute("logeado", username);
                if(username.equals("admin") && password.equals("admin")){
                    context.sessionAttribute("isAdmin", true);
                } else { context.sessionAttribute("isAdmin", false);}

                context.redirect("/");

            }
        });

        app.get("/cart", ctx ->{
            Map<String, Object> modelo = new HashMap<>();
            modelo.put("cart",ProductServices.getCarrito() );
            ctx.render("shoppy/NuevosArchivos/Carrito.html", modelo);
        });
        app.post("/removeCarrito", CrudController::procesarBorrarProductoCarrito);


    }


}
