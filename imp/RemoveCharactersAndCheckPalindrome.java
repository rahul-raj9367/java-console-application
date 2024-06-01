/*

*/
import java.util.*;
class RemoveCharactersAndCheckPalindrome {
    static boolean canBePalindrome(String s,int k){
        return helper(s,0,s.length()-1,k);
    }

    static boolean helper(String s,int left,int right,int k){
        //base case 
        if(left>=right){
            return true;
        }

        if(s.charAt(left)==s.charAt(right)){
            return helper(s,left+1,right-1,k);
        }

        if(k==0) return false;

        return helper(s,left+1,right,k-1) || helper(s,left,right-1,k-1);
    }
    public static void main(String[] args) {
        String s="waterrfretawx";
        int k=3;
        boolean result=canBePalindrome(s,k);
        System.out.println(result);
    }
}
