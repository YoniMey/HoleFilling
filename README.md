# HoleFilling

At first I did't understand the task instructions correctly, so I implament the library in a different way.<br />
I thought that the B group need to be related for each pixel (the 4 or 8 "nibors" of the pixel).<br />
So I calculat dynamcli the color of each pixel from the outside of the hole to its inside.<br />
I think that the resulets were interesting so I left an option to use my algorithem (by pass "other" in args[6]).<br />


## Instruction

First an installation of HoleFillingLib to Maven local repository need to be done by the following line: 
```
mvn install:install-file -Dfile=<path-to-file> -DpomFile=<path-to-pom_file>
```
path-to-file : ```<path of the project>\HoleFillingLib\out\artifacts\HoleFillingLib_jar\HoleFillingLib.jar```<br />
path-to-pom_file : ```<path of the project>\HoleFillingLib\pom.xml```
    
Run the HoleFillingApp by passing the following args: (for some reasone I cuodent run the maven exec:java command by the time I needed to send it. Instead i ran it from Intellij).
```
    args[0] - the original image path. <path>\<imageName>.<format>
    args[1] - the image mask path. <path>\<imageName>.<format>
    args[2] - the fixed image path. <path>\<fixedImageName>.<format>
    args[3] - z
    args[4] - epsilon
    args[5] - boundary type - 8 or 4.
    args[6] - 1 for the original calculation, 2 for my calculation and 3 for the bonus calculation.
```
* In case that the library install dosen't work I add a combine project with both the app and the library classes.
## assumption
  * mask and originalImage need to be at the same size.<br />
  * mask need to be a white image with black "hole" where you want the hole in the originalImage. 
  
