//function created by Yau Mun Lim (@ym.lim) and posted to the image.sc forum July 28, 2022
//accessed at: https://forum.image.sc/t/transfer-class-from-points-to-detections/70119/7

def hierarchy = getCurrentHierarchy()
def points = getAnnotationObjects().findAll{it.getROI().isPoint()}

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
fireHierarchyUpdate()