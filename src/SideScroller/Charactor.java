package SideScroller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;

/**
 * Charator - A player. 
 * Has an X and Y location. A x and y speed (dx, dy), A floor value, Height it can jump. 
 * Has a weapon, and a set of bullets. 
 * Health of 10. 
 * @author tomas
 *
 */
public class Charactor {
	private Image charactor;
	private int x=41,y=405, dx=0, dy=0, oy = 405, baseY = 405, jumpingHeight = 100;
	private boolean jumped = false, jumping = false, crouching = false;
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private ImageIcon charactorIcon = new ImageIcon("./charactorRight.png");
	private ImageIcon charactorLeftIcon = new ImageIcon("./charactorLeft.png");
	private	ImageIcon crouchedIcon = new ImageIcon("./crouchingRight.png");
	private ImageIcon crouchedLeftIcon = new ImageIcon("crouchingLeft.png");
	private Rectangle bounds;
	private float health = 10;
	private Direction direction = Direction.RIGHT;
	
	/**
	 * The direction of travel. 
	 * @author tomas
	 *
	 */
	private enum Direction{
		RIGHT,LEFT
	}
	
	
	/**
	 * Constructor. Gives it an Icon. Sets the bounds of the player. 
	 */
	Charactor(){
		charactor = charactorIcon.getImage();
		bounds = new Rectangle(x,y,charactor.getWidth(null),charactor.getHeight(null));
	}
	
	/**
	 * Get the X position
	 * @return X position
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * Get the Y position
	 * @return Y position
	 */
	public int getY(){
		return y;
	}

	/**
	 * Set X position
	 * @param x the new x position. 
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Set Y position
	 * @param y the new y position.
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Get the BaseY value.
	 * @return the baseY value.
	 */
	public int getBaseY() {
		return baseY;
	}
	
	/**
	 * Get Oy value.
	 * @return the oy value.
	 */
	public int getOy() {
		return oy;
	}

	/**
	 * Set Oy value.
	 * @param oy the new oy value.
	 */
	public void setOy(int oy) {
		this.oy = oy;
	}
	
	/**
	 * Is the charactor jumping?
	 * @return true if jumping, false if not.
	 */
	public boolean isJumping() {
		return jumping;
	}
	
	/**
	 * Is the charactor crouching?
	 * @return true if crouching, false if not.
	 */
	public boolean isCrouching() {
		return crouching;
	}

	/**
	 * Get the width of the charactor.
	 * @return charactors width.
	 */
	public int getWidth(){
		return charactor.getWidth(null);
	}
	
	/**
	 * Get the height of the charactor.
	 * @return charactors height
	 */
	public int getHeight() {
		return charactor.getHeight(null);
	}
	
	/**
	 * Check if the charactor is dead.
	 * @return true if dead. 
	 */
	public boolean isDead(){
		if(health <= 0){
			return true;
		}
		return false;
	}

	/**
	 * Returns the image of the charactor.
	 * @return charactors image.
	 */
	public Image getImage() {
		return charactor;
	}

	/**
	 * Set the charactors image.
	 * @param charactor the new image.
	 */
	public void setImage(Image charactor) {
		this.charactor = charactor;
	}
	
	/**
	 * Get the health of the charactor.
	 * @return the health of the charactor.
	 */
	public float getHealth() {
		return health;
	}

	/**
	 * Set the health of the charactor. 
	 * @param health the new health of the charactor. 
	 */
	public void setHealth(float health) {
		this.health = health;
	}

	/**
	 * Get the boundries of the charactor.
	 * @return
	 */
	public Rectangle getBounds() {
		return bounds;
	}
	
	/**
	 * Set the bounds of the charactor. 
	 * @param bounds the new bounds of the charactor.
	 */
	public void setBounds(Rectangle bounds) {
		this.bounds = bounds;
	}

	/**
	 * Get the Dx value (Speed)
	 * @return the Dx value.
	 */
	public int getDx() {
		return dx;
	}

	/**
	 * Set the Dx value (X speed)
	 * @param dx the new Dx value.
	 */
	public void setDx(int dx) {
		this.dx = dx;
	}

	/**
	 * Get the Dy value. (Y speed)
	 * @return the dy value.
	 */
	public int getDy() {
		return dy;
	}

	/**
	 * Set the Dy value. (Y speed).
	 * @param dy the new dy value.
	 */
	public void setDy(int dy) {
		this.dy = dy;
	}
	
	/**
	 * Fire a bullet from the weapon. 
	 */
	public void fire(){
		int y = 0;
		
		if(!crouching){
			y = this.y + 48;
		}else{
			y = this.y + 70;
		}
		
		if(direction == Direction.RIGHT){
			bullets.add(new Bullet(x+5,y,2));	
		}else if(direction == Direction.LEFT){
			bullets.add(new Bullet(x+5,y,-2));
		}		
	}
	
	/**
	 * Detect a colision with an enemy.
	 * @param enemy the enemy to check against. 
	 */
	public void colisionDetection(Enemy enemy){
		if(bounds.intersects(enemy.getBounds())){
			health -= 0.01;
			if((int)health <= 0){
				health = 0;
			}
		}
		for(Bullet bullet: bullets){
			bullet.colisionDetection(enemy);
		}
	}

	/**
	 * Detect colisions with obsticals. 
	 * @param obstical the obstical to check against. 
	 */
	public void colisionDetection(Obstical obstical){
		if(obstical.getBounds().intersects(bounds)){
			dx = 0;
			if(direction == Direction.RIGHT){
				x--;
			}else if(direction == Direction.LEFT){
				x++;
			}
		}
	}
	
	/**
	 * Update the charactor. 
	 * Deals with moving the charactor, checking for colisions, etc.
	 * @return true if moving. false if the background needs to move. 
	 */
	public boolean update(){
		if(dy==-1){
			jumping = true;
		}else if(dy == 1){
			crouching = true;
		}
		/*
		 * If up key is pressed, were marked as being in the state of jumping,
		 * If we aren't at the top and we haven't completed the jump y is decreased (we go up)
		 * If we reach the top we are in the state of having jumped,
		 * And then y is increased (we go down)
		 * If we are at the bottom and we have jumped, then we have finished jumping
		 * Jumping and Jumped are set to false and the loop doesn't run again until the up button is pressed.
		 */
		if(jumping){
			jumping = true;
			if(y > (oy-jumpingHeight) && !jumped){
				y--;
				if(y <= (oy-jumpingHeight)){jumped=true;}
			}else if(y >= (oy-jumpingHeight) && y < oy && jumped){
				y++;
			}else if(y == oy && jumped){
				jumped = false;
				jumping = false;
			}
		}else{
			y = oy;
		}
		/*
		 * If the down key is pressed we are crouching, If we arent jumping then we load the image for someone crouching, 
		 * we then set that to the image for the charactor,
		 * if we are no longer crouching then everything is reverted back to normal
		 */
		if(crouching && !jumping){
			if(direction == Direction.LEFT){
				charactor = crouchedLeftIcon.getImage();
			}else{
				charactor = crouchedIcon.getImage();
			}
			bounds = new Rectangle(x,y,charactor.getWidth(null),charactor.getHeight(null));
			if(dy != 1){
				crouching = false;
				if(direction == Direction.LEFT){
					charactor = charactorLeftIcon.getImage();
				}else{
					charactor = charactorIcon.getImage();
				}
			}
		}
		/*Lets remove any bullets that have expired, We dont want any crap in out arraylist to slow this game down!
		 * So set a int to -1, cause an array will never have an index -1.
		 * For each bullet, update it (moves it), check its not expired
		 * if its expired then set the int to its index
		 * then outside the loop check if the int has been changed if it has remove that index and reset to -1!
		 */
		int toBeRemoved = -1;
		for(Bullet bullet : bullets){
			bullet.update();
			if(bullet.expired){
				toBeRemoved = bullets.indexOf(bullet);
			}
		}
		if(toBeRemoved != -1){
			bullets.remove(toBeRemoved);
			toBeRemoved = -1;
		}
		/*
		 * If we are with in the bounds of the screen we can move, then move and stop the background moving by returning true
		 */
		if((x+dx) >= 40 && (x+dx) <= 500){
			x += dx;
			bounds = new Rectangle(x,y,charactor.getWidth(null),charactor.getHeight(null));
			return true;
		}
		/*
		 * If we are at the left of the screen stop us from going back on our selves by returning true
		 */
		if(x <= 40){
			bounds = new Rectangle(x,y,charactor.getWidth(null),charactor.getHeight(null));
			return true;
		}
		//Otherwise just return false and move the background!
		bounds = new Rectangle(x,y,charactor.getWidth(null),charactor.getHeight(null));
		return false;		
	}
	
	/**
	 * Monitor key releases . 
	 * Sets speed of travel to 0.
	 * @param e Key Event.
	 */
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT){
			setDx(0);
		}else if(key == KeyEvent.VK_RIGHT){
			setDx(0);
		}else if(key == KeyEvent.VK_UP){
			setDy(0);
		}else if(key == KeyEvent.VK_DOWN){
			setDy(0);
		}else if(key == KeyEvent.VK_SPACE){
		}
	}

	/**
	 * Monitors Key Presses.
	 * Sets direction of travel and speed.
	 * @param e Key event
	 */
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if(key == KeyEvent.VK_LEFT){
			setDx(-1);
			direction = Direction.LEFT;
			charactor = charactorLeftIcon.getImage();
			bounds = new Rectangle(x,y,charactor.getWidth(null),charactor.getHeight(null));
		}else if(key == KeyEvent.VK_RIGHT){
			setDx(1);
			direction = Direction.RIGHT;
			charactor = charactorIcon.getImage();
			bounds = new Rectangle(x,y,charactor.getWidth(null),charactor.getHeight(null));
		}else if(key == KeyEvent.VK_UP){
			setDy(-1);
		}else if(key == KeyEvent.VK_DOWN){
			setDy(1);
		}else if(key == KeyEvent.VK_SPACE){
			fire();
		}
	}
	
	/**
	 * Draw dat shit nigger. 
	 * @param g Graphics to draw G.
	 */
	public synchronized void draw(Graphics2D g){
		g.drawImage(charactor,x,y,null);
		for(Bullet bullet:bullets){
			bullet.draw(g);
		}
		g.drawString("Health :"+(int)health, 10, 20);
	}

}
