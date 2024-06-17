package scheduleApi.schedule.controller.validation.group.save;

import jakarta.validation.GroupSequence;

@GroupSequence({SaveGroup.NotNullGroup.class, SaveGroup.NotBlankGroup.class, SaveGroup.SizeGroup.class, SaveGroup.TimeRangeGroup.class})
public interface SaveGroupSequence {
}