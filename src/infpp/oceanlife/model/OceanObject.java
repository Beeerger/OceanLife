/*
 * OceanLife Project
 */

package infpp.oceanlife.model;

import java.io.Serializable;

/**
 * abstract class for OceanObjects
 */
public abstract class OceanObject implements Serializable {
    protected int x;
    protected int y;
    protected int velX;
    protected int velY;
    protected int size;
    protected int offset;
    protected String name;
    protected String dirX;
    protected String dirY;

    /**
     * create OceanObject
     * @param x the x
     * @param y the y
     * @param name the name
     */
    public OceanObject(int x, int y, String name, int size){
        this.x = x;
        this.y = y;
        this.name = name;
        this.size = size;
    }
    /**
     * move the object
     * @param model the model that should move its objects
     */
    public abstract void move(OceanLifeModel model);

    /**
     * check if the object has hit the right side of the model
     * @param model the model it is in
     * @return hit: true, no hit: false
     */
    protected boolean hitsRight(OceanLifeModel model) {
        return this.getX() + velX >= model.getWidth() - offset;
    }
    /**
     * check if the object has hit the left side of the model
     * @return hit: true, no hit: false
     */
    protected boolean hitsLeft() {
        return this.getX() - velX <= 0;
    }
    /**
     * check if the object has hit the top of the model
     * @return hit: true, no hit: false
     */
    protected boolean hitsTop() {
        return this.getY() - velY < 0;
    }
    /**
     * check if the object has hit the bottom of the model
     * @param model the model it is in
     * @return hit: true, no hit: false
     */
    protected boolean hitsBottom(OceanLifeModel model) {
        return this.getY() + velY > model.getDepth() - offset;
    }

    /**
     * find out the x position after a move
     * @param model the model where the object is in
     * @return the possible x position
     */
    protected abstract int tryMoveX(OceanLifeModel model);

    /**
     * find out the y position after a move
     * @param model the model where the object is in
     * @return the possible y position
     */
    protected abstract int tryMoveY(OceanLifeModel model);

    /**
     * change the horizontal direction that the object is going in to the opposite
     */
    protected void changeDirX() {
        if (dirX.equals("r")) {
            dirX = "l";
        } else {
            dirX = "r";
        }
    }

    /**
     * change the vertical direction that the object is going in to the opposite
     */
    protected void changeDirY() {
        if (dirY.equals("u")) {
            dirY = "d";
        } else {
            dirY = "u";
        }
    }

    /**
     * get the x
     * @return the x
     */
    public int getX(){
        return this.x;
    }

    /**
     * get the y
     * @return the y
     */
    public int getY(){
        return this.y;
    }

    /**
     * set the x
     * @param x the x
     */
    public void setX(int x){
        this.x = x;
    }


    /**
     * set the y
     * @param y the y
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * get the horizontal direction of the object
     * @return dirX the horizontal direction
     */
    public String getDirX() {
        return dirX;
    }

    /**
     * get the vertical direction of the object
     * @return dirY the vertical direction
     */
    public String getDirY() {
        return dirY;
    }

    /**
     * get the size of the object
     */
    public int getSize(){
        return size;
    }

    /***
     * get the name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /***
     * set the name
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the type of the object
     */
    public abstract String getType();


    @Override
    public abstract String toString();
}
