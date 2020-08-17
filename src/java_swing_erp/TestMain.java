package java_swing_erp;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import java_swing_erp.dto.Department;
import java_swing_erp.dto.Student;
import java_swing_erp.ui.component.DepartmentManagement;
import java_swing_erp.ui.component.StudentManagement;
import java_swing_erp.ui.component.content.DepartmentPanel;
import java_swing_erp.ui.component.content.StudentPanel;
import java_swing_erp.ui.component.table.DepartmentTable;
import java_swing_erp.ui.component.table.StudentTable;

public class TestMain {

	public static void main(String[] args) {
		//        test정규표현식적용();
		//        testDepartment();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentManagement frame = new StudentManagement();
					frame.setVisible(true);
					DepartmentManagement frame1 = new DepartmentManagement();
					frame1.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@SuppressWarnings("unused")
	private static void test정규표현식적용() {
		String no = "ab";
		boolean isValid = Pattern.matches("\\d{1,3}", no);
		System.out.println(isValid);

		String name = "이현석";
		boolean isValidHan = Pattern.matches("^[가-힣]+$", name);
		System.out.println(isValidHan);

		String tel = "053-1111-111a";
		boolean isValidTel = Pattern.matches("\\d{3}-\\d{3,4}-\\d{4}", tel);
		System.out.println(isValidTel);
	}

	public static void testDepartment() {
		//		test();        
		DepartmentManagement dframe = new DepartmentManagement();
		JFrame frame = new JFrame();//기본 레이아웃은 borderlayout(동서남북)
		frame.setSize(400, 400);//사이즈설정안하면메뉴창만나오는 ㅈ콩만하게 나옴.
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DepartmentPanel sp = new DepartmentPanel();
		frame.add(sp, BorderLayout.NORTH);//center에 들어감 아무것도 주어지지않아서..
		frame.setVisible(true);

		Department std = new Department(1, "경영학", "053-111-3333");
		sp.setItem(std);

		JButton btn = new JButton("확인");//borderlayout의 south 에 추가
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sp.getItem();
				System.out.println(sp.getItem());
			}
		});
		frame.add(btn, BorderLayout.EAST);

		ArrayList<Department> itemList = new ArrayList<Department>();
		itemList.add(new Department(1, "경영", "053-111-1111"));
		itemList.add(new Department(2, "무역", "053-111-1111"));

		DepartmentTable table = new DepartmentTable();
		table.setItems(itemList);

		/* 지금 콜럼이 안보이는데 콜럼이 보이려면 scrollPane안에다가 table을 넣어줘야한다.
				StudentTable table = new StudentTable();
				table.setItems(stdList);  stdList내용을 table에 넣어주는 함수를 만들어 보자.*/

		JScrollPane jp = new JScrollPane();
		jp.setViewportView(table);

		frame.add(jp, BorderLayout.CENTER);

		Department tstd = new Department(3, "컴퓨터관광", "053-3333-3333");
		itemList.add(tstd);

		table.addRow(tstd);

		//        table.removeRow(1);//인덱스 1 데이터 삭제
		//        itemList.remove(1);//arraylist와 배열의 내용을 동일하게 해주어야하니깐 

		tstd.setName("컴퓨터과학");
		tstd.setTel("053-4444-4444");

		int searchIdx = itemList.indexOf(tstd);

		table.updateRow(searchIdx, tstd);

		frame.setVisible(true);
	}

	public static void testStudent() {
		JFrame frame = new JFrame();//기본 레이아웃은 borderlayout(동서남북)
		frame.setSize(400, 600);
		StudentPanel sp = new StudentPanel();
		frame.add(sp, BorderLayout.NORTH);//center에 들어감

		Student std = new Student(1, "김대훈", 90, 70, 60);
		sp.setItem(std);

		JButton btn = new JButton("확인");//borderlayout의 south 에 추가
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(sp.getItem());
			}
		});
		frame.add(btn, BorderLayout.EAST);

		ArrayList<Student> stdList = new ArrayList<Student>();
		stdList.add(new Student(1, "김대훈", 90, 80, 71));
		stdList.add(new Student(2, "이현석", 91, 81, 100));
		stdList.add(new Student(3, "윤원주", 92, 82, 90));
		stdList.add(new Student(4, "배성덕", 93, 83, 60));

		StudentTable table = new StudentTable();
		table.setItems(stdList);

		JScrollPane jp = new JScrollPane();
		jp.setViewportView(table);

		frame.add(jp, BorderLayout.CENTER);

		Student tstd = new Student(5, "이지수", 80, 70, 60);
		stdList.add(tstd);

		table.addRow(tstd);

		table.removeRow(1);
		stdList.remove(1);

		tstd.setName("백령");
		tstd.setKor(100);
		tstd.setEng(0);
		tstd.setMath(100);

		int searchIdx = stdList.indexOf(tstd);

		table.updateRow(searchIdx, tstd);

		frame.setVisible(true);
	}

}
