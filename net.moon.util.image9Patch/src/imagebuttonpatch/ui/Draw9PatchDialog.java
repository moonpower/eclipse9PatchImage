package imagebuttonpatch.ui;

import imagebuttonpatch.ui.model.Draw9PatchAttribute;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;

public class Draw9PatchDialog extends Dialog {

	private Image image;

	private WorkCanvas workCanvas;
	private PreviewCanvas previewCanvas;

	private int currentX = 0;
	private int currentY = 0;
	private Label xPointLabel;
	private Label yPointLabel;

	private Draw9PatchAttribute attribute;

	public Draw9PatchDialog(Shell parentShell, IFile file) {
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM | SWT.APPLICATION_MODAL);

		if (!file.getName().endsWith(".9.png")) {
			Image image2 = new Image(Display.getDefault(), file.getLocation()
					.toOSString());
			ImageData imageData = image2.getImageData();
			ImageData imageData2 = new ImageData(imageData.width + 2,
					imageData.height + 2, imageData.depth, imageData.palette);
			for (int x = 1; x < imageData2.width - 1; x++) {
				for (int y = 1; y < imageData2.height - 1; y++) {

					int pixel = imageData.getPixel(x - 1, y - 1);
					int alpha = imageData.getAlpha(x - 1, y - 1);
					imageData2.setPixel(x, y, pixel);
					imageData2.setAlpha(x, y, alpha);
				}
			}
			this.image = new Image(Display.getDefault(), imageData2);
		}
		if (this.image == null) {
			this.image = new Image(Display.getDefault(), file.getLocation()
					.toOSString());
		}
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		GridLayout layout = new GridLayout(1, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		container.setLayout(layout);
		container.setLayoutData(new GridData(GridData.FILL_BOTH));

		SashForm sashForm = new SashForm(container, SWT.HORIZONTAL | SWT.SMOOTH);
		sashForm.setLayoutData(new GridData(GridData.FILL_BOTH));

		attribute = new Draw9PatchAttribute(image);

		workCanvas = new WorkCanvas(sashForm, SWT.DOUBLE_BUFFERED
				| SWT.H_SCROLL | SWT.V_SCROLL, attribute);
		previewCanvas = new PreviewCanvas(sashForm, SWT.DOUBLE_BUFFERED
				| SWT.H_SCROLL | SWT.V_SCROLL, attribute);

		sashForm.SASH_WIDTH = 5;
		sashForm.setWeights(new int[] { 1, 1 });

		Composite controlComposite = new Composite(container, SWT.NONE);
		controlComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		layout = new GridLayout(2, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		controlComposite.setLayout(layout);

		createControlBar(controlComposite);

		attribute.setPreviewCanvas(previewCanvas);
		attribute.setxPointLabel(xPointLabel);
		attribute.setyPointLabel(yPointLabel);

		return container;
	}

	private void createControlBar(Composite parent) {
		Composite controlBar = new Composite(parent, SWT.NONE);
		controlBar.setLayout(new GridLayout(6, false));
		controlBar.setLayoutData(new GridData(GridData.FILL_BOTH));

		Label zoomLabel = new Label(controlBar, SWT.NORMAL);
		zoomLabel.setText("Zoom:");
		Label minZoomValueLabel = new Label(controlBar, SWT.NORMAL);
		minZoomValueLabel.setText("100%");

		final Scale zoomScale = new Scale(controlBar, SWT.HORIZONTAL);
		zoomScale.setMaximum(800);
		zoomScale.setMinimum(100);
		zoomScale.setIncrement(100);
		zoomScale.setPageIncrement(100);

		zoomScale.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				int zoomValue = zoomScale.getSelection();
				attribute.setZoomValue(zoomValue);

				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						workCanvas.redrawZoom();
					}
				});

			}
		});
		Label maxZoomLabel = new Label(controlBar, SWT.NORMAL);
		maxZoomLabel.setText("800%");
		final Button showLockButton = new Button(controlBar, SWT.CHECK);
		showLockButton.setText("Show lock");
		showLockButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				attribute.setShowLock(showLockButton.getSelection());
			}
		});
		final Button showContentButton = new Button(controlBar, SWT.CHECK);
		showContentButton.setText("Show content");
		showContentButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				attribute.setShowContent(showContentButton.getSelection());
				previewCanvas.redraw();
			}
		});

		Label scaleLabel = new Label(controlBar, SWT.NORMAL);
		scaleLabel.setText("Patch scale:");

		Label minScaleValueLabel = new Label(controlBar, SWT.NORMAL);
		minScaleValueLabel.setText("2x");

		final Scale patchScale = new Scale(controlBar, SWT.HORIZONTAL);
		patchScale.setMinimum(2);
		patchScale.setMaximum(6);
		patchScale.setIncrement(1);
		patchScale.setPageIncrement(1);

		patchScale.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				int scaleValue = patchScale.getSelection();
				attribute.setScaleValue(scaleValue);
				Display.getDefault().asyncExec(new Runnable() {

					@Override
					public void run() {
						previewCanvas.redrawScale();

					}
				});
			}
		});

		Label maxScaleLabel = new Label(controlBar, SWT.NORMAL);
		maxScaleLabel.setText("6x");

		final Button showPatchButton = new Button(controlBar, SWT.CHECK);
		showPatchButton.setText("Show patch");
		showPatchButton.addListener(SWT.Selection, new Listener() {

			@Override
			public void handleEvent(Event event) {
				attribute.setShowPatch(showPatchButton.getSelection());
				workCanvas.redraw();
			}
		});

		Composite mouseInfoContainer = new Composite(parent, SWT.NONE);
		mouseInfoContainer.setLayout(new GridLayout(1, false));

		xPointLabel = new Label(mouseInfoContainer, SWT.NORMAL);
		xPointLabel.setText("X: " + currentX + " px");
		yPointLabel = new Label(mouseInfoContainer, SWT.NORMAL);
		yPointLabel.setText("Y: " + currentY + " px");
	}

	public Draw9PatchAttribute getAttribute() {
		return attribute;
	}

	public void dispose() {
		workCanvas.dispose();
		previewCanvas.dispose();
	}

}
