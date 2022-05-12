package com.bootcamp.UD22.Act3.modelo.laboratorio.clases;

import java.util.ArrayList;
import java.util.List;

import com.bootcamp.UD22.Act3.modelo.sql.Sql;

public class Asignado{

	private String query;
	private Sql sql = new Sql();
	protected final String DB = "LABORATORIO";
	public Asignado() {
		crearTabla();
	}

	public void crearTabla() {
		query = "CREATE TABLE IF NOT EXISTS ASIGNADO ("
				+ "id int auto_increment primary key,"
				+ "dni varchar(8),"
				+ "id_proyecto char(4),"
				+ "key (dni, id_proyecto),"
				+ "FOREIGN KEY (dni) REFERENCES CIENTIFICO (dni),"
				+ "FOREIGN KEY (id_proyecto) REFERENCES PROYECTO (id))";
		try {
			sql.inyeccionSQL(DB, query);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertAsignado(String id_cientifico, String id_proyecto) throws Exception {
		query = "INSERT INTO ASIGNADO (dni, id_proyecto) VALUES ('" + id_cientifico + "', '" + id_proyecto + "');";
		sql.inyeccionSQL(DB, query);
	}

	public void deleteAsignado() throws Exception {
		query = "DELETE FROM ASIGNADO";
		sql.inyeccionSQL(DB, query);
	}

	public void updateAsignado(String field, String valueOld, String valueNew) throws Exception {
		query = "UPDATE ASIGNADO SET " + field + " = '" + valueNew + "' WHERE " + field + " =  '" + valueOld + "'";
		sql.inyeccionSQL(DB, query);
	}

	public List<String> selectAsignado() throws Exception {
		List<String> list = new ArrayList<String>();
		list = sql.getValues(DB, "ASIGNADO");
		return list;
	}
}
