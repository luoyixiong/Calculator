package com.example.luo.calculator.cal;

/**
 * Created by Luo on 2016/10/12.
 */
import java.util.Stack;

public class ProcessExpression {

    String expression;//输入的数学表达式
    Stack<Double> opnums;//操作数
    Stack<Character> opchas;//操作符
    String num;

    String opcharStr = "+-*/";

    int[][] standop = {
            {1, 1, -1, -1,},
            {-1, -1, -1, -1,},
            {1, 1, 1, 1,},
            {1, 1, -1, -1,},
    };
    public ProcessExpression(String expression) {
        this.expression = expression;
        opnums = new Stack<Double>();
        opchas = new Stack<Character>();
        num = "";
        divideToStack();
    }


    //将传入的数学表达式分解为数字和符号分别加入栈
    private void divideToStack() {
        for (int i = 0; i < expression.length(); i++) {
            switch (expression.charAt(i)) {
                case '+':
                    opchas.push('+');
                    break;
                case '-':
                    if (i == 0) {
                        num += "-";
                    } else {
                        if (expression.charAt(i - 1) == '*' || expression.charAt(i - 1) == '/') {
                            num += "-";
                        } else {
                            opchas.push('-');
                        }
                    }
                    break;
                case '*':
                    opchas.push('*');
                    break;
                case '/':
                    opchas.push('/');
                    break;
                case '.':
                    num += '.';
                case '=':
                    break;
                case '0':
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    num += expression.charAt(i);
                    if (isOpChar(i + 1)) {
                        opnums.push(Double.parseDouble(num));
                        num = "";
                    }
                    break;

            }
        }
    }


    //判断是否为数字字符
    private boolean isNumChar(int j) {
        if ('0' <= expression.charAt(j) && expression.charAt(j) <= '9') {
            return true;
        } else {
            return false;
        }

    }

    //判断是否为操作符
    private boolean isOpChar(int j) {
        if (expression.charAt(j) == '+' || expression.charAt(j) == '-' || expression.charAt(j) == '*'
                || expression.charAt(j) == '/' || expression.charAt(j) == '=') {
            return true;
        } else {
            return false;
        }
    }

    public double getResult() {
        char opcha,opcha2;
        double num3;
        opcha = this.opchas.pop();
        while (!this.opchas.isEmpty()){
            if (standop[opcharStr.indexOf(opcha)][opcharStr.indexOf(this.opchas.peek())] == -1) {//pop出的符号(c1)的优先级小于栈顶符号(c2)

                opcha2 = opcha;     //记录下pop出的元素(c1)
                opcha = this.opchas.pop();//将栈顶元素(c2)pop
                this.opchas.push(opcha2);//将c1重新入栈

                num3 = opnums.pop();
                popandpush(opcha);

                opnums.push(num3);
            } else {

                popandpush(opcha);
            }
            opcha = this.opchas.pop();
        }
        popandpush(opcha);

        return opnums.pop();
    }

    private void popandpush(char opcha) {
        double num2 = opnums.pop();
        double num = opnums.pop();
        opnums.push(cal(num, opcha, num2));

    }

    private double cal(double front, char op, double behind) {
        switch (op) {
            case '+':

                return front + behind;
            case '-':

                return front - behind;
            case '*':

                return front * behind;
            case '/':

                return front / behind;
            default:
                return 0;
        }
    }

}

