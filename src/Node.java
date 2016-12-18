
public class Node {
	int length;
	Node [] edges =new Node[256];
	Node suffixLink=null;
	public Node (int length){
		this.length = length;
	}
}
