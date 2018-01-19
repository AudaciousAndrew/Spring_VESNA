package springjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springjpa.model.User;
import springjpa.model.UserRole;
import springjpa.repo.UserRepo;
import springjpa.repo.UserRoleRepo;

@org.springframework.stereotype.Controller
public class Controller {

    @Autowired
    UserRepo repository;

    @Autowired
    UserRoleRepo roleRepo;

    @RequestMapping(value="/regcheck" , method = RequestMethod.GET)
    public @ResponseBody String regcheck(@RequestParam("username") String username){
        System.out.println("AAAAAA");
        if(username != null){
            if(repository.existsByUsername(username)){
                return "false";
            } else return "true";
        } return "false";
    }

}
