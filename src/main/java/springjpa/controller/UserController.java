package springjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import springjpa.model.User;
import springjpa.model.UserRole;
import springjpa.repo.UserRepo;
import springjpa.repo.UserRoleRepo;

@RestController
public class UserController {
    @Autowired
    UserRepo repository;

    @Autowired
    UserRoleRepo roleRepo;

    @RequestMapping(value = "/register" , method = RequestMethod.POST)
    public ModelAndView register(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception{
        User user;
        UserRole role;
        BCryptPasswordEncoder bCryptPasswordEncoder;
        ModelAndView modelAndView = new ModelAndView();
        if(repository.findByUsername(username) == null ){
            bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user = new User(username , bCryptPasswordEncoder.encode(password) , true);
            role = new UserRole(username , "ROLE_USER");
            repository.save(user);
            roleRepo.save(role);
            modelAndView.setViewName("redirect:/succ");
            return modelAndView;
        } else {
            modelAndView.setViewName("redirect:/fail");
            return modelAndView;
        }
    }


}
