
/**
 * Write a description of BatchInversions here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import java.io.*;

public class BatchInversions {

    //I started with the image I wanted (inImage)
    public ImageResource makeInversion(ImageResource inImage) {
        //I made a blank image of the same size
        ImageResource outImage = new ImageResource(inImage.getWidth(), inImage.getHeight());
        //for each pixel in outImage
        for (Pixel pixel: outImage.pixels()) {
            //look at the corresponding pixel in inImage
            Pixel inPixel = inImage.getPixel(pixel.getX(), pixel.getY());
            //invert pixel's red value
            pixel.setRed(255 - inPixel.getRed());
            //invert pixel's green value
            pixel.setGreen(255 - inPixel.getGreen());
            //invert pixel's blue value
            pixel.setBlue(255 - inPixel.getBlue());
        }
        //outImage is your answer
        return outImage;
    }

    public void selectAndConvert () {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(f);
            ImageResource invert = makeInversion(inImage);
            String fname = inImage.getFileName();
            String newName = "inverted-" + fname;
            invert.setFileName(f.getParentFile() + "\\" + newName);
            invert.draw();
            invert.save();
        }
    }
}
