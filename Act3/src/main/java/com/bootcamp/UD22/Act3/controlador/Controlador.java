package com.bootcamp.UD22.Act3.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.bootcamp.UD22.Act3.modelo.Laboratorio;
import com.bootcamp.UD22.Act3.modelo.sql.SqlQuery;
import com.bootcamp.UD22.Act3.vista.Vista;

public class Controlador implements ActionListener {

	private Vista vista;
	private Laboratorio lab;
	private String tableChoosed = "cientifico";
	private String accionPulsada = "insert";
	private List<String> listSelect;

	public Controlador(Laboratorio lab, Vista vista) {
		this.vista = vista;
		this.lab = lab;

		SqlQuery crearTablas = new SqlQuery();
		crearTablas.SqlCrearTablas();
		lbObjeto();
		mostrarTextFieldInsert();
		addAllListeners();

		vista.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == vista.btnLimpiar) {
			vista.tf1.setText("");
			vista.tf2.setText("");
			vista.tf3.setText("");
			vista.ta.setText("");
		}
		if (e.getSource() == vista.cB_tipo) {
			tableChoosed = vista.cB_tipo.getSelectedItem().toString().toLowerCase();
		
			lbObjeto();
			mostrarTextFieldInsert();
		
		}
		if (e.getSource() == vista.btnInsert) {
			accionPulsada = "insert";
			lbObjeto();
			mostrarTextFieldInsert();

		}
		if (e.getSource() == vista.btnUpdate) {
			accionPulsada = "update";
			lbObjeto();
			vista.lb1.setText("Campo");
			vista.lb2.setText("Valor viejo");
			vista.lb3.setText("Valor nuevo");
			prepareTextFields(3);
		}
		if (e.getSource() == vista.btnSelect) {
			accionPulsada = "select";
			prepareTextFields(0);
			lbObjeto();
		}
		if (e.getSource() == vista.btnDelete) {
			accionPulsada = "delete";
			prepareTextFields(0);
			lbObjeto();
		}
		if (e.getSource() == vista.btnAplicar) {
			String guardarTextArea;
			switch (accionPulsada) {
			case "insert":
				switch (tableChoosed) {
				case "cientifico":
					guardarTextArea = vista.ta.getText().toString();
					try {
						lab.insertCientifico(vista.tf1.getText().toString(), vista.tf2.getText().toString());
						vista.ta.setText(guardarTextArea + "\nCientifico insertado ok " + mostrarFecha());
					} catch (SQLException e2) {
						vista.ta.setText(guardarTextArea + "\n" + e2.getMessage() + " " + mostrarFecha());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					break;
				case "proyecto":
					guardarTextArea = vista.ta.getText().toString();
					try {
						lab.insertProyecto(vista.tf1.getText().toString(), vista.tf2.getText().toString(),
								vista.tf3.getText().toString());
						vista.ta.setText(guardarTextArea + "\nProyecto insertado ok " + mostrarFecha());
					} catch (SQLException e2) {
						vista.ta.setText(guardarTextArea + "\n" + e2.getMessage() + " " + mostrarFecha());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "asignado":
					guardarTextArea = vista.ta.getText().toString();
					try {
						lab.insertAsignado(vista.tf1.getText().toString(), vista.tf2.getText().toString());
						vista.ta.setText(guardarTextArea + "\nAsignado insertado ok " + mostrarFecha());
					} catch (SQLException e2) {
						vista.ta.setText(guardarTextArea + "\n" + e2.getMessage() + " " + mostrarFecha());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				}
				break;
			case "update":
				switch (tableChoosed) {
				case "cientifico":
					guardarTextArea = vista.ta.getText().toString();
					try {
						lab.updateCientifico(vista.tf1.getText().toString(), vista.tf2.getText().toString(),
								vista.tf3.getText().toString());
						vista.ta.setText(guardarTextArea + "\nCientifico update ok " + mostrarFecha());
					} catch (SQLException e2) {
						vista.ta.setText(guardarTextArea + "\n" + e2.getMessage() + " " + mostrarFecha());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "proyecto":
					guardarTextArea = vista.ta.getText().toString();
					try {
						lab.updateProyecto(vista.tf1.getText().toString(), vista.tf2.getText().toString(),
								vista.tf3.getText().toString());
						vista.ta.setText(guardarTextArea + "\nProyecto update ok " + mostrarFecha());
					} catch (SQLException e2) {
						vista.ta.setText(guardarTextArea + "\n" + e2.getMessage() + " " + mostrarFecha());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "asignado":
					guardarTextArea = vista.ta.getText().toString();
					try {
						lab.updateAsignado(vista.tf1.getText().toString(), vista.tf2.getText().toString(),
								vista.tf3.getText().toString());
						vista.ta.setText(guardarTextArea + "\nAsignado update ok " + mostrarFecha());
					} catch (SQLException e2) {
						vista.ta.setText(guardarTextArea + "\n" + e2.getMessage() + " " + mostrarFecha());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

					break;
				default:
					break;
				}
				break;
			case "select":
				switch (tableChoosed) {
				case "cientifico":
					guardarTextArea = vista.ta.getText().toString();
					guardarTextArea = guardarTextArea + "\n--------------SELECT CIENTIFICO----------------\n";
					listSelect = lab.selectCientifico();
					for (int i = 0; i < listSelect.size(); i++) {

						vista.ta.setText(guardarTextArea + "\n " + listSelect.get(i));
						guardarTextArea = vista.ta.getText();
					}

					break;
				case "proyecto":
					guardarTextArea = vista.ta.getText().toString();
					guardarTextArea = guardarTextArea + "\n--------------SELECT PROYECTO----------------\n";
					listSelect = lab.selectProyecto();
					for (int i = 0; i < listSelect.size(); i++) {
						vista.ta.setText(guardarTextArea + "\n " + listSelect.get(i));
						guardarTextArea = vista.ta.getText();
					}
					break;
				case "asignado":
					guardarTextArea = vista.ta.getText().toString();
					guardarTextArea = guardarTextArea + "\n--------------SELECT ASIGNADO----------------\n";
					listSelect = lab.selectAsignado();
					for (int i = 0; i < listSelect.size(); i++) {
						vista.ta.setText(guardarTextArea + "\n " + listSelect.get(i));
						guardarTextArea = vista.ta.getText();
					}
					break;
				default:
					break;
				}
				break;
			case "delete":
				switch (tableChoosed) {
				case "cientifico":
					guardarTextArea = vista.ta.getText().toString();
					try {
						lab.deleteCientifico();
						vista.ta.setText(guardarTextArea + "\nRegistros cientifico borrados ok " + mostrarFecha());
					} catch (SQLException e2) {
						vista.ta.setText(guardarTextArea + "\n" + e2.getMessage() + " " + mostrarFecha());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "proyecto":
					guardarTextArea = vista.ta.getText().toString();
					try {
						lab.deleteProyecto();
						vista.ta.setText(guardarTextArea + "\nRegistros proyecto borrados ok " + mostrarFecha());
					} catch (SQLException e2) {
						vista.ta.setText(guardarTextArea + "\n" + e2.getMessage() + " " + mostrarFecha());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				case "asignado":
					guardarTextArea = vista.ta.getText().toString();
					try {
						lab.deleteAsignado();
						vista.ta.setText(guardarTextArea + "\nRegistros asignado borrados ok " + mostrarFecha());
					} catch (SQLException e2) {
						vista.ta.setText(guardarTextArea + "\n" + e2.getMessage() + " " + mostrarFecha());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					break;
				default:
					break;
				}
				break;
			default:
				break;
			}
		}

	}

	public void lbObjeto() {
		vista.lblObjeto.setText("Opcion " + accionPulsada + " para " + tableChoosed);
	}

	public void mostrarTextFieldInsert() {
		switch (tableChoosed) {
		case "cientifico":
			vista.lb1.setText("Dni");
			vista.lb2.setText("Nombre");
			prepareTextFields(2);
			break;
		case "proyecto":
			vista.lb1.setText("ID");
			vista.lb2.setText("Nombre");
			vista.lb3.setText("Hora");
			prepareTextFields(3);
			break;
		case "asignado":
			vista.lb1.setText("Cientifico");
			vista.lb2.setText("Proyecto");
			prepareTextFields(2);
			break;

		}
	}
	
	
	public void prepareTextFields(int totalFields) {
		hideFields();
		if (totalFields >= 1) {
			vista.lb1.setVisible(true);
			vista.tf1.setVisible(true);
		}
		if (totalFields >= 2) {
			vista.lb2.setVisible(true);
			vista.tf2.setVisible(true);
		}
		if (totalFields >= 3) {
			vista.lb3.setVisible(true);
			vista.tf3.setVisible(true);
		}

	}

	public String mostrarFecha() {
		LocalDateTime localDate = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String formattedLocalDate = localDate.format(formatter);
		return formattedLocalDate;
	}

	public void addAllListeners() {
		this.vista.btnAplicar.addActionListener(this);
		this.vista.btnDelete.addActionListener(this);
		this.vista.btnInsert.addActionListener(this);
		this.vista.btnSelect.addActionListener(this);
		this.vista.btnUpdate.addActionListener(this);
		this.vista.cB_tipo.addActionListener(this);
		this.vista.btnLimpiar.addActionListener(this);
	}

	public void hideFields() {
		vista.lb1.setVisible(false);
		vista.tf1.setVisible(false);
		vista.lb2.setVisible(false);
		vista.tf2.setVisible(false);
		vista.lb3.setVisible(false);
		vista.tf3.setVisible(false);
	}
}
