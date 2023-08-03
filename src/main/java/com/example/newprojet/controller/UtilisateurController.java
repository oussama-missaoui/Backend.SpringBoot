package com.example.newprojet.controller;

import com.example.newprojet.model.Utilisateur;
import com.example.newprojet.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RequestMapping("/util")
@RestController
public class UtilisateurController {
@Autowired
    public UtilisateurRepository utilisateurRepository;

    @PostMapping(path = "/login")
    public Utilisateur Login (@RequestBody Utilisateur util) {

        Utilisateur acteur1  = utilisateurRepository.getUtilisateurByEmailAndPassword(util.email,util.password);

        if (acteur1!=null) {

            System.out.println("Login avec succè");
            return acteur1;
        } else {
            System.out.println("erreur de connection");
            return null;
        }

    }
}
