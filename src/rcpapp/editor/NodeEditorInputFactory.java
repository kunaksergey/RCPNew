package rcpapp.editor;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

import rcpapp.controller.TreeController;
import rcpapp.model.Node;

public class NodeEditorInputFactory implements IElementFactory {

	@Override
	public IAdaptable createElement(IMemento memento) {
		Node node=TreeController.getInstance().findNodeByFullPath(memento.getString(NodeEditorInput.FEATURE_ID));	
		NodeEditorInput input= new NodeEditorInput(node);
		return input;
	}

}
