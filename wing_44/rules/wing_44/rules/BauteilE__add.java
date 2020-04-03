package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.publication.TopologyElement;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class BauteilE__add extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (StructuralElement SE : getGraph().allInstances(StructuralElement.class)) {

			if (SE.getIs_spars() == 1) {
				SparElement sparE = SparElement.create();
				sparE.setSE(SE);
				SE.setSparE(sparE);
				SE.getSub().add(sparE);
			}

			if (SE.getIs_spars() == 1) {

				SparcapElement sparcapE = SparcapElement.create();
				sparcapE.setSE(SE);
				SE.setSCE(sparcapE);
				SE.getSub().add(sparcapE);
			}

			if (SE.getIs_ribs() == 1) {
				RibElement ribE = RibElement.create();
				ribE.setSE(SE);
				SE.setRibE(ribE);
				SE.getSub().add(ribE);
			}
			if (SE.getIs_stringers() == 1) {
				StringerElement stringerE = StringerElement.create();
				stringerE.setSE(SE);
				SE.setStringerE(stringerE);
				SE.getSub().add(stringerE);
			}
			
			if (SE.getIs_skin() == 1) {
				SkinElement skinE = SkinElement.create();
				skinE.setSE(SE);
				SE.setSkinE(skinE);
				SE.getSub().add(skinE);
			}
		}
	}
}