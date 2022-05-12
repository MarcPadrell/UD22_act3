package com.bootcamp.UD22.Act3.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

import com.bootcamp.UD22.Act3.modelo.laboratorio.clases.Laboratorio;
import com.bootcamp.UD22.Act3.vista.Vista;

public class Controlador implements ActionListener {

	protected Vista vista;
	protected Laboratorio lab;
	protected String tableChoosed = "cientifico";
	protected String accionPulsada = "insert";
	private List<String> listSelect;
	protected String guardarTextArea;
	
	private String[] cBcientifico = {"dni","nombre"}; 	
	private String[] cBbproyecto = {"id", "nombre","hora"};
	private String[] cBasignado = {"dni", "id_proyecto"};
	private DefaultComboBoxModel<String> modelCientifico = new DefaultComboBoxModel<>(cBcientifico );
	private DefaultComboBoxModel<String> modelProyecto = new DefaultComboBoxModel<>(cBbproyecto );
	private DefaultComboBoxModel<String> modelAsignado = new DefaultComboBoxModel<>(cBasignado );
	
	public Controlador(Laboratorio lab, Vista vista) {
		this.vista = vista;
		this.lab = lab;
		lbObjeto();
		mostrarCampos();
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
			mostrarCampos();

		}
		if(e.getSource() == vista.cB_campo) {
			mostrarCampos();
		}
		if (e.getSource() == vista.btnInsert) {
			accionPulsada = "insert";
			lbObjeto();
			mostrarCampos();

		}
		if (e.getSource() == vista.btnUpdate) {
			accionPulsada = "update";
			lbObjeto();
			mostrarCampos();
		}
		if (e.getSource() == vista.btnSelect) {
			accionPulsada = "select";
			mostrarCampos();
			lbObjeto();
		}
		if (e.getSource() == vista.btnDelete) {
			accionPulsada = "delete";
			mostrarCampos();
			lbObjeto();
		}
		if (e.getSource() == vista.btnAplicar) {

			switch (accionPulsada) {
			case "insert":
				caseInsert();
				break;
			case "update":
				caseUpdate();
				break;
			case "select":
				caseSelect();
				break;
			case "delete":
				caseDelete();
				break;
			}
		}

	}

	public void lbObjeto() {
		vista.lblObjeto.setText("Opcion " + accionPulsada + " para " + tableChoosed);
	}

	public void mostrarCampos() {
		// tableChoosed accionPulsada

		mostrarTextFieldInsert();
		switch (accionPulsada) {
		case "insert":
			switch (tableChoosed) {
			case "cientifico":
				prepareTextFields(2,false);
				break;
			case "proyecto":
				prepareTextFields(3,false);
				break;
			case "asignado":
				prepareTextFields(2,false);
				break;
			}
			break;
		case "update":
			switch (tableChoosed) {
			case "cientifico":
				vista.cB_campo.setModel(modelCientifico);
				break;
			case "proyecto":
				vista.cB_campo.setModel(modelProyecto);
				break;
			case "asignado":
				vista.cB_campo.setModel(modelAsignado);
				break;
			}
			prepareTextFields(3,true);
			break;
		default:
			prepareTextFields(0,false);
			break;
		}

	}

	public void mostrarTextFieldInsert() {
		switch (tableChoosed) {
		case "cientifico":
			vista.lb1.setText("Dni");
			vista.lb2.setText("Nombre");
			
			break;
		case "proyecto":
			vista.lb1.setText("ID");
			vista.lb2.setText("Nombre");
			vista.lb3.setText("Hora");
	
			break;
		case "asignado":
			vista.lb1.setText("Cientifico");
			vista.lb2.setText("Proyecto");
			break;
		}
				
		switch (accionPulsada) {
		case "update":
			vista.lb1.setVisible(false);
			vista.cB_campo.setVisible(true);
			vista.lb1.setText("Campo");
			vista.lb2.setText("ValorViejo");
			vista.lb3.setText("ValorNuevo");
			break;

		default:
			vista.lb1.setVisible(true);
			vista.cB_campo.setVisible(false);
			break;
		}
	}
	
	public void  update() {
	
	}

	public void prepareTextFields(int totalFields,boolean isUpdate) {
		hideFields();
		if (totalFields >= 1) {
			vista.lb1.setVisible(true);
			if(isUpdate) {
				vista.cB_campo.setVisible(true);
				vista.tf1.setVisible(false);	
			}else {
				vista.cB_campo.setVisible(false);
				vista.tf1.setVisible(true);	
			}
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

	public void caseInsert() {
		switch (tableChoosed) {
		case "cientifico":
			guardarTextArea = vista.ta.getText().toString();
			try {
				lab.getCientifico().insertCientifico(vista.tf1.getText().toString(), vista.tf2.getText().toString());
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
				lab.getProyecto().insertProyecto(vista.tf1.getText().toString(), vista.tf2.getText().toString(),
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
				lab.getAsignado().insertAsignado(vista.tf1.getText().toString(), vista.tf2.getText().toString());
				vista.ta.setText(guardarTextArea + "\nAsignado insertado ok " + mostrarFecha());
			} catch (SQLException e2) {
				vista.ta.setText(guardarTextArea + "\n" + e2.getMessage() + " " + mostrarFecha());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void caseUpdate() {
		switch (tableChoosed) {
		case "cientifico":
			guardarTextArea = vista.ta.getText().toString();
			try {
				lab.getCientifico().updateCientifico(vista.cB_campo.getSelectedItem().toString(), vista.tf2.getText().toString(),
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
				lab.getProyecto().updateProyecto(vista.cB_campo.getSelectedItem().toString(), vista.tf2.getText().toString(),
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
				lab.getAsignado().updateAsignado(vista.cB_campo.getSelectedItem().toString(), vista.tf2.getText().toString(),
						vista.tf3.getText().toString());
				vista.ta.setText(guardarTextArea + "\nAsignado update ok " + mostrarFecha());
			} catch (SQLException e2) {
				vista.ta.setText(guardarTextArea + "\n" + e2.getMessage() + " " + mostrarFecha());
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			break;

		}
	}

	public void caseSelect() {
		switch (tableChoosed) {
		case "cientifico":
			guardarTextArea = vista.ta.getText().toString();
			guardarTextArea = guardarTextArea + "\n--------------SELECT CIENTIFICO----------------\n";
			try {
				listSelect = lab.getCientifico().selectCientifico();
				for (int i = 0; i < listSelect.size(); i++) {

					vista.ta.setText(guardarTextArea + "\n " + listSelect.get(i));
					guardarTextArea = vista.ta.getText();
				}
			} catch (Exception e2) {
				vista.ta.setText(vista.ta.getText() + "\n" + e2.getMessage());
			}
			break;
		case "proyecto":
			guardarTextArea = vista.ta.getText().toString();
			guardarTextArea = guardarTextArea + "\n--------------SELECT PROYECTO----------------\n";
			try {
				listSelect = lab.getProyecto().selectProyecto();
				for (int i = 0; i < listSelect.size(); i++) {
					vista.ta.setText(guardarTextArea + "\n " + listSelect.get(i));
					guardarTextArea = vista.ta.getText();
				}
			} catch (Exception e2) {
				vista.ta.setText(vista.ta.getText() + "\n" + e2.getMessage());
			}
			break;
		case "asignado":
			guardarTextArea = vista.ta.getText().toString();
			guardarTextArea = guardarTextArea + "\n--------------SELECT ASIGNADO----------------\n";
			try {
				listSelect = lab.getAsignado().selectAsignado();
				for (int i = 0; i < listSelect.size(); i++) {
					vista.ta.setText(guardarTextArea + "\n " + listSelect.get(i));
					guardarTextArea = vista.ta.getText();
				}
			} catch (Exception e2) {
				vista.ta.setText(vista.ta.getText() + "\n" + e2.getMessage());
			}
			break;

		}

	}

	public void caseDelete() {
		switch (tableChoosed) {
		case "cientifico":
			guardarTextArea = vista.ta.getText().toString();
			try {
				lab.getCientifico().deleteCientifico();
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
				lab.getProyecto().deleteProyecto();
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
				lab.getAsignado().deleteAsignado();
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
	}

	public Vista getVista() {
		return vista;
	}

	public void setVista(Vista vista) {
		this.vista = vista;
	}

	public Laboratorio getLab() {
		return lab;
	}

	public void setLab(Laboratorio lab) {
		this.lab = lab;
	}

}
