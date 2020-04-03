//package wing_44.rules;
//
//import de.iils.dc43.core.JavaRule;
//import wing_44.*;
//
//import static tec.uom.se.quantity.Quantities.*;
//import static de.iils.dc43.core.util.DC43Util.prettyPrint;
//
//@SuppressWarnings("all")
//public class Rule1 extends JavaRule {
//
//	@Override
//	public void execute() throws Exception {
//		int dotcount = 50;
//		for (Profiles_44 profile : getGraph().allInstances(Profiles_44.class)) {
//
//			
//			double M = 6;
//			double P = 4;
//			double XX = 12;
//			
//			int dotcount1 = 10;
//			double laenge_profil = 10; //Länge_Profil -> l_rechts / l_links -> li / lk / la 
//			double verschiebung_x = 0; //Verschiebung_x -> a / k   -> berechnen aus pfeilung spannweite etc
//			double verschiebung_y =4; // Verschiebung_y -> // y_mac -> berechnen aus  spannweite etc
//			double verschiebung_z =0; // Verschiebung_z -> hoehe
//
//			// NACA4 Profil
//			double m = M / 100;
//			double p = P / 10;
//			double t = XX / 100;
//
//			double panelcount = dotcount1 - 1;
//			double panellength = 1 / panelcount;
//
//			double npoint = (dotcount1 + dotcount1 - 2);
//			int intnpoint = (int) npoint;
//
//			// xu / yu -> Obere Profilseite
//			Point_Profil Point_oben[] = new Point_Profil[dotcount1];
//			double xu[] = new double[dotcount1];
//			double yu[] = new double[dotcount1];
//
//			// xl / yl -> Untere Profilseite
//			Point_Profil Point_unten[] = new Point_Profil[dotcount1];
//			double xl[] = new double[dotcount1];
//			double yl[] = new double[dotcount1];
//
//			// Profildicke
//			double yt[] = new double[dotcount1];
//
//			// Skelettlinie
//			double xc[] = new double[dotcount1];
//			double yc[] = new double[dotcount1];
//
//			// dy_c/dx_c
//			double gc[] = new double[dotcount1];
//
//			// thetha
//			double sc[] = new double[dotcount1];
//
//			// Point_XY
//			Point_Profil Point_xy[] = new Point_Profil[intnpoint];
//			double x[] = new double[dotcount1 + dotcount1 - 2];
//			double y[] = new double[dotcount1 + dotcount1 - 2];
//
//			int i = 1;
//			xc[0] = 0;
//
//			while (i < dotcount1) {
//				xc[i] = i * panellength;
//				i = i + 1;
//			}
//
//			if (m != 0) {
//				for (i = 0; i < dotcount1; i++) {
//					if (xc[i] <= p) {
//						yc[i] = (m / (p * p)) * ((2 * p * xc[i]) - (xc[i] * xc[i]));
//					}
//					if (xc[i] > p) {
//						yc[i] = (m / ((1 - p) * (1 - p))) * (1 - (2 * p) + (2 * p * xc[i]) - (xc[i] * xc[i]));
//					}
//
//					gc[i] = (m / (p * p)) * (2 * p + 2 * xc[i]);
//
//				}
//			}
//
//			for (i = 0; i < dotcount1; i++) {
//				sc[i] = Math.atan(gc[i]);
//			}
//
//			for (i = 0; i < dotcount1; i++) {
//				yt[i] = 5 * t * ((0.29690 * Math.pow(xc[i], 0.5)) - (0.12600 * xc[i]) - (0.35160 * Math.pow(xc[i], 2))
//						+ (0.28430 * Math.pow(xc[i], 3)) - (0.10150 * Math.pow(xc[i], 4)));
//			}
//
//			for (i = 0; i < dotcount1; i++) {
//				xu[i] = xc[i] - yt[i] * Math.sin(sc[i]);
//				yu[i] = yc[i] + yt[i] * Math.cos(sc[i]);
//
//				xl[i] = xc[i] + yt[i] * Math.sin(sc[i]);
//				yl[i] = yc[i] - yt[i] * Math.cos(sc[i]);
//
//			}
//			xu[0] = 0;
//			xu[dotcount1 - 1] = 1;
//			xl[0] = 0;
//			xl[dotcount1 - 1] = 1;
//
//			for (i = 0; i < dotcount1; i++) {
////				x[i] = -xu[dotcount - 1 - i];
//				x[i] = xu[dotcount1 - 1 - i];
//				y[i] = yu[dotcount1 - 1 - i];
//			}
//			for (i = 0; i < (dotcount1 - 2); i++) {
////				x[dotcount + i] = -xl[i + 1];
//				x[dotcount1 + i] = xl[i + 1];
//				y[dotcount1 + i] = yl[i + 1];
//			}
//
//
////			// Profilkoordinaten
////			double xi[] = new double[intnpoint];
////			double yi[] = new double[intnpoint];
////			double zi[] = new double[intnpoint];
////
//////							xmitte[i] = (x[i] * li) - rmitte;
////							ymitte[i] = 0.0;
////							zmitte[i] = (y[i] * li) + hoehe;
//
//			System.out.println("Länge_Profil: " + laenge_profil);
//			
//			System.out.println("Verschiebung_x: " + verschiebung_x);
//			System.out.println("Verschiebung_y: " + verschiebung_y);
//			System.out.println("Verschiebung_z: " + verschiebung_z);
//
//		
//			
////			for (i = 0; i < intnpoint; i++) {
////				xi[i] = (x[i] * laenge_profil)+verschiebung_x;
////				yi[i] = -verschiebung_y;
////				zi[i] = (y[i] * laenge_profil) + verschiebung_z;
////			}
////			
////			
////			Profiles_44 profil = Profiles_44.create();
////			
////			Point_Profil pointi[] = new Point_Profil[intnpoint];
//////	
////			for (i = 0; i < npoint; i++) {
////				pointi[i] = Point_Profil.create();
////		
////				pointi[i].setX(xi[i]);
////				pointi[i].setY(yi[i]);
////				pointi[i].setZ(zi[i]);
////
////				profil_i.getElement().add(pointi[i]);
////				profil_i.getPoint_Profil().add(pointi[i]);
//// 
////			}
////
////			profil_i.setStart(pointi[0]);
//
////			for (i = 0; i < (npoint - 1); i++) {
////				pointi[i].getNextPoint().add(pointi[i + 1]);
//
////			}
////			pointi[intnpoint - 1].getNextPoint().add(pointi[0]);
////			pointk[intnpoint - 1].getNextPoint().add(pointk[0]);
////			pointa[intnpoint - 1].getNextPoint().add(pointa[0]);
//
//
////
//		}
//
//	}
//
//}