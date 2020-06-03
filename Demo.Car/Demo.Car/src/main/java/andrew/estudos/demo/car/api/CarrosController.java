package andrew.estudos.demo.car.api;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import andrew.estudos.demo.car.domain.Carro;
import andrew.estudos.demo.car.domain.CarroService;
import andrew.estudos.demo.car.domain.DTO.CarroDTO;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	@Autowired
	private CarroService service;
	
	
	@GetMapping()
	public ResponseEntity get() {
		return new ResponseEntity<>(service.getCarros(), HttpStatus.OK); 
	}
		
	
	@GetMapping("/{id}")
	public ResponseEntity<CarroDTO> get(@PathVariable("id") Long id) {
		Optional<CarroDTO> carro = service.getCarrosById(id);
		
		return carro.isPresent() ?
				ResponseEntity.ok(carro.get()) :
			    ResponseEntity.notFound().build();
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo) {
	List<CarroDTO> carros = service.getCarrosByTipo(tipo);
	
	return carros.isEmpty() ?
			ResponseEntity.noContent().build() :
			ResponseEntity.ok(carros);
	}
	
	@PostMapping
	public ResponseEntity post(@RequestBody Carro carro) {
		
		try {
			CarroDTO c = service.insert(carro);
			
			URI location = getUri(c.getId());
			return ResponseEntity.created(location).build();
		} catch (Exception ex) {
			return ResponseEntity.badRequest().build();
		}

	}
	
	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(id).toUri();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		carro.setId(id);
		
		CarroDTO c = service.update(carro, id);
		
		return c != null ?
				ResponseEntity.ok(c) :
				ResponseEntity.notFound().build();
	}
	
	@DeleteMapping()
	public String delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return "Carro deletado com sucesso.";
	}
}
