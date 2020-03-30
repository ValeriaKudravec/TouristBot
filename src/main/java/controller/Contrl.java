package controller;

import model.City;
import org.springframework.beans.factory.annotation.Autowired;
import repo.CityRepo;

public class Contrl {

    private static CityRepo cityRepo;

    @Autowired
    public Contrl(CityRepo cityRepo){
        this.cityRepo = cityRepo;
    }

    public static String findInf(String message){
        System.out.println(message);
        City city= cityRepo.findByName(message);
        return city.getInformation();
    }


}
