//package wing_44.rules;
//
//import de.iils.dc43.core.JavaRule;
//import wing_44.*;
//
//import static tec.uom.se.quantity.Quantities.*;
//import static de.iils.dc43.core.util.DC43Util.prettyPrint;
//
//@SuppressWarnings("all")
//public class Punkt_updown_Nachbarn__calc extends JavaRule {
//
//	@Override
//	public void execute() throws Exception {
//		for (Punkt_updown punkt_updown : getGraph().allInstances(Punkt_updown.class)) {
//
//			Profil profil = punkt_updown.getZ_Koordinaten().getProfilebene().getProfil();
//			Z_Koordinaten profilkoord = punkt_updown.getZ_Koordinaten();
//
//			int anzpoint = profil.getPoint_Profil().size();
//
//			Point_Profil pointprofil[] = new Point_Profil[anzpoint];
//
//			for (int i = 0; i < anzpoint; i++) {
//				pointprofil[i] = (Point_Profil) profil.getPoint_Profil().get(i);
//			}
//
//			calc_NachbarPunkte(pointprofil, profilkoord, punkt_updown, anzpoint);
//
//		}
//	}
//
//	private void calc_NachbarPunkte(Point_Profil[] pointprofil, Z_Koordinaten profilkoord, Punkt_updown punkt_updown,
//			int anzpoint) {
//
//		double x = profilkoord.getX().getValue();
//		int is_up_profil;
//		int is_up_punkt = punkt_updown.getIs_up_flag();
//		double x_profil_1;
//		double x_profil_2;
//		int Nr1 = 0;
//		int Nr2 = 0;
//
//		for (int i = 0; i < anzpoint - 1; i++) {
//			x_profil_1 = pointprofil[i].getX().getValue() / 1000;
//			x_profil_2 = pointprofil[i + 1].getX().getValue() / 1000;
//			is_up_profil = pointprofil[i].getIs_oben_flag();
//
////			System.out.println("x_profil_1:  "+x_profil_1);
////			System.out.println("x_profil_2:  "+x_profil_2);
////			System.out.println("x:  "+x);
//
//			if (((x_profil_1 < x && x < x_profil_2) || (x_profil_1 > x && x > x_profil_2))
//					&& is_up_profil == is_up_punkt) {
//				Nr1 = i;
//				Nr2 = i + 1;
//				punkt_updown.setNr1(Nr1);
//				punkt_updown.setNr2(Nr2);
////					System.out.println("öööööööööööö       "+Nr1);
////					System.out.println(Nr2);
//			}
//
//		}
//
//	}
//}