package oliv.exo.rebtal.core;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.core.helpers.RentalAgencyGenerator;

public class RentalCoreActivator implements BundleActivator {

	private static BundleContext context;
	private static RentalAgency a;

	static BundleContext getContext() {
		return context;
	}

	public void start(BundleContext bundleContext) throws Exception {
		RentalCoreActivator.context = bundleContext;
		a =RentalAgencyGenerator.createSampleAgency();
	}
	
	public void stop(BundleContext bundleContext) throws Exception {
		RentalCoreActivator.context = null;
	}
	
	public static RentalAgency getAgence() {
		return a;
	}

}
