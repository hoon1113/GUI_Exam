package GUI_exam;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 계산기 만들기
 * @author 2021011930 김기훈
 * @version 0.0.5
 */
public class Calculate extends JFrame {
	JPanel p1; // 상단 텍스트필드를 넣는 패널
	JPanel p2; // 하단 버튼넣는 패널
	String[] btnText1 = { "AE", "", "%", "CE", "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+"};
	/**
	 * @param String[] btnText1
	 * <br>
	 * 버튼 안에 들어갈 문자를 String 배열을 이용해 만들었음
	 */
	JButton[] b1;
	JTextField t1; // 상단에 들어갈 텍스트 칸 생성
	JTextField t2; // 뒤늦게 추가된 식을 보여주는 텍스트필드
	double result = 0;// 계산 결과
	String operator; // 연산자

	Calculate() {
		this.setTitle("계산기");
		this.setSize(400, 300);
		this.setLayout(new BorderLayout(2,2));
		
		p1 = new JPanel();
		getContentPane().add(p1, BorderLayout.NORTH);
		p1.setLayout(new BorderLayout());
		
		t2 = new JTextField();
		p1.add(t2, BorderLayout.NORTH);
		t2.setBackground(Color.DARK_GRAY);
		t2.setFont(new Font("Arial", Font.BOLD, 20));
		t2.setHorizontalAlignment(JTextField.RIGHT); // 오른쪽 정렬
		t2.setForeground(Color.white); 
		t2.setEnabled(false); // 사용장 작성 금지
		
		t1 = new JTextField();
		t1.setSize(260, 100);
		p1.add(t1, BorderLayout.CENTER);
		
		t1.setBackground(Color.DARK_GRAY); // 글자 색이 안바껴서 배경색을 바꿈
		t1.setFont(new Font("Arial", Font.BOLD, 50)); // 글자 크기 키우기
		t1.setHorizontalAlignment(JTextField.RIGHT); // 오른쪽 정렬
		t1.setEnabled(false); // 사용자 작성 금지
		
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
			b1[i].setFont(new Font("Arial", Font.BOLD, 15)); // 버튼 글자 크기 키우기
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
					t2.setText(t2.getText() + cammand);
				} // 0~9 숫자 클릭시 숫자 텍스트에 입력
				if(cammand.equals(".")) {
					t1.setText(t1.getText() + ".");
					t2.setText(t2.getText() + ".");
				} // 점 클릭시 텍스트에 점 입력
				if(cammand.equals("CE")) {
					t1.setText(t1.getText().substring(0, t1.getText().length() -1));
					t2.setText(t2.getText().substring(0, t2.getText().length() -1));
					// 텍스트의 길이를 뒤에서부터 1개씩 줄이는 원리
				}
				if(cammand.equals("AE")) {
					t1.setText("");
					t2.setText("");
					result = 0;
					// 텍스트 값 모두 없애기
				}
				if (cammand.equals("/") || cammand.equals("*") || cammand.equals("-") || cammand.equals("+") || cammand.equals("%")) {
					operator = cammand;
					switch(operator) {
					case "+": // + 눌렀을때 result 안에 이전에 누른 값을 저장 하라 / 텍스트에 공백 출력
						result = Double.parseDouble(t1.getText()); // double 로 t1에 들어온 값 저장
						t1.setText("");
						t2.setText(result + "+"); // t2칸에는 숫자 없어지지 않고 뒤에 연산자도 적어줌
						break;
					case "-":
						result = Double.parseDouble(t1.getText());
						t1.setText("");
						t2.setText(result + "-");
						break;
					case "/":
						result = Double.parseDouble(t1.getText());
						t1.setText("");
						t2.setText(result + "/");
						break;
					case "*":
						result = Double.parseDouble(t1.getText());
						t1.setText("");
						t2.setText(result + "*");
						break;
					case "%":
						result = Double.parseDouble(t1.getText());
						t1.setText("");
						t2.setText(result + "%");
						break;
					}
				}
				if (cammand.equals("=")) {
					switch (operator) {
					case "+": // 덧셈 / 이전에 저장한 result 값에 다음 입력값을 더해라
						result = result + Double.parseDouble(t1.getText());
						t1.setText(String.valueOf(result)); // String 값으로 변환
						break;
					case "-": // 뺄셈
						result = result - Double.parseDouble(t1.getText());
						t1.setText(String.valueOf(result));
						break;
					case "*": // 곱셈
						result = result * Double.parseDouble(t1.getText());
						t1.setText(String.valueOf(result));
						break;
					case "/": // 나눗셈
						result = result / Double.parseDouble(t1.getText());
						t1.setText(String.valueOf(result));
						break;
					case "%": // 퍼센트 구하는 공식
						result = result / Double.parseDouble(t1.getText()) * 100;
						t1.setText(String.valueOf(result) + "%");
						break;
					}
				}
			});
			p2.add(b1[i]);
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 창 종료되면 프로그램 종료
		this.setVisible(true); // 화면 보여주기 
		this.setResizable(false); // 창크기 조절 X
	}

	public static void main(String[] args) {
		Calculate f = new Calculate();
	}

}