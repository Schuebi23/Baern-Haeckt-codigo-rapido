package codigorapido.coshopcore.entity.converter;

import codigorapido.coshopcore.common.converter.Converter;
import codigorapido.coshopcore.entity.GroupEntity;
import codigorapido.coshopcore.model.Group;

public class GroupEntityToDtoConverter implements Converter<GroupEntity, Group> {

    @Override
    public Group convert(GroupEntity groupEntity) {
        return new Group()
            .id(groupEntity.getId())
            .name(groupEntity.getName())
            .startDate(groupEntity.getStartDate())
            .endDate(groupEntity.getEndDate());
    }
}
