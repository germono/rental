package oliv.exo.rental.ui.handler;

import javax.inject.Named;

import org.eclipse.e4.core.di.annotations.CanExecute;
import org.eclipse.e4.core.di.annotations.Evaluate;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.core.services.events.IEventBroker;
import org.eclipse.e4.ui.services.IServiceConstants;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Display;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalObject;

import oliv.exo.rental.ui.RentalUIConstantes;

public class CopyHandlerEtImperativeExpression implements RentalUIConstantes{
	
	@Evaluate
	public boolean estVisible(@Named(IServiceConstants.ACTIVE_SELECTION) Object selection) {
		return selection instanceof Customer||selection instanceof RentalObject;
	}

	
	@CanExecute
	public boolean vrai() {
		System.out.println("ss");
		return true;
	}
	
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) Customer selection,IEventBroker broker) {
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
		
		broker.send(MESSAGE_RENTAL_CUSTOMER_COPY,textData);
		
	}
	@Execute
	public void execute(@Named(IServiceConstants.ACTIVE_SELECTION) RentalObject selection,IEventBroker broker) {
		System.out.println("Copy");
		Clipboard clipboard = new Clipboard(Display.getCurrent());
		String textData = selection.getName();
		String rtfData = String.format("{\\rtf1\\b\\i %s}",selection.getName());
		TextTransfer textTransfer = TextTransfer.getInstance();
		RTFTransfer rtfTransfer = RTFTransfer.getInstance();
		Transfer[] transfers = new Transfer[]{textTransfer, rtfTransfer};
		Object[] data = new Object[]{textData, rtfData};
		clipboard.setContents(data, transfers);
		clipboard.dispose();
		
		broker.send(MESSAGE_RENTAL_OBJECT_COPY,textData);
		
	}
//	@Execute
//	public void execute() {
//		System.out.println("Copy o");
//		
//		
//	}
	

}
