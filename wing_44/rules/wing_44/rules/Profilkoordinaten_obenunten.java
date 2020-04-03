//package wing_44.rules;
//
//import de.iils.dc43.core.JavaRule;
//import wing_44.*;
//
//import static tec.uom.se.quantity.Quantities.*;
//import static de.iils.dc43.core.util.DC43Util.prettyPrint;
//
//@SuppressWarnings("all")
//public class Profilkoordinaten_obenunten extends JavaRule {
//
//	@Override
//	public void execute() throws Exception {
//		for (Profiles_44 profile : getGraph().allInstances(Profiles_44.class)) {
//
//			int dotcount = 10;
//			double laenge_profil = 10; // Länge_Profil -> l_rechts / l_links -> li / lk / la
//			double verschiebung_x = 0; // Verschiebung_x -> a / k -> berechnen aus pfeilung spannweite etc
//			double verschiebung_y = 4; // Verschiebung_y -> // y_mac -> berechnen aus spannweite etc
//			double verschiebung_z = 0; // Verschiebung_z -> hoehe
//
//			System.out.println("Länge_Profil: " + laenge_profil);
//
//			System.out.println("Verschiebung_x: " + verschiebung_x);
//			System.out.println("Verschiebung_y: " + verschiebung_y);
//			System.out.println("Verschiebung_z: " + verschiebung_z);
//
//			// get generic profiles:
//			int sectionnummer = profile.getLoft_Profil().getSubsections().getSectionnummer().getValue().intValue();
//
//			System.out.println("sectionnummer: " + sectionnummer);
//
//			
////			int oben = profile.getLoft_Profil().getSubsections().getSections().getWing_44().getProfile_generic()
////						.getProfilobenunten().get(0).getOben().getValue().intValue();
////			
////				int obenindex = 0;
////				int untenindex = 1;
////				if (oben == 1) {
////					obenindex = 0;
////					untenindex = 1;
////				} else if (oben == 0) {
////					obenindex = 1;
////					untenindex = 0;
////				}
//
////				Profilobenunten Profiloben = profile.getLoft_Profil().getSubsections().getSections().getWing_44()
////						.getProfile_generic().getProfilobenunten().get(obenindex);
//			
//			Profilgesamt Profilgesamt = profile.getLoft_Profil().getSubsections().getSections().getWing_44()
//					.getProfile_generic().getProfilgesamt();
//			
////				Profilobenunten Profilunten = profile.getLoft_Profil().getSubsections().getSections().getWing_44()
////						.getProfile_generic().getProfilobenunten().get(untenindex);
////
////				
////				System.out.println("Profiloben: " + Profiloben);
////				System.out.println("Profilunten: " + Profilunten);
//
//				double x[] = new double[dotcount + dotcount - 2];
//				double y[] = new double[dotcount + dotcount - 2];
//				
////// wenn oben unten getrennt betrachtet:
////				double xo[] = new double[dotcount];
////				double yo[] = new double[dotcount];
////				double xu[] = new double[dotcount];
////				double yu[] = new double[dotcount];
////				
////				Point_Profil Point_o[] = new Point_Profil[dotcount];
////				Point_Profil Point_u[] = new Point_Profil[dotcount];
////
////				for (int i = 0; i < dotcount; i++) {
////					Point_o[i] = Profiloben.getPoint_Profil().get(i);
////					xo[i] = Point_o[i].getX().getValue();
////					yo[i] = Point_o[i].getY().getValue();
////
////					Point_u[i] = Profiloben.getPoint_Profil().get(i);
////					xu[i] = Point_u[i].getX().getValue();
////					yu[i] = Point_u[i].getY().getValue();
////
////				}
//	
//			
//		
//					
//					double npoint = (dotcount + dotcount - 2);
//
//					int intnpoint = (int) npoint;
//					// Profilkoordinaten
//					double xi[] = new double[intnpoint];
//					double yi[] = new double[intnpoint];
//					double zi[] = new double[intnpoint];
//
//					for (int i = 0; i < intnpoint; i++) {
//						xi[i] = (x[i] * laenge_profil) + verschiebung_x;
//						yi[i] = -verschiebung_y;
//						zi[i] = (y[i] * laenge_profil) + verschiebung_z;
//					}
//
//					Point_Profil pointi[] = new Point_Profil[intnpoint];
//					//
//					for (int i = 0; i < npoint; i++) {
//
//						pointi[i] = Point_Profil.create();
//						
//						pointi[i].setPunktnummer(i);
//
//						pointi[i].setX(xi[i]);
//						pointi[i].setY(yi[i]);
//						pointi[i].setZ(zi[i]);
//
//						profile.getElement().add(pointi[i]);
//						profile.getPoint_Profil().add(pointi[i]);
//
//					}
//
//					profile.setStart(pointi[0]);
//
//					for (int i = 0; i < (npoint - 1); i++) {
//						pointi[i].getNextPoint().add(pointi[i + 1]);
//
//					}
//					pointi[intnpoint - 1].getNextPoint().add(pointi[0]);
//
//		}
//	}
//
//}