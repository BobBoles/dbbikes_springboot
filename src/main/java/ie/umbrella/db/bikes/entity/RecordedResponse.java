package ie.umbrella.db.bikes.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A POJO to allow Jackson map JSON to the Model returned to ThymeLeaf
 * The database stores recorded JSON responses from the JCDecaux api
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="RecordedResponse")
public class RecordedResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;

    @Lob
    @Column
    private String jsonData;
}
