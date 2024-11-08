package local.mateo.cleanArchitecture.domain.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;
import lombok.Getter;

@Data                   
@NoArgsConstructor     
@AllArgsConstructor     
@Builder 
@Setter
@Getter

@Schema(name = "Fruit")
public class FruitModel {
    private Long id;
    private String name;
    private String description;
}
