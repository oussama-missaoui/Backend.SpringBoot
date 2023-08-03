package com.example.newprojet.controller;


import com.example.newprojet.model.Client;
import com.example.newprojet.service.ClientServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/client")
public class ClientController {
    @Autowired
    public ClientServiceInterface clientServiceInterface;

    @GetMapping("/findAllClient")
    public List<Client> findAllAdmin(){
        return clientServiceInterface.findAllClient();
    }
    @GetMapping("/findById/{id}")
    public Client findById(@PathVariable Long id){
        return clientServiceInterface.findById(id);
    }
    @PostMapping("/addClient")
    public Client addAdmin(Client client, @RequestParam MultipartFile file){
        return clientServiceInterface.addClient(client,file);
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        clientServiceInterface.deleteClient(id);
    }

    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        return clientServiceInterface.getFile(filename);
    }
}
