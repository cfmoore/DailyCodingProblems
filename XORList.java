
public class XORList {

	private Node head;
	
	static class Node
	{
		int data;
		int index;
		Node prev;
		Node next;
		Node both;
		
		Node(int d)
		{
			data = d;
			prev = null;
			next = null;
			both = null;
		}
	}
	
	public XORList add(XORList list, int data)
	{
		Node node = new Node(data);
		
		if(list.head == null)
		{
			list.head = node;
			node.index = 0;
		}
		else { 
            // Else traverse till the last node 
            // and insert the new_node there 
            Node last = list.head;
            System.out.println(last.getClass());
            System.out.println(last);
            System.out.println(node);
            //System.out.println(node^last);
            while (last.next != null) { 
                last = last.next; 
            } 
  
            // Insert the new_node at last node 
            node.prev = last;
            last.next = node;
            node.index = last.index+1;
        } 
  
        // Return the list by head 
        return list; 
    } 
	
	public void printer(XORList list)
	{
		Node print = list.head;
		while(print!=null)
		{
			System.out.println(print+" "+print.data);
			print = print.next;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		XORList list = new XORList();
		list.add(list, 42);
		list.add(list, 63);
		list.printer(list);
	}

}
