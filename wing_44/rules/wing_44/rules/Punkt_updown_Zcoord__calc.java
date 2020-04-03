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
//public class Punkt_updown_Zcoord__calc extends JavaRule {
//
//	@Override
//	public void execute() throws Exception {
//		for (Punkt_updown punkt_updown : getGraph().allInstances(Punkt_updown.class)) {
//
//			Z_Koordinaten profilkoord = punkt_updown.getZ_Koordinaten();
//			Profilebene profilebene = punkt_updown.getZ_Koordinaten().getProfilebene();
//			Profil profil = profilebene.getProfil();
//
//			// get Points Profil spezifisch ///////////////////////////////////////
//			int anzpoint = profil.getPoint_Profil().size();
//
//			
//			Point_Profil pointprofil[] = new Point_Profil[anzpoint];
//
//			for (int i = 0; i < anzpoint; i++) {
//				pointprofil[i] = (Point_Profil) profil.getPoint_Profil().get(i);
//			}
//
//			int Nr_1 = punkt_updown.getNr1();
//			int Nr_2 = punkt_updown.getNr2();
//			
//			int is_up_flag = punkt_updown.getIs_up_flag();
//			NumberQuantity x = null;
//
//			x = profilkoord.getX();
//
// 
////			System.out.println("is_up_flag:  " + is_up_flag);
////			System.out.println("start:  ");
////
////			System.out.println("Nr_1:  " + Nr_1);
////			System.out.println("Nr_2:  " + Nr_2);
//
//			NumberQuantity z = interpolate(x, Nr_1, Nr_2, pointprofil);
//			punkt_updown.setX(x);
//			punkt_updown.setZ(z);
//		}
//	}
//
//	private NumberQuantity interpolate(NumberQuantity x, int Nr_1, int Nr_2, Point_Profil[] pointprofil) {
//		
//		
//
//		NumberQuantity x1 = pointprofil[Nr_1].getX();
//
//		NumberQuantity z1 = pointprofil[Nr_1].getZ();
//
//		NumberQuantity x2 = pointprofil[Nr_2].getX();
//		NumberQuantity z2 = pointprofil[Nr_2].getZ();
//
//		NumberQuantity z = z1.add(((x.subtract(x1)).divide(x2.subtract(x1)).multiply(z2.subtract(z1))));
//
////		NumberQuantity z = z1 + ((x - x1) / (x2 - x1) * (z2 - z1));
////		System.out.println("x1:  " + x1);
////		System.out.println("x:  " + x);
////
////		System.out.println("x2:  " + x2);
////		System.out.println("z1:  " + z1);
////
////		System.out.println("z:  " + z);
////		System.out.println("z2:  " + z2);
//
//		return z;
//	}
//
//}