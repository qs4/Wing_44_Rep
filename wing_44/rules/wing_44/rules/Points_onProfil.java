package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Points_onProfil extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Controlpoint_gen cpoint : getGraph().allInstances(Controlpoint_gen.class)) {
			int is_onPro_flag = cpoint.getIs_onPro_flag();
			if (is_onPro_flag == 1) {

				Profil profil = cpoint.getPL_gen_onPro().getWire_gen().getProfilebene_gen().getProfil();

				// get Points Profil spezifisch ///////////////////////////////////////
				int anzpoint = profil.getPoint_Profil().size();

				Point_Profil pointprofil[] = new Point_Profil[anzpoint];

				for (int i = 0; i < anzpoint; i++) {
					pointprofil[i] = (Point_Profil) profil.getPoint_Profil().get(i);
				}

				/////////////////////////////////////////////////////////////////////////////////////////////////////
				int zugehoerigerProfilpunktNr = cpoint.getZugehoerigerProfilpunktNr();
				if (zugehoerigerProfilpunktNr != 0) {
					double x = pointprofil[zugehoerigerProfilpunktNr].getX().getValue();
					double y = pointprofil[zugehoerigerProfilpunktNr].getY().getValue();
					double z = pointprofil[zugehoerigerProfilpunktNr].getZ().getValue();

					cpoint.setX(x);
					cpoint.setY(y);
					cpoint.setZ(z);

				}
//				double x = pointprofil[200].getX().getValue();
//				double y = pointprofil[200].getY().getValue();
//				double z = pointprofil[200].getZ().getValue();
//
//				System.out.println( "x:  "+x);
//				System.out.println("y:  " +y);
//				System.out.println("z:  " +z);

			}
		}
	}
}