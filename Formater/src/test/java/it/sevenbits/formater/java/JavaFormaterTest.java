package it.sevenbits.formater.java;

import it.sevenbits.formater.io.input.stringReader.StringReader;
import it.sevenbits.formater.io.output.stringWriter.StringWriter;
import it.sevenbits.formater.java.lexer.Factory.LexerFactory;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class JavaFormaterTest {
        private JavaFormater javaFormater;

        @Before
        public void setUp() {
            this.javaFormater = new JavaFormater(new LexerFactory());
        }

        @Test
        public void testJavaFormaterSimpleSituation1() throws IOException {
            StringWriter writer = new StringWriter();
            StringReader reader = new StringReader("{{{}}}");
            javaFormater.format(reader, writer);
            assertEquals("Wrong result!",
                    "{\n    {\n        {\n        }\n    }\n}" ,writer.toString());
        }
        @Test
        public void testJavaFormaterRepeatFormat() throws IOException {
            StringWriter writer = new StringWriter();
            StringReader reader = new StringReader("{\n    {\n        {\n        }\n    }\n}");
            javaFormater.format(reader, writer);
            assertEquals("Wrong result!",
                    "{\n    {\n        {\n        }\n    }\n}" ,writer.toString());
        }

        @Test
        public void testJavaFormaterEmptyString() throws IOException {
            StringWriter writer = new StringWriter();
            StringReader reader = new StringReader("");
            javaFormater.format(reader, writer);
            assertEquals("Wrong result!",
                "" ,writer.toString());
        }

        @Test
        public void testJavaFormaterValidCodeExample() throws IOException{
            StringWriter writer = new StringWriter();
            StringReader reader = new StringReader("package it.sevenbits.formater;import java.util.Arrays;\n" +
                    "import java.util.regex.Pattern;\n" +
                    "public class Formater {\n" +
                    "      private String regularExpression;\n" +
                    "      \n" +
                    "      \n" +
                    "      \n" +
                    "        private Formater() {\n" +
                    "            }\n" +
                    "    public Formater(String newRegularExpression) {regularExpression = newRegularExpression;}\n" +
                    "    public String FormatLine(String line) {\n" +
                    "        StringBuilder sb = new StringBuilder();      return sb.toString();\n" +
                    "}\n" +
                    "}");
            javaFormater.format(reader, writer);
            assertEquals("Wrong result!",
                    "package it.sevenbits.formater;\n" +
                            "import java.util.Arrays;\n" +
                            "import java.util.regex.Pattern;\n" +
                            "public class Formater {\n" +
                            "    private String regularExpression;\n" +
                            "\n" +
                            "    private Formater() {\n" +
                            "    }\n" +
                            "    public Formater(String newRegularExpression) {\n" +
                            "        regularExpression = newRegularExpression;\n" +
                            "    }\n" +
                            "    public String FormatLine(String line) {\n" +
                            "        StringBuilder sb = new StringBuilder();\n" +
                            "        return sb.toString();\n" +
                            "    }\n" +
                            "}" ,writer.toString());
        }
}
