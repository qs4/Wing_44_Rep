package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import de.iils.dc43.core.geometry.publication.Component;
import de.iils.dc43.core.geometry.publication.G;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;

import java.util.ArrayList;

import com.google.common.collect.Lists;

import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class Polylines__add extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (Wire_gen W : getGraph().allInstances(Wire_gen.class)) {

			ArrayList<PolyLine_gen> PLs = create_PolyLines(W);
			connectPolyLines2Wire(W, PLs);
			connectPolyLines2EachOther(PLs);

//			G g = G.create();
//			g.getGeometric().add(W);
//			Component.create().setShape(g);			
		}
	}

	private void connectPolyLines2EachOther(ArrayList<PolyLine_gen> PLs) {
		int anzpolylines = PLs.size();

		for (int i = 0; i < anzpolylines - 1; i++) {
			PLs.get(i).getNext().add(PLs.get(i + 1));
		}
		PLs.get(anzpolylines - 1).getNext().add(PLs.get(0));
	}

	private void connectPolyLines2Wire(Wire_gen W, ArrayList<PolyLine_gen> PLs) {
		int anzpolylines = W.getPolylinetypliste().size();
		Bauteil_gen bauteil = W.getProfilebene_gen().getBauteil();
//		System.out.println("PLS:  "+PLs);
//		System.out.println("bauteil:  "+bauteil);

		for (int i = 0; i < anzpolylines; i++) {
			PolyLine_gen PL = PLs.get(i);
			PL.setPolylinenummer(i);
			PL.setWire_gen(W);
			W.getElement().add(PL);
			W.getPolyLine_gen1().add(PL);

			// set ribnummer
			if (PL instanceof PL_onPro_Rib) {
				((PL_onPro_Rib) PL).setPL_onPro_Nr(((Wire_Rib) W).getRib_onPro_Nr().get(i));
			} else if (PL instanceof PolyLine_Str_fest) {
				if (PL.getWire_gen() instanceof Wire_Rib) {
					((PolyLine_Str_fest) PL).setPL_Str_Nr(((Wire_Rib) W).getStr_Nr().get(i));
				} else if (PL.getWire_gen() instanceof Wire_Typ2_SC_STR) {
					int bauteilnummer = bauteil.getBauteilnummer();
					((PolyLine_Str_fest) PL).setPL_Str_Nr(bauteilnummer);
				}
			}
		}

		W.setStart(PLs.get(0));

	}

	private ArrayList<PolyLine_gen> create_PolyLines(Wire_gen W) {
		ArrayList<PolyLine_gen> PLs = Lists.newArrayList();

		int anzpolylines = W.getPolylinetypliste().size();
		int pl_nr = 0;
		for (int i = 0; i < anzpolylines; i++) {

			pl_nr = W.getPolylinetypliste().get(i);
			PolyLine_gen PL = create_Polyline(pl_nr);
			PLs.add(PL);

		}
//		W instanceof Wire_TypX_Rib |
//		if ( W instanceof Wire_TypX_Rib_LE ) {
//			G g = G.create();
//			g.getGeometric().add(W);
//			Component.create().setShape(g);
//		}

		return PLs;

	}

	private PolyLine_gen create_Polyline(int pl_nr) {

		PolyLine_gen PL = null;

		if (pl_nr == 1) {
			PL = PL_onPro_Rib.create();
			PL.setIs_up_flag(1);
//			PL.setOben(true);

		} else if (pl_nr == 2) {
			PL = PL_onPro_Rib.create();
			PL.setIs_up_flag(0);
//			PL.setOben(false);

		} else if (pl_nr == 3) {
			PL = PL_onPro_Sc.create();
			PL.setIs_up_flag(1);
		} else if (pl_nr == 4) {
			PL = PL_onPro_Sc.create();
			PL.setIs_up_flag(0);
		} else if (pl_nr == 5) {
			PL = PL_onPro_Str.create();
			PL.setIs_up_flag(1);
		} else if (pl_nr == 6) {
			PL = PL_onPro_Str.create();
			PL.setIs_up_flag(0);
		} else if (pl_nr == 7) {
			PL = PolyLine_Str_fest.create();
			PL.setIs_up_flag(1);
		} else if (pl_nr == 8) {
			PL = PolyLine_Str_fest.create();
			PL.setIs_up_flag(0);
		} else if (pl_nr == 9) {
			PL = PolyLine_Sparcap_fest.create();
			PL.setIs_up_flag(1);
		} else if (pl_nr == 10) {
			PL = PolyLine_Sparcap_fest.create();
			PL.setIs_up_flag(0);
		} else if (pl_nr == 11) {
			PL = PolyLine_Rib_Seite.create();
			PL.setIs_right_flag(0);
		} else if (pl_nr == 12) {
			PL = PolyLine_Rib_Seite.create();
			PL.setIs_right_flag(1);
		} else if (pl_nr == 13) {
			PL = PolyLine_Spar_obenunten.create();
			PL.setIs_up_flag(1);
		} else if (pl_nr == 14) {
			PL = PolyLine_Spar_Seite.create();
			PL.setIs_right_flag(0);
		} else if (pl_nr == 15) {
			PL = PolyLine_Spar_obenunten.create();
			PL.setIs_up_flag(0);
		} else if (pl_nr == 16) {
			PL = PolyLine_Spar_Seite.create();
			PL.setIs_right_flag(1);
		} else if (pl_nr == 17) {
//			PL = PolyLine_Aus_ou.create();
			PL = PL_onPro_Aus_offset.create();
			PL.setIs_up_flag(1);	
		} else if (pl_nr == 18) {
//			PL = PolyLine_Aus_ou.create();
			PL = PL_onPro_Aus_offset.create();
			PL.setIs_up_flag(0);
		} else if (pl_nr == 19) {
			PL = PolyLine_Aus_Seite.create();
			PL.setIs_right_flag(0);
		} else if (pl_nr == 20) {
			PL = PolyLine_Aus_Seite.create();
			PL.setIs_right_flag(1);
		} else if (pl_nr == 21) {
			PL = PL_onPro_Skin_offset.create();
			PL.setIs_up_flag(1);
		} else if (pl_nr == 22) {
			PL = PL_onPro_Skin_offset.create();
			PL.setIs_up_flag(0);
		} else if (pl_nr == 23) {
			PL = PolyLine_Skin_Seite.create();
			PL.setIs_right_flag(0);
		} else if (pl_nr == 24) {
			PL = PolyLine_Skin_Seite.create();
			PL.setIs_right_flag(1);
		} else if (pl_nr == 25) {
			PL = PolyLine_Aus_Rib_LE_Seite.create();
			PL.setIs_right_flag(0);
		} else if (pl_nr == 26) {
			PL = PolyLine_Aus_Rib_LE_Seite.create();
			PL.setIs_right_flag(1);
		}
		return PL;
	}

}
