-- V4__Insert_nosql_mock_data.sql
-- NoSQL Mock Data for Couchbase (JSON format)

-- Channels Collection
-- Team Text Channels (SQL'deki team_channels tablosuna karşılık gelir)

-- Development Team Channels
INSERT INTO channels (id, channelType, teamId, name, position, ownerId, createdAt, updatedAt) VALUES
                                                                                                  ('ch_dev_general_001', 0, '850e8400-e29b-41d4-a716-446655440001', 'general', 0, '550e8400-e29b-41d4-a716-446655440002', NOW(), NOW()),
                                                                                                  ('ch_dev_random_002', 0, '850e8400-e29b-41d4-a716-446655440001', 'random', 1, '550e8400-e29b-41d4-a716-446655440002', NOW(), NOW()),
                                                                                                  ('ch_dev_code_review_003', 0, '850e8400-e29b-41d4-a716-446655440001', 'code-review', 2, '550e8400-e29b-41d4-a716-446655440002', NOW(), NOW()),

-- Design Team Channels
                                                                                                  ('ch_design_general_004', 0, '850e8400-e29b-41d4-a716-446655440002', 'general', 0, '550e8400-e29b-41d4-a716-446655440005', NOW(), NOW()),
                                                                                                  ('ch_design_inspiration_005', 0, '850e8400-e29b-41d4-a716-446655440002', 'inspiration', 1, '550e8400-e29b-41d4-a716-446655440005', NOW(), NOW()),

-- DevOps Team Channels
                                                                                                  ('ch_devops_general_006', 0, '850e8400-e29b-41d4-a716-446655440003', 'general', 0, '550e8400-e29b-41d4-a716-446655440004', NOW(), NOW()),
                                                                                                  ('ch_devops_alerts_007', 0, '850e8400-e29b-41d4-a716-446655440003', 'alerts', 1, '550e8400-e29b-41d4-a716-446655440004', NOW(), NOW()),

-- Management Team Channels
                                                                                                  ('ch_mgmt_general_008', 0, '850e8400-e29b-41d4-a716-446655440004', 'general', 0, '550e8400-e29b-41d4-a716-446655440003', NOW(), NOW()),
                                                                                                  ('ch_mgmt_planning_009', 0, '850e8400-e29b-41d4-a716-446655440004', 'planning', 1, '550e8400-e29b-41d4-a716-446655440003', NOW(), NOW()),

-- QA Team Channels
                                                                                                  ('ch_qa_general_010', 0, '850e8400-e29b-41d4-a716-446655440005', 'general', 0, '550e8400-e29b-41d4-a716-446655440007', NOW(), NOW()),
                                                                                                  ('ch_qa_bug_reports_011', 0, '850e8400-e29b-41d4-a716-446655440005', 'bug-reports', 1, '550e8400-e29b-41d4-a716-446655440007', NOW(), NOW()),

-- Security Team Channels
                                                                                                  ('ch_security_general_012', 0, '850e8400-e29b-41d4-a716-446655440006', 'general', 0, '550e8400-e29b-41d4-a716-446655440008', NOW(), NOW()),
                                                                                                  ('ch_security_incidents_013', 0, '850e8400-e29b-41d4-a716-446655440006', 'incidents', 1, '550e8400-e29b-41d4-a716-446655440008', NOW(), NOW()),

-- Direct Message Channels (channelType = 1)
                                                                                                  ('dm_john_jane_001', 1, NULL, NULL, NULL, NULL, NOW(), NOW()),
                                                                                                  ('dm_john_mike_002', 1, NULL, NULL, NULL, NULL, NOW(), NOW()),
                                                                                                  ('dm_jane_alice_003', 1, NULL, NULL, NULL, NULL, NOW(), NOW()),

-- Group DM Channel (channelType = 3)
                                                                                                  ('group_dev_team_001', 3, NULL, NULL, NULL, NULL, NOW(), NOW());

-- Channel Recipients (for DM and Group DM channels)
INSERT INTO channel_recipients (channel_id, user_id) VALUES
-- DM Recipients
('dm_john_jane_001', '550e8400-e29b-41d4-a716-446655440002'),
('dm_john_jane_001', '550e8400-e29b-41d4-a716-446655440003'),
('dm_john_mike_002', '550e8400-e29b-41d4-a716-446655440002'),
('dm_john_mike_002', '550e8400-e29b-41d4-a716-446655440006'),
('dm_jane_alice_003', '550e8400-e29b-41d4-a716-446655440003'),
('dm_jane_alice_003', '550e8400-e29b-41d4-a716-446655440005'),

-- Group DM Recipients
('group_dev_team_001', '550e8400-e29b-41d4-a716-446655440002'),
('group_dev_team_001', '550e8400-e29b-41d4-a716-446655440006'),
('group_dev_team_001', '550e8400-e29b-41d4-a716-446655440001');

-- Files Collection
INSERT INTO files (id, storedFileName, originalName, contentType, fileUrl, size, uploadDate, filePurpose, ownerId, isDeleted) VALUES
-- Avatar Files (SQL'deki user_profiles'tan referans alınarak)
('file001', 'admin_avatar.jpg', 'admin.jpg', 'image/jpeg', 'https://example.com/avatars/admin.jpg', 45000, NOW(), 'AVATAR', '550e8400-e29b-41d4-a716-446655440001', false),
('file002', 'john_avatar.jpg', 'john.jpg', 'image/jpeg', 'https://example.com/avatars/john.jpg', 52000, NOW(), 'AVATAR', '550e8400-e29b-41d4-a716-446655440002', false),
('file003', 'jane_avatar.jpg', 'jane.jpg', 'image/jpeg', 'https://example.com/avatars/jane.jpg', 48000, NOW(), 'AVATAR', '550e8400-e29b-41d4-a716-446655440003', false),
('file004', 'bob_avatar.jpg', 'bob.jpg', 'image/jpeg', 'https://example.com/avatars/bob.jpg', 51000, NOW(), 'AVATAR', '550e8400-e29b-41d4-a716-446655440004', false),
('file005', 'alice_avatar.jpg', 'alice.jpg', 'image/jpeg', 'https://example.com/avatars/alice.jpg', 47000, NOW(), 'AVATAR', '550e8400-e29b-41d4-a716-446655440005', false),
('file006', 'mike_avatar.jpg', 'mike.jpg', 'image/jpeg', 'https://example.com/avatars/mike.jpg', 49000, NOW(), 'AVATAR', '550e8400-e29b-41d4-a716-446655440006', false),
('file007', 'sarah_avatar.jpg', 'sarah.jpg', 'image/jpeg', 'https://example.com/avatars/sarah.jpg', 46000, NOW(), 'AVATAR', '550e8400-e29b-41d4-a716-446655440007', false),

-- Message Attachment Files
('file101', 'project_screenshot_20240127.png', 'screenshot.png', 'image/png', 'https://example.com/files/project_screenshot.png', 125000, NOW(), 'MESSAGE_ATTACHMENT', '550e8400-e29b-41d4-a716-446655440002', false),
('file102', 'design_mockup_v2.figma', 'mockup.figma', 'application/octet-stream', 'https://example.com/files/design_mockup.figma', 2500000, NOW(), 'MESSAGE_ATTACHMENT', '550e8400-e29b-41d4-a716-446655440005', false),
('file103', 'deployment_log_prod.txt', 'deploy.log', 'text/plain', 'https://example.com/files/deployment_log.txt', 15000, NOW(), 'MESSAGE_ATTACHMENT', '550e8400-e29b-41d4-a716-446655440004', false),
('file104', 'voice_note_requirements.mp3', 'voice_message.mp3', 'audio/mpeg', 'https://example.com/files/voice_note.mp3', 850000, NOW(), 'MESSAGE_ATTACHMENT', '550e8400-e29b-41d4-a716-446655440003', false),
('file105', 'api_documentation.pdf', 'api_docs.pdf', 'application/pdf', 'https://example.com/files/api_documentation.pdf', 1200000, NOW(), 'MESSAGE_ATTACHMENT', '550e8400-e29b-41d4-a716-446655440006', false);

-- Messages Collection
INSERT INTO messages (id, channelId, authorId, content, timestamp, editedTimestamp, pinned, type, createdAt, updatedAt) VALUES
-- Development Team - General Channel Messages
('msg_001', 'ch_dev_general_001', '550e8400-e29b-41d4-a716-446655440002', 'Hey team! 👋 Ready for the new sprint?', UNIX_TIMESTAMP(NOW() - INTERVAL 1 HOUR) * 1000, NULL, false, 0, NOW() - INTERVAL 1 HOUR, NOW() - INTERVAL 1 HOUR),
('msg_002', 'ch_dev_general_001', '550e8400-e29b-41d4-a716-446655440006', 'Absolutely! Looking forward to the new features we''ll be building.', UNIX_TIMESTAMP(NOW() - INTERVAL 58 MINUTE) * 1000, NULL, false, 0, NOW() - INTERVAL 58 MINUTE, NOW() - INTERVAL 58 MINUTE),
('msg_003', 'ch_dev_general_001', '550e8400-e29b-41d4-a716-446655440001', 'Don''t forget about the code review guidelines we discussed.', UNIX_TIMESTAMP(NOW() - INTERVAL 56 MINUTE) * 1000, NULL, false, 0, NOW() - INTERVAL 56 MINUTE, NOW() - INTERVAL 56 MINUTE),
('msg_004', 'ch_dev_general_001', '550e8400-e29b-41d4-a716-446655440002', 'Great work everyone on the last release! 🎉', UNIX_TIMESTAMP(NOW() - INTERVAL 5 MINUTE) * 1000, NULL, false, 0, NOW() - INTERVAL 5 MINUTE, NOW() - INTERVAL 5 MINUTE),

-- Development Team - Code Review Channel
('msg_005', 'ch_dev_code_review_003', '550e8400-e29b-41d4-a716-446655440002', 'Here''s the screenshot of the new UI component:', UNIX_TIMESTAMP(NOW() - INTERVAL 30 MINUTE) * 1000, NULL, false, 0, NOW() - INTERVAL 30 MINUTE, NOW() - INTERVAL 30 MINUTE),
('msg_006', 'ch_dev_code_review_003', '550e8400-e29b-41d4-a716-446655440006', 'Looks good! Just a small suggestion about the button alignment.', UNIX_TIMESTAMP(NOW() - INTERVAL 28 MINUTE) * 1000, NULL, false, 0, NOW() - INTERVAL 28 MINUTE, NOW() - INTERVAL 28 MINUTE),

-- Design Team - General Channel
('msg_007', 'ch_design_general_004', '550e8400-e29b-41d4-a716-446655440005', 'Working on the new color palette for the dashboard 🎨', UNIX_TIMESTAMP(NOW() - INTERVAL 40 MINUTE) * 1000, NULL, false, 0, NOW() - INTERVAL 40 MINUTE, NOW() - INTERVAL 40 MINUTE),
('msg_008', 'ch_design_general_004', '550e8400-e29b-41d4-a716-446655440005', 'Here''s the latest mockup:', UNIX_TIMESTAMP(NOW() - INTERVAL 36 MINUTE) * 1000, NULL, false, 0, NOW() - INTERVAL 36 MINUTE, NOW() - INTERVAL 36 MINUTE),

-- DevOps Team - Alerts Channel
('msg_009', 'ch_devops_alerts_007', '550e8400-e29b-41d4-a716-446655440004', '🚨 Deployment completed successfully to production', UNIX_TIMESTAMP(NOW() - INTERVAL 15 MINUTE) * 1000, NULL, false, 0, NOW() - INTERVAL 15 MINUTE, NOW() - INTERVAL 15 MINUTE),
('msg_010', 'ch_devops_alerts_007', '550e8400-e29b-41d4-a716-446655440004', 'Deployment logs attached:', UNIX_TIMESTAMP(NOW() - INTERVAL 13 MINUTE) * 1000, NULL, false, 0, NOW() - INTERVAL 13 MINUTE, NOW() - INTERVAL 13 MINUTE),

-- Direct Messages
('msg_011', 'dm_john_jane_001', '550e8400-e29b-41d4-a716-446655440002', 'Hey Jane, can we sync up about the project timeline?', UNIX_TIMESTAMP(NOW() - INTERVAL 20 MINUTE) * 1000, NULL, false, 0, NOW() - INTERVAL 20 MINUTE, NOW() - INTERVAL 20 MINUTE),
('msg_012', 'dm_john_jane_001', '550e8400-e29b-41d4-a716-446655440003', 'Sure! How about tomorrow at 2 PM?', UNIX_TIMESTAMP(NOW() - INTERVAL 18 MINUTE) * 1000, NULL, false, 0, NOW() - INTERVAL 18 MINUTE, NOW() - INTERVAL 18 MINUTE),
('msg_013', 'dm_john_jane_001', '550e8400-e29b-41d4-a716-446655440003', 'Quick voice note about the requirements:', UNIX_TIMESTAMP(NOW() - INTERVAL 16 MINUTE) * 1000, NULL, false, 0, NOW() - INTERVAL 16 MINUTE, NOW() - INTERVAL 16 MINUTE),

-- QA Team Messages
('msg_014', 'ch_qa_general_010', '550e8400-e29b-41d4-a716-446655440007', 'Starting regression tests for the new release 🧪', UNIX_TIMESTAMP(NOW() - INTERVAL 45 MINUTE) * 1000, NULL, false, 0, NOW() - INTERVAL 45 MINUTE, NOW() - INTERVAL 45 MINUTE),
('msg_015', 'ch_qa_bug_reports_011', '550e8400-e29b-41d4-a716-446655440009', 'Found a minor UI issue in the login form', UNIX_TIMESTAMP(NOW() - INTERVAL 25 MINUTE) * 1000, NULL, false, 0, NOW() - INTERVAL 25 MINUTE, NOW() - INTERVAL 25 MINUTE);

-- Message Attachments
INSERT INTO message_attachments (message_id, attachment_id, name, title, description, contentType, size, url, height, width, ephemeral, durationSecs, waveform, flags, type) VALUES
                                                                                                                                                                                 ('msg_005', 'att_001', 'project_screenshot.png', 'Project Screenshot', 'Screenshot of the new UI component', 'image/png', 125000, 'https://example.com/files/project_screenshot.png', 800, 1200, false, NULL, NULL, 0, 'IMAGE'),
                                                                                                                                                                                 ('msg_008', 'att_002', 'design_mockup.figma', 'Design Mockup', 'Latest UI mockup for dashboard', 'application/octet-stream', 2500000, 'https://example.com/files/design_mockup.figma', NULL, NULL, false, NULL, NULL, 0, 'DOCUMENT'),
                                                                                                                                                                                 ('msg_010', 'att_003', 'deployment_log.txt', 'Deployment Log', 'Production deployment logs', 'text/plain', 15000, 'https://example.com/files/deployment_log.txt', NULL, NULL, false, NULL, NULL, 0, 'DOCUMENT'),
                                                                                                                                                                                 ('msg_013', 'att_004', 'voice_note.mp3', 'Voice Message', 'Voice note about project requirements', 'audio/mpeg', 850000, 'https://example.com/files/voice_note.mp3', NULL, NULL, false, 45, '[0.2,0.8,0.6,0.9,0.4,0.7,0.3,0.8,0.5,0.6]', 0, 'AUDIO');

-- Message Mentions
INSERT INTO message_mentions (message_id, mention_id, userId) VALUES
                                                                  ('msg_006', 'mention_001', '550e8400-e29b-41d4-a716-446655440002'),
                                                                  ('msg_012', 'mention_002', '550e8400-e29b-41d4-a716-446655440005');

-- Message Reactions
INSERT INTO message_reactions (message_id, emoji, count, user_ids) VALUES
                                                                       ('msg_004', '👍', 2, '["550e8400-e29b-41d4-a716-446655440006","550e8400-e29b-41d4-a716-446655440001"]'),
                                                                       ('msg_004', '🎉', 1, '["550e8400-e29b-41d4-a716-446655440006"]'),
                                                                       ('msg_007', '🎨', 2, '["550e8400-e29b-41d4-a716-446655440003","550e8400-e29b-41d4-a716-446655440002"]'),
                                                                       ('msg_009', '✅', 3, '["550e8400-e29b-41d4-a716-446655440001","550e8400-e29b-41d4-a716-446655440002","550e8400-e29b-41d4-a716-446655440006"]'),
                                                                       ('msg_014', '🧪', 1, '["550e8400-e29b-41d4-a716-446655440009"]');