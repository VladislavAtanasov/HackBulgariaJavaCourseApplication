import java.util.Scanner;

public class WordGame {

	private static int count = 0;
	
	public static int findWord(char[][] matrix, String word){
		if (word.length() <= matrix.length && word.length() <= matrix[0].length){ 
			int start = 0;
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[i].length; j++) {
					if (word.charAt(0) == matrix[i][j]) {
						findHorizontal(matrix, word, i, j, start);
						findHorizontalBack(matrix, word, i, j, start);
						findVertical(matrix, word, i, j, start);
						findVerticalBack(matrix, word, i, j, start);
						findFDiagonal(matrix, word, i, j, start);
						findFDiagonalBack(matrix, word, i, j, start);					
						findSDiagonal(matrix, word, i, j, start);
						findSDiagonalBack(matrix, word, i, j, start);
					}
				}
			}
			
			return count;
		}
		return count;
	}
	
	private static void findHorizontal(char[][] matrix, String find, int row, int col, int current){
		if (col >= matrix[row].length || find.charAt(current) != matrix[row][col]) {
			return;
		}
		
		if (find.charAt(find.length() - 1) == matrix[row][col]) {
			count++;
			return;
		}
		
		if (find.charAt(current) == matrix[row][col]) {
			findHorizontal(matrix, find, row, col + 1, current + 1);
		}
	}
	
	private static void findHorizontalBack(char[][] matrix, String find, int row, int col, int current){
		if (col < 0 || find.charAt(current) != matrix[row][col]) {
			return;
		}
		
		if (find.charAt(find.length() - 1) == matrix[row][col]) {
			count++;
			return;
		}
		
		if (find.charAt(current) == matrix[row][col]) {
			findHorizontalBack(matrix, find, row, col - 1, current + 1);
		}
	}
	
	private static void findVertical(char[][] matrix, String find, int row, int col, int current){
		if (row >= matrix.length || find.charAt(current) != matrix[row][col]) {
			return;
		}
		
		if (find.charAt(find.length() - 1) == matrix[row][col]) {
			count++;
			return;
		}
		
		if (find.charAt(current) == matrix[row][col]) {
			findVertical(matrix, find, row + 1, col, current + 1);
		}
	}
	
	private static void findVerticalBack(char[][] matrix, String find, int row, int col, int current){
		if (row < 0 || find.charAt(current) != matrix[row][col]) {
			return;
		}
		
		if (find.charAt(find.length() - 1) == matrix[row][col]) {
			count++;
			return;
		}
		
		if (find.charAt(current) == matrix[row][col]) {
			findVerticalBack(matrix, find, row - 1, col, current + 1);
		}
	}
	
	private static void findFDiagonal(char[][] matrix, String find, int row, int col, int current){
		if (row >= matrix.length || col >= matrix[row].length || find.charAt(current) != matrix[row][col]) {
			return;
		}
		
		if (find.charAt(find.length() - 1) == matrix[row][col]) {
			count++;
			return;
		}
		
		if (find.charAt(current) == matrix[row][col]) {
			findFDiagonal(matrix, find, row + 1, col + 1, current + 1);
		}
	}
	
	private static void findFDiagonalBack(char[][] matrix, String find, int row, int col, int current){
		if (row < 0 || col < 0 || find.charAt(current) != matrix[row][col]) {
			return;
		}
		
		if (find.charAt(find.length() - 1) == matrix[row][col]) {
			count++;
			return;
		}
		
		if (find.charAt(current) == matrix[row][col]) {
			findFDiagonalBack(matrix, find, row - 1, col - 1, current + 1);
		}
	}
	
	private static void findSDiagonal(char[][] matrix, String find, int row, int col, int current){
		if (row >= matrix.length || col < 0 || find.charAt(current) != matrix[row][col]) {
			return;
		}
		
		if (find.charAt(find.length() - 1) == matrix[row][col]) {
			count++;
			return;
		}
		
		if (find.charAt(current) == matrix[row][col]) {
			findSDiagonal(matrix, find, row + 1, col - 1, current + 1);
		}
	}
	
	private static void findSDiagonalBack(char[][] matrix, String find, int row, int col, int current){
		if (row < 0 || col > matrix[row].length || find.charAt(current) != matrix[row][col]) {
			return;
		}
		
		if (find.charAt(find.length() - 1) == matrix[row][col]) {
			count++;
			return;
		}
		
		if (find.charAt(current) == matrix[row][col]) {
			findSDiagonalBack(matrix, find, row - 1, col + 1, current + 1);
		}
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String search = sc.next();
//		char[][] table = {
//				{'i','v','a','n'},
//				{'e','v','n','h'},
//				{'i','n','a','v'},
//				{'m','v','v','n'},
//				{'q','r','i','t'}
//		};
		int n = sc.nextInt();
		int m = sc.nextInt();
		char[][] table = new char[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				String symbol = sc.next();
				table[i][j] = symbol.charAt(0);
			}
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				System.out.print(table[i][j] + " ");
			}
			System.out.println();
		}
		int result = findWord(table, search);
		System.out.println(result);
		sc.close();
	}

}
