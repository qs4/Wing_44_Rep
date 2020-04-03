package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;
import static tec.uom.se.quantity.Quantities.*;

import java.util.Enumeration;

import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class add_Bauteil2Bauteils extends JavaRule {

	@Override
	public void execute() throws Exception {
		
		
	
//		for (Bauteils bauteils : getGraph().allInstances(Bauteils.class) {
//			
//		}
//		for (Bauteils bauteils : getGraph().allInstances(Bauteil.class)) {
//			int anzbauteils= bauteils.getStructuralElement().getSubsections().getAnzahlbauteil();
//																			--> getAnzahlSpars
//																			--> getAnzahlRibs
//			
//			for (i = 0; i < anzbauteils; i++) {
// 			bauteil = Stringlist {spar, rib, ...}
//			Bauteil = Stringlist {Spar, ribs}
//		
//				for (Bauteils bauteils : getGraph().allInstances(Bauteil.class)) {
//					Bauteil bauteil[] = new Bauteil[anzbauteil];
//					int i = 0;
//			
//					// add Bauteils
//					for (i = 0; i < anzbauteils; i++) {
//
//					bauteil[i] = Bauteil.create();
//					bauteil[i].setBauteilNummer(i);
//		
//					bauteils.getRib().add(bauteil[i]);
//					bauteil[i].setRibs(bauteils);
//					}
//				}
//			}
//		}
		
		
		
		for (StructuralElement structural : getGraph().allInstances(StructuralElement.class)) {
			
			for (int i = 1; i < 2; i++) {
				Bauteilliste.get(i);
				System.out.println();

			}

		}
		
		
//		for (Tragfluegel tragfluegel : getGraph().allInstances(Tragfluegel.class)) {
//
//			double hoehe = 0.0;
////			if (tragfluegel.getFluegelhoehe() == Fluegelhoehe.Hochdecker) {
////				hoehe = rumpf.getDeff() / 4;
////			}
////			if (tragfluegel.getFluegelhoehe() == Fluegelhoehe.Tiefdecker) {
////				hoehe = -rumpf.getDeff() / 4;
////			}
////			if (tragfluegel.getFluegelhoehe() == Fluegelhoehe.Mitteldecker) {
////				hoehe = 0;
////			}
//			
//	}
	}
}