package com.bootcamp.UD22.Act3.mainApp;

import com.bootcamp.UD22.Act3.controlador.Controlador;
import com.bootcamp.UD22.Act3.modelo.Laboratorio;
import com.bootcamp.UD22.Act3.vista.Vista;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    { 	
       	Laboratorio lab = new Laboratorio();
       	Vista vista= new Vista();
       	Controlador controlador = new Controlador(lab,vista);
    }
}
