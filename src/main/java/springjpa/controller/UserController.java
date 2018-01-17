package springjpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springjpa.model.User;
import springjpa.repo.UserRepo;

@RestController
public class UserController {
    @Autowired
    UserRepo repository;

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
