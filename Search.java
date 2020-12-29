import java.util.*;

public class Search {
    public static final int[][] END = {
        {1, 2, 3},
        {4, 5, 6},
        {7, 8, 0}
    };

    public static void main(String[] args) {
        String solution = search(new Problem(), new State(END));
        System.out.println(solution);
    }
    
    public static String search(Problem p, State goal) {
        State start = new State(p.makeInitialPuzzle());

        p.printPuzzle();

        HashSet<State> closed = new HashSet<State>();
        Queue<State> open = new LinkedList<State>();                        // Breadth-first-search
        // Stack<State> open = new Stack<State>();                          // Depth-first-search

        open.add(start);
        
        while(!open.isEmpty()) {
            State s = open.remove();                                         // Breadth-first-search
            // State s = open.pop();                                         // Depth-first-search
            
            if (p.isGoal(s, goal)) {
                return s.getSolution();
            } else if (!closed.contains(s)) {
                closed.add(s);
                for(State c : p.successors(s)) {
                    open.add(c);
                }
            }
        }
        
        return "failure";
    }
}

