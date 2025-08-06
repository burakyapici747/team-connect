package com.teamconnect.model.sql;

import com.teamconnect.common.enumarator.DefaultMessageNotificationLevel;
import com.teamconnect.common.enumarator.MfaLevel;
import com.teamconnect.common.enumarator.SystemChannelFlag;
import com.teamconnect.common.enumarator.VerificationLevel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "GUILDS")
public class Guild extends BaseModel{
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "icon_hash")
    private String iconHash;

    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;

    @Column(name = "afk_channel_id")
    private UUID afkChannelId;

    @Column(name = "afk_timeout")
    private Integer afkTimeout;

    @Column(name = "verification_level")
    private VerificationLevel verificationLevel;

    @Column(name = "default_message_notifications")
    private DefaultMessageNotificationLevel defaultMessageNotifications;

    @Column(name = "explicit_content_filter")
    private Integer explicitContentFilter;

    @Column(name = "mfa_level")
    private MfaLevel mfaLevel;

    @Column(name = "application_id")
    private UUID applicationId;

    @Column(name = "system_channel_id")
    private UUID systemChannelId;

    @Column(name = "system_channel_flags")
    private SystemChannelFlag systemChannelFlags;

    @Column(name = "rules_channel_id")
    private UUID rulesChannelId;

    @Column(name = "max_presences")
    private Integer maxPresences;

    @Column(name = "max_members")
    private Integer maxMembers;

    @Column(name = "vanity_url_code")
    private String vanityUrlCode;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "banner_hash")
    private String bannerHash;

    @Column(name = "preferred_locale")
    private String preferredLocale;

    @Column(name = "public_updates_channel_id")
    private UUID publicUpdatesChannelId;

    @Column(name = "nsfw_level")
    private Integer nsfwLevel;

    @Column(name = "max_video_channel_users")
    private Integer maxVideoChannelUsers;

    @Column(name = "safety_alerts_channel_id")
    private UUID safetyAlertsChannelId;

    @Column(name = "hub_type")
    private String hubType;

    @Column(name = "approx_member_count")
    private Integer approximateMemberCount;

    @Column(name = "approx_presence_count")
    private Integer approximatePresenceCount;

    //TODO: Channels, Emojis ve Sticker gerekli iliskileri ayarla.
    //TODO: Guild icerisindeki Role yapisini olustur ve iliskilendir.
}
