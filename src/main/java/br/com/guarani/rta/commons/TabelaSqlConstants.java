package br.com.guarani.rta.commons;

public class TabelaSqlConstants {
	
	public static final String RETURN_LIST_TABLES_BY_VERSION = "SELECT t.gua_tab_id, t.gua_tab_nome, t.gua_fk_layout, t.gua_fk_projeto, p.gua_pro_id, p.gua_pro_nome FROM gua_tabela t INNER JOIN gua_projeto p ON p.gua_pro_id = t.gua_fk_projeto WHERE t.gua_fk_layout <= ? AND t.gua_fk_projeto = ?";
	public static final String INSERT_TABLE = "INSERT INTO gua_tabela (gua_tab_nome, gua_fk_layout, gua_fk_projeto) values (?, ?, ?)";
	public static final String RETURN_TABLE_BY_ID = "SELECT t.gua_tab_id, t.gua_tab_nome, t.gua_fk_layout, t.gua_fk_projeto, l.gua_lay_num_ver, p.gua_pro_nome, p.gua_pro_id FROM gua_tabela t INNER JOIN gua_projeto p ON t.gua_fk_projeto = p.gua_pro_id INNER JOIN gua_layout l ON t.gua_fk_layout = l.gua_lay_num_ver WHERE gua_tab_id = ?";
	public static final String RETURN_TAB_SUN = "SELECT t.gua_tab_nome, t.gua_tab_id, t.gua_fk_layout, t.gua_fk_projeto, p.gua_pro_id, p.gua_pro_nome, l.gua_lay_id, l.gua_lay_num_ver, tf.gua_tabmae_id, tf.gua_tabfil_id FROM gua_tabela t INNER JOIN gua_tabela_filha tf ON t.gua_tab_id = tf.gua_tabmae_id WHERE t.gua_tab_id = ?";
			
} 
  
