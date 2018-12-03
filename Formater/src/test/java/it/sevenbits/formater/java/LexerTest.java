package it.sevenbits.formater.java;

import it.sevenbits.formater.io.input.IReader;
import it.sevenbits.formater.java.lexer.Factory.ILexerFactory;
import it.sevenbits.formater.java.lexer.Factory.LexerFactory;
import it.sevenbits.formater.java.lexer.ILexer;
import it.sevenbits.formater.java.token.IToken;
import it.sevenbits.formater.java.token.Token;
import org.junit.Before;
import org.junit.Test;

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
        when(reader.read()).thenReturn((int)'{',(int)'}');
        ILexer lexer = lexerFactory.createLexer(reader);
        IToken token = lexer.getNextToken();
        assertEquals(token, new Token("TOKEN_LEFTBRACE","{"));
        IToken token = lexer.getNextToken();
        assertEquals(token, new Token("TOKEN_RIGHTBRACE","}"));
    }

    @Test
    public void testLexerEmptyReader() throws IOException {
        IReader reader = mock(IReader.class);
        when(reader.read()).thenThrow(new IOException("EOF reached"));
        ILexer lexer = lexerFactory.createLexer(reader);
        IToken token = lexer.getNextToken();
        assertEquals(token, new Token());
    }
}
