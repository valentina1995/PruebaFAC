package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import controler.HighlightDate;
import controler.functionsCRUD;
import model.Evento;
import model.Room;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.TextArea;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.awt.Label;
import javax.swing.JComboBox;
import com.toedter.calendar.JCalendar;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class frmRegistrar extends JInternalFrame {
	private JTextField txtEventoSinTitulo;
	private JTextField txtNum;
	functionsCRUD func = new functionsCRUD();
	ArrayList<Date> noAvailable;
	HighlightDate evaluator = new HighlightDate();
	JCalendar jc = new JCalendar();




	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmRegistrar frame = new frmRegistrar();
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
	public frmRegistrar() {

		highLightEvents(1);

		setClosable(true);
		setTitle("Registrar eventos");
		setBounds(100, 100, 572, 400);
		getContentPane().setLayout(null);

		txtEventoSinTitulo = new JTextField();
		txtEventoSinTitulo.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		txtEventoSinTitulo.setText("Evento sin titulo");
		txtEventoSinTitulo.setBounds(10, 11, 288, 20);
		getContentPane().add(txtEventoSinTitulo);
		txtEventoSinTitulo.setColumns(10);

		txtNum = new JTextField();
		txtNum.setBounds(10, 147, 86, 20);
		getContentPane().add(txtNum);
		txtNum.setColumns(10);

		JLabel lblCantidadDePersonas = new JLabel("Cantidad de personas");
		lblCantidadDePersonas.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		lblCantidadDePersonas.setBounds(106, 146, 192, 20);
		getContentPane().add(lblCantidadDePersonas);

		TextArea txtDesc = new TextArea();
		txtDesc.setBounds(10, 232, 396, 128);
		getContentPane().add(txtDesc);

		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = comboBox.getSelectedItem().toString();
				int id = func.getId(name);
				highLightEvents(id);
				
			}
		});
		comboBox.setBounds(10, 52, 151, 22);
		getContentPane().add(comboBox);
		chargeCombo(comboBox);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(10, 85, 119, 20);
		getContentPane().add(dateChooser);

		String format = "MM-dd-yyyy";
		SimpleDateFormat sdf = new SimpleDateFormat(format);

		JButton btnNewButton = new JButton("Guardar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nameEvent = String.valueOf(txtEventoSinTitulo.getText())	;
				String dateCh = sdf.format(dateChooser.getDate());
				int numPeo = Integer.valueOf(txtNum.getText());
				String desc = txtDesc.getText();
				int idRoom = func.getId(comboBox.getSelectedItem().toString());		
				Evento event = new Evento(nameEvent, dateCh, numPeo, desc, idRoom);
				System.out.println(event.toString());
				if(func.crearEvento(event)) {
					JOptionPane.showMessageDialog(btnNewButton, "Evento guardado");
				}
			}
		});
		btnNewButton.setBounds(438, 324, 89, 23);
		getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Disponibilidad");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Segoe UI Historic", Font.PLAIN, 16));
		lblNewLabel.setBounds(308, 14, 206, 33);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Fecha del evento");
		lblNewLabel_1.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		lblNewLabel_1.setBounds(152, 85, 146, 20);
		getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Descripci\u00F3n :");
		lblNewLabel_2.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		lblNewLabel_2.setBounds(10, 204, 205, 22);
		getContentPane().add(lblNewLabel_2);
		
		JLabel lblSaln = new JLabel("Sal\u00F3n");
		lblSaln.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		lblSaln.setBounds(171, 56, 86, 20);
		getContentPane().add(lblSaln);
		
		jc.getDayChooser().getDayPanel().addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) {
				Date date  = jc.getDate();
				dateChooser.setDate(date);
				System.out.println(date);
				
			}
		});


	}

	public void chargeCombo(JComboBox comboBox) {
		ArrayList<Room> room = func.chargeRoom();
		String name;
		try {
			for (int i = 0; i < room.size(); i++) {
				comboBox.addItem(room.get(i).getNameRoom());
			}
		}catch(Exception ex) {
			System.out.println("Error en la carga del comboBOx"+ex);
		}
		
		

	}

	void highLightEvents(int idRoom) {
		this.jc.getDayChooser().removeDateEvaluator(evaluator);

		this.jc.setCalendar(this.jc.getCalendar());
		
		int d;
		int m;
		
		noAvailable = func.fullDate(idRoom);
		System.out.println(noAvailable.toString());
		Calendar calendario = Calendar.getInstance();
		for (int i = 0; i < noAvailable.size(); i++) {
			calendario.setTime(noAvailable.get(i));
			d = calendario.get(Calendar.DAY_OF_MONTH);
			m = calendario.get(Calendar.MONTH);
			evaluator.add(createDate(d,m));	

		}	
		this.jc.getDayChooser().addDateEvaluator(evaluator);
		this.jc.setCalendar(this.jc.getCalendar());
		this.jc.setBounds(308, 57, 205, 153);
		getContentPane().add(this.jc);

	}
	private Date createDate(int d, int m) {
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, m);
		c.set(Calendar.DAY_OF_MONTH, d);
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		return (c.getTime());
	}
}
