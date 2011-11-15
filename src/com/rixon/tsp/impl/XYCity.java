package com.rixon.tsp.impl;

import com.rixon.tsp.City;
import com.rixon.tsp.Coordinate;
import com.rixon.tsp.Distance;

public class XYCity implements City {
	String name;
	boolean start;
	boolean end;
	Coordinate coordinate;
	
    private XYCity(String name,boolean start,boolean end,int x,int y)
    {
    	this.name = name;
    	this.start = start;
    	this.end = end;
    	coordinate = new XYCoordinate(x,y);
    }

	public String getName() {
		return name;
	}
	public boolean isStart() {
		return start;
	}
	public boolean isEnd() {
		return end;
	}
	
	@Override
	public Coordinate getCoordinate() {
		return coordinate;
	}

	@Override
	public String toString() {
		return "XYCity [name=" + name + ", start=" + start + ", end=" + end
				+ ", coordinate=" + coordinate + "]";
	}

	@Override
	public Distance getDistance(City city) {
	    return coordinate.getDistance(city.getCoordinate());
	}

	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((coordinate == null) ? 0 : coordinate.hashCode());
		result = prime * result + (end ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (start ? 1231 : 1237);
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
		XYCity other = (XYCity) obj;
		if (coordinate == null) {
			if (other.coordinate != null)
				return false;
		} else if (!coordinate.equals(other.coordinate))
			return false;
		if (end != other.end)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (start != other.start)
			return false;
		return true;
	}




	public static class CityBuilder
	{
		private String name;
		private boolean start;
		private boolean end;
		private int x;
		private int y;
	
	    public CityBuilder setName(String name)
	    {
	       this.name = name;
	       return this;
	    }  
	   
	    public CityBuilder setStart(boolean start)
	    {
	       this.start = start;
	       return this;
	    }  

	    public CityBuilder setEnd(boolean end)
	    {
	       this.end = end;
	       return this;
	    }  

	    public CityBuilder setX(int x)
	    {
	       this.x = x;
	       return this;
	    }  

	    public CityBuilder setY(int y)
	    {
	       this.y = y;
	       return this;
	    }
	    
	    public City build()
	    {
	      City city = new XYCity(name,start,end,x,y);
	      return city;
	    }  
    }

}
