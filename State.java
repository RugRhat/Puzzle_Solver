import java.util.ArrayList;

public class State implements Comparable<State> {
    public int[][] puzzle;
    public State parent;
    public int label;
    
    public State(int[][] p) {
        this.puzzle = p;
        this.parent = null;

        this.label =  Integer.parseInt(createLabel(p));
    }

    public State(int[][] p, State parent) {
        this.puzzle = p;
        this.parent = parent;
        this.label =  Integer.parseInt(createLabel(p));
    }
        
    public String getSolution() {                           // Issue: Prints steps in reverse order :(
        String solution = "SOLVED to UNSOLVED: \n";
        State walker = this;

        while(walker != null) {
            for (int i = 0; i < walker.puzzle.length; i ++){
                for (int j = 0; j < walker.puzzle.length; j ++){
                    solution += walker.puzzle[i][j] + " ";
                }
                solution += "\n";
            }

            solution += "\n";
            walker = walker.parent;
        }

        return solution;
    }

    // Creates a label to use for getting the hashcode.
    public String createLabel(int[][] p){                  
        String thisLabel = "";
        for(int i = 0; i < p.length; i ++){
            for(int j = 0; j < p.length; j ++){
                thisLabel += "" + p[i][j] ;                 // Label based on order of numbers
            }
        }

        return thisLabel;
    }
    
    @Override
    public int hashCode() {                         
        return this.label;
    }
    
    @Override
    public boolean equals(Object o) {  
        boolean equal = true;

        if (o instanceof State) {
            for(int i = 0; i < this.puzzle.length; i ++){
                for(int j = 0; j < this.puzzle.length; j ++){
                    if(this.puzzle[i][j] != ((State) o).puzzle[i][j]){
                        equal = false;
                    }
                }
            }
        } else {
            equal = false;
        }

        return equal;
    }
    
    @Override
    public int compareTo(State o) {              // Uses cost... since all costs are equal it returns 0
        return 0;
    }
}

