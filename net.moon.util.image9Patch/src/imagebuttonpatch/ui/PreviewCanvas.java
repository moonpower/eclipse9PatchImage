package imagebuttonpatch.ui;

import imagebuttonpatch.generate.NinePatchImage2;
import imagebuttonpatch.model.ContentData;
import imagebuttonpatch.ui.model.Draw9PatchAttribute;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.ScrollBar;

public class PreviewCanvas {

	private Canvas canvas;
	private Image patchedHeightImage;
	private Image patchedBothImage;
	private Image patchedWidthImage;
	private Image backgroundImage;
	private Image contentHeightImage;
	private Image contentWidthImage;
	private Image contentBothImage;
	private Draw9PatchAttribute attribute;
	private ScrollBar hBar;
	private ScrollBar vBar;
	private Point origin;
	private ContentData contentWidthData;
	private ContentData contentHeightData;
	private ContentData contentBothData;

	public PreviewCanvas(Composite parent, int style,
			Draw9PatchAttribute attribute) {
		this.canvas = new Canvas(parent, style);
		this.attribute = attribute;
		init();

		addListeners();
	}

	private void init() {
		setNinePatchedImages(attribute.getImage());

	}

	private void addListeners() {
		origin = new Point(0, 0);
		hBar = canvas.getHorizontalBar();
		vBar = canvas.getVerticalBar();
		hBar.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				int hSelection = hBar.getSelection();
				int destX = -hSelection - origin.x;

				Rectangle rect = canvas.getBounds();

				canvas.scroll(destX, 0, 0, 0, rect.width, rect.height, false);
				origin.x = -hSelection;
			}
		});

		vBar.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event e) {
				int vSelection = vBar.getSelection();

				int destY = -vSelection - origin.y;

				Rectangle rect = canvas.getBounds();
				canvas.scroll(0, destY, 0, 0, rect.width, rect.height, false);
				origin.y = -vSelection;
			}
		});

		canvas.addListener(SWT.Resize, new Listener() {
			@Override
			public void handleEvent(Event e) {
				resizeScrollBar();
				canvas.redraw();
			}

		});

		canvas.addListener(SWT.Paint, new Listener() {

			@Override
			public void handleEvent(Event event) {
				GC gc = event.gc;

				gc.drawImage(backgroundImage, origin.x, origin.y);

				Point widthImagePoint = computeCenterPosition(
						canvas.getBounds(), patchedWidthImage);
				Point heightImagePoint = computeCenterPosition(
						canvas.getBounds(), patchedHeightImage);

				int hImage = widthImagePoint.y + origin.y
						- patchedHeightImage.getImageData().height - 10;
				int y = hImage;
				int x = heightImagePoint.x;
				if (y < 20) {
					y = 20;
				}
				if (x < 20) {
					x = 20;
				}
				gc.drawImage(patchedHeightImage, x + origin.x, y + origin.y);

				x = widthImagePoint.x;
				if (x < 20) {
					x = 20;
				}
				y = y + patchedHeightImage.getImageData().height + 10;
				gc.drawImage(patchedWidthImage, x + origin.x, y + origin.y);

				y = y + patchedWidthImage.getImageData().height + 10;
				gc.drawImage(patchedBothImage, x + origin.x, y + origin.y);

				if (attribute.isShowContent()) {
					disposeContentImage();
					contentHeightImage = createContentImage(patchedHeightImage,
							contentHeightData);
					contentWidthImage = createContentImage(patchedWidthImage,
							contentWidthData);
					contentBothImage = createContentImage(patchedBothImage,
							contentBothData);

					hImage = widthImagePoint.y + origin.y
							- patchedHeightImage.getImageData().height - 10;
					y = hImage;
					x = heightImagePoint.x;
					if (y < 20) {
						y = 20;
					}
					if (x < 20) {
						x = 20;
					}
					gc.drawImage(contentHeightImage, x + origin.x, y + origin.y);

					x = widthImagePoint.x;
					if (x < 20) {
						x = 20;
					}
					y = y + patchedHeightImage.getImageData().height + 10;
					gc.drawImage(contentWidthImage, x + origin.x, y + origin.y);

					y = y + patchedWidthImage.getImageData().height + 10;
					gc.drawImage(contentBothImage, x + origin.x, y + origin.y);

				}
			}

			private void disposeContentImage() {
				if (contentBothImage != null && !contentBothImage.isDisposed()) {
					contentBothImage.dispose();
				}
				if (contentHeightImage != null
						&& contentHeightImage.isDisposed()) {
					contentHeightImage.dispose();
				}
				if (contentWidthImage != null
						&& !contentWidthImage.isDisposed()) {
					contentWidthImage.dispose();
				}

			}

		});

	}

	private Image createContentImage(Image image, ContentData data) {
		ImageData imageData = image.getImageData();
		ImageData imageData2 = new ImageData(imageData.width, imageData.height,
				24, imageData.palette);

		int endX = data.getStartX() + data.getWidth();
		int startX = data.getStartX();
		int startY = data.getStartY();
		int endY = data.getStartY() + data.getHeight();
		int pixel = imageData.palette.getPixel(new RGB(255, 255, 0));
		for (int x = 0; x < imageData2.width; x++) {
			for (int y = 0; y < imageData2.height; y++) {

				if ((startX <= x && endX >= x) && (startY <= y && endY >= y)) {
					imageData2.setPixel(x, y, pixel);
					imageData2.setAlpha(x, y, 150);
				}
			}
		}
		return new Image(Display.getDefault(), imageData2);
	}

	private void resizeScrollBar() {
		int imageWidth = patchedBothImage.getImageData().width + 50;
		int imageHeight = patchedBothImage.getImageData().height
				+ patchedHeightImage.getImageData().height
				+ patchedWidthImage.getImageData().height + 50;
		Rectangle rect = canvas.getBounds();
		if (backgroundImage != null && !backgroundImage.isDisposed()) {
			backgroundImage.dispose();
		}

		if (rect.width < imageWidth && rect.height < imageHeight) {
			backgroundImage = createTransparentBackground(imageWidth,
					imageHeight);
			vBar.setVisible(true);
			hBar.setVisible(true);
		}
		else if (rect.width < imageWidth && rect.height > imageHeight) {
			backgroundImage = createTransparentBackground(imageWidth,
					rect.height);
			vBar.setVisible(false);
			hBar.setVisible(true);
		}
		else if (rect.width > imageWidth && rect.height < imageHeight) {
			backgroundImage = createTransparentBackground(rect.width,
					imageHeight);
			vBar.setVisible(true);
			hBar.setVisible(false);
		}
		if (backgroundImage.isDisposed()) {
			backgroundImage = createTransparentBackground(rect.width,
					rect.height);
			vBar.setVisible(false);
			hBar.setVisible(false);
		}

		hBar.setMaximum(imageWidth);
		vBar.setMaximum(imageHeight);
		hBar.setThumb(Math.min(imageWidth, rect.width));
		vBar.setThumb(Math.min(imageHeight, rect.height));
		int hPage = imageWidth - rect.width;
		int vPage = imageHeight - rect.height;
		int hSelection = hBar.getSelection();
		int vSelection = vBar.getSelection();
		if (hSelection >= hPage) {
			if (hPage <= 0) {
				hSelection = 0;
			}
			origin.x = -hSelection;
		}
		if (vSelection >= vPage) {
			if (vPage <= 0) {
				vSelection = 0;
			}
			origin.y = -vSelection;
		}
	}

	private Image createTransparentBackground(int width, int height) {
		PaletteData paletteData = new PaletteData(new RGB[] {
				new RGB(204, 204, 204), new RGB(255, 255, 255) });
		System.out.println("createTransparent width,height (" + width + ","
				+ height + ")");
		ImageData imageData = new ImageData(width, height, 8, paletteData);
		RGB grayRGB = new RGB(204, 204, 204);
		RGB whiteRGB = new RGB(255, 255, 255);
		RGB currentColor = null;
		int switchColorNum = 0;
		for (int x = 0; x < width; x++) {
			if (x != 0 && (x % 8) == 0) {
				switchColorNum = switchColorNum == 0 ? 1 : 0;
			}
			for (int y = 0; y < height; y++) {
				if (y < 8) {
					currentColor = switchColorNum == 0 ? grayRGB : whiteRGB;
				}
				else if ((y % 8) == 0) {
					currentColor = currentColor == grayRGB ? whiteRGB : grayRGB;
				}
				int pixel = paletteData.getPixel(currentColor);
				imageData.setPixel(x, y, pixel);

			}
		}

		return new Image(Display.getDefault(), imageData);
	}

	private Point computeCenterPosition(Rectangle bounds, Image image) {
		Point point;
		int y = (bounds.height / 2) - (image.getImageData().height / 2);
		int x = (bounds.width / 2) - (image.getImageData().width / 2);
		point = new Point(x, y);
		return point;
	}

	public void setNinePatchedImages(Image image) {

		int width = image.getImageData().width;
		int height = image.getImageData().height;
		int scaleValue = attribute.getScaleValue();

		disposePatchedImage();
		patchedWidthImage = NinePatchImage2.getImage(width * scaleValue,
				height, image);
		contentWidthData = NinePatchImage2.getContentData(width * scaleValue,
				height, image);
		patchedHeightImage = NinePatchImage2.getImage(width, height
				* scaleValue, image);
		contentHeightData = NinePatchImage2.getContentData(width, height
				* scaleValue, image);
		patchedBothImage = NinePatchImage2.getImage(scaleValue, image);
		contentBothData = NinePatchImage2.getContentData(scaleValue, image);
	}

	private void disposePatchedImage() {
		if (patchedBothImage != null && !patchedBothImage.isDisposed()) {
			patchedBothImage.dispose();
		}

		if (patchedHeightImage != null && !patchedHeightImage.isDisposed()) {
			patchedHeightImage.dispose();
		}

		if (patchedWidthImage != null && !patchedWidthImage.isDisposed()) {
			patchedWidthImage.dispose();

		}
	}

	public void redraw() {
		canvas.redraw();
	}

	public void redrawScale() {
		Image image = attribute.getImage();
		setNinePatchedImages(image);
		resizeScrollBar();

		canvas.redraw();
	}

	public Draw9PatchAttribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Draw9PatchAttribute attribute) {

		this.attribute = attribute;

	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void dispose() {
		List<Image> list = new ArrayList<Image>();
		list.add(backgroundImage);
		list.add(patchedBothImage);
		list.add(patchedHeightImage);
		list.add(patchedWidthImage);
		list.add(contentBothImage);
		list.add(contentHeightImage);
		list.add(contentWidthImage);
		for (Image each : list) {
			if (each != null && !each.isDisposed()) {
				each.dispose();
			}
		}

	}
}
