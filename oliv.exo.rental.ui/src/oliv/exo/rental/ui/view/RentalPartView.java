package oliv.exo.rental.ui.view;


import javax.annotation.PostConstruct;
import javax.inject.Named;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DateTime;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import com.opcoach.training.rental.Rental;
import com.opcoach.training.rental.RentalAgency;

import oliv.exo.rental.ui.RentalUIConstantes;

public class RentalPartView implements RentalUIConstantes{
	

	private Label lblObjet;
	private Label lblNom;
	private Label lblDatedebut;
	private Label lblDatefin;
	private Label lblPrenom;
	private Group group;
	private Text text;	
	private Table table;

	@PostConstruct
	public void creationView(Composite parent, @Named(AGENCE_COURANTE) RentalAgency agence) {
		parent.setLayout(new GridLayout(1, false));
		Group infogroupe= new Group(parent,SWT.NONE);
		infogroupe.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		infogroupe.setText("Information");
		infogroupe.setLayout(new GridLayout(3,false));
		
		lblObjet = new Label(infogroupe, SWT.NONE);
		lblObjet.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 3, 1));
		lblObjet.setText("Objet");
		
		Label  rentedObjectLabel = new Label(infogroupe,SWT.NONE);
		rentedObjectLabel.setText("Loué à : ");
		
		lblPrenom = new Label(infogroupe, SWT.NONE);
		lblPrenom.setText("Prenom");
		
		lblNom = new Label(infogroupe, SWT.NONE);
		lblNom.setText("Nom");
		
		Group grpDatesDeLocation = new Group(parent, SWT.NONE);
		grpDatesDeLocation.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		grpDatesDeLocation.setText("Dates de location");
		grpDatesDeLocation.setLayout(new GridLayout(2, false));
		
		Label lblDu = new Label(grpDatesDeLocation, SWT.NONE);
		lblDu.setText("du :");
		
		lblDatedebut = new Label(grpDatesDeLocation, SWT.NONE);
		lblDatedebut.setText("dateDebut");
		
		Label lblAu = new Label(grpDatesDeLocation, SWT.NONE);
		lblAu.setText("au :");
		
		lblDatefin = new Label(grpDatesDeLocation, SWT.NONE);
		lblDatefin.setText("dateFin");
		
		group = new Group(parent, SWT.NONE);
		group.setLayout(new GridLayout(6, false));
		group.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblNewLabel = new Label(group, SWT.NONE);
		lblNewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNewLabel.setText("New Label");
		
		text = new Text(group, SWT.BORDER);
		text.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		new Label(group, SWT.NONE);
		
		Combo combo = new Combo(group, SWT.NONE);
		combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Button btnNewButton = new Button(group, SWT.NONE);
		btnNewButton.setText("New Button");
		
		Button btnCheckButton = new Button(group, SWT.CHECK);
		btnCheckButton.setText("Check Button");
		new Label(group, SWT.NONE);
		new Label(group, SWT.NONE);
		
		Label label_1 = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		new Label(group, SWT.NONE);
		new Label(group, SWT.NONE);
		new Label(group, SWT.NONE);
		
		Button btnRadioButton = new Button(group, SWT.RADIO);
		btnRadioButton.setText("Radio Button");
		
		Spinner spinner = new Spinner(group, SWT.BORDER);
		
		Label label = new Label(group, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		DateTime dateTime = new DateTime(group, SWT.BORDER);
		
		table = new Table(group, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(100);
		tblclmnNewColumn_1.setText("New Column");
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(100);
		tblclmnNewColumn.setText("New Column");
		new Label(group, SWT.NONE);
		
		Scale scale = new Scale(group, SWT.NONE);
		
		Slider slider = new Slider(group, SWT.NONE);
		
		Link link = new Link(group, SWT.NONE);
		link.setText("<a>New Link</a>");
		setRental(agence.getRentals().get(0));
	}
	
	 
	public void setRental(Rental agence) {
		lblNom.setText(agence.getCustomer().getFirstName());
		lblPrenom.setText(agence.getCustomer().getLastName());
		lblObjet.setText(agence.getRentedObject().getName());
		lblDatedebut.setText(String.format("%d/%d/%d",
				agence.getStartDate().getDay(),
				agence.getStartDate().getMonth(),
				agence.getStartDate().getYear()+1900));
		lblDatefin.setText(String.format("%d/%d/%d",
				agence.getEndDate().getDay(),
				agence.getEndDate().getMonth(),
				agence.getEndDate().getYear()+1900));
	}
	
	@Focus
	private void setFocus() {
		lblNom.setFocus();
	}
}
