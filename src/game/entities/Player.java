package game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import game.Game;

public class Player extends Entity {

	private Game game;
	private float x, y;
	private float my;
	
	public boolean jumping = false;
	
	public Player(Game game, int x, int y) {
		this.x = x; 
		this.y = y;
	}
	
	@Override
	public void update(long dt) {
		this.y += my * (dt / 10.0);
		my += 0.5 * (dt / 10.0);
		
		if(this.y >= 480-Game.GROUND_HEIGHT-20 + 5) {
			this.y = 480-Game.GROUND_HEIGHT-20 + 5;
			jumping = false;
		}
	}
	
	public void jump() {
		if(!jumping) {
			my = -10.0f;
			jumping = true;
		}
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect((int)x, (int)y, 10, 20);
	}
	
	public Rectangle getHitbox() {
		return new Rectangle((int)x, (int)y, 10, 20);
	}

}
