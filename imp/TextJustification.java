/*
Given an array of strings words and a width maxWidth, format the text such that each line has exactly maxWidth characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly maxWidth characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line does not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left-justified, and no extra space is inserted between words.

Note:

A word is defined as a character sequence consisting of non-space characters only.
Each word's length is guaranteed to be greater than 0 and not exceed maxWidth.
The input array words contains at least one word.
 

Example 1:

Input: words = ["This", "is", "an", "example", "of", "text", "justification."], maxWidth = 16
Output:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Example 2:

Input: words = ["What","must","be","acknowledgment","shall","be"], maxWidth = 16
Output:
[
  "What   must   be",
  "acknowledgment  ",
  "shall be        "
]
Explanation: Note that the last line is "shall be    " instead of "shall     be", because the last line must be left-justified instead of fully-justified.
Note that the second line is also left-justified because it contains only one word.
Example 3:

Input: words = ["Science","is","what","we","understand","well","enough","to","explain","to","a","computer.","Art","is","everything","else","we","do"], maxWidth = 20
Output:
[
  "Science  is  what we",
  "understand      well",
  "enough to explain to",
  "a  computer.  Art is",
  "everything  else  we",
  "do                  "
]
*/

//sliding window technique
import java.util.*;
class TextJustification{
  static void fullJustify(String words[],int maxWidth){
    List<String> result=new ArrayList<>();
    int index=0;

    while(index<words.length){
        int count=words[index].length();
        int last=index+1;

        while(last<words.length){
          if(count+words[last].length()+1>maxWidth) break;
          count+=words[last].length()+1;
          last++;
        }

        StringBuilder sb=new StringBuilder();
        sb.append(words[index]);

        int diff=last-index-1;

        if(last==words.length || diff==0){
          for(int i=index+1;i<last;i++){
            sb.append(" ").append(words[i]);
          }
          for(int i=count;i<maxWidth;i++){
            sb.append(" ");
          }
        }else{
          int spaces=(maxWidth-count)/diff;
          int extraspace=(maxWidth-count)%diff;
          for(int i=index+1;i<last;i++){
            
            for(int j=0;j<=(spaces+((i-index-1)<extraspace?1:0));j++){
              sb.append(" ");
            }
            sb.append(words[i]);
          }
        }

        // result.add(sb.toString());
        System.out.println(sb.toString());
        index=last;   
    }
    
  }

  public static void main(String[] args) {
    String[] words1 = {"This", "is", "an", "example", "of", "text", "justification."};
    int maxWidth1 = 16;
    fullJustify(words1, maxWidth1);
  }
}