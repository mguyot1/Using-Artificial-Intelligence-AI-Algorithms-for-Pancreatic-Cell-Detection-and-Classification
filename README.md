# Using Artificial Intelligence (AI) Algorithms for Pancreatic Cell Detection and Classification

The main goal for this project is to take a whole slide image of a pancreatic tissue section, segment each cell and then classify it into the correct cellular subtype. 

Background: Generally, the pancreas can be broken up into endocrine, exocrine and vasculature. The endocrine can further be subdivided into the alpha, beta and delta cells whereas the exocrine can be further divided into acinar and ductal cells. Manual detection and classification of cells can be immensly time consuming and be subject to human bias. In training an AI algorithm, this process can be streamlined to happen much faster and reduce the impact of human bias. However, the challenge is creating an algorithm that is accurate enough, but not overfitted to a single data set. 

Methods: Whole Slide Images from a control pancreas (n=1) were broken up into Regions of Interest (ROI's) that underwent a binary thresholding step to improve the signal to noise ratio. The binarized image was then laoded into QuPath and segmented. Then, an expert pathologist manually graded ~6000 cells for the training data set. They then manually classified a separate region of interest with ~2000 cells. The validity of each of the algorithm types (ANN, KNN and RT) were checked on the testing data set. 
![Workflow](https://user-images.githubusercontent.com/73506962/233518497-f7981b66-ab7b-4f2d-91d8-e8185af59941.png)

Results:
