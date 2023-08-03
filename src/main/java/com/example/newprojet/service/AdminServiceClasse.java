package com.example.newprojet.service;



import com.example.newprojet.model.Admin;
import com.example.newprojet.repository.AdminRepository;
import com.example.newprojet.utils.StoregeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class AdminServiceClasse implements AdminServiceInterface {
    @Autowired
    public AdminRepository adminRepository;
    @Autowired
    public StoregeService storegeService;


@Override
    public List<Admin> findAllAdmin(){
        return adminRepository.findAll();
    }
    @Override
    public Admin addAdmin(Admin admin, MultipartFile file){
        String fileName=storegeService.CreateNameImage(file);
        storegeService.store(file,fileName);
        admin.setPhoto(fileName);
        admin.setRole("admin");
        return adminRepository.save(admin);
    }
    @Override
    public Admin findById(Long id){
        return adminRepository.findById(id).orElse(null);
    }
    @Override
    public ResponseEntity<Resource> getFile(String filename) {
        Resource file = storegeService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }
}
