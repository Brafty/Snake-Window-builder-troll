package Dapackage;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.lang.Thread;

public class Jproject extends JFrame implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	private static JPanel contentPane;

	
	static JLabel[][] LabelList;
	static Grid Level;
	final int ScreenWidth=800;
	final int ScreenHeight=800;
	final int GridWidth=16;
	final int GridHeight=16;
	static long GameSpeed=100;
	static Player MainPlayer;
	static Vector2 TailMove;
	static MoveDirection currentDirection=MoveDirection.LEFT;
	static Color SnakeColor=new Color(0,170,0);
	static Color GrassColor=new Color(120,200,90);

	public static void main(String[] args) {

					Jproject frame = new Jproject();
					frame.setVisible(true);
					GameLoop();
	}


	
	
	public Jproject() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, ScreenWidth+16, ScreenHeight+39);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLayout(null);
		this.addKeyListener(this);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		Level=new Grid(ScreenWidth,ScreenHeight,GridWidth,GridHeight);
		LabelList=new JLabel[GridWidth][GridHeight];
		Vector2 Pos;
		MainPlayer=new Player(new Vector2(GridWidth/2,GridHeight/2),Level.CelList,GridWidth,GridHeight);
		Level.SpawnApple();
		for(int x=0;x<Level.GetGridWidth();x++) {
			for(int y=0;y<Level.GetGridHeight();y++) {
				Pos=Level.GetPosition(x, y);
				LabelList[x][y] = new JLabel("");
				LabelList[x][y].setBounds(Pos.x, Pos.y, Level.GetCellWidth(), Level.GetCellHeight());
				LabelList[x][y].setOpaque(true);
				if(Level.CelList[x][y].Solid==true) { LabelList[x][y].setBackground(Color.gray);
				
				}else {LabelList[x][y].setBackground(GrassColor);}
				
				if(Level.CelList[x][y].HasApple) { LabelList[x][y].setBackground(Color.red); }
				//System.out.println("x: "+Pos.x+"  y: "+Pos.y+"  CellWidth: "+Level.GetCellWidth()+"  CellHeight: "+Level.GetCellHeight());
				contentPane.add(LabelList[x][y]);			
				
			}
		}
		LabelList[MainPlayer.FrontPos.x][MainPlayer.FrontPos.x].setBackground(SnakeColor);
		LabelList[MainPlayer.BackPos.x][MainPlayer.BackPos.y].setBackground(SnakeColor);
		TailMove=MainPlayer.FrontPos;
	}
	
	static void GameLoop() {

		try {
		    Thread.sleep(GameSpeed);
		} catch(InterruptedException e) {
		    System.out.println("F");
		}

		System.out.println("RAN");
		MainPlayer.MoveFront(currentDirection);
		if(Level.CelList[MainPlayer.FrontPos.x][MainPlayer.FrontPos.y].Solid==true) {System.out.print("haha");	return;}
		Level.CelList[MainPlayer.FrontPos.x][MainPlayer.FrontPos.y].Solid=true;
		LabelList[MainPlayer.FrontPos.x][MainPlayer.FrontPos.y].setBackground(SnakeColor);
		
		
		if(Level.CelList[MainPlayer.FrontPos.x][MainPlayer.FrontPos.y].HasApple==false) {
		LabelList[MainPlayer.BackPos.x][MainPlayer.BackPos.y].setBackground(GrassColor);	
		Level.CelList[MainPlayer.BackPos.x][MainPlayer.BackPos.y].Solid=false;
		System.out.println("delete");
		MainPlayer.MoveTail();
		}else {
			MainPlayer.PlayerLength+=1;
			Level.CelList[MainPlayer.FrontPos.x][MainPlayer.FrontPos.y].HasApple=false;
			Level.SpawnApple();
			LabelList[Level.ApplePos.x][Level.ApplePos.y].setBackground(Color.red);
			MainPlayer.MoveTail();
		}
		//System.out.println("xFront:"+MainPlayer.FrontPos.x+"    yFront: "+MainPlayer.FrontPos.y);
		//System.out.println("xBack:"+MainPlayer.BackPos.x+"    yBack: "+MainPlayer.BackPos.y);
		GameLoop();
	} 



	@Override
	public void keyTyped(KeyEvent e) {
		char LastInput= e.getKeyChar();
		if(LastInput=='A'||LastInput=='a') {
			if(MainPlayer.LastDirection!=MoveDirection.RIGHT) 	currentDirection=MoveDirection.LEFT;
		}else if(LastInput=='D'||LastInput=='d') {
			if(MainPlayer.LastDirection!=MoveDirection.LEFT)	currentDirection=MoveDirection.RIGHT;
		}else if(LastInput=='S'||LastInput=='s') {
			if(MainPlayer.LastDirection!=MoveDirection.UP)		currentDirection=MoveDirection.DOWN;
		}else if(LastInput=='W'||LastInput=='w') {
			if(MainPlayer.LastDirection!=MoveDirection.DOWN)	currentDirection=MoveDirection.UP;
		}	
	}
	
	public void keyPressed(KeyEvent e) {	}
	
	public void keyReleased(KeyEvent e) {	}
}
