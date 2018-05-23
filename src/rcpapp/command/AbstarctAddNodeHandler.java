package rcpapp.command;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;

import rcpapp.editor.NodeEditor;
import rcpapp.editor.NodeEditorInput;
import rcpapp.model.Node;

abstract public class AbstarctAddNodeHandler extends AbstractHandler {

	abstract public boolean isFolder();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
		IWorkbenchPage page = window.getActivePage();

		// get the selection
		ISelection selection = HandlerUtil.getCurrentSelection(event);
		if (selection != null && selection instanceof IStructuredSelection) {
			Object obj = ((IStructuredSelection) selection).getFirstElement();
			// if we had a selection lets open the editor
			if (obj != null) {
				try {
					Node parent = (Node) obj;
					Node node = new Node("new");
					node.setParent(parent);
					node.setFolder(isFolder());
					IEditorInput input = new NodeEditorInput(node);
					page.openEditor(input, NodeEditor.ID);
				} catch (PartInitException e) {
					throw new RuntimeException(e);
				}
			}
		}
		return null;
	}
}
