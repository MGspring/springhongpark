package com.example.firstproject.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.firstproject.dto.CommentDto;
import com.example.firstproject.entity.Article;
import com.example.firstproject.entity.Comment;
import com.example.firstproject.repository.ArticleRepository;
import com.example.firstproject.repository.CommentRepository;

@Service
public class CommentService {

	@Autowired
	private CommentRepository commentRepository;
	@Autowired
	private ArticleRepository articleRepository;

	public List<CommentDto> comments(Long articleId) {
		// 반환
		return commentRepository.findByArticleId(articleId)
			.stream()
			.map(comment -> CommentDto.createCommentDto(comment))
			.collect(Collectors.toList());
	}

	@Transactional
	public CommentDto create(Long articleId, CommentDto dto) {
		// 게시글 조회 예외 발생
		Article article = articleRepository.findById(articleId)
			.orElseThrow(() -> new IllegalArgumentException("댓글 생성 실패"));

		// 댓글 엔티티 생성
		Comment comment = Comment.createComment(dto, article);
		// 댓글 엔티티를 DB로 저장
		Comment created = commentRepository.save(comment);

		// DTO로 바꿔서 반환
		return CommentDto.createCommentDto(created);
	}

	@Transactional
	public CommentDto update(Long id, CommentDto dto) {
		// 댓글 조회 및 예외 발상
		Comment target = commentRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("댓글 수정 실패"));

		// 댓글 수정
		target.patch(dto);

		// DB로 갱신
		Comment updated = commentRepository.save(target);

		// 댓글 entity를 dto로 변환후 반환
		return CommentDto.createCommentDto(updated);
	}

	@Transactional
	public CommentDto delete(Long id) {
		// 댓글 조회 및 예외 발생
		Comment target = commentRepository.findById(id)
			.orElseThrow(() -> new IllegalArgumentException("댓글 삭제 실패 대상이 없음"));
		
		// 댓글 삭제
		commentRepository.delete(target);
		
		// 삭제 댓글 DTO로 반환
		return CommentDto.createCommentDto(target);
	}
}
