package imagebuttonpatch;

import imagebuttonpatch.generate.NinePatchImage2;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import shared.SharedImages;

public class CopyOfTestImage {
	public CopyOfTestImage() {
		Display display = Display.getDefault();
		Shell shell = new Shell(display, SWT.SHELL_TRIM);

		shell.setLayout(new FillLayout());
		createComposite(shell);
		shell.setSize(500, 500);
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
		shell.dispose();

	}

	private void createComposite(Composite parent) {
		Composite container = new Composite(parent, SWT.NORMAL);
		GridLayout layout = new GridLayout(1, false);

		container.setLayout(layout);
		Button button1 = new Button(container, SWT.PUSH);
		button1.setText("¹öÆ°");
		button1.setLayoutData(new GridData(GridData.FILL_BOTH));

		Image image = SharedImages.getImage(SharedImages.SAMPLE_BUTTON_9);

		Composite createComposite = NinePatchImage2.createComposite(container,
				SWT.NORMAL, new GridLayout(3, false), image);

		createComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		for (int i = 0; i < 3; i++) {
			Button button = new Button(createComposite, SWT.PUSH);
			button.setText("dd");
		}

	}

	public static void main(String[] args) {
		new CopyOfTestImage();
	}
}
