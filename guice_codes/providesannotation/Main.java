package providesannotation;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provides;
import com.google.inject.name.Named;

//creating the interface
interface SpellCheck {
	public void checkSpelling ();
}

//creating the class 
class SpellCheckImpl implements SpellCheck {
	private String dbUrl;
	private String user;
	private int timeout;
	
	public SpellCheckImpl (String dbUrl, String user, int timeout){
		this.dbUrl = dbUrl;
		this.user = user;
		this.timeout = timeout;
	}
	
	@Override
	public void checkSpelling () {
		System.out.printf ("Url :- %s\n", dbUrl);
		System.out.printf ("user :- %s\n", user);
		System.out.printf("timeout :- %d\n", timeout);
	}
}

//creating the binding module
class TextEditorModule extends AbstractModule{
	private SpellCheck spellchecker;
	
	@Override
	public void configure () { }
	@Provides 
	public SpellCheck provideSpellChecker () {
		String url = "demo url";
		String user = "demo user";
		int timeout = 123;
		SpellCheckImpl sp = new SpellCheckImpl (url, user, timeout);
		return sp;
	}
}


class TextEditor {
	private SpellCheck spellchecker;
	
	@Inject
	public TextEditor (SpellCheck spellchecker) {
		this.spellchecker = spellchecker;
	}
	
	public void makeSpellCheck () {
		spellchecker.checkSpelling();
	}
}
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Injector injector  = Guice.createInjector(new TextEditorModule());
		TextEditor editor = injector.getInstance (TextEditor.class);
		editor.makeSpellCheck();
	}

}
