package com.chiefsretro.services;

import com.chiefsretro.entities.DbProduct;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.*;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;

@Service
public class FirebaseService {

    private static FirebaseApp app;
    private static FirebaseApp adminApp;
    private static FirebaseDatabase database;
    private static FirebaseDatabase adminDatabase;
    private static DataSnapshot products;
    private static DataSnapshot adminProducts;
    private static boolean isInit = false;

    public static void init() {


        app = FirebaseApp.getInstance();
        adminApp = FirebaseApp.getInstance("admin");
        System.out.println("adminApp: " + adminApp.getName());
        FirebaseApp.getApps().forEach((k) ->System.out.println("app: " + k.getName()));

        database = FirebaseDatabase.getInstance();
        adminDatabase = FirebaseDatabase.getInstance(adminApp);
        isInit = true;
        System.out.println("Firebase init");
    }

    @Scheduled(fixedRate=300000, initialDelay=1000)
    public void maintenance() {
        if(!isInit) {
            init();
        }
        System.out.println(new Date(System.currentTimeMillis()) + " Starting maintenance");
        orderCheck();
    }

    private static void orderCheck() {
        database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("orders/");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot child: dataSnapshot.getChildren()) {
                    StripeService.requestOrderProcessing(child);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public static void compareDatabases() {
        DatabaseReference product = database.getReference("products/");
        System.out.println(adminApp);
        System.out.println(adminDatabase);
        DatabaseReference adminProduct = adminDatabase.getReference("products/");

        product.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                products = dataSnapshot;
                System.out.println("handle products");
                if(adminProducts.hasChildren()) {
                    compareSnapshots(adminProducts, products);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adminProduct.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adminProducts = dataSnapshot;
                System.out.println("handle adminProducts");
                if(products.hasChildren()) {
                    compareSnapshots(products, adminProducts);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                databaseError.toException().printStackTrace();
            }
        });

    }

    private static void compareSnapshots(DataSnapshot ds1, DataSnapshot ds2) {
        try {
            System.out.println("comparing snapshots");

            try {
                ArrayList<DbProduct> adminProductList = (ArrayList<DbProduct>) ds1.getValue();
                ArrayList<DbProduct> appProductList = (ArrayList<DbProduct>) ds2.getValue();

                if (!adminProductList.equals(appProductList)) {
                    equalizeDatabases(ds1, ds2);
                } else {
                    System.out.println("Databases are equal");
                }
            } catch(ClassCastException e) {
                System.out.println("ERROR in FIREBASE PRODUCT Database " + e.getMessage());

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void equalizeDatabases(DataSnapshot defaultSnap, DataSnapshot adminSnap) {
        System.out.println("Equalizing Databases");



        //after reset products
        products = null;
        adminProducts = null;
    }
}
