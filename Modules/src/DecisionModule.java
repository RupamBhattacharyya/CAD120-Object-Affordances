/**
@ author Rupam Bhattacharyya
*/
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DecisionModule{
	public void resultManager(String groundAff[], String trainFileName, String testFileName, String objNames[]) throws IOException{
		System.out.println("Inside Decision module: "+testFileName);
		//PrintWriter writer1=new PrintWriter(testFileName);
		PrintWriter writer1=new PrintWriter(new FileWriter(testFileName, true));
		//new FileWriter(trainFileName, true)
		File source=new File(trainFileName);
		File dest=new File(testFileName);
		try {
			copyFileUsingStream(source, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int flagImaginary=getImaginaryStatus(testFileName);
		if(flagImaginary==0){
			if(objNames.length==1){
				String lowerUpper1=objNames[0].substring(0, 1).toUpperCase() + objNames[0].substring(1);
				writer1.append("!hasImmovablePart("+lowerUpper1+","+" Lid)");
			}
			else
				writer1.append("!hasImmovablePart("+"Imaginary,"+ " Lid)");
		}
		writer1.close();
		//This is where; you append the ground truths. 
		Writer output;
		//output = new BufferedWriter(new FileWriter(source, true));  //clears file every time
		output = new PrintWriter(new FileWriter(trainFileName, true));  //clears file every time
		String lowerToUpper;
		String lowerToUpper1;
		int flagMatch=0;
		int flag1=0;
		String objNamesCopy[]=new String[objNames.length];
		//*********************Start***********************
		
		for(int ni=0; ni<objNames.length;ni++){
			if(objNames.length>1){
				for(int nj=1;nj<objNames.length;nj++){
					if(objNames[ni].equals(objNames[nj])){
						System.out.println("Carry On..Match found");
						flagMatch=1;
					}
					else
						break;
				}
				System.out.println("*****");
			}
			else{
				flag1=1;	
				break;
			}
		}
		if(flagMatch==1 && flag1==0){
			for(int k=0; k<objNames.length;k++){
				//objNames[k]=objNames[k]+k;
				objNamesCopy[k]=objNames[k]+k;
			}
		}
		for(int kk=0; kk<objNames.length; kk++){
			System.out.println(objNames[kk]);
		}
		//flag1=0;
		//flagMatch=0;
		//*********************End*************************
		for(int i=0; i<groundAff.length;i++){
			//output.append(groundAff[i]);
			if(flagMatch==1 && flag1==0){
				//objNamesCopy
				lowerToUpper=objNamesCopy[i].substring(0, 1).toUpperCase() + objNamesCopy[i].substring(1);
				lowerToUpper1=groundAff[i].substring(0, 1).toUpperCase() + groundAff[i].substring(1);
				if(i==0){
					if(groundAff[i].equals("stationary")) 
						output.append("//"+"Affordance("+lowerToUpper1+","+lowerToUpper+")");
					else
						output.append("Affordance("+lowerToUpper1+","+lowerToUpper+")");
				}
				else{
					if(groundAff[i].equals("stationary")) 
						output.append("\n"+"//"+"Affordance("+lowerToUpper1+","+lowerToUpper+")");
					else
						output.append("\n"+"Affordance("+lowerToUpper1+","+lowerToUpper+")");
				}
			}
			else{
				lowerToUpper=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1);
				lowerToUpper1=groundAff[i].substring(0, 1).toUpperCase() + groundAff[i].substring(1);
				if(i==0){
					if(groundAff[i].equals("stationary")) 
						output.append("//"+"Affordance("+lowerToUpper1+","+lowerToUpper+")");
					else
						output.append("Affordance("+lowerToUpper1+","+lowerToUpper+")");
				}
				else{
					if(groundAff[i].equals("stationary")) 
						output.append("\n"+"//"+"Affordance("+lowerToUpper1+","+lowerToUpper+")");
					else
						output.append("\n"+"Affordance("+lowerToUpper1+","+lowerToUpper+")");
				}
			}	
		}
		//WRITE "//" if "STATIONARY"
		output.close();
	}
	public void typeCommand(String groundTruthSubActivity, String rawTDS){
		System.out.println("\nInside DECISION module the ground truth sub-activity is-->"+groundTruthSubActivity);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		String pasteCommand1=rawTDS+"-"+groundTruthSubActivity+"-test.db";
		String pasteCommand2=rawTDS+"-"+groundTruthSubActivity+"-out.mln";
		String resultCommand=rawTDS+"-"+groundTruthSubActivity+".result";
		//System.out.println("pasteCommand1 "+pasteCommand1);
		//System.out.println("pasteCommand2 "+pasteCommand2);
		System.out.println("Please OPEN YOUR Terminal");
		System.out.println("GO to alchemy/bin as a ROOT user");
		//System.out.println("PASTE ./infer -ms -i closing-out.mln -r closing.result -e closing-test.db -q Affordance");
		System.out.println("PASTE ./infer -ms -i "+pasteCommand2+ " -r "+resultCommand+ " -e "+pasteCommand1+ " -q Affordance");
		System.out.println();
	}
	public void printResult(String groundTruthSubActivity, String rawTDS, String parentPath) throws IOException{
		System.out.println("Inside printResult() method--");
		// Assuming the affordance.result path is located below:
		String resultPath=parentPath+"/"+rawTDS+"-"+groundTruthSubActivity+".result";
		System.out.println("Result file located in "+resultPath);
		BufferedReader br1 = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("This is to SHOW you the RESULT");
		// Old code to write the MaxResult.txt file
		System.out.println("Hellow World");
		//Setting the paths for three files for the RESULT.
		//String resultpath=parentPath+"/"+rawTDS+"-"+groundTruthSubActivity+".result";
		String formattedFilePath=parentPath+"/"+rawTDS+"-"+groundTruthSubActivity+"-"+"FormatedResult.txt";
		String viewFilePath=parentPath+"/"+rawTDS+"-"+groundTruthSubActivity+"-"+"ViewResult.txt";
		String maxResultPath=parentPath+"/"+rawTDS+"-"+groundTruthSubActivity+"-"+"MaxResult.txt";
		PrintWriter writer = new PrintWriter("/home/rupam/Documents/JavaPhDCode/Test/src/FormatedResult.txt", "UTF-8");
		PrintWriter writer1 = new PrintWriter("/home/rupam/Documents/JavaPhDCode/Test/src/ViewResult.txt", "UTF-8");
		PrintWriter writer2 = new PrintWriter("/home/rupam/Documents/JavaPhDCode/Test/src/MaxResult.txt", "UTF-8");
		/*
		PrintWriter writer = new PrintWriter(formattedFilePath, "UTF-8");
		PrintWriter writer1 = new PrintWriter(viewFilePath, "UTF-8");
		PrintWriter writer2 = new PrintWriter(maxResultPath, "UTF-8");
		*/		
		int countObj=0;
		int count=0;
		int oneGoCount=0;
		String affordanceString="";
		String objString="";
		String valueAff="";
		 ArrayList<String> obj = new ArrayList<String>();
		int j=1;
		String path="/home/rupam/Documents/JavaPhDCode/Test/src/closing.result";// This is replaced with resultpath
		//String path=resultPath;
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
	    countObj=count/12;
	    oneGoCount=count/3;
	    System.out.println("Object count in the MLN Test file---------->"+countObj);
	    for(int i=1;i<=countObj;i++){
			//System.out.println("i----"+i);
			for(j=i;j<=countObj*12;j+=3){
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
	    	//Main logic starts here
	    	Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(tokenize[k]);
	        while(m.find()) {
	          //System.out.println(m.group(1));  
	          String x=m.group(1);
	          String temp[]=x.split(",");
	          temp1=temp;
	        }
	        //String temp[]=(m.group(1)).split(",");
	        
	        for(int l=0;l<temp1.length;l++){
	        	//System.out.println("Parsed String ---"+temp1[l]);
	        	//System.out.println("affordanceString ---"+temp[l]);
	        }
	        affordanceString=temp1[0];
	        objString=temp1[1];
	        valueAff=valueString[1];
	        //Final Printed string
	        String printString=temp1[0]+" "+temp1[1]+" "+valueString[1];
	        //System.out.println("***********************************************");
	        //System.out.println(" "+affordanceString+" "+objString+" "+valueAff);
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
	    String path1="/home/rupam/Documents/JavaPhDCode/Test/src/FormatedResult.txt";
	    //String path1=formattedFilePath;
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
	    
	    String path2="/home/rupam/Documents/JavaPhDCode/Test/src/ViewResult.txt";
	    //String path2=viewFilePath;
	    String testReturnedItem2=readFile(path2, StandardCharsets.UTF_8);
	    String tokenize2[]=testReturnedItem2.split("\n");
	    //For loop for comparison
	    double max=-0.99999;
	    int indexAffordanceName=0;
	    int limit=0;
	    String affordanceString1="";
	    String objName="";
	    for(int gogo1=0; gogo1<countObj;gogo1++){
	    	for(j=0+limit;j<=(11+limit);j++){
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
	    	limit=12*(gogo1+1);
	    	//Switch case to look into the affordance name using (indexAffordanceName-limit)
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
	private static void copyFileUsingStream(File source, File dest) throws IOException {
	    InputStream is = null;
	    OutputStream os = null;
	    try {
	        is = new FileInputStream(source);
	        os = new FileOutputStream(dest);
	        byte[] buffer = new byte[1024];
	        int length;
	        while ((length = is.read(buffer)) > 0) {
	            os.write(buffer, 0, length);
	        }
	    } finally {
	        is.close();
	        os.close();
	    }
	}
	static int getImaginaryStatus(String testFilename) throws IOException{
		 BufferedReader br = new BufferedReader(new FileReader(testFilename));
		 String s;
		 int linecount=0;
		 int flagImaginary=0;
		    
		 String keyword1="hasDetachablePart";
		 String keyword2="hasNonDetachablePart";
		 String keyword3="hasImmovablePart";
		 String line;
		    
		 while ((s=br.readLine())!=null){
		   if((s.contains(keyword1)) || (s.contains(keyword2)) || (s.contains(keyword3))){
			   System.out.println(s);
			   flagImaginary=1;
			   break;
		   } 
		 }
		return flagImaginary;
	}
}
