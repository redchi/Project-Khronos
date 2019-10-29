package x3;

public class data {

	public static double[] mousepos = new double[2]; 
	public static double angle = 0;
	public static square[] bullets = new square[999];
	
	static int count=0;
	public static void addbullet(square bullet) {
		bullets[count] = bullet;
		count+=1;
		
	}
	
	
	
}
