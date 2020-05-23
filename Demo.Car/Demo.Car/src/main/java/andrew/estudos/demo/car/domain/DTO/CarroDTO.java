package andrew.estudos.demo.car.domain.DTO;

import andrew.estudos.demo.car.domain.Carro;
import lombok.Data;

@Data
public class CarroDTO {
	private Long id;
	
	private String nome;
	private String tipo;
	
	public CarroDTO(Carro c) {
		this.id = c.getId();
		this.nome = c.getNome();
		this.tipo = c.getTipo();
	}
	

}
