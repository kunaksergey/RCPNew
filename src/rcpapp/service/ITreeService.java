package rcpapp.service;
import java.util.List;

import rcpapp.model.Node;

public interface ITreeService {
	public Node createNewTree();
	public void addChield(Node parent,Node chield);
	public void removeChield(Node parent,Node chield);
	public Node getRoot();
	public void remove(Node node);
	public List<Node> getListRoots();
}
