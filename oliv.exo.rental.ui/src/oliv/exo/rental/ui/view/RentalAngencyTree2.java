package oliv.exo.rental.ui.view;

import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;

import oliv.exo.rental.ui.RentalUIConstantes;

public class RentalAngencyTree2 implements RentalUIConstantes{

	@PostConstruct
	public void initArbre(Composite parent, @Named(AGENCE_COURANTE) RentalAgency agence) {
		TreeViewer tv  = new TreeViewer(parent,SWT.VIRTUAL);
		tv.setUseHashlookup(true);
		tv.setContentProvider(new RentalProvider2(tv));
		tv.setLabelProvider((RentalProvider2)tv.getContentProvider());
		List root=Arrays.asList(agence);
		tv.setInput(root);
		tv.getTree().setLayoutData(GridDataFactory.fillDefaults().create());
		tv.setChildCount(root, root.size());
		tv.getTree().setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		System.out.println(root.size());

	}
}
