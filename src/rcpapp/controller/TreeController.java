package rcpapp.controller;

import java.util.ArrayList;
import java.util.List;

import rcpapp.model.IChangeModelListener;
import rcpapp.model.Node;
import rcpapp.service.ITreeService;
import rcpapp.service.LocalTreeService;

public class TreeController {
	private static TreeController treeController;
	private ITreeService service = new LocalTreeService();
	private List<IChangeModelListener> listeners = new ArrayList<>();

	private TreeController() {

	}

	public static TreeController getInstance() {
		if (treeController == null) {
			treeController = new TreeController();
		}
		return treeController;
	}

	public void setService(ITreeService service) {
		this.service = service;
	}

	public List<Node> listNode() {
		return service.getListRoots();
	}

	public Node findNodeByFullPath(String fullPath) {
		return findNodeByPath(getRoot(), fullPath.substring(1, fullPath.length()));
	}

	public Node findNodeByPath(Node node, String path) {
		boolean isPoint = (path.indexOf("/") == -1) ? true : false; //true-it's target
		String name=path.split("/")[0]; //name node 
		Node currentNode=node.getChields()
			.stream()
			.filter(c->c.getName().equals(name))
			.findFirst().orElse(null);
		return (isPoint)?currentNode:findNodeByPath(currentNode,path.substring(name.length()+1, path.length()));
		
	}

	public void remove(Node node) {
		service.remove(node);
		changedModel();
	}

	public Node getRoot() {
		return service.getRoot();
	}

	public void addAsChield(Node chield) {
		if (chield.getParent() != null) {
			chield.getParent().addChield(chield);
			changedModel();
		}
	}

	public void addAsChield(Node parent, Node chield) {
		if (parent != null && chield != null)
			chield.setParent(parent);
		parent.addChield(chield);
		changedModel();
	}

	public void changedModel() {
		listeners.forEach(l -> l.changeModel());
	}

	public void addChangeModelListener(IChangeModelListener listener) {
		listeners.add(listener);
	}
}
