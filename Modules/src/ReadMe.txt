Following procedure need to be followed to run all the above files successfully.

Input:-------

A1. The directory structure in Eclipse project should be the following:
   Affordance (Java project name)
    -src
    -CAD-120 annotations
    -Output
    
A2. All my CAD-120 annotations are present in  "/home/rupam/Documents/JavaPhDCode/Affordance". You have to replace the content present in line number 46 of "MasterMain.java" with your own location of CAD-120 annotations.

A3. A particular high level activity has been represented by a unique ten digit string (like 1204150645) in CAD-120 dataset. For testing / training on a new high level activity; you have to manually change the variable name (MasterMain.rawTDS) with a specific ten digit string (line number 49 of "MasterMain.java").   
   
Output:------

B1. Following three folders will be generated in the java project Affordance (mentioned above). Test/Train files will be present in the respective folders whose structure has already been mentioned in the paper. However, the result folder will be empty; the reason of which has been explained in "Result" folder of this repository. 
    
    -Test folder e.g. (1-ArrangingObjects-0510175411-Test)
    -Train folder e.g. (1-ArrangingObjects-0510175411-Train)
    -Result folder e.g. (1-ArrangingObjects-0510175411-Result)
