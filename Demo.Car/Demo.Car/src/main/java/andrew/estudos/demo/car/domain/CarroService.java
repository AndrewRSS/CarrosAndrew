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
	
		return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());
	
	}
	

	public Optional<CarroDTO> getCarrosById(Long id) {

		return rep.findById(id).map(CarroDTO::create);
	}

	public List<CarroDTO> getCarrosByTipo(String tipo) {

		return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());

	}


	public CarroDTO insert(Carro carro) {
		Assert.isNull(carro.getId());

		return CarroDTO.create(rep.save(carro));
	}

	public CarroDTO update(Carro carro, Long id) {
		Assert.notNull(id);
		//Busca o carro no banco de dados
		Optional<Carro> optional = rep.findById(id);
		if(optional.isPresent()) {
			Carro db = optional.get();
			//copiar as propriedades
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id" + db.getId());

			// Atualiza o carro
			rep.save(db);

			return CarroDTO.create(db);
		} else {
			throw new RuntimeException("Não foi possível atualizar o registro");
		}
	}


	public void delete(Long id) {
		Optional<CarroDTO> carro = getCarrosById(id);
		if(carro.isPresent()) {
			rep.deleteById(id);
		}

	}














}
