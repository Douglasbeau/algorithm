public class TestAlloc{
	private static final int _1M = 1024*1024;
	public static void main(String[] args) {
		byte[] alloc1, alloc2, alloc3, alloc4;
		alloc1 = new byte[2*_1M];
		alloc2 = new byte[2*_1M];
		alloc3 = new byte[2*_1M];
		alloc4 = new byte[4*_1M];
	}
}
