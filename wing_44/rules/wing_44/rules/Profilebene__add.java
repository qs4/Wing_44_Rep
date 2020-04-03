package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Profilebene__add extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Bauteil_gen bauteil : getGraph().allInstances(Bauteil_gen.class)) {
			
			add_Profilebene_left(bauteil);
			add_Profilebene_right(bauteil);
			
		}
	}

	private void add_Profilebene_right(Bauteil_gen bauteil) {
		Profilebene profil_right = Profilebene.create();
		profil_right.setIs_right_flag(1);
		bauteil.setProfil_right_g(profil_right);
		profil_right.setBauteil(bauteil);		
	}

	private void add_Profilebene_left(Bauteil_gen bauteil) {
		Profilebene profil_left = Profilebene.create();
		profil_left.setIs_right_flag(0);
		bauteil.setProfil_left_g(profil_left);
		profil_left.setBauteil(bauteil);
		

	}

}