//Set channel names
//Need to be in the same order as the channels are imported from the .tiff files
setChannelNames('DAPI',
'Glucagon',
'CD324',
'Somatostatin',
'CD31',
'Chromogranin A',
'Cytokeratin',  
'Amylase',
'Lactoferrin', 
'Trypsin', 
'Pancretic Lipase')

//Set channel colors to make it uniform
//Need to be in the same order as the channels are imported from the .tiff files
setChannelColors(
    getColorRGB(0, 0, 255),        //DAPI
    getColorRGB(0, 255, 0),        //Glucagon
    getColorRGB(255, 0, 255),      //CD324
    getColorRGB(255, 255, 255),    //Somatostatin
    getColorRGB(230, 179, 204),    //CD31
    getColorRGB(153, 51, 0),       //Chromogranin A
    getColorRGB(255, 255, 0),      //Cytokeratin
    getColorRGB(0, 255, 255),       //Amylase
    getColorRGB(153, 204, 204),    //Lactoferrin
    getColorRGB(204, 128, 57),     //Trypsin
    getColorRGB(102, 77, 179)      //Pancreatic Lipase
)