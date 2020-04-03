//package wing_44.rules;
//
//import de.iils.dc43.core.JavaRule;
//import wing_44.*;
//
//import static tec.uom.se.quantity.Quantities.*;
//
//import java.util.ArrayList;
//
//import com.google.common.collect.Lists;
//
//import static de.iils.dc43.core.util.DC43Util.prettyPrint;
//
//@SuppressWarnings("all")
//public class test extends JavaRule {
//
//	@Override
//	public void execute() throws Exception {
//		for (BauteilElement bauteilE : getGraph().allInstances(BauteilElement.class)) {
//			int anzbauteil = get_anzbauteil(bauteilE);
//
//			ArrayList<Bauteil_gen> bauteils1 = create_Bauteil1(bauteilE, anzbauteil);
//			connectbauteil1(bauteilE, bauteils1);
//
//			ArrayList<Bauteil_gen> bauteils2 = create_Bauteil2(bauteilE, anzbauteil);
//			if (bauteils2.get(0) != null) {
//				connectbauteil2(bauteilE, bauteils2);
//			}
//		}
//	}
//
//
//	private void connectbauteil2(BauteilElement bauteilE, ArrayList<Bauteil_gen> bauteils) {
//		int size = bauteils.size();
//
//		for (int i = 0; i < size; i++) {
//			bauteils.get(i).setBauteilE(bauteilE);
//			bauteilE.getBauteil_typ2().add(bauteils.get(i));
//			bauteilE.getSub().add(bauteils.get(i));
//		}
//	}
//
//	private void connectbauteil1(BauteilElement bauteilE, ArrayList<Bauteil_gen> bauteils) {
//		int size = bauteils.size();
//
//		for (int i = 0; i < size; i++) {
//			bauteils.get(i).setBauteilE(bauteilE);
//			bauteilE.getBauteil_typ1().add(bauteils.get(i));
//			bauteilE.getSub().add(bauteils.get(i));
//		}
//	}
//
//	private ArrayList<Bauteil_gen> create_Bauteil2(BauteilElement bauteilE, int anzbauteil) {
//		ArrayList<Bauteil_gen> bauteils = Lists.newArrayList();
//
//		Bauteil_gen bauteil2[] = new Bauteil_gen[anzbauteil];
//
//		for (int i = 0; i < anzbauteil; i++) {
//
//			if (bauteilE instanceof SparcapElement) {
////				bauteil2[i] = Sparcap.create();
//				bauteil2[i] = Sparcap_down.create();
//			} else if (bauteilE instanceof RibElement) {
//				int sectionnummer = bauteilE.getSE().getSegments().getSubsection().getSectionnummer();
//				if (sectionnummer == 1) {
//					bauteil2[i] = Rib.create();
//				}
//
//			} else if (bauteilE instanceof SparElement) {
//				bauteil2[i] = null;
//			} else if (bauteilE instanceof StringerElement) {
//				bauteil2[i] = Stringer_down.create();
//			}
//
//			if (bauteil2[i] != null) {
//				bauteil2[i].setBauteilnummer(i);
//				bauteil2[i].setSubsegmentnummer(2);
//			}
//
//			bauteils.add(bauteil2[i]);
//		}
//
//		return bauteils;
//	}
//
//	private int get_anzbauteil(BauteilElement bauteilE) {
//		int anzbauteil = 0;
//		if (bauteilE instanceof SparcapElement) {
//			anzbauteil = bauteilE.getSE().getAnzspars();
//		} else if (bauteilE instanceof RibElement) {
//			anzbauteil = bauteilE.getSE().getAnzribs();
//		} else if (bauteilE instanceof SparElement) {
//			anzbauteil = bauteilE.getSE().getAnzspars();
//		} else if (bauteilE instanceof StringerElement) {
//			anzbauteil = bauteilE.getSE().getAnzstringers();
//		}
//
//		return anzbauteil;
//	}
//
//	private ArrayList<Bauteil_gen> create_Bauteil1(BauteilElement bauteilE, int anzbauteil) {
//
//		ArrayList<Bauteil_gen> bauteils = Lists.newArrayList();
//
//		Bauteil_gen bauteil1[] = new Bauteil_gen[anzbauteil];
//
//		for (int i = 0; i < anzbauteil; i++) {
//
//			if (bauteilE instanceof SparcapElement) {
////				bauteil1[i] = Sparcap.create();
//				bauteil1[i] = Sparcap_up.create();
//			} else if (bauteilE instanceof RibElement) {
//				bauteil1[i] = Rib.create();
//			} else if (bauteilE instanceof SparElement) {
//				bauteil1[i] = Spar.create();
//
//			} else if (bauteilE instanceof StringerElement) {
//				bauteil1[i] = Stringer_up.create();
//			}
//			bauteil1[i].setBauteilnummer(i);
//			bauteil1[i].setSubsegmentnummer(1);
//			bauteils.add(bauteil1[i]);
//		}
//
//		return bauteils;
//	}
//
//
//}