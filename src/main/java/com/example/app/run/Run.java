package com.example.app.run;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

/* Records are immutable, once we initialise with a value, can't be changed.
* They have all the getters and setters, toString and necessary methods inbuilt.
*/

public record Run(
        Integer id,
        @NotEmpty // validation annotation
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        @Positive // validation annotation
        Integer miles,
        Location location
) {
    public Run {
        if (completedOn.isBefore(startedOn)) {
            throw new IllegalArgumentException("Completed On must be after Started On");
        }
    }

    /* Could've done manual validation like if (title.isEmpty()) */
}