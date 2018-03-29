package es.uniovi.asw.parser.agents;


/**
 * 
 * @author Jorge  Class used to represent EntityAgents and parse their data.
 *
 */
public class EntityAgent extends AbstractAgent {

	public EntityAgent(String name, String location, String email, String identifier, int kind) {
		super(name, location, email, identifier, kind);
	}

	public EntityAgent(Object[] data) {
		super((String)data[0],(String)data[1],(String)data[2],(String) data[3], (Integer)data[4]);	
	}

	public String toString() {
		return "Entity Agent [Name=" + this.name
				+ ", location=" + this.location
				+ ", email=" + this.email
				+ ", identifier=" + this.identifier
				+"]";
	}
}
