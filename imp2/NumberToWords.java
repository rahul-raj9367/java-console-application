/*

*/
class NumberToWords {
    private static final String[] units = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static final String[] teens = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static final String[] tens = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public static void main(String[] args) {
        System.out.println(convertToWords(123));
        System.out.println(convertToWords(456));
        System.out.println(convertToWords(789));
        System.out.println(convertToWords(100));
        System.out.println(convertToWords(101));
        System.out.println(convertToWords(1111));

    }

    static String convertToWords(int num){
    	if(num==0){
    		return "Zero";
    	}

    	String word="";
        if(num>=1000){
            word+=units[num/1000]+" Thousand ";
            num=num%1000;
        }
    	if(num>=100){
    		word+=units[num/100]+" Hundred ";
    		num=num%100;
    	}

    	if(num>=20){
    		word+=tens[num/10]+" ";
    		num=num%10;
    	}

    	if(num>0 && num<10){
    		word+=units[num]+" ";
    	}else if(num>=10){
    		word+=teens[num-10]+" ";
    	}

    	return word;
    }
}
