package java_swing_erp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java_swing_erp.ui.component.DepartmentManagement;
import java_swing_erp.ui.component.StudentManagement;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Erp extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JButton btnDepartment;
	private JButton btnStudent;
	private StudentManagement stdFrame;
	private DepartmentManagement dptFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Erp frame = new Erp();
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
	public Erp() {
		initComponents();
	}
	private void initComponents() {
		setTitle("학사관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 416, 128);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(1, 0, 0, 0));
		
		btnStudent = new JButton("학생관리");
		btnStudent.addActionListener(this);
		contentPane.add(btnStudent);
		
		btnDepartment = new JButton("학과관리");
		btnDepartment.addActionListener(this);
		contentPane.add(btnDepartment);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDepartment) {
			actionPerformedBtnDepartment(e);
		}
		if (e.getSource() == btnStudent) {
			actionPerformedBtnStudent(e);
		}
	}
	protected void actionPerformedBtnStudent(ActionEvent e) {
		if(stdFrame == null) {
			stdFrame = new StudentManagement();
		}
		stdFrame.setVisible(true);
	}
	protected void actionPerformedBtnDepartment(ActionEvent e) {
		if(dptFrame == null) {
			dptFrame = new DepartmentManagement();
		}
		dptFrame.setVisible(true);
	}
}
