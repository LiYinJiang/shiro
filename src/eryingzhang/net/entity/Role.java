package eryingzhang.net.entity;

public class Role {
    private Long id;

    private String name;

    private String desc_;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDesc() {
        return desc_;
    }

    public void setDesc(String desc_) {
        this.desc_ = desc_ == null ? null : desc_.trim();
    }
}