package com.hmsapp.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {

    @PostMapping("/addProperty")
    public String addProperty(){
        return "added";
    }

    @DeleteMapping("/deleteProperty")
    public String deleteProperty(){
        return "deleted";
    }

    @PutMapping("/updateProperty")
    public String updateProperty(){
        return "updated";
    }

}
