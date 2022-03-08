# HoleFilling

At first I did't understand the task instructions correctly, so I implament the library in a different way.<br />
I thought that the B group needs to be related to each pixel (the 4 or 8 "neighbor" of the pixel).<br />
So I calculated dynamicly the color of each pixel from the outside of the hole to the inside.<br />
I think that the resulets were interesting so I left an option to use my algorithem (by pass "2" in args[6]).<br />


## Instructions

First an installation of HoleFillingLib to Maven local repository needs to be done by the following line: 
```
mvn install:install-file -Dfile=<path-to-file> -DpomFile=<path-to-pom_file>
```
path-to-file : ```<path of the project>\HoleFillingLib\out\artifacts\HoleFillingLib_jar\HoleFillingLib.jar```<br />
path-to-pom_file : ```<path of the project>\HoleFillingLib\pom.xml```

(for some reasone I could not run the maven exec:java command by the time I needed to submit the task. Instead I ran it from Intellij).<br />
Run the HoleFillingApp by passing the following args: 
```
    args[0] - the original image path. <path>\<imageName>.<format>
    args[1] - the image mask path. <path>\<imageName>.<format>
    args[2] - the fixed image path. <path>\<fixedImageName>.<format>
    args[3] - z
    args[4] - epsilon
    args[5] - boundary type - 8 or 4.
    args[6] - 1 for the original calculation, 2 for my calculation and 3 for the bonus calculation.
```
* In case that the library installation dose not work I added a combined project with both the app and the library classes.
## assumptions
  * Mask and originalImage need to be at the same size.<br />
  * Mask needs to be a white image with a black "hole" where you want the hole to be in the originalImage. 
  
