//package wing_44.rules;
//
//import de.iils.dc43.core.JavaRule;
//import tec.uom.se.NumberQuantity;
//import wing_44.*;
//
//import static tec.uom.se.quantity.Quantities.*;
//
//import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;
//
//import static de.iils.dc43.core.util.DC43Util.prettyPrint;
//
//@SuppressWarnings("all")
//public class Wires__Profilebene_set extends JavaRule {
//
//	@Override
//	public void execute() throws Exception {
//		// Funktion wird eigentlich nicht gebraucht und dient nur der Übersichtlichkeit!
//		for (Wire_gen W : getGraph().allInstances(Wire_gen.class)) {
//
//			// Up
//			Punkt_zNr punkt_start_up = set_start_point_up(W);
//			Punkt_zNr punkt_end_up = set_end_point_up(W);
//
//			setValues_up(W, punkt_start_up, punkt_end_up);
//
//			// Bot
//			Punkt_zNr punkt_start_bot = set_start_point_bot(W);
//			Punkt_zNr punkt_end_bot = set_end_point_bot(W);
//
//			setValues_bot(W, punkt_start_bot, punkt_end_bot);
//
//		}
//	}
//
//// 0 = s, 1 = s2, 2= m_s, 3 = m_e, 4 = e, 5 = e2
//
//	private Punkt_zNr set_start_point_up(Wire_gen W) {
//		Profilebene P = W.getProfilebene_gen();
//
//		int b_art = P.getBauteil().getBauteilart();
//		Punkt_zNr Point = null;
//		
//		if (b_art == 1) { // Sparcap
//			int bauteiltyp = P.getBauteil().getBauteiltypnummer();
//
//			Point = P.getCoordinates_basis_up().getPunkt_zNr().get(0);
////			if (bauteiltyp==3) {
////			Point = P.getCoordinates_basis_up().getPunkt_zNr().get(4);
////
////			}
//		} else if (b_art == 2) { // Rib
//			if (W instanceof Wire_Rib) {
//				Point = P.getCoordinates_basis_up().getPunkt_zNr().get(1);
//			} else if (W instanceof Wire_Ellipse) {
//				Point = P.getCoordinates_basis_up().getPunkt_zNr().get(2);
//			}
//		} else if (b_art == 3) { // Stringer
//			int stringernummer = P.getBauteil().getBauteilnummer();
//			Bauteil_gen B = P.getBauteil();
////			if (B instanceof Stringer_up) {
////				Point = P.getZKoordinaten_up().getPunkt_zNr().get(stringernummer*2);
////			}else if (B instanceof Stringer_down) {
//				Point = P.getCoordinates_str_up().getPunkt_zNr().get(stringernummer*2);
////			}
//		} else if (b_art == 4) { // Spar
//			Point = P.getCoordinates_basis_up().getPunkt_zNr().get(0);			
//		}
//
//		return Point;
//
//	}
//
//	private Punkt_zNr set_end_point_up(Wire_gen W) {
//		Profilebene P = W.getProfilebene_gen();
//
//		int b_art = P.getBauteil().getBauteilart();
//		Punkt_zNr Point = null;
//
//		if (b_art == 1) { // Sparcap
//			int bauteiltyp = P.getBauteil().getBauteiltypnummer();
//
//			Point = P.getCoordinates_basis_up().getPunkt_zNr().get(1);			
////			if (bauteiltyp==3) {
////				Point = P.getCoordinates_basis_up().getPunkt_zNr().get(5);
////
////				}
//		} else if (b_art == 2) { // Rib
//
//			if (W instanceof Wire_Rib) {
//				Point = P.getCoordinates_basis_up().getPunkt_zNr().get(4);			
//
//			} else if (W instanceof Wire_Ellipse) {
//				Point = P.getCoordinates_basis_up().getPunkt_zNr().get(3);			
//			}
//		} else if (b_art == 3) { // Stringer
//			int stringernummer = P.getBauteil().getBauteilnummer();
//			Bauteil_gen B = P.getBauteil();
////			if (B instanceof Stringer_up|B instanceof Stringer_down) {
//				Point = P.getCoordinates_str_up().getPunkt_zNr().get(stringernummer*2+1);
////			}
//		} else if (b_art == 4) { // Spar
//			Point = P.getCoordinates_basis_up().getPunkt_zNr().get(1);// eigl noch andere nötig + sparwidth -->später			
//		}
//
//		return Point;
//	}
//
//	private Punkt_zNr set_start_point_bot(Wire_gen W) {
//		Profilebene P = W.getProfilebene_gen();
//
//		int b_art = P.getBauteil().getBauteilart();
//		Punkt_zNr Point = null;
//
//		if (b_art == 1) { // Sparcap
//			Point = P.getCoordinates_basis_down().getPunkt_zNr().get(1);
//		} else if (b_art == 2) { // Rib
//			if (W instanceof Wire_Rib) {
//				Point = P.getCoordinates_basis_down().getPunkt_zNr().get(4);			
//			} else if (W instanceof Wire_Ellipse) {
//				Point = P.getCoordinates_basis_down().getPunkt_zNr().get(3);			
//			}
//		} else if (b_art == 3) { // Stringer
//			int stringernummer = P.getBauteil().getBauteilnummer();
//			Bauteil_gen B = P.getBauteil();
//			Point = P.getCoordinates_str_down().getPunkt_zNr().get(stringernummer*2+1);
//
//		} else if (b_art == 4) { // Spar
//			Point = P.getCoordinates_basis_down().getPunkt_zNr().get(1);			
//		}
//
//		return Point;
//	}
//	private Punkt_zNr set_end_point_bot(Wire_gen W) {
//		Profilebene P = W.getProfilebene_gen();
//
//		int b_art = P.getBauteil().getBauteilart();
//		Punkt_zNr Point = null;
//
//		if (b_art == 1) { // Sparcap
//
//			Point = P.getCoordinates_basis_down().getPunkt_zNr().get(0);
//
//		} else if (b_art == 2) { // Rib
//			if (W instanceof Wire_Rib) {
//				Point = P.getCoordinates_basis_down().getPunkt_zNr().get(1);			
//			} else if (W instanceof Wire_Ellipse) {
//				Point = P.getCoordinates_basis_down().getPunkt_zNr().get(2);
//			}
//		} else if (b_art == 3) { // Stringer
//			int stringernummer = P.getBauteil().getBauteilnummer();
//			Bauteil_gen B = P.getBauteil();
//			Point = P.getCoordinates_str_down().getPunkt_zNr().get(stringernummer*2);
//		} else if (b_art == 4) { // Spar
//			Point = P.getCoordinates_basis_down().getPunkt_zNr().get(0);			
//		}
//		
//
//		return Point;
//	}
//
//	private void setValues_up(Wire_gen wire, Punkt_zNr punkt_start, Punkt_zNr punkt_end) {
//
//		NumberQuantity x_start = punkt_start.getX();
//		NumberQuantity x_end = punkt_end.getX();
//		NumberQuantity x_up_start = punkt_start.getX();
//		NumberQuantity x_up_end = punkt_end.getX();
//
//		NumberQuantity z_start = punkt_start.getZ();
//		NumberQuantity z_end = punkt_end.getZ();
//
//		int Nr_start = punkt_start.getNr1();
//		int Nr_end = punkt_end.getNr2();
//
//		int anzpointpolylinetop = Math.abs(Nr_start - Nr_end) + 2 + 1;
//
//		wire.setNr_start_up(Nr_start);
//		wire.setNr_end_up(Nr_end);
//
//		wire.setAnzpointPolylineTop(anzpointpolylinetop);
//		wire.setAnzpointPolyline(anzpointpolylinetop);
//		
//		wire.setX_start(x_start);
//		wire.setX_end(x_end);
//		wire.setX_up_s(x_up_start);
//		wire.setX_up_e(x_up_end);
//
//		wire.setZ_up_s(z_start);
//		wire.setZ_up_e(z_end);
//
//	}
//
//	private void setValues_bot(Wire_gen wire, Punkt_zNr punkt_start, Punkt_zNr punkt_end) {
//		NumberQuantity x_start = punkt_start.getX();
//		NumberQuantity x_end = punkt_end.getX();
//		NumberQuantity x_bot_start = punkt_start.getX();
//		NumberQuantity x_bot_end = punkt_end.getX();
//
//		NumberQuantity z_start = punkt_start.getZ();
//		NumberQuantity z_end = punkt_end.getZ();
//
//
//		wire.setX_bot_s(x_bot_start);
//		wire.setX_bot_e(x_bot_end);
//
//		wire.setZ_bot_s(z_start);
//		wire.setZ_bot_e(z_end);
//
//		int Nr_start = punkt_start.getNr1();
//		int Nr_end = punkt_end.getNr2();
//
//		int anzpointpolylinebot = Math.abs(Nr_start - Nr_end) + 2 + 1;
//
//		wire.setNr_start_bot(Nr_start);
//		wire.setNr_end_bot(Nr_end);
//
//		wire.setAnzpointPolylineBot(anzpointpolylinebot);
//		wire.setAnzpointPolyline(anzpointpolylinebot);
//	}
//
//}