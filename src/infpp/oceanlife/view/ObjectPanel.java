package infpp.oceanlife.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;

/**
 * class for the presentation of objects as panels
 */
public class ObjectPanel extends JPanel {
    private final int size;
    BufferedImage image;

    public ObjectPanel (String type, String direction, int size){
        this.size = size;
        String imageFile;
        // check what type of object we have to present (only 2 possible objects)
        if (type.equals("Fish")){
            if (direction.equals("r")) {
                imageFile = "src/infpp/oceanlife/view/pictures/NewFish-r.png";
            } else {
                imageFile = "src/infpp/oceanlife/view/pictures/NewFish-l.png";
            }
        }
        else{
            imageFile = "src/infpp/oceanlife/view/pictures/NewStone.png";
        }
        try {
            image = ImageIO.read(new File(imageFile));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        //setOpaque(false);
        setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paintComponent(g2d);
        Image scaledImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        g2d.drawImage(scaledImage, 0, 0,this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        Image scaledImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        g2d.drawImage(scaledImage, 0, 0,this);
    }
}
