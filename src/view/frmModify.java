package view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import controler.functionsCRUD;
import model.Evento;
import model.Room;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class frmModify extends JInternalFrame {
	functionsCRUD func = new functionsCRUD();
	ArrayList<Evento> event;
	int id;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmModify frame = new frmModify();
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
	public frmModify() {
		setTitle("Modificar fecha de evento");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		
		JDateChooser dcEvents = new JDateChooser();
		dcEvents.setBounds(10, 56, 130, 20);
		getContentPane().add(dcEvents);
		
		String format = "yyyy-MM-dd";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		
		JComboBox cbEvents = new JComboBox();
		cbEvents.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		cbEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = cbEvents.getSelectedItem().toString();
				for (int i = 0; i < event.size(); i++) {
					if(name == event.get(i).getNombre()) {
						String date = event.get(i).getFecha();
						Date dateCon = null;
						try {
							dateCon = sdf.parse(date);
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}

						dcEvents.setDate(dateCon);
					}
					
				}
				}
		});
		cbEvents.setBounds(10, 11, 208, 22);
		getContentPane().add(cbEvents);
		chargeCombo(cbEvents);
		
	
		
		JButton btnModify = new JButton("Actualizar");
		btnModify.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String dateCh = sdf.format(dcEvents.getDate());
				int id = func.getIdEvent(cbEvents.getSelectedItem().toString());
				String name = cbEvents.getSelectedItem().toString();
				for (int i = 0; i < event.size(); i++) {
					if(name == event.get(i).getNombre()) {
							event.get(i).setFecha(dateCh);
							if(func.modificarEvento(event.get(i), id)) {
								JOptionPane.showInternalMessageDialog(btnModify, "Evento actualizado");
							}
					}
				}
			}
		});
		
		btnModify.setBounds(231, 223, 178, 23);
		getContentPane().add(btnModify);

	}
	
	public void chargeCombo(JComboBox comboBox) {
		this.event = func.chargeEvents();
		try {
			for (int i = 0; i < event.size(); i++) {
				comboBox.addItem(event.get(i).getNombre());
			}
		}catch(Exception ex) {
			System.out.println("Error en la carga del comboBox"+ex);
		}
	
}
}
