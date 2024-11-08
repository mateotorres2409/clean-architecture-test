package local.mateo.cleanArchitecture.domain.repository;

import java.util.List;
import java.util.Optional;

import local.mateo.cleanArchitecture.domain.model.FruitModel;

public interface FruitRepository {
    FruitModel save(FruitModel fruit);
    List<FruitModel> findAll();
    Optional<FruitModel> findById(Long id);
    boolean deleteById(Long id);
    FruitModel update(FruitModel fruit);
}
