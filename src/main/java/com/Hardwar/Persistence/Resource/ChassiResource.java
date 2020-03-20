package com.Hardwar.Persistence.Resource;

import com.Hardwar.Persistence.Entitys.Chassi;
import com.Hardwar.Persistence.Service.ChassiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/chassi")
public class ChassiResource {

    @Autowired
    ChassiService service;

    @GetMapping
    public @ResponseBody
    List<Chassi> getAllChassis(){
       return service.getAllChassis();
    }

    @GetMapping(path = "{domainname}")
    public @ResponseBody
    List<Chassi> getAllbyDomain(@PathVariable("domainname")String domainName){
        return service.getAllByDomain(domainName);
    }

    @GetMapping("delete/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }

    @GetMapping(path = "price/{amount}")
    public @ResponseBody
    List<Chassi> getAllbyDomain(@PathVariable("amount")int amount){
        return service.getAllByPrice(amount);
    }

    @PutMapping
    public List<Chassi> saveAllRAM(@RequestBody List<Chassi> chassis) {
        return service.saveAll(chassis);
    }
}
