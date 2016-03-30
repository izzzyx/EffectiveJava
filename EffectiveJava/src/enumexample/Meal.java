package enumexample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public enum Meal {
	MAINCOURSE(Food.MainCourse.class),DESSERT(Food.Dessert.class),COFFEE(Food.Coffee.class);
	
	public interface Food {
		enum MainCourse implements Food{
			LASAGNE, BURRITO
		}
		enum Dessert implements Food{
			TIRAMISU, GELATO
		}
		enum Coffee implements Food{
			BLACK_COFFEE, DECAF_COFFEE
		}
	}
	
	private Food[] values;
	private int i = 0;
	private Meal(Class<? extends Food> kind){
		values = kind.getEnumConstants();
	}
	
	public Food[] randomSelection(){
		return values;
	}
	
/*	public static void main(String args[]){
		
		Meal m = Meal.valueOf("MAINCOURSE");
		Food[] values = m.randomSelection();
		for(Food value : values){
			System.out.println(value);
		}
	}*/
	public static void main(String args[]){
		
		try {
			InputStream in = null;
			in = Runtime.getRuntime().exec("javap Meal").getInputStream();
			BufferedReader bf = new BufferedReader(new InputStreamReader(in));
			String s = "";
			while((s = bf.readLine()) != null){
				System.out.println(s);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

