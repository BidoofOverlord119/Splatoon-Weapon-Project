public abstract class Weapon
{
   private String weaponName;
   private double damagePerHit;

   public Weapon( String wName, Double damage )
   {
      weaponName = wName;
      damagePerHit = damage;
   }

   public void setWeaponName( String wName )
   {
      weaponName = wName;
   } 
   
   public String getweaponName()
   {
      return weaponName;
   }

   public void setDamagePerHit(double damage )
   {   
      
      damagePerHit = damage;
      
   } 

   

   public double getDamagePerHit()
   {
      return damagePerHit;
   }

   public String toString()
   {
      return String.format( "%s\nstats:\n\tdamage: %.1f",
      getweaponName(), getDamagePerHit() );
   }

}


