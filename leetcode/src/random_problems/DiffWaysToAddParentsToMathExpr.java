package random_problems;

import linked_list.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string of numbers and operators, return all possible results from computing all the different possible ways
 * to group numbers and operators. The valid operators are +, - and *.
 *
 * Example 1:
 *
 * Input: "2-1-1"
 * Output: [0, 2]
 * Explanation:
 * ((2-1)-1) = 0
 * (2-(1-1)) = 2
 * Example 2:
 *
 * Input: "2*3-4*5"
 * Output: [-34, -14, -10, -10, 10]
 * Explanation:
 * (2*(3-(4*5))) = -34
 * ((2*3)-(4*5)) = -14
 * ((2*(3-4))*5) = -10
 * (2*((3-4)*5)) = -10
 * (((2*3)-4)*5) = 10
 *
 */
public class DiffWaysToAddParentsToMathExpr {

    Map<String, List> mem = new HashMap();

    public static void main(String[] args) {
        DiffWaysToAddParentsToMathExpr obj = new DiffWaysToAddParentsToMathExpr();
        List<Integer> res;
        res = obj.diffWaysToCompute("2-3*4+7");
        res.forEach(n-> System.out.print(n + " "));
        System.out.println("");
        res = obj.diffWaysToComputeDP("2-3*4+7");
        res.forEach(n-> System.out.print(n + " "));
        System.out.println("");
    }

    public List<Integer> diffWaysToComputeDP(String input) {
        List<Integer> nums = new ArrayList<>();
        List<Character> opers = new ArrayList<>();
        int num = 0;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if ('9'>= ch && ch >= '0') {
                num *= 10;
                num += (ch - '0');
            } else {
                nums.add(num);
                num = 0;
                opers.add(ch);
            }
        }
        nums.add(num);
        List<Integer>[][] dp = new ArrayList[nums.size()][nums.size()];

        for (int i = 0; i < nums.size(); i++) {
            dp[i][i] = new ArrayList();
            dp[i][i].add(nums.get(i));
        }

        for (int d = 1; d <= nums.size() - 1; d++) {
            for (int i = 0; i+d < nums.size(); i++) {
                dp[i][i+d] = new ArrayList();
                for (int j = i; j < i+d; j++) {
                    for (int left:  dp[i][j]) {
                        for (int right : dp[j+1][i+d]) {
                            dp[i][i+d].add(doOper(left, right, opers.get(j)));
                        }
                    }
                }
            }
        }
        return dp[0][nums.size() - 1];
    }

    int doOper(int x, int y, char oper) {
        if (oper == '+') return x + y;
        else if (oper == '-') return x - y;
        else return x * y;
    }

    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res= new ArrayList();
        if (input == null || input.isEmpty())
            return res;
        if (mem.containsKey(input)) {
            return mem.get(input);
        }
        boolean isCalcStep = false;
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '*' || ch == '+' || ch == '-') {
                isCalcStep = true;
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for (int lNum : left) {
                    for (int rNum : right) {
                        switch (ch) {
                            case '*' :
                                res.add(lNum * rNum);
                                break;
                            case '+' :
                                res.add(lNum + rNum);
                                break;
                            default:
                                res.add(lNum - rNum);
                                break;
                        }
                    }
                }
            }
        }
        mem.put(input, res);
        if (!isCalcStep)
            res.add(Integer.parseInt(input));
        return res;
    }
}
