package view;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import controler.functionsCRUD;
import model.Evento;

import java.awt.BorderLayout;
import com.toedter.calendar.JMonthChooser;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.beans.PropertyChangeEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class frmQuery extends JInternalFrame {
	
	functionsCRUD func = new functionsCRUD();
	private JTable tblEvents;
	DefaultTableModel modelo = null;
	ArrayList<Evento> events;
	Calendar calendar = Calendar.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmQuery frame = new frmQuery();
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
	public frmQuery() {
		this.events = func.chargeEvents();
		
		setTitle("Consultar eventos");
		setClosable(true);
		setBounds(100, 100, 528, 364);
		getContentPane().setLayout(null);
		
		tblEvents = new JTable();
		tblEvents.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		tblEvents.setBounds(10, 68, 492, 255);
		getContentPane().add(tblEvents);
		
		modelo = new DefaultTableModel();
		
		String[] columns = {"Nombre", "Fecha ", "Puestos", "descripción", "Salón"};
		
		modelo.setColumnIdentifiers(columns);
				
		JMonthChooser monthChooser = new JMonthChooser();
		monthChooser.getComboBox().setFont(new Font("Segoe UI", Font.PLAIN, 13));
		monthChooser.setBounds(33, 31, 100, 20);
		getContentPane().add(monthChooser);
		
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
				
		JButton btnNewButton = new JButton("Filtrar");
		btnNewButton.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int month = monthChooser.getMonth();
				for (int i = 0; i < events.size(); i++) {
					try {
						String dateCh = events.get(i).getFecha();
						Date fecha = (Date) sdf.parse(dateCh);
						calendar.setTime(fecha);
						int mont = calendar.MONTH;
						System.out.println(month+1 +"  "+mont );
						if(mont == month+1) {
							String nameEvent = events.get(i).getNombre();					
							int numPeo = events.get(i).getCdtPersonas();
							String desc = events.get(i).getDescripcion();
							String salon = func.getNameRoom(events.get(i).getIdSalon());
							modelo.addRow(new Object[] {nameEvent, dateCh, numPeo, desc, salon});
							
						}
												

					}catch (ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				
				}
				tblEvents.setModel(modelo);
				
			}
		});
		btnNewButton.setBounds(157, 31, 89, 23);
		getContentPane().add(btnNewButton);
			

	}
}
