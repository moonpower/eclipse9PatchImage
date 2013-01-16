package imagebuttonpatch.model;

public class HTransparentScaleLine implements IHorizontalLine {

	int startX;
	int endX;
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
