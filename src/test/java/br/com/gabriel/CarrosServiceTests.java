package br.com.gabriel;

import br.com.gabriel.api.exception.ObjectNotFoundException;
import br.com.gabriel.domain.Carro;
import br.com.gabriel.domain.CarroService;
import br.com.gabriel.domain.dto.CarroDTO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarrosServiceTests {

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
		CarroDTO op =  service.getCarroByID(id);
		Assert.assertNotNull(op);

		//Valida se o carro foi cadastrado

		Assert.assertEquals("Ferrari", carroDTO.getNome());
		Assert.assertEquals("esportivo", carroDTO.getTipo());

		//deleta o carro
		service.delete(id);

		try {
		//verifica se o carro foi deletado
		Assert.assertNull(service.getCarroByID(id));
		} catch (ObjectNotFoundException e) {
			//TESTE OK
		}
	}

	@Test
	public void testeLista() {
		List<CarroDTO> car = service.getCarros();
		Assert.assertEquals(30, car.size());
	}

	@Test
	public void testeGet() {
		CarroDTO op = service.getCarroByID(11L);
		Assert.assertNotNull(op);
		Assert.assertEquals("Ferrari FF", op.getNome());
	}

}
