package bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Date;

import rmi.CoordGpsInt;
import rmi.ReleveInt;

public class Releve extends UnicastRemoteObject implements ReleveInt, Serializable {

	
	private static final long serialVersionUID = 1L;

	private CoordGpsInt coordGps;

	private Double profondeur;

	private Date dateReleve;	
	

	
	public Releve() throws RemoteException {
		super();
	}

	public Releve(CoordGpsInt coordGps) throws RemoteException {
		super();
		this.coordGps = coordGps;
	}

	public Double getProfondeur() {
		return profondeur;
	}

	public void setProfondeur(Double profondeur) {
		this.profondeur = profondeur;
	}

	public Date getDateReleve() {
		return dateReleve;
	}

	public void setDateReleve(Date dateReleve) {
		this.dateReleve = dateReleve;
	}
	
	public CoordGpsInt getCoordGps() {
		return coordGps;
	}

	public void setCoordGps(CoordGpsInt coordGps) {
		this.coordGps = (CoordGps)coordGps;
	}


	public Integer getId() throws RemoteException {
		return coordGps.getId();
	}

	public void setId(Integer id) throws RemoteException {
		coordGps.setId(id);	
	}

	public Double getLattitude() throws RemoteException {
		return coordGps.getLattitude();
	}

	public void setLattitude(Double lattitude) throws RemoteException {
		coordGps.setLattitude(lattitude);
		
	}

	public Double getLongitude() throws RemoteException {
		return coordGps.getLongitude();
	}

	public void setLongitude(Double longitude) throws RemoteException {
		coordGps.setLongitude(longitude);
		
	}
	
	
	
}
