package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceCalculeMissionInt extends Remote {
	
	
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
	void calculeMission(int id) throws RemoteException;

}
