package codigorapido.coshopcore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(force = true)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "requests")
public class RequestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_item")
    private ItemEntity item;

    @ManyToOne(optional = false)
    @JoinColumn(name = "fk_member")
    private MemberEntity member;

    @Column(nullable = false)
    private Integer quantity;

    @Column(name = "for_everyone", nullable = false)
    @Builder.Default
    private boolean forEveryone = false;
}
