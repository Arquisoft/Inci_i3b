package uniovi.es.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uniovi.es.entities.Incidence;
import uniovi.es.producers.KafkaProducer;
import uniovi.es.repositories.IncidentsRepository;


@Service
public class IncidentsService {

	private final static String topic = "incidences";
	
	@Autowired
	private IncidentsRepository incidentsRepository;
	@Autowired
	private KafkaProducer kafkaProducer;

	public void addIncident(Incidence incident) {
		incidentsRepository.save(incident);
		
		kafkaProducer.send(topic, incident);
	}

	public List<Incidence> getAgentIncidents(String agentName) {
		return incidentsRepository.getIncidentsByUsername(agentName);
	}

}
