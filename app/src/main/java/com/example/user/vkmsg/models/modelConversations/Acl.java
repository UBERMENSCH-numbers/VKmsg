
package com.example.user.vkmsg.models.modelConversations;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Acl {

    @SerializedName("can_invite")
    @Expose
    private Boolean canInvite;
    @SerializedName("can_change_info")
    @Expose
    private Boolean canChangeInfo;
    @SerializedName("can_change_pin")
    @Expose
    private Boolean canChangePin;
    @SerializedName("can_promote_users")
    @Expose
    private Boolean canPromoteUsers;
    @SerializedName("can_see_invite_link")
    @Expose
    private Boolean canSeeInviteLink;
    @SerializedName("can_change_invite_link")
    @Expose
    private Boolean canChangeInviteLink;

    public Boolean getCanInvite() {
        return canInvite;
    }

    public void setCanInvite(Boolean canInvite) {
        this.canInvite = canInvite;
    }

    public Boolean getCanChangeInfo() {
        return canChangeInfo;
    }

    public void setCanChangeInfo(Boolean canChangeInfo) {
        this.canChangeInfo = canChangeInfo;
    }

    public Boolean getCanChangePin() {
        return canChangePin;
    }

    public void setCanChangePin(Boolean canChangePin) {
        this.canChangePin = canChangePin;
    }

    public Boolean getCanPromoteUsers() {
        return canPromoteUsers;
    }

    public void setCanPromoteUsers(Boolean canPromoteUsers) {
        this.canPromoteUsers = canPromoteUsers;
    }

    public Boolean getCanSeeInviteLink() {
        return canSeeInviteLink;
    }

    public void setCanSeeInviteLink(Boolean canSeeInviteLink) {
        this.canSeeInviteLink = canSeeInviteLink;
    }

    public Boolean getCanChangeInviteLink() {
        return canChangeInviteLink;
    }

    public void setCanChangeInviteLink(Boolean canChangeInviteLink) {
        this.canChangeInviteLink = canChangeInviteLink;
    }

}
