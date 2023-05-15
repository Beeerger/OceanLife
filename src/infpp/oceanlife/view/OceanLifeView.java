/*
 * OceanLife Project
 */

package infpp.oceanlife.view;

import infpp.oceanlife.ControllableView;
import infpp.oceanlife.controller.OceanLifeController;
import infpp.oceanlife.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Objects;

/**
 * class to present the Objects
 */
@SuppressWarnings("ALL")
public class OceanLifeView extends JFrame implements ActionListener, ControllableView {
    private OceanLifeController controller;
    private JPanel panel;
    private boolean running;

    private OceanPanel oceanPanel;
    private ArrayList<ObjectPanel> objectPanels;

    //declare the buttons
    private JButton loadButton;
    private JButton saveButton;
    private JButton startButton;
    private JButton stopButton;
    private JButton insertButton;
    private JButton deleteButton;
    private JButton quitButton;
    private JButton stepButton;
    private JButton updateButton;
    private JButton testButton;
    //declare the lists
    private JComboBox<String> insertObjectList;
    private JComboBox<String> deleteObjectList;
    //declare the fields and labels
    private JTextField name;
    private JTextField xCord;
    private JTextField yCord;
    private JLabel nameL;
    private JLabel xCordLabel;
    private JLabel yCordLabel;
    private JLabel infoLabel;

    /**
     * create view for the controller
     * @param controller the controller
     */
    public OceanLifeView(OceanLifeController controller){
        this.controller = controller;
        //create the Panel for the main window
        this.panel = new JPanel() {
            @Override
            public void paintComponents(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                super.paintComponents(g2d);
                oceanPanel.repaint();
                for (ObjectPanel op: objectPanels) {
                    g2d.drawImage(op.image, 0,0, null);
                }
            }
        };
        // adjust the size to the model
        setSize(getOceanWidth() + 40, getOceanDepth() + 400);
        //cosmetics
        //panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        panel.setLayout(null);
        // add all the components to the window (panel)
        addComponents();
        // create and start thread to constantly update the screen
        Thread t = new Thread(new UpdateThread());
        t.start();
        // Quality of Life
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2 + 55);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("OceanLife");
        setContentPane(panel);
        setVisible(true);
    }

    /**
     * class that works the Thread to constantly update the screen
     */
    private class UpdateThread implements Runnable {
        public void run() {
            while (true) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println(e);
                }
                updateScreen();
            }
        }
    }

    /**
     * add all the needed components to the panel of the view
     */
    private void addComponents() {
        add(panel, BorderLayout.CENTER);

        // create the list of possible Objects
        String[] insertList = new String[2];
        insertList[0] = "Fish";
        insertList[1] = "Stone";

        //create the list of current objects
        int numObjects = getObjectCount();
        String[] deleteList = new String[numObjects];
        for (int i = 0; i < numObjects; i++) {
            deleteList[i] = getObjectList().get(i).getName();
        }

        // create every element and set its position on the frame

        loadButton = new JButton("load");
        loadButton.setBounds(   10,      0, 90, 50);
        loadButton.addActionListener(this);

        saveButton = new JButton("save");
        saveButton.setBounds(   10,      50, 90, 50);
        saveButton.addActionListener(this);

        /**
         * button to start the program with the preset information
         */
        startButton = new JButton("start");
        startButton.setBounds(  100,    0, 100, 50);
        startButton.addActionListener(this);

        /**
         * button to stop the model
         */
        stopButton = new JButton("stop");
        stopButton.setBounds(   100,    50, 100, 50);
        stopButton.addActionListener(this);
        /**
         * the label for the x coordinates for adding objects
         */
        xCordLabel = new JLabel("x coordinates: ");
        xCordLabel.setBounds(    210,    0, 100, 50);
        /**
         * the label for the y coordinates for adding objects
         */
        yCordLabel = new JLabel("y coordinates: ");
        yCordLabel.setBounds(    210,    50, 100, 50);
        /**
         *  the label for the name field
         */
        nameL = new JLabel("object name: ");
        nameL.setBounds(         210,    100, 100, 50);
        /**
         * text field to enter x coordinates while adding objects
         */
        xCord = new JTextField();
        xCord.setBounds(        300,    0, 100, 50);
        /**
         * text field to enter y coordinates while adding objects
         */
        yCord = new JTextField();
        yCord.setBounds(        300,    50, 100, 50);
        /**
         * text field to enter the name while adding objects
         */
        name = new JTextField();
        name.setBounds(        300,    100, 100, 50);
        /**
         * button to insert the object with given parameters
         */
        insertButton = new JButton("insert");
        insertButton.setBounds( 400,    50, 100, 50);
        insertButton.addActionListener(this);
        /**
         * combo box for the possible types of objects to create
         */
        insertObjectList = new JComboBox<>(insertList);
        insertObjectList.setBounds(   400,    0, 100, 50);

        /**
         * combo box to choose from all available objects to delete
         */
        deleteObjectList = new JComboBox<>();
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel();
        deleteObjectList.addActionListener(this);
        // fill the combo box with already existing objects (during startup, see in the beginning of method)
        for (String ob: deleteList){
            comboBoxModel.addElement(ob);
        }
        deleteObjectList.setBounds(   500,    0, 100, 50);
        deleteObjectList.setModel(comboBoxModel);

        /**
         *  button to start the delete process
         */
        deleteButton = new JButton("delete");
        deleteButton.setBounds( 500,    50, 100, 50);
        deleteButton.addActionListener(this);
        /**
         * button to quit the program
         */
        quitButton = new JButton("quit");
        quitButton.setBounds(   600,    0, 100, 50);
        quitButton.addActionListener(this);
        /**
         * button to move the model once
         */
        stepButton = new JButton("step");
        stepButton.setBounds(   600,    50, 100, 50);
        stepButton.addActionListener(this);
        /**
         * button to update the graphics
         */
        updateButton = new JButton("update");
        updateButton.setBounds (700, 0, 100, 50);
        updateButton.addActionListener(this);
        /**
         * button to set a test status
         */
        testButton = new JButton("test");
        testButton.setBounds(700, 50, 100, 50);
        testButton.addActionListener(this);

        /**
         * a subclass of a panel for the background and object presentation
         */
        oceanPanel = new OceanPanel(this);
        //oceanPanel.image.getScaledInstance(model.getWidth(), model.getDepth(), Image.SCALE_SMOOTH);
        oceanPanel.setBounds(   10,      160, getOceanWidth(), getOceanDepth());

        /**
         * label to present some information
         */
        infoLabel = new JLabel("Press Start and then use update and step to present the objects and move them!");
        infoLabel.setBounds(50, 960, 600, 50);

        // add elements to the panel
        panel.add(oceanPanel);
        panel.add(saveButton);
        panel.add(loadButton);
        panel.add(startButton);
        panel.add(stopButton);
        panel.add(insertButton);
        panel.add(insertObjectList);
        panel.add(deleteButton);
        panel.add(deleteObjectList);
        panel.add(quitButton);
        panel.add(stepButton);
        panel.add(updateButton);
        panel.add(testButton);
        panel.add(nameL);
        panel.add(xCordLabel);
        panel.add(yCordLabel);
        panel.add(name);
        panel.add(xCord);
        panel.add(yCord);
        panel.add(infoLabel);
    }

    /**
     * find out what action to perform after button press
     * @param e the button that has been pressed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // find the case that has to be performed
        switch(e.getActionCommand()) {
            case "start":
                start();
                break;
            case "load":
                load();
                break;
            case "save":
                save();
                break;
            case "stop":
                stop();
                UpdateWindow stopWindow = new UpdateWindow("stopped the model", this);
                break;
            case "insert":
                insert();
                break;
            case "delete":
                try {
                    // find out which object to delete from the combobox (via the name)
                    String name = Objects.requireNonNull(deleteObjectList.getSelectedItem()).toString();
                    deleteObject(name);
                } catch (Exception exc) {
                    UpdateWindow deleteE = new UpdateWindow("Kein Objekt ausgewählt", this);
                }
                break;
            case "quit":
                // tell the controller to quit
                QuitWindow quitWindow = new QuitWindow("Quitting", this);
                break;
            case "step":
                System.out.println("--MOVING--");
                // tell the controller to step
                step();
                break;
            case "update":
                updateScreen();
                showObjects();
                break;
            case "test":
                test();
                showObjects();
                updateList();
                UpdateWindow testWindow = new UpdateWindow("test objects loaded", this);
                break;
        }
    }

    /**
     * delete old objects from the screen and paint all the current objects again
     */
    public void updateScreen() {
        // initialise new List of ObjectPanels
        objectPanels = new ArrayList<>();
        // clear the "old" panels from the model
        oceanPanel.removeAll();
        // add for every object in the model an objectPanel with its position to the main panel
        for (OceanObject ob: getObjectList()){
            int size = ob.getSize();
            ObjectPanel picture = new ObjectPanel(ob.getType(), ob.getDirX(), size);
            picture.setBounds(ob.getX(), ob.getY(), size, size);
            oceanPanel.add(picture);
        }
        oceanPanel.repaint();
    }


    /**
     * update the delete list of the JComboBox
     */
    private void updateList() {
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) deleteObjectList.getModel();
        model.removeAllElements();
        for (OceanObject oob: getObjectList()) {
            model.addElement(oob.getName());
        }
    }

    /**
     * add the object with inserted information through the controller
     */
    public void addObject(String type, String name, int x, int y, int size) {
        int PosX;
        int PosY;
        // cast string to integer
        try {
            if (type.equals("")) throw new Exception("no type selected");
            if (name.equals("")) throw new Exception("no name");
            // tell the controller what to do
            controller.addObject(type, name, x, y, size);
            // add the option to delete to the combobox
            addDeleteBoxItem(name);
            UpdateWindow completeW = new UpdateWindow("Objekt wurde erfolgreich eingefügt", this);
            showObjects();
        //} catch (NumberFormatException exc) {
        } catch (Exception e) {
            System.out.println("caught exception " + e.getMessage());
            UpdateWindow errorW = new UpdateWindow("Objekt konnte nicht eingefügt werden: \n" + e.getMessage(), this);
        }
    }

    /**
     * delete the object from the deleteObjectList from the list and tell the controller to delete it from the model
     */
    public void deleteObject(String name) {
        // tell the controller to delete it
        controller.deleteObject(name);
        // remove the option from the combo box
        removeDeleteBoxItem();
        UpdateWindow deleteW = new UpdateWindow("Objekt wurde entfernt", this);
        showObjects();
    }

    /**
     * find out the number of objects in the model
     * @return the number of objects in the model
     */
    private int getObjectCount() {
        return getObjectList().size();
    }

    /**
     * get the objectList of the model
     * @return the object list of the model
     */
    public ArrayList<OceanObject> getObjectList() {
        return controller.getObjectList();
    }

    /**
     * add the given option to the combo box
     * @param name the name to add to the list
     */
    private void addDeleteBoxItem(String name){
        DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) deleteObjectList.getModel();
        model.addElement(name);
    }

    /**
     * remove the selected item from the combo box
     */
    private void removeDeleteBoxItem() {
        if (deleteObjectList.getSelectedIndex() < deleteObjectList.getItemCount()){
            DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) deleteObjectList.getModel();
            model.removeElementAt(deleteObjectList.getSelectedIndex());
//            deleteObjectList.removeItemAt(deleteObjectList.getSelectedIndex());
        }
    }

    /**
     * empty delete list on the view
     */
    public void clearDeleteList(){
        deleteObjectList.removeAllItems();
    }

    /**
     * load the last saved model from the saveFile
     */
    public void load() {
        try {
            if (running) throw new Exception("Program still running.");
            controller.load();
            updateList();
            LoadWindow loadWindow = new LoadWindow(this);
        } catch (Exception e) {
            new UpdateWindow("Es konnte nicht geladen werden: " + e.getMessage(), this);
        }
    }

    /**
     * save the current model to the saveFile
     */
    public void save() {
        try {
            if (running) throw new Exception("Program still running.");
            controller.save();
            UpdateWindow saveWindow = new UpdateWindow("saved current ocean", this);
        } catch (Exception e) {
            new UpdateWindow("Es konnte nicht gespeichert werden: " + e.getMessage(), this);
        }
    }

    public void start() {
        if (!running) {
            running = true;
            controller.start();
        }
    }

    /**
     * stop the model
     */
    public void stop() {
        if (running) {
            running = false;
            controller.stop();
        }
    }

    /**
     * start the model
     */
    public void test() {
        controller.test();
    }

    /**
     * move the model once
     */
    public void step(){
        controller.step();
        showObjects();
        updateScreen();
    }

    /**
     *
     */
    private void insert() {
        // just using final variable for now, you could add another TextField to customise sizes
        final int size = 100;

        String tempType = insertObjectList.getSelectedItem().toString();
        String tempName = name.getText();
        String tempPosX = xCord.getText();
        String tempPosY = yCord.getText();
        int PosX;
        int PosY;
        try {
            // check if text field data is valid (int, not null) through parsing
            PosX = Integer.parseInt(tempPosX);
            PosY = Integer.parseInt(tempPosY);
            // if successful, go and try to add the object
            addObject(tempType, tempName, PosX, PosY, size);
        } catch (Exception exc) {
            System.out.println("caught exception " + exc.getMessage());
            UpdateWindow errorW = new UpdateWindow("Objekt konnte nicht eingefügt werden: \n" + "no valid coordinate type (only int)", this);
        }
    }

    /**
     * tell the controller to quit
     */
    public void quit() {
        controller.quit();
    }

    // textual representation only for debugging purposes
    /**
     * show the object from the given controller in the terminal
     */
    private void showObjects(){
        System.out.println(controller.getOceanModel());
    }

    /**
     * get the width of the model
     * @return the width of the model
     */
    private int getOceanWidth(){
        return getOceanModel().getWidth();
    }

    /**
     * get the depth of the model
     * @return the depth of the model
     */
    private int getOceanDepth(){
        return getOceanModel().getDepth();
    }

    /**
     *
     * @return the model of the controller of the view
     */
    public OceanLifeModel getOceanModel() {
        return controller.getOceanModel();
    }

    /**
     *
     * @return the controller of the view
     */
    private OceanLifeController getController() {
        return controller;
    }
}
