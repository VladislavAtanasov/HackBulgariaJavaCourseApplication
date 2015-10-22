import java.util.Scanner;

public class Points {

	public static void findPoint(String message, int start, int end){
		boolean reversed = false;
		for (int i = 0; i < message.length(); i++) {
			if (message.charAt(i) == '~') {
				if (reversed) {
					reversed = false;
				}else{
					reversed = true;
				}
			}
			if (message.charAt(i) == '<' && !reversed) {
				start--;
			} 
			else if(message.charAt(i) == '<' && reversed){
				start++;
			}
			if (message.charAt(i) == '>' && !reversed) {
				start++;
			} 
			else if(message.charAt(i) == '>' && reversed){
				start--;
			}
			if (message.charAt(i) == '^' && !reversed) {
				end--;
			} 
			else if(message.charAt(i) == '^' && reversed){
				end++;
			}
			if (message.charAt(i) == 'v' && !reversed) {
				end++;
			} 
			else if(message.charAt(i) == 'v' && reversed){
				end--;
			}
		}
		System.out.println("(" + start + ", " + end + ")");
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Starting point: ");
		String user = sc.next();
		int y = Integer.parseInt(user.substring(user.indexOf(",") + 1, user.indexOf(")")));
		int x = Integer.parseInt(user.substring(user.indexOf("(") + 1, user.indexOf(",")));
		String input = sc.next();
		findPoint(input, x, y);
		sc.close();
	}

}
