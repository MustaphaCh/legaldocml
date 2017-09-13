package io.legaldocml.xpath.cerebro;

import io.legaldocml.akn.AkomaNtoso;
import io.legaldocml.akn.element.Debate;
import io.legaldocml.akn.element.Meta;
import org.junit.Test;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

public class CerebroTest {

    @Test
    public void test() {


       /* JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();

        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
        StandardJavaFileManager manager = compiler.getStandardFileManager(
                diagnostics, null, StandardCharsets.UTF_8);

        JavaSourceFromString jsfs = new JavaSourceFromString( "CustomProcessor", "test");
        Iterable<? extends JavaFileObject> fileObjects = Arrays.asList( jsfs);

        StringWriter output = new StringWriter();
        JavaCompiler.CompilationTask task = compiler.getTask( output, manager, diagnostics,
        null, null, fileObjects );

        System.out.println(task.call());

        System.out.println("--->" + output.toString());*/

        CerebroBuilder builder  = new CerebroBuilder();
        builder.append(Cerebro.getCodeLink(AkomaNtoso.class, Debate.class));
        builder.append(Cerebro.getCodeLink(Debate.class, Meta.class));

        System.out.println(builder.build());


    }

    public class JavaSourceFromString extends SimpleJavaFileObject {
        final String code;

        public JavaSourceFromString( String name, String code) {
            super( URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension),Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
    }
}
