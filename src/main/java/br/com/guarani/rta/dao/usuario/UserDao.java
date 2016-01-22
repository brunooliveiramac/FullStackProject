package br.com.guarani.rta.dao.usuario;



import org.springframework.security.core.userdetails.UserDetailsService;

import br.com.guarani.rta.dao.Dao;
import br.com.guarani.rta.entidade.User;

 
public interface UserDao extends Dao<User, Long>, UserDetailsService
{

	User findByName(String name);//Assinatura de busca do usuário.

}