
public class countOdds {
	public int count(int[] n, int pos) {
		if(n.length == 0 || n == null || pos < 1) return 0;
		if(pos == n.length ) {
			return (n[pos-1] % 2);
		}
		else return n[pos-1] % 2 + count(n, pos + 1);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		countOdds a  = new countOdds();
		int[] n = {1, 2 , 3, 4, 5, 6};
		int pos = 1;
		System.out.println(a.count(n, pos));
	}

}
