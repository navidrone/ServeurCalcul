package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MissionInt extends Remote{


	public Integer getId() throws RemoteException;

	public void setId(Integer id) throws RemoteException;

	public String getName() throws RemoteException;

	public void setName(String name) throws RemoteException;
	
	public String getType()  throws RemoteException;

	public void setType(String type) throws RemoteException;

	public CoordGpsInt getCoord_dep() throws RemoteException;

	public void setCoord_dep(CoordGpsInt coord_dep)  throws RemoteException;

	public CoordGpsInt getCoord_ar()  throws RemoteException;

	public void setCoord_ar(CoordGpsInt coord_ar)  throws RemoteException;

	public Double getPeriode()  throws RemoteException;

	public void setPeriode(Double periode)  throws RemoteException;

	public Double getDensite()  throws RemoteException;

	public void setDensite(Double densite) throws RemoteException;

	public Double getPortee()  throws RemoteException;

	public void setPortee(Double portee)  throws RemoteException;
	
	public List<? extends ReleveInt> getReleve()  throws RemoteException;

	public void setReleve(List<? extends ReleveInt> releve) throws RemoteException;
	
	public List<? extends DroneInt> getFlotte() throws RemoteException;

	public void setFlotte(List<? extends DroneInt> flotte) throws RemoteException;
	
}
