package rcpapp.view;

import java.net.URL;

import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.LocalResourceManager;
import org.eclipse.jface.resource.ResourceManager;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import rcpapp.Application;
import rcpapp.model.Node;

public class TreeLabelProvider extends LabelProvider implements IStyledLabelProvider {
	private ResourceManager resourceManager = new LocalResourceManager(JFaceResources.getResources());
	private final ImageDescriptor FOLDER_DESCRIPTOR = createImageDescriptor("icons/folder.png");
	private final ImageDescriptor FILE_DESCRIPTOR = createImageDescriptor("icons/page.png");

	@Override
	public StyledString getStyledText(Object element) {
		// TODO Auto-generated method stub
		Node node = ((Node) element);
		StyledString styledString = new StyledString(node.getName());
		return styledString;
	}

	@Override
	public Image getImage(Object element) {
		return ((Node) element).isFolder() ? getResourceManager().createImage(FOLDER_DESCRIPTOR)
				: getResourceManager().createImage(FILE_DESCRIPTOR);

	}

	private ImageDescriptor createImageDescriptor(String fileName) {
		Bundle bundle = FrameworkUtil.getBundle(TreeLabelProvider.class);
		URL url = FileLocator.find(bundle, new Path(fileName), null);
		return ImageDescriptor.createFromURL(url);
	}

	protected ResourceManager getResourceManager() {
		if (resourceManager == null) {
			resourceManager = new LocalResourceManager(JFaceResources.getResources());
		}
		return resourceManager;
	}

	@Override
	public void dispose() {
		// garbage collect system resources
		if (resourceManager != null) {
			resourceManager.dispose();
			resourceManager = null;
		}
	}
}
