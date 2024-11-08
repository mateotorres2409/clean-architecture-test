package local.mateo.cleanArchitecture.application.usecase.fruit;

import java.util.Optional;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import local.mateo.cleanArchitecture.domain.model.FruitModel;
import local.mateo.cleanArchitecture.domain.repository.FruitRepository;

@ApplicationScoped
public class GetFruitByIdUseCase {

    private final FruitRepository fruitRepository;

    @Inject
    public GetFruitByIdUseCase(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public Optional<FruitModel> execute(Long id) {
        return fruitRepository.findById(id);
    }
}
