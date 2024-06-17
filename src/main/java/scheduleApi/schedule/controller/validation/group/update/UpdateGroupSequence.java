package scheduleApi.schedule.controller.validation.group.update;

import jakarta.validation.GroupSequence;
import scheduleApi.schedule.controller.validation.group.save.SaveGroupSequence;

@GroupSequence({UpdateGroup.class, SaveGroupSequence.class})
public interface UpdateGroupSequence {
}
