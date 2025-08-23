package codigorapido.coshopcore.entity.converter;

import codigorapido.coshopcore.common.converter.Converter;
import codigorapido.coshopcore.entity.MemberEntity;
import codigorapido.coshopcore.model.Member;

public class MemberEntityToDtoConverter implements Converter<MemberEntity, Member> {

    @Override
    public Member convert(MemberEntity memberEntity) {
        return new Member()
            .id(memberEntity.getId())
            .displayName(memberEntity.getDisplayName());
    }

}
