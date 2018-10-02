import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.net.ServerSocket;

import javax.swing.JFrame;

public class Circle1 extends JFrame{
	
	private int radius;
	private int vuj;
	private int koti;
	
	public Circle1(int radius,int vuj, int koti)
	{
		this.radius=radius;
		this.vuj=vuj;
		this.koti=koti;

		frame();
				
	}
	
	public void frame()
	{
		setTitle("circle");
		setSize(800, 600);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE );
		
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.blue);
		g.fillRect(0, 0, 800, 600);
		g.setColor(Color.GREEN);
		g.fillArc(vuj-radius, koti-radius, 2*radius, 2*radius, 0, 200);
		g.fillArc(vuj-radius, koti-radius, 2*radius, 2*radius, 200, 360);
		g.setFont(new Font("Arial Bold",Font.BOLD,30));
		g.drawString("bal", 100, 100);
		
		
		
	}
	
	public void c() {
		int x=7,y=0,z=10;
		if(x>5) {
			if(y<0) {
				
			}
			else {
				
			}
		}
		else if(z>8) {
			
		}
		
		int numberOfPlayers = 6;
	    String sport = null;

	    if (n >= 0) {
	        switch(n) {
	            case 0:
	            case 1: 
	                printf("zero or one\n");
	                break;
	            case 2: 
	                printf("two\n");
	                break;
	            case 3:
	            case 4: 
	                printf("three or four\n");
	                break;
	            default:
	            	 printf("greater than 4\n");
		             break;
	            	
	                
	        		
	            }
	        	
	        }
	    else {
	        printf ("negative\n");
	    }
	    
	    
	    for(int i=0;i<10;i++) {
	    	
	    }
	    while(x<10) {
	    	if(y>7);
	    }
	    do {
			
		} while (z>5);
	}
	public void d() {
		int x,y;
		if(x>y && y>5) {
			
		}
		else if(x<y || y<2) {
			
		}
		else if(x<y|| y<2);
		
	}
	
	

}
