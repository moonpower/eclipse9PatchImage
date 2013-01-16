package imagebuttonpatch.model;

public class HorizontalTextLine {
	int startX = 0;
	int endX = 0;
	int width = 0;

	public HorizontalTextLine() {

	}

	public HorizontalTextLine(int startX, int endX, int width) {
		super();
		this.startX = startX;
		this.endX = endX;
		this.width = width;
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getEndX() {
		return endX;
	}

	public void setEndX(int endX) {
		this.endX = endX;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}
