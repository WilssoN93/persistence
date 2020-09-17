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

    @GetMapping(path = "{amount}/optionalDrive/")
    public @ResponseBody
    Computer computerWithOptionalDrive(@PathVariable("amount")int amount,
                                       @RequestParam(name = "primaryDrive") String primaryDrive,
                                       @RequestParam(name = "optionalDrive") String optionalDrive){
       return builder.getAComputer(amount ,primaryDrive ,optionalDrive);
    }

    @GetMapping(path = "{amount}")
    public @ResponseBody
    Computer computerWithoutOptionalDrive(@PathVariable("amount")int amount, @RequestParam(name = "primaryDrive") String primaryDrive){
        return builder.getAComputer(amount, primaryDrive, null);
    }
}
