package codigorapido.coshopcore.entity.converter;

import codigorapido.coshopcore.common.converter.Converter;
import codigorapido.coshopcore.entity.GroupEntity;
import codigorapido.coshopcore.model.GroupCreate;

public class GroupCreateToEntityConverter implements Converter<GroupCreate, GroupEntity> {

    @Override
    public GroupEntity convert(GroupCreate group) {
        return GroupEntity.builder()
            .name(group.getName())
            .startDate(group.getStartDate())
            .endDate(group.getEndDate())
            .build();
    }
}
