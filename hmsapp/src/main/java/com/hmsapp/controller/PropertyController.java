package com.hmsapp.controller;

import com.hmsapp.entity.Property;
import com.hmsapp.payload.PropertyDto;
import com.hmsapp.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/property")
public class PropertyController {
    private PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }


    @PostMapping("/addProperty")
    public ResponseEntity<PropertyDto> addProperty(@RequestBody PropertyDto propertyDto){
        PropertyDto dto = propertyService.addProperty(propertyDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
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
