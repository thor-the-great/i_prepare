package cracking.moderate;

public class Calculator {

    float compute(String expr) {
        char op = '+';
        float result = 0;
        StringBuilder sb = new StringBuilder();
        for (int i =0; i < expr.length(); i++) {
            char ch = expr.charAt(i);
            if (ch != '+' && ch != '-') {
                sb.append(ch);
                if (i == expr.length() - 1) result = dpPlusMinus(op, result, sb.toString());
            } else {
                result = dpPlusMinus(op, result, sb.toString());
                sb.setLength(0);
                op = ch;
            }
        }
        return result;
    }

    private float dpPlusMinus(char op, float result, String group) {
        float groupNum = calcGroup(group);
        if (op == '+') result += groupNum;
        else result -= groupNum;
        return result;
    }

    float calcGroup(String group) {
        float res = 1;
        String s = "";
        char op = '*';
        for (int i = 0; i < group.length(); i++) {
            if (group.charAt(i) != '*' && group.charAt(i) != '/') {
                s = s + group.charAt(i);
            } else {
                float num = Float.parseFloat(s);
                s = "";
                if (op == '*') res = res * num;
                else res = res / num;
                op = group.charAt(i);
            }
        }
	    if (!s.isEmpty()) {
            float num = Float.parseFloat(s);
            if (op == '*') res = res * num;
            else res = res / num;
        }
	    return res;
    }

    public static void main (String[] args) {
        Calculator obj = new Calculator();
        String exp = "2*3+5/6*3+15";
        System.out.println(obj.compute(exp));
        exp = "2*3+5/6*3+15*2-4*6/12";
        System.out.println(obj.compute(exp));
        exp = "15-3";
        System.out.println(obj.compute(exp));
    }
}
