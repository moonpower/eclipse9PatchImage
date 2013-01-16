package imagebuttonpatch.model;

public interface IVerticalLine {
	public static final int BLACK_LINE = 0;
	public static final int TRANSPARENT_LINE = -1;

	public int getStartY();

	public void setStartY(int startY);

	public int getEndY();

	public void setEndY(int endY);

	public int getLineInfo();

	public void setHeight(int height);

	public int getHeight();

}
