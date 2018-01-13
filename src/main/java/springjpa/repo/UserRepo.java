package springjpa.repo;

import org.springframework.data.repository.CrudRepository;
import springjpa.model.User;

public interface UserRepo extends CrudRepository<User , Long> {
    public User findByLogin(String login);
}
