package frame;

import game.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Window extends JFrame  {

	public static int height = 480;
	public static int width = 640;

	private Game game;
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

	private long lastUpdate = System.currentTimeMillis();

	public Window() {
		this.setResizable(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("POF - Runner");

		this.setVisible(true);
		Insets i = this.getInsets();
		this.setSize(width + i.left + i.right, height + i.top + i.bottom);

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

		game = new Game(this);
		new Thread(()->{
			while(true) {
				draw();
				game.update(System.currentTimeMillis() - lastUpdate);

				lastUpdate = System.currentTimeMillis();
			}
		}).start();
	}

	public void draw() {
		Graphics g = image.getGraphics();
		Graphics g2 = this.getRootPane().getGraphics();

		g.setColor(Color.WHITE);
		g.fillRect(0, 0, image.getWidth(), image.getHeight());

		game.draw(g);

		g2.drawImage(image, 0, 0, null);
	}
}