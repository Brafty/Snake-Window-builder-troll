package Dapackage;

public class GridCell {
	public boolean HasApple;
	public boolean Solid;
	int x;
	int y;

	public GridCell(int xPosition, int yPosition) {
		Solid=false;
		HasApple=false;
		x=xPosition;
		y=yPosition;
	}

}
