package SideScroller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Background {

	public Image backgroundImage;
	public int x=0,y=0, dx=0, dy=0;
	
	Background(){
		ImageIcon backgroundIcon = new ImageIcon("./background.png");
		backgroundImage = backgroundIcon.getImage();
	}
	
	Background(int x){
		ImageIcon backgroundIcon = new ImageIcon("./background.png");
		backgroundImage = backgroundIcon.getImage();
		this.x = x;
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
	
	public void update(){
		this.x += this.dx;
		this.y += this.dy;
		
	}

	public Image getImage() {
		return backgroundImage;
	}

	public void setImage(Image backgroundImage) {
		this.backgroundImage = backgroundImage;
	}

	public int getDx() {
		return dx;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public int getDy() {
		return dy;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT){
			setDx(0);
		}else if(key == KeyEvent.VK_RIGHT){
			setDx(0);
		}
	}

	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT){
			setDx(1);
		}else if(key == KeyEvent.VK_RIGHT){
			setDx(-1);
		}
	}
	
	public synchronized void draw(Graphics2D g){
		g.drawImage(backgroundImage,x,y,null);
	}
}
