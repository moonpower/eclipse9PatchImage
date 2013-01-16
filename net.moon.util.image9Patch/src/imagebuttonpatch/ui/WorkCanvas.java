package imagebuttonpatch.ui;

import imagebuttonpatch.generate.ImageGuideLine;
import imagebuttonpatch.model.IHorizontalLine;
import imagebuttonpatch.model.IVerticalLine;
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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class WorkCanvas {
	private Image workImage;
	private Draw9PatchAttribute attribute;
	private Canvas canvas;

	private List<Integer> mouseEventList = new ArrayList<Integer>();
	private boolean isLockArea;
	private int RIGHT_CLICK = 3;
	private int LEFT_CLICK = 1;
	private int currentX = 0;
	private int currentY = 0;

	public WorkCanvas(Composite parent, int style, Draw9PatchAttribute attribute) {
		this.canvas = new Canvas(parent, style);
		this.attribute = attribute;
		init();
		addListeners();
	}

	private void init() {

		ImageData imageData = attribute.getImage().getImageData();
		this.workImage = new Image(Display.getDefault(), imageData);

		this.canvas.setBackground(Display.getDefault().getSystemColor(
				SWT.COLOR_GRAY));
	}

	private void addListeners() {

		this.canvas.addListener(SWT.Paint, new Listener() {

			private Image lockAreaImage;
			private Image showPatchImage;
			private Image backgroundImage;

			@Override
			public void handleEvent(Event event) {
				Point point = computeCenterPosition(canvas.getBounds(),
						workImage);
				GC gc = event.gc;
				if (backgroundImage != null && !backgroundImage.isDisposed()) {
					backgroundImage.dispose();
				}
				backgroundImage = createTransparentBackground(
						workImage.getImageData().width,
						workImage.getImageData().height);

				gc.drawImage(backgroundImage, point.x, point.y);
				gc.drawImage(workImage, point.x, point.y);

				if (attribute.isShowPatch()) {
					disposeShowImage();
					showPatchImage = getShowPatchImage();
					gc.drawImage(showPatchImage,
							point.x + (1 * attribute.getZoomValue() / 100),
							point.y + (1 * attribute.getZoomValue() / 100));
				}

				if (isLockArea) {
					ImageData imageData = getLockImageData();
					disposeLockImage();
					lockAreaImage = new Image(Display.getDefault(), imageData);
					gc.drawImage(lockAreaImage,
							point.x + (1 * attribute.getZoomValue() / 100),
							point.y + (1 * attribute.getZoomValue() / 100));
				}
			}

			private void disposeShowImage() {
				if (showPatchImage != null && !showPatchImage.isDisposed()) {
					showPatchImage.dispose();
				}
			}

			private void disposeLockImage() {
				if (lockAreaImage != null && !lockAreaImage.isDisposed()) {
					lockAreaImage.dispose();
				}
			}

		});

		this.canvas.addListener(SWT.MouseDown, new Listener() {

			@Override
			public void handleEvent(Event event) {
				if (!mouseEventList.contains(event.button)) {
					mouseEventList.add(event.button);
				}
				Point point = computeCenterPosition(canvas.getBounds(),
						workImage);
				int width = workImage.getImageData().width;
				int height = workImage.getImageData().height;
				if (mouseEventList.contains(LEFT_CLICK)
						&& mouseEventList.size() == 1) {
					int blackPixel = 0;
					int blackAlpha = 255;
					drawPixelEvent(event, point, width, height, blackPixel,
							blackAlpha);
				}
				else if (mouseEventList.contains(RIGHT_CLICK)
						&& mouseEventList.size() == 1) {
					int tranparentPixel = 0;
					int tranparentAlpha = 0;
					drawPixelEvent(event, point, width, height,
							tranparentPixel, tranparentAlpha);
				}

			}

		});
		this.canvas.addListener(SWT.MouseMove, new Listener() {

			@Override
			public void handleEvent(final Event event) {

				Point point = computeCenterPosition(canvas.getBounds(),
						workImage);
				int width = workImage.getImageData().width;
				int height = workImage.getImageData().height;
				if ((event.x > point.x && event.x < point.x + width)
						&& (event.y > point.y && event.y < point.y + height)) {
					currentX = event.x - point.x;
					currentY = event.y - point.y;
					attribute.getxPointLabel().setText(
							"X: " + currentX / (attribute.getZoomValue() / 100)
									+ " px");
					attribute.getyPointLabel().setText(
							"Y: " + currentY / (attribute.getZoomValue() / 100)
									+ " px");

				}
				if (attribute.isShowLock()) {
					if ((event.x > point.x
							+ (1 * attribute.getZoomValue() / 100) && event.x < point.x
							+ width - (1 * attribute.getZoomValue() / 100))
							&& (event.y > point.y
									+ (1 * attribute.getZoomValue() / 100) && event.y < point.y
									+ height
									- (1 * attribute.getZoomValue() / 100))) {
						isLockArea = true;
						canvas.redraw();
					}
					else {
						isLockArea = false;
						canvas.redraw();
					}
				}

				if (mouseEventList.contains(LEFT_CLICK)
						&& mouseEventList.size() == 1) {
					int blackPixel = 0;
					int blackAlpha = 255;
					drawPixelEvent(event, point, width, height, blackPixel,
							blackAlpha);
				}
				else if (mouseEventList.contains(RIGHT_CLICK)
						&& mouseEventList.size() == 1) {
					int transparentPixel = 0;
					int transparentAlpha = 0;
					drawPixelEvent(event, point, width, height,
							transparentPixel, transparentAlpha);
				}

			}

		});
		this.canvas.addListener(SWT.MouseUp, new Listener() {

			@Override
			public void handleEvent(Event event) {
				if (mouseEventList.contains(event.button)) {
					mouseEventList.remove((Integer) event.button);
				}

			}
		});

	}

	private Image getShowPatchImage() {
		ImageGuideLine imageGuideLine = new ImageGuideLine(attribute.getImage()
				.getImageData());
		ImageData imageData = attribute.getImage().getImageData();
		int yellowPixel = imageData.palette.getPixel(new RGB(255, 255, 0));
		int orangePixel = imageData.palette.getPixel(new RGB(255, 165, 0));
		ImageData imageData2 = new ImageData(imageData.width, imageData.height,
				24, imageData.palette);
		List<IHorizontalLine> horizontalScaleLineList = imageGuideLine
				.getHorizontalScaleLineList();
		List<IHorizontalLine> hBlackLineList = new ArrayList<IHorizontalLine>();
		for (IHorizontalLine each : horizontalScaleLineList) {
			if (each.getLineInfo() == IHorizontalLine.BLACK_LINE) {
				hBlackLineList.add(each);
			}
		}
		List<IVerticalLine> verticalScalelLineList = imageGuideLine
				.getVerticalScalelLineList();
		List<IVerticalLine> vBlackLineList = new ArrayList<IVerticalLine>();
		for (IVerticalLine each : verticalScalelLineList) {
			if (each.getLineInfo() == IHorizontalLine.BLACK_LINE) {
				vBlackLineList.add(each);
			}
		}
		for (int x = 0; x < imageData.width - 2; x++) {
			for (int y = 0; y < imageData.height - 2; y++) {
				boolean isCrossArea = false;
				for (IHorizontalLine hEach : hBlackLineList) {

					for (IVerticalLine vEach : vBlackLineList) {
						if ((hEach.getStartX() <= x && hEach.getEndX() > x)
								&& (vEach.getStartY() <= y && vEach.getEndY() > y)) {
							imageData2.setPixel(x, y, orangePixel);
							imageData2.setAlpha(x, y, 120);
							isCrossArea = true;
						}
						else if (hEach.getStartX() <= x && hEach.getEndX() > x
								&& !isCrossArea) {
							imageData2.setPixel(x, y, yellowPixel);
							imageData2.setAlpha(x, y, 120);
						}
						else if (vEach.getStartY() <= y && vEach.getEndY() > y
								&& !isCrossArea) {
							imageData2.setPixel(x, y, yellowPixel);
							imageData2.setAlpha(x, y, 120);
						}

					}
				}

			}
		}
		ImageData scaledTo = imageData2.scaledTo(
				imageData2.width * (attribute.getZoomValue() / 100),
				imageData2.height * (attribute.getZoomValue() / 100));

		return new Image(Display.getDefault(), scaledTo);
	}

	private ImageData getLockImageData() {
		ImageData imageData = new ImageData(workImage.getImageData().width
				- (2 * attribute.getZoomValue() / 100),
				workImage.getImageData().height
						- (2 * attribute.getZoomValue() / 100), 24,
				workImage.getImageData().palette);
		for (int x = 0; x < imageData.width; x++) {
			for (int y = 0; y < imageData.height; y++) {
				imageData.setPixel(x, y, 0);
				imageData.setAlpha(x, y, 80);
			}
		}
		return imageData;
	}

	private Point computeCenterPosition(Rectangle bounds, Image image) {

		Point point;
		int y = (bounds.height / 2) - (image.getImageData().height / 2);
		int x = (bounds.width / 2) - (image.getImageData().width / 2);
		point = new Point(x, y);

		return point;
	}

	private void setPixel(ImageData imageData, int pixel, int alpha,
			int imagePositionX, int imagePositionY) {
		imageData.setPixel(imagePositionX, imagePositionY, pixel);
		imageData.setAlpha(imagePositionX, imagePositionY, alpha);
	}

	private void drawPixelEvent(final Event event, Point point, int width,
			int height, int pixel, int alpha) {
		ImageData imageData = attribute.getImage().getImageData();

		if ((event.x > point.x && event.x < point.x + width)
				&& (event.y > point.y && event.y < point.y + height)) {
			currentX = event.x - point.x;
			currentY = event.y - point.y;

			int imagePositionX = currentX / (attribute.getZoomValue() / 100);
			int imagePositionY = currentY / (attribute.getZoomValue() / 100);

			int imageWidth = attribute.getImage().getImageData().width;
			int imageHeight = attribute.getImage().getImageData().height;
			if ((imagePositionX == 0 || imagePositionX == imageWidth - 1)
					&& !(imagePositionY == 0 || imagePositionY == imageHeight - 1)) {

				setPixel(imageData, pixel, alpha, imagePositionX,
						imagePositionY);
			}
			else {

				if ((imagePositionY == 0 || imagePositionY == imageHeight - 1)
						&& !(imagePositionX == 0 || imagePositionX == imageWidth - 1)) {
					setPixel(imageData, pixel, alpha, imagePositionX,
							imagePositionY);
				}
			}
			if (!attribute.getImage().isDisposed()
					|| attribute.getImage() != null) {
				attribute.getImage().dispose();
			}
			attribute.setImage(new Image(Display.getDefault(), imageData));
			redrawZoom();
			attribute.getPreviewCanvas().redrawScale();

		}
	}

	private Image createTransparentBackground(int width, int height) {
		PaletteData paletteData = new PaletteData(new RGB[] {
				new RGB(204, 204, 204), new RGB(255, 255, 255) });
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
		Image backgroundImage = new Image(Display.getDefault(), imageData);
		return backgroundImage;
	}

	public void redrawZoom() {
		Image image = attribute.getImage();
		int imageWidth = image.getImageData().width;
		int imageHeight = image.getImageData().height;
		int imageZoomWidth = imageWidth * (attribute.getZoomValue() / 100);
		int imageZoomeHeight = imageHeight * (attribute.getZoomValue() / 100);
		ImageData imageData = image.getImageData();
		ImageData zoomImageData = imageData.scaledTo(imageZoomWidth,
				imageZoomeHeight);

		disposeWorkImage();
		workImage = new Image(Display.getDefault(), zoomImageData);
		canvas.setLayoutData(new GridData(workImage.getImageData().width,
				workImage.getImageData().height));
		canvas.getParent().update();
		canvas.redraw();
	}

	private void disposeWorkImage() {
		if (workImage != null && !workImage.isDisposed()) {
			workImage.dispose();
		}
	}

	public void redraw() {
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
		workImage.dispose();
	}

}
