package oliv.exo.rental.ui.view;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;

import oliv.exo.rental.ui.RentalUIConstantes;

public class RentalAngencyTree2 implements RentalUIConstantes{

	@PostConstruct
	public void initArbre(Composite parent, @Named(AGENCE_COURANTE) RentalAgency agence) {
		TreeViewer tv  = new TreeViewer(parent,SWT.VIRTUAL);
		tv.setContentProvider(new RentalProvider2());
		tv.setLabelProvider((RentalProvider2)tv.getContentProvider());
		tv.setInput(Arrays.asList(agence));
	}
}
