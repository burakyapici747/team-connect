package com.teamconnect.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import com.teamconnect.api.input.user.*;
import com.teamconnect.common.enumarator.FilePurposeType;
import com.teamconnect.dto.*;
import com.teamconnect.model.nosql.File;
import com.teamconnect.model.sql.UserRole;
import com.teamconnect.service.ChannelService;
import com.teamconnect.service.FileService;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.teamconnect.common.enumarator.Role;
import com.teamconnect.exception.UserAlreadyExistsException;
import com.teamconnect.exception.UserNotFoundException;
import com.teamconnect.model.sql.User;
import com.teamconnect.model.sql.UserProfile;
import com.teamconnect.repository.postgresql.UserProfileRepository;
import com.teamconnect.repository.postgresql.UserRepository;
import com.teamconnect.service.UserService;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UserServiceImpl {
}
