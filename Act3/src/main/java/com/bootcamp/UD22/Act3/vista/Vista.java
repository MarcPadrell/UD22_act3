package com.bootcamp.UD22.Act3.vista;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;

public class Vista extends JFrame {

	private JPanel contentPane;
	public JTextField tf1,tf2,tf3;
	public JButton btnUpdate, btnInsert,btnDelete, btnSelect, btnAplicar;
	public JComboBox cB_tipo,cB_campo;
	public JTextArea ta;
	public JLabel  lb1, lb2, lb3 , lblObjeto;
	public JButton btnLimpiar;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista frame = new Vista();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Vista() {
		setTitle("CRUD");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1281, 387);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnUpdate = new JButton("Update");
		btnUpdate.setBounds(132, 18, 89, 23);
		contentPane.add(btnUpdate);
		
		btnInsert = new JButton("Insert");
		btnInsert.setBounds(33, 18, 89, 23);
		contentPane.add(btnInsert);
		
		btnDelete = new JButton("Delete");
		btnDelete.setBounds(132, 52, 89, 23);
		contentPane.add(btnDelete);
		
		btnSelect = new JButton("Select");
		btnSelect.setBounds(33, 52, 89, 23);
		contentPane.add(btnSelect);
		
		String[] opciones = {"Cientifico", "Proyecto", "Asignado"};
		cB_tipo = new JComboBox(opciones);
		cB_tipo.setBounds(33, 111, 188, 22);
		contentPane.add(cB_tipo);
		
		lblObjeto= new JLabel("Objeto");
		lblObjeto.setBounds(33, 86, 188, 14);
		contentPane.add(lblObjeto);
		
		lb1 = new JLabel("");
		lb1.setBounds(33, 144, 89, 14);
		contentPane.add(lb1);
		
		tf1 = new JTextField();
		tf1.setBounds(33, 156, 86, 20);
		contentPane.add(tf1);
		tf1.setColumns(10);
		
		tf2 = new JTextField();
		tf2.setColumns(10);
		tf2.setBounds(33, 202, 86, 20);
		contentPane.add(tf2);
		
		lb2 = new JLabel("");
		lb2.setBounds(33, 189, 89, 14);
		contentPane.add(lb2);
		
		lb3 = new JLabel("");
		lb3.setBounds(33, 233, 86, 14);
		contentPane.add(lb3);
		
		tf3 = new JTextField();
		tf3.setBounds(33, 248, 86, 20);
		contentPane.add(tf3);
		tf3.setColumns(10);
		
		btnAplicar = new JButton("Aplicar");
		btnAplicar.setBounds(132, 155, 89, 23);
		contentPane.add(btnAplicar);
		
		ta = new JTextArea();
		ta.setBounds(282, 17, 970, 320);
		contentPane.add(ta);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.setBounds(132, 201, 89, 23);
		contentPane.add(btnLimpiar);
		
		cB_campo = new JComboBox();
		cB_campo.setBounds(33, 155, 89, 22);
		contentPane.add(cB_campo);
		cB_campo.setVisible(false);
	}
}
