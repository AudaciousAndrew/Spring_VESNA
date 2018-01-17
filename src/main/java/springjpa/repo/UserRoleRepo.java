package springjpa.repo;

import org.springframework.data.repository.CrudRepository;
import springjpa.model.UserRole;

public interface UserRoleRepo extends CrudRepository<UserRole , Long>{
    
}
