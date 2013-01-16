package imagebuttonpatch.generate;

import imagebuttonpatch.model.ContentData;
import imagebuttonpatch.model.ImageDataGroup;
import imagebuttonpatch.model.SlicedImageData;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public class CombineImageData {
	private Image image;
	private ImageData imageData;
	private ImageDataGroup imageDataGroup;

	public CombineImageData() {
	}

	public void setInput(ImageDataGroup imageDataGroup) {
		this.imageDataGroup = imageDataGroup;

	}

	private void create() {

		ImageData combinedImageData = new ImageData(imageDataGroup.getWidth(),
				imageDataGroup.getHeight(),
				imageDataGroup.getImageData().depth,
				imageDataGroup.getImageData().palette);

		for (SlicedImageData each : imageDataGroup.getSlicedImageDataList()) {
			for (int x = 0; x < each.getImageData().width; x++) {
				for (int y = 0; y < each.getImageData().height; y++) {
					int pixel = each.getImageData().getPixel(x, y);
					int alpha = each.getImageData().getAlpha(x, y);

					combinedImageData.setPixel(x
							+ each.getHorizontalLine().getStartX(), y
							+ each.getVerticalLine().getStartY(), pixel);
					combinedImageData.setAlpha(x
							+ each.getHorizontalLine().getStartX(), y
							+ each.getVerticalLine().getStartY(), alpha);

				}
			}
		}
		imageData = combinedImageData;

		image = new Image(Display.getDefault(), imageData);
	}

	private void createBackground(Color color) {

		ImageData combinedImageData = new ImageData(imageDataGroup.getWidth(),
				imageDataGroup.getHeight(),
				imageDataGroup.getImageData().depth,
				imageDataGroup.getImageData().palette);
		int bgPixel = imageDataGroup.getImageData().palette.getPixel(color
				.getRGB());
		int whitePixel = imageDataGroup.getImageData().palette
				.getPixel(new RGB(255, 255, 255));
		int blackPixel = imageDataGroup.getImageData().palette
				.getPixel(new RGB(0, 0, 0));
		for (SlicedImageData each : imageDataGroup.getSlicedImageDataList()) {
			for (int x = 0; x < each.getImageData().width; x++) {
				for (int y = 0; y < each.getImageData().height; y++) {
					int pixel = each.getImageData().getPixel(x, y);
					int alpha = each.getImageData().getAlpha(x, y);
					if ((pixel == blackPixel || pixel == whitePixel)
							&& alpha == 0) {
						pixel = bgPixel;
					}
					combinedImageData.setPixel(x
							+ each.getHorizontalLine().getStartX(), y
							+ each.getVerticalLine().getStartY(), pixel);
					combinedImageData.setAlpha(x
							+ each.getHorizontalLine().getStartX(), y
							+ each.getVerticalLine().getStartY(), alpha);

				}
			}
		}
		imageData = combinedImageData;

		image = new Image(Display.getDefault(), imageData);
	}

	public Image getImage(Color color) {
		createBackground(color);
		return image;
	}

	public Image getImage() {
		create();
		return image;
	}

	public ImageData getImageData() {
		create();
		return imageData;
	}

	public ContentData getContentData() {
		return imageDataGroup.getContentData();
	}
}
