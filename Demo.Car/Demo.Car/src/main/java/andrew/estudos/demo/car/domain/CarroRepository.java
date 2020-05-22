package andrew.estudos.demo.car.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CarroRepository extends CrudRepository<Carro, Long> {

	List<Carro> findByTipo(String tipo);
}
