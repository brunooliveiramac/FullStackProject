package br.com.guarani.rta.commons;

public class CampoSqlConstants {
	
	public static final String RETURN_LIST_OF_ALL_CAMPS_BY_PARAMS = "SELECT c.gua_cam_id, c.gua_cam_nome, c.gua_cam_tipo, c.gua_cam_tam, c.gua_fk_tabela, c.gua_fk_layout, t.gua_tab_id, t.gua_fk_layout, p.gua_pro_id, p.gua_pro_nome, cl.gua_cam_logs_nome, cl.gua_fk_campo, cl.gua_fk_logs_layout FROM gua_campo c INNER JOIN gua_projeto    p  ON p.gua_pro_id    = c.gua_fk_projeto INNER JOIN gua_tabela     t  ON c.gua_fk_tabela = t.gua_tab_id LEFT JOIN gua_campo_logs cl ON (c.gua_cam_id = cl.gua_fk_campo AND cl.gua_fk_logs_layout = 69) WHERE c.gua_fk_layout <= 69 AND t.gua_tab_id = 19 AND c.gua_fk_projeto = 11 ORDER BY c.gua_fk_layout";
	public static final String CAMP_BY_TABLE = null;
	public static final String RETURN_CAMP_BY_TABLE = "SELECT c.tabela_id, c.ativo, c.nulo, c.deci, c.tam, c.cprimaria, c.atributo_id, a.id, a.descricao, a.tipo FROM Campo c LEFT JOIN Atributo a ON c.atributo_id = a.id WHERE tabela_id = ?";

	

}
