package ProjectTrial.service;

import java.util.List;

import ProjectTrial.entity.Java;

public interface JavaService {
	
	public List<Java> findAll();
	
	public Java findById(int javaId);
	
	public void save(Java theJava);
	
	public void deleteById(int javaId);

}
