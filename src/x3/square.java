package x3;

import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL11;

public class square {
	int height;
	int length;
	float angle;
	double[] pos = new double[2];
	boolean facingforward;
	
	double bx;
	double by;
	
	double ba;
	
	public square(int size_x,int size_y) {
		height = size_y;
		length = size_x;
		angle = 0;
		facingforward = true;
	}
	
	public void draw(double x_in,double y_in) {
			int l = length/2;
			int h = height/2;
			double x;
			double y;
			if( pos[0] == 0) {
				 x = x_in;
				 y = y_in;
				pos[0]=x;
				pos[1]=y;
			}
			else {
				x = pos[0];
				y = pos[1];
				
			}
			
		   
			GL11.glVertex2d(x+l,y+h);
		   GL11.glVertex2d(x+l ,y -h);
		   GL11.glVertex2d(x-l ,y-h);
		   GL11.glVertex2d(x-l,y+h);
	
		
	}
	
	public void rotate(double[] mousepos) {
		if(angle>=360) {
			angle = angle - 360;
		}
		
		
		double mx = mousepos[0];
		double my = mousepos[1];
		double x = pos[0];
		double y = pos[1];

		 /*
		 GL11.glBegin(GL_LINE);
		 GL11.glVertex2d(x, y);
		 GL11.glVertex2d(mx, my);
		 GL11.glEnd();
		 */
		 
		String quad = new String();
		if(facingforward == false) {
			glTranslatef((float)x,(float) y, 0);
			GL11.glRotatef(angle, 0, 0, 1);
			glTranslatef((float)-x,(float) -y, 0);
			facingforward = true;
		}
		
		
		//which quad ffs
		int inverter_x;
		int inverter_y;
		if(mx>x) {
			quad+="+";
			inverter_x = 1;
		}
		else {
			quad+="-";
			inverter_x = -1;
		}
		
		if(my>y) {
			quad+="+";
			inverter_y = 1;
		}
		else {
			quad+="-";
			inverter_y = -1;
		}
		double O = inverter_y*(my-y);
		double A = inverter_x*(mx-x);
		//System.out.println(O/A);
		
		 double cal_angle = Math.toDegrees(Math.atan(O/A));
		double rotation_angle = 0;
		 switch (quad) {
		  case "++":
			  rotation_angle = 90 - cal_angle;
			  break;
			  
		  case "+-":
			  rotation_angle = -1*(90 + 90 - cal_angle);
			  break;
			  
		  case "--":
			  rotation_angle = 180 + 90 - cal_angle;
			  break;
			  
		  case "-+":
			  rotation_angle = -1*(270 + 90 - cal_angle);
			  break;
		  default:
			    System.out.println("Quad Error!"); 
		 }
		 GL11.glVertex2d(x, y);
		 GL11.glVertex2d(mx, my);
		 
		 float angle1 = (float) rotation_angle; 
		// System.out.println("Rot angle = "+ angle + " Quad = "+ quad );
		 
	//	System.out.println(angle1+" degres" + "	Quad - " + quad + "cur = " + angle);
			 angle = angle1;
			 glTranslatef((float)x,(float) y, 0);
			// glTranslatef(,0,0);
			 GL11.glRotatef(-angle1, 0, 0, 1);
			 glTranslatef((float)-x,(float) -y, 0);
			 facingforward = false;
			
	}
		 
	
	public void move(String key) {
		//System.out.println("1");
 //glTranslatef((float)pos[0],(float) pos[1], 0);
		
		
		
		glTranslatef((float)pos[0],(float) pos[1], 0);
		GL11.glRotatef(angle, 0, 0, 1);
		glTranslatef((float)-pos[0],(float) -pos[1], 0);
		
		float speed = 1.5f;
		switch (key) {
		  case "w":
			
			  glTranslatef(0,speed,0);
			  pos[1] = pos[1] + speed;
			  System.out.println(pos[1]);
			  break;
		  case "a":
			  glTranslatef(-speed,0,0);
			  pos[0] = pos[0]- speed;
			  break;
		  case "s":
			  glTranslatef(0,-speed,0);
			  pos[1] = pos[1] - speed;
			  break;
		  case "d":
			  glTranslatef(speed,0,0);
			  pos[0] = pos[0]+ speed;
			  break;
			
			 
			  
	}
		
		facingforward = true;
		rotate(data.mousepos);
			//rotate
		
		
	//	glTranslatef((float) -pos[0],(float) -pos[1], 0);
		//System.out.println(pos[1]);
	
	}
	
	
	public void shoot(double[] mousepos) {
		
	//	glTranslatef((float)pos[0],(float) pos[1], 0);
		
		
		double mx = mousepos[0];
		double my = mousepos[1];
		double x = pos[0];
		double y = pos[1];
		
		
		double O = (my-y);
		double A = (mx-x);
		System.out.println(O+" "+A);
		
		square bullet = new square(10,10);
		
		ba = Math.atan(O/A);
		float speed = 5f;
		bullet.bx = Math.cos(ba)*speed;
		bullet.by = Math.sin(ba)*speed;
		
		
		 System.out.println("bx - "+bx);
		 try {
			Thread.sleep(40);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
		
		
		//GL11.glBegin(GL11.GL_QUADS);
		bullet.draw(x,y);
		//GL11.glEnd();
		data.addbullet(bullet);
		System.out.println();
	//	glTranslatef((float)-pos[0],(float) -pos[1], 0);
	}
	
	public void movebullet() {
		//System.out.println("called ");
		pos[0]+=bx;
		pos[1]+=by;
	}
	
}
