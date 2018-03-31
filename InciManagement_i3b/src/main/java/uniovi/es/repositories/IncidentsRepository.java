package uniovi.es.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import uniovi.es.entities.Incidence;

public interface IncidentsRepository extends MongoRepository<Incidence, String> {
	public List<Incidence> getIncidentsByUsername(String name);
}
