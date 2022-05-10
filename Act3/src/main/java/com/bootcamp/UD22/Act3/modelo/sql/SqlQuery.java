package com.bootcamp.UD22.Act3.modelo.sql;

public class SqlQuery {
	
	private String DB = "LABORATORIO";
	private String queryCrearCientifico = "CREATE TABLE IF NOT EXISTS CIENTIFICO ("
			+ "dni varchar(8) primary key,"
			+ "nomapels nvarchar(255));";
	private String queryCrearProyecto = "CREATE TABLE IF NOT EXISTS PROYECTO ("
			+ "id char(4) primary key,"
			+ "nombre nvarchar(255),"
			+ "horas int);";
	private String queryCrearAsignado = "CREATE TABLE IF NOT EXISTS ASIGNADO ("
			+ "id int auto_increment primary key,"
			+ "dni varchar(8),"
			+ "id_proyecto char(4),"
			+ "key (dni, id_proyecto),"
			+ "FOREIGN KEY (dni) REFERENCES CIENTIFICO (dni),"
			+ "FOREIGN KEY (id_proyecto) REFERENCES PROYECTO (id))";

	public void SqlCrearTablas() {
		Sql.crearConnection();
		System.out.println(("creado"));
		Sql.crearDB(DB);		
		try {
			Sql.inyeccionSQL(DB, queryCrearCientifico);
			Sql.inyeccionSQL(DB, queryCrearProyecto);
			Sql.inyeccionSQL(DB, queryCrearAsignado);
		}catch (Exception e) {
			// TODO: handle exception
		}
		Sql.cerrarConnection();
	}

}
