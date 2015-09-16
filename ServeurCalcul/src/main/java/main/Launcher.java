package main;

import java.io.DataInputStream;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;

import service.ServiceCalculeMissionImp;

public class Launcher {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

		 try 
	        { 
			 			 
	            ServiceCalculeMissionImp fabrique = new ServiceCalculeMissionImp("CalculMission",1099); 

		        DataInputStream in = new DataInputStream(System.in); 
		        System.out.println("************************************************"); 
		        System.out.println("************************************************"); 
		        System.out.println("SERVEUR DE CALCUL DEMARRE !!!!!!!!!! "); 
		        System.out.println("************************************************"); 
		        System.out.println("************************************************"); 
		        String valeur= in.readLine(); 

		        // Désenregistrement de l'OD de l'annuaire 
		        Naming.unbind("rmi://localhost:1099/CalculMission"); 

		        // Destruction de l'OD 
		        UnicastRemoteObject.unexportObject(fabrique,true); 
		        
	            
	        } 
	        catch (Exception e) 
	        { 
	            System.out.println("FabriqueMission err: " + e.getMessage()); 
	            e.printStackTrace(); 
	        } 
		 
	}

}
