package kmeans_proj;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

import weka.clusterers.SimpleKMeans;
import weka.core.Instance;
import weka.core.Instances;
//import weka.core.converters.ArffLoader.ArffReader;

public class Clustering {

	public static void main(String[] args) throws Exception {
		
		BufferedReader reader = null;
		reader = new BufferedReader(new FileReader("all_month_new.arff"));
		
		Instances data = new Instances(reader);
		 reader.close();
		// setting class attribute
		// data.setClassIndex(data.numAttributes() - 1);
		//System.out.print("executed");
		
		Scanner input = new Scanner(System.in);
		System.out.println("Please enter number of clusters you want: ");
     	int numberOfClusters = input.nextInt();
     	
     	SimpleKMeans kmeans = new SimpleKMeans();

     	
     	kmeans.setPreserveInstancesOrder(true);
     	kmeans.setNumClusters(numberOfClusters);
     	kmeans.buildClusterer(data);

     	// This array returns the cluster number (starting with 0) for each instance
     	// The array has as many elements as the number of instances
     	int[] assignments = kmeans.getAssignments();

     	//int i=0;
     	/*for(int clusterNum : assignments) {
     	    System.out.printf("Instance %d -> Cluster %d", i, clusterNum);
     	    i++;
     	}*/
	    for (int k = 0; k < data.numInstances(); k++) {
	      System.out.println( data.instance(k) + " is in cluster " + kmeans.clusterInstance(data.instance(k)));

	    }
     	
	    Instances instances = kmeans.getClusterCentroids();
	    for ( int j = 0; j < instances.numInstances(); j++ ) {
	        // for each cluster center
	        Instance inst = instances.instance( j );
	        // as you mentioned, you only had 1 attribute
	        // but you can iterate through the different attributes
	        double value = inst.value( 0 );
	        System.out.println( "Value for centroid " + j + ": " + value );
	    }
	    
	   	    
}
}
