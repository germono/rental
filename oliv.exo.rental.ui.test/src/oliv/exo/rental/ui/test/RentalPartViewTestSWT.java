package oliv.exo.rental.ui.test;



import static org.junit.Assert.assertNotNull;

import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.EclipseContextFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.junit.jupiter.api.Test;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;

import oliv.exo.rental.ui.addon.InitAgence;
import oliv.exo.rental.ui.view.RentalPartView;

public class RentalPartViewTestSWT {
	
	@Test 
	public void test1() {
		Display display=new Display();
		Shell fen = new Shell(display);
		
		
		Bundle e4Bundle = Platform.getBundle("org.eclipse.e4.ui.workbench");
		BundleContext e4BundleContext = e4Bundle.getBundleContext();
		IEclipseContext context = EclipseContextFactory.getServiceContext(e4BundleContext);
		
		ContextInjectionFactory.make(InitAgence.class, context); 
		
		context.set(Composite.class, fen.getShell());
		
		RentalPartView p = ContextInjectionFactory.make(RentalPartView.class, context);
		//RentalPartView rt = new RentalPartView();
		//rt.creationView(fen.getShell());
		
//		fen.open();
//		while (!fen.isDisposed()) {
//			if (!display.readAndDispatch()) {
//				display.sleep();
//			}
//		}
//		display.dispose();
		assertNotNull(p);
		
	}

}
