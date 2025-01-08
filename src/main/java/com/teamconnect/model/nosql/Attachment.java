package com.teamconnect.model.nosql;

import com.teamconnect.common.enumarator.FileType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Attachment {
    private String id;
    private String name;
    private Long sizeInKb;
    private FileType type;
    private String url;
}
