package wing_44.rules;

import de.iils.dc43.core.JavaRule;
import wing_44.*;

import static tec.uom.se.quantity.Quantities.*;
import static de.iils.dc43.core.util.DC43Util.prettyPrint;

@SuppressWarnings("all")
public class BauteilE__setValues extends JavaRule {

	@Override
	public void execute() throws Exception {
		for (BauteilElement bauteilE : getGraph().allInstances(BauteilElement.class)) {

			setValues(bauteilE);

		}
	}



	private void setValues(BauteilElement bauteilE) {
		if (bauteilE instanceof SparcapElement) {

		} else if (bauteilE instanceof RibElement) { 

			double ribthickness = 0.01;
			((RibElement) bauteilE).setThickness(ribthickness);
			
			 calc_n_set_rippenabstand(bauteilE);

		} else if (bauteilE instanceof SparElement) { 

		} else if (bauteilE instanceof StringerElement) {
			
			double stringerwidth = 0.06;
			((StringerElement) bauteilE).setWidth(stringerwidth);
			
			double stringerheight = 0.06;
			((StringerElement) bauteilE).setHeight(stringerheight);
			
			 calc_stringerabstand(bauteilE);
		} else if (bauteilE instanceof SkinElement) {
			double skinthickness = 0.02;
			((SkinElement) bauteilE).setThickness(skinthickness);


		}
		
	}

	private void calc_n_set_rippenabstand(BauteilElement bauteilE) {
		// get Links
		Subsection subsec = bauteilE.getSE().getSegments().getSubsection();
		StructuralElement sE = bauteilE.getSE();
		
		// get Values
		double section_width = subsec.getSection_witdh().getValue();
		int anzribtyp1 = sE.getAnzribtyp1().intValue();
		int anzribtyp2 = sE.getAnzribtyp2().intValue();
		
		double thickness = ((RibElement) bauteilE).getThickness().getValue();
		
		// calc Values
		double rippenabstand1 = (section_width/anzribtyp1)-thickness;
		((RibElement) bauteilE).setRippenabstand1(rippenabstand1);
		
		double rippenabstand2 = (section_width/anzribtyp2)-thickness;
		((RibElement) bauteilE).setRippenabstand2(rippenabstand2);
	}
	
	private void calc_stringerabstand(BauteilElement bauteilE) {
		// get Links
		Segment segment = bauteilE.getSE().getSegments();
		StructuralElement sE = bauteilE.getSE();
		
		double segment_width_prozent = segment.getSegment_width_prozent().getValue();
		int anzstringer1 = sE.getAnzstringertyp1().intValue();
		int anzstringer2 = sE.getAnzstringertyp2().intValue();

		double stringerwidth = ((StringerElement) bauteilE).getWidth().getValue();
		double stringerabstand1_prozent =  (segment_width_prozent/anzstringer1)-stringerwidth;
		double stringerabstand2_prozent =  (segment_width_prozent/anzstringer2)-stringerwidth;
		
		((StringerElement) bauteilE).setStringerabstand1_Prozent(stringerabstand1_prozent);
		((StringerElement) bauteilE).setStringerabstand2_Prozent(stringerabstand2_prozent);

	}
}