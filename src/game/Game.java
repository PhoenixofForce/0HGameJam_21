package game;

import frame.InputHandler;
import game.entities.Ground;
import game.entities.Player;
import sun.net.www.content.text.plain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.w3c.dom.css.Rect;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Game {

	public static final int GROUND_HEIGHT = 220;
	public static final int GROUND_WIDTH = 80;
		
	private InputHandler ih;
	private Player p;
	
	private List<Ground> groundTiles;
	
	private float points = 0;	

	private int lastPoints = 0;
	private int lastObs = 0;
	
	public Game(Window w) {
		p = new Player(this, 90 , 480-GROUND_HEIGHT-20 + 5);
		ih = new InputHandler(this);

		w.addKeyListener(ih);
		
		this.groundTiles = new ArrayList<>();
		for(int i = -1; i <= 640.0 / GROUND_WIDTH + 1; i++) {
			groundTiles.add(new Ground(this, i * GROUND_WIDTH, new Random().nextInt(10)-5));
		}
	}

	public void update(long dt) {
		points += dt / 100.0;
		
		lastPoints = Math.max(lastPoints -1, 0);
		
		if(ih.isKeyPressed(KeyEvent.VK_SPACE)) {
			p.jump();
		}
		
		if(ih.isKeyPressed(KeyEvent.VK_CONTROL)) {
		}
		
		for(int i = 0; i < groundTiles.size(); i++) {
			groundTiles.get(i).update(dt);
			
			if(groundTiles.get(i).isObstacle) {
				if(p.getHitbox().intersects(groundTiles.get(i).getHitbox())) {
					if(!p.jumping) {
						System.exit(0);
					}
				}
				
				else if(p.getHitbox().intersects(groundTiles.get(i).getEXTREMHitbox())) { 
					if(lastPoints == 0) {
						points += 250;
						lastPoints = 60;
					}
					
				}
			}
		}
		
		p.update(dt);
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawString(((int) points) + "", 20, 20);
		
		for(int i = 0; i < groundTiles.size(); i++) groundTiles.get(i).draw(g);
		
		p.draw(g);

	}
	
	public void spawnGround(Ground b) {
		lastObs = Math.max(lastObs -1, 0);
		
		groundTiles.add(b);
		if(Math.random() < 0.2) {
			if(lastObs == 0) {
				b.setObstacle();
				lastObs = 2;
			}
		}
	}

	public void removeGround(Ground e) {
		groundTiles.remove(e);
	}
}