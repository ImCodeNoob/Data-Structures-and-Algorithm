import java.util.*;

public class EvaulateTemperature {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		double temp = sc.nextDouble();
		String unit = sc.next();
		if(unit.equals("C") || unit.equals("c") || unit.equals("Celsius")) {
			temp = 1.80 * temp + 32;
		}
		if(temp < 0) System.out.println("Extremyly cold");
		if(temp >= 0 && temp <= 32) System.out.println("Very cold");
		if(temp >= 33 && temp <= 50) System.out.println("Cold");
		if(temp >= 51 && temp <= 70) System.out.println("Mild");
		if(temp >= 71 && temp <= 90) System.out.println("Warm");
		if(temp >= 91 && temp <= 100) System.out.println("Hot");
		if(temp > 100) System.out.println("Very hot");
	}

}
