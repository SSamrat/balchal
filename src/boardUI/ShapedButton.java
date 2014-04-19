package boardUI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;

import javax.swing.JButton;

public class ShapedButton extends JButton {
	public ShapedButton() {

		Dimension size = getPreferredSize();
		size.width = size.height = Math.max(size.width, size.height);
		setPreferredSize(size);
		setContentAreaFilled(false);

	}

	protected void paintComponent(Graphics g) {
		if (getModel().isArmed()) {
			g.setColor(Color.blue);
		} else {
			g.setColor(getBackground());
		}
		g.fillOval(0, getHeight() / 8, getWidth(), getHeight()
				- (getHeight() / 4));
		super.paintComponent(g);
	}

	protected void paintBorder(Graphics g) {
		g.setColor(getForeground());
		g.drawOval(0, getHeight() / 8, getWidth(), getHeight()
				- (getHeight() / 4));
	}

	Shape shape;

	public boolean contains(int x, int y) {
		if (shape == null || !shape.getBounds().equals(getBounds())) {
			shape = new Ellipse2D.Float(0, getHeight() / 8, getWidth(),
					getHeight() - getHeight() / 4);
		}
		return shape.contains(x, y);
	}

}
