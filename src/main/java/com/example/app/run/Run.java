package com.example.app.run;

import java.time.LocalDateTime;

/* Records are immutable, once we initialise with a value, can't be changed.
* They have all the getters and setters, toString and necessary methods inbuilt.
*/

public record Run(
        Integer id,
        String title,
        LocalDateTime startedOn,
        LocalDateTime completedOn,
        Integer miles,
        Location location
) {}