package x3;

//import org.lwjgl.*;
//import org.lwjgl.opengl.GL11.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import  org.lwjgl.glfw.GLFWCursorPosCallback;
//import  org.lwjgl.

public class Main {
	
	int SCREEN_WIDTH = 800;
	int SCREEN_HEIGHT = 800;
	
	GLFWCursorPosCallback mousepos;
	
	public Main() {
	
		
		if ( glfwInit() == false) {
			System.out.print("error glf");
		}
		
		long win = 	glfwCreateWindow(SCREEN_WIDTH, SCREEN_HEIGHT, "1st", 0, 0);
		
		glfwShowWindow(win);
		glfwMakeContextCurrent(win);
		GL.createCapabilities();
	
	//	square s = new square(100,100,50,50);
		
		//glViewport( 0.0f, 0.0f, SCREEN_WIDTH, SCREEN_HEIGHT ); // specifies the part of the window to which OpenGL will draw (in pixels), convert from normalised to pixels
	    glMatrixMode( GL_PROJECTION ); // projection matrix defines the properties of the camera that views the objects in the world coordinate frame. Here you typically set the zoom factor, aspect ratio and the near and far clipping planes
	    glLoadIdentity( ); // replace the current matrix with the identity matrix and starts us a fresh because matrix transforms such as glOrpho and glRotate cumulate, basically puts us at (0, 0, 0)
	    glOrtho( 0, SCREEN_WIDTH, 0, SCREEN_HEIGHT, 0, 1 ); // essentially set coordinate system
	    glMatrixMode( GL_MODELVIEW ); // (default matrix mode) modelview matrix defines how your objects are transformed (meaning translation, rotation and scaling) in your world
	    glLoadIdentity( ); // same as above comment
	
	    square s = new square(100,100);
		while (glfwWindowShouldClose(win) == false){
			glfwPollEvents();
			glClear(GL_COLOR_BUFFER_BIT);
			//s.draw(50, 50);
			mousepos = new Mouse(SCREEN_HEIGHT);

			
			glfwSetCursorPosCallback(win, mousepos );
			
			//Mouse mp = (Mouse) mousepos;

			//System.out.println(data.mousepos[0]);
			
			//glTranslatef(100, 100, 0);
			//GL11.glRotatef(20, 0, 0, 1);
			GL11.glBegin(GL11.GL_QUADS);
			
			
			for(square b : data.bullets) {
				if(b!= null) {
					b.draw(200, 200);
					b.movebullet();
				}
			}
			
			
			//s.draw(50, 50);
			//glTranslatef(100,300,0);
			s.draw(200,100);
			
			float speed =0.4f;
			
			
			//
				
			//check for bullet
			// shoot it
			
		    GL11.glEnd();
		
		   
			
			//
		    
			checkinput(win,s);
			
			
			s.rotate(data.mousepos);
		   // glTranslatef(-100, -100, 0);
			//GL11.glRotatef(-1f, 0, 0, 1);
			glfwSwapBuffers(win);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		
		glfwTerminate();
		
	}
	
	public void checkinput(long win, square s) {
		if(glfwGetKey(win, GLFW_KEY_W) == GLFW_TRUE) {
			s.move("w");
		}
		else if(glfwGetKey(win, GLFW_KEY_A) == GLFW_TRUE) {
			s.move("a");
		}
		else if(glfwGetKey(win, GLFW_KEY_S) == GLFW_TRUE) {
			s.move("s");
		}
		else if(glfwGetKey(win, GLFW_KEY_D) == GLFW_TRUE) {
			s.move("d");
		}
		else if(glfwGetMouseButton(win, GLFW_MOUSE_BUTTON_1) == GLFW_TRUE) {
			GL11.glBegin(GL11.GL_QUADS);
			s.shoot(data.mousepos);
			GL11.glEnd();
		}
		
	}

	public static void main(String[] args) {
		Main n = new Main();
	//	n.t1();
	}
	


}





