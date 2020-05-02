package GIFcreation;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;

public class CreateGifExample {

    public static void main(String[] args) throws Exception {
        BufferedImage first = ImageIO.read(new File("C:\\Users\\User\\Desktop\\gif images\\base.jpg"));
        ImageOutputStream output = new FileImageOutputStream(new File("C:\\Users\\User\\Desktop\\gif images\\example.gif"));

        GifSequenceWriter writer = new GifSequenceWriter(output, first.getType(), 250, true);
        writer.writeToSequence(first);

        File[] images = new File[]{new File("C:\\Users\\User\\Desktop\\gif images\\1.jpg"),new File("C:\\Users\\User\\Desktop\\gif images\\2.jpg")};
        System.out.println(images[0]);
        for (File image : images) {
            BufferedImage next = ImageIO.read(image);
            writer.writeToSequence(next);
        }

        writer.close();
        output.close();
    }
}