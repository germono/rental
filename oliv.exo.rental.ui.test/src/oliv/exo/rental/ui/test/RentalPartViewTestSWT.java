package oliv.exo.rental.ui.test;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.jupiter.api.Test;

import oliv.exo.rental.ui.view.RentalPartView;

public class RentalPartViewTestSWT {
	
	@Test
	public void test1() {
		Display display=new Display();
		Shell fen = new Shell(display);
		
		
		RentalPartView rt = new RentalPartView();
		rt.creationView(fen.getShell());
		
		fen.open();
		while (!fen.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

}
