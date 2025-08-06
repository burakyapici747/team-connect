package com.teamconnect.api.controller;

import com.teamconnect.api.input.guild.GuildChannelCreateInput;
import com.teamconnect.api.input.guild.GuildChannelPositionModifyInput;
import com.teamconnect.api.input.guild.GuildModifyInput;
import com.teamconnect.api.input.team.GuildCreateInput;
import com.teamconnect.api.output.ResponseWrapper;
import com.teamconnect.api.output.team.TeamCreateOutput;
import com.teamconnect.dto.ChannelDto;
import com.teamconnect.security.CustomUserDetails;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping(path = "/api/v1/guilds")
public class GuildController {
    @PostMapping
    public ResponseEntity<ResponseWrapper<TeamCreateOutput>> createGuild(
        @AuthenticationPrincipal UserDetails userDetails,
        @RequestPart("input") @Valid GuildCreateInput input,
        @RequestPart("file") MultipartFile file
    ) throws IOException {
        return null;
    }

    @GetMapping(path = "/{guildId}")
    public ResponseEntity<ResponseWrapper<Void>> getGuild(
        @PathVariable("guildId") UUID guildId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @GetMapping(path = "/{guildId}/preview")
    public ResponseEntity<ResponseWrapper<Void>> getGuildPreview(
        @PathVariable("guildId") UUID guildId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @PutMapping(path = "/{guildId}/preview")
    public ResponseEntity<ResponseWrapper<Void>> modifyGuild(
        @PathVariable("guildId") UUID guildId,
        @RequestBody GuildModifyInput input,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @DeleteMapping("/{guildId}")
    public ResponseEntity<ResponseWrapper<Void>> deleteGuild(
        @PathVariable("guildId") UUID guildId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @GetMapping(path = "/{guildId}/channels")
    public ResponseEntity<ResponseWrapper<ChannelDto>> getChannels(
        @PathVariable("guildId") UUID guildId,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        return null;
    }

    @PostMapping(path = "/{guildId}/channels")
    public ResponseEntity<ResponseWrapper<Void>> createChannel(
        @PathVariable("guildId") UUID guildId,
        @RequestBody GuildChannelCreateInput input,
        @AuthenticationPrincipal CustomUserDetails userDetails
    ){
        return null;
    }

    @PatchMapping(path = "/{guildId}/channels")
    public ResponseEntity<ResponseWrapper<Void>> modifyGuildChannelPosition(
        @PathVariable("guildId") UUID guildId,
        @RequestBody GuildChannelPositionModifyInput input,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @GetMapping(path = "/{guildId}/members/{userId}")
    public ResponseEntity<ResponseWrapper<Void>> getGuildMember(
        @PathVariable("guildId") UUID guildId,
        @PathVariable("userId") UUID userId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @GetMapping(path = "/{guildId/members}")
    public ResponseEntity<ResponseWrapper<Void>> getGuildMembers(
        @PathVariable("guildId") UUID guildId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @GetMapping(path = "/{guildId/members/search}")
    public ResponseEntity<ResponseWrapper<Void>> searchGuildMembers(
        @PathVariable("guildId") UUID guildId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @PutMapping(path = "/{guildId}/members/{userId}")
    public ResponseEntity<ResponseWrapper<Void>> addGuildMember(
        @PathVariable("guildId") UUID guildId,
        @PathVariable("userId") UUID userId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @PatchMapping(path = "/{guildId}/members/{userId}")
    public ResponseEntity<ResponseWrapper<Void>> modifyGuildMember(
        @PathVariable("guildId") UUID guildId,
        @PathVariable("userId") UUID userId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @PatchMapping(path = "/{guildId}/members/@me")
    public ResponseEntity<ResponseWrapper<Void>> modifyCurrentGuildMember(
        @PathVariable("guildId") UUID guildId,
        @PathVariable("userId") UUID userId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }

    @DeleteMapping(path = "/{guildId/members/{userId}")
    public ResponseEntity<ResponseWrapper<Void>> removeGuildMember(
        @PathVariable("guildId") UUID guildId,
        @PathVariable("userId") UUID userId,
        @AuthenticationPrincipal CustomUserDetails user
    ){
        return null;
    }
}
