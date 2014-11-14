public class Gifts {
    public static void main(String[] args) {
        int [][] gifts = new int[][] {new int[]{1, 10, 3, 8}, new int[]{12,2,9,6}, new int[]{5,7,4,11}, new int[]{3, 7, 16, 5}};
        max(gifts, 4, 4);
        
    }

    public static void max(int[][] gifts, int cols, int rows) {
        System.out.println(mmax(gifts, cols, rows, 0, 0));
    }

    static int[] mm = new int [44];
    public static int mmax(int[][] gifts, int cols, int rows, int col, int row) {
        if (mm[row*10 + col] != 0) {
            System.out.println("MM: " + col + ":" + row);
            return mm[row*10 + col];
        }
        int right = -1;
        int bottom = -1;
        if (col < cols - 1) {
            right = mmax(gifts, cols, rows, col+1, row);
        }
        if (row < rows - 1) {
            bottom = mmax(gifts, cols, rows, col, row+1);
        }
        int v = gifts[row][col];
        int r;
        if (right > bottom) {
            r = v+right;
        } else if (bottom > right) {
            r = v+bottom;
        } else { 
            r = v;
        }

        mm[row*10 + col] = r;
        return r;
            
    }

    public static void print(int[][] gifts) {
        for (int i = 0; i < gifts.length; i++) {
            int[] r = gifts[i];
            for (int j = 0; j < r.length; j++) {
                System.out.print(r[j] + "\t"); 
            }
            System.out.println();
        }
    }
}
