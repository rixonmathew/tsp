package com.rixon.tsp.impl;

import java.text.NumberFormat;

import com.rixon.tsp.Distance;

public class DistanceInMeter implements Distance {

	private final String UNIT="Meter";
	private double distance;
	
	public DistanceInMeter(String value)
	{
		distance = Double.valueOf(value);
	}
	
	@Override
	public String getValue() {
		return NumberFormat.getInstance().format(distance);
		//return String.valueOf(distance);
	}

	public String getUnit() {
		return UNIT;
	}

	@Override
	public int compareTo(Distance distance) {
		if (this == distance || this.equals(distance))
		{
			return 0;
		}
		if (this.distance < Double.valueOf(distance.getValue()))
			return -1;
		return 1;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((UNIT == null) ? 0 : UNIT.hashCode());
		long temp;
		temp = Double.doubleToLongBits(distance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DistanceInMeter other = (DistanceInMeter) obj;
		if (UNIT == null) {
			if (other.UNIT != null)
				return false;
		} else if (!UNIT.equals(other.UNIT))
			return false;
		if (Double.doubleToLongBits(distance) != Double
				.doubleToLongBits(other.distance))
			return false;
		return true;
	}
	
}
