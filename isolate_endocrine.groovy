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
