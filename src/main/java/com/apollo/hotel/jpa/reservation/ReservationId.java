package com.apollo.hotel.jpa.reservation;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class ReservationId extends AbstractEntityId<UUID> {

   /**
   * Default constructor for JPA
   */
   protected ReservationId() {
   }

   public ReservationId(UUID id) {
       super(id);
   }
}