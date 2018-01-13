package springjpa.repo;

import springjpa.model.Point;
import org.springframework.data.repository.CrudRepository;

public interface PointRepo extends CrudRepository<Point, Long> {
}
