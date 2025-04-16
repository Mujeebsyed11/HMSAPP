package com.hmsapp.service;

import com.hmsapp.entity.Property;
import com.hmsapp.payload.PropertyDto;
import com.hmsapp.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private ModelMapper modelMapper;
    private PropertyRepository propertyRepository;

    public PropertyService(ModelMapper modelMapper, PropertyRepository propertyRepository) {
        this.modelMapper = modelMapper;
        this.propertyRepository = propertyRepository;
    }


    public PropertyDto addProperty(PropertyDto propertyDto){
        Property property = propertyRepository.save(convertToEntity(propertyDto));
        return convertToDto(property);
    }


    public Property convertToEntity(PropertyDto propertyDto ){
        return modelMapper.map(propertyDto, Property.class);
    }

    public PropertyDto convertToDto(Property property){
        return modelMapper.map(property, PropertyDto.class);
    }

}
