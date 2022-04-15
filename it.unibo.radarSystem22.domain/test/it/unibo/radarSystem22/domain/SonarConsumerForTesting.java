package it.unibo.radarSystem22.domain;

import static org.junit.Assert.assertTrue;

import it.unibo.radarSystem22.domain.interfaces.IDistance;
import it.unibo.radarSystem22.domain.interfaces.ISonar;
import it.unibo.radarSystem22.domain.utils.BasicUtils;
import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;

class SonarConsumerForTesting extends Thread{
private ISonar sonar;
private int delta;
  public SonarConsumerForTesting( ISonar sonar, int delta) {
    this.sonar = sonar;
    this.delta = delta;
  }
  @Override
  public void run() {
    int v0 = sonar.getDistance().getVal();
    while( sonar.isActive() ) {
      BasicUtils.delay(DomainSystemConfig.sonarDelay/2); 
      IDistance d      = sonar.getDistance();
      int v            = d.getVal();
      int vexpectedMin = v0-delta;
      int vexpectedMax = v0+delta;
      assertTrue(  v <= vexpectedMax && v >= vexpectedMin );
      v0 = v;
    }
  }
}