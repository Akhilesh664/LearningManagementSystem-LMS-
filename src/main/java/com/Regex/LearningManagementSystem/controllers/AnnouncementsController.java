package com.Regex.LearningManagementSystem.controllers;

import com.Regex.LearningManagementSystem.dto.AnnouncementsRequestDto;
import com.Regex.LearningManagementSystem.dto.AnnouncementsResponseDto;
import com.Regex.LearningManagementSystem.services.AnnouncementsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/announcements")
@RequiredArgsConstructor
public class AnnouncementsController {

    private final AnnouncementsService announcementsService;

    @PostMapping
    public ResponseEntity<AnnouncementsResponseDto> createAnnouncement(@RequestBody AnnouncementsRequestDto requestDto) {
        log.info("Creating announcement for courseId: {} by userId: {}", requestDto.getCourseId(), requestDto.getPostedById());
        AnnouncementsResponseDto response = announcementsService.createAnnouncement(requestDto);
        log.info("Announcement created successfully with ID: {}", response.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnnouncementsResponseDto> getAnnouncementById(@PathVariable Long id) {
        log.info("Fetching announcement with ID: {}", id);
        AnnouncementsResponseDto response = announcementsService.getAnnouncementById(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<AnnouncementsResponseDto>> getAllAnnouncements() {
        log.info("Fetching all announcements");
        List<AnnouncementsResponseDto> announcements = announcementsService.getAllAnnouncements();
        log.info("Total announcements fetched: {}", announcements.size());
        return ResponseEntity.ok(announcements);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<AnnouncementsResponseDto>> getAnnouncementsByCourseId(@PathVariable Long courseId) {
        log.info("Fetching announcements for courseId: {}", courseId);
        List<AnnouncementsResponseDto> announcements = announcementsService.getAnnouncementsByCourseId(courseId);
        return ResponseEntity.ok(announcements);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<AnnouncementsResponseDto>> getAnnouncementsByUserId(@PathVariable Long userId) {
        log.info("Fetching announcements posted by userId: {}", userId);
        List<AnnouncementsResponseDto> announcements = announcementsService.getAnnouncementsByUserId(userId);
        return ResponseEntity.ok(announcements);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnnouncementsResponseDto> updateAnnouncement(@PathVariable Long id, @RequestBody AnnouncementsRequestDto requestDto) {
        log.info("Updating announcement with ID: {}", id);
        AnnouncementsResponseDto response = announcementsService.updateAnnouncement(id, requestDto);
        log.info("Announcement updated successfully with ID: {}", id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAnnouncement(@PathVariable Long id) {
        log.info("Deleting announcement with ID: {}", id);
        announcementsService.deleteAnnouncement(id);
        log.info("Announcement deleted successfully with ID: {}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<AnnouncementsResponseDto>> searchAnnouncements(@RequestParam String query) {
        log.info("Searching announcements with query: {}", query);
        List<AnnouncementsResponseDto> announcements = announcementsService.searchAnnouncements(query);
        return ResponseEntity.ok(announcements);
    }

    @GetMapping("/recent")
    public ResponseEntity<List<AnnouncementsResponseDto>> getRecentAnnouncements() {
        log.info("Fetching recent announcements");
        List<AnnouncementsResponseDto> announcements = announcementsService.getRecentAnnouncements();
        return ResponseEntity.ok(announcements);
    }

    @GetMapping("/course/{courseId}/after")
    public ResponseEntity<List<AnnouncementsResponseDto>> getAnnouncementsByCourseAndDateAfter(
            @PathVariable Long courseId,
            @RequestParam String date) {
        log.info("Fetching announcements for courseId: {} after date: {}", courseId, date);
        List<AnnouncementsResponseDto> announcements = announcementsService.getAnnouncementsByCourseAndDateAfter(courseId, date);
        return ResponseEntity.ok(announcements);
    }

}

// [Important] NOTE: for returning responses codes
// ResponseEntity.ok(response); for 200 OK.
// ResponseEntity.status(HttpStatus.CREATED).body(response); for 201 Created.
// ResponseEntity.noContent().build(); for 204 No Content.