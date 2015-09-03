import java.util.ArrayList;


public class GpsCalcul {
	
	private static double earthRadius = 6371.0; // kilometers
	
	/*
	 * calcule la distance entre deux points distinct début et fin
	 * Variable Point debut et fin
	 * Return double
	 */
	
	public static double Distance(Point debut, Point fin) 
    { 
      
      double dLat = Math.toRadians(fin.lat - debut.lat );
      double dLng = Math.toRadians(fin.lon - debut.lon );
      double sindLat = Math.sin(dLat / 2);
      double sindLng = Math.sin(dLng / 2);
      double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)* Math.cos(Math.toRadians(debut.lat)) * Math.cos(Math.toRadians(fin.lat));

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
	
	public static double clacBearing(Point debut, Point fin)
	{
		double dLon = Math.toRadians(fin.lon-debut.lon);
		Point localDebut = new Point(debut.lat,debut.lon);
		Point localFin = new Point(fin.lat,fin.lon);
		
		localDebut.setLat(Math.toRadians(localDebut.lat));
		localDebut.setLon( Math.toRadians(localDebut.lon));
		localFin.setLat(Math.toRadians(localFin.lat));
		localFin.setLon(Math.toRadians(localFin.lon));
		
		
		double y = Math.sin(dLon) * Math.cos(localFin.lat);
		
		double x = Math.cos(localDebut.lat) * Math.sin(localFin.lat) - (Math.sin(localDebut.lat) * Math.cos(localFin.lat) * Math.cos(dLon));
		
		double result = Math.atan2(y, x);
		
		result = Math.toDegrees(result);
		

		
		return result;
		
		
	}
	
	/*
	 * Retrouver un point GPS à partir d'un Bearing, la distance entre les deux point et le point de départ
	 * Variable double brng, dist, Point point
	 * return Point
	 */
	
	public static Point getpointBrng(double brng, double dist, Point point){
		
		dist = dist/earthRadius;
		
		Point localPoint = new Point(point.lat,point.lon);
		
		brng = Math.toRadians(brng);
		
		localPoint.setLat( Math.toRadians(localPoint.lat));
		localPoint.setLon(Math.toRadians(localPoint.lon));
		
		
		localPoint.setLat( Math.asin( Math.sin(localPoint.lat)*Math.cos(dist) + Math.cos(localPoint.lat)*Math.sin(dist)*Math.cos(brng) ));
		
		double a = Math.atan2(Math.sin(brng)*Math.sin(dist)*Math.cos(localPoint.lat), Math.cos(dist)-Math.sin(localPoint.lat)*Math.sin(localPoint.lat));
		
		localPoint.setLon(localPoint.lon+ a);
		localPoint.setLon((localPoint.lon + 3*Math.PI) % (2*Math.PI) - Math.PI);
		
		
		localPoint.setLat(Math.toDegrees(localPoint.lat));
		localPoint.setLon(Math.toDegrees(localPoint.lon));
		
	
		
		return localPoint;
			
	}
	
	/*
	 * Liste les waypoints entre deux point distinct debut et fin, d'intervalle X distEntrepoint
	 * Varible Point début, fin, double distEntrepoint
	 * return ArrayList<Point>
	 */
	public static ArrayList<Point> getListWayPoint(Point debut, Point fin, double distEntrepoint ){
		
		ArrayList<Point> wayPoints = new ArrayList<Point>();
		
		double dist = Distance(debut,fin) * 1000;
		

		double brng = clacBearing(debut,fin);
		
		
		Point point = new Point(debut.lat,debut.lon);
		
		double distBrng = distEntrepoint/1000;
		
		
		 System.out.println("distance = " +  dist + " distance 2 point = " +distEntrepoint);
		
		 wayPoints.add(point);
		 double Ndist = Distance(debut,point) * 1000;
		 
		while(dist > Ndist+distEntrepoint)
		{
			
				point = getpointBrng(brng, distBrng, point);
				
				Ndist = Distance(debut,point) * 1000;
				
				wayPoints.add(point);
				
		}
		wayPoints.add(fin);
		
		 
		return wayPoints;
	}
	public static ArrayList<Point> getListWayPointToc(Point debut, double DistMax, double EcartCercle ){
		
		ArrayList<Point> wayPoints = new ArrayList<Point>();
		Point point = new Point(debut.lat,debut.lon);
		
		double dist = EcartCercle;
		double angle = 0;
		
		wayPoints.add(point);
		
		//System.out.println("distance Max = " +  DistMax + " distance= " +EcartCercle);
		
		while(dist < DistMax ){
			
			angle = 0;
			
			while (angle < 360){
				
				//System.out.println("distance = " +  dist + " angle= " +angle);
				
				point = getpointBrng(angle, dist, debut);
				
				wayPoints.add(point);
				
				angle = angle + 30;
			}
			
			dist = dist + EcartCercle;
		}
		
		return wayPoints;
	}
}
