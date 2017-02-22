import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Test{
	public static void main(String args[]) throws IOException{
		PrintWriter writer = new PrintWriter("/home/rupam/Documents/JavaPhDCode/Test-AIR/src/FormatedResult.txt", "UTF-8");
		PrintWriter writer1 = new PrintWriter("/home/rupam/Documents/JavaPhDCode/Test-AIR/src/ViewResult.txt", "UTF-8");
		PrintWriter writer2 = new PrintWriter("/home/rupam/Documents/JavaPhDCode/Test-AIR/src/MaxResult1.txt", "UTF-8");
		int countObj=0;
		int count=0;
		int oneGoCount=0;
		String affordanceString="";
		String objString="";
		String valueAff="";
		 ArrayList<String> obj = new ArrayList<String>();
		int j=1;
		// Calculating result by following commands****************************
		//String path="/home/rupam/Documents/JavaPhDCode/Test-AIR/src/reaching.result";
		//String path="/home/rupam/Documents/JavaPhDCode/Test-AIR/src/moving.result";
		//String path="/home/rupam/Documents/JavaPhDCode/Test-AIR/src/placing.result";
		String path="/home/rupam/Documents/JavaPhDCode/Test-AIR/src/drinking.result";
		//String path="/home/rupam/Documents/JavaPhDCode/Test-AIR/src/null.result";
		//String path="/home/rupam/Documents/JavaPhDCode/Test-AIR/src/eating.result";
		//String path="/home/rupam/Documents/JavaPhDCode/Test-AIR/src/opening.result";
		//String path="/home/rupam/Documents/JavaPhDCode/Test-AIR/src/closing.result";
		//String path="/home/rupam/Documents/JavaPhDCode/Test-AIR/src/cleaning.result";
		//String path="/home/rupam/Documents/JavaPhDCode/Test-AIR/src/pouring.result";
		InputStream is = new BufferedInputStream(new FileInputStream(path));
	    try {
	        byte[] c = new byte[1024];
	        String str1 ="";//= new String(c);
	        //int count = 0;
	        int readChars = 0;
	        boolean endsWithoutNewLine = false;
	        while ((readChars = is.read(c)) != -1) {
	            for (int i = 0; i < readChars; ++i) {
	                if (c[i] == '\n'){
	                	//System.out.println("str1 >> "+str1);
	                    ++count;
	                }    
	                else{
	                	str1 = new String(c);
	                	//System.out.println("str1 >> "+str1);
	                }
	            }
	            endsWithoutNewLine = (c[readChars - 1] != '\n');
	        }
	        if(endsWithoutNewLine) {
	            ++count;
	        } 
	        System.out.println("Line Count in the Affordance.result File---"+count); 
	    } finally {
	        is.close();
	    }
	    //countObj=count/12;
	    countObj=count/11;
	    //oneGoCount=count/3;
	    oneGoCount=count/countObj;
	    System.out.println("Object count in the MLN Test file---------->"+countObj);
	    for(int i=1;i<=countObj;i++){
			//System.out.println("i----"+i);
			//for(j=i;j<=countObj*12;j+=3){
	    	for(j=i;j<=countObj*11;j+=3){
				//System.out.println("j----"+j);	
			}
		}
	    ArrayList aList= new ArrayList(Arrays.asList(path.split("\n",-1)));
	    String testReturnedItem=readFile(path, StandardCharsets.UTF_8);
	    String tokenize[]=testReturnedItem.split("\n");
	    //System.out.println("String length---"+tokenize.length);
	    String temp1[]=new String[2];
	    for(int k=0; k<tokenize.length;k++){
	    	//System.out.println("Lets see tokenize[]---"+tokenize[k]);
	    	//System.out.println("tokenize[]---"+tokenize[k]);
	    	String valueString[]=(tokenize[k]).split(" ");
	    	for(int kk=0;kk<valueString.length;kk++){
	    		//System.out.println("Lets parse the vale from each line-->"+valueString[kk]);
	    	}
	    	Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(tokenize[k]);
	        while(m.find()) {
	          //System.out.println(m.group(1));  
	          String x=m.group(1);
	          String temp[]=x.split(",");
	          temp1=temp;
	        }
	        
	        for(int l=0;l<temp1.length;l++){
	        	//System.out.println("Parsed String ---"+temp1[l]);
	        	//System.out.println("affordanceString ---"+temp[l]);
	        }
	        affordanceString=temp1[0];
	        objString=temp1[1];
	        valueAff=valueString[1];
	        String printString=temp1[0]+" "+temp1[1]+" "+valueString[1];
	        //System.out.println("***********************************************");
	        writer.print(printString);
	        for(int i=1;i<=count;i++){
				//System.out.println("i----"+i);
				for(j=i;j<=count;j+=3){
					//System.out.println("printString---"+printString);	
				}
			}
	        writer.print("\n");
	        
	        
	    }
	    writer.close();
	    String path1="/home/rupam/Documents/JavaPhDCode/Test-AIR/src/FormatedResult.txt";
	    String testReturnedItem1=readFile(path1, StandardCharsets.UTF_8);
	    String tokenize1[]=testReturnedItem1.split("\n");
	    String tempStore;
	    //for(int gogo=0; gogo<tokenize1.length;gogo++){
	    int gogo=0;
	    //System.out.println("B4 gogo for loop->"+countObj);
	    for(gogo=0; gogo<countObj;gogo++){
	    	//for(j=gogo;j<tokenize1.length;j+=3){
	    	for(j=gogo;j<tokenize1.length;j+=countObj){
				System.out.println("printString---"+tokenize1[j]);
				writer1.println(tokenize1[j]);
			}
	    }
	    writer1.close();
	    
	    String path2="/home/rupam/Documents/JavaPhDCode/Test-AIR/src/ViewResult.txt";
	    String testReturnedItem2=readFile(path2, StandardCharsets.UTF_8);
	    String tokenize2[]=testReturnedItem2.split("\n");
	    //For loop for comparison
	    double max=-0.99999;
	    int indexAffordanceName=0;
	    int limit=0;
	    String affordanceString1="";
	    String objName="";
	    for(int gogo1=0; gogo1<countObj;gogo1++){
	    	//for(j=0+limit;j<=(11+limit);j++){
	    	for(j=0+limit;j<=(10+limit);j++){
	    		String valueString[]=(tokenize2[j]).split(" ");
	    		objName=valueString[1];
				double currentVal=Double.parseDouble(valueString[2]);
				if(currentVal>max){
					max=currentVal;
					indexAffordanceName=j;
				}
	    	}
	    	int affInd=indexAffordanceName-limit;
	    	System.out.println("Affordance index->"+affInd);
	    	limit=11*(gogo1+1);
	    	switch (affInd) {
            		case 0:  affordanceString1 = "Reachable ";
            			writer2.print(affordanceString1);
                     		break;
            		case 1:  affordanceString1 = "Movable ";
            		 	writer2.print(affordanceString1);
                     		break;
            		case 2:  affordanceString1= "Placeable ";
            		 	writer2.print(affordanceString1);
                     		break;
            		case 3:  affordanceString1 = "Pourable ";
            		 	writer2.print(affordanceString1);
                     		break;
            		case 4:  affordanceString1 = "Pourto ";
            		 	writer2.print(affordanceString1);
                     		break;
            		case 5:  affordanceString1 = "Containable ";
            		 	writer2.print(affordanceString1);
                     		break;
            		case 6:  affordanceString1= "Drinkable ";
            		 	writer2.print(affordanceString1);
                     		break;
            		case 7:  affordanceString1 = "Openable ";
            			 writer2.print(affordanceString1);
                     		break;
            		case 8:  affordanceString1 = "Closeable ";
            			 writer2.print(affordanceString1);
                     		break;
            		case 9: affordanceString1= "Cleanable ";
            			writer2.print(affordanceString1);
                     		break;
            		case 10: affordanceString1= "Cleaner ";
            			 writer2.print(affordanceString1);
                     		break;
            		case 11: affordanceString1 = "Stationary ";
            			 writer2.print(affordanceString1);
                     		break;
	            	default: affordanceString1 = "Invalid month ";
                     		break;
        	}
	    	writer2.print(objName);
	    	writer2.print(" ");
	    	writer2.println(max);
	    	max=-0.99999;
	    }
	    writer2.close();
	    
	}
	static String readFile(String path, Charset encoding) throws IOException {
		  byte[] encoded = Files.readAllBytes(Paths.get(path));
		  return new String(encoded, encoding);
}
}
