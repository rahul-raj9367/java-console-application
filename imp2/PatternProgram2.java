/*
H
H E
H E L
H E L L
H E L L O
*/
class PatternProgram2{
	public static void main(String[] args) {
		String s="HELLO";
		StringBuilder sb= new StringBuilder();
		for(int i=0;i<s.length();i++){
			sb.append(s.charAt(i)).append(' ');
			System.out.println(sb.toString());
		}
	}
}