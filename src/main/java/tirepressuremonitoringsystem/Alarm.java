package tirepressuremonitoringsystem;

public class Alarm {
    private final double LowPressureThreshold = 17;
    private final double HighPressureThreshold = 21;

    private Sensor sensor = new Sensor();

    private boolean alarmOn = false;

    public Alarm(Sensor sensor) {

        this.sensor = sensor;
    }

    public Alarm() {

    }

    public void check() {
        double psiPressureValue = getPsiPressureValue();

        if (psiPressureValue < LowPressureThreshold || HighPressureThreshold < psiPressureValue) {
            if(!isAlarmOn()) {
                alarmOn = true;
                System.out.println("Alarm activated!");
            }
        } else {
            if(isAlarmOn()) {
                alarmOn = false;
                System.out.println("Alarm deactivated!");
            }
        }
    }

    public double getPsiPressureValue() {
        return sensor.popNextPressurePsiValue();
    }

    public boolean isAlarmOn() {
        return alarmOn;
    }
}
