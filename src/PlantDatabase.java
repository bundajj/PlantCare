import java.util.*;
public class PlantDatabase {

	
	private ArrayList<Plant> ListOfPlants;

	
	public PlantDatabase() {
		
		this.ListOfPlants=new ArrayList<>();
	}
	

	public ArrayList<Plant> getListOfPlants() {
		return ListOfPlants;
	}


	public void addTolist(Plant...plants) {
		
		this.ListOfPlants.addAll(Arrays.asList(plants));
		
	}
	
public void removeFromlist(Plant...plants) {
		
	this.ListOfPlants.removeAll(Arrays.asList(plants));
}
}
