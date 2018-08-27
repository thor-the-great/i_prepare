package cracking.arrays_hashtables;

public class MaxtrixZeroes {
	  public static void main(String[] args) {
	    MaxtrixZeroes obj = new MaxtrixZeroes();
	    int[][] matrix = new int[3][4];
	    matrix[0] = new int[] { 0, 1, 2, 0 };
	    matrix[1] = new int[] { 3, 4, 5, 6 };
	    matrix[2] = new int[] { 6, 7, 0, 5 };
	    System.out.println("Original array: ");
	    printArray(matrix);
	    System.out.println("Processed array: ");
	    int[][] processed = obj.processZeroes(matrix);
	    printArray(processed);
	    
	    matrix = new int[3][4];
	    matrix[0] = new int[] { 1, 1, 2, 1 };
	    matrix[1] = new int[] { 0, 4, 5, 6 };
	    matrix[2] = new int[] { 6, 0, 3, 5 };
	    System.out.println("Original array: ");
	    printArray(matrix);
	    System.out.println("Processed array: ");
	    processed = obj.processZeroes(matrix);
	    printArray(processed);
	  }
	  
	  int[][] processZeroes(int[][] matrix) {
	    boolean[] columns = new boolean[matrix[0].length];
	    boolean[] rows = new boolean[matrix.length];
	    for (int i=0; i < matrix.length; i++) {
	      for (int j=0; j < matrix[0].length; j++) {
	        if (matrix[i][j] == 0) {
	          columns[j]=true;
	          rows[i]=true;
	        }
	      }
	    }
	    for (int i=0; i < matrix.length; i++) {
	      for (int j=0; j < matrix[0].length; j++) {
	        if (columns[j] || rows[i]) {
	          matrix[i][j] = 0;
	        }
	      }
	    } 
	    return matrix;
	  }
	  
	  private static void printArray(int[][] array) {
		for (int[] i : array) {
			for (int j : i) {
				System.out.print(j + ", ");
			}
			System.out.print("\n");
		}
	  }
	}