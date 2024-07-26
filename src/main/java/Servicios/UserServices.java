package Servicios;

import Modelos.User;

import java.util.ArrayList;
import java.util.List;

public class UserServices {
    private static UserServices Rama;
    private static List<User> users = new ArrayList<>();

    private UserServices() {
        // Agrega un usuario administrador por defecto
        users.add(new User("M1504","Miguel", "123456"));
        users.add(new User( "admin", "admin", "admin1234", true));
    }
    public static UserServices getRama() {
        if(Rama == null){
        Rama = new UserServices();
        }
        return Rama;
    }

    public static List<User> getAllUsers() {
        return users;
    }

    public static User getUserByUsername(String username) {
        return users.stream().filter(user -> user.getUsername().equals(username)).findFirst().orElse(null);
    }

    public static int getLastUser(){
        if(users.isEmpty()){
            return 0;
        } else {
            return users.getLast().getId()+1;
        }
    }


    public static void addUser(User user) {
        users.add(user);
    }
}
