package chapert1;

public class Builder {
	
	
	public static class NutritionFacts{
		
		private final int servingSize;   //required
		private final int servings;  //required
		private final int calories;
		private final int fat;
		private final int sodium;
		private final int carbohydrate;
		
		// 重叠构造器模式
		public NutritionFacts(int servingSize, int servings){
			this(servingSize, servings, 0);
		}
		
		public NutritionFacts(int servingSize, int servings, int calories) {
			this(servingSize, servings, calories, 0);
		}
		
		public NutritionFacts(int servingSize, int servings, int calories, int fat){
			this(servingSize, servings, calories, fat, 0);
		}
		
		public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium){
			this(servingSize, servings, calories, fat, sodium, 0);
		}
		
		public NutritionFacts(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate){
			this.servingSize = servingSize;
			this.servings = servings;
			this.calories = calories;
			this.fat = fat;
			this.sodium = sodium;
			this.carbohydrate = carbohydrate;
		}
		// final
		
		// Builder模式
		private NutritionFacts(nBuilder nBuilder){
			this.servingSize = nBuilder.servingsSize;
			this.servings = nBuilder.servings;
			this.calories = nBuilder.colaries;
			this.fat = nBuilder.fat;
			this.carbohydrate = nBuilder.carbohydrate;
			this.sodium = nBuilder.sodium;
		}
		
		public static class nBuilder{
			
			private final int servingsSize;
			private final int servings;
			
			private int colaries = 0;
			private int fat = 0;
			private int carbohydrate = 0;
			private int sodium = 0;
			
			public nBuilder(int servingsSize, int servings){
				this.servingsSize = servingsSize;
				this.servings = servings;
			}
			
			public nBuilder colaries(int var){
				this.colaries = var;
				return this;
			}
			public nBuilder fat(int var){
				this.fat = var;
				return this;
			}
			public nBuilder carbohydrate(int var){
				this.carbohydrate = var;
				return this;
			}
			public nBuilder sodium(int var){
				this.sodium = var;
				return this;
			}
			
			public NutritionFacts build(){
				return new NutritionFacts(this);
			}
		}
	}
	
	public static void main(String args[]){
		
		NutritionFacts cocoCola = new NutritionFacts.nBuilder(0, 0).carbohydrate(0).colaries(0).fat(0).sodium(0).build();
		
	}
}
