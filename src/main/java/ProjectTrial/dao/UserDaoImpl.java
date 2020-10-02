package ProjectTrial.dao;

import ProjectTrial.entity.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao{

	@Autowired
	private EntityManager entityManager;

	@Override
	public User findByUserName(String theUserName) {
		// get the current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// now retrieve/read from database using username
		Query<User> theQuery = currentSession.createQuery("from User where userName=:uName", User.class);
		theQuery.setParameter("uName", theUserName);
		User theUser;
		try {
			theUser = theQuery.getSingleResult();
		} catch (Exception e) {
			theUser = null;
		}

		return theUser;
	}

	@Override
	public void save(User theUser) {
		// get current hibernate session
		Session currentSession = entityManager.unwrap(Session.class);

		// create the user ... finally LOL
		currentSession.saveOrUpdate(theUser);

	}

	@Override
	public List<User> findAll() {	
		// get the current hibernate session
				Session currentSession = entityManager.unwrap(Session.class);

				// now retrieve/read from database using username
				Query<User> theQuery = currentSession.createQuery("from User", User.class);
				List<User> theUser;
				try {
					theUser = theQuery.getResultList();
				} catch (Exception e) {
					theUser = null;
				}
		return theUser;
	}

}
