package andrew.estudos.demo.car.domain.DTO;

import org.modelmapper.ModelMapper;

import andrew.estudos.demo.car.domain.Carro;
import lombok.Data;

@Data
public class CarroDTO {
	private Long id;
	
	private String nome;
	private String tipo;
	
	
	public static CarroDTO create (Carro c) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(c, CarroDTO.class);
	}
	

}
