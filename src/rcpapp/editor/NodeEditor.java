package rcpapp.editor;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ExtendedModifyEvent;
import org.eclipse.swt.custom.ExtendedModifyListener;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.ISources;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.part.EditorPart;

import rcpapp.controller.TreeController;
import rcpapp.model.Node;

public class NodeEditor extends EditorPart {
	public static final String ID = "rcpapp.view.NodeEditor";
	private static final int MAX_STACK_SIZE = 25;
	private List<String> undoStack;
    private List<String> redoStack;
	private boolean dirty = true;
	private NodeEditorInput input;
	private Node node;
	private Text text;

	public NodeEditor() {

	}

	@Override
	public void init(IEditorSite site, IEditorInput input) throws PartInitException {
		// TODO Auto-generated method stub
		setSite(site);
		setInput(input);
		this.input = (NodeEditorInput) input;
		node = this.input.getNode();
		setPartName(node.fullPath());
	}

	@Override
	public void createPartControl(Composite parent) {
		// TODO Auto-generated method stub
		undoStack = new LinkedList<String>();
	    redoStack = new LinkedList<String>();
		GridLayout layout = new GridLayout();
		layout.numColumns = 1;
		parent.setLayout(layout);
		new Label(parent, SWT.NONE).setText(this.input.getNode().getName());
		text = new Text(parent, SWT.BORDER);
		text.setText(node.getName());
		undoStack.add(0, node.getName());
		text.setLayoutData(new GridData(SWT.FILL, SWT.BEGINNING, true, false));
		addListeneres();
	}

	private void addListeneres() {
		// TODO Auto-generated method stub
		
		text.addModifyListener(new ModifyListener(){
		      public void modifyText(ModifyEvent event) {
		          // Get the widget whose text was modified
		    	  String currText =text.getText();
		    	  if (currText != null && currText.length() > 0) {
		              if (undoStack.size() == MAX_STACK_SIZE) {
		                undoStack.remove(undoStack.size() - 1);
		              }
		              undoStack.add(0, currText);
		            }
		        }
		      });
		
		text.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				switch (e.keyCode) {
				case SWT.F1:
					undo();
					break;
				case SWT.F2:
					redo();
					break;
				default:
					// ignore everything else
				}
			}
		});

	}

	@Override
	public void doSave(IProgressMonitor monitor) {
		// TODO Auto-generated method stub

		// If node is NEW
		if (!node.getParent().hasChield(node)) {
			TreeController.getInstance().addAsChield(node);
		}
		node.setName(text.getText());
		TreeController.getInstance().changedModel();
		setDirty(false);
		monitor.done();
	}

	@Override
	public void doSaveAs() {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return dirty;
	}

	@Override
	public boolean isSaveAsAllowed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFocus() {
		text.setFocus();
	}

	private void setDirty(boolean dirty) {
		if (this.dirty != dirty) {
			this.dirty = dirty;
			// Notify PROP_DIRTY changes to Workbench.
			this.firePropertyChange(IEditorPart.PROP_DIRTY);
		}
	}
	
	private void undo() {
		System.out.println(undoStack.size());
		 if (undoStack.size() > 1) {
			 redoStack.add(0,(String) undoStack.remove(0));
			 String lastEdit = (String) undoStack.remove(0);
			 text.setText(lastEdit);
		    }
		 moveCursorToEnd();
	  }

	  private void redo() {
		  if (redoStack.size() > 0) {
			  String lastEdit = (String) redoStack.remove(0);
			  text.setText(lastEdit);
		    }
		  moveCursorToEnd();
	  }
	  
	  private void moveCursorToEnd() {
		  text.setSelection(text.getText().length());
		  }

}
