package bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import rmi.CoordGpsInt;

public class CoordGps extends UnicastRemoteObject implements Serializable,CoordGpsInt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CoordGps() throws RemoteException{
		super();
	}
	
	
	
	public CoordGps(Double lattitude, Double longitude) throws RemoteException {
		super();
		this.lattitude = lattitude;
		this.longitude = longitude;
	}

	public CoordGps(CoordGpsInt coordGpsInt) throws RemoteException{
		super();
		this.id = coordGpsInt.getId();
		this.lattitude = coordGpsInt.getLattitude();
		this.longitude = coordGpsInt.getLongitude();
	}

	private Integer id;

	private Double lattitude;

	private Double longitude;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Double getLattitude() {
		return lattitude;
	}
	public void setLattitude(Double lattitude) {
		this.lattitude = lattitude;
	}
	public Double getLongitude() {
		return longitude;
	}
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

}