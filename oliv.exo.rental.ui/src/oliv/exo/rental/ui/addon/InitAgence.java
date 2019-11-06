 
package oliv.exo.rental.ui.addon;

import javax.annotation.PostConstruct;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;

import oliv.exo.rebtal.core.RentalCoreActivator;
import oliv.exo.rental.ui.RentalUIConstantes;

import org.eclipse.e4.core.contexts.IEclipseContext;

public class InitAgence  implements RentalUIConstantes{

	@PostConstruct
	public void initAgence(IEclipseContext context) {
		RentalAgency courante = RentalCoreActivator.getAgence();
		context.set(AGENCE_COURANTE, courante);
	}

}
