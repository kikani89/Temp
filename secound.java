import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;

class Node {
	int data;
	Node left, right;

	Node(int value) {
		data = value;
		left = right = null;
	}
}

public class BT_NoParentPtr_Solution1 
{

	Node root;
	private List<Integer> path1 = new ArrayList<>();
	private List<Integer> path2 = new ArrayList<>();

	int findLCA(int n1, int n2) {
		path1.clear();
		path2.clear();
		return findLCAInternal(root, n1, n2);
	}

	private int findLCAInternal(Node root, int n1, int n2) {

		if (!findPath(root, n1, path1) || !findPath(root, n2, path2)) {
			System.out.println((path1.size() > 0) ? "n1 is present" : "n1 is missing");
			System.out.println((path2.size() > 0) ? "n2 is present" : "n2 is missing");
			return -1;
		}

		int i;
		for (i = 0; i < path1.size() && i < path2.size(); i++) {
		// System.out.println(path1.get(i) + " " + path2.get(i));
			if (!path1.get(i).equals(path2.get(i)))
				break;
		}

		return path1.get(i-1);
	}

	private boolean findPath(Node root, int n, List<Integer> path)
	{
		if (root == null) {
			return false;
		}

		path.add(root.data);

		if (root.data == n) {
			return true;
		}

		if (root.left != null && findPath(root.left, n, path)) {
			return true;
		}

		if (root.right != null && findPath(root.right, n, path)) {
			return true;
		}

		path.remove(path.size()-1);

		return false;
	}
	
	int getLevelUtil(Node node, int data, int level) 
    {
        if (node == null)
            return 0;
  
        if (node.data == data)
            return level;
  
        int downlevel = getLevelUtil(node.left, data, level + 1);
        if (downlevel != 0)
            return downlevel;
  
        downlevel = getLevelUtil(node.right, data, level + 1);
        return downlevel;
    }
  
    /* Returns level of given data value */
    int getLevel(Node node, int data) 
    {
        return getLevelUtil(node, data, 1);
    }

	public static void main(String[] args)
	{
		BT_NoParentPtr_Solution1 tree = new BT_NoParentPtr_Solution1();
		Queue<Node> q = new LinkedList<>();
		
		 Scanner scanner = new Scanner(System.in);
	
        int height = Integer.parseInt(scanner.next());
        int a1 = Integer.parseInt(scanner.next());
        int a2 = Integer.parseInt(scanner.next());
        

		/*create root*/
		tree.root = new Node(0);
		q.add(tree.root);
		
		int cnt = 1;
		for (int i = 0; i <= height; i++) {
		    int nodeCount = q.size();
		    if (nodeCount == 0){
		        break;
		    }
		    while (nodeCount > 0) 
		    {
				Node newnode = q.peek();
				q.remove();
				newnode.left = new Node(cnt);
				q.add(newnode.left);
				cnt++;
				newnode.right = new Node(cnt);
				q.add(newnode.right);
				cnt++;
				nodeCount--;
			}
		}
        int lca = tree.findLCA(a1,a2);
        int level = tree.getLevel( tree.root, lca );
		System.out.println( lca + " " + ( --level ) );
	}
}