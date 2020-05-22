package andrew.estudos.demo.car.api;


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

import andrew.estudos.demo.car.domain.Carro;
import andrew.estudos.demo.car.domain.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	@Autowired
	private CarroService service;
	
	
	@GetMapping()
	public ResponseEntity<Iterable<Carro>> get() {
		return new ResponseEntity<>(service.getCarros(), HttpStatus.BAD_REQUEST); 
	}
		
	
	@GetMapping("/{id}")
	public Optional<Carro> get(@PathVariable("id") Long id) {
		return service.getCarrosById(id);
	}
	
	@GetMapping("/tipo/{tipo}")
	public Iterable<Carro> get(@PathVariable("tipo") String tipo) {
		return service.getCarrosByTipo(tipo);
	}
	
	@PostMapping
	public String post(@RequestBody Carro carro) {
		Carro c = service.insert(carro);
		
		return "Carro salvo com sucesso.";
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		
		Carro c = service.update(carro, id);
		
		return "Carro salvo com sucesso.";
	}
	
	@DeleteMapping()
	public String delete(@PathVariable("id") Long id) {
		
		service.delete(id);
		
		return "Carro deletado com sucesso.";
	}
}
