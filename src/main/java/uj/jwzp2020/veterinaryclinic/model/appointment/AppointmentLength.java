package uj.jwzp2020.veterinaryclinic.model.appointment;

import java.util.stream.Stream;

public enum AppointmentLength {
    FIFTEEN_MINUTES(15),
    THIRTY_MINUTES(30),
    FORTY_FIVE_MINUTES(45),
    SIXTY_MINUTES(60);

    private final int minutes;

    AppointmentLength(int minutes) {
        this.minutes = minutes;
    }

    public static AppointmentLength of(int minutes) {
        return Stream.of(AppointmentLength.values())
                .filter(al -> al.minutes == minutes)
                .findFirst()
                .orElse(null);
    }

    public int getMinutes() {
        return minutes;
    }
}
