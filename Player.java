package Dapackage;

public class Player {
	Vector2 FrontPos;
	Vector2 BackPos;
	int PlayerLength;
	Vector2[] PlayerBody;
	MoveDirection LastDirection=MoveDirection.LEFT;
	public Player(Vector2 pos,GridCell[][] MainGrid, int GridHeight, int GridWidth) {
		PlayerLength=1;
		FrontPos=pos;
		BackPos=new Vector2(pos.x+1,pos.y);
		MainGrid[FrontPos.x][FrontPos.y].Solid=true;
		MainGrid[BackPos.x][BackPos.y].Solid=true;
		PlayerBody =new Vector2[(GridWidth-1)*(GridHeight-1)];
		PlayerBody[0]=new Vector2(FrontPos.x,FrontPos.y);
		PlayerBody[1]=new Vector2(BackPos.x,BackPos.y);		
	}
	public void MoveFront(MoveDirection PlayerDirection) {
		if(PlayerDirection==MoveDirection.LEFT) {
			FrontPos.x-=1;

		}else if(PlayerDirection==MoveDirection.RIGHT) {
			FrontPos.x+=1;
		}else if(PlayerDirection==MoveDirection.UP) {
			FrontPos.y-=1;			
		}else {
			FrontPos.y+=1;
		}
		LastDirection=PlayerDirection;
	}
	public void MoveTail() {

		for(int i=PlayerLength;i>0;i--) {
			PlayerBody[i]=new Vector2(PlayerBody[i-1].x,PlayerBody[i-1].y);
		}
		BackPos=PlayerBody[PlayerLength];
		PlayerBody[0]=new Vector2(FrontPos.x,FrontPos.y);
		
	}
}
