//package wing_44.rules;
//
//import de.iils.dc43.core.JavaRule;
//import tec.uom.se.NumberQuantity;
//import wing_44.*;
//
//import static tec.uom.se.quantity.Quantities.*;
//import static de.iils.dc43.core.util.DC43Util.prettyPrint;
//
//@SuppressWarnings("all")
//public class Points_Elli__calc extends JavaRule {
//
//	@Override
//	public void execute() throws Exception {
//		for (Controlpoint_gen cpoint : getGraph().allInstances(Controlpoint_gen.class)) {
//			
//			int is_elli_flag = cpoint.getIs_Elli_tb_flag();
//
//			if (is_elli_flag==1) {
//
//				int Punktnummer = 0;
//
//				Profilebene profilebene = cpoint.getPL_Elli().getWire_gen().getProfilebene_gen();
//
//				NumberQuantity xpos = profilebene.getXpos();
//				NumberQuantity ypos = profilebene.getYpos();
//				NumberQuantity zpos = profilebene.getZpos();
//
//				NumberQuantity x_1 = null;
//				NumberQuantity x_4 = null;
//				NumberQuantity x_2 = null;
//				NumberQuantity x_3 = null;
//
//				NumberQuantity z_1 = null;
//				NumberQuantity z_4 = null;
//
//				if (is_polylinenummer_0 == 1 && is_ellipse == 1) {
//
//					Z_Koordinaten profilkoord_s = profilebene.getProfilpunkt_m_s();
//					Punkt_updown punktup_s = profilkoord_s.getPunkt_up();
//
//					Z_Koordinaten profilkoord_e = profilebene.getProfilpunkt_m_e();
//					Punkt_updown punktup_e = profilkoord_e.getPunkt_up();
//
//					x_1 = punktup_s.getX();
//					x_4 = punktup_e.getX();
//					x_2 = x_1.add((x_4.subtract(x_1)).divide(3));
//					x_3 = x_2.add((x_4.subtract(x_1)).divide(3));
//
//					z_1 = punktup_s.getZ();
//					z_4 = punktup_e.getZ();
//					NumberQuantity offset = (x_4.subtract(x_1)).divide(6);
//
//					if (z_1.getValue() > z_4.getValue()) {
//						z_4 = z_4.subtract(offset);
//						z_1 = z_4;
//
//					} else if (z_1.getValue() < z_4.getValue()) {
//						z_1 = z_1.subtract(offset);
//						z_4 = z_1;
//					}
//
//					Punktnummer = cpoint.getIs_point_nr_0();
//
//				} else if (is_polylinenummer_2 == 1 && is_ellipse == 1) {
//
//					Z_Koordinaten profilkoord_s = profilebene.getProfilpunkt_m_s();
//					Punkt_updown punktdown_s = profilkoord_s.getPunkt_down();
//
//					Z_Koordinaten profilkoord_e = profilebene.getProfilpunkt_m_e();
//					Punkt_updown punktdown_e = profilkoord_e.getPunkt_down();
//
//					x_1 = punktdown_e.getX();
//					x_4 = punktdown_s.getX();
//					x_2 = x_1.subtract((x_1.subtract(x_4)).divide(3));
//					x_3 = x_2.subtract((x_1.subtract(x_4)).divide(3));
//
//					z_1 = punktdown_e.getZ();
//					z_4 = punktdown_s.getZ();
//					NumberQuantity offset = (x_1.subtract(x_4)).divide(6);
//
//					if (z_1.getValue() < z_4.getValue()) {
//						z_4 = z_4.add(offset);
//						z_1 = z_4;
//
//					} else if (z_1.getValue() > z_4.getValue()) {
//						z_1 = z_1.add(offset);
//						z_4 = z_1;
//					}
//
//					Punktnummer = cpoint.getIs_point_nr_2();
//
//				}
//				NumberQuantity x = null;
//				NumberQuantity y = null;
//				NumberQuantity z = null;
//
//				if (Punktnummer == 1) {
//					x = x_1;
//					y = ypos.multiply(-1);
//					z = z_1;
//
//				} else if (Punktnummer == 2) {
//					x = x_2;
//					y = ypos.multiply(-1);
//					z = z_1;
//				} else if (Punktnummer == 3) {
//					x = x_3;
//					y = ypos.multiply(-1);
//					z = z_1;
//				} else if (Punktnummer == 4) {
//
//					x = x_4;
//					y = ypos.multiply(-1);
//					z = z_1;
//				}
//
//				cpoint.setX(x);
//				cpoint.setY(y);
//				cpoint.setZ(z);
//			}
//		}
//	}
//
//}