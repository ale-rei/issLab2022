package it.unibo.radarSystem22.Sprint4.usecases;


import it.unibo.radarSystem22.Sprint4.interfaces.ActionFunction;
import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ILed;
import it.unibo.radarSystem22.domain.interfaces.IRadarDisplay;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

public class Controller {
    private ILed led;
    private ISonar sonar;
    private IRadarDisplay radar;
    private ActionFunction endFun;

    public static Controller create(ILed led, ISonar sonar,IRadarDisplay radar) {
        return new Controller( led,  sonar, radar  );
    }

    private Controller(ILed led, ISonar sonar, IRadarDisplay radar ) {
        this.led    = led;
        this.sonar  = sonar;
        this.radar  = radar;
    }

    public void start(ActionFunction endFun, int limit) {
        this.endFun = endFun;
        sonar.activate();
        activate( limit );
    }

    private void activate(int limit) {
        new Thread() {
            public void run() {
                BasicUtils.aboutThreads("Controller activated | ");
                try {
                    if( sonar.isActive() ) {
                        for( int i=1; i<=limit; i++) {
                            IDistance d = sonar.getDistance();
                            LedAlarmUsecase.doUseCase(led, d);
                            if (radar!= null) RadarGuiUsecase.doUseCase(radar,d);
                            BasicUtils.delay(DomainSystemConfig.sonarDelay);
                        }
                    }
                    sonar.deactivate();
                    endFun.run("Controller | BYE ");
                } catch (Exception e) {
                    e.getMessage();
                }
            }
        }.start();

    }
}
