package gabriel.carros.carros;

import gabriel.carros.carros.domain.Carro;
import gabriel.carros.carros.domain.CarroService;
import gabriel.carros.carros.domain.dto.CarroDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrosApplicationTests {

	@Autowired
	private CarroService service;

	@Test
	public void testeCadastro() {
		Carro c = new Carro();
		c.setNome("Ferrari");
		c.setTipo("esportivo");
		CarroDTO carroDTO = service.save(c);
		Assert.assertNotNull(carroDTO);

		//Obtem o id do carro cadastrado
		Long id = carroDTO.getId();
		Assert.assertNotNull(id);

		//Busca o objeto do carro utilizando o id buscado anteriormente
		Optional<CarroDTO> op =  service.getCarroByID(id);
		Assert.assertTrue(op.isPresent());

		//Valida se o carro foi cadastrado
		carroDTO = op.get();
		Assert.assertEquals("Ferrari", carroDTO.getNome());
		Assert.assertEquals("esportivo", carroDTO.getTipo());

		//deleta o carro
		service.delete(id);

		//verifica se o carro foi deletado
		Assert.assertFalse(service.getCarroByID(id).isPresent());
	}

	@Test
	public void testeLista() {
		List<CarroDTO> car = service.getCarros();
		Assert.assertEquals(30, car.size());
	}

	@Test
	public void testeGet() {
		Optional<CarroDTO> op = service.getCarroByID(11L);
		Assert.assertTrue(op.isPresent());
		CarroDTO c = op.get();
		Assert.assertEquals("Ferrari FF", c.getNome());
	}

}
