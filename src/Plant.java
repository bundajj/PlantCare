
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Plant {

	private String name;
	private boolean needforlight;
	private int needforwater;
	private LocalDate lastWatering;
	private boolean stingy;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getNeedforlight() {
		return needforlight;
	}

	public void setNeedforlight(boolean needforlight) {
		this.needforlight = needforlight;
	}

	public int getNeedforwater() {
		return needforwater;
	}

	public void setNeedforwater(int needforwater) {
		this.needforwater = needforwater;
	}

	public boolean getStingy() {
		return stingy;
	}

	public void setStingy(boolean stingy) {
		this.stingy = stingy;
	}

	public LocalDate getLastWatering() {
		return lastWatering;
	}

	public void setLastWatering(LocalDate lastWatering) {
		this.lastWatering = lastWatering;
	}

	public Plant(String name, boolean needforlight, int needforwater, LocalDate lastWatering, boolean stingy) {

		this.name = name;
		this.needforlight = needforlight;
		this.needforwater = needforwater;
		this.lastWatering = lastWatering;
		this.stingy = stingy;
	}
	

	public Plant(boolean a) {
		
	}

	public Plant() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Give the plant name!");
		this.name = scanner.nextLine();

		System.out.println("How often need to be watered?(in days");
		this.needforwater = scanner.nextInt();
		scanner.nextLine();

		System.out.println("Need  Light? true or false");
		this.needforlight = scanner.nextBoolean();
		scanner.nextLine();

		System.out.println(" Is it stingy?");
		this.stingy = scanner.nextBoolean();
		scanner.nextLine();

		try {
			System.out.println("When was the plant last watered? Enter a (yyyy/mm/dd) format!");
			DateTimeFormatter dareFormat= DateTimeFormatter.ofPattern("yyyy-MM-dd");
			this.lastWatering=LocalDate.parse(scanner.nextLine(),dareFormat);

			//this.lastWatering = new SimpleDateFormat("yyyy/MM/dd").parse(scanner.nextLine());

		} catch (Exception e) {
			System.out.println(e);
		}

		/*
		 * Date date = new Date(); System.out.println("Give the year");
		 * date.setYear(scanner.nextInt()); scanner.nextLine();
		 * System.out.println("Give the moth"); date.setMonth(scanner.nextInt());
		 * scanner.nextLine(); System.out.println("Give the day");
		 * date.setDay(scanner.nextInt()); scanner.nextLine(); this.lastWatering = date;
		 */

	}

	@Override
	public String toString() {

		return "Név: " + name  +"\nNapfényginéyes-e?: "+ needforlight+" \nHány naponta kell öntözni: "+ needforwater+ "\nUtolsó öntözés dátuma: " + lastWatering+" \nSzúros-e: "+stingy;
	}
	public String toString(boolean x) {
		
		return name+" "+needforlight+" "+needforwater+" "+lastWatering+" "+stingy+"\n";
				
	}
	

}
