package com.chiefsretro.controllers;

import com.chiefsretro.services.FirebaseService;
import com.google.firebase.auth.FirebaseAuth;

import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/admin")
public class AdminController {


    @CrossOrigin(origins = {"http://seriouslag.com:80", "http://seriouslag.com", "http://seriouslag.com:4200", "http://localhost:4200", "http://chiefsretro.com"})
    @RequestMapping(value = "/updateDatabase", produces = "application/json")
    @ResponseBody
    public void UpdateProductDatabase(@RequestParam("idToken") String idToken, HttpServletRequest request, HttpServletResponse response) {
        FirebaseAuth.getInstance().verifyIdToken(idToken)
                .addOnFailureListener(e -> {
                    response.setStatus(Response.SC_FORBIDDEN);
                    e.printStackTrace();

                })
                .addOnSuccessListener(decodedToken -> {
                    System.out.println("YAY");
                    String uid = decodedToken.getUid();
                    FirebaseService.compareDatabases();
                    response.setStatus(Response.SC_ACCEPTED);
                });
    }
}
