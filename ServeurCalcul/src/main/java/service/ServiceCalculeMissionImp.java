package service;

import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import bean.Mission;
import bean.Releve;
import rmi.CoordGpsInt;
import rmi.FabriqueMissionInt;
import rmi.MissionInt;
import rmi.ReleveInt;
import rmi.ServiceCalculeMissionInt;

public class ServiceCalculeMissionImp extends UnicastRemoteObject implements Serializable,ServiceCalculeMissionInt {

	private static final long serialVersionUID = 1L;
	
	private FabriqueMissionInt fabriqueMission;
	
	/**
	 * 
	 * Accès exclusif à la FabriqueMission RMI
	 * 
	 * @return
	 */
	private FabriqueMissionInt getFabriqueMission() {
		
		if(fabriqueMission == null){
			
			 try {
				 fabriqueMission =  (FabriqueMissionInt) Naming.lookup("rmi://localhost:1099/FabriqueMission");
		            
		        } catch (Exception e) {
		            System.err.println("Client exception: " + e.toString());
		            e.printStackTrace();
		        }
			
		}
		
		return fabriqueMission;
	}

    public ServiceCalculeMissionImp(String nomRegister, int portRegister) throws Exception 
    { 
   			super(); 

   			try{
       			LocateRegistry.createRegistry(portRegister);
       			//System.setProperty("java.security.policy","file:D:/git/serveurDonnees/security.policy");
						
   			}catch (Exception e){
   				e.printStackTrace();       				
   			}
   			       			
   			try{
   				Naming.unbind("rmi://localhost:"+portRegister+ "/"+ nomRegister);
   			}catch (Exception e){
   				//e.printStackTrace();
   			}
   			
   			
            Naming.bind("rmi://localhost:"+portRegister+ "/"+ nomRegister,this); 
    }
    
    /**
	 * 
	 * Déclenche le calcul des relevés vierges par le serveur de calcul
	 * Ceux-ci sont sauvegardés en base automatiquement. 
	 * 
	 * Cette fonctionnalité est bloquée si la mission a déjà des relevés
	 * 
	 * @param id
	 * @throws RemoteException
	 */
	public void calculeMission(int id) throws RemoteException {

		Mission mission = new Mission(getFabriqueMission().getMission(id));
		
		System.out.println("Appel du serveur de calcul pour la mission : "+id);
		
		// Relevés déjà générés pour cette mission
		if(mission.getReleve() != null && !mission.getReleve().isEmpty()){
			throw new RemoteException("Relevés déjà générés pour cette mission");
		}
		
		boolean bathymetrie = "bathymetrie".equalsIgnoreCase(mission.getType());
		
		if(bathymetrie){
			
			mission = missionBathymetrie(mission);
			
		}else{

			mission = missionToc(mission);
			
		}		
		
		
		for(ReleveInt r:mission.getReleve()){
			CoordGpsInt c = r.getCoordGps();
			System.out.println("Releve : "+c.getLattitude()+" "+c.getLongitude());
		}
		
		getFabriqueMission().saveMission(mission);
		
		
	}
	
	/**
	 * 
	 * Calcule les relevés pour une mission Bathymétrique
	 * 
	 * @param mission
	 * @return
	 */
	private Mission missionBathymetrie(Mission mission) throws RemoteException{
		
		System.out.println("Calcul du mission Bathymétrie");
		
		CoordGpsInt debut = mission.getCoord_dep();
		CoordGpsInt fin = mission.getCoord_ar();		
		double distbypoint= mission.getPeriode();
		
		List<? extends CoordGpsInt> wayPoints =  GpsCalcul.getListWayPoint(debut, fin, distbypoint);
		List<ReleveInt> listeReleve = new ArrayList<ReleveInt>();
		
		for(CoordGpsInt coordGpsInt : wayPoints){
			listeReleve.add(new Releve(coordGpsInt));
		}
		
		mission.setReleve(listeReleve);
			
		return mission;
	}
	
	
	/**
	 * 
	 * Calcule les relevés pour une mission Toc
	 * 
	 * @param mission
	 * @return
	 */
	private Mission missionToc(Mission mission) throws RemoteException{

		System.out.println("Calcul du mission Toc");
		
		CoordGpsInt debut = mission.getCoord_dep();
		double DistMax = mission.getPortee();
		double EcartCercle = mission.getDensite();

		
		List<? extends CoordGpsInt> wayPoints =   GpsCalcul.getListWayPointToc(debut, DistMax/1000.0, EcartCercle/1000.0);
		List<ReleveInt> listeReleve = new ArrayList<ReleveInt>();
		
		for(CoordGpsInt coordGpsInt : wayPoints){
			listeReleve.add(new Releve(coordGpsInt));
		}
		
		mission.setReleve(listeReleve);
			
		return mission;
		
		
		
	}

}
