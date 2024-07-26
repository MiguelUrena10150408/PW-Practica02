package Servicios;

import Modelos.Compra;

import java.util.ArrayList;
import java.util.List;

public class PurchaseServices {
    private static PurchaseServices instance;
    private static List<Compra> purchases = new ArrayList<>();

    private PurchaseServices() {};

    public static PurchaseServices getInstance() {
        if (instance == null) {
            instance = new PurchaseServices();
        }
        return instance;
    }

    public static List<Compra> getAllPurchases() {
        return purchases;
    }

    public static void addPurchase(Compra purchase) {
        purchases.add(purchase);

    }
    public static int getLasPurchase (){
        if(purchases.isEmpty()){
            return 0;
        } else {
            return purchases.getLast().getId()+1;
        }
    }

}
