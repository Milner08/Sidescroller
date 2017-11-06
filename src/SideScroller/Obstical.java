package SideScroller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Obstical {

	public Image image;
	public int x,y;
	public int w = 50, h = 20;
	public Rectangle bounds;
	
	Obstical(int x, int y){
		ImageIcon icon = new ImageIcon("./obctical.png");
		image = icon.getImage();
		
		this.x = x;
		this.y = y;
		w = image.getWidth(null);
		h = image.getHeight(null);
		bounds = new Rectangle(x,y,h,w);
	}
	
	public int getX(){
		return x;
	}

	public int getY(){
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int getHeight() {
		return image.getHeight(null);
	}
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public boolean onTopOf(int xr,int xl){
		if((xr >= x && xl <= x+w)||(xl >= x && xr <= x+w)){
			return true;
		}
		else return false;
	}
	
	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}
	
	/*
	 * Update this shit!
	 */
	public void update(){
		
	}
	
	public synchronized void draw(Graphics2D g){
		g.drawImage(image,x,y,null);
	}
}
