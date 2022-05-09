/**
 * This class is used to aide the creation and functionality of PirateEncounter, 
 * WeatherEncounter and RescueSailors who are all children of this parent class
 * @author Fergus and Oliver
 *
 */
public class RandomEncounter {
	

	private String description;
	//private int chance;
	
	/**
	 * set description to input
	 * @param tdescription
	 */
	public void setDescription(String tdescription) {
		description = tdescription;
	}
	
	/**
	 * 
	 * @return description
	 */
	public String getDescription() {
		return description;
	}

}

