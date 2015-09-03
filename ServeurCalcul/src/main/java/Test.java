
import java.util.ArrayList;
import java.util.Iterator;


public class Test {
	public static void main(String[] args){
		
		double lat1, lat2, lon1, lon2;
		
		lat1 = 43.56277768243877;
		lon1 = 1.4723151549696922;
		
		lat2 = 43.55781114098443;
		lon2 = 1.4787588268518448;
		
		Point debut = new Point(lat1,lon1);
		Point fin = new Point(lat2,lon2);
		
	
		
		ArrayList<Point> wayPoints =  new ArrayList<Point>();
		double distbypoint= 100;
		
		wayPoints = GpsCalcul.getListWayPoint(debut, fin, distbypoint);
		
		Iterator<Point> points = wayPoints.iterator();
		
		int I = 0;
		while(points.hasNext()){
			
			Point PointEX = points.next();
		
			
			System.out.println("Index: "+I+"\n"+PointEX.toString());
			I++;
			
		}
		double DistMax = 10;
		double EcartCercle = 2;
		
		ArrayList<Point> wayPointsToc =  new ArrayList<Point>();
		wayPointsToc = GpsCalcul.getListWayPointToc(debut, DistMax/1000.0, EcartCercle/1000.0);
		
		Iterator<Point> pointsToc = wayPointsToc.iterator();
		
		int Z = 0;
		while(pointsToc.hasNext()){
			
			Point PointEXTOC = pointsToc.next();
		
			
			System.out.println("IndexTOC: "+Z+"\n"+PointEXTOC.toString());
			Z++;
			
		}
		
		
		
	}
}

