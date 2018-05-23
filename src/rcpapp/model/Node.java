package rcpapp.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

public class Node {
	private String name;
	private Node parent;
	private boolean isFolder;
	private List<Node> chields=new ArrayList<>();
	
	public Node() {
	}
	public Node(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public Node getParent() {
		return parent;
	}
	public void setParent(Node parrent) {
		this.parent = parrent;
	}
	public boolean isFolder() {
		return isFolder;
	}
	
	public boolean hasChield(Node chield) {
		return this.getChields().stream().filter(c->c.equals(chield)).findFirst().isPresent();
	}
	
	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}
	public List<Node> getChields() {
		return chields;
	}
	public void setChields(List<Node> chields) {
		this.chields = chields;
	}
	public void addChield(Node chield) {
		chield.setParent(this);
		chields.add(chield);
	}
	
	public String fullPath(){
		StringBuilder path=new StringBuilder("");
		List<String> listPath=new ArrayList<>();
		Node currentNode=this;
		
		while(currentNode.getParent()!=null){
			listPath.add(currentNode.getParent().getName());
			currentNode=currentNode.getParent();
		}
		
		ListIterator<String> itr=listPath.listIterator(listPath.size());
		itr.previous();
		path.append("/");
		while(itr.hasPrevious()){
			String previous=itr.previous();
			path.append(previous);
			path.append("/");
			}
			path.append(name);
		return path.toString();
	}


	

	
}
