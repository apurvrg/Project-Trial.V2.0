package ProjectTrial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ProjectTrial.dao.JavaDao;
import ProjectTrial.entity.Java;

@Service
public class JavaServiceImpl implements JavaService{

	@Autowired
	private JavaDao javaDao;
	
	@Autowired
	public JavaServiceImpl(JavaDao theJavaDao) {
		javaDao = theJavaDao;
	}
	
	@Override
	public List<Java> findAll() {
		return javaDao.findAll();
	}

	@Override
	public Java findById(int javaId) {
		Optional<Java> result = javaDao.findById(javaId);
		
		Java theJava = null;
		if (result.isPresent()) {
			theJava = result.get();
		}
		else {
			// we didn't find the employee
			throw new RuntimeException("Did not find question id - " + javaId);
		}
		
		return theJava;
	}

	@Override
	public void save(Java theJava) {
		javaDao.save(theJava);
	}

	@Override
	public void deleteById(int javaId) {
		javaDao.deleteById(javaId);
		
	}

}
