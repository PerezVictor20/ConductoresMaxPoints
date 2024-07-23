package demo_jdbc.respositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import demo_jdbc.models.DriverMaxPoint;

public class QueryRepository {
    
    String jdbcUrl = "jdbc:postgresql://localhost:5433/formula1";
    String user = "postgres";
    String password = "1234";
    
    public List<DriverMaxPoint> getDriversMaxPoints() {
        ArrayList<DriverMaxPoint> results = new ArrayList<DriverMaxPoint>();
        
        String sql = "SELECT d.forename || ' ' || d.surname AS name, SUM(r.points) AS total_points "
                   + "FROM results as r "
                   + "JOIN drivers as d ON r.driver_id = d.driver_id "
                   + "GROUP BY name "
                   + "ORDER BY total_points DESC "
                   + "LIMIT 10;";
        
        try {
            // Establecer la conexion
            Connection conn = DriverManager.getConnection(jdbcUrl, user, password);
            
            // Crear una sentencia
            PreparedStatement statement = conn.prepareStatement(sql);
            
            ResultSet rs = statement.executeQuery();
            
            while (rs.next()) {
                String driverName = rs.getString("name");
                float points = rs.getFloat("total_points");
                
                DriverMaxPoint result = new DriverMaxPoint(driverName, points);
                results.add(result);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
        return results;
    }
}
