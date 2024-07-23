package demo_jdbc;

import demo_jdbc.models.DriverMaxPoint;
import demo_jdbc.respositories.QueryRepository;

public class Main {

    public static void main(String[] args) {
        
        


        QueryRepository queryRepository = new QueryRepository();
        var results1 = queryRepository.getDriversMaxPoints();  // Cambiado a getDriversMaxPoints
        System.out.println("\n");
		System.out.println("Conductores con mas puntos:");
        for(DriverMaxPoint rs: results1) {
            System.out.println(rs.getDriverName() + "\t" + rs.getPoints());
        }
    }
}
