package com.example.newprojet.service;


import com.example.newprojet.model.Client;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ClientServiceInterface {
    public List<Client> findAllClient();
    public Client addClient(Client client, MultipartFile file);

    public Client findById(Long id);
    public void deleteClient(Long id);
    public ResponseEntity<Resource> getFile(String filename);

}
