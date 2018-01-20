package springjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springjpa.model.Point;
import springjpa.repo.PointRepo;

@RestController

public class PointController {

    @Autowired
    PointRepo repository;

    @RequestMapping(value = "/point" , method = RequestMethod.POST)
    public void addPoint(@RequestBody Point point){
        point.inArea();
        repository.save(point);
    }

    @RequestMapping(value = "/point" , method = RequestMethod.GET)
    public Iterable<Point> pointGet(){
       return repository.findAll();
    }


}
