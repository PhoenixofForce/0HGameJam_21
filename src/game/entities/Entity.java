package game.entities;

import java.awt.*;

public abstract class Entity {

	public abstract void update(long dt);

	public abstract void draw(Graphics g);
}