import com.google.javascript.jscomp.CompilationLevel;
import com.google.javascript.jscomp.Compiler;
import com.google.javascript.jscomp.CompilerOptions;
import com.google.javascript.jscomp.SourceFile;
import com.google.javascript.jscomp.Result;
import com.google.common.collect.ImmutableList;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;

public class Clo {
	public static void main(String... args) throws Throwable {
		System.out.println("Clo");
		Compiler compiler = new Compiler();
		CompilerOptions options = new CompilerOptions();
		CompilationLevel.SIMPLE_OPTIMIZATIONS.setOptionsForCompilationLevel(options);
		
		String script = readFile("long.js");
		
		Result res = compiler.compile(ImmutableList.of(SourceFile.fromCode("/dev/null", "")), ImmutableList.of(SourceFile.fromCode("main.js", script)), options);
		
		System.out.println(compiler.toSource());
		
	}
	
	public static String readFile(String fileName) throws Throwable {
		return new String (Files.readAllBytes(Paths.get(fileName)),Charset.forName("UTF-8"));
	}
}