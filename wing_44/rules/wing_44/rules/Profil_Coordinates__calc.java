package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Profil_Coordinates__calc extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Profil profil : getGraph().allInstances(Profil.class)) {

			int is_right = profil.getProfilebene().getIs_right_flag();

			Profilebene profilebene = profil.getProfilebene();

			double laenge_profil = profilebene.getLaenge().getValue();
			double verschiebung_x = profilebene.getXpos().getValue();
			double verschiebung_y = profilebene.getYpos().getValue();
			double verschiebung_z = profilebene.getZpos().getValue();

			Profile_generic profil_generic = profilebene.getBauteil().getBauteilE().getSE().getSegments().getSubsection().getSec()
					.getWing_44().getProfile_generic();

			int dotcount = profil_generic.getDotcount().getValue().intValue();

			int npoint = (dotcount + dotcount - 2);
			int intnpoint = (int) npoint;

			// get gesamtes generic Profil + generic Profilpunkte
			Profilgesamt profil_ges = profil_generic.getProfilgesamt();

			Point_Profil point_gesamt[] = new Point_Profil[npoint];
			double x_generic[] = new double[npoint];
			double y_generic[] = new double[npoint];
			int is_oben_flag[] = new int[npoint];

			for (int i = 0; i < npoint; i++) {
				point_gesamt[i] = (Point_Profil) profil_ges.getPoint_Profil().get(i);
				x_generic[i] = point_gesamt[i].getX().getValue();
				y_generic[i] = point_gesamt[i].getY().getValue();
				is_oben_flag[i] = point_gesamt[i].getIs_oben_flag().intValue();
			}

			// Generic in Koordinaten Umwandeln -> Skalieren(laenge) +
			// Verschieben(verschiebung)
			double x_koord[] = new double[intnpoint];
			double y_koord[] = new double[intnpoint];
			double z_koord[] = new double[intnpoint];

			for (int i = 0; i < intnpoint; i++) {
				x_koord[i] = ((x_generic[i] * laenge_profil) + verschiebung_x * 1000);
				y_koord[i] = -verschiebung_y * 1000;
				z_koord[i] = ((y_generic[i] * laenge_profil) + verschiebung_z * 1000);
			}

			Point_Profil point_koord[] = new Point_Profil[intnpoint];

			for (int i = 0; i < npoint; i++) {

				point_koord[i] = Point_Profil.create();

				point_koord[i].setPunktnummer(i);
				point_koord[i].setIs_profil44_flag(1);
				point_koord[i].setIs_oben_flag(is_oben_flag[i]);

				point_koord[i].setX(x_koord[i]);
				point_koord[i].setY(y_koord[i]);
				point_koord[i].setZ(z_koord[i]);

				profil.getElement().add(point_koord[i]);
				profil.getPoint_Profil().add(point_koord[i]);

			}

			profil.setStart(point_koord[0]);

			for (int i = 0; i < (npoint - 1); i++) {
				point_koord[i].getNextPoint().add(point_koord[i + 1]);

			}
			point_koord[intnpoint - 1].getNextPoint().add(point_koord[0]);

		}

	}

}