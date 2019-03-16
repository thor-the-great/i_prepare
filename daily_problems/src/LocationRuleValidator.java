import java.util.*;

public class LocationRuleValidator {

    static Map<Character, Integer> charToDirMap = new HashMap<>();
    static {
        charToDirMap.put('N', 0);
        charToDirMap.put('E', 1);
        charToDirMap.put('S', 2);
        charToDirMap.put('W', 3);
    }

    public boolean validate(String[] rules) {
        //map will be our graph and will hold all NaryTreeNode (vertexes)
        Map<Character, Node> graph = new HashMap();
        for (String rule : rules) {
            //split rules into to, direction and from parts
            String[] ruleSplit = rule.split(" ");
            char from = ruleSplit[2].charAt(0);
            char to = ruleSplit[0].charAt(0);
            //get nodes from graph map
            Node fromNode = graph.getOrDefault(from, new Node(from));
            graph.putIfAbsent(from, fromNode);
            Node toNode = graph.getOrDefault(to, new Node(to));
            graph.putIfAbsent(to, toNode);
            //need to do it as array because of possible complex directions like NE
            for (char d : ruleSplit[1].toCharArray()) {
                int dir = charToDirMap.get(d);
                //check if we will not violate rules with this new direction
                if (!isValid(fromNode, toNode, dir))
                    return false;
                //if it's valid - add edge (both directions)
                addEdges(fromNode, toNode, dir);
            }
        }

        return true;
    }

    boolean isValid(Node from, Node to, int dir) {
        //it's invalid only in case 'from' already has the opposite direction edge to 'to'
        int oppositeDir = (dir + 2) % 4;
        return !from.connections[oppositeDir].contains(to);
    }

    void addEdges(Node from, Node to, int dir) {
        //add direct neighbors edges - from->to and to->from
        from.connections[dir].add(to);
        int oppositeDir = (dir + 2) % 4;
        to.connections[oppositeDir].add(from);
        //now check other directions one by one
        for (int baseDir : charToDirMap.values()) {
            if (dir == baseDir) continue;
            for (Node neighbor : from.connections[baseDir]) {
                //skip self-edge
                if (neighbor.val == to.val) continue;
                neighbor.connections[dir].add(to);
                to.connections[oppositeDir].add(neighbor);
            }
        }
    }

    public static void main(String[] args) {
        LocationRuleValidator obj = new LocationRuleValidator();
        System.out.println(obj.validate(new String[] {
                "A N B",
                "B NE C",
                "C N A"})); //false
        System.out.println(obj.validate(new String[] {
                "A N B",
                "C SE B",
                "C N A"}));
        System.out.println(obj.validate(new String[] {
                "A NW B",
                "A N B"}));
        System.out.println(obj.validate(new String[] {
                "A N B",
                "C N B"}));
        System.out.println(obj.validate(new String[] {
                "A N B",
                "B N C",
                "C N D",
                "A N D"}));//true
    }

    class Node {
        char val;
        Set<Node>[] connections = new HashSet[4];

        public Node(char val) {
            this.val = val;
            Arrays.fill(connections, new HashSet());
        }
    }
}
