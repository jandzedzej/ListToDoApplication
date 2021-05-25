package pl.projektyjava.listToDoJavaFX.body;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryTaskRepository extends CrudRepository<StoryTask, Long> {
}
