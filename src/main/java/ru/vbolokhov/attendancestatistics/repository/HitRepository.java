package ru.vbolokhov.attendancestatistics.repository;

import org.springframework.data.repository.CrudRepository;
import ru.vbolokhov.attendancestatistics.domain.Hit;

/**
 * An implementation of CrudRepository for Hit entities.
 * @author Vadim Bolokhov
 */
public interface HitRepository extends CrudRepository<Hit, Integer> {
}
