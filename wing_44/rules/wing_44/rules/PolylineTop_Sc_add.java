//package wing_44.rules;
//
//import de.iils.dc43.core.JavaRule;
//import de.iils.dc43.core.geometry.publication.Component;
//import de.iils.dc43.core.geometry.publication.G;
//import wing_44.*;
//import static tec.uom.se.quantity.Quantities.*;
//import static de.iils.dc43.core.util.DC43Util.prettyPrint;
//
//@SuppressWarnings("all")
//public class PolylineTop_Sc_add extends JavaRule {
//
//	@Override
//	public void execute() throws Exception {
//		int k = 0;
//
//		for (Wire_Sparcap wire_sparcap_inst : getGraph().allInstances(Wire_Sparcap.class)) {
//
//			
//			// PolyLine Sparcap Top
//			PolyLine_Sparcap polyline_sparcap_top = PolyLine_Sparcap.create();
//			polyline_sparcap_top.setPolylinenummer(2);
//			polyline_sparcap_top.setIs_top_flag(1);
//			wire_sparcap_inst.getElement().add(polyline_sparcap_top);
//			wire_sparcap_inst.setPolyLine_Sparcap_top(polyline_sparcap_top);
//			wire_sparcap_inst.setStart(polyline_sparcap_top);
//
//			// PolyLine Sparcap Bot
//			PolyLine_Sparcap polyline_sparcap_bot = PolyLine_Sparcap.create();
//			polyline_sparcap_bot.setPolylinenummer(1);
//			polyline_sparcap_top.setIs_top_flag(0);
////			wire_sparcap_inst.getElement().add(polyline_sparcap_bot);
//			wire_sparcap_inst.setPolyLine_Sparcap_bot(polyline_sparcap_bot);
////			wire_sparcap_inst.setStart(polyline_sparcap_bot);
//
//			polyline_sparcap_top.getNext().add(polyline_sparcap_top);
//			
//			
//			G g = G.create();
//			g.getGeometric().add(polyline_sparcap_top);
////			Direction direction = Direction.create().setDx(1.).setDz(0.);
//			Component.create().setShape(g);
//
//		}
//	}
//
//}