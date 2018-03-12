package utils;

public class InitOrder {

	private int i = 9;
	private String s = "hello world";
	
	public InitOrder() {
		System.out.println("i=" + i);
		System.out.println("s=" + s);
		System.out.println("init InitOrder");
		i = 13;
		s = "hello";
		System.out.println("i=" + i);
		System.out.println("s=" + s);
	}
}
