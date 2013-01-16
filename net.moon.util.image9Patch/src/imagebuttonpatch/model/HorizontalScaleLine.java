package imagebuttonpatch.model;

public class HorizontalScaleLine implements IHorizontalLine {

	int startX = -1;
	int endX = -1;
	int width;
	int lineInfo;

	@Override
	public int getStartX() {
		return startX;
	}

	@Override
	public void setStartX(int startX) {
		this.startX = startX;
	}

	@Override
	public int getEndX() {
		return endX;
	}

	@Override
	public void setEndX(int endX) {
		this.endX = endX;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public int getLineInfo() {
		return lineInfo;
	}

	public void setLineInfo(int lineInfo) {
		this.lineInfo = lineInfo;
	}

}
