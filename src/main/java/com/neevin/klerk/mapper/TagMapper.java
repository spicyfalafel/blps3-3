package com.neevin.klerk.mapper;

import com.neevin.klerk.dto.TagDto;
import com.neevin.klerk.entity.Tag;
import org.springframework.stereotype.Component;

@Component
public class TagMapper {

    public TagDto toDto(Tag tag) {
        return new TagDto(
                tag.getId(),
                tag.getName()
        );
    }

    public Tag fromDto(TagDto tagDto) {
        return new Tag(
                tagDto.getId(),
                tagDto.getName()
        );
    }
}
