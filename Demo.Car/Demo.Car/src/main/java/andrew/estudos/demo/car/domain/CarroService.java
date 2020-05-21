package andrew.estudos.demo.car.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarroService {
	
	@Autowired
	private CarroRepository rep;
	
	public Iterable<Carro> getCarros() {
		return rep.findAll();
	}
	
	
	public Optional<Carro> getCarrosById(Long id) {

		return rep.findById(id);
	}
	
	public Iterable<Carro> getCarrosByTipo(String tipo) {

		return rep.findByTipo(tipo);
	}
	
	
	
	public List<Carro> getCarrosFake() {
		List<Carro> carros = new ArrayList <>();
		
		carros.add(new Carro( 1L, "fusca"));
		carros.add(new Carro( 2L, "Brasilia"));
		carros.add(new Carro( 3L, "Chevete"));

		return carros;
	}


	public Carro insert(Carro carro) {
		Assert.isNull(carro.getId());
		
		return rep.save(carro);
	}
	
	public Carro update(Carro carro, Long id) {
		Assert.notNull(id);
		//Busca o carro no banco de dados
		Optional<Carro> optional = getCarrosById(id);
		if(optional.isPresent()) {
			Carro db = optional.get();
			//copiar as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id" + db.getId());
			
			
			// Atualiza o carro
			rep.save(db);
			
			return db;
		} else {
			throw new RuntimeException("Não foi possível atualizar o registro");
		}
	}


	public void delete(Long id) {
		Optional<Carro> carro = getCarrosById(id);
		if(carro.isPresent()) {
			rep.deleteById(id);
		}
		
	}


	


	






	

}
