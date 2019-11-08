package oliv.exo.rental.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;

import org.eclipse.jface.preference.ComboFieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;

import oliv.exo.rental.ui.addon.Palette;

public class FieldEditorPreferencePalette extends FieldEditorPreferencePage {
	
	@Inject
	@Named("PaletteManager")
	private Map<String, Palette> paletteManager;
	
	public FieldEditorPreferencePalette(){
		super(GRID);
	}
//	@Inject
//	public FieldEditorPreferencePalette(@Named("PaletteManager")Map<String, Palette> paletteManager) {
//		this();
//		this.paletteManager=paletteManager;
//	}


	@Override
	protected void createFieldEditors() {
		List<String[]>l = new ArrayList<String[]>();
		for(Entry<String, Palette> entry : paletteManager.entrySet()) {
			String[] pal=new String[] {entry.getValue().getName(),entry.getKey()};
			l.add(pal);
			System.out.println(pal[0]+" "+pal[1]);
		}
		String[][] entry=l.toArray(new String[][] {});
		//String[][] entry=paletteManager.entrySet().stream().map(e->new String[] {e.getKey(),e.getValue().getClass().getName()}).toArray(new String[][] {{}});
		addField(new ComboFieldEditor("PalettePreference", "Les palettes dispo :", entry,  getFieldEditorParent()));
	}

}
