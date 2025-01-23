package com.teamconnect.configuration;

import com.teamconnect.common.enumarator.ChannelType;
import com.teamconnect.model.nosql.Channel;
import com.teamconnect.model.nosql.Message;
import com.teamconnect.repository.couchbase.ChannelRepository;
import com.teamconnect.repository.couchbase.MessageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Configuration
class DataInitializer {
//    // Kullanıcı ID'leri
//    private static final String USER1 = "e50eb138-aa7c-4845-a9e0-832eaeb47031";
//    private static final String USER2 = "22682bca-16a8-4eda-a6fd-60c2384ab7a2";
//    private static final String USER3 = "db08a20a-079b-4921-b917-55828b0c0af2";
//    private static final String USER4 = "f5a15b66-3b93-4554-a2fe-f88bd87f3855";
//    private static final String USER5 = "02674c58-7db3-43b1-a09e-8a36216069c5";
//    private static final String USER6 = "159594c7-df0b-49ac-bf5f-4ea6f04a9be3";
//    private static final String USER7 = "de44003b-22fc-46df-88cd-7cb45c652fe6";
//
//    @Bean
//    public CommandLineRunner initializeData(ChannelRepository channelRepository, MessageRepository messageRepository) {
//        return args -> {
//            // 1-1 Direct Channel'lar oluştur
//            createDirectChannel(channelRepository, "DM-1-2", USER1, USER2);
//            createDirectChannel(channelRepository, "DM-1-3", USER1, USER3);
//            createDirectChannel(channelRepository, "DM-2-4", USER2, USER4);
//            createDirectChannel(channelRepository, "DM-5-6", USER5, USER6);
//
//            // Grup Direct Channel'lar oluştur
//            createGroupChannel(channelRepository, "Group-1", USER1, USER2, USER3);
//            createGroupChannel(channelRepository, "Group-2", USER2, USER3, USER4, USER5);
//            createGroupChannel(channelRepository, "Group-3", USER5, USER6, USER7);
//            createGroupChannel(channelRepository, "Group-4", USER1, USER4, USER7);
//
//            // Tüm channel'lar için mesaj oluştur
//            List<Channel> channels = channelRepository.findAll();
//            for (Channel channel : channels) {
//                createMessagesForChannel(messageRepository, channelRepository, channel);
//            }
//        };
//    }
//
//    private void createDirectChannel(ChannelRepository repository, String name, String user1, String user2) {
//        Channel channel = new Channel();
//        channel.setId(UUID.randomUUID().toString());
//        channel.setType(ChannelType.DIRECT_CHANNEL);
//        channel.setName(name);
//        channel.setTopic("Direct Messages");
//        channel.setNsfw(false);
//        channel.setOwnerId(user1);
//
//        Set<String> recipients = new HashSet<>();
//        recipients.add(user1);
//        recipients.add(user2);
//        channel.setRecipients(recipients);
//
//        repository.save(channel);
//    }
//
//    private void createGroupChannel(ChannelRepository repository, String name, String... userIds) {
//        Channel channel = new Channel();
//        channel.setId(UUID.randomUUID().toString());
//        channel.setType(ChannelType.DIRECT_CHANNEL);
//        channel.setName(name);
//        channel.setTopic("Group Chat");
//        channel.setNsfw(false);
//        channel.setOwnerId(userIds[0]);
//
//        Set<String> recipients = new HashSet<>(Arrays.asList(userIds));
//        channel.setRecipients(recipients);
//
//        repository.save(channel);
//    }
//
//    private void createMessagesForChannel(MessageRepository messageRepository, ChannelRepository channelRepository, Channel channel) {
//        List<String> messageTemplates = Arrays.asList(
//            "Merhaba, nasılsın?",
//            "Sprint planning toplantısı için müsait misin?",
//            "Code review'ı tamamladım, bakabilir misin?",
//            "Deployment başarıyla tamamlandı.",
//            "Yeni feature'ı test ettim, sorun yok gibi.",
//            "Stand-up'ta görüşelim mi?",
//            "Pull request'i onaylar mısın?",
//            "Bug fix'i yaptım, test edebilir misin?",
//            "API dokümantasyonunu güncelledim.",
//            "Performance optimizasyonunu tamamladım.",
//            "Database migration'ı başarılı.",
//            "Monitoring alertlerini ayarladım.",
//            "Security patch'lerini uyguladım.",
//            "Yeni versiyonu production'a aldım.",
//            "Cache mekanizmasını optimize ettim."
//        );
//
//        // 10-20 arası rastgele mesaj sayısı
//        int messageCount = 10 + new Random().nextInt(11);
//        String lastMessageId = null;
//
//        for (int i = 0; i < messageCount; i++) {
//            Message message = new Message();
//            message.setId(UUID.randomUUID().toString());
//            message.setChannelId(channel.getId());
//
//            // Channel üyelerinden rastgele bir author seç
//            List<String> recipients = new ArrayList<>(channel.getRecipients());
//            String randomAuthor = recipients.get(new Random().nextInt(recipients.size()));
//            message.setAuthorId(randomAuthor);
//
//            // Rastgele bir mesaj seç
//            message.setContent(messageTemplates.get(new Random().nextInt(messageTemplates.size())));
//
//            // Son 7 gün içinde rastgele bir zaman
//            Instant now = Instant.now();
//            long randomMinutes = new Random().nextInt(7 * 24 * 60);
//            message.setTimestamp(now.minus(randomMinutes, ChronoUnit.MINUTES));
//
//            message.setPinned(false);
//            message.setType(0);
//            message.setAttachments(new HashSet<>());
//            message.setMentions(new HashSet<>());
//            message.setReactions(new ArrayList<>());
//
//            messageRepository.save(message);
//            lastMessageId = message.getId();
//        }
//
//        // Channel'ın son mesaj ID'sini güncelle
//        channel.setLastMessageId(lastMessageId);
//        channel.setTeamId(null);
//        channel.setPosition(null);
//        channel.setUserLimit(null);
//        channel.setRateLimitPerUser(null);
//        channel.setIcon(null);
//        channel.setPermissionOverwrites(null);
//        channelRepository.save(channel);
//    }
}
