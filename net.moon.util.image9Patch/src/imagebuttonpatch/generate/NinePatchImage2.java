package imagebuttonpatch.generate;

import imagebuttonpatch.model.ContentData;
import imagebuttonpatch.model.ImageDataGroup;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class NinePatchImage2 {
	public static final int GRID_LAYOUT = 1;
	public static final int FILL_LAYOUT = 0;
	private static CombineImageData combine = new CombineImageData();;

	private static void create(Image guidedImage) {
		if (guidedImage == null) {
			return;
		}
		ImageData imageData = guidedImage.getImageData();
		SliceImageData slice = new SliceImageData();
		ImageDataGroup imageDataGroup = slice.getImageDataGroup(imageData);

		combine.setInput(imageDataGroup);

	}

	private static void resize(int width, int height, Image guidedImage) {
		if (guidedImage == null) {
			return;
		}
		ImageData imageData = guidedImage.getImageData();
		SliceImageData slice = new SliceImageData();
		ImageDataGroup imageDataGroup = slice.getImageDataGroup(imageData);
		ResizeImageData resize = new ResizeImageData();
		ImageDataGroup resizedImageDataGroup = resize.getResizedImageDataGroup(
				width, height, imageDataGroup);
		combine.setInput(resizedImageDataGroup);

	}

	private static void scale(int scale, Image guidedImage) {
		if (guidedImage == null) {
			return;
		}
		ImageData imageData = guidedImage.getImageData();
		SliceImageData slice = new SliceImageData();
		ImageDataGroup imageDataGroup = slice.getImageDataGroup(imageData);
		ResizeImageData resize = new ResizeImageData();
		ImageDataGroup resizedImageDataGroup = resize.getResizedImageDataGroup(
				imageData.width * scale, imageData.height * scale,
				imageDataGroup);
		combine.setInput(resizedImageDataGroup);

	}

	/**
	 * 크기를 변경하여 Image로 반환. 원본 넓이 또는 높이 보다 작은 숫자를 넣을 경우 원본의 크기로 입력됨.
	 * 
	 * @param width
	 * @param height
	 * @return Iamge
	 */
	public static Image getImage(int width, int height, Image guidedImage) {
		resize(width, height, guidedImage);
		if (guidedImage == null) {
			return null;
		}
		return combine.getImage();
	}

	/**
	 * 가이드라인이 있는 이미지를 배경으로 한 컴포지트를 생성하여 반환.
	 * 
	 * @param parent
	 *            Composite
	 * @param style
	 *            int
	 * @param layout
	 *            GridLayout
	 * @param guidedImage
	 *            Image
	 * @return Composite
	 */
	public static Composite createComposite(Composite parent, int style,
			final GridLayout layout, final Image guidedImage) {
		if (parent == null || guidedImage == null) {
			return null;
		}
		final Color color = parent.getBackground();
		final Composite composite = new Composite(parent, style);
		create(guidedImage);
		ContentData contentData = combine.getContentData();
		layout.marginLeft = contentData.getStartX();
		layout.marginRight = (guidedImage.getImageData().width - 2)
				- contentData.getEndX();
		layout.marginTop = contentData.getStartY();
		layout.marginBottom = (guidedImage.getImageData().height - 2)
				- contentData.getEndY();
		composite.setLayout(layout);

		composite.setBackgroundMode(SWT.INHERIT_FORCE);

		composite.addListener(SWT.Resize, new Listener() {
			Image image;

			@Override
			public void handleEvent(Event event) {
				if (image != null && !image.isDisposed()) {
					image.dispose();
				}

				Rectangle rect = composite.getBounds();
				resize(rect.width, rect.height, guidedImage);
				image = combine.getImage(color);
				composite.setBackgroundImage(image);
			}

		});
		return composite;
	}

	/**
	 * 크기를 변경하여 ImageData로 반환. 원본 넓이 또는 높이 보다 작은 숫자를 넣을 경우 원본의 크기로 입력됨.
	 * 
	 * @param width
	 * @param height
	 * @return ImageData
	 */
	public static ImageData getImageData(int width, int height,
			Image guidedImage) {
		resize(width, height, guidedImage);
		if (guidedImage == null) {
			return null;
		}
		return combine.getImageData();
	}

	/**
	 * 가이드라인이 제거된 원본 Image를 반환.
	 * 
	 * @return Image
	 */
	public static Image getImage(Image guidedImage) {
		create(guidedImage);
		if (guidedImage == null) {
			return null;
		}
		return combine.getImage();
	}

	/**
	 * 가이드라인이 제거된 ImageData를 반환.
	 * 
	 * @return ImageData
	 */
	public static ImageData getImageData(Image guidedImage) {
		create(guidedImage);
		if (guidedImage == null) {
			return null;
		}
		return combine.getImageData();
	}

	/**
	 * 배율로 크기를 변경하여 ImageData를 반환.
	 * 
	 * @param scale
	 * @return ImageData
	 */
	public static ImageData getImageData(int scale, Image guidedImage) {
		scale(scale, guidedImage);
		if (guidedImage == null) {
			return null;
		}
		return combine.getImageData();
	}

	/**
	 * 배율로 크기를 변경하여 Image를 반환.
	 * 
	 * @param scale
	 * @return Image
	 */
	public static Image getImage(int scale, Image guidedImage) {
		scale(scale, guidedImage);
		if (guidedImage == null) {
			return null;
		}
		return combine.getImage();
	}

	public static ContentData getContentData(int width, int height,
			Image guidedImage) {
		if (guidedImage == null) {
			return null;
		}
		resize(width, height, guidedImage);
		return combine.getContentData();
	}

	public static ContentData getContentData(Image guidedImage) {
		if (guidedImage == null) {
			return null;
		}
		create(guidedImage);
		return combine.getContentData();
	}

	public static ContentData getContentData(int scale, Image guidedImage) {
		scale(scale, guidedImage);
		if (guidedImage == null) {
			return null;
		}
		return combine.getContentData();
	}
}
