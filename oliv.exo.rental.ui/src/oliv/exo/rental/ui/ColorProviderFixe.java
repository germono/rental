package oliv.exo.rental.ui;

import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

public class ColorProviderFixe implements IColorProvider {

	public ColorProviderFixe() {
	}

	@Override
	public Color getForeground(Object element) {
	
		return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_CYAN);
	}

	@Override
	public Color getBackground(Object element) {
		return Display.getCurrent().getSystemColor(SWT.COLOR_MAGENTA);
	}

}
