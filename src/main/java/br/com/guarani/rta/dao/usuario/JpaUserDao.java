package br.com.guarani.rta.dao.usuario;

import java.util.List;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;



import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import br.com.guarani.rta.dao.JpaDao;
import br.com.guarani.rta.entidade.User;


public class JpaUserDao extends JpaDao<User, Long> implements UserDao
{

	public JpaUserDao(){ //Extend da Classe JpaDao onde h� EntityManager
		
	super(User.class);
	
	}


	@Override
	@Transactional(readOnly = true)//Regras de N�gocio.
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = this.findByName(username);
		if (null == user) {
			throw new UsernameNotFoundException("O usuario com o nome " + username + " N�o foi encontrado");
		}

		return user;
	}


	@Override
	@Transactional(readOnly = true)
	public User findByName(String name){
		final CriteriaBuilder builder = this.getEntityManager().getCriteriaBuilder();
		
		final CriteriaQuery<User> criteriaQuery = builder.createQuery(this.entityClass);

		Root<User> root = criteriaQuery.from(this.entityClass);//Root: classe do Hibernate. Root � sempre uma entidade.
		Path<String> namePath = root.get("name");//Path: pega parametro da entidade.
		criteriaQuery.where(builder.equal(namePath, name));//Se parametro de entrada dor igual � path.

		TypedQuery<User> typedQuery = this.getEntityManager().createQuery(criteriaQuery);
		List<User> users = typedQuery.getResultList();

		if (users.isEmpty()) {
			return null;
		}

		return users.iterator().next();//Interador: se h�, vai para o pr�ximo.
	}

}
