package imagebuttonpatch.model;

import org.eclipse.swt.graphics.ImageData;

public class SlicedImageData {
	public static final int EDIT_IMPOSSIBLE = 0;
	public static final int EDIT_WIDTH = 1;
	public static final int EDIT_HEIGHT = 2;
	public static final int EDIT_BOTH = 3;
	private IHorizontalLine horizontalLine;
	private IVerticalLine verticalLine;
	private ImageData imageData;
	private int editable;

	public ImageData getImageData() {
		return imageData;
	}

	public void setImageData(ImageData imageData) {
		this.imageData = imageData;
	}

	public int getEditable() {
		return editable;
	}

	public void setEditable(int editable) {
		this.editable = editable;
	}

	public IHorizontalLine getHorizontalLine() {
		return horizontalLine;
	}

	public void setHorizontalLine(IHorizontalLine horizontalLine) {
		this.horizontalLine = horizontalLine;
	}

	public IVerticalLine getVerticalLine() {
		return verticalLine;
	}

	public void setVerticalLine(IVerticalLine verticalLine) {
		this.verticalLine = verticalLine;
	}

}
