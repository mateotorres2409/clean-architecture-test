package local.mateo.cleanArchitecture.application.usecase.fruit;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import local.mateo.cleanArchitecture.domain.repository.FruitRepository;

@ApplicationScoped
public class DeleteFruitByIdUseCase {

    private final FruitRepository fruitRepository;

    @Inject
    public DeleteFruitByIdUseCase(FruitRepository fruitRepository) {
        this.fruitRepository = fruitRepository;
    }

    public boolean execute(Long id) {
        return fruitRepository.deleteById(id);
    }
}
