//package wing_44.rules;
//
//import de.iils.dc43.core.JavaRule;
//import wing_44.*;
//
//import static tec.uom.se.quantity.Quantities.*;
//import static de.iils.dc43.core.util.DC43Util.prettyPrint;
//
//@SuppressWarnings("all")
//public class Punkt_updown__add extends JavaRule {
//
//	@Override
//	public void execute() throws Exception {
//		for (Z_Koordinaten z_koord : getGraph().allInstances(Z_Koordinaten.class)) {
//			
//
//			Punkt_updown punkt_up = Punkt_updown.create();
//			punkt_up.setIs_up_flag(1);
//			punkt_up.setZ_Koordinaten(z_koord);
//			z_koord.setPunkt_up(punkt_up);
//
//			Punkt_updown punkt_down = Punkt_updown.create();
//			punkt_down.setIs_up_flag(0);
//			punkt_down.setZ_Koordinaten(z_koord);
//			z_koord.setPunkt_down (punkt_down);
//			
//		}
//	}
//
//}