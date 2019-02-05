package it.sevenbits.formater.javaFormater.stateMachine.lexer;

import it.sevenbits.formater.javaFormater.lexer.token.symbolGroups.SymbolGroups;
import it.sevenbits.formater.javaFormater.stateMachine.Pair;
import it.sevenbits.formater.javaFormater.stateMachine.State;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

class LexerStateMap {
    private final State startState = new State("START");
    private final State endState = new State("END");
    private final State errorState = new State("ERROR");
    private final Map<Pair<State, Integer>, State> states;

    /**
     * Constructor
     */

    LexerStateMap() {
        states = new HashMap<>();
        State lineCommentaryState = new State("ONE_LINE_COMMENTARY");
        State stringLiteralState = new State("STRING_LITERAL");
        State charLiteralStartState = new State("CHAR_LITERAL_START");
        State charLiteralEndState = new State("CHAR_LITERAL_END");
        State spaceState = new State("SPACE");
        State literalState = new State("LITERAL");
        State operationSymbolState = new State("OPERATION_SYMBOL");
        State numberState = new State("NUMBER");
        State commentSuspicionState = new State("COMMENT_SUSPICION");

        states.put(new Pair<>(startState, SymbolGroups.DIGIT), numberState);
        states.put(new Pair<>(startState, SymbolGroups.SINGLE_QUOTATION), charLiteralStartState);
        states.put(new Pair<>(startState, SymbolGroups.DOUBLE_QUOTATION), stringLiteralState);
        states.put(new Pair<>(startState, SymbolGroups.OTHER_SYMBOLS), endState);
        states.put(new Pair<>(startState, SymbolGroups.ENDLINE), endState);
        states.put(new Pair<>(startState, SymbolGroups.LETTER_LOWERCASE), literalState);
        states.put(new Pair<>(startState, SymbolGroups.LETTER_UPPERCASE), literalState);
        states.put(new Pair<>(startState, SymbolGroups.SLASH), commentSuspicionState);
        states.put(new Pair<>(startState, SymbolGroups.SPACE), spaceState);
        states.put(new Pair<>(startState, SymbolGroups.STAR), endState);
        states.put(new Pair<>(startState, SymbolGroups.DOT), endState);
        states.put(new Pair<>(startState, SymbolGroups.SEMICOLON), endState);
        states.put(new Pair<>(startState, SymbolGroups.LEFT_BRACE), endState);
        states.put(new Pair<>(startState, SymbolGroups.RIGHT_BRACE), endState);


        states.put(new Pair<>(lineCommentaryState, SymbolGroups.DIGIT), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.SINGLE_QUOTATION), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.DOUBLE_QUOTATION), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.OTHER_SYMBOLS), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.LETTER_LOWERCASE), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.LETTER_UPPERCASE), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.SLASH), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.SPACE), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.STAR), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.DOT), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.SEMICOLON), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.LEFT_BRACE), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.RIGHT_BRACE), lineCommentaryState);

        states.put(new Pair<>(stringLiteralState, SymbolGroups.DIGIT), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.SINGLE_QUOTATION), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.DOUBLE_QUOTATION), endState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.OTHER_SYMBOLS), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.LETTER_LOWERCASE), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.LETTER_UPPERCASE), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.SLASH), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.SPACE), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.STAR), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.DOT), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.SEMICOLON), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.LEFT_BRACE), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.RIGHT_BRACE), stringLiteralState);

        states.put(new Pair<>(charLiteralStartState, SymbolGroups.DIGIT), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.DOUBLE_QUOTATION), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.OTHER_SYMBOLS), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.LETTER_LOWERCASE), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.LETTER_UPPERCASE), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.SLASH), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.SPACE), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.STAR), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.DOT), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.SEMICOLON), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.LEFT_BRACE), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.RIGHT_BRACE), charLiteralEndState);

        states.put(new Pair<>(literalState, SymbolGroups.DIGIT), literalState);
        states.put(new Pair<>(literalState, SymbolGroups.LETTER_LOWERCASE), literalState);
        states.put(new Pair<>(literalState, SymbolGroups.LETTER_UPPERCASE), literalState);
        states.put(new Pair<>(literalState, SymbolGroups.DOT), literalState);

        states.put(new Pair<>(spaceState, SymbolGroups.SPACE), spaceState);

        states.put(new Pair<>(numberState, SymbolGroups.DIGIT), numberState);

        states.put(new Pair<>(commentSuspicionState, SymbolGroups.SLASH), lineCommentaryState);
        states.put(new Pair<>(commentSuspicionState, SymbolGroups.STAR), lineCommentaryState);
    }

    State getStartState() {
        return startState;
    }

    State getEndState() {
        return endState;
    }

    State getErrorState() {
        return errorState;
    }

    /**
     * Get new state based on current state and signal
     *
     * @param state  current state
     * @param signal current signal
     * @return next State or errorState if can't find state in map
     */

    State getNextState(final State state, final int signal) {
        return states.getOrDefault(new Pair<>(state, signal), errorState);
    }
}
