import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.Timer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class BallFrame extends JFrame implements ActionListener, MouseMotionListener{

	int intScore = 0;

	//class variables
	JLabel background=new JLabel(new ImageIcon("space.jpg"));
	JLabel ball=new JLabel(new ImageIcon("asteroid.gif"));
	JLabel saucer=new JLabel(new ImageIcon("saucer.gif"));
	//JLabel score=new JLabel(new Label(intScore));

	//game timer
	Timer gameTimer = new Timer(100, this);

	int dx = 6; 
	int dy = 10;

	//constructor method
	public BallFrame(){

		background.setLayout(null);

		ball.setBounds(100,100,50,50);
		background.add(ball);
		saucer.setBounds(450,250,65,65);
		background.add(saucer);
		//background.add(score);

		add(background);

		//setup the frame
		setSize(500,500);
		setTitle("Balls");
		setVisible(true);

		addMouseMotionListener(this);

		gameTimer.start();

	}

	public void actionPerformed(ActionEvent e) {

		if (ball.getBounds().intersects(saucer.getBounds()))
			dy = -dy;
		//obunce off the bottom wall
		if (ball.getY() + ball.getHeight() > background.getHeight())
			dy = -dy;
		//move the asteroid to new coordinates
		ball.setBounds(ball.getX()+dx, ball.getY()+dy, 50, 50);
		//bounce off top wall
		if (ball.getY() < 0)
			dy = -dy;
		//move the asteroid to new coordinates
		ball.setBounds(ball.getX()+dx, ball.getY()+dy, 50, 50);
		//bounce off the right wall
		if (ball.getX() + ball.getWidth() > background.getWidth())
			dx = -dx;
		//move the asteroid to new coordinates
		ball.setBounds(ball.getX()+dx, ball.getY()+dy, 50, 50);
		if (ball.getX() < 0)
			dx = -dx;
		//move the asteroid to new coordinates
		ball.setBounds(ball.getX()+dx, ball.getY()+dy, 50, 50);

	}

	public void mouseDragged(MouseEvent e){

	}

	public void mouseMoved(MouseEvent mouse) {
		saucer.setBounds(mouse.getX() - 35, mouse.getY() - 45, 65, 65);
	}


}

