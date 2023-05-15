package infpp.oceanlife.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * subclass for pop-up windows, in case of quitting
 */
public class QuitWindow extends UpdateWindow implements ActionListener {
    private JButton quitB;
    private JButton cancelB;

    public QuitWindow(String text, OceanLifeView view) {
        super(text, view);
    }

    @Override
    public void addComponents() {
        quitB = new JButton("quit");
        quitB.addActionListener(this);
        cancelB = new JButton("cancel");
        cancelB.addActionListener(this);
        panel.add(quitB);
        panel.add(cancelB);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object source = e.getSource();
        if(source == quitB){
            view.quit();
        } else if (source == cancelB){
            dispose();
        }
    }
}
