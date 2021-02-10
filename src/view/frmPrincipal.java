package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class frmPrincipal extends JFrame {
	private JDesktopPane dpPrincipal;
	private frmRegistrar fr;
	private frmQuery fq;
	private frmModify fm;
	private frmDelete fd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frmPrincipal frame = new frmPrincipal();
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
	public frmPrincipal() {
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		setTitle("Eventos Cindy");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("Registro");
		mnNewMenu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("Registrar evento");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarRegistrar();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnModify = new JMenu("Modificar");
		mnModify.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnModify);

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Modificar evento");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarModificar();
			}
		});
		mnModify.add(mntmNewMenuItem_1);

		JMenu mnNewMenu_1 = new JMenu("Consultar");
		mnNewMenu_1.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_1);

		JMenuItem mntmQueryEvents = new JMenuItem("Consultar eventos");
		mntmQueryEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarConsultar();
			}
		});
		mnNewMenu_1.add(mntmQueryEvents);

		JMenu mnNewMenu_2 = new JMenu("Eliminar");
		mnNewMenu_2.setFont(new Font("Segoe UI", Font.PLAIN, 16));
		menuBar.add(mnNewMenu_2);

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Eliminar evento");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cargarBorrar();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_2);

		dpPrincipal = new JDesktopPane();
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGap(0)
						.addComponent(dpPrincipal, GroupLayout.DEFAULT_SIZE, 457, Short.MAX_VALUE)
						.addGap(0))
				);
		groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(dpPrincipal, GroupLayout.DEFAULT_SIZE, 251, Short.MAX_VALUE)
						.addGap(0))
				);
		getContentPane().setLayout(groupLayout);

	}

	void cargarRegistrar() {
		if(fr == null || fr.isClosed()) {
			fr = new frmRegistrar();
			fr.setVisible(true);
			dpPrincipal.add(fr);
			Dimension tf = fr.getSize();
			fr.setLocation((dpPrincipal.getWidth()-tf.width)/2,( dpPrincipal.getHeight()-tf.height)/2);
		}

	}
	void cargarModificar() {
		if(fm == null || fm.isClosed()) {
			fm = new frmModify();
			fm.setVisible(true);
			dpPrincipal.add(fm);
			Dimension tf = fm.getSize();
			fm.setLocation((dpPrincipal.getWidth()-tf.width)/2, (dpPrincipal.getHeight()-tf.height)/2);
		}
	}
	void cargarConsultar() {
		if(fq == null || fq.isClosed()) {
			fq = new frmQuery();
			fq.setVisible(true);
			dpPrincipal.add(fq);
			Dimension tf = fq.getSize();
			fq.setLocation((dpPrincipal.getWidth()-tf.width)/2, (dpPrincipal.getHeight()-tf.height)/2);
		}
	}

	void cargarBorrar() {
		if(fd == null || fd.isClosed()) {
			fd = new frmDelete();
			fd.setVisible(true);
			dpPrincipal.add(fd);
			Dimension tf = fd.getSize();
			fd.setLocation((dpPrincipal.getWidth()-tf.width)/2, (dpPrincipal.getHeight()-tf.height)/2);
		}
	}
}
