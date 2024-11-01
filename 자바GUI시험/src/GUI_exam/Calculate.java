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
 * @author 김기훈
 * @version 0.0.7
 * @see ChatGPT를 이용해 중복 계산을 할 수 있도록 도움을 받음
 * @see chrome 검색창에 '계산기'를 검색했을때 가장 상단에 노출되는 계산기 디자인을 모방함
 */
public class Calculate extends JFrame {
	JPanel p1;  // 상단 텍스트 필드용 패널
	JPanel p2;  // 하단 버튼용 패널
	String[] btnText1 = { "AE", "", "%", "CE", "7", "8", "9", "/", "4", "5", "6", "*", "1", "2", "3", "-", "0", ".", "=", "+" };
	JButton[] buttons;
	JTextField t1, t2;
	double result = 0;
	String operator = "";
	/**
	 * @param isNewOperation 연산결과 중복되기 않게 방지, 새로운 연산이 시작되는 것을 알림
	 */
	boolean isNewOperation = true;
	/**
	 * 기본 생성자: 계산기의 초기 설정과 GUI 요소 생성.
	 */
	Calculate() {
		this.setTitle("계산기");
		this.setSize(400, 300);
		this.setLayout(new BorderLayout(2, 2));

		// 텍스트 필드를 담는 패널 초기화
		p1 = new JPanel(new BorderLayout());
		getContentPane().add(p1, BorderLayout.NORTH);

		// 상단 텍스트 필드 생성 및 설정
		t2 = createTextField(20, Color.DARK_GRAY);
		p1.add(t2, BorderLayout.NORTH);

		// 메인 텍스트 필드 생성 및 설정
		t1 = createTextField(50, Color.DARK_GRAY);
		p1.add(t1, BorderLayout.CENTER);

		// 버튼 패널 초기화 및 설정
		p2 = new JPanel(new GridLayout(5, 4, 2, 2));
		getContentPane().add(p2, BorderLayout.CENTER);
		
		// 없는 색을 만들기 위한 객체 생성
		Color Light_Blue = new Color(30, 144, 255);
		buttons = new JButton[20];
		/**
		 * 버튼 배열 생성 및 이벤트 리스너 설정
		 * 반복문을 이용해 버튼 생성
		 */
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = createButton(btnText1[i], i, Light_Blue);
			p2.add(buttons[i]);
		}

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		/**
		 * @param setResizable 사용자가 마음대로 창크기 조절하는것을 막음
		 */
		this.setResizable(false);
	}

	/**
	 * 주어진 설정으로 JTextField를 생성.
	 * 배경색, 폰트, 폰트색, 텍스트필드 위치 설정, 사용자 입력 제한이 설정 되어있음
	 * @return 설정된 JTextField 객체
	 */
	private JTextField createTextField(int fontSize, Color backgroundColor) {
		JTextField textField = new JTextField();
		textField.setFont(new Font("Arial", Font.BOLD, fontSize));
		textField.setBackground(backgroundColor);
		textField.setForeground(Color.WHITE);
		textField.setHorizontalAlignment(JTextField.RIGHT);
		textField.setEnabled(false);
		return textField;
	}

	/**
	 * 주어진 텍스트와 색상으로 JButton 생성 및 설정.
	 * 버튼 폰트, 폰트색, 버튼 생성, 각 특수한 위치의 버튼 색 지정, 지정위치 버튼의 색 그리고 폰트와 배경색 지정
	 * @param Light_Blue 특수 색상 (Light Blue)
	 * @return 설정된 JButton 객체
	 */
	private JButton createButton(String text, int index, Color Light_Blue) {
		JButton button = new JButton(text);
		button.setFont(new Font("Arial", Font.BOLD, 15));
		button.setForeground(Color.WHITE);

		if (index < 4 || index == 7 || index == 11 || index == 15 || index == 19) {
			button.setBackground(Color.GRAY);
		} else if (index == 18) {
			button.setBackground(Light_Blue);
			button.setForeground(Color.BLACK);
		} else {
			button.setBackground(Color.DARK_GRAY);
		}

		button.addActionListener(e -> onButtonClick(text));
		return button;
	}

	/**
	 * 버튼 클릭 시 호출되는 메서드로, 입력 값에 따라 연산 수행.
	 * @param command 클릭된 버튼의 텍스트 (연산자 또는 숫자)
	 */
	private void onButtonClick(String command) {
		String currentText = t1.getText();

		if (Character.isDigit(command.charAt(0)) || command.equals(".")) {
			if (isNewOperation) {
				t1.setText("");
				isNewOperation = false;
			}
			t1.setText(t1.getText() + command);
			t2.setText(t2.getText() + command);
		} 
		/**
		 * @param if (!currentText.isEmpty()) {}는 currentText가 비어있지 않다면 안에 내용 실행
		 */
		else if (command.equals("CE")) {
			if (!currentText.isEmpty()) {
				t1.setText(currentText.substring(0, currentText.length() - 1));
				t2.setText(t2.getText().substring(0, t2.getText().length() - 1));
			}
		} else if (command.equals("AE")) {
			clearAll();
		} else if ("+-*/%".contains(command)) {
			if (!operator.isEmpty()) {
				performCalculation();
			} else {
				result = Double.parseDouble(currentText);
			}
			operator = command;
			t2.setText(result + " " + operator);
			isNewOperation = true;
		} else if (command.equals("=")) {
			performCalculation();
			operator = "";
			isNewOperation = true;
		}
	}

	/**
	 * 계산기의 모든 텍스트 필드와 연산자 제거.
	 */
	private void clearAll() {
		t1.setText("");
		t2.setText("");
		result = 0;
		operator = "";
		isNewOperation = true;
	}

	/**
	 * 설정된 연산자에 따라 연산을 수행하고 결과를 표시.
	 */
	private void performCalculation() {
		double operand = t1.getText().isEmpty() ? 0 : Double.parseDouble(t1.getText());
		switch (operator) {
		case "+" -> result += operand;
		case "-" -> result -= operand;
		case "*" -> result *= operand;
		case "/" -> result = (operand == 0) ? Double.NaN : result / operand;
		case "%" -> result = result * operand / 100;
		}
		t1.setText(String.valueOf(result));
		t2.setText(String.valueOf(result));
		isNewOperation = true;
	}

	/**
	 * 메인 메서드. 계산기를 실행.
	 */
	public static void main(String[] args) {
		new Calculate();
	}
}
