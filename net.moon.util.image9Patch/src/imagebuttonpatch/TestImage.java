package imagebuttonpatch;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import shared.SharedImages;

public class TestImage {
	public TestImage() {
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
		container.setLayout(new GridLayout(1, false));
		final Composite composite = new Composite(container, SWT.NORMAL);
		GridLayout layout = new GridLayout(3, false);
		layout.marginHeight = 0;
		layout.marginWidth = 0;
		composite.setLayout(layout);
		GridData layoutData = new GridData(GridData.FILL_HORIZONTAL);
		composite.setLayoutData(layoutData);
		composite.setBackgroundMode(SWT.INHERIT_FORCE);
		final Image image = SharedImages.getImage(SharedImages.TEST_BUTTON);
		composite.addListener(SWT.Resize, new Listener() {

			@Override
			public void handleEvent(Event event) {
				Rectangle rect = composite.getBounds();

			}
		});

		for (int i = 0; i < 3; i++) {
			Button button = new Button(composite, SWT.NORMAL);
			button.setText("test");

		}
		Label label = new Label(composite, SWT.NORMAL);
		label.setBackgroundImage(image);
		label.setText("ddddddddddddddddd");
	}

	public static void main(String[] args) {
		new TestImage();
	}
}
