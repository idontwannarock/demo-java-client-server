package net._01;

import java.io.Serializable;

public class DataContainer implements Serializable {
	int sum, sub, pro, div, mod;

	public DataContainer(int sum, int sub, int pro, int div, int mod) {
		super();
		this.sum = sum;
		this.sub = sub;
		this.pro = pro;
		this.div = div;
		this.mod = mod;
	}

	public DataContainer() {
		super();
	}
	
}
