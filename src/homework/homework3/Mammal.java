package homework.homework3;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mammal implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4399939680907426175L;
	private String name;
	private long birthday;
	
	public Mammal(String name, long birthday) {
		super();
		this.name = name;
		this.birthday = birthday;
	}

	public Mammal(long birthday) {
		super();
		this.name = "無名氏";
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getBirthday() {
		return birthday;
	}

	public void setBirthday(long birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		Date date = new Date(birthday);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String birthdayString = sdf.format(date);
		return getClass().getName() + "姓名：" + name + ", 生日：" + birthdayString;
	}
	
}
