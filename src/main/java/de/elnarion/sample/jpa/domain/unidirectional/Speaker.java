package de.elnarion.sample.jpa.domain.unidirectional;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "speaker_seq",sequenceName = "speaker_seq",allocationSize = 1)
public class Speaker {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "speaker_seq")
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
