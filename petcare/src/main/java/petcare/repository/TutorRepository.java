package petcare.repository;

import petcare.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    Optional<Tutor> findByCpf(String cpf);
}
