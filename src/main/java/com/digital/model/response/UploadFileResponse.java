package com.digital.model.response;

public class UploadFileResponse {

    private String originalFilename;
    private String fileType;
    private long size;

    public UploadFileResponse(String originalFilename, String fileType, long size) {
        this.originalFilename = originalFilename;
        this.fileType = fileType;
        this.size = size;
    }

    public String getOriginalFilename() {
        return originalFilename;
    }

    public void setOriginalFilename(String originalFilename) {
        this.originalFilename = originalFilename;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}
