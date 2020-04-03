package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import tec.uom.se.NumberQuantity;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Points_LE_Nase_Rib extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Controlpoint_gen cpoint : getGraph().allInstances(Controlpoint_gen.class)) {
			int is_onPro_flag = cpoint.getIs_onPro_flag();
			if (is_onPro_flag == 1) {
				int zugehoerigerProfilpunktNr = cpoint.getZugehoerigerProfilpunktNr();

				Wire_gen wire = cpoint.getPL_gen().get(0).getWire_gen();
				if (wire instanceof Wire_TypX_Rib_LE) {
					int size = cpoint.getPL_gen().size();
					if (size == 2) {
						PolyLine_gen pl1 = cpoint.getPL_gen().get(0);
						PolyLine_gen pl2 = cpoint.getPL_gen().get(1);
						if ((pl1 instanceof PL_onPro_Rib) & (pl2 instanceof PL_onPro_Rib)) {

							Profil profil = cpoint.getPL_gen_onPro().getWire_gen().getProfilebene_gen().getProfil();

							// get Points Profil spezifisch ///////////////////////////////////////
							int anzpoint = profil.getPoint_Profil().size();

							Point_Profil pointprofil[] = new Point_Profil[anzpoint];

							for (int i = 0; i < anzpoint; i++) {
								pointprofil[i] = (Point_Profil) profil.getPoint_Profil().get(i);
							}
							Profilebene profilebene = cpoint.getPL_gen().get(0).getWire_gen().getProfilebene_gen();
							double x = pointprofil[200].getX().getValue();
							double y = pointprofil[200].getY().getValue();
							double z = pointprofil[200].getZ().getValue();

							cpoint.setX(x);
							cpoint.setY(y);
							cpoint.setZ(z);
							
						}

					}
				}

			}
		}
	}

}