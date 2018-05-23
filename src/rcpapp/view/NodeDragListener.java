package rcpapp.view;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.DragSourceListener;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.part.EditorInputTransfer;

import rcpapp.editor.NodeEditor;
import rcpapp.editor.NodeEditorInput;
import rcpapp.model.Node;

public class NodeDragListener implements DragSourceListener {

	private final TreeViewer viewer;

	public NodeDragListener(TreeViewer viewer) {
		this.viewer = viewer;
	}

	@Override
	public void dragStart(DragSourceEvent event) {
		// TODO Auto-generated method stub
		System.out.println("Start Drag");
	}

	@Override
	public void dragSetData(DragSourceEvent event) {
		if (EditorInputTransfer.getInstance().isSupportedType(event.dataType)) { // if
																					// support
																					// EditorInputTransfer
			IStructuredSelection selection = viewer.getStructuredSelection();
			int i=0;
			EditorInputTransfer.EditorInputData[] arrData=new EditorInputTransfer.EditorInputData[selection.size()];
			Iterator<?> iterator=selection.iterator();
			while(iterator.hasNext()){
				Node node=(Node)iterator.next();
				IEditorInput input = new NodeEditorInput(node);
				arrData[i]=EditorInputTransfer.createEditorInputData(NodeEditor.ID, input);
				i++;
			}
			event.data=arrData;
		}
	}

	@Override
	public void dragFinished(DragSourceEvent event) {
		if (event.detail == DND.DROP_MOVE) {
			System.out.println("Finished Drag");
		}
	}
}
