package com.example.newprojet.controller;

import com.example.newprojet.model.Admin;
import com.example.newprojet.service.AdminServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {
    @Autowired
public AdminServiceInterface adminServiceInterface;
    @GetMapping("/findAllAdmin")
    public List<Admin> findAllAdmin(){
        return adminServiceInterface.findAllAdmin();
    }
    @GetMapping("/findById/{id}")
    public Admin findById(@PathVariable Long id){
        return adminServiceInterface.findById(id);
    }
    @PostMapping("/addAdmin")
    public Admin addAdmin(Admin admin, @RequestParam MultipartFile file){
        return adminServiceInterface.addAdmin(admin,file);
    }
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        return adminServiceInterface.getFile(filename);
    }
}
