package com.teamconnect.common.enumarator;

import lombok.Getter;

@Getter
public enum PermissionType {
    CREATE_INSTANT_INVITE(1L),                 // 1
    KICK_MEMBERS(1L << 1),                     // 2
    BAN_MEMBERS(1L << 2),                      // 4
    ADMINISTRATOR(1L << 3),                    // 8
    MANAGE_CHANNELS(1L << 4),                  // 16
    MANAGE_GUILD(1L << 5),                     // 32
    ADD_REACTIONS(1L << 6),                    // 64
    VIEW_AUDIT_LOG(1L << 7),                   // 128
    PRIORITY_SPEAKER(1L << 8),                 // 256
    STREAM(1L << 9),                           // 512
    VIEW_CHANNEL(1L << 10),                    // 1024
    SEND_MESSAGES(1L << 11),                   // 2048
    SEND_TTS_MESSAGES(1L << 12),               // 4096
    MANAGE_MESSAGES(1L << 13),                 // 8192
    EMBED_LINKS(1L << 14),                     // 16384
    ATTACH_FILES(1L << 15),                    // 32768
    READ_MESSAGE_HISTORY(1L << 16),            // 65536
    MENTION_EVERYONE(1L << 17),                // 131072
    USE_EXTERNAL_EMOJIS(1L << 18),             // 262144
    VIEW_GUILD_INSIGHTS(1L << 19),             // 524288
    CONNECT(1L << 20),                         // 1048576
    SPEAK(1L << 21),                           // 2097152
    MUTE_MEMBERS(1L << 22),                    // 4194304
    DEAFEN_MEMBERS(1L << 23),                  // 8388608
    MOVE_MEMBERS(1L << 24),                    // 16777216
    USE_VAD(1L << 25),                         // 33554432
    CHANGE_NICKNAME(1L << 26),                 // 67108864
    MANAGE_NICKNAMES(1L << 27),                // 134217728
    MANAGE_ROLES(1L << 28),                    // 268435456
    MANAGE_WEBHOOKS(1L << 29),                 // 536870912
    MANAGE_EMOJIS_AND_STICKERS(1L << 30),      // 1073741824
    USE_APPLICATION_COMMANDS(1L << 31),        // 2147483648
    REQUEST_TO_SPEAK(1L << 32),                // 4294967296
    MANAGE_EVENTS(1L << 33),                   // 8589934592
    MANAGE_THREADS(1L << 34),                  // 17179869184
    CREATE_PUBLIC_THREADS(1L << 35),           // 34359738368
    CREATE_PRIVATE_THREADS(1L << 36),          // 68719476736
    USE_EXTERNAL_STICKERS(1L << 37),           // 137438953472
    SEND_MESSAGES_IN_THREADS(1L << 38),        // 274877906944
    USE_EMBEDDED_ACTIVITIES(1L << 39),         // 549755813888
    MODERATE_MEMBERS(1L << 40);                // 1099511627776

    private final long value;

    PermissionType(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    public static long combine(PermissionType... permissions) {
        long combined = 0L;
        for (PermissionType perm : permissions) {
            combined |= perm.getValue();
        }
        return combined;
    }

    public static boolean hasPermission(long combinedPermissions, PermissionType target) {
        return (combinedPermissions & target.getValue()) == target.getValue();
    }
}
