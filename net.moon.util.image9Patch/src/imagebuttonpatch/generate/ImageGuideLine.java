package imagebuttonpatch.generate;

import imagebuttonpatch.model.HTransparentScaleLine;
import imagebuttonpatch.model.HorizontalScaleLine;
import imagebuttonpatch.model.HorizontalTextLine;
import imagebuttonpatch.model.IHorizontalLine;
import imagebuttonpatch.model.IVerticalLine;
import imagebuttonpatch.model.VTransparentScaleLine;
import imagebuttonpatch.model.VerticalScaleLine;
import imagebuttonpatch.model.VerticalTextLine;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.ImageData;

public class ImageGuideLine {
	private final int BLACK_PIXEL_ALPHA = 255;
	private final int TRANSPARENT_PIXEL_ALPHA = 0;
	private int hScaleBlackLineCount;
	private int vScaleBlackLineCount;
	private ImageData imageData;

	public ImageGuideLine(ImageData imageData) {
		this.imageData = imageData;
	}

	public List<IHorizontalLine> getHorizontalScaleLineList() {
		HorizontalScaleLine horizontalScaleLine = null;
		HTransparentScaleLine transparentScaleLine = null;
		hScaleBlackLineCount = 0;
		List<IHorizontalLine> horizontalLineList = new ArrayList<IHorizontalLine>();
		for (int x = 1; x < imageData.width; x++) {
			int alpha = imageData.getAlpha(x, 0);
			switch (alpha) {
			case BLACK_PIXEL_ALPHA:
				if (transparentScaleLine != null) {
					addHorizontalLineList(transparentScaleLine,
							horizontalLineList, x);
					transparentScaleLine = null;
				}
				if (horizontalScaleLine == null) {
					horizontalScaleLine = new HorizontalScaleLine();
					horizontalScaleLine
							.setLineInfo(horizontalScaleLine.BLACK_LINE);
					horizontalScaleLine.setStartX(x - 1);
					hScaleBlackLineCount++;
				}
				else if (horizontalScaleLine != null
						&& x + 1 == imageData.width) {
					addHorizontalLineList(horizontalScaleLine,
							horizontalLineList, x);
					horizontalScaleLine = null;
				}

				break;
			case TRANSPARENT_PIXEL_ALPHA:
				if (horizontalScaleLine != null) {
					addHorizontalLineList(horizontalScaleLine,
							horizontalLineList, x);
					horizontalScaleLine = null;
				}
				if (transparentScaleLine == null) {
					transparentScaleLine = new HTransparentScaleLine();
					transparentScaleLine
							.setLineInfo(transparentScaleLine.TRANSPARENT_LINE);
					transparentScaleLine.setStartX(x - 1);

				}
				else if (transparentScaleLine != null
						&& x + 1 == imageData.width) {
					addHorizontalLineList(transparentScaleLine,
							horizontalLineList, x);
					transparentScaleLine = null;
				}
				break;
			}
		}
		return horizontalLineList;
	}

	public List<IVerticalLine> getVerticalScalelLineList() {
		VerticalScaleLine verticalScaleLine = null;
		VTransparentScaleLine transparentScaleLine = null;
		vScaleBlackLineCount = 0;
		List<IVerticalLine> verticalLineList = new ArrayList<IVerticalLine>();
		for (int y = 1; y < imageData.height; y++) {
			int alpha = imageData.getAlpha(0, y);
			switch (alpha) {
			case BLACK_PIXEL_ALPHA:
				if (verticalScaleLine == null) {
					verticalScaleLine = new VerticalScaleLine();
					verticalScaleLine.setLineInfo(IVerticalLine.BLACK_LINE);
					verticalScaleLine.setStartY(y - 1);
					vScaleBlackLineCount++;
				}
				else if (verticalScaleLine != null && y + 1 == imageData.height) {
					addVerticalLineList(verticalScaleLine, verticalLineList, y);
					verticalScaleLine = null;
				}
				if (transparentScaleLine != null) {
					addVerticalLineList(transparentScaleLine, verticalLineList,
							y);
					transparentScaleLine = null;
				}
				break;
			case TRANSPARENT_PIXEL_ALPHA:
				if (verticalScaleLine != null) {
					addVerticalLineList(verticalScaleLine, verticalLineList, y);
					verticalScaleLine = null;
				}
				if (transparentScaleLine == null) {
					transparentScaleLine = new VTransparentScaleLine();
					transparentScaleLine
							.setLineInfo(IVerticalLine.TRANSPARENT_LINE);
					transparentScaleLine.setStartY(y - 1);
				}
				else if (transparentScaleLine != null
						&& y + 1 == imageData.height) {
					addVerticalLineList(transparentScaleLine, verticalLineList,
							y);
					transparentScaleLine = null;
				}
				break;
			}
		}
		return verticalLineList;

	}

	private void addHorizontalLineList(Object object,
			List<IHorizontalLine> horizontalLineList, int x) {
		if (object instanceof HTransparentScaleLine) {
			HTransparentScaleLine transparentScaleLine = (HTransparentScaleLine) object;
			transparentScaleLine.setEndX(x - 1);
			transparentScaleLine.setWidth(transparentScaleLine.getEndX()
					- transparentScaleLine.getStartX());

			horizontalLineList.add(transparentScaleLine);
		}
		else if (object instanceof HorizontalScaleLine) {
			HorizontalScaleLine horizontalScaleLine = (HorizontalScaleLine) object;
			horizontalScaleLine.setEndX(x - 1);
			horizontalScaleLine.setWidth(horizontalScaleLine.getEndX()
					- horizontalScaleLine.getStartX());

			horizontalLineList.add(horizontalScaleLine);

		}
	}

	private void addVerticalLineList(Object object,
			List<IVerticalLine> verticalLineList, int y) {
		if (object instanceof VTransparentScaleLine) {
			VTransparentScaleLine transparentScaleLine = (VTransparentScaleLine) object;
			transparentScaleLine.setEndY(y - 1);
			transparentScaleLine.setHeight(transparentScaleLine.getEndY()
					- transparentScaleLine.getStartY());
			verticalLineList.add(transparentScaleLine);
		}
		else if (object instanceof VerticalScaleLine) {
			VerticalScaleLine verticalScaleLine = (VerticalScaleLine) object;
			verticalScaleLine.setEndY(y - 1);
			verticalScaleLine.setHeight(verticalScaleLine.getEndY()
					- verticalScaleLine.getStartY());
			verticalLineList.add(verticalScaleLine);
		}
	}

	/**
	 * @return
	 */
	public HorizontalTextLine getHorizontalTextLine() {
		HorizontalTextLine horizontalTextLine = null;
		for (int x = 1; x < imageData.width; x++) {
			int alpha = imageData.getAlpha(x, imageData.height - 1);
			switch (alpha) {
			case BLACK_PIXEL_ALPHA:
				if (horizontalTextLine == null) {
					horizontalTextLine = new HorizontalTextLine();
					horizontalTextLine.setStartX(x - 1);
					horizontalTextLine.setWidth(1);
					horizontalTextLine.setEndX(x);
				}
				else if (horizontalTextLine != null) {
					horizontalTextLine.setEndX(x);
					horizontalTextLine.setWidth(horizontalTextLine.getEndX()
							- horizontalTextLine.getStartX());
				}
				break;

			}
		}
		if (horizontalTextLine == null) {
			horizontalTextLine = new HorizontalTextLine(0, imageData.width - 2,
					imageData.width - 2);
		}

		return horizontalTextLine;
	}

	public VerticalTextLine getVerticalTextLine() {
		VerticalTextLine verticalTextLine = null;
		for (int y = 1; y < imageData.height; y++) {
			int alpha = imageData.getAlpha(imageData.width - 1, y);

			switch (alpha) {
			case BLACK_PIXEL_ALPHA:
				if (verticalTextLine == null) {
					verticalTextLine = new VerticalTextLine();
					verticalTextLine.setStartY(y - 1);
					verticalTextLine.setHeight(1);
					verticalTextLine.setEndY(y);
				}
				else if (verticalTextLine != null) {
					verticalTextLine.setEndY(y);
					verticalTextLine.setHeight(verticalTextLine.getEndY()
							- verticalTextLine.getStartY());
				}
				break;
			}
		}
		if (verticalTextLine == null) {
			verticalTextLine = new VerticalTextLine(0, imageData.height - 2,
					imageData.height - 2);

		}

		return verticalTextLine;
	}

	public int gethScaleBlackLineCount() {
		return hScaleBlackLineCount;
	}

	public int getvScaleBlackLineCount() {
		return vScaleBlackLineCount;
	}

}
