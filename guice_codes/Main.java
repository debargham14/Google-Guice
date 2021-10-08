import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.name.Named;
import com.google.inject.name.Names;

//creating the interface
interface SpellCheck {
	public void checkSpelling ();
}

//creating the concrete class
class SpellCheckImpl implements SpellCheck{
	
	@Override
	public void checkSpelling () {
		System.out.println ("We are inside the concrete check spelling class !");
	}
}

//creating another implementaion of the interface
class SpellCheckModified implements SpellCheck{
	
	@Override
	public void checkSpelling () {
		System.out.println ("We are inside the modified version");
	}
}

//creating the binding module
class TextEditorModule extends AbstractModule {
	@Override
	public void configure () {
		bind (SpellCheck.class).annotatedWith (Names.named ("spellcheck1")).to (SpellCheckImpl.class);
		bind (SpellCheck.class).annotatedWith(Names.named ("spellcheck2")).to (SpellCheckModified.class);
	}
}

//creating the roor Text Editor class
class TextEditorMain {
	protected SpellCheck spellchecker;
	
	public void makeSpellCheck () {
		spellchecker.checkSpelling();
	}
}

//creating the TextEditor
class TextEditor1  extends TextEditorMain{	
	@Inject
	public TextEditor1 (@Named ("spellcheck1") SpellCheck spellchecker) {
		this.spellchecker = spellchecker;
	}
}

class TextEditor2  extends TextEditorMain{
	@Inject
	public TextEditor2 (@Named ("spellcheck2") SpellCheck spellchecker) {
		this.spellchecker = spellchecker;
	}
}

//class to implement the dependency injection
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Injector injector  = Guice.createInjector(new TextEditorModule());
		TextEditor1 editor1 = injector.getInstance (TextEditor1.class);
		editor1.makeSpellCheck();
		TextEditor2 editor2 = injector.getInstance(TextEditor2.class);
		editor2.makeSpellCheck();
	}

}
