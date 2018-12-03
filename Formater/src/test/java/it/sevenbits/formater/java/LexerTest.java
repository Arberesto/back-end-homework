package it.sevenbits.formater.java;

import it.sevenbits.formater.io.input.IReader;
import it.sevenbits.formater.java.lexer.Factory.ILexerFactory;
import it.sevenbits.formater.java.lexer.Factory.LexerFactory;
import it.sevenbits.formater.java.lexer.ILexer;
import it.sevenbits.formater.java.token.IToken;
import it.sevenbits.formater.java.token.Token;
import org.junit.Before;
import org.junit.Test;

import java.io.EOFException;
import java.io.IOException;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LexerTest {
    private ILexerFactory lexerFactory;

    @Before
    public void setUp() {
        lexerFactory = new LexerFactory();
    }

    @Test
    public void testLexerAllTokens() throws IOException {
        IReader reader = mock(IReader.class);
        when(reader.read()).thenReturn((int)'{',(int)'\t',(int)'\n',(int)' ',(int)'r',(int)';',(int)'}');
        when(reader.hasNext()).thenReturn(true, true, true, true, true, true, true, true);
        ILexer lexer = lexerFactory.createLexer(reader);
        IToken token = lexer.getNextToken();
        assertEquals(token, new Token("TOKEN_LEFTBRACE", "{"));
        token = lexer.getNextToken();
        assertEquals(token, new Token("TOKEN_TABULATION", "\t"));
        token = lexer.getNextToken();
        assertEquals(token, new Token("TOKEN_NEWLINE", "\n"));
        token = lexer.getNextToken();
        assertEquals(token, new Token("TOKEN_SPACE", " "));
        token = lexer.getNextToken();
        assertEquals(token, new Token("TOKEN_NON-DIVIDER", "r"));
        token = lexer.getNextToken();
        assertEquals(token, new Token("TOKEN_SEMICOLON", ";"));
        token = lexer.getNextToken();
        assertEquals(token, new Token("TOKEN_RIGHTBRACE", "}"));
    }

    @Test
    public void testLexerEmptyReader() throws IOException {
        IReader reader = mock(IReader.class);
        when(reader.hasNext()).thenReturn(false);
        when(reader.read()).thenThrow(new EOFException("EOF reached"));
        try {
            lexerFactory.createLexer(reader);
        } catch (IOException e) {
            assertEquals(e.getMessage(),"lexer init IO error");
        }
    }

    @Test
    public void testLexerIfNextTokenNull() throws IOException {
        IReader reader = mock(IReader.class);
        when(reader.read()).thenReturn((int)'r',(int) ';');
        when(reader.hasNext()).thenReturn(true,true, false);
        ILexer lexer = lexerFactory.createLexer(reader);
        IToken token = lexer.getNextToken();
        assertEquals(token,new Token("TOKEN_NON-DIVIDER","r"));
        token = lexer.getNextToken();
        assertEquals(token,new Token("TOKEN_SEMICOLON",";"));
        token = lexer.getNextToken();
        assertEquals(token,new Token());
    }
}
