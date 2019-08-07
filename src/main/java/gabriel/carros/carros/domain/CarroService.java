package gabriel.carros.carros.domain;

import gabriel.carros.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<Carro> getCarros() {
        return carroRepository.findAll().stream().map(CarroDTO::new).collect(Collectors.toList());
    }

    public Optional<Carro> getCarroByID(Long id) {
        return carroRepository.findById(id);
    }

    public List<Carro> getCarroByTipo(String tipo) {
        return carroRepository.findByTipo(tipo);
    }

    public Carro save(Carro carro) {
        return carroRepository.save(carro);
    }

    public List<Carro> getCarrosFake() {

        List<Carro> carros = new ArrayList<>();

        return carros;
    }

    public Carro update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro");

        //busca o carro no banco de dados
        Optional<Carro> optional = getCarroByID(id);
        //valida se o carro existe
        if(optional.isPresent()){
            // copia as propriedades vindas do json para o banco
            Carro db = optional.get();
            db.setNome(carro.getNome());
            db.setTipo(carro.getTipo());
            System.out.print("Carro id: "+db.getId());
            //salva as alterações
            carroRepository.save(db);
            return db;
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro");
        }
    }

    public void delete(Long id) {
        Optional<Carro> carro = getCarroByID(id);
        if (carro.isPresent()) {
            carroRepository.deleteById(id);
        } else {
            throw new RuntimeException("Não foi possível deletar o registro");
        }
    }
}
