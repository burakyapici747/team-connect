package com.teamconnect.model.nosql;

import com.teamconnect.common.enumarator.FileType;

public class Attachment {
    private String id;
    private String name;
    private Long sizeInKb;
    private FileType type;
    private String url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSizeInKb() {
        return sizeInKb;
    }

    public void setSizeInKb(Long sizeInKb) {
        this.sizeInKb = sizeInKb;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
