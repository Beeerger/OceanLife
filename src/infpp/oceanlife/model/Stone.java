/*
 * OceanLife Project
 */

package infpp.oceanlife.model;

import java.io.Serial;
import java.io.Serializable;

/**
 * class of Stone objects
 */
public class Stone extends OceanObject implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
    private final String type;
    private boolean stuck;
    private int weight;

    /**
     * create stone object with given parameters and sets type, velocity, offset and the stuck boolean
     * @param x the x
     * @param y the y
     * @param name the name
     * @param weight the weight
     */
    public Stone(int x, int y, String name, int weight, int size){
        super(x, y, name, size);
        type = "Stone";
        this.weight = weight;

        offset = (int) (size - (size * 0.1));

        stuck = false;
        dirX = "r";
        dirY = "d";

        velX = 20;
        velY = 50;
    }

    /**
     * move the stone with fixed values
     */
    @Override
    public void move(OceanLifeModel model){
        if (!stuck) {
            boolean movedX = false;
            boolean movedY = false;
            // check if we have to move AND change direction
            if ((dirX.equals("r") & this.hitsRight(model)) | (dirX.equals("l") & this.hitsLeft())) {
                this.setX(tryMoveX(model));
                movedX = true;
                changeDirX();
            } else if ((dirY.equals("d") & this.hitsBottom(model)) | (dirY.equals("u") & this.hitsTop())) {
                this.setY(tryMoveY(model));
                movedY = true;
                changeDirY();
            }
            // check if we can simply move
            if (!movedX) {
                this.setX(tryMoveX(model));
            }
            if (!movedY) {
                this.setY(tryMoveY(model));
            }
        }

    }

    public int tryMoveX(OceanLifeModel model) {
            if (dirX.equals("r")) {
                if (hitsRight(model)){
                    return (model.getWidth() - offset);
                } else {
                    return (this.getX() + velX);
                }
            } else {
                if (hitsLeft()) {
                    return (0);
                } else {
                    return (this.getX() - velX);
                }
            }
    }

    public int tryMoveY(OceanLifeModel model) {
            if (dirY.equals("d")) {
                if (hitsBottom(model)) {
                    stuck = true;
                    return (model.getDepth() - offset);
                } else {
                    return (this.getY() + velY);
                }
            } else {
                if (hitsTop()) {
                    return (0);
                } else {
                    return (this.getY() - velY);
                }
            }
    }

    public void changeDir() {
        // do nothing
    }

    /**
     *
     * @return weight of the stone
     */
    public int getWeight() {
        return weight;
    }

    /**
     *
     * @param weight the weight
     */
    public void setWeight(int weight) {
        this.weight = weight;
    }

    /**
     *
     * @return the type of the object
     */
    @Override
    public String getType(){
        return this.type;
    }

    @Override
    public String toString(){
        return "Name: " + this.getName() + "; position: x = " + this.getX() + ", y = " + this.getY() + " weight: " + weight + "g" + ", size: " + getSize();
    }
}
