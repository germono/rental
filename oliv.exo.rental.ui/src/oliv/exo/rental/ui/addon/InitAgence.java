 
package oliv.exo.rental.ui.addon;

import javax.annotation.PostConstruct;

import org.eclipse.e4.core.contexts.IEclipseContext;

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
		RentalFactory f = RentalFactory.eINSTANCE;
		 Address ad1 = f.createAddress();
		    ad1.setNumber(12);
		    ad1.setStreetType(StreetType.ROAD);
		    ad1.setStreetName("des bois");
		    ad1.setZipCode("31400");
		    ad1.setCity("Toulouse");
		for (int i = 0; i < 100; i++) {
			Customer c1 = f.createCustomer();
		    c1.setFirstName("John");
		    c1.setLastName("Wayne "+i);
		    c1.setAddress(ad1);
		    courante.addCustomer(c1);
		}
		context.set(AGENCE_COURANTE, courante);
	}

}
