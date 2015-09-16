package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CoordGpsInt extends Remote {

	public Integer getId() throws RemoteException;
	
	public void setId(Integer id)  throws RemoteException;

	public Double getLattitude() throws RemoteException;
	
	public void setLattitude(Double lattitude)  throws RemoteException;
	
	public Double getLongitude()   throws RemoteException;
	
	public void setLongitude(Double longitude) throws RemoteException;
	
	
}
