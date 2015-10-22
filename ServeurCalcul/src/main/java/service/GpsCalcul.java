package service;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import bean.CoordGps;
import rmi.CoordGpsInt;


public class GpsCalcul {
	
	private static double earthRadius = 6371.0; // kilometers
	
	/*
	 * calcule la distance entre deux points distinct début et fin
	 * Variable Point debut et fin
	 * Return double
	 */
	
	public static double Distance(CoordGpsInt debut, CoordGpsInt fin) throws RemoteException
    { 
      
      double dLat = Math.toRadians(fin.getLattitude() - debut.getLattitude() );
      double dLng = Math.toRadians(fin.getLongitude() - debut.getLongitude() );
      double sindLat = Math.sin(dLat / 2);
      double sindLng = Math.sin(dLng / 2);
      double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)* Math.cos(Math.toRadians(debut.getLattitude())) * Math.cos(Math.toRadians(fin.getLattitude()));

      double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
      
      double dist = earthRadius * c;

      return dist;
    }
	
	/*
	 * calcule du Bearing, calcule de la trajectoire à suivre à partir d'un un Point début
	 *  à un point fin
	 *  Variable Point debut et fin
	 *  Return double
	 */
	
	public static double clacBearing(CoordGpsInt debut, CoordGpsInt fin) throws RemoteException
	{
		double dLon = Math.toRadians(fin.getLongitude()-debut.getLongitude());
		CoordGps localDebut = new CoordGps(debut.getLattitude(),debut.getLongitude());
		CoordGps localFin = new CoordGps(fin.getLattitude(),fin.getLongitude());
		
		localDebut.setLattitude(Math.toRadians(localDebut.getLattitude()));
		localDebut.setLongitude( Math.toRadians(localDebut.getLongitude()));
		localFin.setLattitude(Math.toRadians(localFin.getLattitude()));
		localFin.setLongitude(Math.toRadians(localFin.getLongitude()));
		
		
		double y = Math.sin(dLon) * Math.cos(localFin.getLattitude());
		
		double x = Math.cos(localDebut.getLattitude()) * Math.sin(localFin.getLattitude()) - (Math.sin(localDebut.getLattitude()) * Math.cos(localFin.getLattitude()) * Math.cos(dLon));
		
		double result = Math.atan2(y, x);
		
		result = Math.toDegrees(result);
		

		
		return result;
		
		
	}
	
	/*
	 * Retrouver un point GPS à partir d'un Bearing, la distance entre les deux point et le point de départ
	 * Variable double brng, dist, Point point
	 * return Point
	 */
	
	public static CoordGpsInt getpointBrng(double brng, double dist, CoordGpsInt point) throws RemoteException{
		
		dist = dist/earthRadius;
		
		CoordGps localPoint = new CoordGps(point.getLattitude(),point.getLongitude());
		
		brng = Math.toRadians(brng);
		
		localPoint.setLattitude( Math.toRadians(localPoint.getLattitude()));
		localPoint.setLongitude(Math.toRadians(localPoint.getLongitude()));
		
		
		localPoint.setLattitude( Math.asin( Math.sin(localPoint.getLattitude())*Math.cos(dist) + Math.cos(localPoint.getLattitude())*Math.sin(dist)*Math.cos(brng) ));
		
		double a = Math.atan2(Math.sin(brng)*Math.sin(dist)*Math.cos(localPoint.getLattitude()), Math.cos(dist)-Math.sin(localPoint.getLattitude())*Math.sin(localPoint.getLattitude()));
		
		localPoint.setLongitude(localPoint.getLongitude()+ a);
		localPoint.setLongitude((localPoint.getLongitude() + 3*Math.PI) % (2*Math.PI) - Math.PI);
		
		
		localPoint.setLattitude(Math.toDegrees(localPoint.getLattitude()));
		localPoint.setLongitude(Math.toDegrees(localPoint.getLongitude()));
		
	
		
		return localPoint;
			
	}
	
	/*
	 * Liste les waypoints entre deux point distinct debut et fin, d'intervalle X distEntrepoint
	 * Varible Point début, fin, double distEntrepoint
	 * return ArrayList<Point>
	 */
	public static List<? extends CoordGpsInt> getListWayPoint(CoordGpsInt debut, CoordGpsInt fin, double distEntrepoint ) throws RemoteException{
		
		ArrayList<CoordGpsInt> wayPoints = new ArrayList<CoordGpsInt>();
		
		double dist = Distance(debut,fin) * 1000;
		

		double brng = clacBearing(debut,fin);
		
		
		CoordGps point = new CoordGps(debut.getLattitude(),debut.getLattitude());
		
		double distBrng = distEntrepoint/1000;
		
		
		 System.out.println("distance = " +  dist + " distance 2 point = " +distEntrepoint);
		
		 wayPoints.add(point);
		 double Ndist = Distance(debut,point) * 1000;
		 
		while(dist > Ndist+distEntrepoint)
		{
			
				point = (CoordGps)getpointBrng(brng, distBrng, point);
				
				Ndist = Distance(debut,point) * 1000;
				
				wayPoints.add(point);
				
		}
		wayPoints.add(fin);
		
		 
		return wayPoints;
	}
	
	
	
	
	public static List<? extends CoordGpsInt> getListWayPointToc(CoordGpsInt debut, double DistMax, double EcartCercle ) throws RemoteException{
		
		ArrayList<CoordGpsInt> wayPoints = new ArrayList<CoordGpsInt>();
		CoordGps point = new CoordGps(debut.getLattitude(),debut.getLongitude());
		
		double dist = EcartCercle;
		double angle = 0;
		
		wayPoints.add(point);
		
		System.out.println("distance Max = " +  DistMax + " distance= " +EcartCercle);
		
		while(dist < DistMax ){
			
			angle = 0;
			
			while (angle < 360){
				
				System.out.println("distance = " +  dist + " angle= " +angle);
				
				point = (CoordGps)getpointBrng(angle, dist, debut);
				
				wayPoints.add(point);
				
				angle = angle + 30;
			}
			
			dist = dist + EcartCercle;
		}
		
		return wayPoints;
	}
}
