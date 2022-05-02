package it.unibo.radarSystem22.domain.concrete;

import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.models.LedModel;

import java.io.IOException;

public class LedConcrete extends LedModel implements ILed {
    private Runtime rt  = Runtime.getRuntime();

    @Override
    protected void ledActivate(boolean state) {
        try {
            if( state ) rt.exec( "sudo bash led25GpioTurnOn.sh" );
            else rt.exec( "sudo bash led25GpioTurnOff.sh" );
        } catch (IOException e) {
            System.out.println("LedConcrete | ERROR " +  e.getMessage());
        }
    }
}

