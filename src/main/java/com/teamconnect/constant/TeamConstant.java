package com.teamconnect.constant;

public final class TeamConstant {
    private TeamConstant() {
        throw new IllegalStateException("Constant class");
    }

    // Success Messages
    public static final String TEAM_CREATED = "Takım başarıyla oluşturuldu";
    public static final String TEAM_UPDATED = "Takım başarıyla güncellendi";
    public static final String TEAM_DELETED = "Takım başarıyla silindi";
    public static final String MEMBER_ADDED = "Üye başarıyla eklendi";
    public static final String MEMBER_REMOVED = "Üye başarıyla çıkarıldı";
    public static final String MEMBER_ROLE_UPDATED = "Üye rolü başarıyla güncellendi";

    // Error Messages
    public static final String TEAM_NOT_FOUND = "Takım bulunamadı: %s";
    public static final String TEAM_NAME_EXISTS = "Takım adı zaten kullanımda: %s";
    public static final String MEMBER_NOT_FOUND = "Takım üyeliği bulunamadı";
    public static final String USER_NOT_FOUND = "Kullanıcı bulunamadı: %s";
    public static final String MEMBER_ALREADY_EXISTS = "Kullanıcı zaten takımın üyesi";

    // Validation Messages
    public static final String NAME_NOT_EMPTY = "Takım adı boş olamaz";
    public static final String NAME_SIZE = "Takım adı 3-50 karakter arasında olmalıdır";
    public static final String DESCRIPTION_SIZE = "Açıklama en fazla 500 karakter olabilir";
} 