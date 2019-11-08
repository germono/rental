package oliv.exo.chapeau;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspective;
import org.eclipse.e4.ui.model.application.ui.advanced.MPerspectiveStack;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.e4.ui.workbench.modeling.EModelService;
import org.eclipse.e4.ui.workbench.modeling.EPartService;

public class generePerspective {

	@PostConstruct
	private void pu(IEclipseContext context,EModelService ms,MPart part,MApplication appli,EPartService eps) {
		MWindow refwin=ms.getTopLevelWindowFor(part);
		MPerspective p = (MPerspective)ms.cloneSnippet(appli, "oliv.exo.rental.ui.perspective", refwin);
		MPerspectiveStack ps= (MPerspectiveStack)ms.find("oliv.exo.chapeau.perspectivestack.0", appli);
		ps.getChildren().add(p);
		
		eps.switchPerspective(p);

	}
}
