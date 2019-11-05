package com.Hardwar.Persistence.Resource;

import com.Hardwar.Persistence.Entitys.PowerSupplyUnit;
import com.Hardwar.Persistence.Service.PowerSupplyUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/psu")
public class PowerSupplyUnitResource {

    @Autowired
    PowerSupplyUnitService service;

    @GetMapping
    public @ResponseBody
    List<PowerSupplyUnit> getAllPSUs(){
       return service.getAllPSUs();
    }

    @GetMapping(path = "{domainname}")
    public List<PowerSupplyUnit> getAllByDomain(@PathVariable("domainname")String domainName){
        return service.getAllByDomainName(domainName);
    }

    @GetMapping(path = "price/{amount}")
    public List<PowerSupplyUnit> getAllByDomain(@PathVariable("amount")int amount){
        return service.getAllByPrice(amount);
    }

    @PutMapping
    public List<PowerSupplyUnit> saveAllRAM(@RequestBody List<PowerSupplyUnit> PSUs){
        return service.saveAll(PSUs);
    }
}
