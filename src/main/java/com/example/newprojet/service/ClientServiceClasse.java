package com.example.newprojet.service;


import com.example.newprojet.model.Client;
import com.example.newprojet.repository.ClientRepository;

import com.example.newprojet.utils.StoregeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class ClientServiceClasse implements ClientServiceInterface{
    @Autowired
    public ClientRepository clientRepository;
    @Autowired
    public StoregeService storegeService;

    public List<Client> findAllClient(){
        return clientRepository.findAll();
    }

    public Client addClient(Client client, MultipartFile file){
        String fileName=storegeService.CreateNameImage(file);
        storegeService.store(file,fileName);
        client.setPhoto(fileName);
        client.setRole("client");
        return clientRepository.save(client);
    }

    public Client findById(Long id){
        return clientRepository.findById(id).orElse(null);
    }
    public void deleteClient(Long id){
        clientRepository.deleteById(id);
    }
    public ResponseEntity<Resource> getFile(String filename) {
        Resource file = storegeService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }




}
