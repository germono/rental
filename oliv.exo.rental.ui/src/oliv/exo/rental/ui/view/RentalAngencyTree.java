package oliv.exo.rental.ui.view;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;

import oliv.exo.rental.ui.RentalUIConstantes;

public class RentalAngencyTree implements RentalUIConstantes{

	@PostConstruct
	public void initArbre(Composite parent, @Named(AGENCE_COURANTE) RentalAgency agence) {
		TreeViewer tv  = new TreeViewer(parent);
		tv.setContentProvider(new RentalProvider());
		tv.setLabelProvider((RentalProvider)tv.getContentProvider());
		tv.setInput(Arrays.asList(agence));
	}
}
