package view;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;

import controler.functionsCRUD;
import model.Evento;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class frmDelete extends JInternalFrame {
	
	functionsCRUD func = new functionsCRUD();
	ArrayList<Evento> events;
	private JTable table;
	DefaultTableModel modelo = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmDelete frame = new frmDelete();
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
	public frmDelete() {
		setTitle("Eliminar evento");
		setClosable(true);
		setBounds(100, 100, 450, 300);
		
		this.events = func.listarEventos();
		getContentPane().setLayout(null);
		
		table = new JTable();
		table.setBounds(0, 0, 434, 221);
		getContentPane().add(table);

		this.modelo = new DefaultTableModel();

		cargarTabla(table);
		
		JButton btnDelete = new JButton("Eliminar evento");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				if(row != -1) {
					if(func.borrarEvento(events.get(row).getId())) {
						cargarTabla(table);
						JOptionPane.showInternalMessageDialog(btnDelete, "Evento eliminado");
					}
				}else {
					JOptionPane.showInternalMessageDialog(btnDelete, "Selecciona una fila para borrar");
				}
			}
		});
		btnDelete.setBounds(259, 236, 144, 23);
		getContentPane().add(btnDelete);
		
		

		

	}
	
	void cargarTabla(JTable table) {
		String[] columns = {"Nombre", "Fecha ", "Puestos", "descripción", "Salón"};
		this.modelo.setColumnIdentifiers(columns);
		this.modelo.setRowCount(0);

		for (Evento evento : events) {
			String dateCh = evento.getFecha();
			String nameEvent = evento.getNombre();				
			int numPeo = evento.getCdtPersonas();
			String desc = evento.getDescripcion();
			String salon = func.getNameRoom(evento.getIdSalon());
			this.modelo.addRow(new Object[] {nameEvent, dateCh, numPeo, desc, salon});		
		}
		table.setModel(this.modelo);
		this.modelo.fireTableDataChanged();
		
	}
	
	void limpiarTabla(JTable table) {
		this.modelo.setRowCount(0);
		
	}
}
