package newBalls;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

public class PBall extends Thread implements PShapes
{
	int x, y, r, dx, dy, maxX, minX, maxY, minY;
	Color color;
	PFrame pf;
	PPanel pp;
	
	private Random rand = new Random();
	private float rr = rand.nextFloat();
	private float g = rand.nextFloat();
	private float b = rand.nextFloat();
	Color clr = new Color(rr, g, b);

	@Override
	public void draw(Graphics2D gg) 
	{
		gg.setColor( clr );
		gg.fillOval(x, y, r+r, r+r);
	}

	@Override
	public void init( int x, int y, Color clr ) 
	{

		this.x = x - r;
		this.y = y - r; 
		this.color = clr;
		while ( dx == 0 || dy == 0 )
		{
			dx = 1 + ( int ) ( Math.random() * 10 );
			dy = -1 + ( int ) ( Math.random() * 10 );
		}
		this.start();
	}
	
	@Override
	public void run() 
	{
		super.run();
		while(true)
		{
			this.move();
			try 
			{
				sleep(10);	
			}
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}	
		}
	}

	@Override
	public void move() 
	{
		if(x <= 0)
		{
			dx = 1;// + ( int ) ( Math.random() * 5 );
		}

		if(y <= 0) 
		{
			dy = 1;// + ( int ) ( Math.random() * 5 );
		}

		if(x >= 1264- ( 2*r ) ) 
		{
			dx = -1;// (1;// + ( int ) ( Math.random() * 5 ));
		}

		if(y >= 561- ( 2*r ) )
		{
			dy = - 1;//(1 + ( int ) ( Math.random() * 5 ));
		}
		x += dx;
		y += dy;
		minX = x;
		minY = y;
		maxX = x + r*2;
		maxY = y + r*2;
	}

	@Override
	public void move(PFrame pf) {
		// TODO Auto-generated method stub
		
	}
}
