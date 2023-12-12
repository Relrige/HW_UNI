/*
kulakevych Stas
 */

import acm.program.*;

public class SymbolCheck extends ConsoleProgram {
	public boolean j = true;
	public boolean check = true;
	public int counter = 0;
	public int counterNumb = 0;
	public int counterWord = 0;

	public void run() {
		this.setSize(500, 500);
		this.setFont("SeinsSherif-25");
		while (j) {
			String str = readLine("Enter char");
			if (!str.isEmpty())
		//		wordsNumber(str);
				check(str);

		}
	}
    public void wordsNumber(String str){
        int count = 0;
        int wCount = 0;
        while (str.contains(" ")){
            wCount++;
           String s = str.substring(0,str.indexOf(" "));
           String s2 = str.substring(0,str.indexOf("."));
           if(s.length()>s2.length())
        	   s=s2;
           str = str.substring(str.indexOf(" ")+1,str.length());
           int l=str.length();
           str = str.substring(str.indexOf(".")+1,str.length());
           int l2 =str.length();
           if(l>l2)
           {
        	   
           }
           else
           {
        	   
           }
           if (s.equals(" ")|| s.length()==0)continue;
            int countY = 0;
           for (int i = 0; i<s.length();i++){
               if (s.charAt(i)<=47||s.charAt(i)>=58){
                   if(s.charAt(i)!=44&&s.charAt(i)!=46){
                       count++;
                       break;
                   }
                   else if(i==0&&s.charAt(i)==','){
                	   System.out.println("Works");
                       count++;
                   }
                   else if(s.charAt(s.length()-1)=='.'|| s.length()-1==','){
                       count++;
                   }
                   else countY++;

               }
           }
           if (countY>1){
               count++;
           }
        }
        System.out.println(count);
        int countY = 0;
        if (str.equals(" ")|| str.length()==0){

        }
        else{
            wCount++;
            
        for (int i = 0; i<str.length();i++){
        	if(((i==str.length()-1)&& (str.charAt(i)=='.')||str.charAt(i)==',') || (i<str.length()-1)&&(str.charAt(i+1)==' '&&(str.charAt(i)=='.'||str.charAt(i)==',')))
            {
           	 count++;
           	 System.out.println(count);
         	  break;
            }
            if (str.charAt(i)<=47||str.charAt(i)>=58){
                if(str.charAt(i)!=44&&str.charAt(i)!=46){
                    count += 1;
                    break;
                }
                else countY++;
            }
        }
        
        if (countY>1){
            count++;
        }

    } 
        //println("All number: " +(wCount));
        println("Words number: " +count);
        println("Numbers number: " +(wCount-count));
    }
    public void onlyLetter(String str){
    	String str1 = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z'
					|| str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
				str1 += str.charAt(i);
			}
		}
		println("Only letters: " + str1);

    }
    public void onlyUnique(String str){
    	String str2 = "";
		for (int i = 1; i < str.length(); i++) {

			if (str.charAt(i - 1) != str.charAt(i)) {
				str2 += str.charAt(i - 1);
			}
		}
		str2 += str.charAt(str.length() - 1);
		println("Without same " + str2);
		check = true;
    }
    public void onlyUpper(String str){
    	String str3 = "";
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ' && check) {
				if (str.charAt(i) >= 'A' && str.charAt(i) <= 'Z') {
					int k = 0;
					while (true) {
						str3 += str.charAt(i + k);
						k++;
						if (i + k == str.length() || str.charAt(i + k) == ' ')
							break;
					}
					str3 += ' ';
					check = false;
				} else {
					check = false;
				}
			} else if (str.charAt(i) == ' ') {
				check = true;
			}
		}
		println("Only upper: "+str3);
		check = true;
    }

	private void check(String str) {
		
		wordsNumber(str);
		onlyLetter(str);
		onlyUnique(str);
		onlyUpper(str);
		

	}
}