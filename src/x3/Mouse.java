package x3;

import org.lwjgl.glfw.GLFWCursorPosCallback;

public class Mouse extends GLFWCursorPosCallback{
	double X;
	double Y;
	int height;
	
	public Mouse(int height_in) {
		height = height_in;
		
	}
	
	@Override
	public void invoke(long win, double x, double y) {
		// TODO Auto-generated method stub
		X = x;
		Y = -1*(y - height);
		
		double[] pos = new double[2];
		pos[0] = X;
		pos[1] = Y;
		data.mousepos = pos;
		//System.out.println(X +  " " + Y);
	}
	
	
}
