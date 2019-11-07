package oliv.exo.rental.ui.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;

public class CopyHandlerEtImperativeExpression {
	
	
	@CanExecute
	public boolean vrai() {
		System.out.println("ss");
		return true;
	}
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) Customer selection) {
		System.out.println("Copy");
		Clipboard clipboard = new Clipboard(Display.getCurrent());
		String textData = selection.getDisplayName();
		String rtfData = String.format("{\\rtf1\\b\\i %s}",selection.getDisplayName());
		TextTransfer textTransfer = TextTransfer.getInstance();
		RTFTransfer rtfTransfer = RTFTransfer.getInstance();
		Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer};
		Object[] data = new Object[]{textData, rtfData};
		clipboard.setContents(data, transfers);
		clipboard.dispose();
	}
//	@Execute
//	public void execute() {
//		System.out.println("Copy o");
//		
//		
//	}
	

}
