package com.example.newprojet.service;

import com.example.newprojet.model.Admin;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AdminServiceInterface {
    public List<Admin> findAllAdmin();
    public Admin addAdmin(Admin admin, MultipartFile file);

    public Admin findById(Long id);
    public ResponseEntity<Resource> getFile(String filename);




}
