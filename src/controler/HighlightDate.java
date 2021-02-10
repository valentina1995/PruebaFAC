package controler;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Date;


import com.toedter.calendar.IDateEvaluator;

public class HighlightDate implements IDateEvaluator{

	
	private final ArrayList<Date> list = new ArrayList<>();

    public void add(Date date) {
        list.add(date);
    }
    
	@Override
	public Color getInvalidBackroundColor() {
		// TODO Auto-generated method stub
		return Color.white;
	}

	@Override
	public Color getInvalidForegroundColor() {
		// TODO Auto-generated method stub
		return Color.RED;
	}

	@Override
	public String getInvalidTooltip() {
		// TODO Auto-generated method stub
		return "Salón Ocupado";
	}

	@Override
	public Color getSpecialBackroundColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Color getSpecialForegroundColor() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getSpecialTooltip() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isInvalid(Date date) {
		
		return list.contains(date);
	}

	@Override
	public boolean isSpecial(Date arg0) {
		// TODO Auto-generated method stub
		return false;
	}

}
