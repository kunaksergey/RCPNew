package rcpapp.service;

import java.util.List;

import rcpapp.model.Node;

public class LocalTreeService implements ITreeService {
	private Node root = createNewTree();

	@Override
	public Node createNewTree() {
		Node root = new Node();
		root.setFolder(false);
		root.setName("/");

		Node firstFolder = new Node();
		firstFolder.setFolder(true);
		firstFolder.setName("first folder");
		
		Node secondFolder = new Node();
		secondFolder.setFolder(true);
		secondFolder.setName("second folder");

		Node firstFile = new Node();
		firstFile.setFolder(false);
		firstFile.setName("one file");

		Node secondFile = new Node();
		secondFile.setFolder(false);
		secondFile.setName("two file");
		
		Node thirdFile = new Node();
		thirdFile.setFolder(false);
		thirdFile.setName("third file");
		
		Node forthFile = new Node();
		forthFile.setFolder(false);
		forthFile.setName("forth file");
		secondFolder.addChield(thirdFile);
		secondFolder.addChield(forthFile);
		firstFolder.addChield(secondFile);
		firstFolder.addChield(secondFolder);
		root.addChield(firstFolder);
		root.addChield(firstFile);
		return root;
	}

	@Override
	public void addChield(Node parent, Node chield) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeChield(Node parent, Node chield) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Node> getListRoots() {
		// TODO Auto-generated method stub
		return root.getChields();
	}

	@Override
	public void remove(Node node) {
		// TODO Auto-generated method stub
		Node parent=node.getParent();
		parent.getChields().remove(node);
	}

	@Override
	public Node getRoot() {
		// TODO Auto-generated method stub
		return root;
	}

}
