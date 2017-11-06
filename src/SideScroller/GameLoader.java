package SideScroller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * Game Loader, Starts JFrame.
 * @author tomas
 *
 */
@SuppressWarnings("serial")
public class GameLoader extends JFrame implements ActionListener{

	public boolean running = true;
	public int w,h,state;
	public Game game;
	public Timer timer;
	
	/**
	 * Main, Starts GameLoader
	 * @param args
	 */
	public static void main(String[] args) {
		new GameLoader();
	}
	
	/**
	 * Starts the timer.
	 * Inits the game.
	 */
	public GameLoader(){
		init();
		timer = new Timer(5,this);
		timer.start();
	}

	/**
	 * Init
	 * Sets up frame
	 * Adds Game.
	 */
	private void init(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //How the game should close
		setResizable(false); //Stop it from being resized 
		setFocusable(true); //Stop it from ...? :S
	  	this.addKeyListener(new KeyController());
		setFont(new Font("Arial",Font.PLAIN,40)); //set the font to use
		setSize(750,600); //set the size of the window
		setTitle("SideScroller - By Tom Milner"); //set the title
		setLocation((Toolkit.getDefaultToolkit().getScreenSize().width/2) - 300, (Toolkit.getDefaultToolkit().getScreenSize().height/2) - 300); //set where it should be placed initially 
		setVisible(true); //Make it visible
		running  = true; //Its running...
		w = getWidth(); //Set w to its width
		h = getHeight(); //set h to its height
		game = new Game(w,h-20); //Create a new game object, pass it the width and height
		add(game); //Add the games JPannel to the JFrame
	}
	
	/**
	 * Update on ticks of the timer.
	 */
	public void actionPerformed(ActionEvent e) {
		game.update();
	}
	
	/**
	 * Key Controller, Passes key presses through to the game. 
	 * @author tomas
	 *
	 */
	private class KeyController extends KeyAdapter {
        public void keyReleased(KeyEvent e) {
                game.keyReleased(e);
        }

        public void keyPressed(KeyEvent e) {
                game.keyPressed(e);
        }
	}
}
