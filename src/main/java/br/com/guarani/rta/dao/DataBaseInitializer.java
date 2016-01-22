package br.com.guarani.rta.dao;

import java.util.Date;



import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.guarani.rta.dao.usuario.UserDao;
import br.com.guarani.rta.entidade.User;


/**
 * Initialize the database with some test entries.
 * 
 * @author Bruno 
 */
public class DataBaseInitializer
{


	private UserDao userDao;

	private PasswordEncoder passwordEncoder;


	protected DataBaseInitializer()
	{
		/* Default constructor for reflection instantiation */
	}


	public DataBaseInitializer(UserDao userDao, PasswordEncoder passwordEncoder)
	{
		this.userDao = userDao;
		this.passwordEncoder = passwordEncoder;
	}


	public void initDataBase()
	{
		
		
	/*	User userUser = new User("user", this.passwordEncoder.encode("user"));
		userUser.addRole("user");
		this.userDao.save(userUser);

		User adminUser = new User("admin", this.passwordEncoder.encode("admin"));
		adminUser.addRole("user");
		adminUser.addRole("admin");
		this.userDao.save(adminUser);*/


	}

}