package com.Hardwar.Persistence.Resource;

import com.Hardwar.Persistence.Entitys.Storage;
import com.Hardwar.Persistence.Service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/storage")
public class StorageResource {

    @Autowired
    StorageService service;


    @GetMapping(path = "{domainname}")
    public List<Storage> getAllByDomain(@PathVariable("domainname")String domainName){
        return service.getAllByDomain(domainName);
    }

    @GetMapping
    public @ResponseBody
    List<Storage> getAllStorage(){
        return service.getAllStorage();
    }

    @PutMapping
    public List<Storage> saveAllRAM(@RequestBody List<Storage> listOfStorage){
        return service.saveAllStorage(listOfStorage);
    }
}
