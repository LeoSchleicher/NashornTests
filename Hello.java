
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.*;
import java.io.FileReader;
import jdk.nashorn.api.scripting.ScriptObjectMirror;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.charset.Charset;

public class Hello {
	
	public static void h1() throws Throwable {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("nashorn");
		engine.eval("function sum(a, b) { return a + b; }");
		System.out.println(engine.eval("sum(1, 52);"));
	}
	
	public static void uglify() throws Throwable {
		ScriptEngineManager engineManager = new ScriptEngineManager();
		ScriptEngine engine = engineManager.getEngineByName("nashorn");
		
		engine.eval(new FileReader("u.js")); // uglifyjs --self > u.js
		engine.eval(new FileReader("uhelper.js"));
		
		// engine.eval("function func(code) { return UglifyJS.parse(code).print_to_string() }");
		Invocable invocable = (Invocable) engine;
		//String res = (String)invocable.invokeFunction("func", "alert('nanana');");
		
		// ScriptObjectMirror uglify = (ScriptObjectMirror)engine.eval("UglifyJS");
		// String ugly = (String)uglify.callMember("parse", "alert('nanana');");
		String script = readFile("long.js");
		String parsed = "";
		
		for(int i = 0; i < 20; i++) {
			long start = System.nanoTime();
			parsed = (String)invocable.invokeFunction("func", script);
			long end = System.nanoTime();
			long microseconds = (end - start) / 1000 / 1000; // milliseconds actual
			System.out.println(microseconds);
		}
		
		System.out.println("before / after: " + script.length()+" / " + parsed.length());
		System.out.println("savings: "+( (parsed.length()+0.001) / (script.length()+0.001) * 100) + "%");
		System.out.println(parsed);
	}
	
	public static String readFile(String fileName) throws Throwable {
		return new String (Files.readAllBytes(Paths.get(fileName)),Charset.forName("UTF-8"));
	}

	public static void main(String... args) throws Throwable {
		uglify();
	}
}