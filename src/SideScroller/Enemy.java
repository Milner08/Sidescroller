package SideScroller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Enemy {

	public Image image;
	public int x=755,y, oy;
	public int dx=-1, dy=1;
	public Rectangle bounds;
	public boolean enemy = true, dead = false;
	public int health = 2;
	
	Enemy(int x, int y){
		ImageIcon icon = new ImageIcon("./enemy.png");
		image = icon.getImage();
		this.x = x;
		this.y = y;
		this.oy = y;
		bounds = new Rectangle(x,y,image.getHeight(null), image.getWidth(null));
	}
	
	public boolean isEnemy() {
		return enemy;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead() {
		this.dead = true;
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
	
	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
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
	
	public Rectangle getBounds() {
		return bounds;
	}

	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	public void hit(){
		health--;
		if(health == 0 ){
			dead=true;
		}
	}
	
	/*
	 * Update this shit!
	 */
	public void update(boolean moving){
		if(moving){
			this.x += this.dx;	
		}
		if(y<oy){dy=1;}
		if(y>(oy+50)){dy=-1;}
		this.y += this.dy;
		bounds = new Rectangle(x,y,image.getHeight(null), image.getWidth(null));
	}
	
	public synchronized void draw(Graphics2D g){
		g.drawImage(image,x,y,null);
	}
}
