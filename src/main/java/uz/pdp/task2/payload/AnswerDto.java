package uz.pdp.task2.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class AnswerDto {
    @NotNull
    private String answer;

    @NotNull
    private boolean isCorrect;

    @NotNull
    private int userId;

    @NotNull
    private int taskId;
}
