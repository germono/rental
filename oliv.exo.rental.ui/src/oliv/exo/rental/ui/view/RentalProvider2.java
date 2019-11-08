package oliv.exo.rental.ui.view;

import java.util.List;

import org.eclipse.jface.viewers.ILazyTreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeViewer;

import com.opcoach.training.rental.Customer;
import com.opcoach.training.rental.RentalAgency;


public class RentalProvider2 extends LabelProvider implements ILazyTreeContentProvider{
	
	private TreeViewer fViewer;
	private Object[] nodes;
	
	public RentalProvider2(TreeViewer fViewer) {
		
		this.fViewer=fViewer;
	}

	@Override
	public Object getParent(Object element) {
		System.out.println("Parent?"+element.getClass().getName());
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
		System.out.println(parent.getClass().getName());
		Object element;
		if(parent instanceof List) {
			element=((List) parent).get(index);
//		}else if(parent instanceof RentalAgency) {
//			fViewer.setChildCount(element, 3);
//			return;
//		}else if(parent instanceof Node) {
//			fViewer.setChildCount(element,((Node) element).getChildren().length);
//			return;
		}else {
			element=null;
		}
		fViewer.replace(parent, index, element);
		updateChildCount(element, -1);
//		if(parent instanceof RentalAgency) {
//			((RentalAgency) parent).getChild(index);
//		}
//		Node element = ((Node) parent).getChild(index);
//		fViewer.replace(parent, index, element);
//		updateChildCount(element, -1);
		
	}

	@Override
	public void updateChildCount(Object element, int currentChildCount) {
		//System.out.println(element + " " + ((Node) element).getChildCount());
		//fViewer.setChildCount(element, ((Node) element).getChildCount());
		System.out.println("Con"+element.getClass().getName());
		if(element instanceof List) {
			fViewer.setChildCount(element, ((List)element).size());
		}
		if(element instanceof RentalAgency) {
			fViewer.setChildCount(element, 3);
			return;
		}
		if(element instanceof Node) {
			fViewer.setChildCount(element,((Node) element).getChildren().length);
			return;
		}
		fViewer.setChildCount(element,0);
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
		private RentalProvider2 getEnclosingInstance() {
			return RentalProvider2.this;
		}
		
	}
}
