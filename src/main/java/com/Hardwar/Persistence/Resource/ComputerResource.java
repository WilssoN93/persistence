package com.Hardwar.Persistence.Resource;

import com.Hardwar.Persistence.Utils.Builder;
import com.Hardwar.Persistence.Entitys.Computer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(path = "/computer")
public class ComputerResource {


    @Autowired
    Builder builder;

    @GetMapping(path = "{amount}")
    public @ResponseBody
    Computer getAComputer(@PathVariable("amount")int amount){
       return builder.getAComputer(amount);
    }
}
