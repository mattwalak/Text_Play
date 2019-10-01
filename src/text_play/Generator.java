/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package text_play;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

/**
 *
 * @author Steve
 */
public class Generator {
    ImageConvert img;
    String complete, in;
    
    public Generator(){
    }
    
    public String genorateText(int where, int chars){ //where = 1 --> from website
        String out = "";
        if(where == 1){
            HtmlUnitDriver driver = new HtmlUnitDriver();
            driver.navigate().to("http://www.lipsum.com/");
            WebElement bytes = driver.findElement(By.id("bytes"));
            bytes.click();
            WebElement amount = driver.findElement(By.id("amount"));
            amount.clear();
            amount.sendKeys(Integer.toString(chars));
            amount.click();
            WebElement start = driver.findElement(By.id("start"));
            start.click();
            
            
            WebElement generate = driver.findElement(By.id("generate"));
            generate.click();
            WebElement text = driver.findElement(By.id("lipsum"));
            //System.out.println(text.getText());
            //System.out.println(removeLines(text.getText()));
            out = removeLines(text.getText());
            driver.close();
        }
        
        return out;
    }
    
    public void getPictureBold(ImageConvert pic){
        img = pic;
        img.getText();
        in = img.complete;
        System.out.println(in);
        String genorated = genorateText(1,in.length());
        System.out.println(genorated);
        complete = "<span class=\"light\">"+genorated;
        int off = 20;
        int current = 0;
        for(int i=0;i<in.length();i++){
            char c = in.charAt(i);
            if(c == '1' && current == 0){
                complete = complete.substring(0, i+off)+"</span><span class=\"dark\">"+complete.substring(i+off);
                off = off + 26;
                current = 1;
            }
            if(c != '1' && current == 1){
                complete = complete.substring(0, i+off)+"</span><span class=\"light\">"+complete.substring(i+off);
                off = off + 27;
                current = 0;
            }
        }
        complete = complete + "</style>";
        System.out.println(complete);
    }
    
    
    public String removeLines(String remove){
        while(remove.indexOf("\n")!= -1){
            remove = remove.substring(0,remove.indexOf("\n"))+ remove.substring(remove.indexOf("\n")+1);
        }
        return remove;
    }
    
    public static void wait(int time){
        try{
                Thread.sleep(time);
            }catch(Exception e){
                
            }
    }
}
