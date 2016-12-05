import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Field extends JPanel {

	private static final int WIDTH = 790;
	private static final int HEIGHT = 900;
	private Game game;

	private boolean isActive;

	private Image background;

	public Field(Game g) {
		game = g;
		setOpaque(true);

		isActive = false;
		try {
			background = ImageIO.read(new File("backgroundbaseball.jpg"));
		} catch (IOException e) {
		}
	}

	public void setGame(Game g) {
		game = g;

	}

	public boolean setState() {

		repaint();

		return isActive;
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(WIDTH, HEIGHT);
	}

	@Override
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);

		g.drawImage(background, 0, 0, this);
		g.setFont(new Font("Kai", Font.BOLD, 30));

		g.setColor(Color.RED);
		g.setFont(new Font("Kai", Font.BOLD, 60));
		if (game.getBases()[0] != null)
			g.fillRect(613, 397, 50, 50);
		if (game.getBases()[1] != null)
			g.fillRect(370, 150, 50, 50);
		if (game.getBases()[2] != null)
			g.fillRect(125, 397, 50, 50);

		if (game.isGameOver()) {
			g.setColor(Color.GRAY);
			g.setFont(new Font("Kai", Font.PLAIN, 50));
			if (game.getHomeScore() > game.getAwayScore())
				g.drawString("Winner: Home Team", 160, 400);
			else if (game.getHomeScore() == game.getAwayScore())
				g.drawString("Tie", 350, 400);
			else
				g.drawString("Winner: Away Team", 160, 400);
		}
		drawScoreboard(g);

	}

	private void drawScoreboard(Graphics g) {
		g.setColor(Color.darkGray);
		g.fillRoundRect(10, 650, 220, 130, 30, 30);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Kai", Font.PLAIN, 30));
		g.drawString("Home: " + game.getHomeScore(), 25, 685);
		g.drawString("Away: " + game.getAwayScore(), 25, 725);
		g.drawString("Outs: " + game.getOuts(), 25, 765);

		if (game.getHalf() == true)
			g.drawString(game.getCurrentInning() + "▲", 170, 765);
		else
			g.drawString(game.getCurrentInning() + "▼", 170, 765);
	}

}