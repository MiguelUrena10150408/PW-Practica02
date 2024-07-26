package org.example;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
            var app = Javalin.create(config -> {
                config.staticFiles.add(staticFile ->{
                    staticFile.hostedPath = "/";
                    staticFile.directory = "shoppy";
                    staticFile.location = Location.CLASSPATH;
                } );
                    })
                    .patch("/", ctx -> ctx.render("/shoppy/assets/index.html"))

                    .start(7070);
    }
}