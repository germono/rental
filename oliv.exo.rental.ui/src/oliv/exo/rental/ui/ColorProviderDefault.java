package oliv.exo.rental.ui;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Display;

import com.opcoach.e4.preferences.ScopedPreferenceStore;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;



public class ColorProviderDefault implements IColorProvider ,RentalUIConstantes{
	private ScopedPreferenceStore pref;
	
	@Inject
	public ColorProviderDefault(@Named("PREF")ScopedPreferenceStore isc) {
		pref=isc; 
	}

	@Override
	public Color getForeground(Object element) {
		if(element instanceof RentalAgency) 
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE);
		if(element instanceof Customer) 			
			return Display.getCurrent().getSystemColor(SWT.COLOR_CYAN);
			//return getAColor(pref.getString(PREF_COLOR_CUSTUMER));
		if(element instanceof Rental) 
			return getAColor(pref.getString(PREF_COLOR_RENTAL));
		if(element instanceof RentalObject) 
			return  getAColor(pref.getString(PREF_COLOR_OBJECT));
		return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_RED);
	}
	private Color getAColor(String rgbKey) {
		ColorRegistry colorRegistry = JFaceResources.getColorRegistry();
		Color col = colorRegistry.get(rgbKey);
		if (col==null) {
			colorRegistry.put(rgbKey, StringConverter.asRGB(rgbKey));
			col=colorRegistry.get(rgbKey);
		}
		return col;
	}
	@Override
	public Color getBackground(Object element) {
		// TODO Auto-generated method stub
		return null;
	}

}
