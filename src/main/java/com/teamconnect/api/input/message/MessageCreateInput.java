package com.teamconnect.api.input.message;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class MessageCreateInput {
    @NotBlank(message = "Content is required")
    @Size(max = 2000, message = "Content cannot exceed 2000 characters")
    private String content;

    private List<MultipartFile> multipartFileList;

    public MessageCreateInput() {
    }

    public MessageCreateInput(String content, List<MultipartFile> multipartFileList) {
        this.content = content;
        this.multipartFileList = multipartFileList;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<MultipartFile> getMultipartFileList() {
        return multipartFileList;
    }

    public void setMultipartFileList(List<MultipartFile> multipartFileList) {
        this.multipartFileList = multipartFileList;
    }
}
