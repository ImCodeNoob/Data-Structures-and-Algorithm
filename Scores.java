import java.util.*;

public class Scores {
	private static double average(double[] n) {
		double sum = 0;
		for(int i=0; i<n.length; i++) {
			sum = sum + n[i];
		}
		return sum / n.length;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] names = new String[10];
		double[][] scores = new double[10][5];
		Scanner sc = new Scanner(System.in);
		for(int i=0; i < names.length; i++) {
			names[i] = sc.next();
			for(int j=0; j<scores[i].length; j++) {
				scores[i][j] = sc.nextDouble();
			}
		}
		for(int i=0; i<names.length; i++) {
			System.out.println(names[i] + " Average Score is : " + average(scores[i]));
		}
	}

}
