package game.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import game.Game;

public class Ground extends Entity {
	
	private Game game;
	public int dy;
	public float x;
	
	public boolean isObstacle = false;
	
	public Ground(Game game, int x, int dy) {
		this.game = game;
		this.dy = dy;
		this.x = x;
	}
	
	
	@Override
	public void update(long dt) {
		x -= dt / 3.0f;
		
		if(x <= -Game.GROUND_WIDTH) {
			game.removeGround(this);
			game.spawnGround(new Ground(game, 640 - (Game.GROUND_WIDTH + (int) x), new Random().nextInt(10)-5));
		}
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect((int) x - 1, 480 - Game.GROUND_HEIGHT - dy, Game.GROUND_WIDTH + 2, Game.GROUND_HEIGHT + dy);
		
		if(isObstacle) {
			g.setColor(Color.RED);
			g.fillRect((int) (x - 1 + 1.5 * Game.GROUND_WIDTH / 4.0), 480 - Game.GROUND_HEIGHT - 20 - dy, (int) (Game.GROUND_WIDTH / 4.0), 20);
		}
		
	}

	public void setObstacle() {
		isObstacle = true;
	}
	
	public Rectangle getHitbox() {
		return new Rectangle((int) (x - 1 + 1.5 * Game.GROUND_WIDTH / 4.0), 480 - Game.GROUND_HEIGHT - 20 - dy, (int) (Game.GROUND_WIDTH / 4.0), 20);
	}
	
	public Rectangle getEXTREMHitbox() {
		return new Rectangle((int) (x - 1 + 1.5 * Game.GROUND_WIDTH / 4.0), -10, (int) (Game.GROUND_WIDTH / 4.0), 660);
	}
}
