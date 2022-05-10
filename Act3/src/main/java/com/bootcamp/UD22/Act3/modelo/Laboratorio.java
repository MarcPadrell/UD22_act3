package com.bootcamp.UD22.Act3.modelo;

import java.util.ArrayList;
import java.util.List;

import com.bootcamp.UD22.Act3.modelo.laboratorio.clases.Asignado;
import com.bootcamp.UD22.Act3.modelo.laboratorio.clases.Cientifico;
import com.bootcamp.UD22.Act3.modelo.laboratorio.clases.Proyecto;
import com.bootcamp.UD22.Act3.modelo.sql.Sql;

public class Laboratorio {

	private List<Cientifico> cientificos = new ArrayList<Cientifico>();
	private List<Proyecto> proyectos = new ArrayList<Proyecto>();
	private List<Asignado> asignados = new ArrayList<Asignado>();

	private String query;
	private final String db_laboratorio = "LABORATORIO";
	public Laboratorio() {

	}
	
	public void insertCientifico(String dni, String nombre) throws Exception {
		query = "INSERT INTO CIENTIFICO VALUES ('" + dni + "', '" + nombre + "')";
			Sql.crearConnection();
			Sql.inyeccionSQL(db_laboratorio, query);
			Sql.cerrarConnection();	
	}

	public void deleteCientifico() throws Exception{
		query = "DELETE * FROM CIENTIFICO";
			Sql.crearConnection();
			Sql.inyeccionSQL(db_laboratorio, query);
			Sql.cerrarConnection();	
	}

	public void updateCientifico(String field, String valueOld, String valueNew)throws Exception {
		query = "UPDATE CIENTIFICO SET "+ field +" = '" +valueNew+ "' WHERE " +field+" = "+valueOld;
			Sql.crearConnection();
			Sql.inyeccionSQL(db_laboratorio, query);
			Sql.cerrarConnection();	
	}
	
	public List<String> selectCientifico() {
		List<String> list = new ArrayList<String>();
		Sql.crearConnection();
		list = Sql.getValues(db_laboratorio, "CIENTIFICO");
		Sql.cerrarConnection();
		return list;
	}
	
	public void insertProyecto(String id, String nombre, String horas) throws Exception {
		query = "INSERT INTO PROYECTO VALUES ('" + id + "', '" + nombre + "'," +horas +"  );";
			Sql.crearConnection();
			Sql.inyeccionSQL(db_laboratorio, query);
			Sql.cerrarConnection();	
	}

	public void deleteProyecto() throws Exception{
		query = "DELETE * FROM PROYECTO";
			Sql.crearConnection();
			Sql.inyeccionSQL(db_laboratorio, query);
			Sql.cerrarConnection();	
	}

	public void updateProyecto(String field, String valueOld, String valueNew)throws Exception {
		query = "UPDATE PROYECTO SET "+ field +" = '" +valueNew+ "' WHERE " +field+ " = " +"'"+valueOld+"'";
			Sql.crearConnection();
			Sql.inyeccionSQL(db_laboratorio, query);
			Sql.cerrarConnection();	
	}

	public List<String> selectProyecto() {
		List<String> list = new ArrayList<String>();
		Sql.crearConnection();
		list = Sql.getValues(db_laboratorio, "PROYECTO");
		Sql.cerrarConnection();
		return list;
	}

	public void insertAsignado(String id_cientifico, String id_proyecto) throws Exception {
		query = "INSERT INTO ASIGNADO (dni, id_proyecto) VALUES ('" + id_cientifico + "', '" + id_proyecto + "');";
			Sql.crearConnection();
			Sql.inyeccionSQL(db_laboratorio, query);
			Sql.cerrarConnection();	
	}

	public void deleteAsignado() throws Exception{
		query = "DELETE * FROM ASIGNADO";
			Sql.crearConnection();
			Sql.inyeccionSQL(db_laboratorio, query);
			Sql.cerrarConnection();	
	}

	public void updateAsignado(String field, String valueOld, String valueNew)throws Exception {
		query = "UPDATE ASIGNADO SET "+ field +" = '" +valueNew+ "' WHERE " +field+" = "+valueOld;
			Sql.crearConnection();
			Sql.inyeccionSQL(db_laboratorio, query);
			Sql.cerrarConnection();	
	}

	public List<String> selectAsignado() {
		List<String> list = new ArrayList<String>();
		Sql.crearConnection();
		list = Sql.getValues(db_laboratorio, "ASIGNADO");
		Sql.cerrarConnection();
		return list;
	}
	public List<Cientifico> getCientificos() {
		return cientificos;
	}

	public void setCientificos(List<Cientifico> cientificos) {
		this.cientificos = cientificos;
	}

	public List<Proyecto> getProyectos() {
		return proyectos;
	}

	public void setProyectos(List<Proyecto> proyectos) {
		this.proyectos = proyectos;
	}

	public List<Asignado> getAsignados() {
		return asignados;
	}

	public void setAsignados(List<Asignado> asignados) {
		this.asignados = asignados;
	}
	
}