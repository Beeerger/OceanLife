package infpp.oceanlife;

import infpp.oceanlife.model.OceanObject;

import java.util.ArrayList;

/**
 * Interface to ensure integration of Controller and Model
 */
public interface ControllableModel {

    /**
     * move every object in the objectList of the ocean, that does not move into the position
     * of another object
     */
    void step();

    /**
     * add an object to the objectList if possible
     * @param type the type of the object
     * @param name the name of the object
     * @param x the x position of the object
     * @param y the y position of the object
     * @throws Exception various
     */
    void addObject(String type, String name, int x, int y, int size) throws Exception;

    /**
     * delete the object with the given name from the model and the view
     * @param name the name of the object
     */
    void deleteObject (String name);

    /**
     * set the object list of the model to given list
     * @param objectList the new object list
     */
    void setObjectList(ArrayList<OceanObject> objectList);

    /**
     *
     * @return the object list of the model
     */
    ArrayList<OceanObject> getObjectList();

    /**
     *
     * @return the depth of the model
     */
    int getDepth();

    /**
     *
     * @return the width of the model
     */
    int getWidth();
}
