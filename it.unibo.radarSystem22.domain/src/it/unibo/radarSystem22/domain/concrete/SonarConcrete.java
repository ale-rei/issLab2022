package it.unibo.radarSystem22.domain.concrete;

import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.models.SonarModel;
import it.unibo.radarSystem22.domain.utils.Distance;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SonarConcrete extends SonarModel implements ISonar {
   private Process p;
   private BufferedReader reader;

   @Override
   public void activate() {
      System.out.println("SonarConcrete | activate");
      if (p==null) {
         try {
            p = Runtime.getRuntime().exec("sudo ./SonarAlone");
            reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
         } catch (Exception e) {
            System.out.println("SonarConcrete | sonarSetUp ERROR" + e.getMessage());
         }
      }
      super.activate();
   }

   @Override
   protected void computeDistance() {
      try{
         String data = reader.readLine();
         if (data == null) return;
         int v = Integer.parseInt(data);
         System.out.println("SonarConcrete | v=" +v);
         int lastSonarVal = dist.getVal();
         if(lastSonarVal !=v)
            dist = new Distance(v);
      }catch(Exception e){
         System.out.println("SonarConcrete | "+ e.getMessage());
      }
   }

   @Override
   public void deactivate() {
      System.out.println("SonarConcrete | deactivate");
      dist = new Distance(90);
      if (p != null) {
         p.destroy();  //Block the runtime process
         p = null;
      }
      super.deactivate();
   }
}
