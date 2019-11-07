package oliv.exo.rental.ui;

import java.util.Arrays;

import org.eclipse.jface.preference.ColorFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;

public class FieldEditorPreferencePageCouleur extends FieldEditorPreferencePage implements RentalUIConstantes {

	public FieldEditorPreferencePageCouleur() {
		super(GRID);
	}
	@Override
	protected void createFieldEditors() {
		addField(new ColorFieldEditor(PREF_COLOR_CUSTUMER, "Custumer", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_COLOR_OBJECT, "Object", getFieldEditorParent()));
		addField(new ColorFieldEditor(PREF_COLOR_RENTAL, "Rental", getFieldEditorParent()));
		
	}

}
