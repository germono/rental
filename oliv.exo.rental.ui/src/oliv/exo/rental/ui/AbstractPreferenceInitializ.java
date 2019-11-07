package oliv.exo.rental.ui;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.core.runtime.preferences.DefaultScope;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.swt.graphics.RGB;
import org.osgi.service.prefs.BackingStoreException;

public class AbstractPreferenceInitializ extends AbstractPreferenceInitializer implements RentalUIConstantes{

	public AbstractPreferenceInitializ() {
		
	}

	@Override
	public void initializeDefaultPreferences() {
		IEclipsePreferences node = DefaultScope.INSTANCE.getNode(ID_PLUGIN);
		if (node!=null) {
			node.put(PREF_COLOR_CUSTUMER, StringConverter.asString(new RGB(0, 0, 100)));
			node.put(PREF_COLOR_OBJECT, StringConverter.asString(new RGB(0, 100, 0)));
			node.put(PREF_COLOR_RENTAL, StringConverter.asString(new RGB(100, 0, 0)));
			try {
				node.flush();
			} catch (BackingStoreException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
