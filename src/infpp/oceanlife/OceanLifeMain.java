/*
 * OceanLife Project
 */
package infpp.oceanlife;

import infpp.oceanlife.controller.*;
import infpp.oceanlife.model.Fish;
import infpp.oceanlife.model.OceanLifeModel;

import java.util.ArrayList;

/**
 * Class to start the Project
 */
public class OceanLifeMain {

	/**
	 * creates an instance of OceanLifeController and starts it.
	 * @param args command line arguments
	 */
	public static void main(String[] args) throws Exception {
		OceanLifeController controller = new OceanLifeController();
//		OceanLifeModel model = new OceanLifeModel(new ArrayList<>());
//		model.addObject("Fish", "Test", 300, 400, 100);
//		System.out.println(model);
	}
}