
import java.util.*;
public class RandomFromSet {
	
	public static void main (String[] args) {
		
		double n = 2 + (Math.random() * 10) / 2 * 2;
		//n = n *2;
		System.out.print(doRound(n));
	}
	
	private static double doRound(double x) {
		
		double y = Math.floor(x + 0.5);
		return y;
	}
	

}
