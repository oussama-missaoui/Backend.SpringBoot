package com.example.newprojet.service;

import com.example.newprojet.model.Client;
import com.example.newprojet.model.Publication;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PublicationServiceInterface {
    public List<Publication> findAllPublication();
    public Publication addPublication(Publication publication, MultipartFile file);
    public Publication addPublicationc(Publication publication, MultipartFile file,Long idc);
    public void addContact(Publication pub ,Long idpubli, Long idcli);
    public void ContactMy(Publication pub);
    public Publication update (Long id,Publication pub);

    public Publication findById(Long id);
    public List<Publication> findByType(String type);
    public List<Publication> findByIdc(Long id);
    public void deletePublication(Long id);
    public ResponseEntity<Resource> getFile(String filename);

}
