package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.publication.Curve;
import de.iils.dc43.core.geometry.publication.Point;
import de.iils.dc43.core.geometry.publication.PolyLine;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Points__add extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Wire_gen W : getGraph().allInstances(Wire_gen.class)) {
//			System.out.println("START ----- Bauteil:   " + W.getProfilebene_gen().getBauteil());

			int anzpolyline = W.getAnzpolyline();

			for (int m = 0; m < anzpolyline; m++) {

				PolyLine_gen PL = W.getPolyLine_gen1().get(m);

				int pl_nr = PL.getPolylinenummer();

				if (pl_nr == 0) {
					int anzpoints = PL.getAnzpoints();

					ArrayList<Controlpoint_gen> points = create_Points(anzpoints);

					points = setZugehoeringerProfilpunkt(points, PL);

					connect_Points(points, PL, anzpoints, pl_nr);

				} else if (pl_nr > 0 & pl_nr < anzpolyline - 1) {
					int anzpoints = PL.getAnzpoints();

					ArrayList<Controlpoint_gen> points = create_Points(anzpoints - 1);
					points = add_lastpoint(pl_nr, W, points);

					points = setZugehoeringerProfilpunkt(points, PL);

					connect_Points(points, PL, anzpoints, pl_nr);

				} else if (pl_nr == anzpolyline - 1) {

					int anzpoints = PL.getAnzpoints();

					ArrayList<Controlpoint_gen> points = create_Points(anzpoints - 2);

					points = add_lastpoint(pl_nr, W, points);

					points = add_firstpoint(W, points);

					points = setZugehoeringerProfilpunkt(points, PL);

					connect_Points(points, PL, anzpoints, pl_nr);

				}
			}

		}
	}

	private ArrayList<Controlpoint_gen> create_Points(int anzpoints) {

		ArrayList<Controlpoint_gen> points = Lists.newArrayList();

		Controlpoint_gen point[] = new Controlpoint_gen[anzpoints];

		for (int i = 0; i < anzpoints; i++) {
			point[i] = Controlpoint_gen.create();
			points.add(point[i]);
		}

		return points;

	}

	private ArrayList<Controlpoint_gen> setZugehoeringerProfilpunkt(ArrayList<Controlpoint_gen> points,
			PolyLine_gen PL) {

		if (PL instanceof PolyLine_Profil) {
			int anzpoints = PL.getAnzpoints();
			
			
			int is_up_flag =0;
//			if (PL instanceof PL_onPro_Skin_offset) {
//				Skin skin = (Skin) PL.getWire_gen().getProfilebene_gen().getBauteil();
//				is_up_flag = skin.getIs_up_flag();
//			}else {
				is_up_flag = PL.getIs_up_flag(); // ist PolyLine oben unten
			
			int	Nr_start = PL.getNr_start() + 1;
		
			for (int i = 1; i < anzpoints - 1; i++) {
				points.get(i).setZugehoerigerProfilpunktNr(Nr_start - i);
			}
		}
		return points;
	}

	private ArrayList<Controlpoint_gen> add_lastpoint(int pl_nr, Wire_gen W, ArrayList<Controlpoint_gen> points) {

		int last_pl_nr = pl_nr - 1;

		PolyLine_gen last_pl = W.getPolyLine_gen1().get(last_pl_nr);

		int last_anzpoints = last_pl.getAnzpoints();

		Controlpoint_gen lastpoint = null;

		lastpoint = last_pl.getCP_gen2().get(last_anzpoints - 1);

		ArrayList<Controlpoint_gen> points_new = Lists.newArrayList();

		points_new.add(lastpoint);
		points_new.addAll(points);

		return points_new;

	}

	private ArrayList<Controlpoint_gen> add_firstpoint(Wire_gen W, ArrayList<Controlpoint_gen> points) {

		PolyLine_gen first_pl = W.getPolyLine_gen1().get(0);

		int first_anzpoints = first_pl.getAnzpoints();

		Controlpoint_gen first_point = null;

		first_point = first_pl.getCP_gen2().get(0);

		points.add(first_point);

		return points;
	}

	private void connect_Points(ArrayList<Controlpoint_gen> points, PolyLine_gen pl, int anzpoints, int pl_nr) {

		// verschieben / Testweiße
		int pl_typ_nr = setValues_Points(points, pl, pl_nr);

		pl.setStart(points.get(0));

		for (int i = 0; i < (anzpoints - 1); i++) {
			points.get(i).getNextPoint().add(points.get(i + 1));
		}

		for (int i = 0; i < (anzpoints); i++) {
			points.get(i).getPL_gen();
			points.get(i).getIs_point_nr_X().add(i + 1);
			points.get(i).getIs_polyline_nr_X().add(pl_typ_nr);
			pl.getElement().add(points.get(i));
			pl.getCP_gen2().add(points.get(i));
			points.get(i).getPL_gen().add(pl);
		}

		// verfeinern! noch fehlerhaft!? --> connectPoints2PLs_spezifisch:
		for (int i = 0; i < (anzpoints); i++) {
			if (pl instanceof PolyLine_onProfil_bound) {
				pl.getCP_gen_onPro().add(points.get(i));
				points.get(i).setPL_gen_onPro(pl);
				points.get(i).setIs_onPro_flag(1);
			} else if (pl instanceof PolyLine_Sparcap_fest) {
				pl.getCP_Sc().add(points.get(i));
				points.get(i).setPL_Sc(pl);
				points.get(i).setIs_Sc_fest_flag(1);
			} else if (pl instanceof PolyLine_Str_fest) {
				pl.getCp_Str().add(points.get(i));
				points.get(i).setPL_Str(pl);
				points.get(i).setIs_Str_fest_flag(1);
			} else if (pl instanceof PolyLine_Rib_Seite) {
				pl.getCP_Rb_S().add(points.get(i));
				points.get(i).setPL_Rb_S(pl);
				points.get(i).setIs_Rb_S_flag(1);
			} else if (pl instanceof PolyLine_Spar_Seite) {
				pl.getCP_Spar_s().add(points.get(i));
				points.get(i).setPL_Spar_s(pl);
				points.get(i).setIs_Spar_s_flag(1);
			} else if (pl instanceof PolyLine_Spar_obenunten) {
				pl.getCP_Spar_ou().add(points.get(i));
				points.get(i).setPL_Spar_ou(pl);
				points.get(i).setIs_Spar_ou_flag(1);
//			} else if (pl instanceof PolyLine_Aus_ou) {
//				pl.getCP_Aus_ou().add(points.get(i));
//				points.get(i).setPL_Aus_ou(pl);
//				points.get(i).setIs_Aus_ou_flag(1);
			} else if (pl instanceof PolyLine_onProfil_in) {
				pl.getCP_onPro_in().add(points.get(i));
				points.get(i).setPL_onPro_in(pl);
				points.get(i).setIs_onPro_inside_flag(1);	

				
			} else if (pl instanceof PolyLine_Aus_Seite) {
				pl.getCP_Aus_s().add(points.get(i));
				points.get(i).setPL_Aus_s(pl);
				points.get(i).setIs_Aus_s_flag(1);
			} else if (pl instanceof PolyLine_Aus_Rib_LE_Seite) {
				
				pl.getCP_Aus_s().add(points.get(i));
				points.get(i).setPL_Aus_s(pl);
				points.get(i).setIs_Aus_Rb_LE_right_flag(1);	
				
				
//			} else if (pl instanceof PL_onPro_Skin_offset) {
//				pl.getCP_Skin_S()().add(points.get(i));
//				points.get(i).setPL_Aus_s(pl);
//				points.get(i).setIs_Aus_s_flag(1);
			}
		}

	}

	private int setValues_Points(ArrayList<Controlpoint_gen> points, PolyLine_gen pl, int pl_nr) {
		int polylinetyp = pl.getWire_gen().getPolylinetypliste().get(pl_nr);
		return polylinetyp;
	}

}