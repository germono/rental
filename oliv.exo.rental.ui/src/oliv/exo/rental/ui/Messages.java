package oliv.exo.rental.ui;

import org.eclipse.osgi.util.NLS;

public class Messages extends NLS {
	private static final String BUNDLE_NAME = "oliv.exo.rental.ui.messages"; //$NON-NLS-1$
	public static String Activator_toto;
	static {
		// initialize resource bundle
		NLS.initializeMessages(BUNDLE_NAME, Messages.class);
	}

	private Messages() {
	}
}
