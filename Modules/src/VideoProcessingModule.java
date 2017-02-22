/*
 * @author Rupam Bhattacharyya
 * Two core functionalities related to human joint and object has been done.
 * Annotations from CAD-120 Video dataset is going to be utilized here.
 * Started on 29-11-2016 
*/
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.lang.ProcessBuilder.Redirect;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
public class VideoProcessingModule{
	public String[] skeletonManager(int FrameNumber) throws IOException{
		return null;
	}
	static int frameNumberOnLoad;
	public static int sharedCounter=0;
	public String[] skeletonManager(int frameNo, String rawTDS, String parentPath) throws IOException{
		VideoProcessingModule.sharedCounter=0;
		frameNumberOnLoad=frameNo;
		String Path=parentPath+"/"+rawTDS+".txt";
		String testReturnedItem1=readFile(Path, StandardCharsets.UTF_8);
		//System.out.println("Graet Line length->\n"+testReturnedItem1.length());
		String NewString = testReturnedItem1.replaceAll("End", "");
		String NewSTring1= NewString.substring(0, NewString.length()-3);
		String tokenize[]=NewSTring1.split(",");
		String[] finalJointPos=new String[12];
		groupStrings(tokenize, tokenize.length, finalJointPos);
		return finalJointPos;
	}
	static  String readFile(String path, Charset encoding) throws IOException {
		byte[] encoded = Files.readAllBytes(Paths.get(path));
		return new String(encoded, encoding);
	}
	static String [] groupStrings(String str[], int totalFrames, String [] finalJointPos){
		int flag=0;
		int flagForLoop1=0;
		int flagForLoop2=0;
		int i=0, j, k=0, l, kk=0, ll=0;
		String currenFrame=null;
		int groupNumber=totalFrames/171;
		System.out.println("total frames->"+totalFrames);
		System.out.println("Group Number->"+groupNumber+"\n");
		while(i<totalFrames && (i/171)<groupNumber){
		//while(i<groupNumber){	
		String [] elevenJoint = new String[154];
		String [] fourJoint = new String[16];	
			for (j=i; j<i+171; j++){
				if (flag==0){
					currenFrame=str[i];
					System.out.println("current frame number->"+currenFrame+"\n");
					flag=1;
				}
				if (flagForLoop1==0){
					for(k=j+1; k<j+155; k++){
						elevenJoint[kk]=str[k];
						//System.out.println("Processing First 11 joint Array->"+elevenJoint[kk]);
						kk++;
					}
					processJoint(currenFrame, elevenJoint, finalJointPos);
					flagForLoop1=1;
				}
				if(flagForLoop2==0){
					for(l=k; l<k+16; l++){
						fourJoint[ll]=str[l];
						ll++;
					}
					flagForLoop2=1;
					processRemainingJoint(currenFrame, fourJoint, finalJointPos);
				}
			}
			flag=0;
			flagForLoop1=0;
			flagForLoop2=0;
			kk=0;
			ll=0;
			i=i+171;
		}
		return finalJointPos;
	}
	static String [] processJoint(String FrameNumber, String [] elevenJoint, String [] finalJointPos ){
		String text = FrameNumber;
		text = text.replace("\n", "").replace("\r", "");
		FrameNumber=text;
		int myFrame=Integer.parseInt(FrameNumber);
		System.out.println("myFrame------ProcessJoint outside if-------->"+myFrame);
		if(myFrame==frameNumberOnLoad){
			System.out.println("myFrame-------------ProcessJoint------------>"+myFrame);
			System.out.println("frameNumberOnLoad---ProcessJoint------------>"+frameNumberOnLoad);
			
			System.out.println("Inside processJoint () where Frame Number->"+FrameNumber);
			int i=0, j=0, k=0, l=0;
			int jointChoice=0;
			while(i<elevenJoint.length){
				switch(jointChoice){
					case 0:
						System.out.println("Joint name-->Head and joint number-------->1");
						break;
					case 1:
						System.out.println("Joint name-->NECK and joint number------------>2");
						break;
					case 2:
						System.out.println("Joint name-->TORSO and joint number----------->3");
						break;
					case 3:
						System.out.println("Joint name-->LEFT_SHOULDER and joint number--->4");
						break;
					case 4:
						System.out.println("Joint name-->LEFT_ELBOW and joint number------>5");
						break;
					case 5:
						System.out.println("Joint name-->RIGHT_SHOULDER and joint number-->6");
						break;
					case 6:
						System.out.println("Joint name-->RIGHT_ELBOW and joint number----->7");
						break;
					case 7:
						System.out.println("Joint name-->LEFT_HIP and joint number-------->8");
						break;
					case 8:
						System.out.println("Joint name-->LEFT_KNEE and joint number------->9");
						break;
					case 9:
						System.out.println("Joint name-->RIGHT_HIP and joint number------>10");
						break;
					case 10:
						System.out.println("Joint name-->RIGHT_KNEE and joint number----->11");
						break;
					default: 
						System.out.println("Invalid Joint ");
						break;
				}
				for(j=i;j<i+14;j++){
					for (k=j; k<j+10; k++){
						//if ((k%9)==0){
						if (k==(j+10-1)){
							//System.out.println("Orientation matrix->"+elevenJoint[k]);
							//System.out.println("Confidence value for this orientation->"+elevenJoint[k]);
						}
						else {
							//System.out.println("Orientation matrix (3*3) component->"+elevenJoint[k]);
						}
					}
					for (l=k;l<k+4;l++){
						if (l==(k+4-1)){
							//System.out.println("Position matrix->"+elevenJoint[k]);
							//System.out.println("Confidence value for this position->"+elevenJoint[l]);
						}
						else{
							if(jointChoice==0 || jointChoice==2)
								System.out.println("Position component->"+elevenJoint[l]);
							if(jointChoice==0){
								System.out.println("sharedCounter for 12 Length array "+VideoProcessingModule.sharedCounter);
								finalJointPos[VideoProcessingModule.sharedCounter]=elevenJoint[l];
								VideoProcessingModule.sharedCounter++;
							}	
							else if(jointChoice==2){
								finalJointPos[VideoProcessingModule.sharedCounter]=elevenJoint[l];
								VideoProcessingModule.sharedCounter++;
							}	
						}
					}
					break;
				}
				i=i+14;
				j=0;
				k=0;
				l=0;
				jointChoice=(jointChoice+1)%11;
			}
		}
		return finalJointPos;
	}
	static String [] processRemainingJoint(String FrameNumber, String [] fourJoint, String [] finalJointPos){
    	System.out.println("Inside processRemainingJoint () ( OUTSIDE IF )where Frame Number->"+FrameNumber);
    	String text = FrameNumber;
		text = text.replace("\n", "").replace("\r", "");
		FrameNumber=text;
		int myFrame=Integer.parseInt(FrameNumber);
		System.out.println("frameNumberOnLoad "+frameNumberOnLoad);
		System.out.println("myFrame "+myFrame);
		if(myFrame==frameNumberOnLoad){
			System.out.println("processRemainingJoint myFrame-------------->"+myFrame);
			System.out.println("processRemainingJoint frameNumberOnLoad---->"+frameNumberOnLoad);
			
			//System.out.println("Length of the fourJoint array->"+fourJoint.length);
			//System.out.println("Sent string array fourJoint[]->"+Arrays.toString(fourJoint)+"\n\n");
			System.out.println("Inside processRemainingJoint () where Frame Number->"+FrameNumber);
			int i=0, j=0;
			int jointChoice=0;
			//System.out.println("Four joint string array length->"+ fourJoint.length);
			while(i<fourJoint.length){
				switch(jointChoice){
					case 0:
							System.out.println("Joint name-->LEFT_HAND and joint number-------->12");
							break;
					case 1:
							System.out.println("Joint name-->RIGHT_HAND and joint number----------->13");
							break;
					case 2:
							System.out.println("Joint name-->LEFT_FOOT and joint number------------>14");
							break;
					case 3:
							System.out.println("Joint name-->RIGHT_FOOT and joint number----------->15");
							break;
					default: 
							System.out.println("Invalid Joint ");
							break;
				}
				for(j=i;j<i+4;j++){
					if (j==(i+4-1)){
						//System.out.println("Orientation matrix->"+fourJoint[j]);
						//System.out.println("Confidence value for this Position->"+fourJoint[j]);
					}
					else{ 
						if(jointChoice==0 || jointChoice==1)
							System.out.println("Position component->"+fourJoint[j]);
						if(jointChoice==0){
							finalJointPos[VideoProcessingModule.sharedCounter]=fourJoint[j];
							VideoProcessingModule.sharedCounter++;
						}
						else if(jointChoice==1){
							finalJointPos[VideoProcessingModule.sharedCounter]=fourJoint[j];
							VideoProcessingModule.sharedCounter++;
						}
					}
						//break;
				}
				i=i+4;
				j=0;
				jointChoice=(jointChoice+1)%4;
			}
		} 
		return finalJointPos;
	}
	public String[] calculateCentroid(int FrameNo1, int objPosIndex, String [] objNames, String rawTDS1, String parentPath1) throws IOException{
		String objectXYPos=parentPath1+"/"+rawTDS1+"_obj"+(objPosIndex+1)+".txt";
		System.out.println(("objectXYPos path "+objectXYPos));
		String testReturnedItem1=readFile(objectXYPos, StandardCharsets.UTF_8);
		String tokenize[]=testReturnedItem1.split("\n");
		int saveIndexJ=0;
		String frameNumber=Integer.toString(FrameNo1);
		for(int j=0; j<tokenize.length; j++){
			//System.out.println(" "+tokenize[j]);
			String temp[]=tokenize[j].split(",");
			for(int k=0; k<temp.length; k++){
				if(temp[0].equals(frameNumber)){
				//if((k+1)==FrameNo1){
					System.out.println("temp[k] contains "+temp[k]);
					saveIndexJ=j;
					break;
					}
				}
			}
			String processString[]=(tokenize[saveIndexJ]).split(",");
			for(int l=2; l<6;l++){
				System.out.print(" "+processString[l]);
			}
			System.out.println("\nprocessString Length()-->"+processString.length);
			System.out.println();
			return processString;
	}
}
