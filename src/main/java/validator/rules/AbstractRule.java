package validator.rules;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@AllArgsConstructor
@Getter
@Setter

public abstract class AbstractRule {
    private String message;

    public abstract boolean examineQuery(ArrayList<String> query);
}