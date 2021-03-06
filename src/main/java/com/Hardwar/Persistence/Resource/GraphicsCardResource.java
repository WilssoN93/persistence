package com.Hardwar.Persistence.Resource;

import com.Hardwar.Persistence.Entitys.GraphicsCard;
import com.Hardwar.Persistence.Service.GraphicsCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping(path = "/graphicscard")
public class GraphicsCardResource {

    @Autowired
    GraphicsCardService service;

    @GetMapping
    public @ResponseBody
    List<GraphicsCard> getAllGraphicsCards(){
        return service.getAllNonParsedGPUs();
    }

    @GetMapping("{domainName}")
    public @ResponseBody
    List<GraphicsCard> getAllGraphicsCardsByDomainName(@PathVariable("domainName") String domainName){
       return service.getAllGraphicsCardsByDomainName(domainName);
    }

    @GetMapping("price/{amount}")
    public @ResponseBody
    List<GraphicsCard> getAllGraphicsCardsByPrice(@PathVariable("amount") String amount){
        return service.getAllByPriceAmount(amount);
    }

    @PutMapping
    public List<GraphicsCard> saveAllGraphicsCards(@RequestBody List<GraphicsCard> graphicsCardList){
        return service.saveAll(graphicsCardList);
    }

    @GetMapping("delete/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }

    @PutMapping("match")
    public void matchAll(){
        service.matchAll();
    }
}
