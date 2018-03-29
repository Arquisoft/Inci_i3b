package es.uniovi.asw.parser.agents;

/**
 * 
 * @author Jorge. Class used to represent abstract Agents and parse their data.
 *
 */
public abstract class AbstractAgent {
	protected String name;
	protected String location;
	protected String email;	
	protected String identifier;
	protected int kind;
	protected String password;
	
	public AbstractAgent(String name, String location, String email, String identifier, int kind) {
		super();
		this.name = name;
		this.location = location;
		this.email = email;
		this.identifier = identifier;
		this.kind = kind;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdentifier() {
		return identifier;
	}
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}
	public int getKind() {
		return kind;
	}
	public void setKind(int kind) {
		this.kind = kind;
	}

	public void setPassword(String string) {
		password = string;
	}
	public String getPassword() {
		return this.password;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identifier == null) ? 0 : identifier.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbstractAgent other = (AbstractAgent) obj;
		if (identifier == null) {
			if (other.identifier != null)
				return false;
		} else if (!identifier.equals(other.identifier))
			return false;
		return true;
	}
	
	
	
}
