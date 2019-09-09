package gabriel.carros.carros.api;

import gabriel.carros.carros.domain.Carro;
import gabriel.carros.carros.domain.CarroService;
import gabriel.carros.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {

    @Autowired
    private CarroService service;

    @GetMapping()
    public ResponseEntity<List<CarroDTO>> get() {
        return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable("id") Long id) {
        Optional<CarroDTO> carro = service.getCarroByID(id);
        return carro.isPresent() ?
            ResponseEntity.ok(carro.get()) :
            ResponseEntity.notFound().build();
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity getCarrosByTipo(@PathVariable("tipo") String tipo) {
        List<CarroDTO> carros = service.getCarroByTipo(tipo);
        return carros.isEmpty() ?
                ResponseEntity.noContent().build() :
                ResponseEntity.ok(carros);
    }

    @PostMapping()
    public ResponseEntity post(@RequestBody Carro carro){
        try {
            CarroDTO carroDTO = service.save(carro);
            URI location = getURI(carroDTO.getId());
            return ResponseEntity.created(location).build();
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    private URI getURI(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(id).toUri();
    }

    @PutMapping("update/{id]")
    public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Carro carro) {
        try {
        carro.setId(id);
        CarroDTO carroDTO = service.update(carro, id);

        return carroDTO != null ?
                ResponseEntity.ok(carroDTO) :
                ResponseEntity.notFound().build();

        } catch (Exception ex){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.delete(id);
        return "Carro deletado";
    }
}
