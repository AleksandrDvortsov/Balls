package newBalls;

import java.awt.Color;
import java.awt.Graphics2D;

public interface PShapes 
{
	void draw ( Graphics2D gg );
	void init ( int x, int y, Color clr );
	void move ( PFrame pf );
	void move();
}
