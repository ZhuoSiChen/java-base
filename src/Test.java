public class Test {
	public static int countOne(int n) throws Exception {
		if (n < 0) {
			throw new Exception("");
		}
		int count = 0;
		int n1 = n;
		for (int i = 0; i < 32; i++) {
			if (n1 %2 == 1){
				count++;
			}
			n1 = n1>>1;
		}
		return count;
	}

	public static int removeBlank(char []chars){
		int count = 0;
		char jump = 0;
		for (int i=0;i< chars.length;i++){

			if(chars[i]==' '){
				jump++;
				count++;
				if(chars[i+1]!=' '){
					chars[i+1 -jump] = jump;
					jump = 0;
				}
			}
		}
		int jump1 = 0;
		for (int i = 0; i < chars.length; i++) {
			System.out.print(new Integer(chars[i]) + " ");
		}
		System.out.println();
		for (int i = 0; i < chars.length; i++) {
			if(chars[i] >= 'a'&& chars[i] <= 'z'){
				chars[i-jump1] = chars[i];
			}else{
				jump1+=chars[i];
			}
		}
		for(int i=0;i<chars.length-jump1;i++){
			System.out.print(chars[i]);
		}
		System.out.println();
		System.out.println(chars.length);
		System.out.println(chars.length);
		System.out.println(chars);
		return count;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(countOne(3));
		System.out.println(removeBlank("aaa a          b  cd       d".toCharArray()));
	}
}
