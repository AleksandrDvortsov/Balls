package newBalls;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PPanel extends JPanel implements MouseListener, AdaptiveComponent
{
	ArrayList<PBall> list = null;
	PFrame pf;
	int count, count2;
	PBall o = null;
	PBall dellBall = null;
//	PBall xz777 = null;
//	PBall qwerty = null;
	boolean V = true;
//	Timer xz;
//	Timer addBallTwo;
	
//	PBall xz2 = null;

	public PPanel( PFrame pf )
	{
		this.pf = pf;
		setLayout(null);
		list = new ArrayList<PBall>();
		setBackground(Color.BLACK);
		addMouseListener(this);
		setVisible(true);
		count =1;

		Timer timer = new Timer( count, new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				repaint();
			}
		});
		timer.start();
//		Timer delDall = new Timer(8000, new ActionListener() {
//			
//			@Override
//			public void actionPerformed(ActionEvent arg0) 
//			{
//				for ( PBall xz : list )
//				list.remove(xz);
//			}
//		});
//		delDall.start();
	}

	@Override
	public void changeBounds(int Width, int Height) 
	{
		float a,b,c,d;
		a = 0;
		b = 0;
		c = (float) Width/1264f *1264;
		d = ( float) Height/561*561;
		setBounds((int)a, (int)b, (int)c, (int)d);
	}

	@Override
	public void mouseClicked(MouseEvent e) 
	{

		if ( e.getButton() == MouseEvent.BUTTON1 )
		{
			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
			int x = e.getX();
			int y = e.getY();
			
			PBall ps = new PBall();
//			ps.r = ( int ) ( 25 + Math.random() * 100 );
			ps.r = 50;
			ps.init(x, y, Color.YELLOW);
				list.add(ps);	
			count2 = 5000;
//			Timer xz;
//			Timer addBallTwo;
//			if ( V == true )
//			{
//				xz = new Timer(1000,  new ActionListener() 
//				{
//					@Override
//					public void actionPerformed(ActionEvent e) 
//					{
//						addBallTwo = new Timer(2000, new ActionListener() 
//						{
//							@Override
//							public void actionPerformed(ActionEvent arg0) 
//							{
//								PBall qwe = null;
//								for ( PBall pballs : list )
//								{
//									xz777 = pballs;
//									for ( int i = 0; i < 2; i++ )
//									{
//										qwe = new PBall();
//										qwe.r = xz777.r - 10;
//										
//										qwe.init(xz777.x, xz777.y, Color.BLACK);
//										if ( qwe.r < 30 )
//										{
//											V = false;
//											dellBall = qwe;
//											break;
//										}
//										else
//										list.add(qwe);
//										
//									}
//								if ( qwe.r < 30 )
//								{
//									V = false;
//									dellBall = qwe;
//									break;
//								}
//								else
//								list.add(qwe);
//		
//								}
//							}
//						}); 
//						addBallTwo.start();
//					}	
//				});
//				xz.start();
//				System.out.println( "777777777777777777777777" + "xz start ");
//		}

			
			////////////////////////////////////////////////////////////////////////////////////////////////////////////////
			
		}
		
		if ( e.getButton() == MouseEvent.BUTTON2 )
		{
			PBall dell = null;
			for ( PBall pballs : list )
			{
				if ( e.getX() <= pballs.maxX 
						&& e.getX() >= pballs.minX 
						&& e.getY() >= pballs.minY 
						&& e.getY() <= pballs.maxY )
				{
					dell = pballs;
				}
			}
			list.remove(dell);
		}
		if ( e.getButton() == MouseEvent.BUTTON3 )
		{
			PBall qwe = null;
			int x = e.getX();
			int y = e.getY();
			PBall xz = null;
			PBall xz2 = null;
			
			for ( PBall pballs : list )
			{
				if ( e.getX() <= pballs.maxX && e.getX() >= pballs.minX 	&& e.getY() >= pballs.minY && e.getY() <= pballs.maxY )
				{
					xz = pballs;
				}
			}
			if ( xz == null )
				return;
				for ( int i = 0; i < 8; i++ )
				{
					qwe = new PBall();
					qwe.r = xz.r - 10;
					qwe.init(x, y, Color.BLACK);
					list.add(qwe);
					if ( qwe.r < 10 )
					{
						xz2 = qwe;
						list.remove(xz2);
					}
				}
			list.remove(xz);
//			list.remove(xz2);//////////////////////
		}
		
	}

	public void paint ( Graphics g )
	{
		super.paint(g);
		Graphics2D gg = ( Graphics2D ) g;
		for ( PBall shapes : list ) 
		{
			gg.setRenderingHint ( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
			shapes.draw(gg);

//			shapes.move();
//			if ( list.size() > 50 )
			
//				eatBall(shapes);
			repulsion(shapes);
		}
		list.remove(dellBall); // dellBall eat bal
//		list.remove(o); //// eat dell small

//		list.remove(xz777);	
	}
	
	void eatBall ( PBall xz )
	{
//		PBall dellBall = null;
		for ( PBall ball : list )
		{
			if ( xz.minX > ball.minX && xz.maxX <= ball.maxX  && xz.maxY >= ball.minY && xz.maxY < ball.maxY)
			{
				ball.r += xz.r/2;
				o = xz;
				if ( ball.r >= 450 )
					dellBall = ball;
			}
		}
	}
	
	void repulsion ( PBall repulsionBall )
	{
		for ( PBall ball : list )
		{
			if ( repulsionBall == ball )
			{
				continue;	
			}
			if ( intersection(repulsionBall, ball) == true )
			{
				if(repulsionBall.minX >= ball.minX  && repulsionBall.minX <= ball.maxX && repulsionBall.maxY >= ball.minY && repulsionBall.maxY <= ball.maxY )
				{
					repulsionBall.dy = - (1 + ( int ) ( Math.random() * 5 ));
					repulsionBall.dx = 1 + ( int ) ( Math.random() * 5 );
					ball.dy = 1 + ( int ) ( Math.random() * 5 );
					ball.dx = - (1 + ( int ) ( Math.random() * 5 ));
				}
				
				if(repulsionBall.minX >= ball.minX && repulsionBall.minX <= ball.maxX && repulsionBall.minY >= ball.minY && repulsionBall.minY <= ball.maxY )
				{
					repulsionBall.dy = 1 + ( int ) ( Math.random() * 5 );
					repulsionBall.dx = 1 + ( int ) ( Math.random() * 5 );
					ball.dy = - (1 + ( int ) ( Math.random() * 5 ));
					ball.dx = - (1 + ( int ) ( Math.random() * 5 ));
				}

				if(repulsionBall.maxX >= ball.minX && repulsionBall.maxX <= ball.maxX && repulsionBall.maxY >= ball.minY && repulsionBall.minY <= ball.maxY )
				{
					repulsionBall.dy = 1 + ( int ) ( Math.random() * 5 );
					repulsionBall.dx = - (1 + ( int ) ( Math.random() * 5 ));
					ball.dy = - (1 + ( int ) ( Math.random() * 5 ));
					ball.dx = 1 + ( int ) ( Math.random() * 5 );
				}
				
				if(repulsionBall.maxX >= ball.minX && repulsionBall.maxX <= ball.maxX && repulsionBall.minY <= ball.minY && repulsionBall.maxY >= ball.minY)
				{
					repulsionBall.dy = - (1 + ( int ) ( Math.random() * 5 ));
					repulsionBall.dx = - (1 + ( int ) ( Math.random() * 5 ));
					ball.dy = 1 + ( int ) ( Math.random() * 5 );
					ball.dx = 1 + ( int ) ( Math.random() * 5 );
				}

				//repulsionBall ������
				if(repulsionBall.minX > ball.minX  && repulsionBall.minX <= ball.maxX && repulsionBall.minY == ball.minY && repulsionBall.maxX == ball.maxX && ball != repulsionBall)
				{
					repulsionBall.dx = 1 + ( int ) ( Math.random() * 5 );
					repulsionBall.dy = 0;
					ball.dx = - (1 + ( int ) ( Math.random() * 5 ));
					ball.dy = 0;
				}
				
				//repulsionBall �����
				if(repulsionBall.minX < ball.minX  && repulsionBall.maxX >= ball.minX && repulsionBall.minY == ball.minY && repulsionBall.maxX == ball.maxX && ball != repulsionBall)
				{
					repulsionBall.dx = - (1 + ( int ) ( Math.random() * 5 ));
					repulsionBall.dy = 0;
					ball.dx = 1 + ( int ) ( Math.random() * 5 );
					ball.dy = 0;
				}
				
				//repulsionBall ������
				if(repulsionBall.minX == ball.minX  && repulsionBall.maxX == ball.maxX && repulsionBall.minY > ball.minY && repulsionBall.minY <= ball.maxY && ball != repulsionBall)
				{
					repulsionBall.dy = 1 + ( int ) ( Math.random() * 5 );
					repulsionBall.dx = 0;
					ball.dy = - (1 + ( int ) ( Math.random() * 5 ));
					ball.dx = 0;
				}
				
				//repulsionBall �����
				if(repulsionBall.minX == ball.minX  && repulsionBall.maxX == ball.maxX && repulsionBall.maxY < ball.maxY && repulsionBall.maxY >= ball.minY && ball != repulsionBall)
				{
					repulsionBall.dy = - (1 + ( int ) ( Math.random() * 5 ));
					repulsionBall.dx = 0;
					ball.dy = 1 + ( int ) ( Math.random() * 5 );
					ball.dx = 0;
				}
			}
		}
	}
	
	private boolean intersection(PBall a, PBall b)
	{
			boolean res = false;

//		if( Math.sqrt(Math.pow((b.x - a.x),2) + Math.pow((b.y - a.y),2)) <= (a.r + b.r) )
			if( Math.sqrt(Math.pow(((b.x + b.r) - (a.x + a.r)),2) + Math.pow(((b.y+ b.r) - (a.y+ a.r)),2)) <= (a.r + b.r) )
			res = true;
		
		if(a == b)
			res = false;
		
		return res;
	}

	
	@Override
	public void mouseEntered(MouseEvent e) 
	{
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{

	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{	
	}
}
