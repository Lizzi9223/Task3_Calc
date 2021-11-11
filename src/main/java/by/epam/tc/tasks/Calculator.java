package by.epam.tc.tasks;

import java.util.ArrayList;
import java.util.List;

public class Calculator {

    private Calculator(){}

    public static int calculate(String expression){
        List<Lexeme> lexemes = LexemeList.getLexemes(expression);
        LexemeList lexemeList = new LexemeList(lexemes);
        return count(lexemeList);
    }

    private static int count(LexemeList lexemes) {
        Lexeme lexeme = lexemes.getNext();
        if (lexeme.getType() == LexemeType.EOF) {
            return 0;
        } else {
            lexemes.goBack();
            return plusMinus(lexemes);
        }
    }

    private static int plusMinus(LexemeList lexemes) {
        int value = multDiv(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.getNext();
            switch (lexeme.getType()) {
                case OP_PLUS:
                    value += multDiv(lexemes);
                    break;
                case OP_MINUS:
                    value -= multDiv(lexemes);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                    lexemes.goBack();
                    return value;
                default:
                    throw new RuntimeException();
            }
        }
    }

    private static int multDiv(LexemeList lexemes) {
        int value = number(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.getNext();
            switch (lexeme.getType()) {
                case OP_MULT:
                    value *= number(lexemes);
                    break;
                case OP_DIV:
                    value /= number(lexemes);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                case OP_PLUS:
                case OP_MINUS:
                    lexemes.goBack();
                    return value;
                default:
                    throw new RuntimeException();
            }
        }
    }

    private static int number(LexemeList lexemes) {
        Lexeme lexeme = lexemes.getNext();
        switch (lexeme.getType()) {
            case NUMBER:
                return Integer.parseInt(lexeme.getValue());
            case LEFT_BRACKET:
                int value = plusMinus(lexemes);
                lexeme = lexemes.getNext();
                if (lexeme.getType() != LexemeType.RIGHT_BRACKET) {
                    throw new RuntimeException();
                }
                return value;
            default:
                throw new RuntimeException();
        }
    }
}
