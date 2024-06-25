/*


*/
class ReverseDigitAddNumber{
	public static void main(String[] args) {
		int num=1234;
		int temp=num;
		int sum=0;
		while(temp>0){
			int tem=temp%10;
			sum=(sum*10)+tem;
			temp=temp/10;
		}

		int answer=num+sum;
		System.out.println(answer);
	}
}