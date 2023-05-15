package infpp.oceanlife.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.*;

/**
 * class for the presentation of the ocean as a panel
 */
public class OceanPanel extends JPanel {
    private OceanLifeView view;
    BufferedImage image;

    public OceanPanel(OceanLifeView view) {
        this.view = view;
        String imageFile = "src/infpp/oceanlife/view/pictures/ocean.jpg";
        try {
            image = ImageIO.read(new File(imageFile));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        setSize(view.getOceanModel().getWidth(), view.getOceanModel().getDepth());
        setVisible(true);
    }

    /**
     *
     * @param g the graphic
     */
    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        super.paintComponent(g2d);
//        g2d.drawImage(image, 0, 0,view.getWidth(), view.getHeight(), null);
//        g2d.drawImage(image, 0, 0, null);
        // I have to do this scaling. Don't ask me why
        Image scaledImage = image.getScaledInstance(view.getOceanModel().getWidth(), view.getOceanModel().getDepth(), Image.SCALE_SMOOTH);
        g2d.drawImage(scaledImage, 0, 0,this);
    }
}
