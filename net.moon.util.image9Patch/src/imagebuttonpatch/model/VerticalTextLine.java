package imagebuttonpatch.model;

public class VerticalTextLine {

	int startY = 0;
	int endY = 0;
	int height = 1;

	public VerticalTextLine() {

	}

	public VerticalTextLine(int startY, int endY, int height) {
		super();
		this.startY = startY;
		this.endY = endY;
		this.height = height;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
