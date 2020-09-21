package ProjectTrial.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ProjectTrial.entity.Java;

public interface JavaDao extends CrudRepository<Java, Integer>{
	
	public List<Java> findAll();
	
	public Optional<Java> findById(int javaId);

}
