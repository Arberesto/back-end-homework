package it.sevenbits.formater.javaFormater.stateMachine.lexer;

import it.sevenbits.formater.javaFormater.lexer.command.commandFactory.LexerCommandFactory;
import it.sevenbits.formater.javaFormater.stateMachine.Pair;
import it.sevenbits.formater.javaFormater.stateMachine.State;

import java.util.HashMap;
import java.util.Map;

public class LexerStateMap {
    private final State startState = new State("START");
    private final State endState = new State("END");
    private final State errorState = new State("ERROR");
    private final Map<Pair<State, Integer>, State> states;

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

        states.put(new Pair<>(startState, LexerCommandFactory.digit), numberState);
        states.put(new Pair<>(startState, LexerCommandFactory.singleQuotation), charLiteralStartState);
        states.put(new Pair<>(startState, LexerCommandFactory.doubleQuotation), stringLiteralState);
        states.put(new Pair<>(startState, LexerCommandFactory.otherSymbols), endState);
        states.put(new Pair<>(startState, LexerCommandFactory.endline), endState);
        states.put(new Pair<>(startState, LexerCommandFactory.letterLowercase), literalState);
        states.put(new Pair<>(startState, LexerCommandFactory.letterUppercase), literalState);
        states.put(new Pair<>(startState, LexerCommandFactory.slash), commentSuspicionState);
        states.put(new Pair<>(startState, LexerCommandFactory.space), spaceState);
        states.put(new Pair<>(startState, LexerCommandFactory.star), endState);

        states.put(new Pair<>(lineCommentaryState, LexerCommandFactory.digit), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, LexerCommandFactory.singleQuotation), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, LexerCommandFactory.doubleQuotation), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, LexerCommandFactory.otherSymbols), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, LexerCommandFactory.endline), errorState);
        states.put(new Pair<>(lineCommentaryState, LexerCommandFactory.letterLowercase), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, LexerCommandFactory.letterUppercase), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, LexerCommandFactory.slash), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, LexerCommandFactory.space), lineCommentaryState);
        states.put(new Pair<>(lineCommentaryState, LexerCommandFactory.star), lineCommentaryState);

        states.put(new Pair<>(stringLiteralState, LexerCommandFactory.digit), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, LexerCommandFactory.singleQuotation), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, LexerCommandFactory.doubleQuotation), endState);
        states.put(new Pair<>(stringLiteralState, LexerCommandFactory.otherSymbols), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, LexerCommandFactory.endline), errorState);
        states.put(new Pair<>(stringLiteralState, LexerCommandFactory.letterLowercase), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, LexerCommandFactory.letterUppercase), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, LexerCommandFactory.slash), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, LexerCommandFactory.space), stringLiteralState);
        states.put(new Pair<>(stringLiteralState, LexerCommandFactory.star), stringLiteralState);

        states.put(new Pair<>(charLiteralStartState, LexerCommandFactory.digit), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, LexerCommandFactory.singleQuotation), errorState);
        states.put(new Pair<>(charLiteralStartState, LexerCommandFactory.doubleQuotation), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, LexerCommandFactory.otherSymbols), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, LexerCommandFactory.endline), errorState);
        states.put(new Pair<>(charLiteralStartState, LexerCommandFactory.letterLowercase), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, LexerCommandFactory.letterUppercase), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, LexerCommandFactory.slash), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, LexerCommandFactory.space), charLiteralEndState);
        states.put(new Pair<>(charLiteralStartState, LexerCommandFactory.star), charLiteralEndState);

        states.put(new Pair<>(charLiteralEndState, LexerCommandFactory.digit), errorState);
        states.put(new Pair<>(charLiteralEndState, LexerCommandFactory.singleQuotation), endState);
        states.put(new Pair<>(charLiteralEndState, LexerCommandFactory.doubleQuotation), errorState);
        states.put(new Pair<>(charLiteralEndState, LexerCommandFactory.otherSymbols), errorState);
        states.put(new Pair<>(charLiteralEndState, LexerCommandFactory.endline), errorState);
        states.put(new Pair<>(charLiteralEndState, LexerCommandFactory.letterLowercase), errorState);
        states.put(new Pair<>(charLiteralEndState, LexerCommandFactory.letterUppercase), errorState);
        states.put(new Pair<>(charLiteralEndState, LexerCommandFactory.slash), errorState);
        states.put(new Pair<>(charLiteralEndState, LexerCommandFactory.space), errorState);
        states.put(new Pair<>(charLiteralEndState, LexerCommandFactory.star), errorState);

        states.put(new Pair<>(spaceState, LexerCommandFactory.digit), errorState);
        states.put(new Pair<>(spaceState, LexerCommandFactory.singleQuotation), errorState);
        states.put(new Pair<>(spaceState, LexerCommandFactory.doubleQuotation), errorState);
        states.put(new Pair<>(spaceState, LexerCommandFactory.otherSymbols), errorState);
        states.put(new Pair<>(spaceState, LexerCommandFactory.endline), errorState);
        states.put(new Pair<>(spaceState, LexerCommandFactory.letterLowercase), errorState);
        states.put(new Pair<>(spaceState, LexerCommandFactory.letterUppercase), errorState);
        states.put(new Pair<>(spaceState, LexerCommandFactory.slash), errorState);
        states.put(new Pair<>(spaceState, LexerCommandFactory.space), spaceState);
        states.put(new Pair<>(spaceState, LexerCommandFactory.star), errorState);

        states.put(new Pair<>(literalState, LexerCommandFactory.digit), literalState);
        states.put(new Pair<>(literalState, LexerCommandFactory.singleQuotation), errorState);
        states.put(new Pair<>(literalState, LexerCommandFactory.doubleQuotation), errorState);
        states.put(new Pair<>(literalState, LexerCommandFactory.otherSymbols), errorState);
        states.put(new Pair<>(literalState, LexerCommandFactory.endline), errorState);
        states.put(new Pair<>(literalState, LexerCommandFactory.letterLowercase), literalState);
        states.put(new Pair<>(literalState, LexerCommandFactory.letterUppercase), literalState);
        states.put(new Pair<>(literalState, LexerCommandFactory.slash), errorState);
        states.put(new Pair<>(literalState, LexerCommandFactory.space), errorState);
        states.put(new Pair<>(literalState, LexerCommandFactory.star), errorState);

        states.put(new Pair<>(operationSymbolState, LexerCommandFactory.digit), errorState);
        states.put(new Pair<>(operationSymbolState, LexerCommandFactory.singleQuotation), errorState);
        states.put(new Pair<>(operationSymbolState, LexerCommandFactory.doubleQuotation), errorState);
        states.put(new Pair<>(operationSymbolState, LexerCommandFactory.otherSymbols), errorState);
        states.put(new Pair<>(operationSymbolState, LexerCommandFactory.endline), errorState);
        states.put(new Pair<>(operationSymbolState, LexerCommandFactory.letterLowercase), errorState);
        states.put(new Pair<>(operationSymbolState, LexerCommandFactory.letterUppercase), errorState);
        states.put(new Pair<>(operationSymbolState, LexerCommandFactory.slash), errorState);
        states.put(new Pair<>(operationSymbolState, LexerCommandFactory.space), errorState);
        states.put(new Pair<>(operationSymbolState, LexerCommandFactory.star), errorState);

        states.put(new Pair<>(numberState, LexerCommandFactory.digit), numberState);
        states.put(new Pair<>(numberState, LexerCommandFactory.singleQuotation), errorState);
        states.put(new Pair<>(numberState, LexerCommandFactory.doubleQuotation), errorState);
        states.put(new Pair<>(numberState, LexerCommandFactory.otherSymbols), errorState);
        states.put(new Pair<>(numberState, LexerCommandFactory.endline), errorState);
        states.put(new Pair<>(numberState, LexerCommandFactory.letterLowercase), errorState);
        states.put(new Pair<>(numberState, LexerCommandFactory.letterUppercase), errorState);
        states.put(new Pair<>(numberState, LexerCommandFactory.slash), errorState);
        states.put(new Pair<>(numberState, LexerCommandFactory.space), errorState);
        states.put(new Pair<>(numberState, LexerCommandFactory.star), errorState);

        states.put(new Pair<>(commentSuspicionState, LexerCommandFactory.digit), errorState);
        states.put(new Pair<>(commentSuspicionState, LexerCommandFactory.singleQuotation), errorState);
        states.put(new Pair<>(commentSuspicionState, LexerCommandFactory.doubleQuotation), errorState);
        states.put(new Pair<>(commentSuspicionState, LexerCommandFactory.otherSymbols), errorState);
        states.put(new Pair<>(commentSuspicionState, LexerCommandFactory.endline), errorState);
        states.put(new Pair<>(commentSuspicionState, LexerCommandFactory.letterLowercase), errorState);
        states.put(new Pair<>(commentSuspicionState, LexerCommandFactory.letterUppercase), errorState);
        states.put(new Pair<>(commentSuspicionState, LexerCommandFactory.slash), lineCommentaryState);
        states.put(new Pair<>(commentSuspicionState, LexerCommandFactory.space), errorState);
        states.put(new Pair<>(commentSuspicionState, LexerCommandFactory.star), lineCommentaryState);
    }

    public State getStartState() {
        return startState;
    }

    public State getEndState() {
        return endState;
    }

    public State getErrorState() {return errorState;}

    public State getNextState(final State state, final int signal) {
        return states.getOrDefault(new Pair<>(state, signal), errorState);
    }
}
