/*

*/
class Node{
	Node next;
	int value;
	Node(int value,Node next){
		this.value=value;
		this.next=null;
	}
	Node(int value){
		this.value=value;
		this.next=null;
	}
}
class ReverseLinkedList{
	static Node convertArr2LL(int arr[]){
		Node head=new Node(arr[0]);
		Node mover=head;
		for(int i=1;i<arr.length;i++){
			Node temp=new Node(arr[i]);
			mover.next=temp;
			mover=temp;
		}

		return head;
	}
	static Node reverseLL(Node head){
		Node temp=head;
		Node prev=null;

		while(temp!=null){
			Node front=temp.next;
			temp.next=prev;
			prev=temp;
			temp=front;
		}
		return prev;
	}
	static void printLL(Node head){
		Node temp=head;
		while(temp!=null){
			System.out.print(temp.value+" ");
			temp=temp.next;
		}
	}
	public static void main(String[] args) {
		int arr[]={1,2,3,4,5};
		Node head=convertArr2LL(arr);
		Node temp=reverseLL(head);
		printLL(temp);
	}
}
/*
OUTPUT
5 4 3 2 1
*/