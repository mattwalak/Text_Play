package text_play;

import java.awt.*;
import java.awt.datatransfer.*;

public class Text_Play {

    public static void main(String[] args) {
        ImageConvert img = new ImageConvert("C:\\Users\\Steve\\Desktop\\Text_Play\\MIT.png",100,55,4,2);        
        img.getText();
        
        String get= img.complete;
        StringSelection selec= new StringSelection(get);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selec, selec);
    }
    
}
