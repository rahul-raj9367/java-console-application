/*
A valid IP address consists of exactly four integers separated by single dots. Each integer is between 0 and 255 (inclusive) and cannot have leading zeros.

For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses, but "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.
Given a string s containing only digits, return all possible valid IP addresses that can be formed by inserting dots into s. You are not allowed to reorder or remove any digits in s. You may return the valid IP addresses in any order.

 

Example 1:
Input: s = "25525511135"
Output: ["255.255.11.135","255.255.111.35"]
Example 2:

Input: s = "0000"
Output: ["0.0.0.0"]
Example 3:

Input: s = "101023"
Output: ["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
*/
import java.util.*;
class RestoreIPAddresses{
	static List<String> findRestoreIPAddresses(String s){
		List<String> list=new ArrayList<>();
		int length=s.length();

		for(int i=1; i<4 && i<s.length()-2;i++){
			for(int j=i+1; j<i+4 && j<s.length()-1;j++){
				for(int k=j+1; k<j+4 && k<s.length();k++){
					String s1=s.substring(0,i);
					String s2=s.substring(i,j);
					String s3=s.substring(j,k);
					String s4=s.substring(k,length);

					if(isValid(s1) && isValid(s2) && isValid(s3) && isValid(s4)){
						list.add(s1+"."+s2+"."+s3+"."+s4);
					}

				}
			}
		}

		return list;
	}
	static boolean isValid(String s){
		if(s.length()>3 || s.length()==0 || (s.charAt(0)=='0' && s.length()>1) || Integer.parseInt(s)>255){
			return false;
		}
		return true;
	}
	public static void main(String[] args) {
		String s = "25525511135";
		System.out.println(findRestoreIPAddresses(s));
	}
}
/*
[255.255.11.135, 255.255.111.35]
*/