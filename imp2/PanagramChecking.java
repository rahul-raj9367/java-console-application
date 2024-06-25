/*
Given a string s check if it is "Panagram" or not.

A "Panagram" is a sentence containing every letter in the English Alphabet.

Example 1:

Input:
s = "Bawds jog, flick quartz, vex nymph"
Output: 
1
Explanation: 
In the given input, there
are all the letters of the English
alphabet. Hence, the output is 1.
*/
class PanagramChecking{
	static boolean findPanagramChecking(String s){
		s=s.replaceAll("[^a-zA-Z0-9]","").toLowerCase();

		int freq[]=new int[26];
		for(char c:s.toCharArray()){
			freq[c-'a']++;
		}

		int count=0;
		for(int i=0;i<freq.length;i++){
			if(freq[i]>=1){
				count++;
			}
		}

		if(count==26){
			return true;
		}else{
			return false;
		}
	}
	public static void main(String[] args) {
		String s="Bawds jog, flick quartz, vex nymph";
		System.out.println(findPanagramChecking(s));
	}
}