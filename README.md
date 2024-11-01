# GUI_Exam 계산기 만들기<br>
#### 구글 검색창에 '계산기'라고 검색하면 바로 상단에 뜨는 계산기를 참고하여 만들었음<br>
틀만 구성한 version 0.0.1 등록 (2024/10/20) - 만들기 시작한 날짜

## 기능 설명<br>
**기본 연산자** : 덧셈, 뺄셈, 곱셈, 나눗셈, 퍼센트 기능을 사용할수있음<br>
**삭제 기능** : 'CE' 기능 과 'AE' 기능이 있는데 'CE'는 마지막 입력값을 삭제하고, 'AE'는 모든 값을 지우고 리셋한다.<br>
**중복 계산** : 앞에 숫자를 넣고 두번째 사칙연산을 넣으면 '='을 입력한 것과 같이 첫번째 연산을 처리한뒤 두번째 연산자가 붙는다.<br>
```
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
```
위 메서드를 보면 알 수 있듯이 사칙연산을 입력받으면 이전 결과값에 operand 즉 다음 입력값을 연산자 모양에 맞게 연산하게 된다.<br>
나누기 같은 경우 0 값이 들어오면 잘못된 계산이라고 알려주기 위해 NaN값이 출력된다.<br>
## 디자인 <br>

![image](https://github.com/user-attachments/assets/2c67e313-7d03-4b1e-9b8c-59a9b1c266c4)
![image](https://github.com/user-attachments/assets/b5182886-5d0c-49a3-af2a-0da4b29f7adf)
