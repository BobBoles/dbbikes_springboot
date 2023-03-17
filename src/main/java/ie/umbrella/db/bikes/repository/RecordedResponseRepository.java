package ie.umbrella.db.bikes.repository;

import ie.umbrella.db.bikes.entity.RecordedResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordedResponseRepository extends JpaRepository<RecordedResponse, Long> {
    public RecordedResponse findByUrl(String url);
}
