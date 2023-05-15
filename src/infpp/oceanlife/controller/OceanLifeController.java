/*
 * OceanLife Project
 */
package infpp.oceanlife.controller;

import infpp.oceanlife.ControllableModel;
import infpp.oceanlife.ControllableView;
import infpp.oceanlife.view.*;
import infpp.oceanlife.model.*;
import java.io.*;
import java.util.*;

/**
 * Class to control the Project
 */
public class OceanLifeController implements ControllableView, ControllableModel {
    private OceanLifeView view;
    private OceanLifeModel model;
    private boolean running;


    public OceanLifeController() {
        // create objectList
        ArrayList<OceanObject> objectList = new ArrayList<>();
        // create Ocean
        this.model = new OceanLifeModel(objectList);
        // create view with this controller object
        this.view = new OceanLifeView(this);
    }

    /**
     * class that works the Thread to constantly move the model
     */
    private class MoveThread implements Runnable {
        public void run () {
            while (running) {
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    System.out.println(e);
                }
                step();
            }
            System.out.println("*_*_*_*_*_ Thread Stopped _*_*_*_*_*_*");
        }
    }

    /**
     * load the last saved model from the saveFile
     */
    public void load() {
        try {
            InputStream is = new FileInputStream("src/infpp/oceanlife/controller/saveFile");
            ObjectInputStream ois = new ObjectInputStream(is);
            model = (OceanLifeModel) ois.readObject();
            System.out.println("Loaded last saved ocean");
            ois.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * save the current model to the saveFile
     */
    public void save() {
        try {
            OutputStream os = new FileOutputStream("src/infpp/oceanlife/controller/saveFile");
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(model);
            System.out.println("Saved the current ocean to saveFile");
            oos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * starts the thread to constantly move the objects
     */
    public void start() {
        // command line output for debug purposes
        System.out.println("Starting OceanLife...");
        running = true;
        Thread t = new Thread(new MoveThread());
        t.start();
    }


    /**
     * pause the program
     */
    public void stop(){
        running = false;
    }

    /**
     * fills the ocean with test OceanObjects
     */
    public void test(){
        // delete all objects first
        clearDeleteList();
        setObjectList(new ArrayList<>());
        // command line output for debug purposes
        System.out.println("Starting OceanLife...");

        // create objectList
        try {
            addObject("Fish", "Alfred", 100, 200, 100);
            addObject("Fish", "Bertha", 200, 200, 100);
            addObject("Fish", "Cleo", 500, 300, 100);
            addObject("Stone", "Flint", 700, 100, 100);
        } catch (Exception exc) {
            System.out.println("Startup ERROR, change saved values for objects in the controller");
            System.exit(2);
        }
    }

    /**
     * move the model once
     */
    public void step() {
        // moving
        model.step();
    }

    /**
     *
     * @return the object list of the model
     */
    public ArrayList<OceanObject> getObjectList(){
        return model.getObjectList();
    }

    /**
     * set the object list of the model to given list
     * @param objectList the new object list
     */
    public void setObjectList(ArrayList<OceanObject> objectList) {
        model.setObjectList(objectList);
    }

    /**
     * empty delete list on the view
     */
    public void clearDeleteList(){
        view.clearDeleteList();
    }

    /**
     *
     * @return the width of the model
     */
    public int getWidth() {
        return model.getWidth();
    }

    /**
     *
     * @return the depth of the model
     */
    public int getDepth() {
        return model.getDepth();
    }

    /**
     * send the parameters to the model to create an object if possible
     * @param type the type of the object
     * @param name the name of the object
     * @param x the x pos of the object
     * @param y the y pos of the object
     */
    public void addObject(String type, String name,int x,int y, int size) throws Exception {
        model.addObject(type, name, x, y, size);
    }

    /**
     * delete the object with the given name from the model and the view
     * @param name the name of the object
     */
    public void deleteObject (String name){
        model.deleteObject(name);
    }

    /**
     * quit the program
     */
    public void quit(){
        System.exit(0);
    }

    /**
     *
     * @return the LinkedList of models in this controller
     */
    public OceanLifeModel getOceanModel() {
        return model;
    }
}
