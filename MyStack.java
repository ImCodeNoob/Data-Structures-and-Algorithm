import java.util.*;

public class MyStack<AnyType> {
	private ArrayList<AnyType> list;
	int size;
	public MyStack() {
		list = new ArrayList<AnyType>();
		size = 0;
	} 
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void push(AnyType item) {
		list.add(item);
		size++;
	}
	
	public AnyType pop() {
		AnyType t = list.remove(size -1);
		size--;
		return t;
	}
	
	public AnyType top() {
		AnyType t = list.get(size -1);
		return t;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String s = sc.next();
		if(s == null) throw new NullPointerException();
		Map<Character, Character> map = new HashMap<>();
		map.put(')', '(');
		map.put(']', '[');
		map.put('}', '{');
		MyStack<Character> stack = new MyStack<>();
		for(int i=0; i<s.length(); i++) {
			Character c = s.charAt(i);
			switch (c) {
				case '(': case'[': case '{':
					stack.push(c);
					break;
				case ')': case']': case'}':
					if(stack.isEmpty() || stack.pop() != map.get(c))  {
						System.out.println("There is an error");
					}
					break;
			}
		}
		System.out.println(stack.isEmpty());
	}
}
