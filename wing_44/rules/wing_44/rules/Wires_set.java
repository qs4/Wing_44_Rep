//package wing_44.rules;
//
//import de.iils.dc43.core.JavaRule;
//import wing_44.*;
//
//import static tec.uom.se.quantity.Quantities.*;
//import static de.iils.dc43.core.util.DC43Util.prettyPrint;
//
//@SuppressWarnings("all")
//public class Wires_set extends JavaRule {
//
//	@Override
//	public void execute() throws Exception {
//		for (Wire_Sparcap wire_sparcap : getGraph().allInstances(Wire_Sparcap.class)) {
//			wire_sparcap.setIs_sparcap_flag(1);
//			wire_sparcap.setAnzpolyline(2);
//		}
//		for (Wire_Rib wire_rib : getGraph().allInstances(Wire_Rib.class)) {
//			wire_rib.setIs_rib_flag(1);
//			wire_rib.setAnzpolyline(4);
//		}
//		for (Wire_Ellipse wire_ellipse : getGraph().allInstances(Wire_Ellipse.class)) {
//			wire_ellipse.setIs_ellipse_flag(1);
//			wire_ellipse.setAnzpointPolylineTop(4);
//			wire_ellipse.setAnzpointPolylineBot(4);
//		}
//	}
//
//}