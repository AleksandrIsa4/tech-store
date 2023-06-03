package org.example.model.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.example.exception.BadRequestException;

@AllArgsConstructor
@Getter
@ToString
public enum LaptopDiagonal {

    DIAGONAL1("13"),
    DIAGONAL2("14"),
    DIAGONAL3("15"),
    DIAGONAL4("17");

    private final String type;

    public static String from(String param) {
        for (LaptopDiagonal value : LaptopDiagonal.values()) {
            if (value.getType().equals(param)) {
                return value.getType();
            }
        }
        throw new BadRequestException("Заданный параметр не корректен");
    }
}
