package RedboxInventory.Movie;

public class Movies implements Comparable<Movies>
{
    String title = "";
    public int available = 0;
    public int rented = 0;
    
    //default constructor
    Movies()
    {}
    
    //overloaded constructor
    public Movies(String title, int avail, int rent)
    {
        this.title = title;
        available = avail;
        rented = rent;
    }
    
    //overloaded constructor for renting and returning
    public Movies(String title)
    {
        this.title = title;
    }
    
    //overloaded constructor for adding and removing
    public Movies(String title, int avail)
    {
        this.title = title;
        available = avail;
    }
    
    //setters
    public void setTitle(String title)
    {
    	this.title = title;
    }
    public void setAvailable(int avail)
    {
    	available = avail;
    }
    public void setRented(int rent) 
    {
    	rented = rent;
    }
    
    //getters
    public String getTitle() 
    {
    	return title;
    }
    public int getAvailable() 
    {
    	return available;
    }
    public int getRented() 
    {
    	return rented;
    }
    
    @Override
    public int compareTo(Movies t)
    {
        int val = 0;
        val = title.compareTo(t.title);
        return val;
    }
    
    @Override
    public String toString()
    {
        return String.format("%-33s%-8d%-8d", title, available, rented);
    }
    
    public void add(int num)
    {
       available += num;
    }
    
    public void remove(int num)
    {
       available -= num;
    }
    
    public void rent()
    {
       rented++;
       available--;
    }

    public void returned()
    {
       rented--;
       available++;
    }
}

