package com.apollo.hotel.jpa.employee;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class EmployeeId extends AbstractEntityId<UUID> {

   /**
   * Default constructor for JPA
   */
   protected EmployeeId() {
   }

   public EmployeeId(UUID id) {
       super(id);
   }
}