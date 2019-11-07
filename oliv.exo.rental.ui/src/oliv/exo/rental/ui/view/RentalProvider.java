package oliv.exo.rental.ui.view;

import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.resource.StringConverter;
import org.eclipse.jface.viewers.IColorProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.opcoach.e4.preferences.ScopedPreferenceStore;
import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

import oliv.exo.rental.ui.RentalUIConstantes;

public class RentalProvider extends LabelProvider implements ITreeContentProvider,IColorProvider,RentalUIConstantes{

	@Inject @Named(RENTAL_UI_IMG_REGISTRE)
	private ImageRegistry bankImg;
	
	
	private ScopedPreferenceStore pref;
	@Inject
	public RentalProvider(@Named("PREF")ScopedPreferenceStore isc) {
		pref=isc; 
	}
	
	@Override
	public Object[] getElements(Object inputElement) {
		if(inputElement instanceof Collection<?>) {
			return ((Collection<?>)inputElement).toArray();
		}
		return null;
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if(parentElement instanceof RentalAgency) {
			return new Object[] {new Node(Node.CUSTOMERS,(RentalAgency)parentElement),
					new Node(Node.LOCATIONS,(RentalAgency)parentElement),
					new Node(Node.OBJET,(RentalAgency)parentElement)};
		}
		if(parentElement instanceof Node) {
			return ((Node) parentElement).getChildren();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		if(element instanceof Customer) {
			return ((Customer)element).getParentAgency();
		}
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		
		return (element instanceof RentalAgency||element instanceof Node) ;
	}
	
	@Override
	public String getText(Object element) {
		if(element instanceof RentalAgency) 
			return ((RentalAgency) element).getName();
		if(element instanceof Customer) 
			return ((Customer) element).getDisplayName();		
		if(element instanceof RentalObject) 
			return ((RentalObject) element).getName();		
		return element.toString();
	}
	private class Node{
		public static final String CUSTOMERS = "Customers";
		public static final String LOCATIONS = "Locations";
		public static final String OBJET = "Object";
		private String label;
		private RentalAgency agence;
		public Node(String label, RentalAgency agence) {
			super();
			this.label = label;
			this.agence = agence;
		}
		public Object[] getChildren() {
			if(label==CUSTOMERS)
				return agence.getCustomers().toArray();
			if(label==LOCATIONS)
				return agence.getRentals().toArray();
			if(label==OBJET)
				return agence.getObjectsToRent().toArray();
			return null;
		}
		@Override
		public String toString() {
			return label;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + ((agence == null) ? 0 : agence.hashCode());
			result = prime * result + ((label == null) ? 0 : label.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Node other = (Node) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (agence == null) {
				if (other.agence != null)
					return false;
			} else if (!agence.equals(other.agence))
				return false;
			if (label == null) {
				if (other.label != null)
					return false;
			} else if (!label.equals(other.label))
				return false;
			return true;
		}
		private RentalProvider getEnclosingInstance() {
			return RentalProvider.this;
		}
		
	}
	
	@Override
	public Color getForeground(Object element) {
		if(element instanceof RentalAgency) 
			return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_BLUE);
		if(element instanceof Customer) 			
			return getAColor(pref.getString(PREF_COLOR_CUSTUMER));
		if(element instanceof Rental) 
			return getAColor(pref.getString(PREF_COLOR_RENTAL));
		if(element instanceof Node) 
				return Display.getCurrent().getSystemColor(SWT.COLOR_DARK_MAGENTA);
		if(element instanceof RentalObject) 
			return  getAColor(pref.getString(PREF_COLOR_OBJECT));
		return null;
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
	@Override
	public Image getImage(Object element) {
//		System.out.println(bankImg);
		if(element instanceof RentalAgency) 
			return bankImg.get(IMG_AGENCY);
		if(element instanceof Customer) 
			return bankImg.get(IMG_CUSTO);
		if(element instanceof Rental) 
			return bankImg.get(IMG_RENTAL);
		if(element instanceof Node) 
			return bankImg.get(IMG_SAMPLE);
		if(element instanceof RentalObject) 
			return bankImg.get(IMG_RENTAL_OBJET);
		return null;
	}

	
}
