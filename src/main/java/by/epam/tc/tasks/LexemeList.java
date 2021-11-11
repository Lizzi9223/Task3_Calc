package by.epam.tc.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class LexemeList {
    private List<Lexeme> lexemes;
    private int index = 0;

    LexemeList(List<Lexeme> expression) {
        this.lexemes = expression;
    }

    List<Lexeme> getLexemesList() {
        return lexemes;
    }

    int getIndex() {
        return index;
    }

    Lexeme getNext(){
        return lexemes.get(index++);
    }

    void goBack(){
        if(index>0)
            index--;
    }

    static List<Lexeme> getLexemes(String expression) {
        List<Lexeme> lexemes = new ArrayList<>();
        int pos = 0;
        while (pos < expression.length()) {
            char c = expression.charAt(pos);
            switch (c) {
                case '(':
                    lexemes.add(new Lexeme(LexemeType.LEFT_BRACKET, c));
                    pos++;
                    break;
                case ')':
                    lexemes.add(new Lexeme(LexemeType.RIGHT_BRACKET, c));
                    pos++;
                    break;
                case '+':
                    lexemes.add(new Lexeme(LexemeType.OP_PLUS, c));
                    pos++;
                    break;
                case '-':
                    lexemes.add(new Lexeme(LexemeType.OP_MINUS, c));
                    pos++;
                    break;
                case '*':
                    lexemes.add(new Lexeme(LexemeType.OP_MULT, c));
                    pos++;
                    break;
                case '/':
                    lexemes.add(new Lexeme(LexemeType.OP_DIV, c));
                    pos++;
                    break;
                default:
                    if (c >= '0' && c <= '9') {
                        StringBuilder sb = new StringBuilder();
                        do {
                            sb.append(c);
                            pos++;
                            if (pos >= expression.length()) {
                                break;
                            }
                            c = expression.charAt(pos);
                        } while (c >= '0' && c <= '9');
                        lexemes.add(new Lexeme(LexemeType.NUMBER, sb.toString()));
                    } else {
                        if (c != ' ') {
                            throw new RuntimeException();
                        }
                        pos++;
                    }
            }
        }
        lexemes.add(new Lexeme(LexemeType.EOF, ""));
        return lexemes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LexemeList that = (LexemeList) o;
        return Objects.equals(lexemes, that.lexemes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lexemes);
    }

    @Override
    public String toString() {
        return "LexemeList{" +
                "lexemes=" + lexemes +
                ", index=" + index +
                '}';
    }
}
