package imagebuttonpatch.generate;

import imagebuttonpatch.model.ContentData;
import imagebuttonpatch.model.IHorizontalLine;
import imagebuttonpatch.model.IVerticalLine;
import imagebuttonpatch.model.ImageDataGroup;
import imagebuttonpatch.model.SlicedImageData;

import java.util.List;

import org.eclipse.swt.graphics.ImageData;

public class ResizeImageData {

	public ImageDataGroup getResizedImageDataGroup(int width, int height,
			ImageDataGroup imageDataGroup) {
		if (imageDataGroup.getWidth() > width) {
			width = imageDataGroup.getWidth();
		}
		if (imageDataGroup.getHeight() > height) {
			height = imageDataGroup.getHeight();
		}
		int extendWidth;
		int extendHeight;
		int extraWidth;
		int extraHeight;
		int remainWidth = width - imageDataGroup.getWidth();
		if (remainWidth > 0 && imageDataGroup.gethScaleGuideLineCount() > 0) {
			extendWidth = remainWidth
					/ imageDataGroup.gethScaleGuideLineCount();
			extraWidth = remainWidth % imageDataGroup.gethScaleGuideLineCount();

		}
		else {
			extendWidth = 0;
			extraWidth = 0;
		}
		int remainHeight = height - imageDataGroup.getHeight();
		if (remainHeight > 0 && imageDataGroup.getvScaleGuideLineCount() > 0) {
			extendHeight = remainHeight
					/ imageDataGroup.getvScaleGuideLineCount();
			extraHeight = remainHeight
					% imageDataGroup.getvScaleGuideLineCount();
		}
		else {
			extendHeight = 0;
			extraHeight = 0;
		}
		SlicedImageData previousSlicedImageData = null;
		List<SlicedImageData> slicedImageDataList = imageDataGroup
				.getSlicedImageDataList();
		for (SlicedImageData each : slicedImageDataList) {
			previousSlicedImageData = getPreviousSlicedImageData(
					slicedImageDataList, each);
			if (isTopHorizontal(each)
					&& each.getHorizontalLine().getLineInfo() == IHorizontalLine.BLACK_LINE) {

				ImageData scaledTo = each.getImageData().scaledTo(
						each.getHorizontalLine().getWidth() + extendWidth
								+ extraWidth,
						each.getVerticalLine().getHeight());
				each.getHorizontalLine().setWidth(scaledTo.width);
				each.setImageData(scaledTo);

				each.getHorizontalLine().setStartX(
						previousSlicedImageData.getHorizontalLine().getEndX());
				int endX = each.getHorizontalLine().getStartX()
						+ each.getImageData().width;
				each.getHorizontalLine().setEndX(endX);
				extraWidth = 0;
			}
			else if (isTopHorizontal(each)
					&& each.getHorizontalLine().getLineInfo() == IHorizontalLine.TRANSPARENT_LINE) {
				each.getHorizontalLine().setStartX(
						previousSlicedImageData.getHorizontalLine().getEndX());

				int endX = each.getHorizontalLine().getStartX()
						+ each.getImageData().width;
				each.getHorizontalLine().setEndX(endX);

			}
			else if (isLeftVertical(each)
					&& each.getVerticalLine().getLineInfo() == IVerticalLine.BLACK_LINE) {

				ImageData scaledTo = each.getImageData().scaledTo(
						each.getHorizontalLine().getWidth(),
						each.getVerticalLine().getHeight() + extendHeight
								+ extraHeight);
				each.getVerticalLine().setHeight(scaledTo.height);
				each.setImageData(scaledTo);

				each.getVerticalLine().setStartY(
						previousSlicedImageData.getVerticalLine().getEndY());

				int endY = each.getVerticalLine().getStartY()
						+ each.getImageData().height;
				each.getVerticalLine().setEndY(endY);
				extraHeight = 0;
			}
			else if (isLeftVertical(each)
					&& each.getVerticalLine().getLineInfo() == IVerticalLine.TRANSPARENT_LINE) {

				each.getVerticalLine().setStartY(
						previousSlicedImageData.getVerticalLine().getEndY());

				each.getVerticalLine().setEndY(
						previousSlicedImageData.getVerticalLine().getEndY()
								+ each.getVerticalLine().getHeight());
			}
			else if (each.getVerticalLine().getLineInfo() == IVerticalLine.BLACK_LINE
					&& each.getHorizontalLine().getLineInfo() == IHorizontalLine.BLACK_LINE) {
				ImageData scaledTo = each.getImageData().scaledTo(
						each.getHorizontalLine().getWidth(),
						each.getVerticalLine().getHeight());
				each.setImageData(scaledTo);
			}
			else if (!(each.getHorizontalLine().getStartX() == 0)
					&& !(each.getVerticalLine().getStartY() == 0)
					&& each.getVerticalLine().getLineInfo() == IVerticalLine.BLACK_LINE
					&& each.getHorizontalLine().getLineInfo() == IHorizontalLine.TRANSPARENT_LINE) {
				ImageData scaledTo = each.getImageData().scaledTo(
						each.getHorizontalLine().getWidth(),
						each.getVerticalLine().getHeight());
				each.setImageData(scaledTo);
			}
			else if (!(each.getHorizontalLine().getStartX() == 0)
					&& !(each.getVerticalLine().getStartY() == 0)
					&& each.getVerticalLine().getLineInfo() == IVerticalLine.TRANSPARENT_LINE
					&& each.getHorizontalLine().getLineInfo() == IHorizontalLine.BLACK_LINE) {
				ImageData scaledTo = each.getImageData().scaledTo(
						each.getHorizontalLine().getWidth(),
						each.getVerticalLine().getHeight());
				each.setImageData(scaledTo);
			}

		}
		setContentData(imageDataGroup, remainWidth, remainHeight);
		ImageDataGroup newImageDataGroup = newImageDataGroup(imageDataGroup,
				remainHeight, remainWidth);
		return newImageDataGroup;
	}

	private void setContentData(ImageDataGroup imageDataGroup, int remainWidth,
			int remainHeight) {
		ContentData contentData = imageDataGroup.getContentData();
		int endY = contentData.getEndY() + remainHeight;
		if (contentData.getStartY() == 0) {
			contentData.setHeight(endY);
		}
		else {
			contentData.setEndY(endY);
			contentData.setHeight(endY - contentData.getStartY());
		}

		int endX = contentData.getEndX() + remainWidth;
		if (contentData.getStartX() == 0) {
			contentData.setWidth(endX);
		}
		else {
			contentData.setEndX(endX);
			contentData.setWidth(endX - contentData.getStartX());
		}
		ContentData newContentData = newContentData(contentData, endY, endX);

		imageDataGroup.setContentData(newContentData);
	}

	private ContentData newContentData(ContentData contentData, int endY,
			int endX) {
		ContentData newContentData = new ContentData();
		newContentData.setStartY(contentData.getStartY());
		newContentData.setStartX(contentData.getStartX());
		newContentData.setEndX(endX);
		newContentData.setEndY(endY);
		newContentData.setHeight(contentData.getHeight());
		newContentData.setWidth(contentData.getWidth());
		return newContentData;
	}

	private SlicedImageData getPreviousSlicedImageData(
			List<SlicedImageData> slicedImageDataList, SlicedImageData each) {
		int indexOf = slicedImageDataList.indexOf(each);
		if (indexOf == 0) {
			return each;
		}

		return slicedImageDataList.get(indexOf - 1);
	}

	private boolean isLeftVertical(SlicedImageData each) {
		return each.getHorizontalLine().getStartX() == 0
				&& !(each.getVerticalLine().getStartY() == 0);
	}

	private boolean isTopHorizontal(SlicedImageData each) {
		return each.getVerticalLine().getStartY() == 0
				&& !(each.getHorizontalLine().getStartX() == 0);
	}

	private ImageDataGroup newImageDataGroup(ImageDataGroup imageDataGroup,
			int remainHeight, int remainWidth) {
		ImageDataGroup newImageDataGroup = new ImageDataGroup();
		newImageDataGroup.setImageData(imageDataGroup.getImageData());
		newImageDataGroup.setSlicedImageDataList(imageDataGroup
				.getSlicedImageDataList());
		newImageDataGroup.sethScaleGuideLineCount(imageDataGroup
				.gethScaleGuideLineCount());
		newImageDataGroup.setvScaleGuideLineCount(imageDataGroup
				.getvScaleGuideLineCount());
		newImageDataGroup.setHeight(imageDataGroup.getHeight() + remainHeight);
		newImageDataGroup.setWidth(imageDataGroup.getWidth() + remainWidth);
		newImageDataGroup.setContentData(imageDataGroup.getContentData());
		return newImageDataGroup;
	}
}
