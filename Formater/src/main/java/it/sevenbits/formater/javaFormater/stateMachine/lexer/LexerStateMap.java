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

        states.put(new Pair<>(startState, SymbolGroups.digit), numberState);
        states.put(new Pair<>(startState, SymbolGroups.singleQuotation), charLiteralStartState);
        states.put(new Pair<>(startState, SymbolGroups.doubleQuotation), stringLiteralState);
        states.put(new Pair<>(startState, SymbolGroups.otherSymbols), endState);
        states.put(new Pair<>(startState, SymbolGroups.endline), endState);
        states.put(new Pair<>(startState, SymbolGroups.letterLowercase), literalState);
        states.put(new Pair<>(startState, SymbolGroups.letterUppercase), literalState);
        states.put(new Pair<>(startState, SymbolGroups.slash), commentSuspicionState);
        states.put(new Pair<>(startState, SymbolGroups.space), spaceState);
        states.put(new Pair<>(startState, SymbolGroups.star), endState);
        states.put(new Pair<>(startState, SymbolGroups.dot), endState);

        states.put(new Pair<>(lineCommentaryState, SymbolGroups.digit), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.singleQuotation), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.doubleQuotation), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.otherSymbols), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.endline), errorState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.letterLowercase), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.letterUppercase), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.slash), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.space), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.star), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, SymbolGroups.dot), lineCommentaryState);

        states.put(new Pair<>(stringLiteralState, SymbolGroups.digit), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.singleQuotation), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.doubleQuotation), endState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.otherSymbols), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.endline), errorState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.letterLowercase), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.letterUppercase), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.slash), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.space), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.star), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, SymbolGroups.dot), stringLiteralState);

        states.put(new Pair<>(charLiteralStartState, SymbolGroups.digit), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.singleQuotation), errorState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.doubleQuotation), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.otherSymbols), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.endline), errorState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.letterLowercase), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.letterUppercase), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.slash), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.space), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.star), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, SymbolGroups.dot), charLiteralEndState);

        states.put(new Pair<>(charLiteralEndState, SymbolGroups.digit), errorState);
        states.put(new Pair<>(charLiteralEndState, SymbolGroups.singleQuotation), endState);
        states.put(new Pair<>(charLiteralEndState, SymbolGroups.doubleQuotation), errorState);
        states.put(new Pair<>(charLiteralEndState, SymbolGroups.otherSymbols), errorState);
        states.put(new Pair<>(charLiteralEndState, SymbolGroups.endline), errorState);
        states.put(new Pair<>(charLiteralEndState, SymbolGroups.letterLowercase), errorState);
        states.put(new Pair<>(charLiteralEndState, SymbolGroups.letterUppercase), errorState);
        states.put(new Pair<>(charLiteralEndState, SymbolGroups.slash), errorState);
        states.put(new Pair<>(charLiteralEndState, SymbolGroups.space), errorState);
        states.put(new Pair<>(charLiteralEndState, SymbolGroups.star), errorState);
        states.put(new Pair<>(charLiteralEndState, SymbolGroups.dot), errorState);

        states.put(new Pair<>(spaceState, SymbolGroups.digit), errorState);
        states.put(new Pair<>(spaceState, SymbolGroups.singleQuotation), errorState);
        states.put(new Pair<>(spaceState, SymbolGroups.doubleQuotation), errorState);
        states.put(new Pair<>(spaceState, SymbolGroups.otherSymbols), errorState);
        states.put(new Pair<>(spaceState, SymbolGroups.endline), errorState);
        states.put(new Pair<>(spaceState, SymbolGroups.letterLowercase), errorState);
        states.put(new Pair<>(spaceState, SymbolGroups.letterUppercase), errorState);
        states.put(new Pair<>(spaceState, SymbolGroups.slash), errorState);
        states.put(new Pair<>(spaceState, SymbolGroups.space), spaceState);
        states.put(new Pair<>(spaceState, SymbolGroups.star), errorState);
        states.put(new Pair<>(spaceState, SymbolGroups.dot), errorState);

        states.put(new Pair<>(literalState, SymbolGroups.digit), literalState);
        states.put(new Pair<>(literalState, SymbolGroups.singleQuotation), errorState);
        states.put(new Pair<>(literalState, SymbolGroups.doubleQuotation), errorState);
        states.put(new Pair<>(literalState, SymbolGroups.otherSymbols), errorState);
        states.put(new Pair<>(literalState, SymbolGroups.endline), errorState);
        states.put(new Pair<>(literalState, SymbolGroups.letterLowercase), literalState);
        states.put(new Pair<>(literalState, SymbolGroups.letterUppercase), literalState);
        states.put(new Pair<>(literalState, SymbolGroups.slash), errorState);
        states.put(new Pair<>(literalState, SymbolGroups.space), errorState);
        states.put(new Pair<>(literalState, SymbolGroups.star), errorState);
        states.put(new Pair<>(literalState, SymbolGroups.dot), literalState);

        states.put(new Pair<>(operationSymbolState, SymbolGroups.digit), errorState);
        states.put(new Pair<>(operationSymbolState, SymbolGroups.singleQuotation), errorState);
        states.put(new Pair<>(operationSymbolState, SymbolGroups.doubleQuotation), errorState);
        states.put(new Pair<>(operationSymbolState, SymbolGroups.otherSymbols), errorState);
        states.put(new Pair<>(operationSymbolState, SymbolGroups.endline), errorState);
        states.put(new Pair<>(operationSymbolState, SymbolGroups.letterLowercase), errorState);
        states.put(new Pair<>(operationSymbolState, SymbolGroups.letterUppercase), errorState);
        states.put(new Pair<>(operationSymbolState, SymbolGroups.slash), errorState);
        states.put(new Pair<>(operationSymbolState, SymbolGroups.space), errorState);
        states.put(new Pair<>(operationSymbolState, SymbolGroups.star), errorState);
        states.put(new Pair<>(operationSymbolState, SymbolGroups.dot), errorState);

        states.put(new Pair<>(numberState, SymbolGroups.digit), numberState);
        states.put(new Pair<>(numberState, SymbolGroups.singleQuotation), errorState);
        states.put(new Pair<>(numberState, SymbolGroups.doubleQuotation), errorState);
        states.put(new Pair<>(numberState, SymbolGroups.otherSymbols), errorState);
        states.put(new Pair<>(numberState, SymbolGroups.endline), errorState);
        states.put(new Pair<>(numberState, SymbolGroups.letterLowercase), errorState);
        states.put(new Pair<>(numberState, SymbolGroups.letterUppercase), errorState);
        states.put(new Pair<>(numberState, SymbolGroups.slash), errorState);
        states.put(new Pair<>(numberState, SymbolGroups.space), errorState);
        states.put(new Pair<>(numberState, SymbolGroups.star), errorState);
        states.put(new Pair<>(numberState, SymbolGroups.dot), errorState);

        states.put(new Pair<>(commentSuspicionState, SymbolGroups.digit), errorState);
        states.put(new Pair<>(commentSuspicionState, SymbolGroups.singleQuotation), errorState);
        states.put(new Pair<>(commentSuspicionState, SymbolGroups.doubleQuotation), errorState);
        states.put(new Pair<>(commentSuspicionState, SymbolGroups.otherSymbols), errorState);
        states.put(new Pair<>(commentSuspicionState, SymbolGroups.endline), errorState);
        states.put(new Pair<>(commentSuspicionState, SymbolGroups.letterLowercase), errorState);
        states.put(new Pair<>(commentSuspicionState, SymbolGroups.letterUppercase), errorState);
        states.put(new Pair<>(commentSuspicionState, SymbolGroups.slash), lineCommentaryState);
        states.put(new Pair<>(commentSuspicionState, SymbolGroups.space), errorState);
        states.put(new Pair<>(commentSuspicionState, SymbolGroups.star), lineCommentaryState);
        states.put(new Pair<>(commentSuspicionState, SymbolGroups.dot), errorState);
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
