package com.bootcamp.UD22.Act3.modelo.laboratorio.clases;

import java.util.ArrayList;
import java.util.List;

import com.bootcamp.UD22.Act3.modelo.sql.Sql;

public class Cientifico {


	private String query;
	private Sql sql = new Sql();
	protected final String DB = "LABORATORIO";
	
	public Cientifico() {
		crearTabla();
	}
	public void crearTabla() {
		query = "CREATE TABLE IF NOT EXISTS CIENTIFICO ("
				+ "dni varchar(8) primary key,"
				+ "nomapels nvarchar(255));";
		try {
			sql.inyeccionSQL(DB, query);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertCientifico(String dni, String nombre) throws Exception {
		query = "INSERT INTO CIENTIFICO VALUES ('" + dni + "', '" + nombre + "')";
		sql.inyeccionSQL(DB, query);
	}

	public void deleteCientifico() throws Exception {
		query = "DELETE FROM CIENTIFICO";
		sql.inyeccionSQL(DB, query);
	}

	public void updateCientifico(String field, String valueOld, String valueNew) throws Exception {
		query = "UPDATE CIENTIFICO SET " + field + " = '" + valueNew + "' WHERE " + field + " = '" + valueOld+"'";
		sql.inyeccionSQL(DB, query);
	}

	public List<String> selectCientifico()throws Exception {
		List<String> list = new ArrayList<String>();
		list = sql.getValues(DB, "CIENTIFICO");
		return list;
	}

	
}
