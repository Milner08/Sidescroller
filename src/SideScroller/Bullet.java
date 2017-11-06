package SideScroller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Bullet {
	public Image bullet;
	public int x,y, dx=2, dy=0;
	public boolean expired;
	public Rectangle bounds;
	
	public Bullet(int x,int y, int dx){
		ImageIcon Icon = new ImageIcon("./bullet.png");
		bullet = Icon.getImage();
		this.x = x;
		this.y = y;
		this.dx = dx;
		bounds = new Rectangle(x,y,bullet.getWidth(null),bullet.getHeight(null));
	}

	public Image getBullet() {
		return bullet;
	}

	public void setBullet(Image bullet) {
		this.bullet = bullet;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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
	
	public boolean colisionDetection(Enemy enemy){
		if(bounds.intersects(enemy.getBounds())){
			expired = true;
			enemy.hit();
			return true;
		}
		return false;
	}
	
	public void update(){
		this.x += dx;
		bounds = new Rectangle(x,y,bullet.getWidth(null),bullet.getHeight(null));
		if(x > 750){
			expired = true;
		}
	}
	
	public synchronized void draw(Graphics2D g){
		g.drawImage(bullet,x,y,null);
	}
}
