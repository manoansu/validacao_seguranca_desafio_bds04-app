package pt.amane.bds04.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pt.amane.bds04.dto.EventDTO;
import pt.amane.bds04.entities.City;
import pt.amane.bds04.entities.Event;
import pt.amane.bds04.repositories.EventRepository;

@Service
public class EventService {
	
	@Autowired
	private EventRepository repository;
	
	@Transactional(readOnly = true)
	public Page<EventDTO> findAll(Pageable pageable){
		Page<Event> list = repository.findAll(pageable);
		return list.map(dto -> new EventDTO(dto));
	}

	@Transactional
	public EventDTO create(EventDTO dto) {
		Event event = new Event();
		event.setName(dto.getName());
		event.setDate(dto.getDate());
		event.setUrl(dto.getUrl());
		event.setCity(new City(dto.getCityId(), null));
		event = repository.save(event);
		return new EventDTO(event);
	}

}
