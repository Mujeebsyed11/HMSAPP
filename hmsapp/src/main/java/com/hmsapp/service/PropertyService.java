package com.hmsapp.service;

import com.hmsapp.entity.City;
import com.hmsapp.entity.Country;
import com.hmsapp.entity.Property;
import com.hmsapp.payload.PropertyDto;
import com.hmsapp.repository.CityRepository;
import com.hmsapp.repository.CountryRepository;
import com.hmsapp.repository.PropertyRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private ModelMapper modelMapper;
    private PropertyRepository propertyRepository;
    private CountryRepository countryRepository;
    private CityRepository cityRepository;

    public PropertyService(ModelMapper modelMapper, PropertyRepository propertyRepository, CountryRepository countryRepository, CityRepository cityRepository) {
        this.modelMapper = modelMapper;
        this.propertyRepository = propertyRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
    }


    public PropertyDto addProperty(PropertyDto propertyDto){
        return convertToDto(propertyRepository.save(convertToEntity(propertyDto)));
    }








    public Property convertToEntity(PropertyDto propertyDto){
        Property property = modelMapper.map(propertyDto, Property.class);
        Country country = countryRepository.findByName(propertyDto.getCountry()).orElseGet(
                ()->{
                    Country newCountry = new Country();
                    newCountry.setName(propertyDto.getCountry());
                    return countryRepository.save(newCountry);
                });

        City city = cityRepository.findByName(propertyDto.getCity()).orElseGet(
                ()->{
                    City newCity = new City();
                    newCity.setName(propertyDto.getCity());
                    return cityRepository.save(newCity);
                });
        property.setCountry(country);
        property.setCity(city);
        return property;
    }

    public PropertyDto convertToDto(Property property){
        PropertyDto propertyDto = modelMapper.map(property, PropertyDto.class);
        propertyDto.setCountry(property.getCountry().getName());
        propertyDto.setCity(property.getCity().getName());
        return propertyDto;
    }
}
