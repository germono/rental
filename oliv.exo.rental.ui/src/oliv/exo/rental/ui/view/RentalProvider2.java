package oliv.exo.rental.ui.view;

import org.eclipse.jface.viewers.ILazyTreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;

public class RentalProvider2 extends LabelProvider implements ILazyTreeContentProvider{

	@Override
	public Object getParent(Object element) {
		if(element instanceof Customer) {
			return ((Customer)element).getParentAgency();
		}
		return null;
	}

	
	@Override
	public String getText(Object element) {
		if(element instanceof RentalAgency) 
			return ((RentalAgency) element).getName();
		if(element instanceof Customer) 
			return ((Customer) element).getDisplayName();		
		return element.toString();
	}

	@Override
	public void updateElement(Object parent, int index) {
		if(parent instanceof RentalAgency) {
			((RentalAgency) parent).getCustomers().get(index);
			
		}
	}

	@Override
	public void updateChildCount(Object element, int currentChildCount) {
		if(element instanceof RentalAgency) {
			int nbCus= ((RentalAgency) element).getCustomers().size();
			
		}
	}

}
