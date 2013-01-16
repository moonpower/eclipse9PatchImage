package imagebuttonpatch.generate;

import imagebuttonpatch.model.ContentData;
import imagebuttonpatch.model.HorizontalTextLine;
import imagebuttonpatch.model.IHorizontalLine;
import imagebuttonpatch.model.IVerticalLine;
import imagebuttonpatch.model.ImageDataGroup;
import imagebuttonpatch.model.SlicedImageData;
import imagebuttonpatch.model.VerticalTextLine;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.graphics.ImageData;

public class SliceImageData {

	public SliceImageData() {

	}

	public ImageDataGroup getImageDataGroup(ImageData imageData) {
		List<SlicedImageData> list = new ArrayList<SlicedImageData>();
		ImageGuideLine guideLine = new ImageGuideLine(imageData);
		List<IHorizontalLine> horizontalScaleLineList = guideLine
				.getHorizontalScaleLineList();
		List<IVerticalLine> verticalScalelLineList = guideLine
				.getVerticalScalelLineList();

		ImageData noneGuideLineImageData = getNoneGuideLineImageData(imageData);

		for (IVerticalLine verticalLine : verticalScalelLineList) {
			for (IHorizontalLine horizontalLine : horizontalScaleLineList) {
				ImageData extractedImageData = extractedImageData(
						noneGuideLineImageData, verticalLine, horizontalLine);
				addSlicedImageDataList(list, verticalLine, horizontalLine,
						extractedImageData);
			}
		}

		ImageDataGroup imageDataGroup = newImageDataGroup(
				noneGuideLineImageData, list, guideLine);
		return imageDataGroup;
	}

	private ImageData getNoneGuideLineImageData(ImageData originalImageData) {
		ImageData imageData = new ImageData(originalImageData.width - 2,
				originalImageData.height - 2, originalImageData.depth,
				originalImageData.palette);
		for (int x = 1; x < imageData.width; x++) {
			for (int y = 1; y < imageData.height; y++) {
				int pixel = originalImageData.getPixel(x, y);
				int alpha = originalImageData.getAlpha(x, y);
				imageData.setPixel(x - 1, y - 1, pixel);
				imageData.setAlpha(x - 1, y - 1, alpha);
			}
		}
		return imageData;
	}

	private ImageDataGroup newImageDataGroup(ImageData imageData,
			List<SlicedImageData> list, ImageGuideLine guideLine) {
		ImageDataGroup imageDataGroup = new ImageDataGroup();
		imageDataGroup.setSlicedImageDataList(list);
		imageDataGroup.sethScaleGuideLineCount(guideLine
				.gethScaleBlackLineCount());
		imageDataGroup.setvScaleGuideLineCount(guideLine
				.getvScaleBlackLineCount());
		imageDataGroup.setHeight(imageData.height);
		imageDataGroup.setWidth(imageData.width);
		imageDataGroup.setImageData(imageData);
		imageDataGroup.setHorizontalTextLine(guideLine.getHorizontalTextLine());
		imageDataGroup.setVerticalTextLine(guideLine.getVerticalTextLine());
		imageDataGroup.setContentData(createContentData(guideLine));
		return imageDataGroup;
	}

	private ContentData createContentData(ImageGuideLine guideLine) {
		ContentData contentData = new ContentData();
		HorizontalTextLine horizontalTextLine = guideLine
				.getHorizontalTextLine();
		contentData.setStartX(horizontalTextLine.getStartX());
		contentData.setEndX(horizontalTextLine.getEndX());
		contentData.setWidth(horizontalTextLine.getWidth());

		VerticalTextLine verticalTextLine = guideLine.getVerticalTextLine();

		contentData.setStartY(verticalTextLine.getStartY());
		contentData.setEndY(verticalTextLine.getEndY());
		contentData.setHeight(verticalTextLine.getHeight());
		return contentData;
	}

	private void addSlicedImageDataList(List<SlicedImageData> list,
			IVerticalLine verticalLine, IHorizontalLine horizontalLine,
			ImageData extractedImageData) {
		SlicedImageData slicedImageData = new SlicedImageData();
		slicedImageData.setHorizontalLine(horizontalLine);
		slicedImageData.setVerticalLine(verticalLine);
		slicedImageData.setImageData(extractedImageData);

		list.add(slicedImageData);
	}

	private ImageData extractedImageData(ImageData imageData,
			IVerticalLine verticalLine, IHorizontalLine horizontalLine) {
		ImageData extractedImageData = new ImageData(horizontalLine.getWidth(),
				verticalLine.getHeight(), imageData.depth, imageData.palette);
		for (int x = horizontalLine.getStartX(); x < horizontalLine.getEndX(); x++) {
			for (int y = verticalLine.getStartY(); y < verticalLine.getEndY(); y++) {
				int pixel = imageData.getPixel(x, y);
				int alpha = imageData.getAlpha(x, y);
				extractedImageData.setPixel(x - horizontalLine.getStartX(), y
						- verticalLine.getStartY(), pixel);
				extractedImageData.setAlpha(x - horizontalLine.getStartX(), y
						- verticalLine.getStartY(), alpha);
			}
		}
		return extractedImageData;
	}
}
