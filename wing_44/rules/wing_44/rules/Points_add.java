//package wing_44.rules;
//
//import de.iils.dc43.core.JavaRule;
//import wing_44.*;
//
//import static tec.uom.se.quantity.Quantities.*;
//import static de.iils.dc43.core.util.DC43Util.prettyPrint;
//
//@SuppressWarnings("all")
//public class Points_add extends JavaRule {
//
//	@Override
//	public void execute() throws Exception {
//		int k = 0;
//		for (PolyLine_Sparcap polyline_sparcap_bot : getGraph().allInstances(PolyLine_Sparcap.class)) {
//
//			int PolyLineNummer = polyline_sparcap_bot.getPolylinenummer().intValue();
//			if (PolyLineNummer == 1) {
//				int anzpoint = polyline_sparcap_bot.getAnzpoint_bot();
//
//				Controlpoint_Spline_Sparcap pointsparcap_inst[] = new Controlpoint_Spline_Sparcap[anzpoint];
//
//				int i = 0;
//				for (i = 0; i < anzpoint; i++) {
//					pointsparcap_inst[i] = Controlpoint_Spline_Sparcap.create();
//					pointsparcap_inst[i].setPartofPolyLine(1);
//					pointsparcap_inst[i].setPunktnummer_1(i);
//					pointsparcap_inst[i].setIs_1(1);
//					
//					polyline_sparcap_bot.getElement().add(pointsparcap_inst[i]);
//					polyline_sparcap_bot.getCp_PolylineBot_Sc().add(pointsparcap_inst[i]);
//				}
//
//				polyline_sparcap_bot.setStart(pointsparcap_inst[0]);
//				for (i = 0; i < (anzpoint - 1); i++) {
//					pointsparcap_inst[i].getNextPoint().add(pointsparcap_inst[i + 1]);
////				pointsparcap_inst[i].getNextControlPoint().add(pointsparcap_inst[i + 1]);
//
//				}
////			pointsparcap_inst[7].getNextPoint().add(pointsparcap_inst[0]);
//			}
//		}
//	}
//
//}