 
package oliv.exo.rental.ui.addon;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.opcoach.e4.preferences.ScopedPreferenceStore;
import com.opcoach.training.rental.RentalAgency;

import oliv.exo.rebtal.core.RentalCoreActivator;
import oliv.exo.rental.ui.RentalUIConstantes;

public class InitAgence  implements RentalUIConstantes{

	@PostConstruct
	public void initAgence(IEclipseContext context) {
		RentalAgency courante = RentalCoreActivator.getAgence();
		context.set(AGENCE_COURANTE, courante);
		context.set(RENTAL_UI_IMG_REGISTRE, getLocalImageRegistry());
		context.set("PREF", new ScopedPreferenceStore(InstanceScope.INSTANCE, "oliv.exo.rental.ui"));
	}
	
	
	ImageRegistry getLocalImageRegistry() {
		Bundle b = FrameworkUtil.getBundle(getClass());
		ImageRegistry reg=new ImageRegistry();
		for(String img : Arrays.asList(IMG_AGENCY,IMG_CUSTO,IMG_RENTAL,IMG_RENTAL_OBJET,IMG_SAMPLE))
			reg.put(img, ImageDescriptor.createFromURL(b.getEntry(img)));
//		System.out.println("bob");
		return reg;
	}
	
	@Inject
	@Optional
	public void reactMessage(@UIEventTopic("rental/*")String lacopy) {
		System.out.println("Il y a eu une copy : "+lacopy);
	}
	
}
