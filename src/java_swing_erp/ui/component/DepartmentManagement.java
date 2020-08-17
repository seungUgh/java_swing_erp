package java_swing_erp.ui.component;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;

import java_swing_erp.dto.Department;
import java_swing_erp.dto.Student;
import java_swing_erp.ui.component.content.DepartmentPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java_swing_erp.ui.component.table.DepartmentTable;
import javax.swing.JScrollPane;

public class DepartmentManagement extends JFrame implements ActionListener {

	private JPanel contentPane;
	private DepartmentPanel pDepartment;
	private JPanel pBtns;
	private JPanel pList;
	private JButton btnAdd;
	private JButton btnCancel;
	private JScrollPane scrollPane;
	private DepartmentTable table;
	private ArrayList<Department> dptList = new ArrayList<Department>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DepartmentManagement frame = new DepartmentManagement();
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
	public DepartmentManagement() {
		initComponents();
		
		Department dpt = new Department(1, "국어국문학과", "010-2792-0111");
		pDepartment.setItem(dpt);
		
		dptList = new ArrayList<Department>();
		dptList.add(new Department(1, "영여영문학과", "010-111-1111"));
		dptList.add(new Department(2, "독어독문학과", "010-111-1112"));
		dptList.add(new Department(3, "중어중문학과", "010-111-1113"));
		dptList.add(new Department(4, "경제학과", "010-111-1114"));
		
		table.setItems(dptList);
	}
	private void initComponents() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(550, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
		
		pDepartment = new DepartmentPanel();
		contentPane.add(pDepartment);
		
		pBtns = new JPanel();
		contentPane.add(pBtns);
		
		btnAdd = new JButton("추가");
		btnAdd.addActionListener(this);
		pBtns.add(btnAdd);
		
		btnCancel = new JButton("취소");
		btnCancel.addActionListener(this);
		pBtns.add(btnCancel);
		
		pList = new JPanel();
		contentPane.add(pList);
		pList.setLayout(new BorderLayout(0, 0));
		
		scrollPane = new JScrollPane();
		pList.add(scrollPane, BorderLayout.CENTER);
		
		table = new DepartmentTable();
		scrollPane.setViewportView(table);
		
		CustomPopupMenu popMenu = new CustomPopupMenu(this);
		table.setComponentPopupMenu(popMenu);
		scrollPane.setViewportView(table);
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println(e);
		
		if(e.getSource() instanceof JButton) {
		if (e.getSource() == btnCancel) {
			actionPerformedBtnCancel(e);
		}
		if (e.getSource() == btnAdd) {
			if(e.getActionCommand().equals("추가")) {
				actionPerformedBtnAdd(e);
			}else { 
				actionPerformedBtnUpdate();
		}
	}
}
		if(e.getSource() instanceof JMenuItem) {
			 if (e.getActionCommand().equals("수정")) {
	                actionPerformedMenuUpdate();
	            }
	            if (e.getActionCommand().equals("삭제")) {
	                actionPerformedMenuDelete();
	            }
	            if (e.getActionCommand().equals("세부 정보")) {
	                actionPerformedMenuDetail();
	            }
	        }
		}
	private void actionPerformedMenuDelete() {
//      System.out.println("삭제~~~");  
      int selIdx = table.getSelectedRow();
    //StudentTable에서 removeRow()확인하면 int
      if (selIdx == -1) {
          JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요.");
          return;
      }
    //인덱스의 위치를 알아야한다. -> arraylist삭제
      Department deletedDpt = dptList.remove(selIdx);
      table.removeRow(selIdx);
      JOptionPane.showMessageDialog(null, String.format("%s(%d) 삭제 완료!!", deletedDpt.getName(), deletedDpt.getNo()));
	}

	private void actionPerformedMenuDetail() {
		  System.out.println("세부 정보~~~");    
	        int selIdx = table.getSelectedRow();
	        if (selIdx == -1) {
	            JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요.");
	            return;
	        }
	        Department detailDpt = dptList.get(selIdx);
	        System.out.println(detailDpt);
	        DepartmentDetailDlg dlg = new DepartmentDetailDlg();
	        dlg.setStudent(detailDpt);
	        dlg.setTfNotEditable();
	        dlg.setVisible(true);
	}

	private void actionPerformedMenuUpdate() {
		System.out.println("수정~~~");
		int selIdx = table.getSelectedRow();
		if (selIdx == -1) {
            JOptionPane.showMessageDialog(null, "해당 항목을 선택하세요.");
            return;
        }
		Department selDpt = dptList.get(selIdx);
		 pDepartment.setItem(selDpt);
	        //1. 버튼의 텍스를 수정으로 변경
	        //2. pStudent 학번은 변경 불가능하게..
	        btnAdd.setText("수정");
	        pDepartment.setEditableNoTf(false);
	}

	private void actionPerformedBtnUpdate() {
		Department updatedDpt = pDepartment.getItem();
		int idx = dptList.indexOf(updatedDpt);
		dptList.set(idx, updatedDpt);
		table.updateRow(idx, updatedDpt);
		pDepartment.clearTf();
		pDepartment.setEditableNoTf(true);
		btnAdd.setText("추가");
		JOptionPane.showMessageDialog(null, String.format(""));
		
	}

	protected void actionPerformedBtnAdd(ActionEvent e) {
		Department newDpt = pDepartment.getItem();
		System.out.println(newDpt);
		table.addRow(newDpt);
		pDepartment.clearTf();
		JOptionPane.showMessageDialog(null, String.format("%s(%d) 수정 완료!!", newDpt.getName(), newDpt.getNo()));
		dptList.add(newDpt);
	}
	
	protected void actionPerformedBtnCancel(ActionEvent e) {
		pDepartment.clearTf();
		if(btnAdd.getText().equals("수정")) {
			btnAdd.setText("추가"	);
			pDepartment.setEditableNoTf(true);
			table.clearSelection();
		}
	}
	
	private class CustomPopupMenu extends JPopupMenu {
		public CustomPopupMenu(ActionListener listner) {
			JMenuItem updateMenu = new JMenuItem("수정");
			updateMenu.addActionListener(listner);
			add(updateMenu);
			JMenuItem deleteMenu = new JMenuItem("삭제");
			deleteMenu.addActionListener(listner);
			add(deleteMenu);
			JMenuItem detailMenu = new JMenuItem("세부 정보");
			detailMenu.addActionListener(listner);
			add(detailMenu);
		}
	}
	
}
