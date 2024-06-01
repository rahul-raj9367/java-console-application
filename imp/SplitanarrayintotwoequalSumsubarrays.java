/*
Given an array of integers greater than zero, find if it is possible to split it in two subarrays (without reordering the elements), such that the sum of the two subarrays is the same. Print the two subarrays.

Examples : 

Input : Arr[] = { 1 , 2 , 3 , 4 , 5 , 5  }
Output :  { 1 2 3 4 } 
          { 5 , 5 }

Input : Arr[] = { 4, 1, 2, 3 }
Output : {4 1}
         {2 3}

Input : Arr[] = { 4, 3, 2, 1}
Output : Not Possible
*/
class SplitanarrayintotwoequalSumsubarrays{
	static void findSplitanarrayintotwoequalSumsubarrays(int arr[],int n){
		int splitpoint=findtwoequal(arr,n);

		if(splitpoint==-1 || splitpoint==n){
			System.out.println("Not Possible");
			return;
		}

		for(int i=0;i<arr.length;i++){

			if(splitpoint==i)
				System.out.println();

			System.out.print(arr[i]+" ");
		}
	}
	static int findtwoequal(int arr[],int n){
		int leftsum=0;
		for(int i=0;i<n;i++){
			leftsum+=arr[i];

			int rightsum=0;
			for(int j=i+1;j<n;j++){
				rightsum+=arr[j];
			}

			if(leftsum==rightsum){
				return i+1;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		int	arr[] = { 1 , 2 , 3 , 4 , 5 , 5  };
		int n=arr.length;
		findSplitanarrayintotwoequalSumsubarrays(arr,n);
	}
}