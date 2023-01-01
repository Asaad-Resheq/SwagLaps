

public class Main {

	public static void main(String[] args) {
		
		
		
		
		SwagLaps swag=new SwagLaps();
		swag.beforeTesting();
		swag.login();
		swag.sorting_high_low();
		swag.sorting_low_High();
		swag.afterTesting();
	
		
		
		
		
		
		
		
		
		
		
		
		
	}
	public static int lastOc (String[]names,String name){
        for (int i=names.length-1;i>=0;i--){
            if (names[i].toLowerCase()==name){
                return i;
            }
        }
        return -1;
    }
}
