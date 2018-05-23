package rcpapp.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.ui.part.EditorInputTransfer;

import rcpapp.model.Node;

public class TestTable {
	private Table dropTable;
	private TableItem item;

	public TestTable(Composite composite) {
		dropTable = new Table(composite, SWT.BORDER);
		item = new TableItem(dropTable, SWT.NONE);
		item.setText("Name");
		
		int operations = DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT;
		DropTarget target = new DropTarget(dropTable, operations);
		
		Transfer[] types = new Transfer[] {TextTransfer.getInstance()};
		target.setTransfer(types);

		target.addDropListener(new DropTargetListener(){

			@Override
			public void dragEnter(DropTargetEvent event) {
				// TODO Auto-generated method stub
			
			}

			@Override
			public void dragLeave(DropTargetEvent event) {
				// TODO Auto-generated method stub
				System.out.println("drag leave");
			}

			@Override
			public void dragOperationChanged(DropTargetEvent event) {
				// TODO Auto-generated method stub
				System.out.println("drag change");
			}

			@Override
			public void dragOver(DropTargetEvent event) {
			
			}

			@Override
			public void drop(DropTargetEvent event) {
				// TODO Auto-generated method stub
				System.out.println("data set");
				item.setText((String)event.data);
			}

			@Override
			public void dropAccept(DropTargetEvent event) {
				// TODO Auto-generated method stub
				System.out.println("drop accept");
			}});
	}

}
