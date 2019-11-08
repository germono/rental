package oliv.exo.rental.ui.view;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.services.EMenuService;
import org.eclipse.e4.ui.workbench.modeling.ESelectionService;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Composite;

import com.opcoach.training.rental.RentalAgency;

import oliv.exo.rental.ui.RentalUIConstantes;
import oliv.exo.rental.ui.addon.Palette;

public class RentalAngencyTree implements RentalUIConstantes{

	private TreeViewer tv;
	private RentalProvider rp;

	@PostConstruct
	public void initArbre(Composite parent, @Named(AGENCE_COURANTE) RentalAgency agence,IEclipseContext context,
			ESelectionService selectionService,EMenuService menuService) {
		tv = new TreeViewer(parent);
		menuService.registerContextMenu(tv.getControl(), "oliv.exo.rental.ui.popupmenu.memupopuparbre");
		rp = ContextInjectionFactory.make(RentalProvider.class,context);
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
	
//	@Inject
//	//nodePath=ID_PLUGIN, n'est pas obligatoire car deja dans le plugin
//	public void refreshTree(@Preference(nodePath=ID_PLUGIN,value=PREF_COLOR_CUSTUMER)String custCol,
//			@Preference(value=PREF_COLOR_RENTAL)String rentCol,
//			@Preference(value=PREF_COLOR_OBJECT)String objCol) {
//		if(tv!=null) {
//			tv.refresh();
//		}
//	}
	@Inject
	//nodePath=ID_PLUGIN, n'est pas obligatoire car deja dans le plugin
	public void refreshTree(@Named("PaletteActuel")Palette nouvellepalette) {
		System.out.println("Mise a jour de l'arbre");
		if(tv!=null) {
			rp.setPalette(nouvellepalette);
			tv.refresh();
		}
	}
}
