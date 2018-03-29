package es.uniovi.asw.parser.agents;

/**
 * 
 * @author Jorge  Class used to represent SensorAgents and parse their data.
 *
 */
public class SensorAgent extends AbstractAgent {

	public SensorAgent(String name, String location, String email, String identifier, int kind) {
		super(name, location, email, identifier, kind);
	}

	public SensorAgent(Object[] data) {
		super((String)data[0],(String)data[1],(String)data[2],(String) data[3], (Integer)data[4]);	
	}

	public String toString() {
		return "Sensor Agent [Name=" + this.name
				+ ", location=" + this.location
				+ ", email=" + this.email
				+ ", identifier=" + this.identifier
				+"]";
	}
}
