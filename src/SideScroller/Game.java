package SideScroller;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Game. Handles game logic. Renders to the screen.
 * @author tomas
 *
 */
public class Game extends JPanel{

	private static final long serialVersionUID = 1L;
	public int w,h;
	public Background bg, bg2;
	public Charactor jim;
	public ArrayList<Enemy> enemys = new ArrayList<Enemy>();
	public Obstical obs1;
	
	/**
	 * Game Constructor. Sets up the size and layouts of the JPanel. 
	 * Then Initialises and Repaints.
	 * @param w Width of JPanel.
	 * @param h Height of JPanel. 
	 */
	public Game(int w, int h) {
		this.w = w; this.h = h; 
		setLayout(null);
	  	setSize(w,h);
	  	setFocusable(true);
	  	requestFocusInWindow();
	  	setPreferredSize(new Dimension(w,h));
	  	setBounds(0, 0, w, h);
  		init();
  		repaint();
  		
	}
	
	/**
	 * Initialise Game. Sets backgrounds, adds your player, adds enemys, all that jazz.
	 * Two backgrounds so that they are always repeating, one is always after the one currently on the screen. 
	 */
	public void init(){
		bg = new Background(); 
		bg2 = new Background(3000);
		jim = new Charactor(); 
		for(int i = 0; i < 20; i++){ //Add 20 Enemies. 
			addEnemy();
		}
		//obs1 = new Obstical(300, 470);
		//System.out.println("OBS1" + obs1.bounds.x + ", " + obs1.bounds.x+obs1.bounds.width + ", " + obs1.bounds.y + ", " + obs1.bounds.y + obs1.bounds.height);

	}
	
	/**
	 * Add an Enemy. DUH. Adds it at a random position. 
	 */
	public void addEnemy(){
		int position = (int) Math.random()*6000+100;
		enemys.add(new Enemy(position, 400));
	}
	
	/**
	 * Update the game. Move the backgrounds once it goes off screen. 
	 * Update the Charector, if he is at the limits, move the background. 
	 * Update the Enemies, move if the charector is moving past the limits. 
	 * Remove dead enemies. Detect collisions with enemies. 
	 */
	public void update(){
		boolean moving = false;
		if(bg.getX() <= -2250)bg2.setX(3000+bg.getX());
		if(bg2.getX() <= -2250)bg.setX(3000+bg2.getX());	
		if(!jim.update()){
			bg.update();
			bg2.update();
			moving = true;
		}
		for(Enemy enemy: enemys){
			enemy.update(moving);
		}
		
		int toBeRemoved = -1;
		for(Enemy enemy: enemys){
			jim.colisionDetection(enemy);
			if(enemy.isDead()){
				toBeRemoved = enemys.indexOf(enemy);
			}
		}
		
		//jim.colisionDetection(obs1);
		/*if(obs1.onTopOf(jim.getX()+jim.getWidth(), jim.getX())){
			jim.setOy(obs1.getY()-jim.getHeight());
		}
		else{
			if(!jim.isJumping()){
				jim.setY(jim.getBaseY());
			}
			jim.setOy(jim.getBaseY());
		}*/
		
		if(toBeRemoved != -1){
			enemys.remove(toBeRemoved);
			toBeRemoved = -1;
		}
		
		repaint();
	}

	/**
	 * Paint it bitches. 
	 * Draws each object to the screen. 
	 * If dead write DEAD.
	 */
	public void paint(Graphics G) {
		Graphics2D g = (Graphics2D)G;
		super.paint(G);
		if(!jim.isDead()){
			bg.draw(g);
			bg2.draw(g);
			jim.draw(g);
			//obs1.draw(g);
			for(Enemy enemy: enemys){
				enemy.draw(g);
			}
		}else{
			g.drawString("DEAD", w/2, h/2);
		}
	}
	
	
	/**
	 * Send Key Released events to charectors and BG. 
	 * @param e KeyEvent.
	 */
	public void keyReleased(KeyEvent e) {
		jim.keyReleased(e);
		bg.keyReleased(e);
		bg2.keyReleased(e);
	}

	/**
	 * Send Key Presseed events to charectors and BG. 
	 * @param e KeyEvent.
	 */
	public void keyPressed(KeyEvent e) {
		jim.keyPressed(e);
		bg.keyPressed(e);
		bg2.keyPressed(e);
	}
}
