package infpp.oceanlife;

import infpp.oceanlife.model.OceanLifeModel;
import infpp.oceanlife.model.OceanObject;

import java.util.ArrayList;

/**
 * Interface to ensure integration of Controller and View
 */
public interface ControllableView {

    /**
     * add the object with inserted information through the controller
     * @param type type of the object to be added
     * @param name of the object to be added
     * @param x the x position of the object
     * @param y the y position of the object
     * @throws Exception if it can't be added
     */
    void addObject(String type, String name, int x, int y, int size) throws Exception;

    /**
     * tell the controller to delete the object with given name from the model
     * @param  name the name of the object to delete
     */
    void deleteObject(String name);

    /**
     * start a thread to constantly move inserted objects
     */
    void start();

    /**
     * load the last saved model from the saveFile
     */
    void load();

    /**
     * save the current model to the saveFile
     */
    void save();

    /**
     * start the model
     */
    void test();

    /**
     * move the model once
     */
    void step();

    /**
     * stop the model
     */
    void stop();

    /**
     * tell the controller to quit
     */
    void quit();

    /**
     *
     * @return the model of the controller of the view
     */
    OceanLifeModel getOceanModel();
    /**
     * get the objectList of the model
     * @return the object list of the model
     */
    ArrayList<OceanObject> getObjectList();

    /**
     * empty delete list on the view
     */
    void clearDeleteList();
}
