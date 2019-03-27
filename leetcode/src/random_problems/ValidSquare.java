package random_problems;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class ValidSquare {

    Map<Integer, LinkedList<Integer>> xMap;

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        xMap = new HashMap();

        if (!addCoordinate(xMap, p1) ||
                !addCoordinate(xMap, p2) ||
                !addCoordinate(xMap, p3) ||
                !addCoordinate(xMap, p4) )
            return false;

        LinkedList<Integer> y = null;
        for (int x : xMap.keySet()) {
            if (y == null)
                y = xMap.get(x);
            else {
                LinkedList<Integer> y2 = xMap.get(x);
                if (y.getFirst() == y2.getFirst() && y.getLast() == y2.getLast())
                    return true;
                else
                    return false;
            }
        }

        return false;
    }

    boolean addCoordinate(Map<Integer, LinkedList<Integer>> xMap, int[] p) {
        if (!xMap.containsKey(p[0])) {
            if (xMap.size() == 2)
                return false;
            xMap.put(p[0], new LinkedList());
            xMap.get(p[0]).add(p[1]);
        } else {
            LinkedList<Integer> yCoord = xMap.get(p[0]);
            if (yCoord.size() == 2)
                return false;

            int y = yCoord.get(0);
            if (p[1] == y)
                return false;

            if (p[1] < y)
                yCoord.addFirst(p[1]);
            else
                yCoord.addLast(p[1]);
        }

        return true;
    }

    public static void main(String[] args) {
        ValidSquare obj = new ValidSquare();
        System.out.println(obj.validSquare(
                new int[] {1,0},
                new int[] {-1,0},
                new int[] {0,1},
                new int[] {0,-1}
        ));
    }
}
