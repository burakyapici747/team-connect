INSERT INTO users (id, username, email, password, avatar_url, status, last_seen_at, created_at, updated_at) VALUES
                                                                                                                ('550e8400-e29b-41d4-a716-446655440007', 'sarahconnor', 'sarah.connor@example.com', '$2a$10$QiVif.3ZUjSjsm2jA4IR1OMVZJYScAanjuTMzKC77a1N3rEMtEpsy', 'https://example.com/avatars/sarah.jpg', 'ONLINE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('550e8400-e29b-41d4-a716-446655440008', 'tomanderson', 'tom.anderson@example.com', '$2a$10$QiVif.3ZUjSjsm2jA4IR1OMVZJYScAanjuTMzKC77a1N3rEMtEpsy', 'https://example.com/avatars/tom.jpg', 'AWAY', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('550e8400-e29b-41d4-a716-446655440009', 'emilydavis', 'emily.davis@example.com', '$2a$10$QiVif.3ZUjSjsm2jA4IR1OMVZJYScAanjuTMzKC77a1N3rEMtEpsy', 'https://example.com/avatars/emily.jpg', 'BUSY', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('550e8400-e29b-41d4-a716-446655440010', 'davidwilson', 'david.wilson@example.com', '$2a$10$QiVif.3ZUjSjsm2jA4IR1OMVZJYScAanjuTMzKC77a1N3rEMtEpsy', 'https://example.com/avatars/david.jpg', 'OFFLINE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Bu kullanıcılar için profiller
INSERT INTO user_profiles (id, user_id, avatar_file_id, avatar_file_url, full_name, bio, timezone, language, birthday, gender, theme_preference, created_at, updated_at) VALUES
                                                                                                                                                                             ('650e8400-e29b-41d4-a716-446655440007', '550e8400-e29b-41d4-a716-446655440007', 'file007', 'https://example.com/avatars/sarah.jpg', 'Sarah Connor', 'Quality Assurance Engineer', 'America/Chicago', 'en', '1987-11-20 00:00:00', 'Female', 'light', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                                                             ('650e8400-e29b-41d4-a716-446655440008', '550e8400-e29b-41d4-a716-446655440008', 'file008', 'https://example.com/avatars/tom.jpg', 'Tom Anderson', 'Security Specialist', 'Europe/Berlin', 'en', '1983-04-14 00:00:00', 'Male', 'dark', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                                                             ('650e8400-e29b-41d4-a716-446655440009', '550e8400-e29b-41d4-a716-446655440009', 'file009', 'https://example.com/avatars/emily.jpg', 'Emily Davis', 'Business Analyst', 'America/Denver', 'en', '1991-08-07 00:00:00', 'Female', 'light', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                                                             ('650e8400-e29b-41d4-a716-446655440010', '550e8400-e29b-41d4-a716-446655440010', 'file010', 'https://example.com/avatars/david.jpg', 'David Wilson', 'Technical Writer', 'Asia/Seoul', 'en', '1989-01-30 00:00:00', 'Male', 'dark', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Bu kullanıcılar için roller
INSERT INTO user_role (id, user_id, role_name, created_at, updated_at) VALUES
                                                                           ('750e8400-e29b-41d4-a716-446655440007', '550e8400-e29b-41d4-a716-446655440007', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           ('750e8400-e29b-41d4-a716-446655440008', '550e8400-e29b-41d4-a716-446655440008', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           ('750e8400-e29b-41d4-a716-446655440009', '550e8400-e29b-41d4-a716-446655440009', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           ('750e8400-e29b-41d4-a716-446655440010', '550e8400-e29b-41d4-a716-446655440010', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Yeni team oluşturalım
INSERT INTO team (id, name, icon, description, owner_id, created_at, updated_at) VALUES
                                                                                     ('850e8400-e29b-41d4-a716-446655440005', 'QA Team', 'https://example.com/icons/qa-team.png', 'Quality assurance and testing team', '550e8400-e29b-41d4-a716-446655440007', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                     ('850e8400-e29b-41d4-a716-446655440006', 'Security Team', 'https://example.com/icons/security-team.png', 'Information security and compliance team', '550e8400-e29b-41d4-a716-446655440008', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Bu teamlere üyeler ekleyelim
INSERT INTO team_member (id, team_id, user_id, member_type, created_at, updated_at) VALUES
-- QA Team
('950e8400-e29b-41d4-a716-446655440010', '850e8400-e29b-41d4-a716-446655440005', '550e8400-e29b-41d4-a716-446655440007', 'CREATOR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440011', '850e8400-e29b-41d4-a716-446655440005', '550e8400-e29b-41d4-a716-446655440009', 'MEMBER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Security Team
('950e8400-e29b-41d4-a716-446655440012', '850e8400-e29b-41d4-a716-446655440006', '550e8400-e29b-41d4-a716-446655440008', 'CREATOR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440013', '850e8400-e29b-41d4-a716-446655440006', '550e8400-e29b-41d4-a716-446655440001', 'MEMBER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Bu teamler için channel'lar
INSERT INTO team_channels (team_id, channel_id) VALUES
                                                    ('850e8400-e29b-41d4-a716-446655440005', 'ch_qa_general_010'),
                                                    ('850e8400-e29b-41d4-a716-446655440005', 'ch_qa_bug_reports_011'),
                                                    ('850e8400-e29b-41d4-a716-446655440006', 'ch_security_general_012'),
                                                    ('850e8400-e29b-41d4-a716-446655440006', 'ch_security_incidents_013');

INSERT INTO friendship (id, user1_id, user2_id, status, created_at, updated_at) VALUES
                                                                                    ('a50e8400-e29b-41d4-a716-446655440007', '550e8400-e29b-41d4-a716-446655440007', '550e8400-e29b-41d4-a716-446655440009', 'FRIEND', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                    ('a50e8400-e29b-41d4-a716-446655440008', '550e8400-e29b-41d4-a716-446655440008', '550e8400-e29b-41d4-a716-446655440001', 'FRIEND', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                    ('a50e8400-e29b-41d4-a716-446655440009', '550e8400-e29b-41d4-a716-446655440010', '550e8400-e29b-41d4-a716-446655440002', 'REQ_UID1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                    ('a50e8400-e29b-41d4-a716-446655440010', '550e8400-e29b-41d4-a716-446655440007', '550e8400-e29b-41d4-a716-446655440008', 'REQ_UID2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);