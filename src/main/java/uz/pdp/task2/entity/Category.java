package uz.pdp.task2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer numberOfStars;

    @Column
    private boolean isSuccess;

    @ManyToMany
    private List<Language> languages;

    public Category(String name, Integer numberOfStars, boolean isSuccess, List<Language> languages) {
        this.name = name;
        this.numberOfStars = numberOfStars;
        this.isSuccess = isSuccess;
        this.languages = languages;
    }
}
