package GUI_exam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 계산기 만들기
 * @author 2021011930 김기훈
 * @version 0.0.2
 */
public class Calculate extends JFrame {
	JPanel p1; // 상단 패널
	JPanel p2; // 하단 패널
	String[] btnText1 = { "(", ")", "%", "CE", "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ",", "=", "+"};
	/**
	 * @param String[] btnText1
	 * <br>
	 * 버튼 안에 들어갈 문자를 String 배열을 이용해 만들었음
	 */
	JButton[] b1 = new JButton[20];
	/**
	 * @param JButton[] b1
	 * <br>
	 * 버튼 리스트를 생성해서 총 20개의 칸을 생성해준다.
	 */
	JTextField t1; // 상단에 들어갈 텍스트 칸 생성

	Calculate() {
		this.setTitle("계산기");
		this.setSize(260, 300);
		this.setLayout(new BorderLayout());
		p1 = new JPanel();
		p1.setLayout(new BorderLayout(5, 5));
		p2 = new JPanel();
		p2.setLayout(new GridLayout(5, 3, 2, 2));
		/**
		 * GridLayout안에 가로 3줄 새로 5줄 칸 형성, 칸 사이 간격 2, 2
		 */
		t1 = new JTextField();
		t1.setEnabled(false);
		this.add(p1, "North"); // 페널 북쪽에 위치
		this.add(p2, "Center"); // 페널 중간에 위치
		p1.add(t1, "West"); // p1안에서 서쪽에 위치
		int a = 0; // for 문에서 버튼에 텍스트 값을 차례대로 넣기 위해 만든 변수
		Color Light_Blue = new Color(30,144,255); // setBackground에 없는 색을 넣기 위함
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				b1[j] = new JButton(btnText1[a]);
				a += 1;
				b1[j].setBackground(Color.DARK_GRAY);
				b1[j].setForeground(Color.white);
				if (a < 5 || a == 8 || a == 12 || a == 16 || a == 20) {
					b1[j].setBackground(Color.GRAY);
				}else if (a == 19) {
					b1[j].setBackground(Light_Blue);
					b1[j].setForeground(Color.BLACK);
				}
				/**
				 * if 이용해 디자인 수정. <br>
				 * 숫자 칸만 진한 회색, 다른 곳은 밝은 회색, = 값은 파랑으로 수정
				 */
				p2.add(b1[j]);
			}
		}
		/**
		 * 2중 for 문 사용 <br>
		 * 버튼생성은 왼쪽에서 오른쪽, 위에서 아래로 생성 <br>
		 * 버튼의 색상은 회색, 텍스트 색상은 흰색 <br>
		 * @see 버튼 중복 생성 블로그 참조 <br>
		 * @link https://intunknown.tistory.com/entry/%EC%9E%90%EB%B0%94-GUI-%EB%B2%84%ED%8A%BC-%EB%B0%B0%EC%97%B4%EC%BD%94%EB%93%9C-%EC%A4%91%EB%B3%B5
		 */
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		Calculate f = new Calculate();
	}

}
