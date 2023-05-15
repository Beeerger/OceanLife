/*
 * OceanLife Project
 */

package infpp.oceanlife.model;



import infpp.oceanlife.ControllableModel;

import javax.sound.midi.SysexMessage;
import java.io.Serial;
import java.io.Serializable;
import java.util.*;

/**
 * class of OceanLifeModel objects (i.e. oceans)
 */
public class OceanLifeModel implements ControllableModel, Serializable {
	@Serial
	private static final long serialVersionUID = 1L;
	private int width;
	private int depth;
	private ArrayList<OceanObject> objectList;

	/**
	 * create Ocean with given LinkedList with OceanObjects
	 * @param objectList the objectList
	 */
	public OceanLifeModel(ArrayList<OceanObject> objectList) {
		this.objectList = objectList;
		this.width = 1500;
		this.depth = 800;
	}

	/**
	 * add an object to the objectList if possible
	 * @param type the type of the object
	 * @param name the name of the object
	 * @param x the x position of the object
	 * @param y the y position of the object
	 * @throws Exception various
	 */
	public void addObject(String type, String name, int x, int y, int size) throws Exception {
		if (x > width - size | x < 0 | y < 0 | y > depth - size) throw new Exception("Out of bounds");
		for (OceanObject other: objectList) {
			if (conflictPos(x, y, other.getX(), other.getY(), size, other.getSize())) throw new Exception("position occupied");
			if (name.equals(other.getName())) throw new Exception("name taken");
		}
		if (type.equals("Fish")){
			Fish object = new Fish(x, y, name, size);
			objectList.add(object);
		}
		else {
			Stone object = new Stone(x, y, name, 50, size);
			objectList.add(object);
		}
		System.out.println("object added successfully");
	}

	/**
	 * move every object in the objectList of the ocean, that does not move into the position
	 * of another object
	 */
	public void step() {
		for (OceanObject ob : objectList) {
			ob.move(this);
			checkConflicts(ob);
		}
	}

	/**
	 * check if an object is too close to other objects (i.e. in conflict)
	 * and then perform resolving action
	 * @param ob the OceanObject to check
	 */
	private void checkConflicts(OceanObject ob) {
			for (OceanObject ob2 : objectList) {
				if (ob.getName().equals(ob2.getName())) {
					continue;
				}
				int x1 = ob.getX();
				int y1 = ob.getY();
				int size1 = ob.getSize();
				int x2 = ob2.getX();
				int y2 = ob2.getY();
				int size2 = ob2.getSize();

				if (conflictPos(x1, y1, x2, y2, size1, size2)) {
					System.out.println("******** CONFLICT START **********");
					System.out.println("Ob1: " + ob.getName() + ", Ob2: " + ob2.getName());
					System.out.println(this);
					System.out.println("******** CONFLICT MOVE 1 **********");
					ob.changeDirX();
					ob.move(this);
					System.out.println(this);
					System.out.println("******** CONFLICT END **********");
				}
			}
	}


	/**
	 * checks if the positions of two objects overlap
	 * @param x1 the x position of object 1
	 * @param y1 the y position of object 1
	 * @param x2 the x position of object 2
	 * @param y2 the y position of object 2
	 * @return no conflict: false, conflict: true
	 */
	private boolean conflictPos(int x1, int y1, int x2, int y2, int size1, int size2) {
		int tolerance;
		if (size1 >= size2) {
			tolerance = size1;
		} else {
			tolerance = size2;
		}

		int diffX = Math.abs(x1 - x2);
		int diffY = Math.abs(y1 - y2);
		boolean similarX = diffX < tolerance;
		boolean similarY = diffY < tolerance;

		return similarX & similarY;
	}

	/**
	 *
	 * @param name the name of the object that should be deleted
	 */
	public void deleteObject (String name) {
		for (OceanObject ob: getObjectList()){
			if (ob.getName().equals(name)) {
				getObjectList().remove(ob);
				break;
			}
		}
	}
	
	/**
	 * @return the LinkedList with OceanObjects
	 */
	public ArrayList<OceanObject> getObjectList() {
		return objectList;
	}
	
	/**
	 * @param objectList the LinkedList with OceanObjects
  	 */
	public void setObjectList(ArrayList<OceanObject> objectList) {
		this.objectList = objectList;
	}


	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}
	
	/**
	 * @param depth the depth to set
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * @return the ocean with the fish as string
	 */
	@Override
	public String toString() {
		StringBuilder fishString = new StringBuilder();
		for (OceanObject ob : objectList) {
			fishString.append('\n').append(ob.toString());
		}
		return '\n'+ "Ozean: width: " + width + " depth: "+ depth + '\n' +"Objects: "+  fishString;
		}
}

