package com.bootcamp.UD22.Act3.modelo.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Sql {

	private static String user = "remote";
	private static String pswd = "Reus_2022";
	private static String ip = "192.168.88.123";
	private static Connection mysql = null;
	private static Statement statement = null; //creamos un statement global para que lo vean los demas metodos sin necesidad de crear otra nueva conexion
	
	public static Connection crearConnection() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String conection = "jdbc:mysql://"+ip+":3306?useTimezone=true&serverTimezone=UTC";
			mysql = DriverManager.getConnection(conection,user,pswd);
			System.out.println("conectado");
			statement = mysql.createStatement(); 
			return mysql;
		} catch (SQLException | ClassNotFoundException ex) {
			System.out.println("nose pudo conectar");
			System.out.println(ex);
			return mysql;
		}
	
	}
	public static void cerrarConnection() {//cerrar conexion
		try {
			mysql.close();
			System.out.println("\nSql cerrado correctamente\n");
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	
	public static void crearDB(String name) {
		try {
			String query = "CREATE DATABASE IF NOT EXISTS " + name;
			statement.executeUpdate(query);
			System.out.println("Tabla " + name + " creada correctamente.");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void inyeccionSQL(String db, String query)throws Exception {
		
	
			String querydb = "USE " + db + ";";
			statement.executeUpdate(querydb); // ejecutamos USE "database";
			statement.executeUpdate(query);		
		
	}

	public static List getValues(String db, String table_name) {
		List<String> listSelect = new ArrayList<String>();
		String text = "";
		try {
			String querydb = "USE "+db+";";
			statement.executeUpdate(querydb);
			
			String Query = "SELECT * FROM "+ table_name;
			java.sql.ResultSet resultSet = statement.executeQuery(Query); //resultset almacena los valores de la tabla por filas			
			ResultSetMetaData rsmd = resultSet.getMetaData(); //almacena otra informacion mas especifica, como las columnas con sus nombres
			int columnsNumber = rsmd.getColumnCount();
			System.out.println("-------SELECT "+ table_name+ "-----------");
			while(resultSet.next()) {
				text = "";
				for (int i = 1; i <= columnsNumber; i++) {
			        if (i > 1) System.out.print(",  ");
					  String columnValue = resultSet.getString(i);
				        System.out.print(columnValue + " " + rsmd.getColumnName(i));
				     text = text + columnValue.toString() + " "+ rsmd.getColumnName(i); 
				}
				
				listSelect.add(text);
				System.out.println("\n");
			}
			return listSelect;
		}catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}


}
