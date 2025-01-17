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

//    private static final String USER1 = "02674c58-7db3-43b1-a09e-8a36216069c5";
//    private static final String USER2 = "f5a15b66-3b93-4554-a2fe-f88bd87f3855";
//    private static final String USER3 = "db08a20a-079b-4921-b917-55828b0c0af2";
//    private static final String USER4 = "22682bca-16a8-4eda-a6fd-60c2384ab7a2";
//    private static final String USER5 = "e50eb138-aa7c-4845-a9e0-832eaeb47031";
//
//    @Bean
//    public CommandLineRunner initializeData(ChannelRepository channelRepository, MessageRepository messageRepository) {
//        return args -> {
//            // Örnek mesaj içerikleri - her kullanıcı için özel mesajlar
//            Map<String, List<String>> userSpecificMessages = new HashMap<>();
//
//            userSpecificMessages.put(USER1, Arrays.asList(
//                "Herkese günaydın!",
//                "Sprint planning toplantısını 14:00'e alabiliriz mi?",
//                "Code review'ları tamamladım.",
//                "Deployment için hazırım."
//            ));
//
//            userSpecificMessages.put(USER2, Arrays.asList(
//                "Backend servisleri test ettim, sorun yok.",
//                "Pull request'i inceleyebilir misiniz?",
//                "Veritabanı optimizasyonunu tamamladım.",
//                "Monitoring araçlarını kurdum."
//            ));
//
//            userSpecificMessages.put(USER3, Arrays.asList(
//                "Frontend değişikliklerini push ettim.",
//                "UI testleri başarılı.",
//                "Design system güncellemesi hazır.",
//                "Component library'yi güncelledim."
//            ));
//
//            userSpecificMessages.put(USER4, Arrays.asList(
//                "API dokümantasyonunu güncelledim.",
//                "Performance testlerini çalıştırdım.",
//                "Cache mekanizmasını optimize ettim.",
//                "Security patch'leri uyguladım."
//            ));
//
//            userSpecificMessages.put(USER5, Arrays.asList(
//                "DevOps pipeline'ı güncelledim.",
//                "Kubernetes cluster'ı hazır.",
//                "Monitoring alertlerini ayarladım.",
//                "Backup sistemi kuruldu."
//            ));
//
//            // Var olan kanalları al
//            List<Channel> channels = channelRepository.findAll();
//
//            // Her kanal için mesajlar oluştur
//            for (Channel channel : channels) {
//                // Her kanal için rastgele sayıda mesaj (10-20 arası)
//                int messageCount = 10 + new Random().nextInt(11);
//
//                String lastMessageId = null;
//
//                for (int i = 0; i < messageCount; i++) {
//                    Message message = new Message();
//                    message.setId(UUID.randomUUID().toString());
//                    message.setChannelId(channel.getId());
//
//                    // Kanal üyelerinden rastgele bir author seç
//                    Set<String> recipients = channel.getRecipients();
//                    List<String> recipientsList = new ArrayList<>(recipients);
//                    String randomAuthor = recipientsList.get(new Random().nextInt(recipientsList.size()));
//                    message.setAuthorId(randomAuthor);
//
//                    // Seçilen author'a özel mesajlardan birini seç
//                    List<String> authorMessages = userSpecificMessages.get(randomAuthor);
//                    message.setContent(authorMessages.get(new Random().nextInt(authorMessages.size())));
//
//                    // Zaman damgası oluştur (son 7 gün içinde rastgele)
//                    Instant now = Instant.now();
//                    long randomMinutes = new Random().nextInt(7 * 24 * 60); // 7 gün içinde rastgele dakika
//                    message.setTimestamp(now.minus(randomMinutes, ChronoUnit.MINUTES));
//
//                    message.setPinned(false);
//                    message.setType(0); // Normal mesaj
//                    message.setAttachments(new HashSet<>());
//                    message.setMentions(new HashSet<>());
//                    message.setReactions(new ArrayList<>());
//
//                    // Mesajı kaydet
//                    messageRepository.save(message);
//                    lastMessageId = message.getId();
//                }
//
//                // Kanalın son mesaj ID'sini güncelle
//                channel.setLastMessageId(lastMessageId);
//                channelRepository.save(channel);
//            }
//        };
//    }
}
