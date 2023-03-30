import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.*;

import com.sun.source.tree.ContinueTree;

public class Main {

	public static void main(String[] args) {

		PlantDatabase database = new PlantDatabase();
		
		
		try {
			File myObj = new File("Növenyek.txt");
		      Scanner myReader = new Scanner(myObj);
		     
		     while (myReader.hasNext()) {
		    	 String []data = myReader.nextLine().split(" ");
		    	 Plant a= new Plant(true);
		    	 a.setName(data[0]);
		    	 a.setNeedforlight((Boolean.parseBoolean(data[1])));
		    	 a.setNeedforwater((Integer.parseInt(data[2])));
		    	 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    	 a.setLastWatering(LocalDate.parse(data[3],formatter));
		    	 a.setStingy((Boolean.parseBoolean(data[4])));
		    	database.addTolist(a);
		    	
			}
		     
		      myReader.close();
		      System.out.println("A fáljban szereplő növények:\n---------------------------------");
				AllPlantinfo(database);
		} catch (FileNotFoundException e) {
			System.out.println("A fálj még nem létezik, hozzon létre egyet.");
		}
		
		
		menu(database);
		 try {
			FileWriter myWriter = new FileWriter("Növenyek.txt",false);
			for (Plant data :database.getListOfPlants()) {
				myWriter.write(data.toString(true));
			}
			myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}

	public static void AllPlantinfo(PlantDatabase db) {

		if (db.getListOfPlants().size() > 0) {
			for (Plant p : db.getListOfPlants()) {

				System.out.println(p.toString());
				System.out.println("----------------------------------");

			}
		} else {
			System.out.println("A lista üres");
		}

	}

	public static void modifyPlant(Plant plant) {

		//System.out.println("What do you want to modify?");
		//System.out.println("1 -name 2 -needforlight 3- needforwater 4- lastWatering 5-stingy 6- exit");
		Scanner myScanner = new Scanner(System.in);
		boolean exit = false;
		//do {
			while(!exit) {
			try {
				System.out.println("What do you want to modify?");
				System.out.println("1 -name 2 -needforlight 3- needforwater 4- lastWatering 5-stingy 6- exit");
				switch (myScanner.nextInt()) {

				case 1:
					myScanner.nextLine();
					System.out.println("Give the plant name!");
					plant.setName(myScanner.nextLine());
					break;
				case 2:
					myScanner.nextLine();
					System.out.println("Light? true or false");
					plant.setNeedforlight(myScanner.nextBoolean());

					break;

				case 3:
					myScanner.nextLine();
					System.out.println("Water in days?");
					plant.setNeedforwater(myScanner.nextInt());

					break;
				case 4:

					try {
						myScanner.nextLine();
						System.out.println("Change last watering date  yyyy/MM/dd");
						DateTimeFormatter dareFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
						plant.setLastWatering(LocalDate.parse(myScanner.nextLine(), dareFormat));
					} catch (Exception e) {

						System.out.println(e + "ErroR");
					}
					break;
				case 5:
					myScanner.nextLine();
					System.out.println("Stingy? true or false");
					plant.setStingy(myScanner.nextBoolean());

					break;
				case 6:
					exit = true;
					break;
				default:
					System.out.println("ERROR: Wrong number given");

				}
			} catch (InputMismatchException e) {
				System.out.println(e);
				
			}finally {
				myScanner.nextLine();
			}
			}
		//} while (!exit);
		
	}

	public static ArrayList<Plant> needToWater(PlantDatabase db) {
		ArrayList<Plant> returnList = new ArrayList<Plant>();
		for (Plant p : db.getListOfPlants()) {
			if (ChronoUnit.DAYS.between(p.getLastWatering(), LocalDate.now()) > p.getNeedforwater()) {
				returnList.add(p);
			}
		}
		return returnList;
	}

	
	public static void menu(PlantDatabase db) {
		
		boolean exit = false;
		Scanner scanner = new Scanner(System.in);
		do {
		//while(!exit) {
			
			System.out.println("***************Növénygondozás**************");
			System.out.println(
					"1- Új növény rögzítése \n2 - Növények törlése\n3 Növény módosítása \n4 Növény listázása\n5 Szomjas növény öntözése \n6 kilépés");
			switch (scanner.nextInt()) {
			case 1:
				Plant p = new Plant();
				db.addTolist(p);

				break;
			case 2:
				scanner.nextLine();
				System.out.println("Adja meg a növény nevét");
				String novenyCase2 = scanner.nextLine();
				for (int i = 0; i < db.getListOfPlants().size(); i++) {
					if (novenyCase2.equals(db.getListOfPlants().get(i).getName())) {
						db.removeFromlist(db.getListOfPlants().get(i));
					}

				}

				break;
			case 3:
				scanner.nextLine();
				System.out.println("Adja meg a növény nevét a listából amit modósítani szeretne");
				AllPlantinfo(db);
				String novenyCase3 = scanner.nextLine();
				// index = 0;
				for (int i = 0; i < db.getListOfPlants().size(); i++) {
					if (novenyCase3.equals(db.getListOfPlants().get(i).getName())) {
						
						modifyPlant(db.getListOfPlants().get(i));
					}
					
				//	index = i;
					
				}
				
				//modifyPlant(db.getListOfPlants().get(index));

				break;
			case 4:
				AllPlantinfo(db);
				break;
			case 5:
				// index5 = 0;
				scanner.nextLine();
				System.out.println("Válassza ki a növényt a listából:");
				
				ArrayList<Plant> locsolando= needToWater(db);
				for (Plant plant : locsolando) {
					System.out.println(plant.getName());
				}
				
				String novenyCase5 = scanner.nextLine();
				for (int i = 0; i < db.getListOfPlants().size(); i++) {
					if (novenyCase5.equals(db.getListOfPlants().get(i).getName())) {
						db.getListOfPlants().get(i).setLastWatering(LocalDate.now());
					}
					
						
					
				}
				
				break;
			case 6:
				System.out.println("Most már kikapcsolhatja a számítógépet");
				exit = true;

				break;

			default:
				System.out.println("Error Wrong number!(1-5)");
				break;
			}
			
				
	//}
		} while (!exit);
		
	}
	

	
}
