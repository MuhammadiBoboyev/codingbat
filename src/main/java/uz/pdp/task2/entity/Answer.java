package uz.pdp.task2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String answer;

    @Column
    private boolean isCorrect;

    @ManyToOne
    private User user;

    @ManyToOne
    private Task task;

    public Answer(String answer, boolean isCorrect, User user, Task task) {
        this.answer = answer;
        this.isCorrect = isCorrect;
        this.user = user;
        this.task = task;
    }
}
