package chapter6;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class EnumDemo {
	
	public enum Planet{
		
		MERCURY(3.302e+23, 2.439e6),
		VENUS(4.869e+24, 6.052e6),
		EARTH(5.975e+24, 6.378e6),
		MARS(6.419e+23, 3.393e6);
		//剩下的4颗行星省略
		
		private final double mass; //单位kg
		private final double radius; //单位m
		private final double surfaceGravity; 
		
		private final static double G = 6.67300E-11;
		
		Planet(double mass, double radius){
			
			this.mass = mass;
			this.radius = radius;
			this.surfaceGravity = G * mass / (radius * radius);
		
		}
		
		public double mass(){ return mass; }
		public double radius(){ return radius; }
		public double surfaceGravity(){ return surfaceGravity; }
		
		public double surfaceWeight(double mass){
			return mass * surfaceGravity;  //F=ma
		}
	}
	
	public static void main(String args[]){
		double earthWeight = 6.66666;
		double mass = earthWeight / Planet.EARTH.surfaceGravity();
		
		for(Planet p :Planet.values()){
			System.out.format("Weight on %s is %f%n",p, p.surfaceWeight(mass));
		}
		
	}
	
	
}


