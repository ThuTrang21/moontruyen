package com.mt.mootruyen.controller;

import com.mt.mootruyen.dto.request.ApiResponse;
import com.mt.mootruyen.dto.request.StoryCreationRequest;
import com.mt.mootruyen.dto.request.StoryUpdateRequest;
import com.mt.mootruyen.entity.Story;
import com.mt.mootruyen.service.StoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stories")
public class StoryController {
    @Autowired
    private StoryService storyService;

    @GetMapping
    ApiResponse<List<Story>> getAllStories() {
        return ApiResponse.<List<Story>>builder()
                .data(storyService.getAllStories())
                .build();
    }
    @GetMapping("/id/{storyId}")
    ApiResponse<Story> getStory(@PathVariable String storyId) {
        return ApiResponse.<Story>builder()
                .data(storyService.getStoryById(storyId))
                .build();
    }

    @GetMapping("{slug}")
    ApiResponse<Story> getStoryBySlug(@PathVariable String slug) {
        return ApiResponse.<Story>builder()
                .data(storyService.getStoryBySlug(slug))
                .build();
    }

    @PostMapping
    ApiResponse<Story> createStory(@RequestBody StoryCreationRequest request){
        return ApiResponse.<Story>builder()
                .data(storyService.createStory(request))
                .build();
    }

    @PutMapping("{storyId}")
    ApiResponse<Story> updateStory(@PathVariable String storyId, @RequestBody StoryUpdateRequest request){
        return ApiResponse.<Story>builder()
                .data(storyService.updateStory(storyId, request))
                .build();
    }

    @DeleteMapping("{storyId}")
    ApiResponse<String> deleteStory(@PathVariable("storyId") String storyId){
        storyService.deleteStory(storyId);
        return ApiResponse.<String>builder()
                .message("Story deleted successfully!")
                .build();
    }
}
