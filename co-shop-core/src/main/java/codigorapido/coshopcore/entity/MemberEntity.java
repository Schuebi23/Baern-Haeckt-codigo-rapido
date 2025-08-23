package codigorapido.coshopcore.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "members")
@Data
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 60)
    private String displayName;

    @ManyToMany(mappedBy = "members")
    private Set<GroupEntity> groups = new HashSet<>();
}
