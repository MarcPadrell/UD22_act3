package com.bootcamp.UD22.Act3.modelo.laboratorio.clases;

import com.bootcamp.UD22.Act3.modelo.sql.Sql;

public class Cientifico {

	private String dni_cientifico;
	private String nomApels_cientifico;
	private String query_cientifico;
	private String db = "LABORATORIO";

	public Cientifico() {

	}

	public String getDni_cientifico() {
		return dni_cientifico;
	}

	public void setDni_cientifico(String dni_cientifico) {
		this.dni_cientifico = dni_cientifico;
	}

	public String getNomApels_cientifico() {
		return nomApels_cientifico;
	}

	public void setNomApels_cientifico(String nomApels_cientifico) {
		this.nomApels_cientifico = nomApels_cientifico;
	}

	public String getQuery_cientifico() {
		return query_cientifico;
	}

	public void setQuery_cientifico(String query_cientifico) {
		this.query_cientifico = query_cientifico;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	

	
}
