/**
 * 
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @author Jullien
 *
 */
public interface DroneInt extends Remote {
	
	public Integer getId()  throws RemoteException;

	public void setId(Integer id)  throws RemoteException;

	public String getName()  throws RemoteException;

	public void setName(String name)  throws RemoteException;
	

}
