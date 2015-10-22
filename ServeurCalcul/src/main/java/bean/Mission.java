package bean;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import rmi.CoordGpsInt;
import rmi.DroneInt;
import rmi.MissionInt;
import rmi.ReleveInt;

public class Mission extends UnicastRemoteObject implements Serializable,MissionInt{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String type;
	private CoordGps coord_dep;
	private CoordGps coord_ar;
	private Double periode;
	private int nbDrone;
	private Double densite;
	private Double portee;
	
    private ArrayList<ReleveInt> releve;
    private ArrayList<DroneInt> flotte;

	
	public Mission() throws RemoteException {
		super();
	}
	
	public Mission(MissionInt missionInt) throws RemoteException {
		super();
		this.id = missionInt.getId();
		this.name = missionInt.getName() ;
		this.type = missionInt.getType() ;
		this.periode = missionInt.getPeriode() ;
		this.densite = missionInt.getDensite() ;
		this.portee = missionInt.getPortee() ;
		this.coord_dep = new CoordGps(missionInt.getCoord_dep());

		if("bathymetrie".equalsIgnoreCase(missionInt.getType())){
			this.coord_ar = new CoordGps(missionInt.getCoord_ar());
		}
		
		ArrayList<Releve> releveList = new ArrayList<Releve>();
		ArrayList<Drone>  droneList  = new ArrayList<Drone>();
		
		if(missionInt.getReleve() != null){
			
			for(ReleveInt releveInt:missionInt.getReleve()){
				Releve r = new Releve(releveInt.getCoordGps());				
				releveList.add(r);
			}
			
		}

		if(missionInt.getFlotte() != null){
		
			for(DroneInt droneInt:missionInt.getFlotte()){
				Drone d = new Drone();
				BeanUtils.copyProperties(droneInt, d);
				droneList.add(d);
			}
			
		}
		this.nbDrone = droneList.size() ;
		
		this.setReleve(releveList);
		this.setFlotte(droneList);
		
		
	}


	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public CoordGpsInt getCoord_dep() {
		return coord_dep;
	}

	public void setCoord_dep(CoordGpsInt coord_dep) {
		this.coord_dep = (CoordGps)coord_dep;
	}

	public CoordGpsInt getCoord_ar() {
		return coord_ar;
	}

	public void setCoord_ar(CoordGpsInt coord_ar) {
		this.coord_ar = (CoordGps)coord_ar;
	}

	public Double getPeriode() {
		return periode;
	}

	public void setPeriode(Double periode) {
		this.periode = periode;
	}

	public int getNb_drone() {
		return nbDrone;
	}

	public void setNb_drone(int nb_drone) {
		this.nbDrone = nb_drone;
	}

	public Double getDensite() {
		return densite;
	}

	public void setDensite(Double densite) {
		this.densite = densite;
	}

	public Double getPortee() {
		return portee;
	}

	public void setPortee(Double portee) {
		this.portee = portee;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;		
	}

	public List<? extends ReleveInt> getReleve() {
		return releve;
	}

	public void setReleve(List<? extends ReleveInt> releve) {
		this.releve = (ArrayList<ReleveInt>)releve;
		
	}

	public List<? extends DroneInt> getFlotte() {
		return flotte;
	}

	public void setFlotte(List<? extends DroneInt> flotte) {
		this.flotte = (ArrayList<DroneInt>)flotte;
	}

	public void addDrone(String droneName) throws RemoteException {
		// TODO Auto-generated method stub
		//UNUSED
		
	}

	public void deleteDrone(String droneName) throws RemoteException {
		// TODO Auto-generated method stub
		//UNUSED
	}

}