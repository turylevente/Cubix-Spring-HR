package hu.cubix.hr.tlevi.repositories;

import hu.cubix.hr.tlevi.models.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {

}
