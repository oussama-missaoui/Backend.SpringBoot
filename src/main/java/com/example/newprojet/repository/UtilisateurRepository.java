package com.example.newprojet.repository;



import com.example.newprojet.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
public Utilisateur getUtilisateurByEmailAndPassword(String email,String password);

}
