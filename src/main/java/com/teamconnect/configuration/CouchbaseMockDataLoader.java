package com.teamconnect.configuration;

import com.teamconnect.common.enumarator.ChannelType;
import com.teamconnect.common.enumarator.FilePurposeType;
import com.teamconnect.common.enumarator.FileType;
import com.teamconnect.model.nosql.*;
import com.teamconnect.repository.couchbase.ChannelRepository;
import com.teamconnect.repository.couchbase.FileRepository;
import com.teamconnect.repository.couchbase.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.*;

@Component
public class CouchbaseMockDataLoader implements CommandLineRunner {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public void run(String... args) throws Exception {
        if (channelRepository.count() == 0) {
            System.out.println("Loading NoSQL mock data...");
            loadMockData();
            System.out.println("NoSQL mock data loaded successfully!");
        }
    }

    private void loadMockData() {
        // Files oluştur
        List<File> files = createFiles();
        fileRepository.saveAll(files);

        // Channels oluştur
        List<Channel> channels = createChannels();
        channelRepository.saveAll(channels);

        // Messages oluştur
        List<Message> messages = createMessages();
        messageRepository.saveAll(messages);
    }

    private List<Channel> createChannels() {
        List<Channel> channels = new ArrayList<>();
        Instant now = Instant.now();

        // Development Team Channels
        channels.add(createTeamTextChannel("ch_dev_general_001", "850e8400-e29b-41d4-a716-446655440001",
            "general", 0, "550e8400-e29b-41d4-a716-446655440002", now));
        channels.add(createTeamTextChannel("ch_dev_random_002", "850e8400-e29b-41d4-a716-446655440001",
            "random", 1, "550e8400-e29b-41d4-a716-446655440002", now));
        channels.add(createTeamTextChannel("ch_dev_code_review_003", "850e8400-e29b-41d4-a716-446655440001",
            "code-review", 2, "550e8400-e29b-41d4-a716-446655440002", now));

        // Design Team Channels
        channels.add(createTeamTextChannel("ch_design_general_004", "850e8400-e29b-41d4-a716-446655440002",
            "general", 0, "550e8400-e29b-41d4-a716-446655440005", now));
        channels.add(createTeamTextChannel("ch_design_inspiration_005", "850e8400-e29b-41d4-a716-446655440002",
            "inspiration", 1, "550e8400-e29b-41d4-a716-446655440005", now));

        // DevOps Team Channels
        channels.add(createTeamTextChannel("ch_devops_general_006", "850e8400-e29b-41d4-a716-446655440003",
            "general", 0, "550e8400-e29b-41d4-a716-446655440004", now));
        channels.add(createTeamTextChannel("ch_devops_alerts_007", "850e8400-e29b-41d4-a716-446655440003",
            "alerts", 1, "550e8400-e29b-41d4-a716-446655440004", now));

        // Management Team Channels
        channels.add(createTeamTextChannel("ch_mgmt_general_008", "850e8400-e29b-41d4-a716-446655440004",
            "general", 0, "550e8400-e29b-41d4-a716-446655440003", now));
        channels.add(createTeamTextChannel("ch_mgmt_planning_009", "850e8400-e29b-41d4-a716-446655440004",
            "planning", 1, "550e8400-e29b-41d4-a716-446655440003", now));

        // QA Team Channels
        channels.add(createTeamTextChannel("ch_qa_general_010", "850e8400-e29b-41d4-a716-446655440005",
            "general", 0, "550e8400-e29b-41d4-a716-446655440007", now));
        channels.add(createTeamTextChannel("ch_qa_bug_reports_011", "850e8400-e29b-41d4-a716-446655440005",
            "bug-reports", 1, "550e8400-e29b-41d4-a716-446655440007", now));

        // Security Team Channels
        channels.add(createTeamTextChannel("ch_security_general_012", "850e8400-e29b-41d4-a716-446655440006",
            "general", 0, "550e8400-e29b-41d4-a716-446655440008", now));
        channels.add(createTeamTextChannel("ch_security_incidents_013", "850e8400-e29b-41d4-a716-446655440006",
            "incidents", 1, "550e8400-e29b-41d4-a716-446655440008", now));

        // Direct Messages (friendship tablosundaki FRIEND durumundaki kullanıcılar arasında)
        channels.add(createDMChannel("dm_john_jane_001",
            Set.of("550e8400-e29b-41d4-a716-446655440002", "550e8400-e29b-41d4-a716-446655440003"), now));
        channels.add(createDMChannel("dm_john_mike_002",
            Set.of("550e8400-e29b-41d4-a716-446655440002", "550e8400-e29b-41d4-a716-446655440006"), now));
        channels.add(createDMChannel("dm_jane_alice_003",
            Set.of("550e8400-e29b-41d4-a716-446655440003", "550e8400-e29b-41d4-a716-446655440005"), now));

        // Group DM örneği
        channels.add(createGroupDMChannel("group_dev_team_001",
            Set.of("550e8400-e29b-41d4-a716-446655440002", "550e8400-e29b-41d4-a716-446655440006", "550e8400-e29b-41d4-a716-446655440001"), now));

        return channels;
    }

    private Channel createTeamTextChannel(String id, String teamId, String name, int position, String ownerId, Instant timestamp) {
        Channel channel = new Channel();
        channel.setId(id);
        channel.setChannelType(ChannelType.TEAM_TEXT);
        channel.setTeamId(teamId);
        channel.setName(name);
        channel.setPosition(position);
        channel.setOwnerId(ownerId);
        channel.setPermissionOverwrites(0);
        channel.setCreatedAt(timestamp);
        channel.setUpdatedAt(timestamp);
        return channel;
    }

    private Channel createDMChannel(String id, Set<String> recipients, Instant timestamp) {
        Channel channel = new Channel();
        channel.setId(id);
        channel.setChannelType(ChannelType.DM);
        channel.setRecipients(recipients);
        channel.setCreatedAt(timestamp);
        channel.setUpdatedAt(timestamp);
        return channel;
    }

    private Channel createGroupDMChannel(String id, Set<String> recipients, Instant timestamp) {
        Channel channel = new Channel();
        channel.setId(id);
        channel.setChannelType(ChannelType.GROUP_DM);
        channel.setRecipients(recipients);
        channel.setCreatedAt(timestamp);
        channel.setUpdatedAt(timestamp);
        return channel;
    }

    private List<File> createFiles() {
        List<File> files = new ArrayList<>();
        Instant now = Instant.now();

        // Avatar dosyaları (SQL'deki user_profiles'tan referans alınarak)
        files.add(createFile("file001", "admin_avatar.jpg", "admin.jpg", "image/jpeg",
            "https://example.com/avatars/admin.jpg", 45000L, "AVATAR", "550e8400-e29b-41d4-a716-446655440001", now));
        files.add(createFile("file002", "john_avatar.jpg", "john.jpg", "image/jpeg",
            "https://example.com/avatars/john.jpg", 52000L, "AVATAR", "550e8400-e29b-41d4-a716-446655440002", now));
        files.add(createFile("file003", "jane_avatar.jpg", "jane.jpg", "image/jpeg",
            "https://example.com/avatars/jane.jpg", 48000L, "AVATAR", "550e8400-e29b-41d4-a716-446655440003", now));
        files.add(createFile("file004", "bob_avatar.jpg", "bob.jpg", "image/jpeg",
            "https://example.com/avatars/bob.jpg", 51000L, "AVATAR", "550e8400-e29b-41d4-a716-446655440004", now));
        files.add(createFile("file005", "alice_avatar.jpg", "alice.jpg", "image/jpeg",
            "https://example.com/avatars/alice.jpg", 47000L, "AVATAR", "550e8400-e29b-41d4-a716-446655440005", now));
        files.add(createFile("file006", "mike_avatar.jpg", "mike.jpg", "image/jpeg",
            "https://example.com/avatars/mike.jpg", 49000L, "AVATAR", "550e8400-e29b-41d4-a716-446655440006", now));
        files.add(createFile("file007", "sarah_avatar.jpg", "sarah.jpg", "image/jpeg",
            "https://example.com/avatars/sarah.jpg", 46000L, "AVATAR", "550e8400-e29b-41d4-a716-446655440007", now));

        // Mesaj eklenti dosyaları
        files.add(createFile("file101", "project_screenshot.png", "screenshot.png", "image/png",
            "https://example.com/files/project_screenshot.png", 125000L, "ATTACHMENT", "550e8400-e29b-41d4-a716-446655440002", now));
        files.add(createFile("file102", "design_mockup.figma", "mockup.figma", "application/octet-stream",
            "https://example.com/files/design_mockup.figma", 2500000L, "ATTACHMENT", "550e8400-e29b-41d4-a716-446655440005", now));
        files.add(createFile("file103", "deployment_log.txt", "deploy.log", "text/plain",
            "https://example.com/files/deployment_log.txt", 15000L, "ATTACHMENT", "550e8400-e29b-41d4-a716-446655440004", now));
        files.add(createFile("file104", "voice_note.mp3", "voice_message.mp3", "audio/mpeg",
            "https://example.com/files/voice_note.mp3", 850000L, "ATTACHMENT", "550e8400-e29b-41d4-a716-446655440003", now));
        files.add(createFile("file105", "api_docs.pdf", "api_documentation.pdf", "application/pdf",
            "https://example.com/files/api_docs.pdf", 1200000L, "ATTACHMENT", "550e8400-e29b-41d4-a716-446655440006", now));

        return files;
    }

    private File createFile(String id, String storedName, String originalName, String contentType,
                            String url, Long size, String purpose, String ownerId, Instant timestamp) {
        File file = new File();
        file.setId(id);
        file.setStoredFileName(storedName);
        file.setOriginalName(originalName);
        file.setContentType(contentType);
        file.setFileUrl(url);
        file.setSize(size);
        file.setUploadDate(timestamp);
        file.setFilePurpose(FilePurposeType.valueOf(purpose));
        file.setOwnerId(ownerId);
        file.setDeleted(false);
        return file;
    }

    private List<Message> createMessages() {
        List<Message> messages = new ArrayList<>();
        Instant now = Instant.now();

        // Development Team - General Channel Messages
        String devGeneralChannel = "ch_dev_general_001";
        messages.add(createMessage("msg_001", devGeneralChannel, "550e8400-e29b-41d4-a716-446655440002",
            "Hey team! 👋 Ready for the new sprint?", now.minusSeconds(3600), 0));
        messages.add(createMessage("msg_002", devGeneralChannel, "550e8400-e29b-41d4-a716-446655440006",
            "Absolutely! Looking forward to the new features we'll be building.", now.minusSeconds(3500), 0));
        messages.add(createMessage("msg_003", devGeneralChannel, "550e8400-e29b-41d4-a716-446655440001",
            "Don't forget about the code review guidelines we discussed.", now.minusSeconds(3400), 0));

        // Message with reactions
        Message msgWithReactions = createMessage("msg_004", devGeneralChannel, "550e8400-e29b-41d4-a716-446655440002",
            "Great work everyone on the last release! 🎉", now.minusSeconds(300), 0);
        msgWithReactions.getReactions().add(createReaction("👍", List.of("550e8400-e29b-41d4-a716-446655440006", "550e8400-e29b-41d4-a716-446655440001")));
        msgWithReactions.getReactions().add(createReaction("🎉", List.of("550e8400-e29b-41d4-a716-446655440006")));
        messages.add(msgWithReactions);

        // Development Team - Code Review Channel
        String codeReviewChannel = "ch_dev_code_review_003";
        Message msgWithAttachment = createMessage("msg_005", codeReviewChannel, "550e8400-e29b-41d4-a716-446655440002",
            "Here's the screenshot of the new UI component:", now.minusSeconds(1800), 0);
        msgWithAttachment.getAttachments().add(createScreenshotAttachment());
        messages.add(msgWithAttachment);

        messages.add(createMessage("msg_006", codeReviewChannel, "550e8400-e29b-41d4-a716-446655440006",
            "Looks good! Just a small suggestion about the button alignment.", now.minusSeconds(1700), 0));

        // Design Team - General Channel
        String designGeneralChannel = "ch_design_general_004";
        messages.add(createMessage("msg_007", designGeneralChannel, "550e8400-e29b-41d4-a716-446655440005",
            "Working on the new color palette for the dashboard 🎨", now.minusSeconds(2400), 0));

        Message designMsgWithAttachment = createMessage("msg_008", designGeneralChannel, "550e8400-e29b-41d4-a716-446655440005",
            "Here's the latest mockup:", now.minusSeconds(2200), 0);
        designMsgWithAttachment.getAttachments().add(createFigmaAttachment());
        messages.add(designMsgWithAttachment);

        // DevOps Team - Alerts Channel
        String devOpsAlertsChannel = "ch_devops_alerts_007";
        Message deploymentMsg = createMessage("msg_009", devOpsAlertsChannel, "550e8400-e29b-41d4-a716-446655440004",
            "🚨 Deployment completed successfully to production", now.minusSeconds(900), 0);
        deploymentMsg.getReactions().add(createReaction("✅", List.of("550e8400-e29b-41d4-a716-446655440001", "550e8400-e29b-41d4-a716-446655440002")));
        messages.add(deploymentMsg);

        Message logMsg = createMessage("msg_010", devOpsAlertsChannel, "550e8400-e29b-41d4-a716-446655440004",
            "Deployment logs attached:", now.minusSeconds(800), 0);
        logMsg.getAttachments().add(createLogAttachment());
        messages.add(logMsg);

        // Direct Messages
        String dmChannel = "dm_john_jane_001";
        messages.add(createMessage("msg_011", dmChannel, "550e8400-e29b-41d4-a716-446655440002",
            "Hey Jane, can we sync up about the project timeline?", now.minusSeconds(1200), 0));
        messages.add(createMessage("msg_012", dmChannel, "550e8400-e29b-41d4-a716-446655440003",
            "Sure! How about tomorrow at 2 PM?", now.minusSeconds(1100), 0));

        Message voiceMsg = createMessage("msg_013", dmChannel, "550e8400-e29b-41d4-a716-446655440003",
            "Quick voice note about the requirements:", now.minusSeconds(1000), 0);
        voiceMsg.getAttachments().add(createVoiceAttachment());
        messages.add(voiceMsg);

        // QA Team Messages
        String qaGeneralChannel = "ch_qa_general_010";
        Message qaMsg = createMessage("msg_014", qaGeneralChannel, "550e8400-e29b-41d4-a716-446655440007",
            "Starting regression tests for the new release 🧪", now.minusSeconds(2700), 0);
        qaMsg.getReactions().add(createReaction("🧪", List.of("550e8400-e29b-41d4-a716-446655440009")));
        messages.add(qaMsg);

        String qaBugChannel = "ch_qa_bug_reports_011";
        messages.add(createMessage("msg_015", qaBugChannel, "550e8400-e29b-41d4-a716-446655440009",
            "Found a minor UI issue in the login form", now.minusSeconds(1500), 0));

        // Security Team
        String securityChannel = "ch_security_general_012";
        Message securityMsg = createMessage("msg_016", securityChannel, "550e8400-e29b-41d4-a716-446655440008",
            "Security audit completed for Q1 🔒", now.minusSeconds(3200), 0);
        securityMsg.getReactions().add(createReaction("🔒", List.of("550e8400-e29b-41d4-a716-446655440001")));
        messages.add(securityMsg);

        // Group DM Messages
        String groupDmChannel = "group_dev_team_001";
        messages.add(createMessage("msg_017", groupDmChannel, "550e8400-e29b-41d4-a716-446655440002",
            "Hey team, shall we have a quick standup call?", now.minusSeconds(600), 0));
        messages.add(createMessage("msg_018", groupDmChannel, "550e8400-e29b-41d4-a716-446655440006",
            "Sure! I'm available now", now.minusSeconds(580), 0));

        Message standupMsg = createMessage("msg_019", groupDmChannel, "550e8400-e29b-41d4-a716-446655440001",
            "Let me join in 5 minutes", now.minusSeconds(560), 0);
        standupMsg.getReactions().add(createReaction("👍", List.of("550e8400-e29b-41d4-a716-446655440002", "550e8400-e29b-41d4-a716-446655440006")));
        messages.add(standupMsg);

        // Pinned message with document
        Message pinnedMsg = createMessage("msg_020", devGeneralChannel, "550e8400-e29b-41d4-a716-446655440006",
            "Here's the API documentation for the new endpoints:", now.minusSeconds(480), 0);
        pinnedMsg.setPinned(true);
        pinnedMsg.getAttachments().add(createPdfAttachment());
        pinnedMsg.getReactions().add(createReaction("📚", List.of("550e8400-e29b-41d4-a716-446655440002", "550e8400-e29b-41d4-a716-446655440001")));
        messages.add(pinnedMsg);

        return messages;
    }

    private Message createMessage(String id, String channelId, String authorId, String content, Instant timestamp, int type) {
        Message message = new Message();
        message.setId(id);
        message.setChannelId(channelId);
        message.setAuthorId(authorId);
        message.setContent(content);
        message.setTimestamp(timestamp.toEpochMilli());
        message.setType(type);
        message.setPinned(false);
        message.setCreatedAt(timestamp);
        message.setUpdatedAt(timestamp);
        message.setAttachments(new HashSet<>());
        message.setMentions(new HashSet<>());
        message.setReactions(new ArrayList<>());
        return message;
    }

    private Attachment createScreenshotAttachment() {
        Attachment attachment = new Attachment();
        attachment.setId("att_001");
        attachment.setName("project_screenshot.png");
        attachment.setTitle("Project Screenshot");
        attachment.setDescription("Screenshot of the new UI component");
        attachment.setContentType("image/png");
        attachment.setSize(125000L);
        attachment.setUrl("https://example.com/files/project_screenshot.png");
        attachment.setHeight(800);
        attachment.setWidth(1200);
        attachment.setEphemeral(false);
        attachment.setType(FileType.IMAGE);
        return attachment;
    }

    private Attachment createFigmaAttachment() {
        Attachment attachment = new Attachment();
        attachment.setId("att_002");
        attachment.setName("design_mockup.figma");
        attachment.setTitle("Design Mockup");
        attachment.setDescription("Latest UI mockup for dashboard");
        attachment.setContentType("application/octet-stream");
        attachment.setSize(2500000L);
        attachment.setUrl("https://example.com/files/design_mockup.figma");
        attachment.setEphemeral(false);
        attachment.setType(FileType.DOCUMENT);
        return attachment;
    }

    private Attachment createLogAttachment() {
        Attachment attachment = new Attachment();
        attachment.setId("att_003");
        attachment.setName("deployment_log.txt");
        attachment.setTitle("Deployment Log");
        attachment.setDescription("Production deployment logs");
        attachment.setContentType("text/plain");
        attachment.setSize(15000L);
        attachment.setUrl("https://example.com/files/deployment_log.txt");
        attachment.setEphemeral(false);
        attachment.setType(FileType.DOCUMENT);
        return attachment;
    }

    private Attachment createVoiceAttachment() {
        Attachment attachment = new Attachment();
        attachment.setId("att_004");
        attachment.setName("voice_note.mp3");
        attachment.setTitle("Voice Message");
        attachment.setDescription("Voice note about project requirements");
        attachment.setContentType("audio/mpeg");
        attachment.setSize(850000L);
        attachment.setUrl("https://example.com/files/voice_note.mp3");
        attachment.setDurationSecs(45);
        attachment.setWaveform("[0.2,0.8,0.6,0.9,0.4,0.7,0.3,0.8,0.5,0.6]");
        attachment.setEphemeral(false);
        attachment.setType(FileType.AUDIO);
        return attachment;
    }

    private Attachment createPdfAttachment() {
        Attachment attachment = new Attachment();
        attachment.setId("att_005");
        attachment.setName("api_documentation.pdf");
        attachment.setTitle("API Documentation");
        attachment.setDescription("Complete API documentation for v2.0");
        attachment.setContentType("application/pdf");
        attachment.setSize(1200000L);
        attachment.setUrl("https://example.com/files/api_documentation.pdf");
        attachment.setEphemeral(false);
        attachment.setType(FileType.DOCUMENT);
        return attachment;
    }

    private Map<String, Object> createReaction(String emoji, List<String> userIds) {
        Map<String, Object> reaction = new HashMap<>();
        reaction.put("emoji", emoji);
        reaction.put("count", userIds.size());
        reaction.put("users", userIds);
        return reaction;
    }
}
