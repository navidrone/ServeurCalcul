package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;



public interface MissionInt extends Remote{


	public Integer getId() throws RemoteException;

	public void setId(Integer id) throws RemoteException;

	public String getName() throws RemoteException;

	public void setName(String name) throws RemoteException;
	
	public List<? extends ReleveInt> getReleve()  throws RemoteException;

	public void setReleve(List<? extends ReleveInt> releve) throws RemoteException;
	
	public List<? extends DroneInt> getFlotte() throws RemoteException;

	public void setFlotte(List<? extends DroneInt> flotte) throws RemoteException;
	
}
