INSERT INTO users (id, username, email, password, avatar_url, status, last_seen_at, created_at, updated_at) VALUES
                                                                                                                ('550e8400-e29b-41d4-a716-446655440001', 'admin', 'admin@teamconnect.com', '$2a$10$QiVif.3ZUjSjsm2jA4IR1OMVZJYScAanjuTMzKC77a1N3rEMtEpsy', 'https://example.com/avatars/admin.jpg', 'ONLINE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('550e8400-e29b-41d4-a716-446655440002', 'johndoe', 'john.doe@example.com', '$2a$10$QiVif.3ZUjSjsm2jA4IR1OMVZJYScAanjuTMzKC77a1N3rEMtEpsy', 'https://example.com/avatars/john.jpg', 'ONLINE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('550e8400-e29b-41d4-a716-446655440003', 'janedoe', 'jane.doe@example.com', '$2a$10$QiVif.3ZUjSjsm2jA4IR1OMVZJYScAanjuTMzKC77a1N3rEMtEpsy', 'https://example.com/avatars/jane.jpg', 'AWAY', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('550e8400-e29b-41d4-a716-446655440004', 'bobsmith', 'bob.smith@example.com', '$2a$10$QiVif.3ZUjSjsm2jA4IR1OMVZJYScAanjuTMzKC77a1N3rEMtEpsy', 'https://example.com/avatars/bob.jpg', 'BUSY', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('550e8400-e29b-41d4-a716-446655440005', 'alicewonder', 'alice.wonder@example.com', '$2a$10$QiVif.3ZUjSjsm2jA4IR1OMVZJYScAanjuTMzKC77a1N3rEMtEpsy', 'https://example.com/avatars/alice.jpg', 'OFFLINE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                ('550e8400-e29b-41d4-a716-446655440006', 'mikejohnson', 'mike.johnson@example.com', '$2a$10$QiVif.3ZUjSjsm2jA4IR1OMVZJYScAanjuTMzKC77a1N3rEMtEpsy', 'https://example.com/avatars/mike.jpg', 'ONLINE', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Mock User Profiles
INSERT INTO user_profiles (id, user_id, avatar_file_id, avatar_file_url, full_name, bio, timezone, language, birthday, gender, theme_preference, created_at, updated_at) VALUES
                                                                                                                                                                             ('650e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440001', 'file001', 'https://example.com/avatars/admin.jpg', 'System Administrator', 'System administrator for TeamConnect', 'UTC', 'en', '1990-01-01 00:00:00', 'Other', 'dark', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                                                             ('650e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440002', 'file002', 'https://example.com/avatars/john.jpg', 'John Doe', 'Software Developer passionate about clean code', 'America/New_York', 'en', '1992-05-15 00:00:00', 'Male', 'light', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                                                             ('650e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440003', 'file003', 'https://example.com/avatars/jane.jpg', 'Jane Doe', 'Product Manager with 5+ years experience', 'Europe/London', 'en', '1988-09-22 00:00:00', 'Female', 'light', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                                                             ('650e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440004', 'file004', 'https://example.com/avatars/bob.jpg', 'Bob Smith', 'DevOps Engineer who loves automation', 'America/Los_Angeles', 'en', '1985-12-03 00:00:00', 'Male', 'dark', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                                                             ('650e8400-e29b-41d4-a716-446655440005', '550e8400-e29b-41d4-a716-446655440005', 'file005', 'https://example.com/avatars/alice.jpg', 'Alice Wonder', 'UI/UX Designer creating beautiful experiences', 'Asia/Tokyo', 'en', '1995-07-11 00:00:00', 'Female', 'light', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                                                                                                             ('650e8400-e29b-41d4-a716-446655440006', '550e8400-e29b-41d4-a716-446655440006', 'file006', 'https://example.com/avatars/mike.jpg', 'Mike Johnson', 'Backend Developer specializing in microservices', 'Australia/Sydney', 'en', '1990-03-28 00:00:00', 'Male', 'dark', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Mock User Roles
INSERT INTO user_role (id, user_id, role_name, created_at, updated_at) VALUES
                                                                           ('750e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440001', 'ADMIN', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           ('750e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440002', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           ('750e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440003', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           ('750e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440004', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           ('750e8400-e29b-41d4-a716-446655440005', '550e8400-e29b-41d4-a716-446655440005', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                           ('750e8400-e29b-41d4-a716-446655440006', '550e8400-e29b-41d4-a716-446655440006', 'USER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Mock Teams
INSERT INTO team (id, name, icon, description, owner_id, created_at, updated_at) VALUES
                                                                                     ('850e8400-e29b-41d4-a716-446655440001', 'Development Team', 'https://example.com/icons/dev-team.png', 'Main development team for core products', '550e8400-e29b-41d4-a716-446655440002', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                     ('850e8400-e29b-41d4-a716-446655440002', 'Design Team', 'https://example.com/icons/design-team.png', 'Creative team responsible for UI/UX design', '550e8400-e29b-41d4-a716-446655440005', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                     ('850e8400-e29b-41d4-a716-446655440003', 'DevOps Team', 'https://example.com/icons/devops-team.png', 'Infrastructure and deployment team', '550e8400-e29b-41d4-a716-446655440004', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                     ('850e8400-e29b-41d4-a716-446655440004', 'Management Team', 'https://example.com/icons/mgmt-team.png', 'Product and project management team', '550e8400-e29b-41d4-a716-446655440003', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Mock Team Members
INSERT INTO team_member (id, team_id, user_id, member_type, created_at, updated_at) VALUES
-- Development Team members
('950e8400-e29b-41d4-a716-446655440001', '850e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440002', 'CREATOR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440002', '850e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440006', 'MEMBER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440003', '850e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440001', 'MEMBER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Design Team members
('950e8400-e29b-41d4-a716-446655440004', '850e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440005', 'CREATOR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440005', '850e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440003', 'MEMBER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- DevOps Team members
('950e8400-e29b-41d4-a716-446655440006', '850e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440004', 'CREATOR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440007', '850e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440001', 'MEMBER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),

-- Management Team members
('950e8400-e29b-41d4-a716-446655440008', '850e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440003', 'CREATOR', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
('950e8400-e29b-41d4-a716-446655440009', '850e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440001', 'MEMBER', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

-- Mock Team Channels (NoSQL channel ID'leri simüle edildi)
INSERT INTO team_channels (team_id, channel_id) VALUES
                                                    ('850e8400-e29b-41d4-a716-446655440001', 'ch_dev_general_001'),
                                                    ('850e8400-e29b-41d4-a716-446655440001', 'ch_dev_random_002'),
                                                    ('850e8400-e29b-41d4-a716-446655440001', 'ch_dev_code_review_003'),
                                                    ('850e8400-e29b-41d4-a716-446655440002', 'ch_design_general_004'),
                                                    ('850e8400-e29b-41d4-a716-446655440002', 'ch_design_inspiration_005'),
                                                    ('850e8400-e29b-41d4-a716-446655440003', 'ch_devops_general_006'),
                                                    ('850e8400-e29b-41d4-a716-446655440003', 'ch_devops_alerts_007'),
                                                    ('850e8400-e29b-41d4-a716-446655440004', 'ch_mgmt_general_008'),
                                                    ('850e8400-e29b-41d4-a716-446655440004', 'ch_mgmt_planning_009');

INSERT INTO friendship (id, user1_id, user2_id, status, created_at, updated_at) VALUES
                                                                                    ('a50e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440003', 'FRIEND', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                    ('a50e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440006', 'FRIEND', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                    ('a50e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440003', '550e8400-e29b-41d4-a716-446655440005', 'FRIEND', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                    ('a50e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440001', 'FRIEND', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                    ('a50e8400-e29b-41d4-a716-446655440005', '550e8400-e29b-41d4-a716-446655440005', '550e8400-e29b-41d4-a716-446655440006', 'REQ_UID1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
                                                                                    ('a50e8400-e29b-41d4-a716-446655440006', '550e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440002', 'FRIEND', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
