/*
 * OceanLife Project
 */
package infpp.oceanlife.model;

import java.io.Serial;
import java.io.Serializable;

/**
 * class of Fish objects
 */
public class Fish extends OceanObject implements Serializable {
	@Serial
	private static final long serialVersionUID = 2L;
	private final String type;

	/**
	 * create Fish Object with given parameters and sets type, velocity, offset and direction
	 * @param x the x
	 * @param y the y
	 * @param name the name
	 */
	public Fish(int x, int y, String name, int size) {
		super(x, y, name, size);
		type = "Fish";
		dirX = "r";
		dirY = "d";
		offset = (int) (size - (size * 0.2));

		velX = 30;
		velY = 7;
	}

	/**
	 * move the Fish with fixed values
	 */
	@Override
	public void move(OceanLifeModel model) {
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

	/**
	 * find out the x position after a move
	 * @param model the model where the object is in
	 * @return the possible x position
	 */
	public int tryMoveX (OceanLifeModel model) {
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

	/**
	 * find out the y position after a move
	 * @param model the model where the object is in
	 * @return the possible y position
	 */
	public int tryMoveY (OceanLifeModel model) {
		if (dirY.equals("d")) {
			if (hitsBottom(model)) {
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

	@Override
	public int getSize() {
		return size;
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
	public String toString() {
		return "Name: " + this.getName() + "; position: x = " + this.getX() + ", y = " + this.getY() + ", dir: " + this.getDirX() + ", size: " + getSize();
	}
}
