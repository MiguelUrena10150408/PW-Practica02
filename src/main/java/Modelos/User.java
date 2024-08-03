package Modelos;
import Servicios.UserServices;

public class User {
     int id;
     String username;
     String name;
     String password;
     boolean isAdmin;

    // Getters y setters
    public User(){}
    public User(String username, String name, String password){
        this.id = UserServices.getLastUser();
        this.username = username;
        this.name = name;
        this.password = password;
        this.isAdmin = false;

    }


    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }
}