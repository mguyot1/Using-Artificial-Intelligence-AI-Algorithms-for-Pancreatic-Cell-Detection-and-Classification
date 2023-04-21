//import statements
import java.util.ArrayList;

//get number of objects (equal to the number of cells)
cells = getCellObjects()
numCells = cells.size();

//get current image data and hierarchy
imageData = getCurrentImageData();
def hierarchy = getCurrentHierarchy()

//define classifier going to test validity of (NEED TO CHANGE THIS STRING WHEN CHANGING CLASSIFIER OF INTEREST)
def classifier = loadObjectClassifier('endo_vs_exo_vasc')

//apply classifier (will change the class of each cell)
classifier.classifyObjects(imageData, cells, true)

//create variables
int incorrect = 0;
int correct = 0;
ArrayList<String> predictedData = new ArrayList<String>();
ArrayList<String> tempPredictedData = new ArrayList<String>();
ArrayList<String> labels = new ArrayList<String>();
ArrayList<String> trueData = new ArrayList<String>();

//read all of the predicted cell classes from classifier and add to new ArrayList 
for(int i=0;i<cells.size();i++)
{
    predictedData.add(cells[i].getPathClass().toString());
}
 
//create temp sorted list of data so that labels can be accessed
for(int i=0;i<cells.size();i++)
{
    tempPredictedData.add(predictedData.get(i));
}

//sort temp data
Collections.sort(tempPredictedData);
//print 'temp' + tempPredictedData
//print 'classafter:' + predictedData

//add all new isntances of class (should result in ArrayList of size numLabels - 1)
for(int i = 0;i< tempPredictedData.size()-1;i++) {
   if(tempPredictedData.get(i) != tempPredictedData.get(i+1)) {
      labels.add(tempPredictedData.get(i)) 
   }
}
labels.add(tempPredictedData.get(tempPredictedData.size()-1)) //add the last label since it was not added in for-loop

//print labels (ensure that this is correct to make sure confusion matrix makes sense)
print 'labels: ' + labels

//return how many class options there are
int numClasses = labels.size();

//return points (MANAULLY GRADED TISSUE MASK)
def points = getAnnotationObjects().findAll{it.getROI().isPoint()}

//set class of each cell to the same class as the point inside of the cell
//this for-loop logic was created by Yau Mun Lim (@ym.lim) and posted to the image.sc forum July 28, 2022
//accessed at: https://forum.image.sc/t/transfer-class-from-points-to-detections/70119/7
for (def pointGroup in points) {
    def pathClass = pointGroup.getPathClass()
    
    for (def point in pointGroup.getROI().getAllPoints()) {
        double x = point.getX()
        double y = point.getY()
        int z = 0 // assuming not Z stack
        int t = 0 // assuming not time series

        def cell = PathObjectTools.getObjectsForLocation(hierarchy, x, y, z, t, 0).findAll{it.isDetection()}
        cell.each{it.setPathClass(pathClass)}
    }
}

//read all of the true cell classes from the points and add to new ArrayList 
for(int i=0;i<cells.size();i++)
{
    trueData.add(cells[i].getPathClass().toString());
}
//print 'trueData' + trueData

//create instance of confusion matrix with size of nxn where n is the number of classes
int [][] confusionMatrix = new int[numClasses][numClasses];

for(int i=0; i < numCells;i++) {
    String predictedLabel = predictedData.get(i);                                            //get the predicted label for cell i
    //print 'predicted' + predictedLabel
    String trueLabel = trueData.get(i);                                                      //get the true label for cell i
    //print 'true' + trueLabel
    int predictedLabelIndex = labels.indexOf(predictedLabel);                                //get the index of the predicted label in confusion matrix
    //print ('predicted: ' + predictedLabel + ' which is: ' + predictedLabelIndex)
    int trueLabelIndex = labels.indexOf(trueLabel);                                          //get the index of the true label in confusion matrix
    confusionMatrix[predictedLabelIndex][trueLabelIndex] += 1;                               //increment the cell of the confusion matrix by one 
    
    
    if(predictedLabel != trueLabel) {                                                        //add up how many incorrect classifications there were
       //print('BAD - predicted: ' + predictedLabel + ' but was actually: ' + trueLabel + ' at position: ' + i)
       incorrect++
    }
    else {                                                                                   //add up how many correct classifications there were
       //print 'GOOD - predicted: ' + predictedLabel + '(' + predictedLabelIndex + ')' + 'and was : ' + trueLabel + '(' + trueLabelIndex + ')' + ' at position: ' + i
      correct++;
  }
}

//print confusion matrix results
for(int i=0;i<numClasses;i++)
{
    print confusionMatrix[i]
}

//print results of classifier validation
print 'The number of correct classification was: ' + correct
print 'The number of incorrect classification was: ' + incorrect
print 'The accuracy of the ANN is: ' + (correct/numCells)*100;


