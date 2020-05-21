package unit_tests;

import org.junit.Test;
import tirepressuremonitoringsystem.Alarm;

import static org.junit.Assert.*;

public class AlarmFakeTest {

    @Test
    public void verifies_that_alarm_is_On_when_pressure_is_lower_than_17(){

        Alarm alarm = new FakeAlarm(15.0);

        alarm.check();

        assertTrue(alarm.isAlarmOn());
    }

    @Test
    public void verifies_that_alarm_is_Off_when_pressure_is_between_17_and_21() {

        Alarm alarm = new FakeAlarm(18.0);

        alarm.check();

        assertFalse(alarm.isAlarmOn());

    }

    @Test
    public void verifies_that_alarm_is_On_when_pressure_is_higher_than_21() {

        Alarm alarm = new FakeAlarm(22.0);

        alarm.check();

        assertTrue(alarm.isAlarmOn());

    }



    class FakeAlarm extends Alarm {

        private final double pressureValue;

        public FakeAlarm(double pressureValue){

            this.pressureValue = pressureValue;
        }

        @Override
        public double getPsiPressureValue(){
            return pressureValue;
        }
    }
}
