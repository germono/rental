package oliv.exo.rental.ui.view;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;

import oliv.exo.rental.ui.RentalUIConstantes;

public class RentalAngencyTree implements RentalUIConstantes{

	@PostConstruct
	public void initArbre(Composite parent, @Named(AGENCE_COURANTE) RentalAgency agence,IEclipseContext context) {
		TreeViewer tv  = new TreeViewer(parent);
		RentalProvider rp=ContextInjectionFactory.make(RentalProvider.class,context);
		tv.setContentProvider(rp);
		tv.setLabelProvider(rp);
		tv.setInput(Arrays.asList(agence));
	}
}
