package rcpapp.view;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;

import rcpapp.model.Node;

public class TreeContentProvider implements ITreeContentProvider {

    @Override
    public Object getParent(Object element) {
        return  ((Node)element).getParent();
    }

    @Override
    public Object[] getElements(Object inputElement) {
        return ArrayContentProvider.getInstance().getElements(inputElement);
    }

    @Override
    public Object[] getChildren(Object parentElement) {
        return ((Node)parentElement).getChields().toArray();
    }
    
    @Override
    public boolean hasChildren(Object element) {
        Node file = (Node) element;
        if (file.isFolder()) {
            return true;
        }
        return false;
    }
}
