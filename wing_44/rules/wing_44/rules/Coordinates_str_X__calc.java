package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;

import java.util.List;

import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Coordinates_str_X__calc extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Coordinates_Stringer coord_str : getGraph().allInstances(Coordinates_Stringer.class)) {

			Profil profil = coord_str.getProfilebene().getProfil();
			Profilebene profilebene = coord_str.getProfilebene();

			int anzpoint = profil.getPoint_Profil().size();

			Point_Profil pointprofil[] = new Point_Profil[anzpoint];

			for (int i = 0; i < anzpoint; i++) {
				pointprofil[i] = (Point_Profil) profil.getPoint_Profil().get(i);
			}

			calc_x_stringer(coord_str);

		}
	}

	private void calc_x_stringer(Coordinates coord_str) {

		int subsegmentnr = coord_str.getProfilebene().getBauteil().getSubsegmentnummer();

		StructuralElement SE = coord_str.getProfilebene().getBauteil().getBauteilE().getSE();
		int anzstringer = 0;
		double stringerwidth = 0;
		if (subsegmentnr == 1) {
			// unterschiedlche stringerwidths möglich (theorethisch)!
			anzstringer = SE.getAnzstringertyp1();
//			System.out.println("anzstringer:  " + anzstringer);

			stringerwidth = SE.getStringerE().getWidth().getValue();
//			System.out.println("stringerwidth:  " + stringerwidth);

			// wenn Subsegmente eingeführt!!
//			double stringerabstand1 = profilebene.getBauteil().getBauteilE().getSE().getStringerE().getStringerabstand1_Prozent().getValue();
		} else if (subsegmentnr == 2) {
			anzstringer = SE.getAnzstringertyp2();
//			System.out.println("anzstringer:  " + anzstringer);

			stringerwidth = SE.getStringerE().getWidth().getValue();
//			System.out.println("stringerwidth:  " + stringerwidth);

			// wenn Subsegmente eingeführt!!
//			double stringerabstand1 = profilebene.getBauteil().getBauteilE().getSE().getStringerE().getStringerabstand1_Prozent().getValue();

		}

		// in subsegment rein!
		Coordinates_Basis coord_b = (Coordinates_Basis) coord_str.getProfilebene().getCoordinates_basis_up();
		int size_x = coord_b.getX().size();
		double x_s2 = coord_b.getX().get(1);
		double x_e = coord_b.getX().get(size_x-1);

		double subsegmentbreite = Math.abs((x_e - x_s2));
		double x_str_start = 0;
		double x_str_end = 0;

//		System.out.println("x_s2:  " + x_s2);
//		System.out.println("anzstringer:  " + anzstringer);
//		System.out.println("subsegmentbreite:  " + subsegmentbreite);

		for (int i = 1; i < anzstringer + 1; i++) {
			x_str_start = x_s2 + (subsegmentbreite) / (anzstringer + 1) * i - stringerwidth/2;
			x_str_end = x_str_start + stringerwidth;

//			System.out.println("x_str:  " + x_str);

			coord_str.getX().add(x_str_start);
			coord_str.getX().add(x_str_end);

		}

	}

}