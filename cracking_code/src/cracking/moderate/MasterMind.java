package cracking.moderate;

public class MasterMind {

    Result checkGuess(String g, String sol) {
        Result res = new Result();
        int[] colorCount = new int[4];
        for (int i = 0; i < sol.length(); i++) {
            if (g.charAt(i) == sol.charAt(i)) {
                res.h++;
            } else {
                colorCount[getSymbolCode(sol.charAt(i))] += 1;
            }
        }

        for (int i = 0; i < g.length(); i++) {
            if (g.charAt(i) != sol.charAt(i) && colorCount[getSymbolCode(g.charAt(i))] > 0) {
                res.p++;
                colorCount[getSymbolCode(g.charAt(i))] -= 1;
            }
        }
        return res;
    }

    int getSymbolCode(char symbol) {
        switch (symbol) {
            case 'R':
                return 0;
            case 'G':
                return 1;
            case 'B':
                return 2;
            case 'Y':
                return 3;
            default:
                return -1;
        }
    }

    public static void main(String[] args) {
        MasterMind obj = new MasterMind();
        String sol = "RGBG";
        System.out.println(obj.checkGuess("RRGG", sol).toString());
        System.out.println(obj.checkGuess("RGGG", sol).toString());
        System.out.println(obj.checkGuess("YYYY", sol).toString());
        System.out.println(obj.checkGuess("GRGB", sol).toString());
    }
}

class Result {
    int h;
    int p;

    @Override
    public String toString() {
        return "hits = " + h + ", pseudo-hits = " + p;
    }
}
