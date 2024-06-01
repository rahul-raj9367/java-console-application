/*

*/
import java.util.*;
class Node{
	int start,end,pos;
	Node(int start,int end,int pos){
		this.start=start;
		this.end=end;
		this.pos=pos;
	}
}
class Nmeetingsinoneroom{
	static int maxMeetings(int start[],int end[],int n){
		Node arr[]=new Node[n];
		for(int i=0;i<n;i++){
			arr[i]=new Node(start[i],end[i],i+1);
		}

		
		Arrays.sort(arr,new Comparator<Node>(){
			public int compare(Node a,Node b){
				if(a.end==b.end) return a.start-b.start;
				return a.end-b.end;
			}
		});


		int beg=-1,count=0;
		ArrayList<Integer> result=new ArrayList<>();
		for(int i=0;i<n;i++){
			if(arr[i].start>beg){
				result.add(arr[i].pos);
				beg=arr[i].end;
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		int[] start = {1, 3, 0, 5, 8, 5};
        int[]   end = {2, 4, 6, 7, 9, 9};
        int n = start.length;

        // Calling the maxMeetings method and printing the result
        int maxMeetingsAttended = maxMeetings(start, end, n);
        System.out.println("Maximum number of meetings that can be attended: " + maxMeetingsAttended);
	}
}