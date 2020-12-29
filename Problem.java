import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Problem {
    private int[][] initial = new int[3][3];
    private Random rand = new Random();                     // Didn't like being in makeInitialPuzzle...

    public int[][] makeInitialPuzzle(){
        ArrayList<Integer> used = new ArrayList<>();        // List with previously added values (for uniqueness)
    
        for (int row = 0; row < initial.length; row ++) {
            for (int col = 0; col < initial.length; col ++) {
                int randomNumber = rand.nextInt(9);         // Random number between 0 & 8

                if(used.isEmpty()){
                    initial[row][col] = randomNumber;               
                }else{
                    while(used.contains(randomNumber)){
                        randomNumber = rand.nextInt(9);
                    }
                    initial[row][col] = randomNumber;
                }
                used.add(randomNumber);
            }
        }

        return initial;
    }
    
    public boolean isGoal(State curr, State goal) {
        return curr.equals(goal);
    }
    
    // Creates list of successors.
    public List<State> successors(State curr) {                 
        List<State> list = new ArrayList<State>();

        int a = 0; 
        int b = 0;
        for (int i = 0; i < curr.puzzle.length; i ++){
            for (int j = 0; j < curr.puzzle.length; j ++){
                int m = curr.puzzle[i][j];           
                if(m == 0){
                    a = i;
                    b = j;
                }
            }
        }

        if(b < 2){
            list.add(new State(moveRight(curr.puzzle, a, b), curr));
        }
        if(b > 0){
            list.add(new State(moveLeft(curr.puzzle, a, b), curr));
        }
        if(a < 2){
            list.add(new State(moveDown(curr.puzzle, a, b), curr));
        }
        if(a > 0){
            list.add(new State(moveUp(curr.puzzle, a, b), curr));
        }

        return list;
    }

    public void copyPuzzle(int[][] first, int[][] second){
        for (int i = 0; i < first.length; i ++){
            for (int j = 0; j < first.length; j ++){
                first[i][j] = second[i][j];
            }
        }
    }

    public void printPuzzle(){                                              // For debugging
        System.out.println("\nInitial Puzzle: ");
        for (int i = 0; i < initial.length; i ++) {
            for (int j = 0; j < initial.length; j ++) {
                System.out.print(initial[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public int[][] moveRight(int[][] puzzle, int a, int b){
        int[][] opt = new int[3][3];    

        copyPuzzle(opt, puzzle);               

        int temp = opt[a][b + 1];
        opt[a][b + 1] = opt[a][b];
        opt[a][b] = temp;

        return opt;
    }

    public int[][] moveLeft(int[][] puzzle, int a, int b){
        int[][] opt = new int[3][3];
       
        copyPuzzle(opt, puzzle);

        int temp = opt[a][b - 1];
        opt[a][b - 1] = opt[a][b];
        opt[a][b] = temp;
 
        return opt;
    }
        
    public int[][] moveDown(int[][] puzzle, int a, int b){
        int[][] opt = new int[3][3];
        
        copyPuzzle(opt, puzzle);

        int temp = opt[a + 1][b];
        opt[a + 1][b] = opt[a][b];
        opt[a][b] = temp;

        return opt;
    }

    public int[][] moveUp(int[][] puzzle, int a, int b){
        int[][] opt = new int[3][3];
        
        copyPuzzle(opt, puzzle);

        int temp = opt[a - 1][b];
        opt[a - 1][b] = opt[a][b];
        opt[a][b] = temp;

        return opt;
    }
}