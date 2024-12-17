package rakuproject.raku.domain.house.entity;

import jakarta.persistence.*;
import lombok.*;




@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Image")
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long B_buildNumber;

    @Lob
    @Column(name = "B_picture", nullable = true)
    private byte[] Picture;






}
