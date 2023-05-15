package infpp.oceanlife.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * subclass for pop-up windows, in case of loading
 */
public class LoadWindow extends UpdateWindow implements ActionListener {
    private JButton cancelB;


    public LoadWindow(OceanLifeView view) {
        super(view);
        String text = "The last saved ocean has been loaded";
        infoL = new JLabel(text);
        panel.add(infoL);
    }
}
