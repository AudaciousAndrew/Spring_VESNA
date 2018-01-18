package springjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springjpa.model.Point;
import springjpa.repo.PointRepo;

@RestController

public class PointController {

    @Autowired
    PointRepo repository;

    @RequestMapping(value = "/point" , method = RequestMethod.POST)
    public void addPoint(
            @RequestParam("x") double x ,
            @RequestParam("y") double y ,
            @RequestParam("r") double r   ){
        Point point = new Point( x , y , r);
        repository.save(point);
    }

    @RequestMapping("/save")
    public String process(){
        repository.save(new Point(1 , 1,  4));
        repository.save(new Point(1 , -1,  4 ));
        repository.save(new Point(1 , -1,  4 ));
        repository.save(new Point(1 , 1,  4 ));
        return "Save done";
    }

    @RequestMapping("/find")
    public String findAll(){
        String result = "<html>";
        for ( Point point : repository.findAll()){
            result  += "<div>" + point.toString() + "</div>";
        }
        return  result + "</html>";
    }

    @RequestMapping("/clear")
    public String clear(){
        repository.deleteAll();
        return "Delete done";
    }
}
