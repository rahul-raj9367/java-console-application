/*
Given a string s and two words w1 and w2 that are present in S. The task is to find the minimum distance between w1 and w2. Here, distance is the number of steps or words between the first and the second word. 

Examples: 

Input : s = "geeks for geeks contribute practice", w1 = "geeks", w2 = "practice"
Output : 1 
There is only one word between the closest occurrences of w1 and w2.

Input : s = "the quick the brown quick brown the frog", w1 = "quick", w2 = "frog"
Output : 2
*/
class MinimumDistanceBetweenWordsofaString{
	static int findMinimumDistanceBetweenWordsofaString(String s,String w1,String w2){
		String sp[]=s.split(" ");
		int minDistance=Integer.MAX_VALUE;
		int lastPosW1 = -1;
        int lastPosW2 = -1;

        for(int i=0;i<sp.length;i++){
        	if(sp[i].equals(w1)){
        		lastPosW1=i;
        		if(lastPosW2!=-1){
        			minDistance=Math.min(minDistance,lastPosW1-lastPosW2-1);
        		}
        	}else if(sp[i].equals(w2)){
        		lastPosW2=i;
        		if(lastPosW1!=-1){
        			minDistance=Math.min(minDistance,lastPosW2-lastPosW1-1);
        		}
        	}
        }

        return (minDistance==Integer.MAX_VALUE) ?-1:minDistance;


        //brute force
        // for(int i=0;i<sp.length-1;i++){
        // 	for(int j=i+1;j<sp.length;j++){
        // 		if(sp[i].equals(w1) && sp[j].equals(w1)){
        // 			minDistance=Math.min(minDistance,j-i-1);
        // 		}
        // 	}
        // }

        // return minDistance;
	}
	public static void main(String[] args) {
		String s ="geeks for geeks contribute practice", w1 = "geeks",w2 = "practice";
		System.out.println(findMinimumDistanceBetweenWordsofaString(s,w1,w2));
	}
}