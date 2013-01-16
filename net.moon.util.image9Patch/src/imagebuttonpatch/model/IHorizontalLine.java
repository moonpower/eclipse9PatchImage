package imagebuttonpatch.model;

public interface IHorizontalLine {
	public static final int BLACK_LINE = 0;
	public static final int TRANSPARENT_LINE = -1;

	public int getStartX();

	public void setStartX(int startX);

	public int getEndX();

	public void setEndX(int endX);

	public int getWidth();

	public void setWidth(int width);

	public int getLineInfo();

}
