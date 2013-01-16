package imagebuttonpatch.model;

public class VTransparentScaleLine implements IVerticalLine {

	private int StartY;
	private int EndY;
	private int height;
	private int lineInfo;

	@Override
	public int getStartY() {
		return StartY;
	}

	@Override
	public void setStartY(int startY) {
		StartY = startY;
	}

	@Override
	public int getEndY() {
		return EndY;
	}

	@Override
	public void setEndY(int endY) {
		EndY = endY;
	}

	@Override
	public int getLineInfo() {
		return lineInfo;
	}

	public void setLineInfo(int lineInfo) {
		this.lineInfo = lineInfo;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

}
