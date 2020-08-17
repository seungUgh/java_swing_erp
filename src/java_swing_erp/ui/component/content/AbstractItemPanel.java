package java_swing_erp.ui.component.content;

import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public abstract class AbstractItemPanel<T> extends JPanel {
	//패널에있는 component는 할때마다 다르기때문에 지운다
	public abstract void setItem(T item);

	public abstract T getItem();

	abstract boolean isValidTf();

	boolean isTfEmpty() {
		//모든 component중 텍스트가 공백이면 체크! ->레이블5개,텍스트5개의 총 10개의 컴퍼넌트있다. 
		//이거를 getComponets()배열로 가져옴
		for(Component c : getComponents()) {
			//System0.out.println(c);
			if(c instanceof JTextField) {
				//label빼고 textfield만 검색
				if (((JTextField) c).getText().isEmpty()) {
					return true;
				}
			}//한번도 안먹엇다는이야기는 다 안비었다는 이야기 
		}
		return false;
	}
	public void setTfEditable(boolean isEditable) {
		for(Component c : getComponents()) {
			if(c instanceof JTextField) {
				((JTextField) c).setEditable(isEditable);
			}// c의 textfield만 찾아서 비활성화 수정 불가능하게 한다. 
		}
	}
	public void clearTf() {
		for(Component c : getComponents()) {
			if(c instanceof JTextField) {
				 ((JTextField) c).setText("");
			}
		}
	}
	
	public abstract void setEditableNoTf(boolean isEditable);

}	


