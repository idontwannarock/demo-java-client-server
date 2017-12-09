package homework.extraPractice1;

import java.io.Serializable;
import java.math.BigDecimal;

public class DataContainer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3290636072231171676L;
	BigDecimal sum, sub, pro, div;

	public DataContainer() {
		super();
	}

	public DataContainer(BigDecimal sum, BigDecimal sub, BigDecimal pro, BigDecimal div) {
		super();
		this.sum = sum;
		this.sub = sub;
		this.pro = pro;
		this.div = div;
	}

}
