package week_7;

public class SeriesSystemReliability { 
    public static void main(String[] args) { 
        double[] componentReliabilities = {0.99, 0.98, 0.97, 0.96, 0.95}; 
        double systemReliability = calculateSeriesSystemReliability(componentReliabilities); 
        System.out.println("The reliability of the series system is: " + systemReliability); 
    } 
    
    public static double calculateSeriesSystemReliability(double[] reliabilities) { 
        double systemReliability = 1.0; // Start with a system that's initially 100% reliable 
        for (double reliability : reliabilities) { 
            systemReliability *= reliability; 
        } 
        return systemReliability; 
    } 
}