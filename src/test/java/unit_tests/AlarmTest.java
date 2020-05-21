package unit_tests;

import org.junit.Before;
import org.junit.Test;
import tirepressuremonitoringsystem.Alarm;
import tirepressuremonitoringsystem.Sensor;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class AlarmTest {

    Sensor sensor;
    double sensorResponse;

    @Before
    public void init(){
        sensor = mock(Sensor.class);

    }

    @Test
    public void spying_the_interaction_between_alarm_class_and_sensor_collaborator(){

        Alarm alarm = new Alarm(sensor);

        alarm.check();

        verify(sensor).popNextPressurePsiValue();
    }

    @Test
    public void verifies_that_alarm_is_Off_when_pressure_is_between_17_and_21() {

        sensorResponse = 18.0;

        when(sensor.popNextPressurePsiValue()).thenReturn(sensorResponse);
        Alarm alarm = new Alarm(sensor);

        alarm.check();

        assertFalse(alarm.isAlarmOn());
    }


    @Test
    public void verifies_that_alarm_is_On_when_pressure_is_lower_than_17() {

        sensorResponse = 16.0;

        when(sensor.popNextPressurePsiValue()).thenReturn(sensorResponse);
        Alarm alarm = new Alarm(sensor);

        alarm.check();

        assertTrue(alarm.isAlarmOn());
    }


    @Test
    public void verifies_that_alarm_is_On_when_pressure_is_higher_than_21() {

        sensorResponse = 22.0;

        when(sensor.popNextPressurePsiValue()).thenReturn(sensorResponse);
        Alarm alarm = new Alarm(sensor);

        alarm.check();

        assertTrue(alarm.isAlarmOn());
    }

    @Test
    public void verifies_that_alarm_goes_from_activated_to_deactivated(){

        sensorResponse = 15.0;
        double sensorResponse2 = 18.0;

        when(sensor.popNextPressurePsiValue()).thenReturn(sensorResponse).thenReturn(sensorResponse2);
        Alarm alarm = new Alarm(sensor);

        alarm.check();

        assertTrue(alarm.isAlarmOn());

        alarm.check();

        assertFalse(alarm.isAlarmOn());

    }









}

