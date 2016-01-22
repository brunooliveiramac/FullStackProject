package br.com.guarani.rta.commons;

public class ProjetoSqlConstants {
	
	public static final String RETURN_LIST_OF_ALL_PROJECTS = "SELECT * FROM projeto";
	public static final String RETURN_PROJECT_BY_ID = "SELECT gua_pro_id, gua_pro_nome FROM projeto WHERE gua_pro_id = ?";
	public static final String INSERT_PROJECT = "INSERT INTO projeto (gua_pro_nome) VALUES (?)"; 
	public static final String UPDATE_PROJECT = "UPDATE projeto SET gua_pro_nome = ? WHERE gua_pro_id = ?";
	public static final String DELETE_PROJECT = "DELETE FROM gua_projeto WHERE gua_pro_id = ?";
	public static final String RETURN_LIST_USER_PROJECTS = "SELECT up.username, up.projetoid, p.gua_pro_id, p.gua_pro_nome FROM projeto p INNER JOIN user_projeto up ON up.projetoid = p.gua_pro_id WHERE up.username = ? ";
}
  