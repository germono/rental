 
package oliv.exo.rental.ui.addon;

import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.eclipse.core.runtime.preferences.IScopeContext;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.opcoach.e4.preferences.ScopedPreferenceStore;
import com.opcoach.training.rental.Address;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalFactory;
import com.opcoach.training.rental.StreetType;

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
	
	
}
