package scheduleApi.schedule.controller.validation;

import jakarta.validation.GroupSequence;

@GroupSequence({ValidationGroups.NotNullGroup.class, ValidationGroups.NotBlankGroup.class, ValidationGroups.ValueRangeGroup.class})
public interface ValidationOrder {
}