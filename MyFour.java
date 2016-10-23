
public class MyFour<T> {
	private T item1;
	private T item2;
	private T item3;
	private T item4;
	public MyFour(T item1, T item2, T item3, T item4) {
		this.item1 = item1;
		this.item2 = item2;
		this.item3  = item3;
		this.item4 = item4;
	}
	
	public boolean allEqual() {
		if(item1.equals(item2) && item2.equals(item3) && item3.equals(item4)) return true;
		else return false;
	}
	
	public void shiftLeft() {
		T tmp = item1;
		item1 = item2;
		item2 = item3;
		item3  = item4;
		item4 = tmp;
	}
	
	public String toString() {
		return "(" + item1 + ", " + item2 + ", " + item3 + ", " + item4 + ")";
	}
	
	public static void main(String[] args) {
		MyFour str = new MyFour("w", "w", "w", "w");
		System.out.println(str);
		System.out.println(str.allEqual());
		MyFour number = new MyFour(1, 2, 3, 4);
		System.out.println(number);
		System.out.println(number.allEqual());
		number.shiftLeft();
		System.out.println(number);
		
	}
}
