package oliv.exo.rental.ui.view;


import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;

import oliv.exo.rebtal.core.RentalCoreActivator;

public class RentalPartView {
	
	private Label rentedObjectLabel;

	@PostConstruct
	public void creationView(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		Group infogroupe= new Group(parent,SWT.NONE);
		infogroupe.setText("Information");
		infogroupe.setLayout(new GridLayout(2,false));
		
		rentedObjectLabel = new Label(infogroupe,SWT.NONE);
		rentedObjectLabel.setText("Salut");
		setRental(RentalCoreActivator.getAgence().getRentals().get(0));
	}
	
	public void setRental(Rental agence) {
		rentedObjectLabel.setText("Loué à : "+agence.getCustomer().getFirstName());
	}
	
	@Focus
	private void setFocus() {
		rentedObjectLabel.setFocus();
	}

}
