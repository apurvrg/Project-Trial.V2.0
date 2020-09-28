package ProjectTrial.dao;

import ProjectTrial.entity.Java;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface JavaDao extends CrudRepository<Java, Integer>{
	
	List<Java> findAll();
	
	Optional<Java> findById(int javaId);

}
