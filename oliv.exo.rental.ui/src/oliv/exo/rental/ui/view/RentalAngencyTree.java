package oliv.exo.rental.ui.view;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.net.ssl.ExtendedSSLSession;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;

import oliv.exo.rental.ui.RentalUIConstantes;

public class RentalAngencyTree implements RentalUIConstantes{

	@PostConstruct
	public void initArbre(Composite parent, @Named(AGENCE_COURANTE) RentalAgency agence,IEclipseContext context,
			ESelectionService selectionService,EMenuService menuService) {
		TreeViewer tv  = new TreeViewer(parent);
		menuService.registerContextMenu(tv.getControl(), "oliv.exo.rental.ui.popupmenu.memupopuparbre");
		RentalProvider rp=ContextInjectionFactory.make(RentalProvider.class,context);
		tv.setContentProvider(rp);
		tv.setLabelProvider(rp);
		tv.setInput(Arrays.asList(agence));
		tv.addSelectionChangedListener(new ISelectionChangedListener() {
			
			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection)event.getSelection();
				
				selectionService.setSelection(sel.size()==1?sel.getFirstElement():sel.toArray());
			}
		});
	}
}
