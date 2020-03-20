package com.Hardwar.Persistence.Resource;

import com.Hardwar.Persistence.Entitys.MotherBoard;
import com.Hardwar.Persistence.Service.MotherBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/motherboards")
public class MotherBoardResource {

    @Autowired
    MotherBoardService service;

    @GetMapping
    public @ResponseBody
    List<MotherBoard> getAllMotherBoards(){
       return service.getAllMotherBoards();
    }
    @GetMapping(path = "{domainname}")
    public List<MotherBoard> getAllByDomain(@PathVariable("domainname")String domainName){
        return service.getAllByDomain(domainName);
    }

    @GetMapping(path = "price/{amount}")
    public List<MotherBoard> getAllByDomain(@PathVariable("amount")int amount){
        return service.getAllMotherBoardsByPrice(amount);
    }

    @GetMapping(path = "price/{amount}/{socket}")
    public List<MotherBoard> getAllByDomain(@PathVariable("amount")int amount,@PathVariable("socket")String socket){
        return service.getAllMotherBoardsByPriceAndSocket(amount,socket);
    }

    @GetMapping("delete/{id}")
    public void delete(@PathVariable("id") Long id){
        service.delete(id);
    }

    @PutMapping
    public List<MotherBoard> saveAllMotherBoards(@RequestBody List<MotherBoard> motherBoards){
        return service.saveAllMotherBoards(motherBoards);
    }
}
