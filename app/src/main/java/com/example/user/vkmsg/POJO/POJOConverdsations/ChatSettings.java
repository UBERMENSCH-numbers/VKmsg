
package com.example.user.vkmsg.POJO.POJOConverdsations;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatSettings {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("pinned_message")
    @Expose
    private PinnedMessage pinnedMessage;
    @SerializedName("members_count")
    @Expose
    private Integer membersCount;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("photo")
    @Expose
    private Photo_ photo;
    @SerializedName("active_ids")
    @Expose
    private List<Integer> activeIds = null;
    @SerializedName("acl")
    @Expose
    private Acl acl;
    @SerializedName("is_group_channel")
    @Expose
    private Boolean isGroupChannel;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public PinnedMessage getPinnedMessage() {
        return pinnedMessage;
    }

    public void setPinnedMessage(PinnedMessage pinnedMessage) {
        this.pinnedMessage = pinnedMessage;
    }

    public Integer getMembersCount() {
        return membersCount;
    }

    public void setMembersCount(Integer membersCount) {
        this.membersCount = membersCount;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Photo_ getPhoto() {
        return photo;
    }

    public void setPhoto(Photo_ photo) {
        this.photo = photo;
    }

    public List<Integer> getActiveIds() {
        return activeIds;
    }

    public void setActiveIds(List<Integer> activeIds) {
        this.activeIds = activeIds;
    }

    public Acl getAcl() {
        return acl;
    }

    public void setAcl(Acl acl) {
        this.acl = acl;
    }

    public Boolean getIsGroupChannel() {
        return isGroupChannel;
    }

    public void setIsGroupChannel(Boolean isGroupChannel) {
        this.isGroupChannel = isGroupChannel;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

}
