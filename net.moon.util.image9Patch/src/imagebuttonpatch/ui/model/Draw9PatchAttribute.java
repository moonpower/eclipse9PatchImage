package imagebuttonpatch.ui.model;

import imagebuttonpatch.ui.PreviewCanvas;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;

public class Draw9PatchAttribute {
	private PreviewCanvas previewCanvas;
	private Label xPointLabel;
	private Label yPointLabel;
	private boolean showLock = false;
	private boolean showPatch = false;
	private int zoomValue = 100;
	private boolean showContent = false;
	private int scaleValue = 2;
	private Image image;

	public Draw9PatchAttribute(Image image) {
		this.image = image;
	}

	public PreviewCanvas getPreviewCanvas() {
		return previewCanvas;
	}

	public void setPreviewCanvas(PreviewCanvas previewCanvas) {
		this.previewCanvas = previewCanvas;
	}

	public Label getxPointLabel() {
		return xPointLabel;
	}

	public void setxPointLabel(Label xPointLabel) {
		this.xPointLabel = xPointLabel;
	}

	public Label getyPointLabel() {
		return yPointLabel;
	}

	public void setyPointLabel(Label yPointLabel) {
		this.yPointLabel = yPointLabel;
	}

	public boolean isShowLock() {
		return showLock;
	}

	public void setShowLock(boolean showLock) {
		this.showLock = showLock;
	}

	public boolean isShowPatch() {
		return showPatch;
	}

	public void setShowPatch(boolean showPatch) {
		this.showPatch = showPatch;
	}

	public int getZoomValue() {
		return zoomValue;
	}

	public void setZoomValue(int zoomValue) {
		this.zoomValue = zoomValue;
	}

	public boolean isShowContent() {
		return showContent;
	}

	public void setShowContent(boolean showContent) {
		this.showContent = showContent;
	}

	public int getScaleValue() {
		return scaleValue;
	}

	public void setScaleValue(int scaleValue) {
		this.scaleValue = scaleValue;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

}
