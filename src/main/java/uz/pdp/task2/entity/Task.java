package uz.pdp.task2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String text;

    @Column(nullable = false)
    private boolean hasStar;

    @Column(nullable = false)
    private String solution;

    @Column(nullable = false)
    private String hint;

    @Column(nullable = false)
    private String method;

    @ManyToOne
    private Category category;

    public Task(String name, String text, boolean hasStar, String solution, String hint, String method, Category category) {
        this.name = name;
        this.text = text;
        this.hasStar = hasStar;
        this.solution = solution;
        this.hint = hint;
        this.method = method;
        this.category = category;
    }
}
