package fruitshop.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class FormatMoney {
	public static String formatMoney(int money) {
		DecimalFormatSymbols symbols = new DecimalFormatSymbols();
		symbols.setGroupingSeparator('.');
		symbols.setDecimalSeparator(',');
		
		DecimalFormat decimalFormat = new DecimalFormat("#.###", symbols);
		return decimalFormat.format(money);
	}
}
