/*
Input: F
Output:
A B C D E F
A B C D E
A B C D
A B C
A B
A
A
A B
A B C
A B C D
A B C D E
A B C D E F
*/
class PatternProgram1{
	public static void main(String[] args) {
		char in='F';

		for(char i=in;i>='A';i--){
			for(char j='A';j<=i;j++){
				System.out.print(j+" ");
			}
			System.out.println();
		}


		for(char i='A';i<=in;i++){
			for(char j='A';j<=i;j++){
				System.out.print(j+" ");
			}
			System.out.println();
		}
	}
}