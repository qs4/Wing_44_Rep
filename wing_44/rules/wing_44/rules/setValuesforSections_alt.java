//package wing_44.rules;
//
//import de.iils.dc43.core.JavaRule;
//import wing_44.*;
//
//import static tec.uom.se.quantity.Quantities.*;
//import static de.iils.dc43.core.util.DC43Util.prettyPrint;
//
//@SuppressWarnings("all")
//public class setValuesforSections_alt extends JavaRule {
//
//	@Override
//	public void execute() throws Exception {
//	
//	for (Subsections subsection : getGraph().allInstances(Subsections.class)) {
//			
//			int anzahlsparcaps = subsection.getStructuralElement1().getSparcaps().getSparcap1().size();
//			int upper = 1;
//			int lower = 1;
//			for (int f = 0; f < anzahlsparcaps; f++) {
//
//			Sparcap sparcap = subsection.getStructuralElement1().getSparcaps().getSparcap1().get(f);
//			double height_1 = sparcap.getHeight_1().getValue();
//			double height_2 = sparcap.getHeight_2().getValue();
//			double width_1 = sparcap.getWidth_1().getValue();
//			double width_2 = sparcap.getWidth_2().getValue();
//			double xpos_root;
//			double xpos_tip;
//
//
//			double m_prozent = sparcap.getSparcaps1().getStructuralElement().getSubsections().getM_prozent().getValue();
//			double n_prozent = sparcap.getSparcaps1().getStructuralElement().getSubsections().getN_prozent().getValue();
//			double o_prozent = sparcap.getSparcaps1().getStructuralElement().getSubsections().getO_prozent().getValue();
//
//			double laenge_links = sparcap.getSparcaps1().getStructuralElement().getSubsections().getLaenge_links().getValue();
//			double laenge_rechts = sparcap.getSparcaps1().getStructuralElement().getSubsections().getLaenge_rechts().getValue();			
//		
//			double verschiebung_x_links = sparcap.getSparcaps1().getStructuralElement().getSubsections().getVerschiebung_x_links().getValue();
//			double verschiebung_y_links = sparcap.getSparcaps1().getStructuralElement().getSubsections().getVerschiebung_y_links().getValue();
//			double verschiebung_x_rechts = sparcap.getSparcaps1().getStructuralElement().getSubsections().getVerschiebung_x_rechts().getValue();
//			double verschiebung_y_rechts = sparcap.getSparcaps1().getStructuralElement().getSubsections().getVerschiebung_y_rechts().getValue();
//
//			double prozent = 0;
//
//			int upperflag = sparcap.getIs_up_flag().intValue();
//			double z_base = 0;
//
//			if (upperflag == 1) {
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
//			} else if (upperflag == 0) {
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
//			xpos_root = laenge_links * prozent + verschiebung_x_links;
//			xpos_tip = laenge_rechts * prozent + verschiebung_x_rechts;
//			double sparnummer = sparcap.getSparnummer();
////			System.out.println("xpos_root"+xpos_root);
////			System.out.println("xpos_tip"+xpos_tip);
//
//
//			// Y
//			double z_0 = z_base;
//			double z_1 = z_base - height_2 + height_1;
//			double z_2 = z_base - height_2;
//
//
//			int anzlofts = sparcap.getAnzlofts();
//
//			int anzpolylines = sparcap.getLoft_sparcap().getAnzpolyline();
//			int anzwire = sparcap.getLoft_sparcap().getAnzwire();
//
//			int anzpoints = sparcap.getLoft_sparcap().getWire_Sparcap().get(0).getPolyLine_Sparcap_bot().get(0)
//					.getAnzpoint_bot();
//
//
//			Controlpoint_Spline_Sparcap pointsparcap[] = new Controlpoint_Spline_Sparcap[anzpoints];
////			Point_PolyLine_Sparcap pointsparcap[] = new Point_PolyLine_Sparcap[anzpoints];
//
////			Loft_Sparcap loft = sparcap.getLoft_sparcap();
//
////			for (Loft_Sparcap loft : lofts) {
//				for (int p = 0; p < anzwire; p++) {
//					for (int r = 0; r < anzpolylines; r++) {
//						for (int i = 0; i < anzpoints; i++) {
//							pointsparcap[i] = (Controlpoint_Spline_Sparcap) sparcap.getLoft_sparcap().getWire_Sparcap().get(p).getPolyLine_Sparcap_bot().get(r)
//									.getControlpoint_Spline_Sparcap().get(i);
////							pointsparcap[i] = (Point_PolyLine_Sparcap) loft.getWire_Sparcap().get(p)
////									.getPolyLine_Sparcap().get(r).getPoint_sparcap().get(i);
//
//							int o = (int) sparcap.getLoft_sparcap().getWire_Sparcap().get(p).getPolyLine_Sparcap_bot().get(r).getControlpoint_Spline_Sparcap().get(i).getPunktnummer()
//									.intValue();
////							int o = (int) loft.getWire_Sparcap().get(p).getPolyLine_Sparcap().get(r)
////									.getPoint_sparcap().get(i).getPunktnummer().intValue();
//
////							 set Y
//							if (p == 0) {
//								double y_0 = verschiebung_y_links;
//								pointsparcap[i].setY(-y_0);
//							} else {
//								double y_0 = verschiebung_y_rechts;
//								pointsparcap[i].setY(-y_0);
//							}
//
//						}
//
//						if (p == 0) {
//							double x_0 = xpos_root;
//
//							double x_1 = x_0 + (width_2 - width_1) / 2;
//							double x_2 = x_0 + (width_2 - width_1) / 2 + width_1;
//							double x_3 = x_0 + width_2;
//
//							// set X
//							pointsparcap[0].setX(x_0);
//							pointsparcap[1].setX(x_0);
//
//							pointsparcap[2].setX(x_1);
//							pointsparcap[3].setX(x_1);
//
//							pointsparcap[4].setX(x_2);
//							pointsparcap[5].setX(x_2);
//
//							pointsparcap[6].setX(x_3);
//							pointsparcap[7].setX(x_3);
//						} else {
//							double x_0 = xpos_tip;
//
//							double x_1 = x_0 + (width_2 - width_1) / 2;
//							double x_2 = x_0 + (width_2 - width_1) / 2 + width_1;
//							double x_3 = x_0 + width_2;
//							// set X
//							pointsparcap[0].setX(x_0);
//							pointsparcap[1].setX(x_0);
//
//							pointsparcap[2].setX(x_1);
//							pointsparcap[3].setX(x_1);
//
//							pointsparcap[4].setX(x_2);
//							pointsparcap[5].setX(x_2);
//
//							pointsparcap[6].setX(x_3);
//							pointsparcap[7].setX(x_3);
//						}
//
//						// set Z
//						pointsparcap[0].setZ(z_0);
//						pointsparcap[7].setZ(z_0);
//
//						pointsparcap[1].setZ(z_1);
//						pointsparcap[2].setZ(z_1);
//						pointsparcap[5].setZ(z_1);
//						pointsparcap[6].setZ(z_1);
//
//						pointsparcap[3].setZ(z_2);
//						pointsparcap[4].setZ(z_2);
//					}
//				}
////			}
//		}
//	}
//	}
//
//}