package andrew.estudos.demo.car.domain;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import andrew.estudos.demo.car.domain.DTO.CarroDTO;

@Service
public class CarroService {

	@Autowired
	private CarroRepository rep;

	public List<CarroDTO> getCarros() {

		return rep.findAll().stream().map(CarroDTO::new).collect(Collectors.toList());

	}


	public Optional<Carro> getCarrosById(Long id) {

		return rep.findById(id);
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {

		return rep.findByTipo(tipo).stream().map(CarroDTO::new).collect(Collectors.toList());

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
