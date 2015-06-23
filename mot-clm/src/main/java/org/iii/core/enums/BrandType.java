package org.iii.core.enums;

/**
 * BrandType 廠牌型式
 * @author 黃小貓	
 * @version 2014/5/19
 */

public enum BrandType {
	/** TOYOTA. */
	TOYOTA("TOYOTA 豐田"),
	
	/** BENZ. */
	BENZ("Benz 賓士"),
	
	/** BMW. */
	BMW("BMW"),
	
	/** Audi. */
	AUDI("Audi 奧迪"),
	
	/** Bentley. */
	BENTLEY("Bentley 賓利"),
	
	/** Daihatsu. */
	DAIHATSU("Daihatsu 大發"),
	
	/** Ferrari. */
	FERRARI("Ferrari 法拉利"),
	
	/** Ford. */
	FORD("Ford 福特"),
	
	/** Honda. */
	HONDA("Honda 本田"),
	
	/** Hyundai. */
	HYUNDAI("Hyundai 現代"),
	
	/** Infiniti. */
	INFINITI("Infiniti 英菲尼迪"),
	
	/** Jaguar. */
	JAGUAR("Jaguar 捷豹"),
	
	/** Lamborghini. */
	LAMBORGHINI("Lamborghini 林寶堅尼"),
	
	/** Lexus. */
	LEXUS("Lexus 凌志"),
	
	/** Lotus. */
	LOTUS("Lotus 蓮花"),
	
	/** Luxgen. */
	LUXGEN("Luxgen 納智捷"),
	
	/** Maserati. */
	MASERATI("Maserati 瑪莎拉蒂"),
	
	/** Mazda. */
	MAZDA("Mazda 馬自達"),
	
	/** MINI. */
	MINI("MINI"),
	
	/** Nissan. */
	NISSAN("Nissan 日產"),
	
	/** Peugeot. */
	PEUGEOT("Peugeot 寶獅"),
	
	/** Porsche. */
	PORSCHE("Porsche 保時捷"),

	/** Rolls-Royce. */
	ROLLSROYCE("Rolls-Royce 勞斯萊斯"),
	
	/** Saab. */
	SAAB("Saab 紳寶"),
	
	/** Skoda. */
	SKODA("Skoda"),
	
	/** smart. */
	SMART("smart"),
	
	/** Suzuki. */
	SUZUKI("Suzuki 鈴木"),
	
	/** Volkswagen. */
	VOLKSWAGEN("Volkswagen 福斯"),
	
	/** Aston Martin. */
	ASTONMARTIN("Aston Martin 亞士頓馬丁"),
	
	/** Mitsubishi. */
	MITSUBISHI("Mitsubishi 三菱");
	
	
	
	private String brandType;
	
	private BrandType(){
		
	}
	
	private BrandType(String brandType) {
		this.brandType = brandType;
	}
	
	public String getBrandType() {
		return brandType;
	}

	
}
