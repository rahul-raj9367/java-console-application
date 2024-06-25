/*
Input  : "abc de"
Output : edc ba

Input : "intern at geeks"
Output : skeegt an retni

Input : "Help others"
Output : sreh topleH
*/
class Reverseastringpreservingspacepositions{
	public static void main(String[] args) {
	 	System.out.println(reverse("abc de"));
	}

	static String reverse(String s){
		char charArray[]=s.toCharArray();

		helper(charArray,0,charArray.length-1);

		return new String(charArray);
	}

	static void helper(char s[],int start,int end){
		//base case
		if(start>=end){
			return;
		}

		if(s[start]==' '){
			helper(s,start+1,end);
			return;
		}

		if(s[end]==' '){
			helper(s,start,end-1);
			return;
		}

		char temp=s[start];
		s[start]=s[end];
		s[end]=temp;


		helper(s,start+1,end-1);
	}
}