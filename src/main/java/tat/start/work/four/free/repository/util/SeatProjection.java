package tat.start.work.four.free.repository.util;

import tat.start.work.four.free.entity.Coworking;

import java.util.UUID;

public interface SeatProjection {
        Coworking getCoworking();

        default UUID getCoworkingId() {
                return getCoworking().getId();
        }
}
