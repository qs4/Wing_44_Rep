//package wing_44.rules;
//
//import de.iils.dc43.core.JavaRule;
//import wing_44.*;
//
//import static tec.uom.se.quantity.Quantities.*;
//import static de.iils.dc43.core.util.DC43Util.prettyPrint;
//
//@SuppressWarnings("all")
//public class setValuesSparcapsinSectionsTrial extends JavaRule {
//
//	@Override
//	public void execute() throws Exception {
//		for (Section1 section1 : getGraph().allInstances(Section1.class)) {
//			
//			double l_links = section1.getSections().getWing_44().getLi().getValue();
//			double l_rechts = section1.getSections().getWing_44().getMAC().getValue();
//			
//			//m_prozent
//			//n_prozent
//			//o_prozent
//			
//			// section1 :
//			// l_links = li
//			// l_rechts = lk
//			// Abstandparameter = k
//
//		}
//
//		for (Section2 section2 : getGraph().allInstances(Section2.class)) {
//			double l_links = section2.getSections().getWing_44().getMAC().getValue();
//			double l_rechts = section2.getSections().getWing_44().getLa().getValue();
//			
//			//m_prozent
//			//n_prozent
//			
//			// section2 :
//			// l_links = lk
//			// l_rechts = la
//			// Abstandparameter = a
//		}
//		
//		
//		// Section 3 überlegen???
//		for (Section2 section2 : getGraph().allInstances(Section2.class)) {
//			double l_links = section2.getSections().getWing_44().getMAC().getValue();
//			double l_rechts = section2.getSections().getWing_44().getLa().getValue();
//			
//			// section2 :
//			// l_links = lk
//			// l_rechts = la
//			// Abstandparameter = a
//		}
//
//	}
//
//}