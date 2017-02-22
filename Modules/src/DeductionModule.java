/**
@author Rupam Bhattacharyya
*/
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public class DeductionModule{
	public String[] relationManager(String trainFileName, String objNames[], String jointPos[], int l, String rawTDS, String parentPath) throws OWLOntologyCreationException, IOException {
		System.out.println("Inside Deduction module: "+trainFileName);
		int flag1=0;
		int flagMatch=0;
		//Remember that the path of trainFileName may be changed; so that Alchemy can reach it.
		PrintWriter writer=new PrintWriter(trainFileName);
		String loadedOntoFeature[]=new String[11];
		//writer.print("Hello now we can write---");
		for(int i=0;i<objNames.length;i++){
			System.out.println("Obj Name-->"+objNames[i]);
			OntologyProcessingModule o=new OntologyProcessingModule();
			loadedOntoFeature=o.getObjectProperty(objNames[i]);			
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
				}
			}
			for(int kk=0; kk<objNames.length; kk++){
				System.out.println(objNames[kk]);
			}
			String upperCase;
			for(int k=0;k<(loadedOntoFeature.length-3);k++){
				System.out.println("Fired query result-->"+loadedOntoFeature[k]);
				if((loadedOntoFeature[k]).equals("[NONE]")){
					
				}
				else{
					if(k==0){
						// Added for Box to Box1
						if(flagMatch==1 && flag1==0)
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1)+i;
						else
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1);
						//writer.print(loadedOntoFeature[k]+"("+objNames[i]+")");
						writer.print(loadedOntoFeature[k]+"("+upperCase+")");
						writer.print("\n");
					}
					else if(k==1){
						if(flagMatch==1 && flag1==0)
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1)+i;
						else
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1);
						//writer.print(loadedOntoFeature[k]+"("+objNames[i]+")");
						writer.print(loadedOntoFeature[k]+"("+upperCase+")");
						writer.print("\n");
					}
					else if(k==2){
						if(flagMatch==1 && flag1==0)
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1)+i;
						else
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1);
						//writer.print(loadedOntoFeature[k]+"("+objNames[i]+")");
						writer.print(loadedOntoFeature[k]+"("+upperCase+")");
						writer.print("\n");
					}
					else if(k==3){
						if(flagMatch==1 && flag1==0)
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1)+i;
						else
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1);
						//writer.print(loadedOntoFeature[k]+"("+objNames[i]+")");
						writer.print(loadedOntoFeature[k]+"("+upperCase+")");
						writer.print("\n");
					}
					else if(k==4){
						if(flagMatch==1 && flag1==0)
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1)+i;
						else
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1);
						//writer.print("hasBaseConcavity("+objNames[i]+","+loadedOntoFeature[k]+")");
						writer.print("hasBaseConcavity("+upperCase+","+"\""+loadedOntoFeature[k]+"\""+")");
						writer.print("\n");
					}
					else if(k==5){
						if(flagMatch==1 && flag1==0)
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1)+i;
						else
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1);
						writer.print("hasImmovablePart("+upperCase+","+" "+loadedOntoFeature[k]+")");
						writer.print("\n");
					}
					else if(k==6){
						if(flagMatch==1 && flag1==0)
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1)+i;
						else
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1);
						writer.print("hasDetachablePart("+upperCase+","+" "+loadedOntoFeature[k]+")");
						writer.print("\n");
					}
					else if(k==7){
						if(flagMatch==1 && flag1==0)
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1)+i;
						else
							upperCase=objNames[i].substring(0, 1).toUpperCase() + objNames[i].substring(1);
						writer.print("hasNonDetachablePart("+upperCase+","+" "+loadedOntoFeature[k]+")");
						writer.print("\n");
					}
				}
			}
		}
		String recvdCornerObjPos[]=new String[12];
		String fourCornerObjPos[]=new String[5];
		String lowerToUpperCase;
		int cornerCount=0;
		for(int m=0; m<objNames.length; m++){
			VideoProcessingModule v=new VideoProcessingModule();
			recvdCornerObjPos=v.calculateCentroid(l, m, objNames, rawTDS, parentPath);
			//Storing
	 		for(int mm=2; mm<6;mm++){
	 			System.out.println("corners of obj-->"+recvdCornerObjPos[mm]);
	 			fourCornerObjPos[cornerCount]=recvdCornerObjPos[mm];
	 			cornerCount++;
	 		}
	 		cornerCount=0;
	 		//Actual centroid
	 		double xPos1=Double.parseDouble(fourCornerObjPos[0]);
	 		double xPos2=Double.parseDouble(fourCornerObjPos[2]);
	 		double yPos1=Double.parseDouble(fourCornerObjPos[1]);
	 		double yPos2=Double.parseDouble(fourCornerObjPos[3]);
	 		double centroidX=(xPos1+xPos2)/2;
	 		double centroidY=(yPos1+yPos2)/2;
	 		System.out.println("Centroid X pos-->"+centroidX);
	 		System.out.println("Centroid Y pos-->"+centroidY);
	 		//Extracting information from 4 Joint Pos given in jointPos[]
	 		for(int n=0; n<jointPos.length;n++){
	 			System.out.println("\nJoint positions "+jointPos[n]);
	 		}
	 		System.out.println("jointPos[] length-->"+jointPos.length);
	 		// Now distance calculation bet Head and obj************************
	 		Double firstRelX=Double.parseDouble(jointPos[0])-centroidX;
	 		Double firstRelY=Double.parseDouble(jointPos[1])-centroidY;
	 		Double distance1=Math.hypot(firstRelX, firstRelY);
	 		if(distance1>400.00 && distance1<=800.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			writer.print("Near("+lowerToUpperCase+", HEAD)");
	 			//writer.print("---Distance---"+distance1);
	 		}
	 		else if(distance1>200.00 && distance1<=400.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			writer.print("Near("+lowerToUpperCase+", HEAD)");
	 			//writer.print("---Distance---"+distance1);
	 		}
	 		else if(distance1>100.00 && distance1<=200.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			writer.print("Touch("+lowerToUpperCase+", HEAD)");
	 			//writer.print("---Distance---"+distance1);
	 		}
	 		else if(distance1>=0.00 && distance1<=100.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//writer.print("Touch("+objNames[m]+", HEAD)");
	 			writer.print("Touch("+lowerToUpperCase+", HEAD)");
	 			//writer.print("---Distance---"+distance1);
	 		}
	 		//Added to see why the blank lines in Train file appears.
	 		else{
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			// Since Ignore has been dropped from MLN; we need to rename "ignore" as the new "Far". (09/01/2017)
	 			writer.print("Medium("+lowerToUpperCase+", HEAD)");
	 			//writer.print("---Distance---"+distance1);
	 		}
	 		
	 		writer.print("\n");
	 		System.out.println("Distance between head and box-->"+distance1);
	 		// Now Torso and obj***********************************************
	 		firstRelX=Double.parseDouble(jointPos[3])-centroidX;
	 		firstRelY=Double.parseDouble(jointPos[4])-centroidY;
	 		Double distance2=Math.hypot(firstRelX, firstRelY);
	 		System.out.println("Distance between Torso and box-->"+distance2);
	 		if(distance2>400.00 && distance2<=800.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			writer.print("Near("+lowerToUpperCase+", TORSO)");
	 			//writer.print("---Distance---"+distance2);
	 		}
	 		else if(distance2>200.00 && distance1<=400.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			writer.print("Near("+lowerToUpperCase+", TORSO)");
	 			//writer.print("---Distance---"+distance2);
	 		}
	 		else if(distance2>100.00 && distance2<=200.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			writer.print("Touch("+lowerToUpperCase+", TORSO)");
	 			//writer.print("---Distance---"+distance2);
	 		}
	 		else if(distance2>=0.00 && distance2<=100.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//writer.print("Touch("+objNames[m]+", TORSO)");
	 			writer.print("Touch("+lowerToUpperCase+", TORSO)");
	 			//writer.print("---Distance---"+distance2);
	 		}
	 		//Added to see why the blank lines in Train file appears.
	 		else{
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);	
	 			writer.print("Medium("+lowerToUpperCase+", TORSO)");
	 			//writer.print("---Distance---"+distance2);
	 		}
	 		writer.print("\n");
	 		//Now Left Hand and obj********************************************
	 		firstRelX=Double.parseDouble(jointPos[6])-centroidX;
	 		firstRelY=Double.parseDouble(jointPos[7])-centroidY;
	 		Double distance3=Math.hypot(firstRelX, firstRelY);
	 		System.out.println("Distance between Left Hand and box-->"+distance3);
	 		if(distance3>400.00 && distance3<=800.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			writer.print("Near("+lowerToUpperCase+", LHAND)");
	 			//writer.print("---Distance---"+distance3);
	 		}
	 		else if(distance3>200.00 && distance3<=400.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			writer.print("Near("+lowerToUpperCase+", LHAND)");
	 			//writer.print("---Distance---"+distance3);
	 		}
	 		else if(distance3>100.00 && distance3<=200.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			writer.print("Touch("+lowerToUpperCase+", LHAND)");
	 			//writer.print("---Distance---"+distance3);
	 		}
	 		else if(distance3>=0.00 && distance3<=100.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//writer.print("Touch("+objNames[m]+", LHAND)");
	 			writer.print("Touch("+lowerToUpperCase+", LHAND)");
	 			//writer.print("---Distance---"+distance3);
	 		}
	 		//Added to see why the blank lines in Train file appears.
	 		else{
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			writer.print("Medium("+lowerToUpperCase+", LHAND)");
	 			//writer.print("---Distance---"+distance3);
	 		}
	 		writer.print("\n");
	 		// Now Right Hand and obj.***************************************
	 		firstRelX=Double.parseDouble(jointPos[9])-centroidX;
	 		firstRelY=Double.parseDouble(jointPos[10])-centroidY;
	 		Double distance4=Math.hypot(firstRelX, firstRelY);
	 		System.out.println("Distance between Right Hand and box-->"+distance4);
	 		if(distance4>400.00 && distance4<=800.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			writer.print("Near("+lowerToUpperCase+", RHAND)");
	 			//writer.print("---Distance---"+distance4);
	 		}
	 		else if(distance4>200.00 && distance4<=400.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			writer.print("Near("+lowerToUpperCase+", RHAND)");
	 			//writer.print("---Distance---"+distance4);
	 		}
	 		else if(distance4>100.00 && distance4<=200.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			writer.print("Touch("+lowerToUpperCase+", RHAND)");
	 			//writer.print("---Distance---"+distance4);
	 		}
	 		else if(distance4>=0.00 && distance4<=100.00){
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//writer.print("Touch("+objNames[m]+", RHAND)");
	 			writer.print("Touch("+lowerToUpperCase+", RHAND)");
	 			//writer.print("---Distance---"+distance4);
	 		}
	 		//Added to see why the blank lines in Train file appears.
	 		else{
	 			if(flagMatch==1 && flag1==0)
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1)+m;
	 			else
	 				lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);
	 			//lowerToUpperCase=objNames[m].substring(0, 1).toUpperCase() + objNames[m].substring(1);	
	 			writer.print("Medium("+lowerToUpperCase+", RHAND)");
	 			//writer.print("---Distance---"+distance4);
	 		}
	 		writer.print("\n");
	 		
	 	}
		int n=objNames.length;
		int r=2;
		// Now, we need to prevent calculations related to o-o relations when there is a single object.\
		int flag=0;
		long res=1;
		if(n>=r)
        {
            res=getFact(n)/(getFact(n-r)*getFact(r));
            System.out.println("The result is *********---->"+res);
        }
        else{ 
        	System.out.println("r cannot be greater than n");
        	flag=1;
        }
		String returnRelName1[]=new String[(int) res];
		returnRelName1=objRelFinder(l, objNames, rawTDS, parentPath);
		for(int p=0;p<returnRelName1.length;p++){
			if(flag!=1){
				writer.print(returnRelName1[p]);
				writer.print("\n");
			}
		}
		writer.close();
		return null;
	}
	public String[] objRelFinder(int l, String[] objNames, String rawTDS, String parentPath) throws IOException {
		// calculate nC2 where n is the total objects in objNames[]
		//*********************Start***********************
		int flagMatch111=0;
		int flag111=0;
		for(int ni=0; ni<objNames.length;ni++){
			if(objNames.length>1){
				for(int nj=1;nj<objNames.length;nj++){
					if(objNames[ni].equals(objNames[nj])){
						System.out.println("Carry On..Match found");
						flagMatch111=1;
					}
					else
						break;
				}
				System.out.println("*****");
			}
			else{
				flag111=1;	
				break;
			}
		}
		if(flagMatch111==1 && flag111==0){
			for(int k=0; k<objNames.length;k++){
				//objNames[k]=objNames[k]+k;
			}
		}
		for(int kk=0; kk<objNames.length; kk++){
			System.out.println(objNames[kk]);
		}
		//flag1=0;
		//flagMatch=0;
		//*********************End*************************
		int n=objNames.length;
		int r=2;
		long res=1;
		if(n>=r)
        {
            res=getFact(n)/(getFact(n-r)*getFact(r));
            System.out.println("The result is *********---->"+res);
        }
        else System.out.println("r cannot be greater than n");
		
		String returnRelName[]=new String[(int) res];
		int totRel=0;
		//calculate o-o relations and return string "Far(o1, o2)"
		int ii=0;
		int jj=0;
		//double centroidX_ii;
		//double centroidY_ii;
		double centroidX_jj;
		double centroidY_jj;
		double resX;
		double resY;
		double distance;
		// Centroid calculation
	 	// Main for loop
		String recvdCornerObjPos[]=new String[12];
		String fourCornerObjPos[]=new String[5];
		String storeMe;
		
		for(ii=0; ii<objNames.length; ii++){
			String lowerUpper1;
			String lowerUpper2;
			VideoProcessingModule v1=new VideoProcessingModule();
			recvdCornerObjPos=v1.calculateCentroid(l, ii, objNames, rawTDS, parentPath);
	 		double xPos1=Double.parseDouble(recvdCornerObjPos[2]);
	 		double xPos2=Double.parseDouble(recvdCornerObjPos[4]);
	 		double yPos1=Double.parseDouble(recvdCornerObjPos[3]);
	 		double yPos2=Double.parseDouble(recvdCornerObjPos[5]);
	 		double centroidX_ii=(xPos1+xPos2)/2;
	 		double centroidY_ii=(yPos1+yPos2)/2;
			  for(jj=ii+1; jj<objNames.length;jj++){
				  VideoProcessingModule v2=new VideoProcessingModule();
				  recvdCornerObjPos=v2.calculateCentroid(l, jj, objNames, rawTDS, parentPath);
				  xPos1=Double.parseDouble(recvdCornerObjPos[2]);
			 	  xPos2=Double.parseDouble(recvdCornerObjPos[4]);
			 	  yPos1=Double.parseDouble(recvdCornerObjPos[3]);
			 	  yPos2=Double.parseDouble(recvdCornerObjPos[5]);
			 	  centroidX_jj=(xPos1+xPos2)/2;
			 	  centroidY_jj=(yPos1+yPos2)/2;
			 	  resX=centroidX_ii-centroidX_jj;
			 	  resY=centroidY_ii-centroidY_jj;
			 	  distance=Math.hypot(resX, resY);
				  //System.out.println(ii+" "+jj);
			 	  //reasoning between distances
			 	 if(distance>400.00 && distance<=800.00){
			 			//writer.print("Far("+objNames[m]+", RHAND)");
			 		 	if(flagMatch111==1 && flag111==0){
			 		 		lowerUpper1=objNames[ii].substring(0, 1).toUpperCase() + objNames[ii].substring(1)+ii;
			 		 		lowerUpper2=objNames[jj].substring(0, 1).toUpperCase() + objNames[jj].substring(1)+jj;
			 		 	}
			 		 	else{
			 		 		lowerUpper1=objNames[ii].substring(0, 1).toUpperCase() + objNames[ii].substring(1);
			 		 		lowerUpper2=objNames[jj].substring(0, 1).toUpperCase() + objNames[jj].substring(1);
			 		 	}
			 		 	storeMe="Near("+lowerUpper1+","+lowerUpper2+")";
			 		    returnRelName[totRel]=storeMe;
			 		    totRel++;
			 	}
			 	else if(distance>200.00 && distance<=400.00){
			 			//lowerUpper1=objNames[ii].substring(0, 1).toUpperCase() + objNames[ii].substring(1);
			 			//lowerUpper2=objNames[jj].substring(0, 1).toUpperCase() + objNames[jj].substring(1);
				 		if(flagMatch111==1 && flag111==0){
			 		 		lowerUpper1=objNames[ii].substring(0, 1).toUpperCase() + objNames[ii].substring(1)+ii;
			 		 		lowerUpper2=objNames[jj].substring(0, 1).toUpperCase() + objNames[jj].substring(1)+jj;
			 		 	}
			 		 	else{
			 		 		lowerUpper1=objNames[ii].substring(0, 1).toUpperCase() + objNames[ii].substring(1);
			 		 		lowerUpper2=objNames[jj].substring(0, 1).toUpperCase() + objNames[jj].substring(1);
			 		 	}
				 		storeMe="Near("+lowerUpper1+","+lowerUpper2+")";
		 		 		returnRelName[totRel]=storeMe;
		 		 		totRel++;
			 	}
			 	else if(distance>100.00 && distance<=200.00){
			 			//lowerUpper1=objNames[ii].substring(0, 1).toUpperCase() + objNames[ii].substring(1);
			 			//lowerUpper2=objNames[jj].substring(0, 1).toUpperCase() + objNames[jj].substring(1);
				 		if(flagMatch111==1 && flag111==0){
			 		 		lowerUpper1=objNames[ii].substring(0, 1).toUpperCase() + objNames[ii].substring(1)+ii;
			 		 		lowerUpper2=objNames[jj].substring(0, 1).toUpperCase() + objNames[jj].substring(1)+jj;
			 		 	}
			 		 	else{
			 		 		lowerUpper1=objNames[ii].substring(0, 1).toUpperCase() + objNames[ii].substring(1);
			 		 		lowerUpper2=objNames[jj].substring(0, 1).toUpperCase() + objNames[jj].substring(1);
			 		 	}
				 		storeMe="Touch("+lowerUpper1+","+lowerUpper2+")";
		 		 		returnRelName[totRel]=storeMe;
		 		 		totRel++;
			 	}
			 	else if(distance>=0.00 && distance<=100.00){
			 			//lowerUpper1=objNames[ii].substring(0, 1).toUpperCase() + objNames[ii].substring(1);
			 			//lowerUpper2=objNames[jj].substring(0, 1).toUpperCase() + objNames[jj].substring(1);
				 		if(flagMatch111==1 && flag111==0){
			 		 		lowerUpper1=objNames[ii].substring(0, 1).toUpperCase() + objNames[ii].substring(1)+ii;
			 		 		lowerUpper2=objNames[jj].substring(0, 1).toUpperCase() + objNames[jj].substring(1)+jj;
			 		 	}
			 		 	else{
			 		 		lowerUpper1=objNames[ii].substring(0, 1).toUpperCase() + objNames[ii].substring(1);
			 		 		lowerUpper2=objNames[jj].substring(0, 1).toUpperCase() + objNames[jj].substring(1);
			 		 	}
		 		 		storeMe="Touch("+lowerUpper1+","+lowerUpper2+")";
		 		 		returnRelName[totRel]=storeMe;
		 		 		totRel++;
			 	}
			 	else{
			 		if(flagMatch111==1 && flag111==0){
		 		 		lowerUpper1=objNames[ii].substring(0, 1).toUpperCase() + objNames[ii].substring(1)+ii;
		 		 		lowerUpper2=objNames[jj].substring(0, 1).toUpperCase() + objNames[jj].substring(1)+jj;
		 		 	}
		 		 	else{
		 		 		lowerUpper1=objNames[ii].substring(0, 1).toUpperCase() + objNames[ii].substring(1);
		 		 		lowerUpper2=objNames[jj].substring(0, 1).toUpperCase() + objNames[jj].substring(1);
		 		 	}
	 		 		storeMe="Medium("+lowerUpper1+","+lowerUpper2+")";
	 		 		returnRelName[totRel]=storeMe;
	 		 		totRel++;
			 	}
			  }
		}
		return returnRelName;
		
		//return null;
	}
	public long getFact(int n)
    {
        long f=1;

        for(int i=n;i>=1;i--)
        {
        f*=i;
        }

        return f;
    }
	public void typeCommand(String groundTruthSubActivity, String rawTDS){
		System.out.println("\nInside DEDUCTION module the ground truth sub-activity is-->"+groundTruthSubActivity);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println();
		String pasteCommand1=rawTDS+"-"+groundTruthSubActivity+"-train.db";
		String pasteCommand2=rawTDS+"-"+groundTruthSubActivity+"-out.mln";
		//System.out.println("pasteCommand1 "+pasteCommand1);
		//System.out.println("pasteCommand2 "+pasteCommand2);
		System.out.println("Please OPEN YOUR Terminal");
		System.out.println("GO to alchemy/bin as a ROOT user");
		//System.out.println("PASTE ./learnwts -d -i affordancerules.mln -o closing-out.mln -t closing-train.db -ne Affordance");
		System.out.println("PASTE ./learnwts -d -i affordancerules.mln -o " +pasteCommand2+ " -t "+pasteCommand1+ " -ne Affordance");
		System.out.println();
	}
}
