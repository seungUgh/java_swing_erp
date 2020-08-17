package java_swing_erp.ui.component;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java_swing_erp.dto.Department;
import java_swing_erp.ui.component.content.DepartmentPanel;

public class DepartmentDetailDlg extends JDialog implements ActionListener {

	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private DepartmentPanel pDepartment;

	public DepartmentDetailDlg() {
		initComponents();
	}

	public void setStudent(Department department) {
		pDepartment.setItem(department);
        
	}

	public void setTfNotEditable() {
		pDepartment.setTfEditable(false);
	}

	private void initComponents() {
		setTitle("학생 세부 정보");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new TitledBorder(null, "\uD559\uC0DD \uC815\uBCF4", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			pDepartment = new DepartmentPanel();
			contentPanel.add(pDepartment, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("확인");
				okButton.addActionListener(this);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == okButton) {
			actionPerformedOkButton(e);
		}
	}

	protected void actionPerformedOkButton(ActionEvent e) {
		dispose();
	}
}