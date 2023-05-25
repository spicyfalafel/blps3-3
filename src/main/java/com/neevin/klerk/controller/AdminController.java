package com.neevin.klerk.controller;

import com.neevin.klerk.dto.ArticleDto;
import com.neevin.klerk.dto.CommentDto;
import com.neevin.klerk.dto.MessageDto;
import com.neevin.klerk.service.ArticleService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.transaction.SystemException;
import java.util.List;

@Validated
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private ArticleService articleService;

    @GetMapping(path="/article", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getArticlesToReview(
            @RequestParam(value = "page", required = false, defaultValue = "0") int page,
            @RequestParam(value = "per_page_count", required = false, defaultValue = "10") int perPageCount
    ){
        List<ArticleDto> articles = articleService.allArticlesToReview(page, perPageCount);
        return ResponseEntity.ok(articles);
    }

    @PostMapping(path="/article/{id}/block", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> blockArticle(
            @PathVariable @Min(1) @Max(Integer.MAX_VALUE) Integer id
    ){
        articleService.blockArticle(id);
        return ResponseEntity.ok(new MessageDto("ok"));
    }

    @PostMapping(path="/article/{id}/unblock", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> unblockArticle(
            @PathVariable @Min(1) @Max(Integer.MAX_VALUE) Integer id
    ){
        articleService.unblockArticle(id);
        return ResponseEntity.ok(new MessageDto("ok"));
    }


    @PostMapping(path="/article/{id}/approve", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> reviewApprove(
            @PathVariable @Min(1) @Max(Integer.MAX_VALUE) Integer id
    ){
        articleService.approveArticle(id);
        return ResponseEntity.ok(new MessageDto("ok"));
    }

    @PostMapping(path="/article/{id}/sendToRework", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> sendToRework(
            @PathVariable @Min(1) @Max(Integer.MAX_VALUE) Integer id,
            @Valid @RequestBody CommentDto commentDto
    ){
        articleService.sendToRework(id, commentDto);
        return ResponseEntity.ok(new MessageDto("ok"));
    }
}
