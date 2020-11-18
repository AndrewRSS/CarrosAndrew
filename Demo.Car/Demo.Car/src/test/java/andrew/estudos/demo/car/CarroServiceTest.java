package andrew.estudos.demo.car;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import andrew.estudos.demo.car.domain.Carro;
import andrew.estudos.demo.car.domain.CarroService;
import andrew.estudos.demo.car.domain.DTO.CarroDTO;

@SpringBootTest
class CarroServiceTest {
	
	@Autowired
	private CarroService service;

	@Test
	void testandoSalvamentoDeCarros() {
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivos");
		
		CarroDTO c = service.insert(carro);
		
		assertNotNull(c);
		
		Long id = c.getId();
        assertNotNull(id);
        
        c = service.getCarrosById(id);
        assertNotNull(c);
        

        assertEquals("Ferrari", c.getNome());
        assertEquals("esportivos", c.getTipo());
        
        service.delete(id);
        
        assertNull(service.getCarrosById(id));
	}
	
	@Test
	public void teste2() {
		List<CarroDTO> carros = service.getCarros();
		assertEquals(30, carros.size());
	}

}
