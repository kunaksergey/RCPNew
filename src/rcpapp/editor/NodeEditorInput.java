package rcpapp.editor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

import rcpapp.model.Node;

public class NodeEditorInput implements IEditorInput{
	private final static String FACTORY_ID = "rcpapp.NodeEditorInputFactory";
	public final static String FEATURE_ID = "featureId";
	 private final Node node;

	public NodeEditorInput(Node node) {
	       this.node = node;
	 }
	
	    
	public Node getNode(){
		return this.node;
	} 
	
	@Override
	public <T> T getAdapter(Class<T> adapter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean exists() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public ImageDescriptor getImageDescriptor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return node.getName();
	}


	@Override
	public String getToolTipText() {
		// TODO Auto-generated method stub
		return node.getName();
	}


	@Override
	public IPersistableElement getPersistable() {
		return new IPersistableElement() {
            public String getFactoryId() {
                return FACTORY_ID;
            }

            public void saveState(IMemento memento) {
                memento.putString(FEATURE_ID, node.fullPath());
            }
        };
	}



}
