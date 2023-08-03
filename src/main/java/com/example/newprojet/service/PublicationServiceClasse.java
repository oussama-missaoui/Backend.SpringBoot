package com.example.newprojet.service;


import com.example.newprojet.model.Client;
import com.example.newprojet.model.Publication;
import com.example.newprojet.repository.ClientRepository;
import com.example.newprojet.repository.PublicationRepository;
import com.example.newprojet.utils.StoregeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PublicationServiceClasse implements PublicationServiceInterface{
    @Autowired
    public PublicationRepository publicationRepository;
    @Autowired
    public ClientRepository clientRepository;
    @Autowired
    public StoregeService storegeService;
    @Autowired
    public JavaMailSender javaMailSender;

    public List<Publication> findAllPublication(){
        return publicationRepository.findAll();
    }

    public Publication addPublication(Publication publication, MultipartFile file){
        String fileName=storegeService.CreateNameImage(file);
        storegeService.store(file,fileName);
        publication.setImage(fileName);
        return publicationRepository.save(publication);
    }
    public Publication addPublicationc(Publication publication, MultipartFile file,Long idc){
        String fileName=storegeService.CreateNameImage(file);
        storegeService.store(file,fileName);
        publication.setImage(fileName);
        publication.idc=idc;
        return publicationRepository.save(publication);
    }
    @Override
    public Publication update(Long id, Publication client) {
        Publication cl = publicationRepository.findById(id).orElse(null);
        cl.setId(id);
        cl.setNom(client.getNom() == null ? cl.getNom() : client.getNom());
        cl.setDescription(client.getDescription() == null ? cl.getDescription() : client.getDescription());
        cl.setEmail(client.getEmail() == null ? cl.getEmail() : client.getEmail());
        cl.setTel(client.getTel() == 0 ? cl.getTel() : client.getTel());

        return publicationRepository.saveAndFlush(cl);
    }
    public void addContact(Publication pub ,Long idpubli, Long idcli){
        Publication p= publicationRepository.findById(idpubli).orElse(null);
        Client c=clientRepository.findById(idcli).orElse(null);

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(p.getEmail());

        msg.setSubject(" BONJOUR "+p.getNom());
        msg.setText( c.getNom()+" Vous envoyer un message :\n"+pub.getDescription()+"\n" +
                "\n\nles Cordonner du client : \n\tNom = "+c.getNom()+"\n\tPrenom = "+c.prenom+
                "\n\tTelephone = "+c.getTel()+"\n\tEmail = "+c.getEmail()+

                "\n\n\n\n\t\t\t\t Contacter nous sur le num  25802253 ");

        javaMailSender.send(msg);


    }

    public void ContactMy(Publication pub){


        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("pettopfe2023@gmail.com");

        msg.setSubject(" BONJOUR ");
        msg.setText( " Le client "+ pub.getNom()+" Vous envoyer un message :\n"+pub.getDescription()+
                "\n Leur adresses mail : "+pub.email);

        javaMailSender.send(msg);


    }


    public Publication findById(Long id){
        return publicationRepository.findById(id).orElse(null);
    }
    public List<Publication> findByType(String type){
        return publicationRepository.findPublicationByType(type);
    }
    public List<Publication> findByIdc(Long id){
        return publicationRepository.findPublicationByIdc(id);
    }

    public void deletePublication(Long id){
        publicationRepository.deleteById(id);
    }
    public ResponseEntity<Resource> getFile(String filename) {
        Resource file = storegeService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
