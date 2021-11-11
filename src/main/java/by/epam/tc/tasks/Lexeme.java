package by.epam.tc.tasks;

import java.util.Objects;

class Lexeme {

    private LexemeType type;
    private String value;

    Lexeme(LexemeType type, char value) {
        this.type = type;
        this.value = Character.toString(value);
    }

    Lexeme(LexemeType type, String value) {
        this.type = type;
        this.value = value;
    }

    LexemeType getType() {
        return type;
    }

    String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lexeme lexeme = (Lexeme) o;
        return value == lexeme.value && type == lexeme.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String toString() {
        return "Sign{" +
                "type=" + type +
                ", value=" + value +
                '}';
    }
}
