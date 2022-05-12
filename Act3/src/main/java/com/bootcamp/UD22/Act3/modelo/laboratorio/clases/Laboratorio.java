package com.bootcamp.UD22.Act3.modelo.laboratorio.clases;

import java.util.ArrayList;
import java.util.List;

import com.bootcamp.UD22.Act3.modelo.sql.Sql;

public class Laboratorio {

	private Cientifico cientifico;
	private Proyecto proyecto;
	private Asignado asignado;
	private String query;
	protected final String DB = "LABORATORIO";
	protected Sql sql = new Sql();

	public Laboratorio() {
		crearDBVideoClub();
		this.cientifico = new Cientifico();
		this.proyecto = new Proyecto();
		this.asignado = new Asignado();
	}
	
	//crearDB
	public void crearDBVideoClub() {
		sql.crearDB(DB);
	}

	public Cientifico getCientifico() {
		return cientifico;
	}

	public Proyecto getProyecto() {
		return proyecto;
	}

	public Asignado getAsignado() {
		return asignado;
	}

}