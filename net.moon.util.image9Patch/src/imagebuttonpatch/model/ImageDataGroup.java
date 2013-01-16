package imagebuttonpatch.model;

import java.util.List;

import org.eclipse.swt.graphics.ImageData;

public class ImageDataGroup {
	private List<SlicedImageData> slicedImageDataList;
	private int hScaleGuideLineCount = 0;
	private int vScaleGuideLineCount = 0;
	private int width;
	private int height;
	private ImageData imageData;
	private ContentData contentData;
	private HorizontalTextLine horizontalTextLine;
	private VerticalTextLine verticalTextLine;

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public List<SlicedImageData> getSlicedImageDataList() {
		return slicedImageDataList;
	}

	public void setSlicedImageDataList(List<SlicedImageData> slicedImageDataList) {
		this.slicedImageDataList = slicedImageDataList;
	}

	public int gethScaleGuideLineCount() {
		return hScaleGuideLineCount;
	}

	public void sethScaleGuideLineCount(int hGuideLineCount) {
		this.hScaleGuideLineCount = hGuideLineCount;
	}

	public int getvScaleGuideLineCount() {
		return vScaleGuideLineCount;
	}

	public void setvScaleGuideLineCount(int vGuideLineCount) {
		this.vScaleGuideLineCount = vGuideLineCount;
	}

	public ImageData getImageData() {
		return imageData;
	}

	public void setImageData(ImageData imageData) {
		this.imageData = imageData;
	}

	public HorizontalTextLine getHorizontalTextLine() {
		return horizontalTextLine;
	}

	public void setHorizontalTextLine(HorizontalTextLine horizontalTextLine) {
		this.horizontalTextLine = horizontalTextLine;
	}

	public VerticalTextLine getVerticalTextLine() {
		return verticalTextLine;
	}

	public void setVerticalTextLine(VerticalTextLine verticalTextLine) {
		this.verticalTextLine = verticalTextLine;
	}

	public ContentData getContentData() {
		return contentData;
	}

	public void setContentData(ContentData contentData) {
		this.contentData = contentData;
	}

}
