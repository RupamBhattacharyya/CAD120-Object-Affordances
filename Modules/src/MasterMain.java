
/**
 * @author Rupam Bhattacharyya
 * This project has 4 classes VideoProcessingModule.java, OntologyProcessingModule.java, DeductionModule.java and DecisionModule.java 
 * It is able to produce affordance result presented in the paper.
 * Started on November 29th, 2016.
*/
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
public class MasterMain{
	// Declare variables of Global Nature.
	public static String userPath;
	public static String createdFilePath;
	public static String createdFilePathFormatted;
	public static String rawTDS;
	public static String startFrame;
	public static String endFrame;
	public static String subactivityIdGroundTruth;
	public static String highLevelActivityGroundTruth;
	public static String parentPath;
	public static ArrayList<String> objNameValuePair=new ArrayList<>();
	public static int objNumber;
	public static int test_Or_train;
	public static String subjectNumber;
	public static String trainFile;
	public static String testFile;
	public static String resultFile;
	public static void main(String args[]) throws IOException{
	//Step:1 User provides Ten Digit String (TDS) of a a) particular subject (subject 1) and 
	//b) High level activity (such as Microwaving food)
	//String parentPath=MasterMain.userPath;
	MasterMain.userPath="/home/rupam/Documents/JavaPhDCode/Affordance";
	MasterMain.parentPath=MasterMain.userPath;
	// Subject 1 Ten Digit Strings************************************************
	MasterMain.rawTDS="1204150645";//Microwaving food
	//MasterMain.rawTDS="1204142055"; // Making cereal
	//MasterMain.rawTDS="0510175411"; // Arranging objects
	//MasterMain.rawTDS="0510181236";// Cleaning objects
	//MasterMain.rawTDS="0510182019";// Having Meal
	//MasterMain.rawTDS="0510175829";// Picking objects
	//MasterMain.rawTDS="1204144410"; //(Stacking)
	//MasterMain.rawTDS="0510180218"; //Taking food
	//MasterMain.rawTDS="1204142858"; //Taking Medicine
	//MasterMain.rawTDS="1204145527"; // Unstacking objects
		
	MasterMain.userPath=MasterMain.userPath+"/"+MasterMain.rawTDS+".txt";//Unstacking objects!
	System.out.println("user path:::"+MasterMain.userPath);
	//Sequence of function calls
	getExperimentInfo();
	getSubjectInfo();
	getHighLevelActivityInfo();
	trainFile=subjectNumber+"-"+highLevelActivityGroundTruth+"-"+MasterMain.rawTDS+"-"+"Train";
	testFile=subjectNumber+"-"+highLevelActivityGroundTruth+"-"+MasterMain.rawTDS+"-"+"Test";
	resultFile=subjectNumber+"-"+highLevelActivityGroundTruth+"-"+MasterMain.rawTDS+"-"+"RESULT";
	File dir = new File(trainFile);
	dir.mkdir();
	File dir1 = new File(testFile);
	dir1.mkdir();
	File dir2 = new File(resultFile);
	dir2.mkdir();
	//Step:2 From this TDS; we create a file TDS-labelling.txt from labeling.txt (CAD-120) 
	//leaving the unmatched TDS.
	// This idea is reflected in this main()
	MasterMain.createdFilePath=MasterMain.parentPath+"/TDS-labelling.txt";
	MasterMain.createdFilePathFormatted=MasterMain.parentPath+"/TDS-labelling-Formatted.txt";
	String labelFilePath=MasterMain.parentPath+"/labeling.txt";
	String activityLabelFilePath=MasterMain.parentPath+"/activityLabel.txt";
	PrintWriter writer = new PrintWriter(MasterMain.createdFilePath, "UTF-8");
	String testReturnedItem1=readFile(labelFilePath, StandardCharsets.UTF_8);
	String tokenize[]=testReturnedItem1.split("\n");
	System.out.println("------->>tokenize ka length "+tokenize.length);
	System.out.println("labelling.txt contains\n");
	int flag=0;
	int count=1;
	for(int i=0; i<tokenize.length;i++){
		//System.out.println(tokenize[i]);
		String temp[]=tokenize[i].split(",");
		for(int j=0;j<temp.length;j++){
			if(temp[j].equals(MasterMain.rawTDS)){
				System.out.println("temp[0]->"+temp[j]);
				//writer.print(temp[j]);
				if(flag==0){
					writer.print(tokenize[i]);
					flag=1;
				}
				count++;
				}
				else{
					//do nothing
					System.out.println("temp[0]->"+temp[j]);
				}
			}
			flag=0;
			writer.print("\n");
		}
		writer.close();
		System.out.println("Count->"+(count-1));
		try {
			formatTDS(createdFilePath, createdFilePathFormatted);
			tdsProcessing(createdFilePathFormatted);
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
		
	}
	static void tdsProcessing(String TDSFilePath) throws IOException, OWLOntologyCreationException{
		String testReturnedItem1=readFile(TDSFilePath, StandardCharsets.UTF_8);
		String tokenize[]=testReturnedItem1.split("\n");
		System.out.println("Created file contains\n");
		for(int i=0; i<tokenize.length;i++){
			String temp[]=tokenize[i].split(",");
			//System.out.println("temp[i]"+temp[i]);
			System.out.println("tokenize[i]-->"+tokenize[i]);
			System.out.println("++++++++++");
			for(int j=0; j<temp.length; j++){
				MasterMain.objNumber=(temp.length-4);
				if(j==1){
					MasterMain.startFrame=temp[j];
					//System.out.println("Goli--"+Label.startFrame);
				}
				else if(j==2){
					MasterMain.endFrame=temp[j];
					//System.out.println("Goli--"+Label.endFrame);
				}
				else if(j==3){
					MasterMain.subactivityIdGroundTruth=temp[j];
					//System.out.println("Goli--"+Label.subactivityIdGroundTruth);
				}
			}
		String objNames[]=new String[MasterMain.objNumber];
		String groundAffordances[]=new String[MasterMain.objNumber]; 
		int countGround=0;
		for(int m=(temp.length-1);m>=(temp.length-MasterMain.objNumber);m--){
			groundAffordances[countGround]=temp[m];
			System.out.println("ground affordances of current sub-activity-->"+temp[m]);
			countGround++;
		}
		objNames=loadObjName();
		for(int k=0;k<objNames.length;k++){
			//System.out.println("Returned object names->"+ objNames[k]);
			OntologyProcessingModule o=new OntologyProcessingModule();
			o.getObjectProperty(objNames[k]);
		}
		int startFrameInt=Integer.parseInt(MasterMain.startFrame);
		int endFrameInt=Integer.parseInt(MasterMain.endFrame);
		System.out.println("Start frame and End Frame are "+startFrameInt+" "+endFrameInt);
		String jointPos[]=new String[12];
		for(int l=startFrameInt; l<=endFrameInt; l++){
			VideoProcessingModule v=new VideoProcessingModule(); 
			jointPos=v.skeletonManager(l, MasterMain.rawTDS, MasterMain.parentPath);
			for(int m=0; m<objNames.length; m++){
			 	v.calculateCentroid(l, m, objNames, MasterMain.rawTDS, MasterMain.parentPath);
			}	
			DeductionModule d=new DeductionModule();
			//String trainFileName=MasterMain.parentPath+"/Train/"+MasterMain.rawTDS+"-"+MasterMain.subactivityIdGroundTruth+"-train.db";
			//String trainFileName=MasterMain.parentPath+"/"+trainFile+"/"+MasterMain.rawTDS+"-"+MasterMain.subactivityIdGroundTruth+"-train.db";
			String trainFileName=MasterMain.parentPath+"/"+trainFile+"/"+MasterMain.subactivityIdGroundTruth+"-train.db";
			//d.relationManager(trainFileName, objNames); // this will access returned items from the methods called on o,v
			d.relationManager(trainFileName, objNames, jointPos, l, MasterMain.rawTDS, MasterMain.parentPath);	
			DecisionModule dm=new DecisionModule();
			//String testFileName=MasterMain.parentPath+"/Test/"+MasterMain.rawTDS+"-"+MasterMain.subactivityIdGroundTruth+"-test.db";
			//String testFileName=MasterMain.parentPath+"/"+testFile+"/"+MasterMain.rawTDS+"-"+MasterMain.subactivityIdGroundTruth+"-test.db";
			String testFileName=MasterMain.parentPath+"/"+testFile+"/"+MasterMain.subactivityIdGroundTruth+"-test.db";
			dm.resultManager(groundAffordances, trainFileName, testFileName, objNames); // this will access returned items from the methods called on d
			}
			DeductionModule d=new DeductionModule();
			d.typeCommand(MasterMain.subactivityIdGroundTruth, MasterMain.rawTDS);
			DecisionModule dm=new DecisionModule();
			//dm.typeCommand(MasterMain.subactivityIdGroundTruth, MasterMain.rawTDS);
			//dm.printResult(MasterMain.subactivityIdGroundTruth, MasterMain.rawTDS, MasterMain.parentPath);
			// Above command internally calls printResult() 
			System.out.println("***********");
		}	
	}
	static String[] loadObjName() throws IOException{
		// Remember Step:4
		System.out.println("Inside loadObjName Object count--"+MasterMain.objNumber);
		String objectNames[]=new String[MasterMain.objNumber];
		String replaceWith="activityLabel.txt";
		//String replaced="labeling.txt";
		//String userPathAL=(MasterMain.userPath).replaceAll(replaced, replaceWith);
		String userPathAL=(MasterMain.parentPath)+"/"+replaceWith;
		System.out.println("userPathAL"+userPathAL);
		//System.out.println("Parent path "+MasterMain.parentPath);
		
		//System.out.println("User path---"+Label.userPath);
		String testReturnedItem1=readFile(userPathAL, StandardCharsets.UTF_8);
		String tokenize[]=testReturnedItem1.split("\n");
		//System.out.println("Created file contains\n");
		int saveIndex_i=-555;
		int saveIndex_j;
		for(int i=0; i<tokenize.length;i++){
			//System.out.println("activityLabel.txt--"+tokenize[i]);
			String temp[]=tokenize[i].split(",");
			for(int j=0; j<temp.length;j++){
				if(temp[j].equals(MasterMain.rawTDS)){
					//System.out.println("temp[j]"+temp[j]);
					saveIndex_i=i;
					break;
				}
			}
		}	
		System.out.println("Lets see tokenize[saveIndex_i]  "+tokenize[saveIndex_i]);
		String temp1[]=(tokenize[saveIndex_i]).split(",");
		System.out.println(temp1.length-MasterMain.objNumber);
		int countCheck=0;
		for(int ii=(temp1.length-1);ii>=(temp1.length-MasterMain.objNumber);ii--){
			System.out.println("temp1[ii] "+temp1[ii]);
			String temp11[]=temp1[ii].split(":");
			for(int jj=0; jj<temp11.length;jj++){
				System.out.println("temp11 elements "+temp11[jj]);
				System.out.println(temp11.length);
				if(jj==1){
					(MasterMain.objNameValuePair).add(temp11[jj]);
					objectNames[countCheck]=temp11[jj];
					//Problem in arraylist is that it keep on adding items, so check duplicates.
					//System.out.println();
					countCheck++;
				}
			}
			System.out.println("ii "+ii);
		}
		System.out.println("countCheck "+countCheck);  
		return objectNames;
	}
	static  String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	static void getSubjectInfo(){
		// This method asks user about which subjects (1/2/3/4) have been tested.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter the SUBJECT number (1/2/3/4)");
		try {
			//This location will provide contents related to objects and human.
			String subNumber=br.readLine();
			MasterMain.subjectNumber=subNumber;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static void getExperimentInfo(){
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("For TESTING Experiment type: 0");
		System.out.println("For TRAINING Experiment type: 1");
		try {
			//This location will provide contents related to objects and human.
			String expNumber=br.readLine();
			MasterMain.test_Or_train=Integer.parseInt(expNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static void getHighLevelActivityInfo(){
		// This method asks user about the High Level activity Information.
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Write High Level Activity Name");
		try {
			//This location will provide contents related to objects and human.
			String activity=br.readLine();
			MasterMain.highLevelActivityGroundTruth=activity;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	static void formatTDS(String source, String dest) throws IOException{
		FileReader fr = new FileReader(source); 
		BufferedReader br = new BufferedReader(fr); 
		FileWriter fw = new FileWriter(dest);
		String line;
		while((line = br.readLine()) != null)
		{ 
		    line = line.trim(); // remove leading and trailing whitespace
		    if (!line.equals("")) // don't write out blank lines
		    {
		        fw.write(line, 0, line.length());
		        fw.write("\n");
		    }
		} 
		fr.close();
		fw.close();
	}
}
