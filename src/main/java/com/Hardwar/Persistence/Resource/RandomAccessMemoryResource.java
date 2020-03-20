package com.Hardwar.Persistence.Resource;

import com.Hardwar.Persistence.Entitys.RandomAccessMemory;
import com.Hardwar.Persistence.Service.RandomAccessMemoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ram")
public class RandomAccessMemoryResource {

    @Autowired
    RandomAccessMemoryService service;

    @GetMapping
    public List<RandomAccessMemory> getAllRAM(){
        return service.getAllRAM();
    }

    @GetMapping(path = "{domainname}")
    public List<RandomAccessMemory> getAllByDomain(@PathVariable("domainname")String domainName){
        return service.getAllByDomain(domainName);
    }

    @GetMapping(path = "price/{amount}")
    public List<RandomAccessMemory> getAllByDomain(@PathVariable("amount")int amount){
        return service.getAllByPrice(amount);
    }

    @GetMapping("delete/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }

    @PutMapping
    public List<RandomAccessMemory> saveAllRAM(@RequestBody List<RandomAccessMemory> listOfRAM){
        return service.saveAllRAM(listOfRAM);
    }
}
