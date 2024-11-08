package local.mateo.cleanArchitecture.infraestructure.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import local.mateo.cleanArchitecture.domain.model.FruitModel;
import local.mateo.cleanArchitecture.domain.repository.FruitRepository;

@ApplicationScoped
public class InMemoryFruitRepository  implements FruitRepository {

    private final List<FruitModel> fruits = new ArrayList<>();
    private Long currentId = 1L;

    @Override
    public FruitModel save(FruitModel fruit) {
        fruit.setId(currentId);
        fruits.add(fruit);
        currentId++;
        return fruit;
    }

    @Override
    public List<FruitModel> findAll() {
        return new ArrayList<>(fruits);
    }

    @Override
    public Optional<FruitModel> findById(Long id) {
        return fruits.stream().filter(fruit -> fruit.getId().equals(id)).findFirst();
    }
 
    @Override
    public boolean deleteById(Long id) {
        return fruits.removeIf(fruit -> fruit.getId().equals(id));
    }

    @Override
    public FruitModel update(FruitModel updatedFruit) {
        for (int i = 0; i < fruits.size(); i++) {
            if (fruits.get(i).getId().equals(updatedFruit.getId())) {
                fruits.get(i).setName(updatedFruit.getName());
                fruits.get(i).setDescription(updatedFruit.getDescription());
                return fruits.get(i);
            }
        }
        return null; 
    }
}
