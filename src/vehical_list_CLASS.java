

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dinus
 */
public class vehical_list_CLASS {
    
    private String VEHICALID;
    private String BRAND;
    private String VEHICAL;
    private String YEARMANUFACUTURE;
    private String KMTRAVELLED;
    private String FUEL;
    private String POWER;
    private String WEIGHT;
    private String OWNER; 
    private double PRICE;
    
     public vehical_list_CLASS( String vehicalid, String brand, String vehical, String yearMaufacture, String kmTravelled, String fuel, String power, String weight, String owner, double price)
             {
            this.VEHICALID = vehicalid;
            this.BRAND = brand;
            this.VEHICAL = vehical;
            this.YEARMANUFACUTURE = yearMaufacture;
            this.KMTRAVELLED = kmTravelled;
            this.FUEL = fuel;
            this.POWER = power;
            this.WEIGHT = weight;
            this.OWNER = owner;
            this.PRICE = price;
    }
     
     public String getVehicalid()
    {
        return VEHICALID;
    }
    
    public String getBrand()
    {
        return BRAND;
    }
    
    public String getVehical()
    {
        return VEHICAL;
    } 
    
    public String getManDate()
    {
        return YEARMANUFACUTURE;
    }
    public String getKmTravelled()
    {
        return KMTRAVELLED;
    }
    
    public String getFuel()
    {
        return FUEL;
    }
    
    public String getPower()
    {
        return POWER;
    }
    
    public String getWeight()
    {
        return WEIGHT;
    }
    
    public String getOwner()
    {
        return OWNER;
    }
    
    public double getPrice()
    {
        return PRICE;
    }
}
