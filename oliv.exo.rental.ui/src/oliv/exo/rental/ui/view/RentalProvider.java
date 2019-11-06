package oliv.exo.rental.ui.view;

import java.util.Collection;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;
import com.opcoach.training.rental.RentalObject;

public class RentalProvider extends LabelProvider implements ITreeContentProvider{

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
	}
}
