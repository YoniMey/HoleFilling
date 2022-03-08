# HoleFilling

At first I did't understand the task instructions correctly, so I implament the library in a different way.
I thought that the B group need to be related for each pixel (the 4 or 8 "nibors" of the pixel).
So I calculat dynamcli the color of each pixel from the outside of the hole to its inside.
I think that the resulets were interesting so I left an option to use my algorithem (by pass "other" in args[6]).


#Instruction

First an installation of HoleFillingLib to Maven local repository need to be done by the following line: 
    mvn install:install-file -Dfile=<path-to-file> -DpomFile=<path-to-pom_file>
Run the HoleFillingApp by passing the following args:
    args[0] - the original image path. <path>\<imageName>.<format>
    args[1] - the image mask path. <path>\<imageName>.<format>
    args[2] - the fixed image path. <path>\<fixedImageName>.<format>
    args[3] - z. double, try z = 2.
    args[4] - epsilon. double, try epsilon = 0.0000001.
    args[5] - boundary type - 8 or 4.
    args[6] - "original" for the original calculation. "other" for my calculation.
  
  mask and originalImage need to be at the same size.
  mask need to be a white image with black "hole" where you want the hole in the originalImage. 
  
