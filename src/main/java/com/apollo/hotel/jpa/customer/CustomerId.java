package com.apollo.hotel.jpa.customer;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class CustomerId extends AbstractEntityId<UUID> {

   /**
   * Default constructor for JPA
   */
   protected CustomerId() {
   }

   public CustomerId(UUID id) {
       super(id);
   }
}