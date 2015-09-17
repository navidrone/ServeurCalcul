/**
 * 
 */
package bean;

import java.io.Serializable;


import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmi.DroneInt;

/**
 * @author Jullien
 *
 */

public class Drone extends UnicastRemoteObject implements Serializable, DroneInt {
	
	protected Drone() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final long serialVersionUID = 1L;

	private Integer id;

	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
