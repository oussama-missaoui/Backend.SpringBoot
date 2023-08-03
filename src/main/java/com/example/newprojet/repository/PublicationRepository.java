package com.example.newprojet.repository;

import com.example.newprojet.model.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication,Long> {
    public List<Publication> findPublicationByType(String type);
    public List<Publication> findPublicationByIdc(Long id);


}
