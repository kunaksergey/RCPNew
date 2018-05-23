package rcpapp.view;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.handlers.IHandlerService;
import org.eclipse.ui.part.EditorInputTransfer;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.services.ISourceProviderService;

import rcpapp.command.EditNodeHandler;
import rcpapp.controller.TreeController;
import rcpapp.model.IChangeModelListener;
import rcpapp.model.Node;

public class TreeView extends ViewPart implements IChangeModelListener {
	private TreeViewer viewer;

	@Override
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		viewer.setContentProvider(new TreeContentProvider());
		viewer.getTree().setHeaderVisible(true);
		getSite().setSelectionProvider(viewer);

		TreeViewerColumn mainColumn = new TreeViewerColumn(viewer, SWT.NONE);
		mainColumn.getColumn().setText("Node");
		mainColumn.getColumn().setWidth(300);
		mainColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(new TreeLabelProvider()));

		TreeController treeController = TreeController.getInstance();

		// add data to tree
		viewer.setInput(treeController.listNode());

		treeController.addChangeModelListener(this);

		// the viewer field is an already configured TreeViewer
		Tree tree = viewer.getTree();

		// Drag and Drop
		int operations =  DND.DROP_COPY | DND.DROP_MOVE ;
		Transfer[] transferTypes = new Transfer[] { EditorInputTransfer.getInstance() };
		viewer.addDragSupport(operations, transferTypes, new NodeDragListener(viewer));
		// !Drag and Drop

		tree.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TreeItem item = (TreeItem) e.item;
				Node node = (Node) item.getData();

				ISourceProviderService service = (ISourceProviderService) PlatformUI.getWorkbench()
						.getService(ISourceProviderService.class);

				CommandStateProvider sourceProvider = (CommandStateProvider) service.getSourceProvider("isFolder");

				// if it's not folder-disable icons
				if (node.isFolder()) {
					sourceProvider.setFolder();
				} else {
					sourceProvider.setFile();
				}
			}
		});

		viewer.addDoubleClickListener((event) -> {
			IHandlerService handlerService = getSite().getService(IHandlerService.class);
			try {
				handlerService.executeCommand(EditNodeHandler.ID, null);
			} catch (Exception ex) {
				throw new RuntimeException(ex.getMessage());
			}
		});
	}

	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	@Override
	public void changeModel() {
		viewer.refresh();
	}

}
