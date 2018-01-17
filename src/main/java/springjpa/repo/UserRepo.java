package springjpa.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import springjpa.model.User;

@Repository("userRepo")
public interface UserRepo extends CrudRepository<User , Long> {
    public User findByUsername(String username);
}
