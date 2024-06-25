/*
Input:
"WELCOMETOZOHOOFGRADUATESTUDIES"
"HOO"
Convert the string to 2D matrix
W E L C O 
M E T O Z 
O H O O F 
G R A D U 
A T E S T 
U D I E S
Output: 
Start index:<3,0>
End index:<3,2>
*/
class MatrixTraverse{
	static boolean TraverseleftToRight(int row,int col,String s,char mat[][]){
		int idx=0;
		while(idx<s.length() && col < mat[0].length  && mat[row][col]==s.charAt(idx)){
			idx++;
			col++;
		}

		return idx==s.length();
	}
	static boolean TraversetopToBottom(int row,int col,String s,char mat[][]){
		int idx=0;
		while(idx<s.length() && row<mat.length && mat[row][col]==s.charAt(idx)){
			idx++;
			row++;
		}

		return idx==s.length();
	}
	public static void main(String[] args) {
		 char mat[][] ={{'W','E','L','C','O'},
                        {'M','E','T','O','Z'},
                        {'O','H','O','O','F'},
                        {'G','R','A','D','U'},
                        {'A','T','E','S','T'},
                        {'U','D','I','E','S'}};
          String s2="UTS";
         int startIdx[]=new int[2];
         int endIdx[]=new int[2];

         boolean flag=true;

         for(int i=0;i<mat[0].length && flag ;i++){
         	for(int j=0;j<mat[0].length && flag;j++){
         		if(s2.charAt(0)==mat[i][j]){

         			boolean leftToRight=TraverseleftToRight(i,j,s2,mat);
         			boolean topToBottom=TraversetopToBottom(i,j,s2,mat);

         			if(leftToRight){
         				startIdx[0]=endIdx[0]=i;
         				startIdx[1]=j;
         				endIdx[1]=j+s2.length()-1;
         				flag=false;
         			}

         			if(topToBottom){
         				startIdx[1]=endIdx[1]=j;
         				startIdx[0] = i;
                       	endIdx[0] = i+s2.length()-1;
                       	flag = false;
         			}
         		}
         	}
         }


         if(!flag){
         	System.out.println(startIdx[0]+"   "+startIdx[1]);
         	System.out.println(endIdx[0]+"   "+endIdx[1]);

         }else{
         	System.out.println("Not Found");
         }
	}
}