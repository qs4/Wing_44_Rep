package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;

import java.util.List;

import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Coordinates_Nr1Nr2__calc extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Coordinates coord : getGraph().allInstances(Coordinates.class)) {

			Point_Profil[] pointprofil = get_Profilpoints(coord);
			calc_NachbarPunkte(pointprofil, coord);
		}
	}

	private Point_Profil[] get_Profilpoints(Coordinates coord) {
		Profil profil = coord.getProfilebene().getProfil();

		int anzpoint = profil.getPoint_Profil().size();

		Point_Profil pointprofil[] = new Point_Profil[anzpoint];

		for (int i = 0; i < anzpoint; i++) {
			pointprofil[i] = (Point_Profil) profil.getPoint_Profil().get(i);
		}
		return pointprofil;
	}

	private void calc_NachbarPunkte(Point_Profil[] pointprofil, Coordinates coord) {
		Profil profil = coord.getProfilebene().getProfil();
		int anzpoint = profil.getPoint_Profil().size();

		int is_up_punkt = coord.getIs_up_flag();
		List<Double> x_str = coord.getX();
		int is_up_profil;
		double x_profil_1;
		double x_profil_2;
		int Nr1 = 0;
		int Nr2 = 0;
		for (int k = 0; k < x_str.size(); k++) {

			double x = x_str.get(k);
			
//			if (x == 0) {
//				Nr1 = 0;
//				Nr2 = 0;
//				coord.getNr1().add(Nr1);
//				coord.getNr2().add(Nr2);
//			} else {
				for (int i = 0; i < anzpoint - 1; i++) {
					x_profil_1 = pointprofil[i].getX().getValue() / 1000;
					x_profil_2 = pointprofil[i + 1].getX().getValue() / 1000;
					is_up_profil = pointprofil[i].getIs_oben_flag();

					if (((x_profil_1 < x && x < x_profil_2) || (x_profil_1 > x && x > x_profil_2))
							&& is_up_profil == is_up_punkt) {
						Nr1 = i;
						Nr2 = i + 1;
						coord.getNr1().add(Nr1);
						coord.getNr2().add(Nr2);
					}
				}
//			}
		}

	}
}