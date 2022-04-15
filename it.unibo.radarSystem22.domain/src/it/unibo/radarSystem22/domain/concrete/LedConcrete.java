package it.unibo.radarSystem22.domain.concrete;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.models.LedModel;

import java.io.IOException;

public class LedConcrete extends LedModel implements ILed {
    private Runtime rt  = Runtime.getRuntime();

    @Override
        protected void ledActivate(boolean state) {
            try {
                if( state ) {
                    System.out.println("Led Concrete | ON");
                    rt.exec( "sudo bash led25GpioTurnOn.sh");
                }
                else {
                    System.out.println("Led Concrete | OFF");
                    rt.exec( "sudo bash led25GpioTurnOff.sh");
                }
            } catch (IOException e) {
                System.out.println("LedConcrete | ERROR " +  e.getMessage());
            }
        }
}

