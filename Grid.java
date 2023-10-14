package Dapackage;
import java.util.Random;

 class Grid {
	private int CellWidth;
	private int CellHeight;
	private int CellNumber;
	private int GridHeight;
	private int GridWidth;
	Vector2 ApplePos;
	Random rand=new Random();
	public GridCell[][] CelList;
	public Grid(int ScreenWidth,int ScreenHeight, int GridWidth, int GridHeight) {
		this.CellNumber=GridWidth*GridHeight;
		this.GridWidth=GridWidth;
		this.GridHeight=GridHeight;
		CellWidth=ScreenWidth/GridWidth;
		CellHeight=ScreenHeight/GridHeight;
		CelList=new GridCell[GridWidth][GridHeight];
		int index;
		for(int x=0;x<GridWidth;x++) {
			for(int y=0;y<GridHeight;y++) {
				CelList[x][y]=new GridCell(x,y);
			}
		}
		CreateLevel(CelList);
	}
	public int GetCellWidth() {
		return CellWidth;
	}
	public int GetCellHeight() {
		return CellHeight;
	}
	public int GetCellNumber() {
		return CellNumber;
	}
	public int GetGridWidth() {
		return GridWidth;
	}
	public int GetGridHeight() {
		return GridHeight;
	}
	public Vector2 GetPosition(int x, int y) {
		return new Vector2(x*CellWidth,y*CellHeight);
	}
	void CreateLevel(GridCell[][] k) {
		for(int x=0;x<GridWidth;x++) {
			for(int y=0;y<GridHeight;y++) {
				if(x==0||y==0||x==GridWidth-1||y==GridHeight-1) {
					k[x][y].Solid=true;
				}
			}
		}
	}
	public void SpawnApple() {
		int xApple;
		int yApple;
		do {
			xApple=rand.nextInt(GridWidth-2)+1;
			yApple=rand.nextInt(GridHeight-2)+1;
		}while(CelList[xApple][yApple].Solid);		


		CelList[xApple][yApple].HasApple=true;
		ApplePos=new Vector2(xApple,yApple);
	}
}
