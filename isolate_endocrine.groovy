cells = getCellObjects()
imageData = getCurrentImageData();
def classifier = loadObjectClassifier('ROI5_RT_endo_exo_vasc_unlabeled')
def hierarchy = getCurrentHierarchy()
classifier.classifyObjects(imageData, cells, true)

selectObjectsByClassification("Vasculature");
clearSelectedObjects(true);

selectObjectsByClassification("Exocrine");
clearSelectedObjects(true);


selectObjectsByClassification("Not Stained");
clearSelectedObjects(true);



/*
setOfCellsToRemove = getCellObjects().findAll{it.getPathClass() != null}
remove(setOfCellsToRemove)
//run second classifier on remaining cells
addObjects(setOfCellsToRemove)


runObjectClassifier("Classifier 1", "Classifier 2", "Classifier 3")

def classifiers = [
    "Classifier 1",
    "Classifier 2"
]

// Reset existing classifications
resetDetectionClassifications()

// Loop through all the classifiers in order
def imageData = getCurrentImageData()
def cells = getCellObjects()
for (name in classifiers) {
    // Just keep the cells that haven't been classified
    cells = cells.findAll {it.getPathClass() == null}
    // Apply the next classifier
    def classifier = loadObjectClassifier(name)
    classifier.classifyObjects(imageData, cells, false)
}
fireHierarchyUpdate()
*/