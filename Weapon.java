import javax.print.DocFlavor.STRING;

public class Weapon 
{
   private String weaponClass;
   private String weaponName;
   private double damagePerhit;

   public Weapon()
   {
   }
   public Weapon( String wClass, String wName, Double damage )
   {
      weaponClass = wClass;
      weaponName = wName;
      damagePerhit = damage;
   } 
   
   
   public void setweaponClass( String wClass )
   {
      weaponClass = wClass;
   } 
   
   
   public String getweaponClass()
   {
      return weaponClass;
   } 
   
   
   public void setweaponName( String wName )
   {
      weaponName = wName;
   } 
   
   public String getweaponName()
   {
      return weaponName;
   }

   public void setdamagePerhit( double damage )
   {   
      
      damagePerhit = damage;
      
   } 

   

   public String getdamagePerhit()
   {
      return damagePerhit;
   }

   public String toString()
   {
      return String.format( "%s %s\nstats:\n\tdamage: %d", 
      getweaponName(), getweaponClass(), getdamagePerhit() );
   }

}


