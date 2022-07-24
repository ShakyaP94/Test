package main;

public class Test1 {
    // stores the cell count
    static int COUNT;

    static final int r = 6; //rows
    static final int c = 8; // cell

    // stores information about which cell visited
    static final int visited[][] = new int[r][c];

    // result stores the final
    static final int result[][] = new int[r][c];


    /**
     * @param input find and generate the largest grid by search and mark as visited the cel
     */
    static void generateLargestGrid(int input[][]) {

        int current_max = Integer.MIN_VALUE;

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                resetVisitCount();
                COUNT = 0;

                // checking cell to the right side and update result
                if (j + 1 < c)
                    searchAndVisitCounting(input[i][j], input[i][j + 1],
                            i, j, input);

                if (COUNT >= current_max) {
                    current_max = COUNT;
                    resetResult(input[i][j], input);
                }
                resetVisitCount();
                COUNT = 0;

                // checking cell downwards side and update result
                if (i + 1 < r)
                    searchAndVisitCounting(input[i][j],
                            input[i + 1][j], i, j, input);

                if (COUNT >= current_max) {
                    current_max = COUNT;
                    resetResult(input[i][j], input);
                }
            }
        }
        printResult(current_max);
    }


    /**
     * finding all cells connect with input values
     * input[][]
     */
    static void searchAndVisitCounting(int x, int y, int i, int j, int input[][]) {
        // terminate if x not equal to y
        if (x != y)
            return;

        visited[i][j] = 1;
        COUNT++;

        // x_move and y_move arrays
        int x_move[] = {0, 0, 1, -1};
        int y_move[] = {1, -1, 0, 0};

        // checks all four points
        for (int u = 0; u < 4; u++)
            if ((isValid(i + y_move[u],
                    j + x_move[u], x, input)) == true)
                searchAndVisitCounting(x, y, i + y_move[u],
                        j + x_move[u], input);
    }

    // function to print the result
    static void printResult(int res) {

        // prints the largest component
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (result[i][j] != 0)
                    System.out.print(result[i][j] + " ");
                else
                    System.out.print("* ");
            }
            System.out.println();
        }

        System.out.println("The largest grid size is :" + res);
    }

    /**
     * checks if a cell is valid
     */
    static boolean isValid(int x, int y, int key, int input[][]) {
        if (x < r && y < c &&
                x >= 0 && y >= 0) {
            if (visited[x][y] == 0 &&
                    input[x][y] == key)
                return true;
            else
                return false;
        } else
            return false;
    }


    /**
     * visited array is reset to zero
     */
    static void resetVisitCount() {
        for (int i = 0; i < r; i++)
            for (int j = 0; j < c; j++)
                visited[i][j] = 0;
    }

    /**
     * reset result
     */
    static void resetResult(int key, int input[][]) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                if (visited[i][j] == 1 &&
                        input[i][j] == key)
                    result[i][j] = visited[i][j];
                else
                    result[i][j] = 0;
            }
        }
    }


    //main method
    public static void main(String args[]) {

        //this inputs can apply randomly
        int input[][] = {
                {1, 2, 4, 4, 4, 3, 3, 1},
                {2, 1, 1, 4, 3, 3, 1, 1},
                {3, 2, 1, 1, 2, 3, 2, 1},
                {3, 3, 2, 1, 2, 2, 2, 2},
                {3, 1, 3, 1, 1, 4, 4, 4},
                {1, 1, 3, 1, 1, 4, 4, 4}
        };
        //call largest grid generator
        generateLargestGrid(input);
    }
}