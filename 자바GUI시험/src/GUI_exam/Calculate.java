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
 * @version 0.0.3
 */
public class Calculate extends JFrame {
	JPanel p2;
	String[] btnText1 = { "(", ")", "%", "CE", "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};
	/**
	 * @param String[] btnText1
	 * <br>
	 * 버튼 안에 들어갈 문자를 String 배열을 이용해 만들었음
	 */
	JButton[] b1;
	JTextField t1; // 상단에 들어갈 텍스트 칸 생성
	double result = 0; // 계산 결과
	String operator; // 연산자

	Calculate() {
		this.setTitle("계산기");
		this.setSize(400, 300);
		this.setLayout(new BorderLayout(2,2));
		t1 = new JTextField();
		t1.setSize(260, 100);
		t1.setForeground(Color.black); // 왜 안먹히는가?
		getContentPane().add(t1, BorderLayout.NORTH);
		t1.setEnabled(false);
		
		p2 = new JPanel();
		getContentPane().add(p2, BorderLayout.CENTER);
		p2.setLayout(new GridLayout(5, 3, 2, 2));
		/**
		 * GridLayout안에 가로 3줄 새로 5줄 칸 형성, 칸 사이 간격 2, 2
		 */		
		Color Light_Blue = new Color(30,144,255); // setBackground에 없는 색을 넣기 위함
		b1  = new JButton[20]; // 버튼 만들기 시작
		for (int i = 0; i < b1.length; i++) {
			// 버튼의 길이 만큼 반복해서 버튼 생성, 칸은 GridLayout이용해서 만들었음
			b1[i] = new JButton("" + btnText1[i]);
			b1[i].setBackground(Color.DARK_GRAY);
			b1[i].setForeground(Color.white);
			if (i < 4 || i == 7 || i == 11 || i == 15 || i == 19) {
				b1[i].setBackground(Color.GRAY);
			}else if(i == 18) {
				b1[i].setBackground(Light_Blue);
				b1[i].setForeground(Color.black);
			}
			/**
			 *  if 문 이용해서 디자인함
			 */
			b1[i].addActionListener(e -> {
				// 이벤트 넣기 시작
				String cammand = e.getActionCommand();
				if(cammand.equals("0") || cammand.equals("1") || cammand.equals("2") || cammand.equals("3") || cammand.equals("4") || cammand.equals("5") || cammand.equals("6") || cammand.equals("7") || cammand.equals("8") || cammand.equals("9")) {
					t1.setText(t1.getText() + cammand);
				} // 0~9 숫자 클릭시 숫자 텍스트에 입력
				if(cammand.equals(".")) {
					t1.setText(t1.getText() + ".");
				} // 점 클릭시 텍스트에 점 입력
				if(cammand.equals("CE")) {
					t1.setText(t1.getText().substring(0, t1.getText().length() -1));
					// 텍스트의 길이를 뒤에서부터 1개씩 줄이는 원리
				}
			});
			p2.add(b1[i]);
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setResizable(false);
	}

	public static void main(String[] args) {
		Calculate f = new Calculate();
	}

}