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
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserRepo repository;

    @Autowired
    UserRoleRepo roleRepo;


    //TODO return success page(change on @Controller annotation) or make thymeleaf with params
    @RequestMapping( method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestParam("username") String username, @RequestParam("password") String password) throws Exception{
        User user;
        UserRole role;
        BCryptPasswordEncoder bCryptPasswordEncoder;
        if(repository.findByUsername(username) == null ){
            bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user = new User(username , bCryptPasswordEncoder.encode(password) , true);
            role = new UserRole(username , "ROLE_USER");
            repository.save(user);
            roleRepo.save(role);
            return "User: "+ username +" successfully registered";
        } else return "There is user with such username";

    }

    @RequestMapping("/usersave")
    public String process(){
        repository.save(new User("AudaciousAndrew" , "123pashaloh" , true));
        return "User added";
    }

    @RequestMapping("/userfind")
    public String find(){
        String result = "<html>";
        for(User user : repository.findAll()){
            result += "<div>" + user.toString() + "</div>";
        }
        return result + "</html>";
    }

    @RequestMapping("/userclear")
    public String delete(){
        repository.deleteAll();
        return "Delete users done";
    }

}
