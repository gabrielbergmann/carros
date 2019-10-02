package br.com.gabriel.api.carros;

import br.com.gabriel.api.infra.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {
    @Autowired
    CarroRepository carroRepository;

    public List<CarroDTO> getCarros(Pageable pegeable) {
        List<CarroDTO> carro = carroRepository.findAll(pegeable).stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());;
        return carro;
    }

    public CarroDTO getCarroByID(Long id) {
        Optional<Carro> carro = carroRepository.findById(id);
        return carro.map(CarroDTO::create).orElseThrow(() -> new ObjectNotFoundException("Carro não encontrado!"));
    }

    public List<CarroDTO> getCarroByTipo(String tipo, Pageable pageable) {
        List<CarroDTO> carro = carroRepository.findByTipo(tipo, pageable).stream().map(c -> CarroDTO.create(c)).collect(Collectors.toList());
        return carro;
    }

    public CarroDTO save(Carro carro) {
        Assert.isNull(carro.getId(), "Não foi possível inserir o registro");
        return CarroDTO.create(carroRepository.save(carro));
    }

    public List<Carro> getCarrosFake() {

        List<Carro> carros = new ArrayList<>();

        return carros;
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        //busca o carro no banco de dados
        Optional<Carro> optional = carroRepository.findById(id);
        //valida se o carro existe
        if (optional.isPresent()) {
            // copia as propriedades vindas do json para o banco
            Carro db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.print("Carro id: " + db.getId());
            //salva as alterações
            carroRepository.save(db);
            return CarroDTO.create(db);
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        carroRepository.deleteById(id);
    }
}
