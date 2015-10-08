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
	
	public Integer getId() throws RemoteException;
	
	public void setId(Integer id)  throws RemoteException;

	public Double getLattitude() throws RemoteException;
	
	public void setLattitude(Double lattitude)  throws RemoteException;
	
	public Double getLongitude()   throws RemoteException;
	
	public void setLongitude(Double longitude) throws RemoteException;
}
