package infpp.oceanlife.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * class for pop-up windows
 */
public class UpdateWindow extends JFrame implements ActionListener {
    protected OceanLifeView view;
    protected JButton updateB;
    protected JLabel infoL;
    protected JPanel panel;

    public UpdateWindow(String text, OceanLifeView view) {
        super("Updated");
        setSize(450,100);
        setVisible(true);

        this.view = view;
        this.panel = new JPanel();

        infoL = new JLabel(text);
        panel.add(infoL);
        addComponents();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setContentPane(panel);
    }

    public UpdateWindow(OceanLifeView view) {
        super("Updated");
        setSize(450,100);
        setVisible(true);

        this.view = view;
        this.panel = new JPanel();
        addComponents();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        setContentPane(panel);
    }

    /**
     * add the needed components to the panel
     */
    public void addComponents(){
        updateB = new JButton("Finish");
        updateB.addActionListener(this);
        panel.add(updateB);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if(source == updateB) {
            view.updateScreen();
            dispose();
        }
    }
}