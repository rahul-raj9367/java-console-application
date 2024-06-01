/*
Given a string s, find the length of the longest 
substring
 without repeating characters.

Example 1:
Input: s = "abcabcbb"
Output: 3
Explanation: The answer is "abc", with the length of 3.

Example 2:
Input: s = "bbbbb"
Output: 1
Explanation: The answer is "b", with the length of 1.

Example 3:
Input: s = "pwwkew"
Output: 3
Explanation: The answer is "wke", with the length of 3.
Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/
import java.util.*;
class LongestSubstringWithoutRepeatingCharacters{
	static int findLongestSubstringWithoutRepeatingCharacters(String s){
		int n=s.length();
		int charIndex[]=new int[128];
		int maxLength=0;
		//
		Arrays.fill(charIndex,-1);
		int left=0;

		for(int right=0;right<n;right++){
			if(charIndex[s.charAt(right)]>=left){
				left=charIndex[s.charAt(right)]+1;
			}
			charIndex[s.charAt(right)]=right;
			maxLength=Math.max(maxLength,right-left+1);
		}
		return maxLength;
	}
	public static void main(String[] args) {
		String s="abcabcbb";
		System.out.println(findLongestSubstringWithoutRepeatingCharacters(s));
	}
}