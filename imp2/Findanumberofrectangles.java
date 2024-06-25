/* 
Input: 
0 1 1 0 0
1 1 1 0 0
0 0 0 1 1
0 0 0 1 1
Output: 2
*/
class Findanumberofrectangles{
 public static void main(String[] args) {
        int[][] matrix = {
            {0, 1, 1, 0, 0},
            {1, 1, 1, 0, 0},
            {0, 0, 0, 1, 1},
            {0, 0, 0, 1, 1}
        };
        
        int count = countRectangles(matrix);
        System.out.println("Number of rectangles: " + count);  // Output: 2
    }

    public static int countRectangles(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == 1) {
                    count++;
                    markRectangle(matrix, i, j);
                }
            }
        }
        return count;
    }

    private static void markRectangle(int[][] matrix, int startRow, int startCol) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Find the extent of the rectangle
        int endRow = startRow;
        int endCol = startCol;

        while (endRow < rows && matrix[endRow][startCol] == 1) {
            endRow++;
        }
        endRow--;

        while (endCol < cols && matrix[startRow][endCol] == 1) {
            endCol++;
        }
        endCol--;

        // Mark the rectangle as visited
        for (int i = startRow; i <= endRow; i++) {
            for (int j = startCol; j <= endCol; j++) {
                matrix[i][j] = 0;
            }
        }
    }
}