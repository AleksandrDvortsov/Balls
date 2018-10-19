package newBalls;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.HierarchyBoundsListener;
import java.awt.event.HierarchyEvent;
import java.util.ArrayList;
import javax.swing.JFrame;

public class PFrame extends JFrame
{
	private ArrayList<AdaptiveComponent> pp;

	public PFrame()
	{
		pp = new ArrayList<AdaptiveComponent>();
		setLayout(null);
		setBounds(5, 5, 1280, 600);
		//		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Game Ball");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(Color.red);

		PPanel p = new PPanel(this);
		add(p);
		subs(p);

		getContentPane().addHierarchyBoundsListener(new HierarchyBoundsListener() 
		{

			@Override
			public void ancestorResized(HierarchyEvent e) 
			{
				Component changed = e.getChanged();
				if ( changed.getClass().getSimpleName().equals("JLayeredPane"))
				{
					int Width = changed.getWidth();
					int Height = changed.getHeight();
					for ( AdaptiveComponent p : pp )
					{
						p.changeBounds(Width, Height);
					}
					System.out.println(e.getChanged());
				}
			}
			@Override
			public void ancestorMoved(HierarchyEvent e) 
			{}
		});
		setVisible(true);
	}
	private void subs ( AdaptiveComponent ss )
	{
		pp.add(ss);
	}
}

