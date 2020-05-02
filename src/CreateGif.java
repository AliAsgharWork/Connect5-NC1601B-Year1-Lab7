
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

import GIFcreation.GifSequenceWriter;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class CreateGif {
	static int GifNum=0;
	static int numofcapturesinfile;
	static File[] images;
	static boolean firstGifcreate=true;
	static File file;
    public static void createGif() throws Exception {
    	if(firstGifcreate)AdditionalStuff.createFolder("Connect5Gif");GifIncrementStatus();
    	numofcapturesinfile=AdditionalStuff.GIFCaptureIncrementStatus()-1;
    	images = new File[numofcapturesinfile];
        BufferedImage first = ImageIO.read(new File(System.getProperty("user.home")+"\\workspace\\Lab7\\src\\resources\\base.jpg"));///////////CHANGE BASE AND ADD A METHOD TO MAKE ONE IF NOT PRESENT
       //IF FOLDER IS NOT PRESENT MAKE FOLDER
        ImageOutputStream output = new FileImageOutputStream(new File(AdditionalStuff.dir_Desktop+"\\Connect5Gif\\Gif "+GifNum+".gif"));

        GifSequenceWriter writer = new GifSequenceWriter(output, first.getType(), 250, true);
        writer.writeToSequence(first);

        
        for (int i = 0; i < numofcapturesinfile; i++) {
        	int p =i+1;
			images[i]= new File(AdditionalStuff.dir_Desktop+"\\GifStorage\\ali "+p+".jpg");///folder position will vary
		}
        System.out.println(numofcapturesinfile);
        System.out.println(images[0]);
        for (File image : images) {
            BufferedImage next = ImageIO.read(image);
            writer.writeToSequence(next);
        }
        
        firstGifcreate=false;
        GifNum++;
        writer.close();
        output.close();
    }
    
    
    public static void GifIncrementStatus() {
		
		String dir_name = AdditionalStuff.dir_Desktop+"\\Connect5Gif";
		File dir = new File(dir_name);
		File[] dir_list = dir.listFiles();
		ArrayList<Integer> a =new ArrayList<Integer>();
		a.add(0);
		Integer number = null;
		for(int i=0;i<dir_list.length;++i)
		{
			if(dir_list[i].getName().startsWith("Gif"))
				{
				String [] tempo=dir_list[i].getName().split(" ");
				System.out.println("tempo[1]"+tempo[1]);
				String [] tempo2=tempo[1].split("\\.");
				number = Integer.valueOf(tempo2[0]);
				}
			a.add(number);
			
		}
	
		GifNum=Collections.max(a)+1;//"1" is added because we want the max number's next increment
		
	}

}