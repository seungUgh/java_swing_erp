package java_swing_erp.ui.component.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

import java_swing_erp.dto.Student;

@SuppressWarnings("serial")
public class StudentTable extends AbstractItemTable<Student> {
    
    @Override
    Object[] getColName() {
        return new String[] {"번호", "성명", "국어", "영어", "수학", "총점", "평균"};
    }

    @Override
    Object[] toArray(Student std) {
        return new Object[] {
                std.getNo(), 
                std.getName(), 
                std.getKor(),
                std.getEng(),
                std.getMath(),
                std.getTotal(),
                std.getAverage()};
    }
    
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component c = super.prepareRenderer(renderer, row, column);
        double value = (double) getValueAt(row, 6);//평균행을 가져옴....
        if (value >= 90) {
            c.setBackground(new Color(255, 0, 0, 127));
        }else {
            c.setBackground(Color.white);
        }
        super.prepareRenderer(renderer, row, column);//부모에있는메서드를 호출해줘야된다.이것을 안하면...선택할때 배경처리가 안된다.
        return c;
    }

    @Override
    void setWidthAndAlign() {
      //column width
        tableSetWidth(150, 200, 100, 100, 100, 100, 100);
        //column alignment
        tableCellAign(SwingConstants.CENTER, 0, 1);
        tableCellAign(SwingConstants.RIGHT, 2, 3, 4, 5, 6);        
    }

}




