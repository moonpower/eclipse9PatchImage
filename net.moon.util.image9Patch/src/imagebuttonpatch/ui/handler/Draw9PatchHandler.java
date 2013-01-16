package imagebuttonpatch.ui.handler;

import imagebuttonpatch.ui.Draw9PatchDialog;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.ImageLoader;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.handlers.HandlerUtil;

public class Draw9PatchHandler extends AbstractHandler {

	private IFile file;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchPart activePart = HandlerUtil.getActivePart(event);
		Shell shell = activePart.getSite().getShell();
		IStructuredSelection selection = (IStructuredSelection) HandlerUtil
				.getShowInSelection(event);
		Object firstElement = selection.getFirstElement();
		if (firstElement instanceof IFile) {
			file = (IFile) firstElement;
		}

		Draw9PatchDialog dialog = new Draw9PatchDialog(shell, file);
		if (dialog.open() == IDialogConstants.OK_ID) {
			Image image = dialog.getAttribute().getImage();
			String fileLocation = file.getLocation().toOSString();
			String fileName = file.getName();
			if (!file.getName().endsWith(".9.png")) {
				int lastIndexOf = file.getName().lastIndexOf(".");
				String substring = file.getName().substring(0, lastIndexOf);
				fileName = substring + ".9.png";
				IPath removeLastSegments = file.getLocation()
						.removeLastSegments(1).append(fileName);
				fileLocation = removeLastSegments.toString();

			}
			ImageLoader imageLoader = new ImageLoader();
			imageLoader.data = new ImageData[] { image.getImageData() };

			imageLoader.save(fileLocation, SWT.IMAGE_PNG);

			refresh(fileName);
		}
		dialog.dispose();
		return null;
	}

	private void refresh(String fileName) {
		IPath pathss = new Path(file.getFullPath().removeLastSegments(1) + "/"
				+ fileName);

		IFile file2 = ResourcesPlugin.getWorkspace().getRoot().getFile(pathss);
		try {
			file2.refreshLocal(IResource.DEPTH_ZERO, null);
		}
		catch (CoreException e) {
			e.printStackTrace();
		}
	}
}
