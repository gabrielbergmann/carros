package gabriel.carros.carros.domain.dto;

import gabriel.carros.carros.domain.Carro;
import lombok.Data;
import org.modelmapper.ModelMapper;

@Data
public class CarroDTO {
    private Long id;
    private String nome;
    private String tipo;

    public static CarroDTO create(Carro c) {
        ModelMapper modelMapper = new ModelMapper();
        return  modelMapper.map(c, CarroDTO.class);
    }
}
