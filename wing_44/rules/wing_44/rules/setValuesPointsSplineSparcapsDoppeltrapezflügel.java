//package wing_44.rules;
//
//import de.iils.dc43.core.JavaRule;
//import wing_44.*;
//
//import static tec.uom.se.quantity.Quantities.*;
//
//import java.util.List;
//
//import static de.iils.dc43.core.util.DC43Util.prettyPrint;
//
//@SuppressWarnings("all")
//public class setValuesPointsSplineSparcap extends JavaRule {
//
//	@Override
//	public void execute() throws Exception {
//		int upper = 1;
//		int lower = 1;
//		for (Sparcap sparcap : getGraph().allInstances(Sparcap.class)) {
//
//			double height_1 = sparcap.getHeight_1().getValue();
//			double height_2 = sparcap.getHeight_2().getValue();
//			double width_1 = sparcap.getWidth_1().getValue();
//			double width_2 = sparcap.getWidth_2().getValue();
//			double xpos_root = sparcap.getXposonprofil().getValue();
//			double xpos_tip;
//
//			double m_prozent = sparcap.getSparcaps1().getStructuralElement().getWing_44_structuralelement()
//					.getSections().getSection1().getM_prozent().getValue();
//			double n_prozent = sparcap.getSparcaps1().getStructuralElement().getWing_44_structuralelement()
//					.getSections().getSection1().getN_prozent().getValue();
//			double o_prozent = sparcap.getSparcaps1().getStructuralElement().getWing_44_structuralelement()
//					.getSections().getSection1().getO_prozent().getValue();
//
//			double li = sparcap.getSparcaps1().getStructuralElement().getWing_44_structuralelement().getLi().getValue();
//			double lk = sparcap.getSparcaps1().getStructuralElement().getWing_44_structuralelement().getMAC()
//					.getValue();
//			double pfeilung = sparcap.getSparcaps1().getStructuralElement().getWing_44_structuralelement().getPfeilung()
//					.getValue();
//			double ymac = sparcap.getSparcaps1().getStructuralElement().getWing_44_structuralelement().getYMAC()
//					.getValue();
//
//			double k = Math.sin(Math.toRadians(pfeilung)) * ymac + (li / 4) - (lk / 4) - 5;// -5
//
//			double prozent = 0;
//
//			double upperflag = sparcap.getUpperSparcap().getValue();
//			double z_base = 0;
//
//			if (upperflag == 1.0) {
//				z_base = 0;
//				if (upper == 1) {
//					prozent = m_prozent;
//				} else if (upper == 2) {
//					prozent = n_prozent;
//
//				} else {
//					prozent = o_prozent;
//				}
//				upper++;
//
//			} else if (upperflag == 0.0) {
//				z_base = -3.0;
//				if (lower == 1) {
//					prozent = m_prozent;
//				} else if (lower == 2) {
//					prozent = n_prozent;
//
//				} else {
//					prozent = o_prozent;
//				}
//				lower++;
//			}
//
//			xpos_root = li * prozent;
//			System.out.println("xpos_root " + xpos_root);
//			xpos_tip = lk * prozent + k;
//
////			double y_0 = 0;
//
//			// Y
//			double z_0 = z_base;
//			double z_1 = z_base - height_2 + height_1;
//			double z_2 = z_base - height_2;
//
//			// X
//
//			int anzlofts = sparcap.getAnzlofts();
//			int anzwire = sparcap.getLoft_sparcap().get(0).getAnzwire();
//			int anzsplines = sparcap.getLoft_sparcap().get(0).getAnzspline();
//			int anzpoints = sparcap.getLoft_sparcap().get(0).getWire_Sparcap().get(0).getSpline_Sparcap().get(0)
//					.getAnzpoint();
//
//			Controlpoint_Spline_Sparcap controlpointsparcap[] = new Controlpoint_Spline_Sparcap[anzpoints];
//
//			List<Loft_Sparcap> lofts = sparcap.getLoft_sparcap();
//
//			for (Loft_Sparcap loft : lofts) {
//				for (int p = 0; p < anzwire; p++) {
//					for (int r = 0; r < anzsplines; r++) {
//						for (int i = 0; i < anzpoints; i++) {
//						
//							controlpointsparcap[i] = (Controlpoint_Spline_Sparcap) loft.getWire_Sparcap().get(p)
//									.getSpline_Sparcap().get(r).getControlpoint_Spline_Sparcap().get(i);
//
////							 set Y
//							if (p == 0) {
//								double y_0 = 0.0;
//								controlpointsparcap[i].setY(y_0);
//							} else {
//								double y_0 = ymac;
//								controlpointsparcap[i].setY(-y_0);
//							}
////
//						}
//
//						if (p == 0) {
//							double x_0 = xpos_root;
//							double x_3 = x_0 + width_2;
//////							// set X
//							controlpointsparcap[0].setX(x_0);
//							controlpointsparcap[anzpoints - 1].setX(x_3);
//						} else {
//							double x_0 = xpos_tip;
//							double x_3 = x_0 + width_2;
//////							// set X
//							controlpointsparcap[0].setX(x_0);
//							controlpointsparcap[anzpoints - 1].setX(x_3);
//						}
////
////						// set Z
//						controlpointsparcap[0].setZ(z_0); // Punkt 0 -> siehe PolyLine Sparcap
//						controlpointsparcap[anzpoints - 1].setZ(z_0); // Punkt 7 -> siehe PolyLine Sparcap
//
//					}
//				}
//			}
//		}
//	}
//
//}