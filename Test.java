import java.util.*;
import java.util.Date;
import java.text.SimpleDateFormat;

class Test {
	public static void main (String[] args) {
	    Scanner sc = new Scanner(System.in);
	  HashMap<String, Integer> hash = new LinkedHashMap<>(); //to store date and value
	  int n = sc.nextInt();   // for the number of inputs
	     
	  for(int i=0;i<n;i++)   //to take n inputs
	  {      
              sc.nextLine(); 
	      String str = sc.nextLine();

	      int val = sc.nextInt();
	       	  
	   hash.put(str,val);   //put date and value inside hashMap
	  }  
	solution(hash);  //function to solve the problem
	}

/*In this algorithm we first find those two dates which have some missing dates in between
  by using calculateDifference function and passing dates in an increasing fashion and
  then using those two dates we get there input value and then we use the arithmetic
  progression formula to calculate the difference between each missing terms.By this we 
  put next dates and values to the hashmap by finding the next date of the current date
  and by adding the difference to the current value
  */

public static void solution(HashMap<String,Integer> D)
	{
	    HashMap<String,Integer> hash = new LinkedHashMap<>();  //HashMap to store the output
	    int size = D.size();
	    String dates[] = new String[size];     //array to store the input dates
	   
	    int pos = 0;
	    int missingTerm = 0;
            int missingdays = 0;

  for (Map.Entry<String, Integer> entry : D.entrySet()) {   //for loop to get the dates
	String date = entry.getKey();                           //from hashMap and store in
	int val = D.get(date);                                    //dates array
        dates[pos++] = date;
   }

//startingDate is the date from where dates start missing

  String startingDate = dates[0];                              
  hash.put(startingDate,D.get(startingDate));                 


  for(int i=1;i<size;i++)                                       
{
    //endingDate is the last date before which dates are missing
    
   String endingDate = dates[i];
   if(calculateDifference(startingDate,endingDate)== 0.0) //condition if both starting and
   {                                                     //ending dates have 0 missing date
                                                         //in between
       startingDate = dates[i];
       hash.put(startingDate,D.get(startingDate));
      // System.out.println(hash);
   }
   else{                                                      //condition if both starting 
       float u = calculateDifference(startingDate,endingDate);  //and ending dates have 0 
       missingdays = (int)u;                                //missing date in between
  
  
       
      //getting the values of startingDate and endingDate
       int firstval = D.get(startingDate);
       int lastval = D.get(endingDate);
      
       int terms = missingdays+1;
       
     //here we will use the formula of A.P.
       int valdiff = lastval-firstval;
       int diff = valdiff/terms;

      missingTerm = firstval+diff;
    
    /* inside this for loop next date of current date is found using getNextDate function
       and then date and current value+diff is added to the hash table*/
      
      for(int j=0;j<missingdays;j++)
      {
          String nextDay = getNextDate(startingDate);
          hash.put(nextDay,missingTerm);
          startingDate = nextDay;
          missingTerm = missingTerm+diff;
      }
      hash.put(endingDate,D.get(endingDate)); //to put last date in hash table
      
   }
}
   int count=0;
   System.out.print("{");

   for (Map.Entry<String,Integer> entry : hash.entrySet()) {
    System.out.print("'"+entry.getKey()+"'"+": "+entry.getValue());
     count++;
    if(count!= missingdays+size)
    {
   System.out.print(", ");
    }

}
System.out.print("}");

}

//function to get next date
 public static String getNextDate(String currDate) {
        String nextDate = "";
        try {
            Calendar today = Calendar.getInstance();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
            Date date = format.parse(currDate);
            today.setTime(date);
            today.add(Calendar.DAY_OF_YEAR, 1);
            nextDate = format.format(today.getTime());
        } catch (Exception e) {
            return nextDate;
        }
        return nextDate;
    }

//function to calculate the differnce between two dates
public static float calculateDifference(String start,String end)
{
   SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-mm-dd");
     long diff = -1;
  try {
    Date dateStart = myFormat.parse(start);
    Date dateEnd = myFormat.parse(end);

    //time is always 00:00:00, so rounding should help to ignore the missing hour when going from winter to summer time, as well as the extra hour in the other direction
    diff = Math.round((dateEnd.getTime() - dateStart.getTime()) / (double) 86400000);
  } catch (Exception e) {
    //handle the exception according to your own situation
  }
  if(diff==0)
  {
      return diff;
  }
  return diff-1;
}
        
}