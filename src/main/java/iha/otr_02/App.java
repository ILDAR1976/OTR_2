package iha.otr_02;

import java.util.ArrayList;
import java.util.HashMap;

public class App 
{
    public static HashMap<String, ArrayList<String>> notebook = 
    		new HashMap<String ,ArrayList<String>>();
	
    public static ArrayList<String> phoneList = null;
    
	public static void init(){
        phoneList = new ArrayList<String>();
        phoneList.add("+8 800 2000 500");
        phoneList.add("+8 800 200 600");
        notebook.put("Иванов И.И.", phoneList);
 	
        phoneList = new ArrayList<String>();
        phoneList.add("+8 800 2000 700");
        notebook.put("Петров П.П.", phoneList);
     	
        phoneList = new ArrayList<String>();
        phoneList.add("+8 800 2000 800");
        phoneList.add("+8 800 2000 900");
        phoneList.add("+8 800 2000 000");
        notebook.put("Сидоров С.С.", phoneList);
	}
	
	public static void main( String[] args )
    {
		init();
		
		if (args.length == 1) {
			phoneList = notebook.get(args[0]);	
			
			if (!(phoneList == null)) {
				if (phoneList.size() != 0) {
					System.out.println("User " + args[0] + " contain next phone numbers:");
					for (String item : phoneList) System.out.println(item);
				} else {
					System.out.println( args[0] + " have not a phone numbers!" );
				}
			} else {
				System.out.println("User with name " + args[0] + " not found");
			}
			
		} else {
			System.out.println("Not enter correct parameters!");
		}
    }
}
