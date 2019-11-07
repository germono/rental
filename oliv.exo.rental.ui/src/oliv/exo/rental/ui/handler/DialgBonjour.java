package oliv.exo.rental.ui.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class DialgBonjour {
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SHELL)Shell shell) {
		MessageDialog.openInformation(shell, "Salut","Bonjour a tous");
	}
	@CanExecute
	public boolean canExecute() {
		return true;
	}
}
