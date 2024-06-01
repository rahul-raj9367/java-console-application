/*
Given a 2D grid of n*m of characters and a word, find all occurrences of given word in grid. A word can be matched in all 8 directions at any point. Word is said to be found in a direction if all characters match in this direction (not in zig-zag form). The 8 directions are, horizontally left, horizontally right, vertically up, vertically down, and 4 diagonal directions.

Note: The returning list should be lexicographically smallest. If the word can be found in multiple directions starting from the same coordinates, the list should contain the coordinates only once. 

Example 1:

Input: 
grid = {{a,b,c},{d,r,f},{g,h,i}},
word = "abc"
Output: 
{{0,0}}
Explanation: 
From (0,0) we can find "abc" in horizontally right direction.
*/
class SearchaWordina2DGridofcharacters{
	 // Rows and columns in the given grid
   	static int R,C;

   	
   	static final int x[]={-1,-1,-1,0,0,1,1,1};
   	static final int y[]={-1,0,1,-1,1,-1,0,1};

   	static boolean finddirection(char grid[][],int row,int col,String word){
   		if(grid[row][col]!=word.charAt(0)) return false;
   		int len=word.length();

   		for(int dir=0;dir<8;dir++){
   			int k,rd=row+x[dir],cd=col+y[dir];
   			for(k=1;k<len;k++){
   				if(rd<0 || rd>=R || cd<0 ||cd>=C) break;

   				if(grid[rd][cd]!=word.charAt(k)) break;

   				rd+=x[dir];
   				cd+=y[dir];
   			}

   			if(k==len) return true;
   		}
   		return false;
   	}

   	static void patternSearch(char [][]grid,String word){
   		for(int row=0;row<R;row++){
   			for(int col=0;col<C;col++){
   				if(grid[row][col]==word.charAt(0) && finddirection(grid,row,col,word)){
   					System.out.println(row+" "+col);
   				}
   			}
   		}
   	}
 

    public static void main(String args[]){
        R = 3;
        C = 13;
        char[][] grid = { { 'G', 'E', 'E', 'K', 'S', 'F', 'O', 'R', 'G', 'E', 'E', 'K', 'S' },
                          { 'G', 'E', 'E', 'K', 'S', 'Q', 'U', 'I', 'Z', 'G', 'E', 'E', 'K' },
                          { 'I', 'D', 'E', 'Q', 'A', 'P', 'R', 'A', 'C', 'T', 'I', 'C', 'E' } };
        patternSearch(grid, "GEEKS");
        System.out.println();
        patternSearch(grid, "EEE");
    }
}