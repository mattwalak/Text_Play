package text_play;

import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class ImageConvert {
    BufferedImage img;
    String complete;
    int marginX, marginY, widthOfPage, heightOfPage,pagesX, pagesY,textWidth,textHeight;
    public ImageConvert(String filename, int width, int height, int margX, int margY){
        BufferedImage in = null;
        try {
            in = ImageIO.read(new File(filename));
        } catch (Exception e) {
            e.printStackTrace();
        }
        int w = in.getWidth();
        int h = in.getHeight();
        BufferedImage after = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(1.0, (.35/.9));
        AffineTransformOp scaleOp = 
        new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        after = scaleOp.filter(in, after);
        img = after;
        complete = "";
        marginX = margX;
        marginY = margY;
        heightOfPage = height;
        widthOfPage = width;
        pagesX =  (int)Math.ceil((double)img.getWidth()/(widthOfPage+marginX));
        pagesY =  (int)Math.ceil((double)img.getHeight()/(heightOfPage+marginY));
        textWidth = pagesX * (widthOfPage+marginX);
        textHeight = pagesY * (heightOfPage+marginY);
        System.out.println("Demensions of paper: "+(textWidth)+" X "+textHeight);
        System.out.println("Demensions of image: "+(img.getWidth())+" X "+img.getHeight());
    }
    
    public void getText(){
        for(int y = 0; y < pagesY; y++){
            getPageRow(y);
        }
    }
    
    public void getPageRow(int y){
        for(int x = 0; x < pagesX; x++){
            getPage(x,y);
        }
    }
    
    public void getPage(int pX, int pY){
        for(int y = 0; y < heightOfPage; y++){
            if(pY*(heightOfPage + marginY)+y >= img.getHeight()){
                while(y<heightOfPage+1){
                    for(int i = 0; i < widthOfPage; i++){
                        complete = complete + "-";
                    }
                    y++;
                }
                break;
            }
            getRow(pX,pY,y);
        }
    }
    
    public void getRow(int pX,int pY,int y){
        for(int x = 0; x < widthOfPage;x++){
            int currentX = pX*(widthOfPage+marginX)+x;
            int currentY = pY*(heightOfPage + marginY)+y;
            System.out.print(img.getWidth()+"  ");
            if(currentX >= img.getWidth()){
                System.out.println("Overflow Row");
                while(x<widthOfPage){
                    complete = complete + "-";
                    x++;
                }
                break;
            }
            System.out.print("Getting pixel "+currentX+"  "+currentY+"   ");
            int pixel = img.getRGB(currentX, currentY);
            System.out.println(pixel);
            if(pixel < -1){
                complete = complete+"1";
            }else{
                complete = complete+"0";
        }
        }
    }
    
}
