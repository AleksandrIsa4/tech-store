package org.example.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.example.exception.BadRequestException;

@AllArgsConstructor
@Getter
@ToString
public enum PCForm {

    DESKTOP("Десктоп"),
    NETTOP("Неттоп"),
    MONOBLOCK("Моноблок");

    private final String type;

    public static String from(String param) {
        for (PCForm value : PCForm.values()) {
            if (value.getType().equals(param)) {
                return value.getType();
            }
        }
        throw new BadRequestException("Заданный параметр не корректен");
    }
}
