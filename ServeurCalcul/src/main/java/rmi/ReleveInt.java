/**
 * 
 */
package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 * @author Jullien
 *
 */
public interface ReleveInt extends Remote {	
	

	public Double getProfondeur() throws RemoteException;

	public void setProfondeur(Double profondeur) throws RemoteException;

	public Date getDateReleve()  throws RemoteException;

	public void setDateReleve(Date dateReleve)  throws RemoteException;

	public CoordGpsInt getCoordGps()  throws RemoteException;

	public void setCoordGps(CoordGpsInt coordGps)  throws RemoteException;
	

}
