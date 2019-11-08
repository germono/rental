
package oliv.exo.rental.ui.addon;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.InvalidRegistryObjectException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.InjectionException;
import org.eclipse.e4.core.di.annotations.Optional;
import org.eclipse.e4.core.di.extensions.Preference;
import org.eclipse.e4.ui.di.UIEventTopic;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.viewers.IColorProvider;
import org.osgi.framework.Bundle;
import org.osgi.framework.FrameworkUtil;

import com.opcoach.e4.preferences.ScopedPreferenceStore;
import com.opcoach.training.rental.RentalAgency;

import oliv.exo.rebtal.core.RentalCoreActivator;
import oliv.exo.rental.ui.RentalUIConstantes;

public class InitAgence implements RentalUIConstantes {

	

	private Map<String, Palette> palettes;

	@PostConstruct
	public void initAgence(IEclipseContext context, IExtensionRegistry reg,@Preference(value="PalettePreference") String nomPalette) {
		RentalAgency courante = RentalCoreActivator.getAgence();
		context.set(AGENCE_COURANTE, courante);
		context.set(RENTAL_UI_IMG_REGISTRE, getLocalImageRegistry());
		context.set("PREF", new ScopedPreferenceStore(InstanceScope.INSTANCE, "oliv.exo.rental.ui"));
		palettes=initPalette(reg,context);
		context.set("PaletteManager",palettes);
		insertPaletteInContext(nomPalette,context);
			
	}

	ImageRegistry getLocalImageRegistry() {
		Bundle b = FrameworkUtil.getBundle(getClass());
		ImageRegistry reg = new ImageRegistry();
		for (String img : Arrays.asList(IMG_AGENCY, IMG_CUSTO, IMG_RENTAL, IMG_RENTAL_OBJET, IMG_SAMPLE))
			reg.put(img, ImageDescriptor.createFromURL(b.getEntry(img)));
//		System.out.println("bob");
		return reg;
	}

	@Inject
	@Optional
	public void reactMessage(@UIEventTopic("rental/*") String lacopy) {
		System.out.println("Il y a eu une copy : " + lacopy);
	}

	public void inspectFragment(IExtensionRegistry reg) {
		for (IConfigurationElement elt : reg.getConfigurationElementsFor("org.eclipse.e4.workbench.model")) {
			switch (elt.getName()) {
			case "fragment":
				System.out.format("Model fragment : %s in %s\n", elt.getAttribute("uri"), elt.getNamespaceIdentifier());
				break;
			case "processor":
				System.out.format("Model processor : %s in %s\n", elt.getAttribute("class"),
						elt.getNamespaceIdentifier());
				break;
			default:
				System.out.format("Model autre : %s in %s\n", elt.getName(), elt.getNamespaceIdentifier());
			}
		}
	}

	public Map<String, Palette> initPalette(IExtensionRegistry reg,IEclipseContext context) {
		Map<String, Palette> paletteManager = new HashMap<>();
		for (IConfigurationElement elt : reg.getConfigurationElementsFor("oliv.exo.rental.ui.palette")) {
			if (elt.getName()=="palette") {
				System.out.format("Une palette : %s %s %s in %s\n", elt.getAttribute("id"), elt.getAttribute("name"),
						elt.getAttribute("paletteClass"), elt.getNamespaceIdentifier());
				Bundle b = Platform.getBundle(elt.getNamespaceIdentifier());
				try {
					Palette p = new Palette(elt.getAttribute("id"), elt.getAttribute("name"), 
						(IColorProvider)ContextInjectionFactory.make(b.loadClass(elt.getAttribute("paletteClass")),context));
				
					paletteManager.put(elt.getAttribute("paletteClass"), p);
				} catch (InvalidRegistryObjectException e) {
					e.printStackTrace();
				} catch (InjectionException e) {
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
		return paletteManager;
	}
	
	@Optional
	@Inject
	public void insertPaletteInContext(@Preference(value="PalettePreference") String nom,IEclipseContext context){
		if(palettes!=null) {
		System.out.println("Changement de palette : "+nom);
		
		context.set("PaletteActuel", palettes.get(nom));
		}
	}
	
}
