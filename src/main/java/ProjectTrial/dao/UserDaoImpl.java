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

		/*
		 * Query theQuery =
		 * currentSession.createSQLQuery("CREATE TEMPORARY TABLE `user_answer` (\n" +
		 * "  `user_id` int(11) NOT NULL AUTO_INCREMENT,\n" +
		 * "  `user_answer` varchar(255) NOT NULL,\n" + "  PRIMARY KEY (`id`)\n" +
		 * ") ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;");
		 * System.out.println("temporary table created for user" + theUser);
		 
		System.out.println(theQuery);*/
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
