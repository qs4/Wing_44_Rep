package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import tec.uom.se.NumberQuantity;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;

import java.util.ArrayList;

import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Polylines__setValues extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (PolyLine_gen pl : getGraph().allInstances(PolyLine_gen.class)) {

			Punkt_zNr Startpunkt = null;
			Punkt_zNr Endpunkt = null;

			int is_startpoint;
			Startpunkt = setStartEndpunkt(pl, is_startpoint = 1);
			Endpunkt = setStartEndpunkt(pl, is_startpoint = 0);

			set_Values_xz_Rand(pl, Startpunkt, Endpunkt);

//			 setValues_inside --> interface? wie definieren? if inside-->
//			if (pl instanceof PolyLine_Spar_obenunten | pl instanceof PolyLine_Spar_Seite) { // if instanceof Polyline																			// inside
//				set_Values_xz_Spar_nochAnRichtigenOrtverschieben(pl, Startpunkt, Endpunkt); // set values inside
//			}

			if (pl instanceof PolyLine_Profil) {
				int is_up_flag = pl.getIs_up_flag();
				setValues_Nrn_onProfil(pl, Startpunkt, Endpunkt, is_up_flag);
			}
			setValues_anzpoints_fest(pl);
		}
	}

	private Punkt_zNr setStartEndpunkt(PolyLine_gen pl, int is_startpoint) {
		Profilebene P = pl.getWire_gen().getProfilebene_gen();

		int is_up_flag = pl.getIs_up_flag();
		Punkt_zNr Startpunkt = null;
		Punkt_zNr Endpunkt = null;
		Punkt_zNr Punkt = null;

		if (pl instanceof PolyLine_obenunten) {

			if (is_up_flag == 1) {

				Coordinates_Basis Grundkoordinaten = (Coordinates_Basis) P.getCoordinates_basis_up();
				Coordinates_Stringer Stringerkoordinaten = (Coordinates_Stringer) P.getCoordinates_str_up();

				Startpunkt = set_point_right(pl, Grundkoordinaten, Stringerkoordinaten);
				Endpunkt = set_point_left(pl, Grundkoordinaten, Stringerkoordinaten);

			} else if (is_up_flag == 0) {

				Coordinates_Basis Grundkoordinaten = (Coordinates_Basis) P.getCoordinates_basis_down();
				Coordinates_Stringer Stringerkoordinaten = (Coordinates_Stringer) P.getCoordinates_str_down();

				Startpunkt = set_point_right(pl, Grundkoordinaten, Stringerkoordinaten);
				Endpunkt = set_point_left(pl, Grundkoordinaten, Stringerkoordinaten);
			}
		} else if (pl instanceof PolyLine_seite) {
			int is_right_flag = pl.getIs_right_flag();

			Coordinates_Basis Grundkoordinaten_up = (Coordinates_Basis) P.getCoordinates_basis_up();
			Coordinates_Basis Grundkoordinaten_down = (Coordinates_Basis) P.getCoordinates_basis_down();

			if (is_right_flag == 0) {

				Startpunkt = set_point_left_seite(pl, Grundkoordinaten_up);
				Endpunkt = set_point_left_seite(pl, Grundkoordinaten_down);
			} else if (is_right_flag == 1) {
				Startpunkt = set_point_right_seite(pl, Grundkoordinaten_up);
				Endpunkt = set_point_right_seite(pl, Grundkoordinaten_down);
			}
		}

		if (is_startpoint == 1) {
			Punkt = Startpunkt;
		} else if (is_startpoint == 0) {
			Punkt = Endpunkt;
		}
		return Punkt;
	}

	private Punkt_zNr set_point_right_seite(PolyLine_gen pl, Coordinates_Basis Coord_basis) {
		Punkt_zNr Point = null;

		if (pl instanceof PolyLine_Rib_Seite) { // Rib
			Point = Coord_basis.getPunkt_zNr().get(1);
		} else if (pl instanceof PolyLine_Spar_Seite) { // Spar
			Point = Coord_basis.getPunkt_zNr().get(0);
		} else if (pl instanceof PolyLine_Aus_Seite) { // Aussparung
			Point = Coord_basis.getPunkt_zNr().get(2);
		} else if (pl instanceof PolyLine_Skin_Seite) { // Skin
			Point = Coord_basis.getPunkt_zNr().get(0);
		} else if (pl instanceof PolyLine_Aus_Rib_LE_Seite) { // Aussparung LE
			Point = Coord_basis.getPunkt_zNr().get(2);
		}

		return Point;
	}

	private Punkt_zNr set_point_left_seite(PolyLine_gen pl, Coordinates_Basis Coord_basis) {

		Punkt_zNr Point = null;

		if (pl instanceof PolyLine_Rib_Seite) { // Rib
			Point = Coord_basis.getPunkt_zNr().get(4);
		} else if (pl instanceof PolyLine_Spar_Seite) { // Spar
			Point = Coord_basis.getPunkt_zNr().get(0);
		} else if (pl instanceof PolyLine_Aus_Seite) { // Aussparung
			Point = Coord_basis.getPunkt_zNr().get(3);
		} else if (pl instanceof PolyLine_Skin_Seite) { // Skin
			Point = Coord_basis.getPunkt_zNr().get(4);
		} else if (pl instanceof PolyLine_Aus_Rib_LE_Seite) { // Aussparung LE
			Point = Coord_basis.getPunkt_zNr().get(3);
		}

		return Point;
	}

	private Punkt_zNr set_point_right(PolyLine_gen pl, Coordinates_Basis Coord_basis, Coordinates_Stringer Coord_str) {
		Profilebene P = pl.getWire_gen().getProfilebene_gen();
		Bauteil_gen bauteil = P.getBauteil();
		int b_art = P.getBauteil().getBauteilart();
		int segmentnummer = P.getBauteil().getBauteilE().getSE().getSegments().getSegmentnummer();

		Punkt_zNr Point = null;

		if (pl instanceof PL_onPro_Sc | pl instanceof PolyLine_Sparcap_fest) { // Sparcap

			Point = Coord_basis.getPunkt_zNr().get(0);

		} else if (pl instanceof PL_onPro_Rib) { // Rib
			int PL_onPro_Nr = ((PL_onPro_Rib) pl).getPL_onPro_Nr();

			if (PL_onPro_Nr == 1) {
				if (segmentnummer == 1) {
					Point = Coord_basis.getPunkt_zNr().get(1);
				} else if (segmentnummer == 0) {
					Point = Coord_basis.getPunkt_zNr().get(0);
				}
			} else if (PL_onPro_Nr > 1) {
				Point = Coord_str.getPunkt_zNr().get((PL_onPro_Nr - 2) * 2 + 1);
			}

		} else if (pl instanceof PL_onPro_Str | pl instanceof PolyLine_Str_fest) { // Stringer

			int stringernummer = 0;
			if (bauteil instanceof Stringer) {
				stringernummer = P.getBauteil().getBauteilnummer();
			} else if (bauteil instanceof Rib) {
				stringernummer = ((PolyLine_Str_fest) pl).getPL_Str_Nr();
			}

			Point = Coord_str.getPunkt_zNr().get((stringernummer - 1) * 2);

		} else if (pl instanceof PolyLine_Spar_obenunten) { // Spar

			Point = Coord_basis.getPunkt_zNr().get(0);

		} else if (pl instanceof PL_onPro_Aus_offset) {
			Point = Coord_basis.getPunkt_zNr().get(2);
		} else if (pl instanceof PL_onPro_Skin_offset) {
			Point = Coord_basis.getPunkt_zNr().get(0);
		}

		return Point;
	}

	private Punkt_zNr set_point_left(PolyLine_gen pl, Coordinates_Basis Coord_basis, Coordinates_Stringer Coord_str) {
		Profilebene P = pl.getWire_gen().getProfilebene_gen();
		Bauteil_gen bauteil = P.getBauteil();
		int b_art = P.getBauteil().getBauteilart();

		Punkt_zNr Point = null;

		if (pl instanceof PL_onPro_Sc | pl instanceof PolyLine_Sparcap_fest) { // Sparcap

			Point = Coord_basis.getPunkt_zNr().get(1);

		} else if (pl instanceof PL_onPro_Rib) { // Rib

			int PL_onPro_Nr = ((PL_onPro_Rib) pl).getPL_onPro_Nr();
			Wire_Rib wire = (Wire_Rib) pl.getWire_gen();
			int anzstringers = wire.getAnzstringers();

			if (PL_onPro_Nr == anzstringers + 1) {
				Point = Coord_basis.getPunkt_zNr().get(4);
			} else if (PL_onPro_Nr < anzstringers + 1) {
				Point = Coord_str.getPunkt_zNr().get((PL_onPro_Nr - 1) * 2);
			}

		} else if (pl instanceof PL_onPro_Str | pl instanceof PolyLine_Str_fest) { // Stringer
			int stringernummer = 0;
			if (bauteil instanceof Stringer) {
				stringernummer = P.getBauteil().getBauteilnummer();
			} else if (bauteil instanceof Rib) {
				stringernummer = ((PolyLine_Str_fest) pl).getPL_Str_Nr();
			}
			Point = Coord_str.getPunkt_zNr().get((stringernummer - 1) * 2 + 1);

		} else if (pl instanceof PolyLine_Spar_obenunten) { // Spar
			Point = Coord_basis.getPunkt_zNr().get(0);

		} else if (pl instanceof PL_onPro_Aus_offset) {
			Point = Coord_basis.getPunkt_zNr().get(3);
		} else if (pl instanceof PL_onPro_Skin_offset) {
			Point = Coord_basis.getPunkt_zNr().get(4);
		}

		return Point;
	}

	private void set_Values_xz_Rand(PolyLine_gen pl, Punkt_zNr punkt_start, Punkt_zNr punkt_end) {
		NumberQuantity x_start = punkt_start.getX();
		NumberQuantity x_end = punkt_end.getX();

		NumberQuantity z_start = punkt_start.getZ();
		NumberQuantity z_end = punkt_end.getZ();

		pl.setX_start(x_start);
		pl.setX_end(x_end);

		pl.setZ_start(z_start);
		pl.setZ_end(z_end);

	}

	private void set_Values_xz_Spar_nochAnRichtigenOrtverschieben(PolyLine_gen pl, Punkt_zNr punkt_start,
			Punkt_zNr punkt_end) {
//		System.out.println("hi");
//		System.out.println("START: ------- " + pl.getWire_gen().getProfilebene_gen().getBauteil());
		int is_up_flag = pl.getIs_up_flag();

		// Sparcap Values getten auslagern!!!:
		Sparcap sparcap = null;
		Spar spar = (Spar) pl.getWire_gen().getProfilebene_gen().getBauteil();

		if (is_up_flag == 1) {
			sparcap = (Sparcap) pl.getWire_gen().getProfilebene_gen().getBauteil().getBauteilE().getSE().getSCE()
					.getBauteil_top().get(0);
		} else if (is_up_flag == 0) {
			sparcap = (Sparcap) pl.getWire_gen().getProfilebene_gen().getBauteil().getBauteilE().getSE().getSCE()
					.getBauteil_down().get(0);
		}

		NumberQuantity w2 = sparcap.getWidth_2();
		NumberQuantity w1 = sparcap.getWidth_1();
		NumberQuantity h1 = sparcap.getHeight_1();
		NumberQuantity h2 = sparcap.getHeight_2();

		NumberQuantity w_spar = spar.getWidth();

		NumberQuantity x_start = punkt_start.getX();
		NumberQuantity x_end = punkt_end.getX();

		NumberQuantity z_start = punkt_start.getZ();
		NumberQuantity z_end = punkt_end.getZ();

		if (pl instanceof PolyLine_Spar_obenunten) {

			x_start = x_start.add((w1.add(w2).divide(2)));
			x_end = x_start.add(w_spar);

			z_start = z_start.subtract((h2.subtract(h1)));
			z_end = z_start;

		} else if (pl instanceof PolyLine_Spar_Seite) {
			int is_right_flag = pl.getIs_right_flag();
			if (is_right_flag == 1) {
				x_start = x_start.add((w1.add(w2).divide(2)));
			} else if (is_right_flag == 0) {
				x_start = x_start.add((w1.add(w2).divide(2))).add(w_spar);
			}
			x_end = x_start;
			z_start = z_start.add((h2.add(h1)));
			z_end = z_end.subtract((h2.subtract(h1)));
		}

		pl.setX_start(x_start);
		pl.setX_end(x_end);

		pl.setZ_start(z_start);
		pl.setZ_end(z_end);
	}

	private void setValues_Nrn_onProfil(PolyLine_gen pl, Punkt_zNr punkt_start, Punkt_zNr punkt_end, int is_up_flag) {
		Bauteil_gen bauteil = pl.getWire_gen().getProfilebene_gen().getBauteil();
//		System.out.println("START: ------- " + bauteil);

		int Nr_start = 0;
		int Nr_end = 0;
		if (is_up_flag == 1) {
			Nr_start = punkt_start.getNr1();
			Nr_end = punkt_end.getNr2();
		} else if (is_up_flag == 0) {
			Nr_start = punkt_end.getNr1();
			Nr_end = punkt_start.getNr2();
		}

		int anzpointpolyline = Math.abs(Nr_start - Nr_end) + 2 + 1;

//		int hilf1 = punkt_start.getNr1().intValue();
//		int hilf2 = punkt_end.getNr1().intValue();

// einbauen!!! wenn Zeit!!!
//		if (hilf1 ==hilf2)	 {
//			Nr_start = 0;
//			Nr_end = 0;
//			anzpointpolyline = 3;
//		}

		pl.setNr_start(Nr_start);
		pl.setNr_end(Nr_end);

		pl.setAnzpoints(anzpointpolyline);

	}

	private void setValues_anzpoints_fest(PolyLine_gen pl) {

		if (pl instanceof PolyLine_Sparcap_fest) {
			pl.setAnzpoints(8);
		} else if (pl instanceof PolyLine_Rib_Seite) {
			pl.setAnzpoints(6);
		} else if (pl instanceof PolyLine_Spar_Seite) {
			pl.setAnzpoints(3);
		} else if (pl instanceof PolyLine_Str_fest) {
			pl.setAnzpoints(4);
		} else if (pl instanceof PolyLine_Spar_Seite) {
			pl.setAnzpoints(3);
		} else if (pl instanceof PolyLine_Spar_obenunten) {
			pl.setAnzpoints(3);
//		} else if (pl instanceof PolyLine_Aus_ou) {
//			pl.setAnzpoints(3);
		} else if (pl instanceof PolyLine_Aus_Seite) {
			pl.setAnzpoints(9);
		} else if (pl instanceof PolyLine_Skin_Seite) {
			pl.setAnzpoints(3);
		} else if (pl instanceof PolyLine_Aus_Rib_LE_Seite) {
			pl.setAnzpoints(3);
		}

	}
}