package com.bootcamp.UD22.Act3.modelo.laboratorio.clases;

import java.util.ArrayList;
import java.util.List;

import com.bootcamp.UD22.Act3.modelo.sql.Sql;

public class Proyecto {
	
	private String query;
	private Sql sql = new Sql();
	protected final String DB = "LABORATORIO";
	
	public Proyecto() {
		crearTabla();
	}
	public void crearTabla() {
		query = "CREATE TABLE IF NOT EXISTS PROYECTO ("
				+ "id char(4) primary key,"
				+ "nombre nvarchar(255),"
				+ "horas int);";
		try {
			sql.inyeccionSQL(DB, query);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void insertProyecto(String id, String nombre, String horas) throws Exception {
		query = "INSERT INTO PROYECTO VALUES ('" + id + "', '" + nombre + "'," + horas + "  );";
		sql.inyeccionSQL(DB, query);
	}

	public void deleteProyecto() throws Exception {
		query = "DELETE FROM PROYECTO";
		sql.inyeccionSQL(DB, query);
	}

	public void updateProyecto(String field, String valueOld, String valueNew) throws Exception {
		query = "UPDATE PROYECTO SET " + field + " = '" + valueNew + "' WHERE " + field + " = " + "'" + valueOld + "'";
		sql.inyeccionSQL(DB, query);
	}

	public List<String> selectProyecto() throws Exception {
		List<String> list = new ArrayList<String>();
		list = sql.getValues(DB, "PROYECTO");

		return list;
	}
}
