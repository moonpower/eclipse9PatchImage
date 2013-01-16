package imagebuttonpatch.model;

public class VerticalScaleLine implements IVerticalLine {
	int startY = -1;
	int endY = -1;
	int height;
	int lineInfo;

	@Override
	public int getStartY() {
		return startY;
	}

	@Override
	public void setStartY(int startY) {
		this.startY = startY;
	}

	@Override
	public int getEndY() {
		return endY;
	}

	@Override
	public void setEndY(int endY) {
		this.endY = endY;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public void setHeight(int height) {
		this.height = height;
	}

	@Override
	public int getLineInfo() {
		return lineInfo;
	}

	public void setLineInfo(int lineInfo) {
		this.lineInfo = lineInfo;
	}

}
